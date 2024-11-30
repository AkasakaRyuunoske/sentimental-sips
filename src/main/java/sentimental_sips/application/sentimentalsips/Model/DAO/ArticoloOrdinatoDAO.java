package sentimental_sips.application.sentimentalsips.Model.DAO;

import sentimental_sips.application.sentimentalsips.Model.Entity.ArticoloOrdinato;

import java.util.List;

public interface ArticoloOrdinatoDAO {

    ArticoloOrdinato findArticoloOrdinatoId(int articolo_ordinato_id);

    List<ArticoloOrdinato> findAllArticoloOrdinato();

    void saveArticoloOrdinato(ArticoloOrdinato articolo_ordinato);

    void updateArticoloOrdinato(ArticoloOrdinato articolo_ordinato);

    void deleteArticoloOrdinato(ArticoloOrdinato articolo_ordinato);
}
