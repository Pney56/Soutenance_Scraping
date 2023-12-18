package fr.vannes.ScrapImmo.model.DAO;

import java.sql.Connection;
import java.util.concurrent.CompletableFuture;

public interface DatabaseConnectorDAO {
    CompletableFuture<Connection> getConnectionAsync(String url, String user, String password);
}
