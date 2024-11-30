package sentimental_sips.application.sentimentalsips.Model.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import sentimental_sips.application.sentimentalsips.Controller.ProductPaginationController;
import sentimental_sips.application.sentimentalsips.Model.DAOImpl.CategoriaDAOImpl;
import sentimental_sips.application.sentimentalsips.Model.DAOImpl.ImmagineDAOImpl;
import sentimental_sips.application.sentimentalsips.Model.DAOImpl.ProdottoDAOImpl;
import sentimental_sips.application.sentimentalsips.Model.Entity.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarelloService {
    /**
     * Esegue le validazioni neccessarie e aggiunge il prodotto nel carello in caso le validazioni vanno a buon fine.
     * Altrimenti ritorna errore al cliente
     *
     * @param request la request passata dal controller
     * @param response la response passata dal controller
     * */
    public void addProdotto(HttpServletRequest request, HttpServletResponse response){

        // contiene il contenuto del body della request
        String body = null;
        try {
            body = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);
        } catch (IOException e) {
            System.out.println("Errore nella lettura del corpo nel carello service [POST]");
            response.setStatus(400);
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(body);

        // todo mettere parsing per id
        int prodotto_id = jsonObject.getInt("prodotto_id");
        int prodotto_quantita = Integer.parseInt(jsonObject.getString("prodotto_quantita"));
        String prodotto_nome = jsonObject.getString("prodotto_nome");

        String categoria_nome = jsonObject.getString("prodotto_categoria");
        CategoriaDAOImpl categoriaDAOiml = new CategoriaDAOImpl();

        Categoria categoria = categoriaDAOiml.findCategoriaNome(categoria_nome);
        if (categoria == null){
            System.out.println("Non trovata categoria con nome " + categoria_nome + " nel carello service [POST]");
            response.setStatus(404);
            return;
        }

        Carello carello = (Carello) request.getSession(false).getAttribute("carello");

        ProdottoDAOImpl prodottoDAO = new ProdottoDAOImpl();
        Prodotto prodotto_da_aggiungere = prodottoDAO.getProdottoByIdDescrizioneNomeCategoria(prodotto_id, prodotto_nome, categoria.getCategoria_id());

        if (prodotto_da_aggiungere == null){
            response.setStatus(404);
            return;
        }

        if (prodotto_quantita > prodotto_da_aggiungere.getQuantita_inventario()){
            response.setStatus(400);
            return;
        }

        ProdottoNelCarello prodottoNelCarello = new ProdottoNelCarello(prodotto_da_aggiungere, prodotto_quantita);

        if (carello.getProdotti().size() == 0){
            carello.addProdotto(prodottoNelCarello);
        } else {

            List<ProdottoNelCarello> prodotti = carello.getProdotti();
            List<ProdottoNelCarello> daAggiungere = new ArrayList<>();

            boolean productUpdated = false;

            for (ProdottoNelCarello prodotto : prodotti) {
                // per evitare di modificare la lista al interno del ciclo creo una copia che aggiorno dopo l'iterazione
                // QUESTO DEVE ESSERE EVITATO PERCHE PRODUCE java.util.ConcurrentModificationException
                int prodotto_selezionato_id = prodotto.getProdotto().getProdotto_id();
                int prodotto_nel_carello_id = prodottoNelCarello.getProdotto().getProdotto_id();

                // non era stato ancora aggiunto nel carello
                if (prodotto_selezionato_id != prodotto_nel_carello_id) {
                    continue;
                }


                // verifica che la quantita totale data da quantita selezionata prima + selezionata ora sia minore della quantita totale disponibile
                int quantita_selezionata = prodotto.getQuantita_selezionata();
                int quantita_da_aggiungere = prodottoNelCarello.getQuantita_selezionata();
                int totale_da_aggiungere = quantita_selezionata + quantita_da_aggiungere;

                if (totale_da_aggiungere > prodottoNelCarello.getProdotto().getQuantita_inventario()) {
                    // quantita da aggiungere in totale risulta maggiore di quantita disponibile
                    response.setStatus(400);
                    return;
                }

                // se passa tutti i controlli allora e' un prodotto che era stato gia selezionato, allora viene aggiornata la quantita
                // e' il prodotto viene settato come da aggiugnere
                prodotto.setQuantita_selezionata(totale_da_aggiungere);
                productUpdated = true;
            }

            if (!productUpdated) {
                daAggiungere.add(prodottoNelCarello);
            }

            // aggiornare il carello
            for (ProdottoNelCarello prodotto : daAggiungere) {
                carello.addProdotto(prodotto);
            }
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_CREATED); // 201

        String jsonToReturn = "{ \"message\": \"Prodotto aggiunto con successo!\" }";

        // scrive nel body della response oggetto JSON
        ProductPaginationController.writeBody(response, jsonToReturn);

    }

    public void getProdotti(HttpServletRequest request, HttpServletResponse response){
        Carello carello = (Carello) request.getSession(false).getAttribute("carello");

        ArrayList<ProdottoNelCarello> prodotti = carello.getProdotti();
        ArrayList<Immagine> immagini = new ArrayList<>();
        ImmagineDAOImpl immagineDAO = new ImmagineDAOImpl();

        double subtotal = 0;
        int prodotto_id;
        for(ProdottoNelCarello prodotto : prodotti){
            prodotto_id = prodotto.getProdotto().getProdotto_id();
            subtotal += prodotto.getProdotto().getPrezzo() * prodotto.getQuantita_selezionata();

            try {
                immagini.add(immagineDAO.findImmagineByProdottoId(prodotto_id));
                System.out.println(immagineDAO.findImmagineByProdottoId(prodotto_id));

            } catch (SQLException sqlException){
                System.out.println("Caught SQL Exception when querying prodotto immagine by id: " + prodotto_id);
            }

        }

        System.out.println(prodotti);

        request.setAttribute("prodotti", prodotti);
        request.setAttribute("immagini", immagini);
        request.setAttribute("prezzo_totale", subtotal);
    }

    public void deleteProdotto(HttpServletRequest request, HttpServletResponse response) {
        // contiene il contenuto del body della request
        String body = null;
        try {
            body = request.getReader().lines()
                    .reduce("", (accumulator, actual) -> accumulator + actual);
        } catch (IOException e) {
            System.out.println("Errore nella lettura del corpo nel carello service [DELETE]");
            response.setStatus(400);
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(body);

        String prodotto_nome = jsonObject.getString("prodotto_nome");

        Carello carello = (Carello) request.getSession(false).getAttribute("carello");
        ArrayList<ProdottoNelCarello> prodotti = carello.getProdotti();

        // wtffffffff
        prodotti.removeIf(prodotto -> prodotto.getProdotto().getNome().equals(prodotto_nome));

        response.setStatus(204);
    }
}
