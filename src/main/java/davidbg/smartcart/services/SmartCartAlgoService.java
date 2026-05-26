package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * מחלקה המייצגת את מנוע האופטימיזציה של המערכת.
 * המחלקה מממשת אלגוריתם תכנון דינמי (Dynamic Programming) לפתרון בעיית תרמיל הגב (Knapsack Problem).
 * תפקידה הוא לבנות חבילת מוצרים אופטימלית עבור המשתמש בהתבסס על תקציב, הגבלת כמות וציוני התאמה.
 * @author DAVID BEN GIGI
 */
@Service
public class SmartCartAlgoService 
{
    /**
     * ביצוע אופטימיזציה לבניית חבילת מוצרים 
     * האלגוריתם משקלל את ציון ההתאמה של כל מוצר מול המחיר שלו תחת אילוצי כמות ותקציב
     * * @param items רשימת כל המוצרים הזמינים לבחירה
     * @param budgetUnits סך התקציב המוקצה (ביחידות של עשיריות שקל)
     * @param minItems מספר פריטים מינימלי הנדרש בחבילה
     * @param maxItems מספר פריטים מקסימלי שניתן לכלול בחבילה
     * @return רשימת מוצרים שנבחרו כחבילה האופטימלית ביותר עבור המשתמש
     */
    public List<Product> getOptimalBundle(List<Product> items, int budgetUnits, int minItems, int maxItems) 
    {
        int n = items.size();
        // טבלה תלת ממדית: [אינדקס פריט][כמות פריטים שנבחרו][תקציב שנוצל]
        double[][][] dp = new double[n + 1][maxItems + 1][budgetUnits + 1];

        // אתחול הטבלה בערך -1 לסימון מצבים שאינם ברי-השגה
        for (int i = 0; i <= n; i++) 
        {
            for (int k = 0; k <= maxItems; k++) 
            {
                for (int w = 0; w <= budgetUnits; w++) 
                {
                    dp[i][k][w] = (k == 0) ? 0 : -1.0;
                }
            }
        }

        // מילוי טבלת התכנון הדינמי
        for (int i = 1; i <= n; i++) 
        {
            Product current = items.get(i - 1);
            int price = current.getPrice(); 
            double score = current.getTemporaryScore();

            for (int k = 1; k <= maxItems; k++) 
            {
                for (int w = 0; w <= budgetUnits; w++) 
                {
                    // אופציה 1: אי הכללת הפריט הנוכחי בחבילה
                    dp[i][k][w] = dp[i - 1][k][w];

                    // אופציה 2: הכללת הפריט (בתנאי שהתקציב מאפשר והמצב הקודם היה חוקי)
                    if (w >= price && dp[i - 1][k - 1][w - price] != -1) 
                    {
                        double optionTake = dp[i - 1][k - 1][w - price] + score;
                        if (optionTake > dp[i][k][w]) 
                        {
                            dp[i][k][w] = optionTake;
                        }
                    }
                }
            }
        }   

        // מציאת התוצאה האופטימלית בטווח הכמויות המבוקש
        double bestScore = -1;
        int bestK = -1;
        int bestW = -1;

        for (int k = minItems; k <= maxItems; k++) 
        {
            for (int w = 0; w <= budgetUnits; w++)
            {
                if (dp[n][k][w] > bestScore) 
                {
                    bestScore = dp[n][k][w];
                    bestK = k;
                    bestW = w;
                }
            }
        }

        // במקרה שלא נמצא פתרון חוקי, החזר רשימה ריקה
        if (bestScore == -1) return new ArrayList<>();

        // שחזור (Backtracking) של הפריטים שנבחרו בפועל מתוך הטבלה
        List<Product> selected = new ArrayList<>();
        int currK = bestK;
        int currW = bestW;

        for (int i = n; i > 0 && currK > 0; i--)
        {
            // בדיקה האם הפריט הנוכחי נבחר (הערך השתנה לעומת השורה הקודמת)
            if (dp[i][currK][currW] != dp[i - 1][currK][currW]) 
            {
                Product p = items.get(i - 1);
                selected.add(p);
                currK--;
                currW -= p.getPrice();
            }
        }
        
        return selected;
    }
}