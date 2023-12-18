package fr.vannes.ScrapImmo.model;

import fr.vannes.ScrapImmo.model.DAO.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestConnexionDB {
    public static void main(String[] args) {
        // Paramètres de test
        String testUrl = "jdbc:mysql://localhost:3306/scraping";
        String testUser = "root";
        String testPassword = "";

        // Lancer le test
        testerConnexion(testUrl, testUser, testPassword);
    }

    private static void testerConnexion(String url, String user, String password) {

        // Create an instance of DatabaseConnector
        DatabaseConnector databaseConnector = new DatabaseConnector();


        CompletableFuture<Connection> connectionFuture = databaseConnector.getConnectionAsync(url, user, password);

        connectionFuture.thenAccept(connection -> {
            try {
                if (connection != null) {
                    System.out.println("Connexion réussie à la base de données.");
                    connection.close();
                } else {
                    System.out.println("La connexion a échoué.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }).exceptionally(ex -> {
            System.out.println("Erreur lors de la tentative de connexion : " + ex.getMessage());
            return null;
        });

        // Pour attendre que le processus asynchrone soit terminé avant de finir le programme
        try {
            connectionFuture.get(); // Attendre que la tâche asynchrone soit terminée
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Erreur lors de l'attente de la connexion : " + e.getMessage());
        }
    }
}
