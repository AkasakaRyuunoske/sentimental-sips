package sentimental_sips.application.sentimentalsips.Model.DAO;

import sentimental_sips.application.sentimentalsips.Model.Entity.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaDAO {

    Categoria findCategoriaId(int categoria_id);
    Categoria findCategoriaNome(String nome);

    List<Categoria> findAllCategoria();

    void saveCategoria(Categoria categoria);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(Categoria categoria);
}
