package fr.vannes.ScrapImmo.model.scraper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import fr.vannes.ScrapImmo.model.ScrapingResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OuestFranceImmoScraper {
    private String baseUrl;

    public OuestFranceImmoScraper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<ScrapingResult> scrape() {
        List<ScrapingResult> results = new ArrayList<>();

        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            HtmlPage pageListe = webClient.getPage(baseUrl);
            List<HtmlArticle> annonceContainers = pageListe.getByXPath("//article[@data-t='carte-annonce']");

            for (HtmlArticle container : annonceContainers) {
                DomAttr annonceUrlAttr = container.getFirstByXPath(".//a[contains(@class, 'card-annonce__content__actions__card-link--main-btn')]/@href");
                if (annonceUrlAttr != null) {
                    String annonceUrl = annonceUrlAttr.getValue();
                    annonceUrl = "https://www.ouestfrance-immo.com" + annonceUrl;

                    DomText typeText = container.getFirstByXPath(".//h3[@class='card-annonce__content__title']/p[2]/text()");
                    String type = typeText != null ? typeText.getWholeText().trim() : "";

                    DomText villeText = container.getFirstByXPath(".//h3[@class='card-annonce__content__title']/p[1]/text()");
                    String ville = villeText != null ? villeText.getWholeText().trim() : "";



                    DomText prixText = container.getFirstByXPath(".//span[@class='detail-prix card-annonce__content__price__main']/text()");
                    String prix = prixText != null ? prixText.getWholeText().trim() : "";

                    HtmlImage imgElement = container.getFirstByXPath(".//img[@class='card-annonce__header__img']");
                    String imageUrl = imgElement != null ? imgElement.getAttribute("src") : "";

                    DomText surfaceText = container.getFirstByXPath(".//div[@class='detail-highlights__item badge badge--square']/text()");
                    String surface = surfaceText != null ? surfaceText.getWholeText().trim() : "";

                    DomText descriptionText = container.getFirstByXPath(".//p[@data-v-ce3ef9f4='']/text()");
                    String description = descriptionText != null ? descriptionText.getWholeText().trim() : "";

                    ScrapingResult result = new ScrapingResult(type, ville, prix, surface, imageUrl, description, annonceUrl);
                    results.add(result);
                } else {
                    System.out.println("L'URL de l'annonce est null. Ignoré.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
