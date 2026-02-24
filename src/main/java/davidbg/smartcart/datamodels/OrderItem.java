package davidbg.smartcart.datamodels;

/**
 * @author DAVID BEN GIGI
 */
public class OrderItem 
{
    private String productId;
    private String nameSnapshot;
    private int priceSnapshot;
    private double calculatedScore; // הניקוד שחושב ברגע יצירת הסל

    public OrderItem() {}

    public String getProductId() 
    { 
        return productId; 
    }

    public void setProductId(String productId) 
    { 
        this.productId = productId; 
    }

    public String getNameSnapshot() 
    { 
        return nameSnapshot; 
    }

    public void setNameSnapshot(String nameSnapshot) 
    { 
        this.nameSnapshot = nameSnapshot; 
    }

    public int getPriceSnapshot() 
    { 
        return priceSnapshot; 
    }

    public void setPriceSnapshot(int priceSnapshot) 
    { 
        this.priceSnapshot = priceSnapshot; 
    }

    public double getCalculatedScore() 
    {
        return calculatedScore; 
    }

    public void setCalculatedScore(double calculatedScore) 
    { 
        this.calculatedScore = calculatedScore; 
    }
}