package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * @author DAVID BEN GIGI
 */
@Document(collection = "Products")
public class Product 
{
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private int price; // מיוצג כמספר שלם (אגורות)
    private List<String> tags; // מערך תגיות (חורף, אלגנט וכו')

    @Transient
    private double temporaryScore; // ניקוד דינמי המחושב בזמן ריצה

    public Product() {}

    public String getId() 
    { 
        return id; 
    }

    public void setId(String id) 
    { 
        this.id = id; 
    }

    public String getName() 
    { 
        return name; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }

    public String getImageUrl() 
    { 
        return imageUrl; 
    }

    public void setImageUrl(String imageUrl) 
    { 
        this.imageUrl = imageUrl; 
    }

    public String getDescription() 
    { 
        return description; 
    }

    public void setDescription(String description) 
    { 
        this.description = description; 
    }

    public int getPrice() 
    { 
        return price; 
    }

    public void setPrice(int price) 
    { 
        this.price = price; 
    }

    public List<String> getTags() 
    { 
        return tags; 
    }

    public void setTags(List<String> tags) 
    { 
        this.tags = tags; 
    }

    public double getTemporaryScore() 
    { 
        return temporaryScore; 
    }

    public void setTemporaryScore(double temporaryScore) 
    { 
        this.temporaryScore = temporaryScore; 
    }
}