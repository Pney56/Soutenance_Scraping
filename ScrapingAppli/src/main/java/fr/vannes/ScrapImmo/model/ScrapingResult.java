package fr.vannes.ScrapImmo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class ScrapingResult {
    private final SimpleStringProperty ville = new SimpleStringProperty();
    private final SimpleStringProperty type = new SimpleStringProperty();
    private final SimpleStringProperty prix = new SimpleStringProperty();
    private final SimpleStringProperty surface = new SimpleStringProperty();
    private final SimpleStringProperty annonceUrl = new SimpleStringProperty(); // Attribut ajouté pour l'URL de l'annonce
    private String imageUrl;
    private transient Image image;
    private final SimpleStringProperty description = new SimpleStringProperty();

    // Constructeur mis à jour
    public ScrapingResult(String ville, String type, String prix, String surface, String imageUrl, String description, String annonceUrl) {
        this.ville.set(ville);
        this.type.set(type);
        this.prix.set(prix);
        this.surface.set(surface);
        this.imageUrl = imageUrl;
        this.description.set(description);
        this.annonceUrl.set(annonceUrl);
    }

    // Getters et setters pour l'URL de l'annonce
    public String getAnnonceUrl() {
        return annonceUrl.get();
    }

    public SimpleStringProperty annonceUrlProperty() {
        return annonceUrl;
    }

    public void setAnnonceUrl(String annonceUrl) {
        this.annonceUrl.set(annonceUrl);
    }

    public String getVille() {
        return ville.get();
    }

    public SimpleStringProperty villeProperty() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getPrix() {
        return prix.get();
    }

    public SimpleStringProperty prixProperty() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix.set(prix);
    }

    public String getSurface() {
        return surface.get();
    }

    public SimpleStringProperty surfaceProperty() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface.set(surface);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Image getImage() {
        if (image == null && imageUrl != null && !imageUrl.isEmpty()) {
            try {
                String fullImageUrl = determineFullImageUrl(imageUrl); // Utilisez la propriété existante de la classe
                image = new Image(fullImageUrl, true); // 'true' pour le chargement en arrière-plan
            } catch (IllegalArgumentException e) {
                System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
                // Retourne null ou une image par défaut si l'URL est invalide
                return null;
            }
        }
        return image;
    }

    private String determineFullImageUrl(String imageUrl) {
        if (!imageUrl.startsWith("http")) {
            // Pour OuestFranceImmo, ajoutez le domaine si l'URL est relative
            return "https://www.ouestfrance-immo.com" + imageUrl;
        }
        // Pour Seloger, l'URL est déjà absolue
        return imageUrl;
    }

    @Override
    public String toString() {
        return "ScrapingResult{" +
                "ville=" + ville +
                ", type=" + type +
                ", prix=" + prix +
                ", surface=" + surface +
                ", imageUrl='" + imageUrl + '\'' +
                ", description=" + description +
                ", annonceUrl=" + annonceUrl + // Ajout de l'URL de l'annonce dans toString
                '}';
    }
}
