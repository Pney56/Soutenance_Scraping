package fr.vannes.ScrapImmo.model.DAO;


import fr.vannes.ScrapImmo.model.ScrapingResult;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class AdresseSQLImpl implements AdresseDAO {

    // Mapping des noms de villes aux identifiants de ville
    private static final Map<String, Integer> cityToIdMap = new HashMap<>();
    static {
        cityToIdMap.put("Vannes", 1);
        cityToIdMap.put("Lorient", 2);
        cityToIdMap.put("Quimper", 3);
        cityToIdMap.put("Brest", 4);
        cityToIdMap.put("Rennes", 5);
        cityToIdMap.put("Saint-Brieuc", 6);
    }

    // Mapping des types de bien aux identifiants de type de bien
    private static final Map<String, Integer> typeToIdMap = new HashMap<>();
    static {
        typeToIdMap.put("maison", 1);
        typeToIdMap.put("manoir", 2);
        typeToIdMap.put("parking", 3);
        typeToIdMap.put("immeuble", 4);
    }

    @Override
    public void insertScrapingResult(ScrapingResult result, Connection conn) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO bien_immobilier (titreBien, prixBien, superficieBien, photoBien, descriptionBien, siteBien, idTypeBien, idLocalisationBien, urlAnnonce) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            System.out.println("Préparation de l'insertion pour : " + result);

            // Nettoyage du prix
            String rawPrice = result.getPrix();
            String cleanedPrice = rawPrice.replaceAll("[^\\d.]", "");
            BigDecimal price = new BigDecimal(cleanedPrice);

            // Nettoyage de la ville et obtention de l'ID
            int cityId = getCityId(result.getVille());

            // Nettoyage du type de bien et obtention de l'ID
            int typeBienId = getTypeBienId(result.getType());

            // Nettoyage de la superficie
            String rawSurface = result.getSurface();
            String cleanedSurface = rawSurface.replaceAll("[^\\d.]", "");
            BigDecimal surface = new BigDecimal(cleanedSurface);

            // Configurer les valeurs à partir de l'objet 'result'
            pstmt.setString(1, result.getVille());
            pstmt.setBigDecimal(2, price);
            pstmt.setBigDecimal(3, surface);
            pstmt.setString(4, result.getImageUrl());
            pstmt.setString(5, result.getDescription());
            pstmt.setString(6, "URL du site");      // À déterminer
            pstmt.setInt(7, typeBienId);            // ID du type de bien nettoyé
            pstmt.setInt(8, cityId);                // ID de la ville nettoyée
            pstmt.setString(9, result.getAnnonceUrl()); // Ajout de l'URL de l'annonce

            pstmt.executeUpdate();
            System.out.println("Données insérées avec succès pour : " + result);
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion des données : " + e.getMessage());
        }
    }

    // Méthode pour obtenir l'ID du type de bien à partir du nom brut
    private int getTypeBienId(String rawType) {
        for (Map.Entry<String, Integer> entry : typeToIdMap.entrySet()) {
            if (rawType.toLowerCase().contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return -1; // ou une autre valeur pour indiquer un type inconnu
    }

    // Méthode pour obtenir l'ID de la ville à partir du nom brut
    private int getCityId(String rawCityName) {
        for (Map.Entry<String, Integer> entry : cityToIdMap.entrySet()) {
            if (rawCityName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return -1; // ou une autre valeur pour indiquer une ville inconnue
    }
}
