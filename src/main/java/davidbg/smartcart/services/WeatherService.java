package davidbg.smartcart.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    // המפתח הדיפולטיבי שעבד לך עכשיו בדפדפן
    private final String API_KEY = "dd9ba3f4901e8ad969b3473efdffd16c"; 
    private final RestTemplate restTemplate = new RestTemplate();

    public double getTemperature(String city) {
        try {
            // הוספת units=metric קריטית כדי לקבל צלזיוס
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
            
            String jsonResponse = restTemplate.getForObject(url, String.class);
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            
            // ניווט במבנה של OpenWeatherMap: main -> temp
            return root.path("main").path("temp").asDouble();
            
        } catch (Exception e) {
            System.out.println("שגיאה בשליפת מזג האוויר: " + e.getMessage());
            return 22.0; // גיבוי
        }
    }

    public String getWeatherTag(double temp) 
{
    if (temp < 15) {
        return "Winter"; // תגית באנגלית עבור מסד הנתונים
    } else if (temp >= 15 && temp <= 24) {
        return "Autmun"; // או autumn / mid-season בהתאם למה שיש לך ב-DB
    } else {
        return "Summer"; // תגית באנגלית עבור מסד הנתונים
    }
}

/**
 * מתודת עזר חדשה כדי להציג למשתמש כותרת יפה בעברית
 */
public String getWeatherHebrewName(String englishTag) 
{
    switch (englishTag) {
        case "Winter": return "חורף";
        case "Autmun": return "עונת מעבר";
        case "Summer": return "קיץ";
        default: return "מותאם";
    }
}
}