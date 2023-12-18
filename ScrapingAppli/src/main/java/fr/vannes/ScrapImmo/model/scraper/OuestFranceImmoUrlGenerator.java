package fr.vannes.ScrapImmo.model.scraper;

import java.util.HashMap;
import java.util.Map;

public class OuestFranceImmoUrlGenerator {
    private static final Map<String, String> cityMapping = new HashMap<>();
    private static final Map<String, String> propertyTypeMapping = new HashMap<>();

    static {
        // Mapping pour les villes (noms utilisés dans l'IHM -> codes/abréviations réels)
        cityMapping.put("Vannes", "vannes-56-56000");
        cityMapping.put("Lorient", "lorient-56-56100");
        cityMapping.put("Brest", "brest-29-29200");
        cityMapping.put("Quimper", "quimper-29-29000");
        cityMapping.put("Saint-Brieuc", "saint-brieuc-22-22000");
        cityMapping.put("Rennes", "rennes-35-35000");

        // Mapping pour les types de biens (noms utilisés dans l'IHM -> codes/abréviations réels)
        propertyTypeMapping.put("Maison", "maison");
        propertyTypeMapping.put("Appartement", "appartement");
        propertyTypeMapping.put("Parking", "garage");
        propertyTypeMapping.put("Immeuble", "immeuble");
    }

    public String generateOuestFranceImmoUrl(String city, String propertyType, String minPrice, String maxPrice, String minSurface, String maxSurface) {
        String cityCode = cityMapping.get(city);
        String propertyTypeCode = propertyTypeMapping.get(propertyType);

        if (cityCode == null || propertyTypeCode == null) {
            throw new IllegalArgumentException("Ville ou type de bien invalide");
        }

        return String.format("https://www.ouestfrance-immo.com/acheter/%s/%s/?prix=%s_%s&surface=%s_%s",
                propertyTypeCode, cityCode, minPrice, maxPrice, minSurface, maxSurface);
    }

}
