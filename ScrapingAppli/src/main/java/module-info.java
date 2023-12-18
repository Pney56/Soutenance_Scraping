module fr.vannes.gretajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires htmlunit;
    requires sib.api.v3.sdk;

    opens fr.vannes.ScrapImmo to javafx.fxml;
    exports fr.vannes.ScrapImmo.model;
    exports fr.vannes.ScrapImmo.controller;

    // Ouvrir le package fr.vannes.gretajavafx.controller à javafx.fxml
    opens fr.vannes.ScrapImmo.controller to javafx.fxml;
    exports fr.vannes.ScrapImmo.model.DAO;
    exports fr.vannes.ScrapImmo.model.scraper;
    exports fr.vannes.ScrapImmo.root;
}
