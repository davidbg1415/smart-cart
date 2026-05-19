package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SmartCartAlgoService 
{
    /**
     * מימוש אלגוריתם הליבה  :  DP Knapsack.
     * אופטימיזציה של מחיר לעומת ציון תחת הגבלת כמות פריטים.
     */
    public List<Product> getOptimalBundle(List<Product> items, int budgetUnits, int minItems, int maxItems) 
    {
        int n = items.size();
        // טבלה תלת ממדית: [פריט][כמות][תקציב]
        double[][][] dp = new double[n + 1][maxItems + 1][budgetUnits + 1];

        // אתחול הטבלה בערך -1 לסימון מצבים לא אפשריים
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

        // מילוי הטבלה
        for (int i = 1; i <= n; i++) 
        {
            Product current = items.get(i - 1);
            int price = current.getPrice(); // כבר ביחידות של 10 אגורות
            double score = current.getTemporaryScore();

            for (int k = 1; k <= maxItems; k++) 
            {
                for (int w = 0; w <= budgetUnits; w++) 
                {
                    // אופציה 1: לא לקחת את הפריט
                    dp[i][k][w] = dp[i - 1][k][w];

                    // אופציה 2: לקחת את הפריט (אם התקציב מאפשר והמצב הקודם חוקי)
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

        // מציאת הנקודה האופטימלית בטווח הכמויות 
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

        if (bestScore == -1) return new ArrayList<>();

        // שחזור הפריטים שנבחרו 
        List<Product> selected = new ArrayList<>();
        int currK = bestK;
        int currW = bestW;

        for (int i = n; i > 0 && currK > 0; i--)
        {
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

