package davidbg.smartcart.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * שכבת השירות (Service Layer) לשירותי מזג אוויר חיצוניים.
 * מחלקה זו מתקשרת עם API חיצוני (OpenWeatherMap) כדי לקבל נתוני טמפרטורה בזמן אמת,
 * ומתרגמת אותם לתגיות (Tags) המאפשרות למערכת להמליץ על פריטי לבוש מותאמים אישית.
 * @author DAVID BEN GIGI
 */
@Service
public class WeatherService 
{

    // מפתח ה-API לגישה לשירות החיצוני
    private final String API_KEY = "dd9ba3f4901e8ad969b3473efdffd16c"; 
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * שליפת טמפרטורה נוכחית עבור עיר ספציפית מה-API החיצוני.
     * @param city שם העיר לחיפוש.
     * @return טמפרטורה במעלות צלזיוס, או ערך ברירת מחדל (22.0) במקרה של שגיאה.
     */
    public double getTemperature(String city) 
    {
        try 
        {
            // בניית ה-URL עם פרמטרים של עיר, מפתח API והגדרת יחידות צלזיוס
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
            
            // שליחת בקשת HTTP GET וקבלת התגובה כ-JSON
            String jsonResponse = restTemplate.getForObject(url, String.class);
            
            // ניתוח (Parsing) של ה-JSON כדי לחלץ את הטמפרטורה
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            
            // נתיב הגישה בתוך ה-JSON לטמפרטורה תחת האובייקט 'main'
            return root.path("main").path("temp").asDouble();
            
        } 
        catch (Exception e) 
        {
            // תיעוד השגיאה במקרה של כשל בתקשורת (למשל עיר לא קיימת או שרת API זמין)
            System.out.println("שגיאה בשליפת מזג האוויר: " + e.getMessage());
            return 22.0; // ערך גיבוי למניעת קריסת המערכת
        }
    }

    /**
     * תרגום הטמפרטורה לתגית (Tag) המייצגת את עונת השנה.
     * מאפשר סינון מוצרים במערכת לפי התאמה אקלימית.
     * @param temp הטמפרטורה שנמדדה.
     * @return מחרוזת המייצגת את העונה (Winter/Spring/Summer).
     */
    public String getWeatherTag(double temp) 
    {
        if (temp < 15) 
        {
            return "Winter"; 
        } 
        else if (temp >= 15 && temp <= 24) 
        {
            return "Spring"; 
        } 
        else 
        {
            return "Summer"; 
        }
    }

    /**
     * מתודת עזר להצגת שם העונה בעברית בממשק המשתמש (UI).
     * תורמת לשיפור חוויית המשתמש המקומית.
     * @param englishTag שם העונה באנגלית כפי שנשמר במסד.
     * @return שם העונה בעברית.
     */
    public String getWeatherHebrewName(String englishTag) 
    {
        switch (englishTag) 
        {
            case "Winter": return "חורף";
            case "Spring": return "עונת מעבר";
            case "Summer": return "קיץ";
            default: return "מותאם";
        }
    }
}