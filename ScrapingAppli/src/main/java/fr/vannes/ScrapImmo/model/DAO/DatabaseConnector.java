package fr.vannes.ScrapImmo.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class DatabaseConnector implements DatabaseConnectorDAO {

    @Override
    public CompletableFuture<Connection> getConnectionAsync(String url, String user, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Établir une connexion à la base de données
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                // Log l'erreur pour le débogage
                System.err.println("Erreur de connexion à la base de données: " + ex.getMessage());
                // Lancer une exception avec un message générique pour l'utilisateur
                throw new RuntimeException("Erreur de connexion à la base de données. Veuillez vérifier vos paramètres.");
            }
        });
    }
}

