package fr.vannes.ScrapImmo.model.scraper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import fr.vannes.ScrapImmo.model.ScrapingResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelogerScraper {
    private long delayBetweenRequests;
    private String baseUrl;

    public SelogerScraper(String baseUrl, long delayBetweenRequests) {
        this.baseUrl = baseUrl;
        this.delayBetweenRequests = delayBetweenRequests;
    }

    public List<ScrapingResult> scrape() {
        List<ScrapingResult> results = new ArrayList<>();

        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            HtmlPage pageListe = webClient.getPage(baseUrl);
            List<HtmlElement> annonces = pageListe.getByXPath("//div[@data-testid='sl.explore.card-container']");

            for (HtmlElement annonce : annonces) {
                String titre = "";
                String ville = "";
                String prixTexte = "";
                double surface = 0.0;
                String imageUrl = "";
                String annonceUrl = "";
                String description = "";


                // Extraire les informations à partir de l'élément "annonce"
                HtmlElement titreElement = annonce.getFirstByXPath(".//div[@data-test='sl.address']");
                if (titreElement != null) {
                    titre = titreElement.getTextContent().trim();
                }

                HtmlElement villeElement = annonce.getFirstByXPath(".//div[@data-test='sl.title']");
                if (villeElement != null) {
                    ville = villeElement.getTextContent().trim();
                }

                HtmlElement prixElement = annonce.getFirstByXPath(".//div[@data-test='sl.price-label']");
                if (prixElement != null) {
                    prixTexte = prixElement.getTextContent().trim();
                }

                HtmlElement surfaceElement = annonce.getFirstByXPath(".//ul[@data-test='sl.tagsLine']/li[contains(text(), 'm²')]");
                if (surfaceElement != null) {
                    surface = Double.parseDouble(surfaceElement.getTextContent().replaceAll("[^\\d.]", ""));
                }

                HtmlImage imageElement = annonce.getFirstByXPath(".//img");
                if (imageElement != null) {
                    imageUrl = imageElement.getAttribute("src");
                }

                // extraction pour l'URL de l'annonce
                HtmlAnchor linkElement = annonce.getFirstByXPath(".//a[@data-testid='sl.explore.coveringLink']");
                if (linkElement != null) {
                    String relativeUrl = linkElement.getHrefAttribute();
                    String baseUrl = "https://www.seloger.com";
                    annonceUrl = baseUrl + relativeUrl;
                }

                // Extraction de la description
                HtmlElement descriptionElement = annonce.getFirstByXPath(".//p[@data-testid='sl.explore.card-description']");
                if (descriptionElement != null) {
                    description = descriptionElement.getTextContent().trim();
                }

                // Création de l'objet ScrapingResult avec toutes les informations
                ScrapingResult result = new ScrapingResult(titre, ville, prixTexte, Double.toString(surface), imageUrl, description, annonceUrl);
                results.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
