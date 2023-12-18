package fr.vannes.ScrapImmo.controller;

import fr.vannes.ScrapImmo.model.*;
import fr.vannes.ScrapImmo.model.DAO.AdresseSQLImpl;
import fr.vannes.ScrapImmo.model.DAO.DatabaseConnector;
import fr.vannes.ScrapImmo.model.scraper.OuestFranceImmoScraper;
import fr.vannes.ScrapImmo.model.scraper.OuestFranceImmoUrlGenerator;
import fr.vannes.ScrapImmo.model.scraper.SeLogerUrlGenerator;
import fr.vannes.ScrapImmo.model.scraper.SelogerScraper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HomeController {
    private String storedUser;
    private String storedPassword;
    private String jdbcUrl;

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Définie un nom de fichier par défaut
        fileChooser.setInitialFileName("ScrapingImobilier");

        // Ouvre la boîte de dialogue de sauvegarde
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            // Implémentation de la logique pour écrire les données dans le fichier choisi
            saveDataToFile(file);
        }
    }

    private void saveDataToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (ScrapingResult scrapingResult : resultsTableView.getItems()) {
                writer.println(scrapingResult.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    @FXML
    public void handleSendEmail() {
        // Crée un dialogue pour les informations de l'email
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Envoi Courriel");

        // Ajoute des boutons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Création des labels et champs pour les informations d'email
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField toEmail = new TextField();
        toEmail.setPromptText("Email du destinataire");
        TextField subject = new TextField();
        subject.setPromptText("Objet du mail");

        grid.add(new Label("Email du destinataire:"), 0, 0);
        grid.add(toEmail, 1, 0);
        grid.add(new Label("Objet du mail:"), 0, 1);
        grid.add(subject, 1, 1);

        // Intègre le GridPane dans le dialogue
        dialog.getDialogPane().setContent(grid);

        // Affiche le dialogue et attend la réponse de l'utilisateur
        Optional<String> dialogResult = dialog.showAndWait();

        if (dialogResult.isPresent()) {
            // Configuration de l'API Sendinblue
            ApiClient apiClient = Configuration.getDefaultApiClient();
            ApiKeyAuth apiKey = (ApiKeyAuth) apiClient.getAuthentication("api-key");
            apiKey.setApiKey("YOUR API KEY");

            // Création de l'objet email
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSubject(subject.getText());

            // Définir le contenu du corps de l'e-mail comme texte brut
            sendSmtpEmail.setTextContent("Résultat du scraping");

            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail("nicodh56610@hotmail.fr");
            sendSmtpEmail.setSender(sender);

            SendSmtpEmailTo recipient = new SendSmtpEmailTo();
            recipient.setEmail(toEmail.getText());
            sendSmtpEmail.setTo(Arrays.asList(recipient));

            // Création et ajout de la pièce jointe (résultat du scraping)
            StringBuilder emailBody = new StringBuilder();
            for (ScrapingResult scrapingResult : resultsTableView.getItems()) {
                emailBody.append("Résultat : ").append(scrapingResult.toString()).append("\n\n");
            }
            byte[] emailContentBytes = emailBody.toString().getBytes();
            SendSmtpEmailAttachment attachment = new SendSmtpEmailAttachment();
            attachment.setName("ScrapingResultat.txt"); // Nom du fichier
            attachment.setContent(emailContentBytes); // Contenu du scraping
            sendSmtpEmail.addAttachmentItem(attachment);

            // Envoi de l'email
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            try {
                api.sendTransacEmail(sendSmtpEmail);
                System.out.println("Email envoyé avec succès");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    @FXML
    private TableView<ScrapingResult> resultsTableView;
    @FXML
    private ProgressBar scrapingProgressBar;

    @FXML
    public void initialize() {
        configureImageColumn();
        scrapingProgressBar.setVisible(false);
    }

    private void configureImageColumn() {
        // Trouver la colonne d'image parmi les colonnes existantes
        for (TableColumn<ScrapingResult, ?> column : resultsTableView.getColumns()) {
            if ("Image".equals(column.getText())) {
                // Assumer que c'est la colonne d'image et configurer le cellFactory
                ((TableColumn<ScrapingResult, Image>) column).setCellFactory(tc -> new TableCell<ScrapingResult, Image>() {
                    private final ImageView imageView = new ImageView();

                    {
                        imageView.setFitWidth(300); // défini la largeur
                        imageView.setFitHeight(300); // défini la hauteur
                        imageView.setPreserveRatio(true);
                    }

                    @Override
                    protected void updateItem(Image image, boolean empty) {
                        super.updateItem(image, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            try {
                                // Afficher l'image dans la cellule
                                imageView.setImage(image);
                                setGraphic(imageView);
                            } catch (Exception e) {
                                System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
                                // Si l'image ne peut pas être chargée, ne rien afficher
                                setText(null);
                                setGraphic(null);
                            }
                        }
                    }
                });
                break;
            }
        }
    }


    @FXML
    private void showDatabaseSettingsDialog() {
        // Créer le dialogue
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Paramètres de la base de données");

        // Définir les types de boutons
        ButtonType loginButtonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Créer les labels et champs
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField serverName = new TextField();
        serverName.setPromptText("Nom d'hôte ou adresse IP");
        TextField dbName = new TextField();
        dbName.setPromptText("Nom de la Base de données");
        TextField portNumber = new TextField();
        portNumber.setPromptText("ex:3306");
        TextField username = new TextField();
        username.setPromptText("Nom du login utilisateur");
        PasswordField password = new PasswordField();
        password.setPromptText("Mot de passe utilisateur");

        grid.add(new Label("Nom du serveur:"), 0, 0);
        grid.add(serverName, 1, 0);
        grid.add(new Label("Nom de la BDD:"), 0, 1);
        grid.add(dbName, 1, 1);
        grid.add(new Label("Numéro de Port de la BDD:"), 0, 2);
        grid.add(portNumber, 1, 2);
        grid.add(new Label("Login Utilisateur:"), 0, 3);
        grid.add(username, 1, 3);
        grid.add(new Label("Password Utilisateur:"), 0, 4);
        grid.add(password, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Définir le convertisseur de résultat pour le dialogue
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        // Afficher le dialogue et capturer la réponse
        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(credentials -> {
            this.storedUser = credentials.getKey();
            this.storedPassword = credentials.getValue();
            this.jdbcUrl = "jdbc:mysql://" + serverName.getText() + ":" + portNumber.getText() + "/" + dbName.getText();

            // Ajout de messages de débogage
            System.out.println("Tentative de connexion avec les paramètres suivants :");
            System.out.println("URL: " + jdbcUrl);
            System.out.println("Utilisateur: " + storedUser);
            System.out.println("Mot de passe: " + storedPassword);

            // Create an instance of DatabaseConnector
            DatabaseConnector connector = new DatabaseConnector();

            // Use this instance to call getConnectionAsync
            connector.getConnectionAsync(jdbcUrl, storedUser, storedPassword)
                    .thenAccept(connection -> {
                        System.out.println("Connexion réussie");
                        Platform.runLater(() -> {
                            showAlert("Connexion réussie", "La connexion à la base de données a été établie avec succès.", Alert.AlertType.INFORMATION);
                        });
                        try {
                            connection.close(); // Fermer la connexion après la confirmation
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    })
                    .exceptionally(e -> {
                        System.err.println("Échec de la connexion : " + e.getMessage());
                        Platform.runLater(() -> {
                            showAlert("Échec de la connexion", "Impossible de se connecter à la base de données : " + e.getMessage(), Alert.AlertType.ERROR);
                        });
                        return null;
                    });
        });
    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private ComboBox<String> citySelector;

    @FXML
    private ComboBox<String> propertyTypeSelector;


    @FXML
    private TextField minSurfaceField;
    @FXML
    private TextField maxSurfaceField;
    @FXML
    private TextField minPriceField;
    @FXML
    private TextField maxPriceField;

    @FXML
    private void handleValidation(ActionEvent event) {
        String ville = citySelector.getValue();
        String typeDeBien = propertyTypeSelector.getValue();

        String minSurface = minSurfaceField.getText();
        String maxSurface = maxSurfaceField.getText();
        String minPrice = minPriceField.getText();
        String maxPrice = maxPriceField.getText();

        if (ville != null && typeDeBien != null) {
            // Vider l'affichage existant ici
            clearDisplay();

            scrapingProgressBar.setVisible(true);

            new Thread(() -> {

                if (checkSeloger.isSelected()) {
                    scrapeSeloger(ville, typeDeBien, minPriceField.getText(), maxPriceField.getText(), minSurfaceField.getText(), maxSurfaceField.getText());
                }
                if (checkOuestFrance.isSelected()) {
                    try {
                        scrapeOuestFrance(ville, typeDeBien, minPriceField.getText(), maxPriceField.getText(), minSurfaceField.getText(), maxSurfaceField.getText());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                Platform.runLater(() -> scrapingProgressBar.setVisible(false));
            }).start();
        } else {
            System.err.println("Sélection de ville ou de type de bien manquante !");
        }
    }

    private void clearDisplay() {
        // Réinitialisation de la TableView
        resultsTableView.getItems().clear();
    }


    private void scrapeSeloger(String ville, String typeDeBien, String minPrice, String maxPrice, String minSurface, String maxSurface) {
        SeLogerUrlGenerator urlGenerator = new SeLogerUrlGenerator();
        String url = urlGenerator.generateUrl(ville, typeDeBien, minPrice, maxPrice, minSurface, maxSurface);
        SelogerScraper scraper = new SelogerScraper(url, 1000);
        List<ScrapingResult> results = scraper.scrape();
        Platform.runLater(() -> resultsTableView.getItems().addAll(results));
    }

    private void scrapeOuestFrance(String ville, String typeDeBien, String minPrice, String maxPrice, String minSurface, String maxSurface) throws IOException {
        OuestFranceImmoUrlGenerator urlGenerator = new OuestFranceImmoUrlGenerator();
        String url = urlGenerator.generateOuestFranceImmoUrl(ville, typeDeBien, minPrice, maxPrice, minSurface, maxSurface);
        OuestFranceImmoScraper scraper = new OuestFranceImmoScraper(url);
        List<ScrapingResult> results = scraper.scrape();
        Platform.runLater(() -> resultsTableView.getItems().addAll(results));
    }


    @FXML
    private CheckBox checkSeloger;
    @FXML
    private CheckBox checkOuestFrance;


    private AdresseSQLImpl adresseDB; // Instance de la classe de gestion de base de données


    @FXML
    private void handleSaveToDatabase(ActionEvent event) {
        ObservableList<ScrapingResult> data = resultsTableView.getItems();
        AdresseSQLImpl db = new AdresseSQLImpl();

        System.out.println("Début de l'enregistrement des données dans la base de données...");

        // Create an instance of DatabaseConnector
        DatabaseConnector connector = new DatabaseConnector();

        // Use this instance to call getConnectionAsync
        connector.getConnectionAsync(jdbcUrl, storedUser, storedPassword)
                .thenAccept(connection -> {
            for (ScrapingResult result : data) {
                System.out.println("Enregistrement des données : " + result);
                db.insertScrapingResult(result, connection);
            }
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }).exceptionally(e -> {
            System.err.println("Erreur lors de l'obtention de la connexion : " + e.getMessage());
            return null;
        });
    }





    @FXML
    private void showHelpPopup() {
        // Créez un dialogue
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Aide");

        // Ajoutez un bouton de fermeture
        ButtonType closeButton = new ButtonType("Fermer", ButtonType.OK.getButtonData());
        dialog.getDialogPane().getButtonTypes().setAll(closeButton);

        // Créez un GridPane pour organiser le contenu en trois colonnes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Première colonne pour les options du menu
        TextArea menuOptionsText = new TextArea();
        menuOptionsText.setText("Options du Menu :\n\n" +
                "- Enregistrer dans un fichier : Permet d'enregistrer les résultats dans un fichier.\n" +
                "- Envoi Courriel : Permet d'envoyer les résultats par e-mail.\n" +
                "- Enregistrer dans la base de données : Permet d'enregistrer les résultats dans une base de données.\n" +
                "- Quitter : Permet de quitter l'application.");

        menuOptionsText.setEditable(false);
        menuOptionsText.setWrapText(true);
        grid.add(menuOptionsText, 0, 0);

        // Deuxième colonne pour l'utilisation de l'application
        TextArea appUsageText = new TextArea();
        appUsageText.setText("Utilisation de l'Application :\n\n" +
                "1. Sélectionnez les sources de données (Seloger et/ou OuestFrance).\n" +
                "2. Choisissez une ville et un type de propriété dans les listes déroulantes.\n" +
                "3. Cliquez sur le bouton \"Valider\" pour lancer le scraping.\n" +
                "4. Les résultats seront affichés dans le tableau à droite.\n" +
                "5. Vous pouvez enregistrer les résultats dans un fichier, les envoyer par e-mail ou les enregistrer dans une base de données.\n\n" +
                "N'oubliez pas de configurer la connexion à la base de données dans le menu Paramètres.");

        appUsageText.setEditable(false);
        appUsageText.setWrapText(true);
        grid.add(appUsageText, 1, 0);

        // Troisième colonne pour les paramètres de connexion à la base de données
        TextArea dbConnectionText = new TextArea();
        dbConnectionText.setText("Paramètres de Connexion à la Base de Données :\n\n" +
                "Avant d'utiliser la fonctionnalité d'enregistrement dans la base de données, assurez-vous de configurer les paramètres de connexion à la base de données dans le menu Paramètres.\n\n" +
                "1. Cliquez sur Paramètres dans le menu.\n" +
                "2. Remplissez les champs avec les informations de votre base de données :\n" +
                "   - Nom d'hôte ou adresse IP\n" +
                "   - Nom de la Base de données\n" +
                "   - Numéro de Port de la BDD (par exemple : 3306)\n" +
                "   - Nom du login utilisateur\n" +
                "   - Mot de passe utilisateur\n" +
                "3. Cliquez sur Valider pour établir la connexion.");

        dbConnectionText.setEditable(false);
        dbConnectionText.setWrapText(true);
        grid.add(dbConnectionText, 2, 0);

        // Intégrez le GridPane dans le dialogue
        dialog.getDialogPane().setContent(grid);

        // Affichez le dialogue et attendez la réponse de l'utilisateur
        dialog.showAndWait();
    }

}



