package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.SurveyCriteria;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoringService 
{

    /**
     * פונקציה המחשבת ניקוד מנורמל לכל מוצר (0-100) לפי קריטריוני השאלון.
     * הניקוד הזה הוא הערך שיוזן לאלגוריתם החכם עבור כול פריט .
     */
    public void calculateScoresBySurvey(List<Product> products, SurveyCriteria criteria) 
    {
        for (Product p : products) 
        {
            double currentScore = 0;

            // 1. סינונים קשיחים 
            // אם המגדר או הקטגוריה לא מתאימים - הציון הוא 0 והאלגוריתם ידלג עליהם
            if (!p.getTags().contains(criteria.gender)) 
            {
                p.setTemporaryScore(0);
                continue;
            }
            if (criteria.category != null && !criteria.category.equals("הכל") && !p.getTags().contains(criteria.category)) 
            {
                p.setTemporaryScore(0);
                continue;
            }

            // 2. סה"כ מקסימלי: 99 נקודות
            
            // אירוע (30 נקודות)
            if (p.getTags().contains(criteria.occasion)) currentScore += 30.0;

            // עונה (25 נקודות)
            if (p.getTags().contains(criteria.season))   currentScore += 25.0;

            // סגנון (20 נקודות)
            if (p.getTags().contains(criteria.style))    currentScore += 20.0;

            // סוג בד (14 נקודות)
            if (p.getTags().contains(criteria.fabric))   currentScore += 14.0;

            // צבע (5 נקודות)
            if (p.getTags().contains(criteria.color))    currentScore += 5.0;

            // דוגמה (5 נקודות)
            if (p.getTags().contains(criteria.pattern))  currentScore += 5.0;

            // 3. הוספה (עד 1 נקודה)
            //  (Jitter) שמוודא שגם מוצרים זהים יקבלו מיקום שונה מעט במיון
            currentScore += Math.random(); 

            // וידוא סופי שהציון לא חורג מ-100 
            p.setTemporaryScore(Math.min(100.0, currentScore));
        }
    }
}