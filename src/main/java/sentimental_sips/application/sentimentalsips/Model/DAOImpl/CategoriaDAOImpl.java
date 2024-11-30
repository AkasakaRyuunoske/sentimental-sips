package sentimental_sips.application.sentimentalsips.Model.DAOImpl;

import sentimental_sips.application.sentimentalsips.Model.DAO.CategoriaDAO;
import sentimental_sips.application.sentimentalsips.Model.Entity.Categoria;
import sentimental_sips.application.sentimentalsips.Model.Entity.Prodotto;
import sentimental_sips.application.sentimentalsips.Model.Service.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    DBConnection db = new DBConnection();
    private Connection connection;

    public CategoriaDAOImpl() {
        try {
            connection = db.getConnection();
        } catch (SQLException sqlException) {
            System.out.println("Failed to create categoriaDaoImpl");
            sqlException.printStackTrace();
        }

    }

    @Override
    public Categoria findCategoriaId(int categoria_id) {


        final String QUERY = "Select * from categoria where categoria_id=" + categoria_id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);

            // non solo vedere se c'e ma anche spostare il cursore
            if (resultSet.next()) {
                Categoria categoria = new Categoria(
                        resultSet.getInt("categoria_id"),
                        resultSet.getString("nome")
                );
                return categoria;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Categoria findCategoriaNome(String nome) {
        String query = "SELECT * FROM categoria WHERE nome = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Categoria(resultSet.getInt("categoria_id"), resultSet.getString("nome"));
            }

        } catch (SQLException e) {
            System.out.println("Categoria con nome " + nome + " non trovata");
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Categoria> findAllCategoria() {
        final String QUERY = "SELECT * FROM categoria";

        try {
            PreparedStatement statement = connection.prepareStatement(QUERY);

            ResultSet resultSet = statement.executeQuery();

            List<Categoria> categorie = new ArrayList<>();

            while (resultSet.next()) {
                categorie.add(new Categoria(
                        resultSet.getInt("categoria_id"),
                        resultSet.getString("nome")));
            }
            return categorie;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCategoria(Categoria categoria) {

    }

    @Override
    public void updateCategoria(Categoria categoria) {

    }

    @Override
    public void deleteCategoria(Categoria categoria) {

    }
}
