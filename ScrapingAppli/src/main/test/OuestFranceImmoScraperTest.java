import fr.vannes.ScrapImmo.model.scraper.OuestFranceImmoScraper;
import fr.vannes.ScrapImmo.model.ScrapingResult;

import java.io.IOException;
import java.util.List;

public class OuestFranceImmoScraperTest {

    public static void main(String[] args) {
        // Remplacez cette URL par une URL valide du site OuestFrance Immo pour le test
        String url = "https://www.ouestfrance-immo.com/acheter/maison/vannes-56-56000/";

        System.out.println("URL utilisée pour le test : " + url);

        OuestFranceImmoScraper scraper = new OuestFranceImmoScraper(url);
        List<ScrapingResult> results = scraper.scrape();
        System.out.println("Nombre d'annonces trouvées : " + results.size());

        for (ScrapingResult result : results) {
            System.out.println("Titre : " + result.getType());
            System.out.println("Ville : " + result.getVille());
            System.out.println("Prix : " + result.getPrix());
            System.out.println("Surface : " + result.getSurface());
            System.out.println("Image URL : " + result.getImageUrl());
            System.out.println("Description : " + result.getDescription());
            System.out.println("URL Annonce : " + result.getAnnonceUrl());
            System.out.println("---------------------------------------");
        }
    }
}
