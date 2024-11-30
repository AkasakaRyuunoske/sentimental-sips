package sentimental_sips.application.sentimentalsips.Model.DAO;

import sentimental_sips.application.sentimentalsips.Model.Entity.Ordine;

import java.sql.SQLException;
import java.util.List;

public interface OrdineDAO {

    Ordine findOrdineId(int ordine_id);

    List<Ordine> findAllOrdine();

    void saveOrdine(Ordine ordine) throws SQLException;

    void updateOrdine(Ordine ordine);

    void deleteOrdine(Ordine ordine);

    Ordine findLastOrderByUserId(int utenteId);
}
