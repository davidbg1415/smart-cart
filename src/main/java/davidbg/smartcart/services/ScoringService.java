package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.Product;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * שירות לחישוב ציוני התאמה (Matching Engine).
 * מחלקה זו אחראית על ניתוח ההעדפות של המשתמש כפי שהוגדרו בשאלון הדינמי,
 * ושקלולן מול תגיות המאפיינים של מוצרי החנות לקבלת ציון התאמה באחוזים.
 * @author DAVID BEN GIGI
 */
@Service
public class ScoringService 
{
    /**
     * ביצוע חישוב ציוני התאמה עבור רשימת מוצרים נתונה.
     * האלגוריתם מבצע נורמליזציה של הבחירות ומחשב את הציון לפי יחס ההתאמות בפועל.
     * * @param products רשימת המוצרים שיש לדרג.
     * @param selectedTags רשימת התגיות שהמשתמש בחר בשאלון ההעדפות.
     */
    public void calculateScoresBySurvey(List<Product> products, List<String> selectedTags) 
    {
        for (Product p : products) 
        {
            List<String> productTags = p.getTags();

            // במידה ולמוצר אין תגיות, הציון נקבע כ-0 (לא ניתן לבצע התאמה)
            if (productTags == null || productTags.isEmpty()) 
            {
                p.setTemporaryScore(0.0);
                continue;
            }

            double currentScore = 0; // מונה התאמות בפועל
            double maxScore = 0;     // מכנה (סך האילוצים הממשיים)

            // מעבר על העדפות המשתמש וסינון העדפות כלליות
            for (String userTag : selectedTags) 
            {
                // סינון ערכים ריקים או העדפה גנרית ("הכל") שאינה דורשת סינון
                if (userTag == null || userTag.trim().isEmpty() || userTag.equals("הכל")) 
                {
                    continue;
                }

                maxScore += 1.0; // עדכון הרף למכנה עבור כל אילוץ משמעותי

                // בדיקה האם התגית שהמשתמש בחר קיימת במוצר הנוכחי
                if (hasTag(productTags, userTag)) 
                {
                    currentScore += 1.0;
                }
            }

            // חישוב האחוז הסופי (במקרה שאין אילוצים, הציון הוא 100% כברירת מחדל)
            double finalResult = (maxScore == 0) ? 100.0 : (currentScore / maxScore) * 100;

            // עיגול התוצאה לדיוק של ספרה אחת אחרי הנקודה ושמירת הציון במוצר
            p.setTemporaryScore(Math.round(finalResult * 10.0) / 10.0);
        }
    }

    /**
     * פונקציית עזר לביצוע השוואת תגיות בטוחה (Case-Insensitive).
     * מתעלמת מרווחים מיותרים ומאותיות גדולות/קטנות כדי למנוע טעויות הקלדה.
     * * @param tags רשימת התגיות הקיימות במוצר.
     * @param value התגית אותה מחפשים (מהעדפות המשתמש).
     * @return true במידה ונמצאה התאמה, false אחרת.
     */
    public boolean hasTag(List<String> tags, String value) 
    {
        for (String tag : tags) 
        {
            // השוואה עמידה לרווחים ואותיות (Case-Insensitive)
            if (tag != null && tag.trim().equalsIgnoreCase(value.trim())) 
            {
                return true;
            }
        }
        return false;
    }
}