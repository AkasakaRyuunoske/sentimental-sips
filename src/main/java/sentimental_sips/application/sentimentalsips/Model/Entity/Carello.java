package sentimental_sips.application.sentimentalsips.Model.Entity;

import java.util.ArrayList;

public class Carello {
    private ArrayList<ProdottoNelCarello> prodotti = new ArrayList<>();

    public void addProdotto(ProdottoNelCarello prodotto){
        prodotti.add(prodotto);
    }

    public ArrayList<ProdottoNelCarello> getProdotti(){
        return prodotti;
    }

    @Override
    public String toString() {
        return "Carello{" +
                "prodotti=" + prodotti +
                '}';
    }

    public int size(){
        return prodotti.size();
    }

    public double getTotal(){
        double total = 0.0;

        for(ProdottoNelCarello prodotto : prodotti){
            total += prodotto.getProdotto().getPrezzo() * prodotto.getQuantita_selezionata();
        }

        return total;
    }
}
