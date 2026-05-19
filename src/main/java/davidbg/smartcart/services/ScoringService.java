package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.Product;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * שירות חישוב ציוני התאמה בשיטה הריאליסטית והדינמית.
 * הציון משקף התאמה מתמטית טהורה לפי אחוז התגיות שנבחרו בשאלון.
 */
@Service
public class ScoringService 
{

    public void calculateScoresBySurvey(List<Product> products, List<String> selectedTags) 
    {
        for (Product p : products) 
        {
            List<String> productTags = p.getTags();

            if (productTags == null || productTags.isEmpty()) 
            {
                p.setTemporaryScore(0.0);
                continue;
            }

            double currentScore = 0;
            double maxScore = 0;

            // ריצה על כל התגיות שהמשתמש בחר בשאלון הדינמי
            for (String userTag : selectedTags) 
            {
                // אם המשתמש בחר "הכל" או לא בחר ערך, לא מחשיבים את זה כאילוץ שמוריד ציון
                if (userTag == null || userTag.trim().isEmpty() || userTag.equals("הכל")) 
                {
                    continue;
                }

                maxScore += 1.0; // כל אילוץ ממשי מעלה את הרף של המכנה

                // בדיקה בטוחה האם התגית שנבחרה קיימת ברשימת התגיות של הבגד
                if (hasTag(productTags, userTag)) 
                {
                    currentScore += 1.0;
                }
            }

            // חישוב האחוז הסופי: אם המשתמש לא סינן כלום, הציון הוא 100% כברירת מחדל
            double finalResult = (maxScore == 0) ? 100.0 : (currentScore / maxScore) * 100;

            // עיגול לספרה אחת כדי לשמור על מראה מקצועי (למשל 66.7%)
            p.setTemporaryScore(Math.round(finalResult * 10.0) / 10.0);
        }
    }

    /**
     * פונקציית עזר לבדיקה בטוחה (Case-Insensitive) האם תגית קיימת ברשימה.
     */
    private boolean hasTag(List<String> tags, String value) 
    {
        for (String tag : tags) 
        {
            if (tag != null && tag.trim().equalsIgnoreCase(value.trim())) 
            {
                return true;
            }
        }
        return false;
    }
}