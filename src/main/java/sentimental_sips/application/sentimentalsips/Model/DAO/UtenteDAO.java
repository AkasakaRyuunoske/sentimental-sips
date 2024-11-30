package sentimental_sips.application.sentimentalsips.Model.DAO;

import sentimental_sips.application.sentimentalsips.Model.Entity.Utente;

import java.sql.SQLException;
import java.util.List;

public interface UtenteDAO {

    Utente findUtenteById(int utente_id);

    Utente findUtenteByEmail(String email);

    List<Utente> findAllUtente();

    Utente getUtenteEmailAndPasswordByEmail(String email);

    boolean isEmailExists(String email);

    void saveUtente(Utente utente) throws SQLException;

    void updateUtente(Utente utente) throws SQLException;

    void deleteUtente(Utente utente);
}
