package fr.vannes.ScrapImmo.model.scraper;

import java.util.HashMap;
import java.util.Map;

public class SeLogerUrlGenerator {
    private static final Map<String, String> cityMapping = new HashMap<>();
    private static final Map<String, String> propertyTypeMapping = new HashMap<>();

    static {
        // Mapping pour les villes
        cityMapping.put("Vannes", "560260");
        cityMapping.put("Lorient", "560121");
        cityMapping.put("Quimper", "290232");
        cityMapping.put("Brest", "290019");
        cityMapping.put("Saint-Brieuc", "220278");
        cityMapping.put("Rennes", "350238");

        // Mapping pour les types de biens
        propertyTypeMapping.put("Maison", "2");
        propertyTypeMapping.put("Appartement", "1");
        propertyTypeMapping.put("Parking", "3");
        propertyTypeMapping.put("Immeuble", "11");
    }

    public String generateUrl(String city, String propertyType, String minPrice, String maxPrice, String minSurface, String maxSurface) {
        String cityCode = cityMapping.get(city);
        String propertyTypeCode = propertyTypeMapping.get(propertyType);

        if (cityCode == null || propertyTypeCode == null) {
            throw new IllegalArgumentException("Ville ou type de bien invalide");
        }

        return String.format("https://www.seloger.com/list.htm?projects=2,5&types=%s&natures=1,2,4&places=[{%22inseeCodes%22:[%s]}]&price=%s/%s&surface=%s/%s&mandatorycommodities=0&enterprise=0&qsVersion=1.0&m=search_refine-redirection-search_results",
                propertyTypeCode, cityCode, minPrice, maxPrice, minSurface, maxSurface);
    }

}
