package sentimental_sips.application.sentimentalsips.Model.DAO;

import sentimental_sips.application.sentimentalsips.Model.Entity.Categoria;
import sentimental_sips.application.sentimentalsips.Model.Entity.Prodotto;

import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAO {

    Prodotto findProdottoId(int prodotto_id) throws SQLException;

    List<Prodotto> findAllProdotto();

    void saveProdotto(Prodotto prodotto);

    void updateProdotto(Prodotto prodotto);

    void deleteProdotto(Prodotto prodotto);

    Prodotto getProdottoByIdDescrizioneNomeCategoria(int id, String nome, int categoria);
    List<Prodotto> fetchPaginatedProdotti(int offset);
}
