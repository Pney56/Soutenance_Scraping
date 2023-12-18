import fr.vannes.ScrapImmo.model.scraper.SelogerScraper;
import fr.vannes.ScrapImmo.model.ScrapingResult;

import java.util.List;

public class SelogerScraperTest {

    public static void main(String[] args) {
        // Utilise l'URL spécifiée pour le test
        String baseUrl = "https://www.seloger.com/list.htm?tri=initial&enterprise=0&idtypebien=2&idtt=2,5&naturebien=1,2,4&ci=560260&m=search_hp_new";
        System.out.println("URL de base : " + baseUrl);

        // Créez une instance de SelogerScraper avec l'URL de base et un délai entre les requêtes
        SelogerScraper scraper = new SelogerScraper(baseUrl, 1000); // 1000 ms delay between requests
        try {
            List<ScrapingResult> results = scraper.scrape();
            System.out.println("Nombre d'annonces trouvées : " + results.size());

            for (ScrapingResult result : results) {
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
