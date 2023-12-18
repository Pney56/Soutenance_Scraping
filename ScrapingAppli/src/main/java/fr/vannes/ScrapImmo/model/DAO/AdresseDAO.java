package fr.vannes.ScrapImmo.model.DAO;

import fr.vannes.ScrapImmo.model.ScrapingResult;
import java.sql.Connection;

public interface AdresseDAO {
    void insertScrapingResult(ScrapingResult result, Connection conn);
}
