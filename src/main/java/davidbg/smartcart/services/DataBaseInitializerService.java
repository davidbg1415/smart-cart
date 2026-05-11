package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
 public class DataBaseInitializerService implements CommandLineRunner 
{
    private final ProductService productService;
    private final ProductRepository productRepository;

    public DataBaseInitializerService(ProductService productService, ProductRepository productRepository) 
    {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception 
    {
        // הבדיקה הקריטית: אם יש כבר מוצרים, אל תעשה כלום!
        if (productRepository.count() == 0) 
        {
            System.out.println(" מסד הנתונים ריק - מתחיל להזרים 100 חולצות...");
            
            seedMensShirts();
            seedWomensShirts();
            seedMensPants();   
            seedWomensPants(); 
            
            System.out.println(" done!");
        } 
        else 
        {
            System.out.println(" כבר יש נתונים ( " + productRepository.count() + " מוצרים) - מדלג על האתחול.");
        }
    }

   private void seedMensShirts() 
   {
    // 1. חולצת פשתן קובנית - הלהיט של הקיץ
    productService.addProductToDB(new Product(
        "M-SH-201", "חולצת פשתן Cuban Collar", "images/linen-cuban.jpg",
        "חולצה אוורירית מחקר שוק זארה. בד: 100% פשתן אירופאי מעובד. גזרת Relaxed, צווארון פתוח.",
        1699, // 169.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "בז'", "Relaxed-Fit", "100% פשתן", "Summer", "Vacation", "קליל")
    ));

    // 2. טי-שירט Oversize כבדה - סטריטוור
    productService.addProductToDB(new Product(
        "M-SH-202", "טי-שירט Heavyweight שטופה", "images/heavy-tee.jpg",
        "חולצת סטריטוור במשקל 300 GSM. בד: 100% כותנה אורגנית. מראה Vintage Wash עם כתפיים שמוטות.",
        1299, // 129.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "אפור גרפיט", "Oversize", "100% כותנה", "Streetwear", "Vintage", "יומיום")
    ));

    // 3. חולצת אוקספורד Slim-Fit - למשרד/אירוע קל
    productService.addProductToDB(new Product(
        "M-SH-203", "חולצת אוקספורד יוקרתית", "images/oxford-slim.jpg",
        "חולצה מחויטת לעבודה. בד: 98% כותנה, 2% אלסטן (Stretch). גזרה צמודה, צווארון קשיח.",
        1999, // 199.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "כותנה-אלסטן", "Elegant", "Office", "Classic", "חלק")
    ));

    // 4. סריג פולו ריב - מראה "Old Money"
    productService.addProductToDB(new Product(
        "M-SH-204", "חולצת פולו סרוגה בטקסטורת ריב", "images/knit-polo.jpg",
        "מראה אירופאי קלאסי. בד: ויסקוזה וניילון למגע רך וקריר. שרוול קצר עם סיומת מנג'ט.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "כחול נייבי", "Regular-Fit", "ויסקוזה", "Smart-Casual", "Luxury", "פסים עדינים")
    ));

    // 5. חולצת ג'ינס Western - מראה מחוספס
    productService.addProductToDB(new Product(
        "M-SH-205", "חולצת ג'ינס דנים Western", "images/denim-shirt",
        "חולצת ג'ינס איכותית. בד: 100% כותנה קשיחה (Indigo Denim). שני כיסי חזה, סגירת תיקתקים.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "ג'ינס", "כחול דנים", "Regular-Fit", "100% כותנה", "Casual", "Outdoor", "חלק")
    ));

    // 6. חולצת שרוול ארוך Henley - בייסיק משודרג
    productService.addProductToDB(new Product(
        "M-SH-206", "חולצת הנלי ריב ארוכה", "images/henley-grey",
        "חולצה נוחה ליומיום. בד: 60% כותנה, 40% פוליאסטר. מפתח צוואר עם 3 כפתורים, בד טקסטורלי.",
        1199, // 119.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "אפור מלנז'", "Slim-Fit", "כותנה-פוליאסטר", "Basic", "Home-Lounge", "יומיום")
    ));

    // 7. חולצת פלאנל משובצת - מראה "לומברג'ק"
    productService.addProductToDB(new Product(
        "M-SH-207", "חולצת פלאנל משובצת Heavy", "images/flannel-shirt",
        "חולצה חמה לחורף. בד: 100% כותנה עבה מוברשת. משבצות בגווני אדום ושחור, גזרה רחבה.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "אדום-משובץ", "Oversize", "100% כותנה", "Winter", "Streetwear", "משובץ")
    ));

    // 8. גופיית ספורט מנדפת - Gym Wear
    productService.addProductToDB(new Product(
        "M-SH-208", "גופיית אימון Performance", "images/gym-tank",
        "לביצועים מקסימליים. בד: 100% פוליאסטר מנדף זיעה (Dry-Fit). גזרת גב שחיין לתנועה חופשית.",
        899, // 89.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "שחור", "Active", "פוליאסטר", "Gym", "Sport", "חלק")
    ));

    // 9. חולצת פולו סרוגה (Knit Polo)
    productService.addProductToDB(new Product(
        "M-SH-209", "חולצת פולו סרוגה בטקסטורת מעויינים", "images/knit-polo-tan",
        "מראה יוקרתי ושקט. בד: תערובת כותנה ו-ויסקוזה. גזרה ישרה עם סיומת מנג'ט בקיפול השרוול.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "בז'", "Regular-Fit", "ויסקוזה", "Old-Money", "Luxury", "Smart-Casual")
    ));

    // 10. חולצת טי "מוסל פיט" (Muscle Fit)
    productService.addProductToDB(new Product(
        "M-SH-210", "טי-שירט Muscle Fit שחורה", "images/muscle-tee",
        "בד אלסטי במיוחד המדגיש את מבנה הגוף. בד: 95% כותנה, 5% לייקרה. שרוולים קצרים וצמודים.",
        899, // 89.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "שחור", "Muscle-Fit", "כותנה-לייקרה", "Gym", "Basic", "יומיום")
    ));

    // 11. חולצת צווארון סיני (Mandarin Collar)
    productService.addProductToDB(new Product(
        "M-SH-211", "חולצת כפתורים צווארון סיני לבנה", "https://api.placeholder.com/mandarin-shirt",
        "לוק נקי ומודרני. בד: 100% כותנת פופלין דקה. ללא צווארון מסורתי, כפתורים נסתרים.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "100% כותנה", "Minimalist", "Modern", "חלק")
    ));

    // 12. חולצת פלאנל משובצת "Over-Shirt"
    productService.addProductToDB(new Product(
        "M-SH-212", "ג'קט חולצה (Shacket) משובץ כחול", "https://api.placeholder.com/shacket-blue",
        "חולצה עבה המשמשת כג'קט קל. בד: פלנל צמר סינתטי. גזרת Oversize, כיסים גדולים בחזה.",
        2599, // 259.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "כחול-משובץ", "Oversize", "צמר-סינתטי", "Winter", "Streetwear", "משובץ")
    ));

    // 13. חולצת פופלין בגזרת Boxy
    productService.addProductToDB(new Product(
        "M-SH-213", "חולצת פופלין Boxy-Fit קצרה", "https://api.placeholder.com/m/boxy-poplin",
        "מראה אורבני מודרני. בד: 100% כותנת פופלין פריכה. גזרה רחבה וקצרה עם כתפיים שמוטות.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "ירוק מרווה", "Boxy-Fit", "100% כותנה", "Streetwear", "Minimalist", "קיץ")
    ));

    // 14. חולצת פולו עם רוכסן (Zip Polo)
    productService.addProductToDB(new Product(
        "M-SH-214", "חולצת פולו עם סגירת רוכסן", "https://api.placeholder.com/m/zip-polo",
        "סטייל Smart-Casual נקי. בד: 100% כותנה בטקסטורת Pike. רוכסן מתכת איכותי במקום כפתורים.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "אפור גרפיט", "Slim-Fit", "100% כותנה", "Office", "Classic", "חלק")
    ));

    // 15. חולצת "גרנדד" (Grandad Collar) מפשתן
    productService.addProductToDB(new Product(
        "M-SH-215", "חולצת פשתן צווארון סיני ארוכה", "https://api.placeholder.com/m/grandad-linen",
        "חולצה קלילה ונושמת. בד: תערובת פשתן וכותנה. צווארון עגול ללא קיפול, שרוולים מתקפלים עם כפתור.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "לבן", "Relaxed-Fit", "פשתן-כותנה", "Boho", "Vacation", "קליל")
    ));

    // 16. טי-שירט מנדפת זיעה (Compression)
    productService.addProductToDB(new Product(
        "M-SH-216", "חולצת אימון Compression צמודה", "https://api.placeholder.com/m/active-tee",
        "מיועדת לאימונים עצימים. בד: 88% פוליאסטר, 12% אלסטן. טכנולוגיית מנדפת זיעה, בד גמיש לארבעה כיוונים.",
        1299, // 129.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "שחור", "Compression", "פוליאסטר-אלסטן", "Gym", "Active", "ספורט")
    ));

    // 17. חולצת פלאנל משובצת (Buffalo Check)
    productService.addProductToDB(new Product(
        "M-SH-217", "חולצת פלאנל משבצות באפלו", "https://api.placeholder.com/m/buffalo-plaid",
        "סטייל Workwear קלאסי. בד: 100% כותנה מוברשת עבה. משבצות אדום-שחור גדולות.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "אדום-שחור", "Regular-Fit", "100% כותנה", "Winter", "Casual", "משובץ")
    ));

    // 18. חולצת "ריזורט" (Resort Shirt) מודפסת
    productService.addProductToDB(new Product(
        "M-SH-218", "חולצת ריזורט בהדפס טרופי", "https://api.placeholder.com/m/resort-print",
        "חולצה לחופשה. בד: 100% ויסקוזה נשפכת. הדפס עלי דקל, צווארון פתוח, כפתורי עץ.",
        1499, // 149.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "צבעוני", "Relaxed-Fit", "ויסקוזה", "Summer", "Vacation", "הדפס")
    ));

    // 19. חולצת גולף (Turtleneck) צמר
    productService.addProductToDB(new Product(
        "M-SH-219", "סריג גולף צמר דק", "https://api.placeholder.com/m/turtleneck",
        "מראה אירופאי אלגנטי. בד: 100% צמר מרינו. צווארון גבוה מתקפל, בד רך ונעים למגע.",
        2999, // 299.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "כחול נייבי", "Slim-Fit", "צמר מרינו", "Luxury", "Winter", "Elegant")
    ));

    // 20. גופיית Racerback לאימון
    productService.addProductToDB(new Product(
        "M-SH-220", "גופיית Racerback אתלטית", "https://api.placeholder.com/m/active-tank",
        "חופש תנועה מלא בכתפיים. בד: מיקרופייבר קל משקל. בד מנדף מהיר יבוש.",
        799, // 79.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "ניאון", "Athletic-Fit", "מיקרופייבר", "Gym", "Running", "ספורט")
    ));

    // 21. קפוצ'ון Heavyweight שטוף - Streetwear
    productService.addProductToDB(new Product(
        "M-SH-221", "קפוצ'ון Oversize וינטג' אפור", "https://api.placeholder.com/m/hoodie-vintage",
        "קפוצ'ון כבד (450 GSM). בד: 100% כותנה עם בטנת פליז רכה. מראה שטוף, כיס קנגורו גדול.",
        2499, // 249.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "אפור", "Oversize", "100% כותנה", "Streetwear", "Winter", "חם")
    ));

    // 22. חולצת קורדרוי (Corduroy) - Casual
    productService.addProductToDB(new Product(
        "M-SH-222", "חולצת קורדרוי חום כאמל", "https://api.placeholder.com/m/corduroy-shirt",
        "חולצה בטקסטורת פסים דקה. בד: 100% כותנה קורדרוי. גזרת Regular, שני כיסי חזה עם כפתור.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "חום", "Regular-Fit", "קורדרוי", "Autumn", "Casual", "טקסטורה")
    ));

    // 23. סוודר V-Neck צמר מרינו - Elegant
    productService.addProductToDB(new Product(
        "M-SH-223", "סריג וי צמר מרינו דק", "https://api.placeholder.com/m/v-neck-wool",
        "למראה אלגנטי מעל חולצה מכופתרת. בד: 100% צמר מרינו איטלקי. בד נושם ודק במיוחד.",
        2999, // 299.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "ירוק זית", "Slim-Fit", "צמר מרינו", "Luxury", "Office", "Elegant")
    ));

    // 24. חולצת פופלין פסים - Business
    productService.addProductToDB(new Product(
        "M-SH-224", "חולצת פופלין פסים כחול-לבן", "https://api.placeholder.com/m/stripe-poplin",
        "חולצת משרד קלאסית. בד: 100% כותנת פופלין. גזרת Tailored, פסי סיכה דקים, צווארון נוקשה.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "פסים", "Tailored-Fit", "100% כותנה", "Office", "Classic", "Business")
    ));

    // 25. טי-שירט "בוקסי" בייסיק - Minimalist
    productService.addProductToDB(new Product(
        "M-SH-225", "טי-שירט Boxy-Fit לבן בייסיק", "https://api.placeholder.com/m/boxy-white",
        "גזרה רבועה ומודרנית. בד: 100% כותנה עבה. שרוולים רחבים וקצרים, מפתח צוואר סגור.",
        999, // 99.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "לבן", "Boxy-Fit", "100% כותנה", "Minimalist", "Basic", "יומיום")
    ));

    // 26. חולצת ג'ינס שחורה - Rugged
    productService.addProductToDB(new Product(
        "M-SH-226", "חולצת דנים שחורה שטופה", "https://api.placeholder.com/m/black-denim",
        "מראה אורבני מחוספס. בד: 100% דנים כותנה. כפתורי תיקתק ממתכת, תפרים בולטים.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "ג'ינס", "שחור", "Regular-Fit", "דנים", "Streetwear", "Bold", "חלק")
    ));

    // 27. סוודר "קולור-בלוק" - Trendy
    productService.addProductToDB(new Product(
        "M-SH-227", "סריג קולור-בלוק גווני אדמה", "https://api.placeholder.com/m/colorblock-knit",
        "עיצוב גאומטרי מודרני. בד: תערובת צמר ואקריליק. שילוב צבעי חום, בז' וקרם.",
        2399, // 239.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "צבעוני", "Oversize", "צמר", "Trendy", "Winter", "גאומטרי")
    ));

    // 28. חולצת "שאקט" (Shacket) משובצת - Winter
    productService.addProductToDB(new Product(
        "M-SH-228", "ג'קט חולצה משובץ עבה", "https://api.placeholder.com/m/shacket-plaid",
        "פריט מעבר מושלם. בד: פלנל צמר עבה עם בטנת סאטן. כיסי צד נסתרים.",
        3299, // 329.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "משובץ", "Oversize", "צמר", "Winter", "Outdoor", "חם")
    ));

    // 29. חולצת קומפרשן לאימון
    productService.addProductToDB(new Product(
        "M-SH-229", "חולצת Compression אקטיב", "https://api.placeholder.com/m/compression-black",
        "חולצת אימון צמודה במיוחד התומכת בשרירים. בד: 88% פוליאסטר ממוחזר, 12% אלסטן. מנדפת זיעה ומתייבשת במהירות.",
        1399, // 139.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "שחור", "Compression", "Active", "Gym", "Performance", "חלק")
    ));

    // 30. סוודר צמות קלאסי (Cable Knit)
    productService.addProductToDB(new Product(
        "M-SH-230", "סוודר Cable Knit צמר", "https://api.placeholder.com/m/cable-knit-cream",
        "סריג חורף כבד במראה אירופאי קלאסי. בד: 70% כותנה, 30% צמר. טקסטורת צמות בולטת, צווארון עגול.",
        2899, // 289.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "שמנת", "Regular-Fit", "Cotton-Wool", "Winter", "Classic", "Old-Money")
    ));

    // 31. חולצת הוואי מודפסת (Rayon)
    productService.addProductToDB(new Product(
        "M-SH-231", "חולצת ריזורט רייון מודפסת", "https://api.placeholder.com/m/hawaiian-shirt",
        "חולצה קלילה לחופשה. בד: 100% רייון (ויסקוזה איכותית). הדפס עלים טרופי, גזרה רחבה, כפתורי קליפה.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "צבעוני", "Relaxed-Fit", "Rayon", "Summer", "Vacation", "הדפס")
    ));

    // 32. טי-שירט וינטג' להקה
    productService.addProductToDB(new Product(
        "M-SH-232", "טי-שירט Graphic Vintage", "https://api.placeholder.com/m/band-tee",
        "מראה מכובס ומשופשף. בד: 100% כותנה כבדה. הדפס גרפי בסגנון רוק משנות ה-90, צווארון ריב עבה.",
        1199, // 119.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "אפור פחם", "Oversize", "100% כותנה", "Streetwear", "Graphic", "Vintage")
    ));

    // 33. חולצת פולו רוכסן מודרנית
    productService.addProductToDB(new Product(
        "M-SH-233", "חולצת פולו עם רוכסן מתכת", "https://api.placeholder.com/m/zip-polo",
        "סטייל נקי לעבודה. בד: 100% כותנה בטקסטורת Pike. רוכסן כסוף במקום כפתורים, ללא כיס.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "כחול נייבי", "Slim-Fit", "100% כותנה", "Office", "Modern", "Minimalist")
    ));

    // 34. חולצת שמברה (Chambray)
    productService.addProductToDB(new Product(
        "M-SH-234", "חולצת שמברה כחולה", "https://api.placeholder.com/m/chambray-shirt",
        "מראה דנים קליל. בד: 100% כותנת שמברה. בד דק דמוי ג'ינס, תפרים לבנים בולטים, כפתורי פנינה.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "כחול בהיר", "Regular-Fit", "Chambray", "Casual", "Workwear", "חלק")
    ));

    // 35. גופיית כדורסל רשת
    productService.addProductToDB(new Product(
        "M-SH-235", "גופיית Mesh אתלטית", "https://api.placeholder.com/m/mesh-tank",
        "מתאימה לאימונים ולמראה רחוב. בד: 100% פוליאסטר רשת נושם. גזרה רחבה, סיומת פסים בצוואר.",
        999, // 99.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "שחור-לבן", "Athletic-Fit", "Polyester", "Sport", "Streetwear", "Basic")
    ));

    // 36. חולצת סאטן לערב
    productService.addProductToDB(new Product(
        "M-SH-236", "חולצת סאטן יוקרתית שחורה", "https://api.placeholder.com/m/satin-men",
        "למראה ערב נוצץ ומתוחכם. בד: 100% סאטן משי סינתטי. בד נשפך עם ברק עדין, כפתורים נסתרים.",
        2499, // 249.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "שחור", "Slim-Fit", "Satin", "Evening", "Night-Out", "Luxury")
    ));

    // 37. פלאנל משובצת כבדה
    productService.addProductToDB(new Product(
        "M-SH-237", "חולצת פלאנל משובצת Heavy", "https://api.placeholder.com/m/heavy-flannel",
        "חולצה מחממת שמתפקדת כג'קט. בד: 100% כותנה מוברשת עבה. משבצות בגווני ירוק זית ושחור.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "ירוק-משובץ", "Regular-Fit", "100% כותנה", "Winter", "Outdoor", "Rough")
    ));

    // 38. חולצת מעטפת בוהו
    productService.addProductToDB(new Product(
        "M-SH-238", "חולצת קימונו/מעטפת פשתן", "https://api.placeholder.com/m/wrap-linen",
        "מראה ייחודי ונינוח. בד: 50% פשתן, 50% כותנה. סגירת קשירה פנימית, שרוולים רחבים.",
        2399, // 239.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Relaxed-Fit", "Linen-Blend", "Boho", "Vacation", "Unique")
    ));

    // 39. טי-שירט עם כיס (Pocket Tee)
    productService.addProductToDB(new Product(
        "M-SH-239", "טי-שירט Slub עם כיס", "https://api.placeholder.com/m/pocket-tee",
        "בייסיק עם טקסטורה מעניינת. בד: 100% כותנת Slub (טקסטורה לא אחידה). כיס בחזה שמאל.",
        899, // 89.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "בורדו", "Regular-Fit", "100% כותנה", "Basic", "Casual", "חלק")
    ));

    // 40. גולף צמר מרינו דק
    productService.addProductToDB(new Product(
        "M-SH-240", "סריג גולף Merino פרימיום", "https://api.placeholder.com/m/merino-turtle",
        "פריט חובה למראה שכבות יוקרתי. בד: 100% צמר מרינו דק במיוחד. בד גמיש, רך ולא מגרד.",
        3199, // 319.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "אפור מלנז'", "Slim-Fit", "Merino-Wool", "Winter", "Luxury", "Elegant")
    ));

    // 41. חולצת טוקסידו רשמית
    productService.addProductToDB(new Product(
        "M-SH-241", "חולצת טוקסידו Wing-Collar", "https://api.placeholder.com/m/tux-shirt",
        "החולצה הרשמית ביותר לאירועי ערב. בד: 100% כותנה מצרית איכותית. צווארון כנף לעניבת פרפר, חזית פיקה נוקשה.",
        3499, // 349.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "Luxury", "Formal", "Wedding", "Premium")
    ));

    // 42. חולצת ריצה תרמית
    productService.addProductToDB(new Product(
        "M-SH-242", "חולצת ריצה תרמית ארוכה", "https://api.placeholder.com/m/thermal-run",
        "לפעילות גופנית במזג אוויר קר. בד: 92% פוליאסטר תרמי, 8% אלסטן. פנים מורשת (Brushed) לשמירת חום.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "שחור", "Athletic-Fit", "Thermal", "Active", "Sport", "Winter")
    ));

    // 43. חולצת "גרנדד" פסים
    productService.addProductToDB(new Product(
        "M-SH-243", "חולצת Grandad פסי סיכה", "https://api.placeholder.com/m/grandad-stripes",
        "מראה אירופאי נינוח. בד: 100% כותנת פופלין. צווארון סיני עגול, פסי תכלת-לבן דקים, גזרה ישרה.",
        1699, // 169.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "פסים", "Regular-Fit", "100% כותנה", "Smart-Casual", "Classic", "יומיום")
    ));

    // 44. טי-שירט "רייזר" (Racerback)
    productService.addProductToDB(new Product(
        "M-SH-244", "גופיית Racerback מקצועית", "https://api.placeholder.com/m/gym-racer",
        "חיתוך עמוק בגב להדגשת השרירים. בד: תערובת כותנה ומודל למגע משי. בד נושם וקל במיוחד.",
        799, // 79.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "אפור גרפיט", "Muscle-Fit", "Cotton-Modal", "Gym", "Active", "Basic")
    ));

    // 45. סוודר פולו "Old Money"
    productService.addProductToDB(new Product(
        "M-SH-245", "סריג פולו צמר ומשי", "https://api.placeholder.com/m/silk-wool-polo",
        "פריט פרימיום למראה יוקרתי שקט. בד: 70% צמר מרינו, 30% משי. גזרה צמודה עם צווארון פולו סרוג.",
        4299, // 429.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "כחול נייבי", "Slim-Fit", "Silk-Wool", "Luxury", "Old-Money", "Elegant")
    ));

    // 46. חולצת עבודה (Workshirt) עמידה
    productService.addProductToDB(new Product(
        "M-SH-246", "חולצת עבודה Canvas כבדה", "https://api.placeholder.com/m/canvas-shirt",
        "עמידות מקסימלית לתנאי חוץ. בד: 100% כותנת קנבס עבה. תפרים כפולים מחוזקים, כיסי דגמ\"ח בחזה.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "חקי", "Regular-Fit", "Canvas", "Outdoor", "Workwear", "Rough")
    ));

    // 47. קפוצ'ון "שרפה" (Sherpa)
    productService.addProductToDB(new Product(
        "M-SH-247", "קפוצ'ון בטנת פרווה Sherpa", "https://api.placeholder.com/m/sherpa-hoodie",
        "הפריט הכי חם בקולקציה. בד: פליז עבה עם בטנת פרווה סינתטית בתוך הכובע והגוף. שרוולי ריב.",
        3199, // 319.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוכות", "שחור מלנז'", "Oversize", "Sherpa", "Winter", "Warm", "Casual")
    ));

    // 48. גופיית כדורסל רטרו
    productService.addProductToDB(new Product(
        "M-SH-248", "גופיית רשת Basketball Vintage", "https://api.placeholder.com/m/retro-jersey",
        "מראה רחוב נוסטלגי. בד: 100% פוליאסטר רשת כפול. מספר מודפס בחזית ובגב, סיומת פסים צבעונית.",
        1499, // 149.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "צבעוני", "Athletic-Fit", "Mesh", "Streetwear", "Vintage", "Sport")
    ));

    // 49. חולצת פופלין "סטרצ'" צבעונית
    productService.addProductToDB(new Product(
        "M-SH-249", "חולצת כפתורים Stretch בורדו", "https://api.placeholder.com/m/stretch-shirt",
        "נוחות מקסימלית למראה מחויט. בד: 96% כותנה, 4% אלסטן. בד גמיש במיוחד המאפשר תנועה חופשית.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "בורדו", "Slim-Fit", "Cotton-Stretch", "Office", "Elegant", "חלק")
    ));

    // 50. טי-שירט "בוקסי" (Boxy) עם הדפס
    productService.addProductToDB(new Product(
        "M-SH-250", "טי-שירט Boxy גב מודפס", "https://api.placeholder.com/m/boxy-graphic",
        "גזרת רחוב עדכנית. בד: 100% כותנה אורגנית במשקל בינוני. הדפס אומנותי גדול על כל הגב.",
        1199, // 119.90 NIS
        Arrays.asList("גברים", "חולצות", "קצרות", "לבן", "Boxy-Fit", "100% כותנה", "Streetwear", "Graphic", "Trendy")
    ));
}

private void seedWomensShirts() {
    // 1. חולצת סאטן נשפכת - למראה ערב
    productService.addProductToDB(new Product(
        "W-SH-301", "חולצת סאטן בגזרת מעטפת", "https://images.clothes.com/w/satin-wrap.jpg",
        "חולצה אלגנטית ליציאה. בד: סאטן משי סינתטי (Polyester Satin). מפתח וי, קשירה במותן.",
        1899, // 189.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "ירוק בקבוק", "Regular-Fit", "סאטן", "Evening", "Night-Out", "יוקרתי")
    ));

    // 2. חולצת קרופ (Crop Top) ריב - בייסיק יומיומי
    productService.addProductToDB(new Product(
        "W-SH-302", "חולצת קרופ Seamless ריב", "https://images.clothes.com/w/crop-rib.jpg",
        "חולצת בייסיק צמודה ללא תפרים. בד: מיקרופייבר נמתח. גזרה קצרה מעל הפופיק.",
        699, // 69.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "ורוד עתיק", "Slim-Fit", "מיקרופייבר", "Casual", "Summer", "Basic")
    ));

    // 3. חולצת כפתורים "Boyfriend" - אוברסייז
    productService.addProductToDB(new Product(
        "W-SH-303", "חולצת פופלין Oversize לבנה", "https://images.clothes.com/w/poplin-white.jpg",
        "חולצה גדולה וקלילה בסגנון גברי. בד: 100% כותנת פופלין פריכה. מתאימה מעל בגד ים או עם ג'ינס.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Oversize", "100% כותנה", "Minimalist", "Beachwear", "Office")
    ));

    // 4. חולצת תחרה בוהו-שיק
    productService.addProductToDB(new Product(
        "W-SH-304", "חולצת תחרה רקוקה (Embroidery)", "https://images.clothes.com/w/boho-lace.jpg",
        "חולצה רומנטית לחופשה. בד: 100% כותנה עם רקמת חורים (Eyelet). שרוולים תפוחים.",
        2199, // 219.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "קרם", "Regular-Fit", "כותנה רקוסה", "Romantic", "Vacation", "Boho")
    ));

    // 5. חולצת שיפון רומנטית - Boho Chic
    productService.addProductToDB(new Product(
        "W-SH-305", "חולצת שיפון שקופה למחצה", "https://api.placeholder.com/chiffon-blouse",
        "מראה נשי ועדין. בד: 100% פוליאסטר שיפון. שרוולים תפוחים עם סיומת תחרה, הדפס פרחים עדין.",
        2199, // 219.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "פרחוני", "Regular-Fit", "פוליאסטר", "Romantic", "Date-Night", "פרחוני")
    ));

    // 6. בגד גוף (Bodysuit) צמוד - מראה נקי
    productService.addProductToDB(new Product(
        "W-SH-306", "בגד גוף Seamless בייסיק", "https://api.placeholder.com/bodysuit",
        "מחטב ומחמיא. בד: 90% ניילון, 10% אלסטן. סגירת תיקתקים בתחתית, בד סטרץ' חזק.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "בגד גוף", "בז' ניוד", "Slim-Fit", "ניילון-אלסטן", "Basic", "Minimalist", "חלק")
    ));

    // 7. חולצת "סטרפלס" סרוגה - מראה קיץ יוקרתי
    productService.addProductToDB(new Product(
        "W-SH-307", "טופ סטרפלס סרוג ריב", "https://api.placeholder.com/tube-top",
        "טרנד הקיץ. בד: תערובת כותנה וגומי. גזרה צמודה ללא כתפיות, טקסטורת פסים אנכיים.",
        999, // 99.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "לבן", "Slim-Fit", "כותנה", "Summer", "Vacation", "קליל")
    ));

    // 8. חולצת קטיפה (Velvet) - חורף וערב
    productService.addProductToDB(new Product(
        "W-SH-308", "חולצת קטיפה עם שרוול ארוך", "https://api.placeholder.com/velvet-top",
        "מראה עשיר ויוקרתי. בד: קטיפה סינתטית רכה. צווארון גולף נמוך, ברק עדין בבד.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "בורדו", "Regular-Fit", "קטיפה", "Evening", "Luxury", "Winter")
    ));

    // 9. חולצת מחוך (Corset Top)
    productService.addProductToDB(new Product(
        "W-SH-309", "טופ מחוך בטקסטורת ז'אקרד", "https://api.placeholder.com/corset-top",
        "טרנד מסיבות וערב. בד: פוליאסטר קשיח ומחטב. עצמות פנימיות לעיצוב המותן, רוכסן אחורי.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "שחור", "Slim-Fit", "ז'אקרד", "Night-Out", "Sexy", "יוקרתי")
    ));

    // 10. חולצת אוף-שולדר (Off-the-Shoulder)
    productService.addProductToDB(new Product(
        "W-SH-310", "חולצת אוף-שולדר עם כיווצים", "https://api.placeholder.com/off-shoulder",
        "מראה רומנטי וקליל. בד: 100% ויסקוזה נושמת. סיומת גומי בכתפיים ובשרוולים.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "לבן", "Relaxed-Fit", "ויסקוזה", "Romantic", "Summer", "Boho")
    ));

    // 11. חולצת גולף (Turtleneck) ללא שרוולים
    productService.addProductToDB(new Product(
        "W-SH-311", "סריג גולף דק ללא שרוול", "https://api.placeholder.com/sleeveless-turtle",
        "מראה אלגנטי ומתוחכם. בד: תערובת כותנה ומודל. צווארון גבוה, בד ריב גמיש.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "אפור", "Slim-Fit", "כותנה-מודל", "Office", "Elegant", "Classic")
    ));

    // 12. חולצת מעטפת (Wrap Shirt) מודפסת
    productService.addProductToDB(new Product(
        "W-SH-312", "חולצת מעטפת בהדפס מנומר", "https://api.placeholder.com/wrap-animal",
        "פריט הצהרה אופנתי. בד: קרפ פוליאסטר נשפך. קשירה צידית, מחשוף וי עמוק.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "מנומר", "Regular-Fit", "קרפ", "Bold", "Evening", "הדפס")
    ));

    // 13. חולצת משי (Silk Blouse) יוקרתית
    productService.addProductToDB(new Product(
        "W-SH-313", "חולצת משי עם עניבת פרפר", "https://api.placeholder.com/w/silk-blouse",
        "מראה יוקרתי לעבודה או אירוע. בד: 100% משי טבעי. צווארון גבוה עם קשירת פרפר, שרוולים תפוחים.",
        3499, // 349.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "פנינה", "Regular-Fit", "משי", "Luxury", "Office", "יוקרתי")
    ));

    // 14. חולצת קרופ (Crop Top) עם קשירה
    productService.addProductToDB(new Product(
        "W-SH-314", "טופ קרופ עם קשירה קדמית", "https://api.placeholder.com/w/tie-crop",
        "טרנד הקיץ. בד: כותנה ופוליאסטר. שרוולים קצרים, קשירה במרכז החזה, מראה צעיר.",
        999, // 99.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "צהוב לימון", "Slim-Fit", "כותנה", "Summer", "Casual", "צעיר")
    ));

    // 15. חולצת "מחוך" (Corset Style) סרוגה
    productService.addProductToDB(new Product(
        "W-SH-315", "סריג גופייה בגזרת מחוך", "https://api.placeholder.com/w/knit-corset",
        "שילוב של נוחות וסטייל. בד: סריג ריב עבה ומחטב. גזרת מחוך ללא עצמות, כתפיות דקות.",
        1599, // 159.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "שחור", "Slim-Fit", "סריג", "Night-Out", "Sexy", "חלק")
    ));

    // 16. חולצת בייסיק שרוול ארוך (Second Skin)
    productService.addProductToDB(new Product(
        "W-SH-316", "חולצת בייסיק Second Skin", "https://api.placeholder.com/w/basic-long",
        "פריט חובה לשכבות. בד: מודל ואלסטן רך במיוחד. בד דק ונצמד כמו עור שני.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "חום שוקולד", "Slim-Fit", "מודל-אלסטן", "Basic", "Minimalist", "חלק")
    ));

    // 17. חולצת פופלין עם שרוולים נפוחים
    productService.addProductToDB(new Product(
        "W-SH-317", "חולצת פופלין שרוול נפוח (Puff)", "https://api.placeholder.com/w/puff-sleeve",
        "מראה דרמטי ומעוצב. בד: 100% כותנת פופלין. שרוולי בלון נפוחים, מותן מודגשת.",
        2299, // 229.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "תכלת-פסים", "Regular-Fit", "100% כותנה", "Modern", "Office", "פסים")
    ));

    // 18. גופיית ספורט עם חזייה פנימית
    productService.addProductToDB(new Product(
        "W-SH-318", "גופיית אימון עם Built-in Bra", "https://api.placeholder.com/w/active-bra",
        "תמיכה ונוחות באימון. בד: ניילון ממוחזר ואלסטן. בד מחטב, מנדף זיעה, גב פתוח.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "סגול לילך", "Athletic-Fit", "ניילון", "Gym", "Yoga", "ספורט")
    ));

    // 19. חולצת תחרה (Lace) שקופה
    productService.addProductToDB(new Product(
        "W-SH-319", "חולצת תחרה שחורה רומנטית", "https://api.placeholder.com/w/lace-shirt",
        "מראה ערב מתוחכם. בד: תחרה סינתטית עדינה. דוגמת פרחים, שקופה (דורשת גופיה מתחת).",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "שחור", "Regular-Fit", "תחרה", "Evening", "Romantic", "יוקרתי")
    ));

    // 20. חולצת "טרנץ'" (Trench Style) ללא שרוולים
    productService.addProductToDB(new Product(
        "W-SH-320", "חולצת ג'קט ללא שרוול", "https://api.placeholder.com/w/vest-trench",
        "מראה מחויט ומודרני. בד: תערובת כותנה וגברדין. צווארון דש רחב, כפתרה כפולה, חגורת מותן.",
        2599, // 259.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "בז' חול", "Tailored-Fit", "גברדין", "Minimalist", "Office", "Classic")
    ));

    // 21. סוודר "קייבל" קרופ - Trendy
    productService.addProductToDB(new Product(
        "W-SH-321", "סריג קייבל קרופ שמנת", "https://api.placeholder.com/w/cable-knit",
        "סריגת צמות קלאסית בגזרה קצרה. בד: תערובת כותנה רכה. שרוולים נפוחים מעט.",
        2199, // 219.90 NIS
        Arrays.asList("נשים", "חולצות", "סריגים", "שמנת", "Crop-Fit", "כותנה", "Winter", "Trendy", "צמות")
    ));

    // 22. חולצת סאטן קשירה - Night Out
    productService.addProductToDB(new Product(
        "W-SH-322", "טופ סאטן עם קשירה בצוואר", "https://api.placeholder.com/w/satin-tie",
        "מראה ערב סקסי ויוקרתי. בד: 100% סאטן משי סינתטי. גב פתוח למחצה, קשירת סרט רחבה.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "שחור", "Slim-Fit", "סאטן", "Night-Out", "Luxury", "חלק")
    ));

    // 23. חולצת "סמאק" (Smocked) - Boho
    productService.addProductToDB(new Product(
        "W-SH-323", "חולצת סמאק עם הדפס פרחים", "https://api.placeholder.com/w/smocked-floral",
        "מראה רומנטי לחופשה. בד: ויסקוזה קלילה. כיווצי גומי בחזה (Smocking), שרוולי מלמלה.",
        1599, // 159.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "פרחוני", "Regular-Fit", "ויסקוזה", "Boho", "Romantic", "Summer")
    ));

    // 24. גופיית ספורט "מצלבה" - Active
    productService.addProductToDB(new Product(
        "W-SH-324", "גופיית אימון Cross-Back", "https://api.placeholder.com/w/active-cross",
        "תמיכה גבוהה ועיצוב מודרני. בד: ניילון ואלסטן מחטב. כתפיות דקות מוצלבות בגב.",
        1299, // 129.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "כחול חשמלי", "Athletic-Fit", "ניילון", "Gym", "Active", "ספורט")
    ));

    // 25. חולצת "פיטר פן" - Vintage
    productService.addProductToDB(new Product(
        "W-SH-325", "חולצת פופלין צווארון בובה", "https://api.placeholder.com/w/peter-pan",
        "מראה רטרו מתוחכם. בד: 100% כותנה פופלין. צווארון רחב עם רקמת תחרה, כפתורי פנינה.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Regular-Fit", "100% כותנה", "Vintage", "Elegant", "חלק")
    ));

    // 26. סריג גולף ללא שרוול - Modern Office
    productService.addProductToDB(new Product(
        "W-SH-326", "טופ גולף סרוג ללא שרוול", "https://api.placeholder.com/w/sleeveless-turtle",
        "מתאים מתחת לג'קט או כפריט עצמאי. בד: כותנה ו-מודל. גזרה צמודה ונוחה.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "אפור מלנז'", "Slim-Fit", "כותנה-מודל", "Office", "Minimalist", "Basic")
    ));

    // 27. חולצת "באטו" (צווארון סירה) - Classic
    productService.addProductToDB(new Product(
        "W-SH-327", "חולצת פסים צווארון סירה", "https://api.placeholder.com/w/boat-neck",
        "מראה פריזאי קלאסי. בד: 100% כותנה עבה. פסי רוחב כחול-לבן, שרוול 3/4.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "פסים", "Regular-Fit", "100% כותנה", "Classic", "Parisian", "יומיום")
    ));

    // 28. טופ "סטרפלס" סרוג - Sexy Summer
    productService.addProductToDB(new Product(
        "W-SH-328", "טופ סטרפלס ריב צמוד", "https://api.placeholder.com/w/strapless-rib",
        "גזרה נקייה ומחמיאה. בד: סריג ריב אלסטי. ללא כתפיות, פס סיליקון פנימי למניעת החלקה.",
        999, // 99.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "בז' ניוד", "Slim-Fit", "סריג", "Summer", "Minimalist", "חלק")
    ));

    // 29. חולצת רשת (Mesh) למסיבות
    productService.addProductToDB(new Product(
        "W-SH-329", "טופ רשת מודפס שקוף", "https://api.placeholder.com/w/mesh-top",
        "מראה ערב נועז. בד: 100% ניילון רשת אלסטי. הדפס פסיכדלי בגווני סגול, צווארון גבוה.",
        1299, // 129.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "צבעוני", "Slim-Fit", "Mesh", "Night-Out", "Party", "Bold")
    ));

    // 30. טופ מחוך (Corset Top)
    productService.addProductToDB(new Product(
        "W-SH-330", "טופ מחוך בטקסטורת דנים", "https://api.placeholder.com/w/corset-denim",
        "מראה מחטב ומעוצב. בד: דנים כותנה עם אלסטן. עצמות פנימיות, סגירת רוכסן אחורי כסוף.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "כחול דנים", "Slim-Fit", "Denim", "Sexy", "Night-Out", "Trendy")
    ));

    // 31. חולצת פופלין שרוול נפוח
    productService.addProductToDB(new Product(
        "W-SH-331", "חולצת פופלין שרוול Puff", "https://api.placeholder.com/w/puff-poplin",
        "מראה דרמטי ומחויט. בד: 100% כותנת פופלין פריכה. שרוולים נפוחים מהכתף, חזית כפתורים.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Regular-Fit", "100% כותנה", "Modern", "Office", "Elegant")
    ));

    // 32. גופיית קשירה קדמית
    productService.addProductToDB(new Product(
        "W-SH-332", "גופיית פשתן קשירה מקדימה", "https://api.placeholder.com/w/linen-tie",
        "מושלמת לקיץ הישראלי. בד: 100% פשתן מכובס. שתי קשירות סרט בחזית, מפתח וי עמוק.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "צהוב בננה", "Relaxed-Fit", "100% פשתן", "Summer", "Vacation", "קליל")
    ));

    // 33. חולצת עטלף (Batwing)
    productService.addProductToDB(new Product(
        "W-SH-333", "חולצת סריג עטלף נשפכת", "https://api.placeholder.com/w/batwing-top",
        "גזרה נוחה ומחמיאה לכל גוף. בד: ויסקוזה ופוליאסטר. שרוולים רחבים המתהדקים בפרק כף היד.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "שחור", "Oversize", "Viscose", "Casual", "Comfort", "חלק")
    ));

    // 34. סוודר קרופ (Crop Sweater)
    productService.addProductToDB(new Product(
        "W-SH-334", "סריג קרופ בטקסטורת ריב", "https://api.placeholder.com/w/crop-knit",
        "מראה צעיר ועדכני. בד: אקריליק רך במיוחד. גזרה קצרה מעל המותן, צווארון גולף נמוך.",
        1899, // 189.90 NIS
        Arrays.asList("נשים", "חולצות", "סריגים", "ורוד בייבי", "Crop-Fit", "Acrylic", "Trendy", "Winter", "Streetwear")
    ));

    // 35. חולצת לורקס מנצנצת
    productService.addProductToDB(new Product(
        "W-SH-335", "טופ לורקס מנצנץ לערב", "https://api.placeholder.com/w/lurex-top",
        "מראה מטאלי זוהר. בד: תערובת פוליאסטר וחוטי מתכת (Lurex). שרוולים קצרים, בד נמתח.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "כסף", "Slim-Fit", "Lurex", "Night-Out", "Party", "Metallic")
    ));

    // 36. חולצת סמאק (Smocked)
    productService.addProductToDB(new Product(
        "W-SH-336", "חולצת סמאק פרחונית", "https://api.placeholder.com/w/smocked-floral",
        "מראה רומנטי וכפרי. בד: ויסקוזה דקה. כיווצי גומי (Smocking) לאורך כל הגוף, שרוולים קצרים.",
        1599, // 159.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "פרחוני", "Slim-Fit", "Viscose", "Romantic", "Boho", "Summer")
    ));

    // 37. טופ אסימטרי (One Shoulder)
    productService.addProductToDB(new Product(
        "W-SH-337", "טופ כתף אחת מחטב", "https://api.placeholder.com/w/one-shoulder",
        "מראה מודרני ונקי. בד: 90% ניילון, 10% אלסטן. בד כפול למניעת שקיפות, גזרה צמודה.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "בז' ניוד", "Slim-Fit", "Nylon-Spandex", "Minimalist", "Night-Out", "Modern")
    ));

    // 38. חולצת פליז חמה
    productService.addProductToDB(new Product(
        "W-SH-338", "חולצת פליז Half-Zip", "https://api.placeholder.com/w/fleece-top",
        "לפעילות חוץ או לבית. בד: פליז תרמי רך. רוכסן בחצי הגובה, כיס קנגורו, מחמם במיוחד.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "ירוק מנטה", "Regular-Fit", "Fleece", "Winter", "Active", "Comfort")
    ));

    // 39. חולצת קשירה קדמית (Front Tie)
    productService.addProductToDB(new Product(
        "W-SH-339", "חולצת קרופ קשירה פרפר", "https://api.placeholder.com/w/butterfly-tie",
        "טרנד שנות ה-2000. בד: שיפון פוליאסטר. קשירה במרכז החזה, שרוולי פעמון מתרחבים.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "לבן", "Regular-Fit", "Chiffon", "Y2K", "Trendy", "Summer")
    ));

    // 40. חולצת ג'ינס קלאסית
    productService.addProductToDB(new Product(
        "W-SH-340", "חולצת דנים Western נשים", "https://api.placeholder.com/w/denim-shirt",
        "פריט נצחי. בד: 100% כותנה דנים שטופה. תיקתקים ממתכת, שני כיסי חזה, גזרה מעט צמודה.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ג'ינס", "כחול בינוני", "Regular-Fit", "Denim", "Casual", "Classic", "חלק")
    ));

    // 41. חולצת סאטן קשירה (Wrap)
    productService.addProductToDB(new Product(
        "W-SH-341", "חולצת סאטן מעטפת יוקרתית", "https://api.placeholder.com/w/satin-wrap",
        "מראה ערב נשי ונשפך. בד: סאטן משי סינתטי ברמה גבוהה. קשירה במותן, מחשוף וי מחמיא.",
        2299, // 229.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "כחול רויאל", "Regular-Fit", "Satin", "Evening", "Night-Out", "Luxury")
    ));

    // 42. טופ קרופ מנדף זיעה
    productService.addProductToDB(new Product(
        "W-SH-342", "טופ אימון Crop Performance", "https://api.placeholder.com/w/active-crop-top",
        "מיועד ליוגה ואימוני כוח. בד: ניילון ואלסטן Seamless. בד מחטב ונושם עם חורים לאוורור מתחת לחזה.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "ורוד פסטל", "Slim-Fit", "Seamless", "Active", "Gym", "Sport")
    ));

    // 43. חולצת "ויקטוריאנית" עם תחרה
    productService.addProductToDB(new Product(
        "W-SH-343", "חולצת תחרה צווארון גבוה", "https://api.placeholder.com/w/victorian-lace",
        "מראה רטרו רומנטי. בד: כותנה משולבת עם תחרה עדינה. כפתורי פנינה קטנים, שרוולים נפוחים.",
        2599, // 259.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "שמנת", "Regular-Fit", "Lace-Cotton", "Romantic", "Vintage", "Elegant")
    ));

    // 44. גופיית "הולטר" (Halter Neck)
    productService.addProductToDB(new Product(
        "W-SH-344", "טופ הולטר סרוג גב פתוח", "https://api.placeholder.com/w/halter-knit",
        "מראה קיץ נועז. בד: סריג ויסקוזה קריר. קשירה מאחורי הצוואר, חושפת כתפיים וגב.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "שחור", "Slim-Fit", "Viscose", "Summer", "Night-Out", "Sexy")
    ));

    // 45. סוודר "צ'אנקי" (Chunky Knit)
    productService.addProductToDB(new Product(
        "W-SH-345", "סוודר צ'אנקי Oversize כבד", "https://api.placeholder.com/w/chunky-sweater",
        "להרגיש בתוך ענן. בד: 100% אקריליק רך בסריגה עבה מאוד. צווארון גולף ענק, גזרה רחבה במיוחד.",
        2799, // 279.90 NIS
        Arrays.asList("נשים", "חולצות", "סריגים", "בז' מלנז'", "Oversize", "Chunky-Knit", "Winter", "Warm", "Comfort")
    ));

    // 46. חולצת פשתן קרופ (Crop Linen)
    productService.addProductToDB(new Product(
        "W-SH-346", "חולצת פשתן קצרה מעוצבת", "https://api.placeholder.com/w/crop-linen",
        "קרירה ומתוחכמת. בד: 100% פשתן איכותי. כפתורים גדולים מצופים בד, שרוול קצר מתקפל.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "ירוק זית", "Crop-Fit", "100% פשתן", "Summer", "Minimalist", "Vacation")
    ));

    // 47. חולצת בייסיק צווארון מרובע
    productService.addProductToDB(new Product(
        "W-SH-347", "טופ ריב צווארון מרובע (Square)", "https://api.placeholder.com/w/square-neck",
        "גזרה מחמיאה לקו הצוואר. בד: 95% כותנה, 5% לייקרה. בד ריב כפול למראה נקי.",
        899, // 89.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "לבן", "Slim-Fit", "Ribbed", "Basic", "Minimalist", "יומיום")
    ));

    // 48. חולצת "מעיל" (Shacket) נשים
    productService.addProductToDB(new Product(
        "W-SH-348", "ג'קט חולצה פלאנל ורוד", "https://api.placeholder.com/w/pink-shacket",
        "שילוב מושלם בין חולצה לג'קט. בד: פלאנל כותנה רך. משבצות ורוד-אפור, כיסי צד גדולים.",
        2399, // 239.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוכות", "משובץ", "Oversize", "Flannel", "Autumn", "Casual", "Streetwear")
    ));

    // 49. בגד גוף (Bodysuit) א-סימטרי
    productService.addProductToDB(new Product(
        "W-SH-349", "בגד גוף כתף אחת מחטב", "https://api.placeholder.com/w/asymmetric-body",
        "מראה מודרני וערבי. בד: ניילון ואלסטן בעל ברק עדין. גזרה צמודה מאוד, סגירת תיקתקים.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "בגד גוף", "בורדו", "Slim-Fit", "Nylon-Spandex", "Modern", "Night-Out", "Trendy")
    ));

    // 50. חולצת "סמאק" (Smocked) קרופ
    productService.addProductToDB(new Product(
        "W-SH-350", "טופ סמאק פרחוני קצר", "https://api.placeholder.com/w/floral-smock",
        "מראה צעיר ורומנטי. בד: ויסקוזה נשפכת. גומי מכווץ לאורך כל הגוף, שרוולי מלמלה תפוחים.",
        1299, // 129.90 NIS
        Arrays.asList("נשים", "חולצות", "קצרות", "פרחוני", "Slim-Fit", "Viscose", "Romantic", "Summer", "Boho")
    ));
}
private void seedMensPants() 
{
    // 1. ג'ינס דנים קלאסי - מתאים ל-Casual/Autumn
    productService.addProductToDB(new Product(
        "M-PA-301", "ג'ינס Selvedge Denim", "https://api.placeholder.com/mens-jeans",
        "ג'ינס איכותי ועמיד. בד: דנים 14oz. גזרת Straight, חמישה כיסים.",
        2499, // 249.90 ₪
        Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Classic", "דנים", "Casual", "Autumn", "חלק")
    ));

    // 2. מכנסי צ'ינו Slim-Fit - מתאים ל-Office/Elegant
    productService.addProductToDB(new Product(
        "M-PA-302", "מכנסי צ'ינו Stretch", "https://api.placeholder.com/mens-chinos",
        "מכנסיים מחויטים למשרד. בד: 98% כותנה, 2% לייקרה. גזרה צמודה ומחמיאה.",
        1999, // 199.90 ₪
        Arrays.asList("גברים", "מכנסיים", "ארוך", "בז'", "Slim-Fit", "100% כותנה", "Elegant", "Office", "Spring-Essentials", "חלק")
    ));

    // 3. שורטס פשתן קלילים - מתאים ל-Vacation/Summer
    productService.addProductToDB(new Product(
        "M-PA-303", "שורטס פשתן Cuban Style", "https://api.placeholder.com/mens-shorts-linen",
        "מכנסיים קצרים ואווריריים לקיץ. בד: 100% פשתן נושם. שרוך קשירה במותן.",
        1499, // 149.90 ₪
        Arrays.asList("גברים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "100% פשתן", "Minimalist", "Vacation", "Summer", "חלק")
    ));

    // 4. מכנסי דגמ"ח Cargo - מתאים ל-Streetwear/Gym
    productService.addProductToDB(new Product(
        "M-PA-304", "מכנסי קרגו Utility", "https://api.placeholder.com/mens-cargo",
        "מכנסיים פונקציונליים עם כיסים גדולים. בד: כותנה עמידה בטקסטורת ריפסטופ.",
        2299, // 229.90 ₪
        Arrays.asList("גברים", "מכנסיים", "דגמחמ", "ירוק זית", "Oversize", "100% כותנה", "Streetwear", "Gym", "Autumn", "חלק")
    ));

    // 5. מכנסי פשתן Relaxed - לחופשה/קיץ
productService.addProductToDB(new Product(
    "M-PA-305", "מכנסי פשתן Relaxed Fit", "https://api.placeholder.com/linen-pants",
    "בד: 100% פשתן אירופאי נושם. גזרה רחבה ומשוחררת, שרוך קשירה פנימי במותן.",
    1799, // 179.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Relaxed-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));

// 6. מכנסי צ'ינו Slim-Fit אפורים - למשרד
productService.addProductToDB(new Product(
    "M-PA-306", "מכנסי צ'ינו Stretch Slim", "https://api.placeholder.com/chino-grey",
    "בד: 97% כותנה סרוקה, 3% לייקרה (280 GSM). גזרה צמודה ומחמיאה לעבודה.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "100% כותנה", "Office", "Classic", "Spring-Essentials", "חלק")
));

// 7. מכנסי טרנינג Heavyweight - לחדר כושר/סטריטוור
productService.addProductToDB(new Product(
    "M-PA-307", "מכנסי ג'וגר כותנה כבדה", "https://api.placeholder.com/heavy-sweatpants",
    "בד: 100% כותנה (450 GSM). סיומת מנג'ט בקרסול, כיסים עמוקים עם רוכסן.",
    1699, // 169.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Oversize", "100% כותנה", "Gym", "Streetwear", "Winter", "חלק")
));

// 8. מכנסי צ'ינו Slim-Fit כחול נייבי - למשרד
productService.addProductToDB(new Product(
    "M-PA-308", "מכנסי צ'ינו Slim Navy", "https://api.placeholder.com/mens-chinos-navy",
    "בד: 98% כותנה, 2% אלסטן. גזרה צמודה ומחויטת, מתאים למראה רשמי או יומיומי משודרג.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Slim-Fit", "100% כותנה", "Office", "Classic", "Spring-Essentials", "חלק")
));

// 9. מכנסי דגמ"ח (Cargo) ירוק זית - סטריטוור
productService.addProductToDB(new Product(
    "M-PA-309", "מכנסי קרגו Utility Olive", "https://api.placeholder.com/mens-cargo-olive",
    "בד: 100% כותנת טוויל עמידה. גזרת Relaxed עם 6 כיסים פונקציונליים.",
    2499, // 249.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "ירוק זית", "Regular-Fit", "100% כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 10. מכנסי פשתן שחורים - קיץ/ערב
productService.addProductToDB(new Product(
    "M-PA-310", "מכנסי פשתן שחורים Relaxed", "https://api.placeholder.com/mens-linen-black",
    "בד: 100% פשתן אירופאי. גזרה רחבה וקלילה, מושלם לערבי קיץ או חופשות.",
    1899, // 189.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Relaxed-Fit", "100% פשתן", "Minimalist", "Vacation", "Summer", "חלק")
));

// 11. ג'ינס אפור Slim - יציאה בלילה
productService.addProductToDB(new Product(
    "M-PA-311", "ג'ינס סקיני אפור", "https://api.placeholder.com/mens-grey-jeans",
    "בד: דנים אלסטי. גזרה צמודה במראה מודרני, שטיפה אפורה כהה.",
    2299, // 229.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "דנים", "Night-Out", "Streetwear", "Winter", "חלק")
));

// 12. שורטס כותנה ירוק זית - יומיום
productService.addProductToDB(new Product(
    "M-PA-312", "מכנסי שורטס Chino Short", "https://api.placeholder.com/mens-shorts-olive",
    "בד: כותנה קלה. גזרה ישרה מעל הברך, מושלם ליומיום בקיץ הישראלי.",
    1399, // 139.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "ירוק זית", "Regular-Fit", "100% כותנה", "Casual", "Summer", "חלק")
));

// 13. מכנסי אלגנט צמר מרינו - לאירועים
productService.addProductToDB(new Product(
    "M-PA-313", "מכנסי צמר מרינו מחויטים", "https://api.placeholder.com/mens-wool-pants",
    "בד: 100% צמר מרינו דק. גזרה קלאסית עם כפלים, מתאים לחתונה או אירוע רשמי.",
    3999, // 399.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 14. מכנסי קורדרוי קלאסיים - Winter/Classic
productService.addProductToDB(new Product(
    "M-PA-314", "מכנסי קורדרוי חומים", "https://api.placeholder.com/mens-corduroy",
    "בד: 100% כותנה בטקסטורת קורדרוי עדינה (320 GSM). גזרה ישרה, מראה חורפי קלאסי ומחמם.",
    2699, // 269.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "בז'", "Regular-Fit", "100% כותנה", "Classic", "Winter", "חלק")
));

// 15. מכנסיים משובצים מחויטים - Office/Elegant
productService.addProductToDB(new Product(
    "M-PA-315", "מכנסי צ'ינו משובצים", "https://api.placeholder.com/mens-plaid-chinos",
    "בד: תערובת כותנה וצמר. דוגמת משבצות 'Windowpane' עדינה, מושלם למראה משרדי מתוחכם.",
    2899, // 289.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "100% כותנה", "Elegant", "Office", "Autumn", "משובץ")
));

// 16. שורטס ריצה טכניים - Gym
productService.addProductToDB(new Product(
    "M-PA-316", "מכנסי אימון Short-Running", "https://api.placeholder.com/mens-gym-shorts",
    "בד: פוליאסטר קל ומנדף (Dry-Fit). גזרה קצרה עם שסעים בצדדים לתנועה חופשית במכון.",
    1199, // 119.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "שחור", "Regular-Fit", "100% כותנה", "Gym", "Summer", "חלק")
));

// 17. מכנסיים מחויטים כחול נייבי - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-317", "מכנסי ערב Tuxedo-Style", "https://api.placeholder.com/mens-formal-navy",
    "בד: 100% צמר מרינו דק ואיכותי. גזרה מחויטת עם פס צד מעודן, מתאים לאירועים רשמיים.",
    4299, // 429.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "All-Season", "חלק")
));

// 18. ג'ינס שחור פרום - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-PA-318", "ג'ינס סקיני שחור Ripped", "https://api.placeholder.com/mens-black-ripped",
    "בד: דנים אלסטי שטוף. קרעים עדינים בברכיים, מראה מחוספס ליציאה בלילה.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "דנים", "Night-Out", "Streetwear", "All-Season", "חלק")
));

// 19. מכנסי פשתן ירוק זית - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-319", "מכנסי פשתן Olive Summer", "https://api.placeholder.com/mens-linen-olive",
    "בד: 100% פשתן מכובס למגע רך. גזרה משוחררת, שרוך קשירה במותן למראה נינוח בחופשה.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק זית", "Relaxed-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));
// 20. מכנסי צ'ינו Slim-Fit שחורים - Night-Out/Minimalist
productService.addProductToDB(new Product(
    "M-PA-320", "מכנסי צ'ינו Jet Black", "https://api.placeholder.com/mens-black-chinos",
    "בד: 98% כותנה סרוקה, 2% אלסטן. מראה נקי ומינימליסטי המתאים ליציאה בערב או לפגישות.",
    2299, // 229.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "100% כותנה", "Night-Out", "Minimalist", "All-Season", "חלק")
));

// 21. מכנסיים מחויטים משובצים - Office/Classic
productService.addProductToDB(new Product(
    "M-PA-321", "מכנסיים מחויטים Glen Check", "https://api.placeholder.com/mens-plaid-office",
    "בד: תערובת כותנה וצמר קל. דוגמת משבצות קלאסית בגווני אפור וכחול נייבי.",
    3199, // 319.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "100% כותנה", "Office", "Classic", "Autumn", "משובץ")
));

// 22. מכנסי פשתן קצרים כחול נייבי - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-322", "שורטס פשתן Navy Summer", "https://api.placeholder.com/mens-navy-shorts",
    "בד: 100% פשתן נושם. גזרה ישרה מעל הברך עם שרוך קשירה, אידיאלי לחופשות ולים.",
    1599, // 159.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "כחול נייבי", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));

// 23. ג'ינס אפור כהה שטוף - Streetwear/Casual
productService.addProductToDB(new Product(
    "M-PA-323", "ג'ינס Grey Wash Denim", "https://api.placeholder.com/mens-grey-denim",
    "בד: דנים 12oz עם מעט סטרץ'. מראה אורבני משופשף, גזרת Slim-Fit נוחה ליומיום.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "דנים", "Streetwear", "Casual", "Winter", "חלק")
));

// 24. מכנסי דגמ"ח לבנים - Minimalist/Summer
productService.addProductToDB(new Product(
    "M-PA-324", "מכנסי קרגו לבנים Lightweight", "https://api.placeholder.com/mens-white-cargo",
    "בד: כותנת פופלין דקה. כיסי צד שטוחים למראה נקי, מתאים למזג אוויר חם.",
    2399, // 239.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "לבן", "Regular-Fit", "100% כותנה", "Minimalist", "Summer", "חלק")
));

// 25. מכנסי צמר מרינו יוקרתיים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-325", "מכנסי ערב צמר מרינו", "https://api.placeholder.com/mens-luxury-wool",
    "בד: 100% צמר מרינו משובח (Super 120s). גזרה מחויטת צמודה, הכי אלגנטי שיש לחתונה.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 26. מכנסי ריצה (Joggers) ירוק זית - Gym/Streetwear
productService.addProductToDB(new Product(
    "M-PA-326", "ג'וגר סריג Olive Active", "https://api.placeholder.com/mens-olive-jogger",
    "בד: תערובת כותנה ופוליאסטר (320 GSM). גומי בקרסול וסגירת שרוך, למכון או ללוק יומיומי.",
    1899, // 189.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק זית", "Relaxed-Fit", "100% כותנה", "Gym", "Streetwear", "Spring-Essentials", "חלק")
));

// 27. מכנסי פסים דקים - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-327", "מכנסי פשתן פסים סיכה", "https://api.placeholder.com/mens-striped-linen",
    "בד: פשתן וכותנה. דוגמת פסי אורך כחול-לבן, מראה Vacation קלאסי וקליל.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "פסים")
));

// 28. מכנסי צ'ינו Slim-Fit ירוק זית - Office/Autumn
productService.addProductToDB(new Product(
    "M-PA-328", "מכנסי צ'ינו Olive Slim", "https://api.placeholder.com/mens-olive-chinos",
    "בד: 98% כותנה סרוקה, 2% אלסטן. מראה מחויט ומודרני, מושלם לשילוב עם חולצת אוקספורד.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק זית", "Slim-Fit", "100% כותנה", "Office", "Classic", "Autumn", "חלק")
));

// 29. מכנסי פשתן כחול נייבי - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-329", "מכנסי פשתן Navy Relaxed", "https://api.placeholder.com/mens-navy-linen",
    "בד: 100% פשתן טבעי. גזרה רחבה ונושמת, אידיאלי לאירועי צהריים או חופשות בקיץ.",
    2499, // 249.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Relaxed-Fit", "100% פשתן", "Vacation", "Elegant", "Summer", "חלק")
));

// 30. מכנסי דגמ"ח משובצים - Streetwear/Autumn
productService.addProductToDB(new Product(
    "M-PA-330", "מכנסי קרגו משובצים Grey", "https://api.placeholder.com/mens-plaid-cargo",
    "בד: כותנה עמידה בטקסטורת משבצות עדינה. 6 כיסים וגזרת Oversize למראה אורבני בולט.",
    2699, // 269.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "אפור", "Oversize", "100% כותנה", "Streetwear", "Autumn", "משובץ")
));

// 31. ג'ינס לבן נקי - Minimalist/Spring
productService.addProductToDB(new Product(
    "M-PA-331", "ג'ינס לבן Slim-Fit", "https://api.placeholder.com/mens-white-denim",
    "בד: דנים לבן איכותי. מראה נקי ומינימליסטי שמתאים בול לעונת המעבר.",
    2399, // 239.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Slim-Fit", "דנים", "Minimalist", "Spring-Essentials", "חלק")
));

// 32. מכנסי טרנינג אפורים - Gym/Casual
productService.addProductToDB(new Product(
    "M-PA-332", "מכנסי פליז Grey Heather", "https://api.placeholder.com/mens-grey-sweatpants",
    "בד: 100% כותנה מוברשת (350 GSM). גזרה נוחה לאימון או לבית, רך ומחמם.",
    1599, // 159.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "100% כותנה", "Gym", "Casual", "Winter", "חלק")
));

// 33. מכנסי צמר מרינו שחורים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-333", "מכנסי חליפה צמר מרינו", "https://api.placeholder.com/mens-black-wool",
    "בד: 100% צמר מרינו Super 100s. גזרה קלאסית מחויטת, מתאים לאירועים רשמיים מאוד.",
    4599, // 459.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 34. שורטס פסים כחול-לבן - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-334", "שורטס פשתן פסים סיכה", "https://api.placeholder.com/mens-striped-shorts",
    "בד: תערובת פשתן וכותנה. דוגמת פסים קלאסית, קליל ואופנתי לחופשת קיץ.",
    1699, // 169.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "פסים")
));

// 35. ג'ינס דנים Western כהה - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-PA-335", "ג'ינס Raw Denim כהה", "https://api.placeholder.com/mens-dark-denim",
    "בד: 100% כותנה קשיחה. צבע אינדיגו עמוק, גזרת Straight מחוספסת ליציאה בערב.",
    2899, // 289.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Regular-Fit", "דנים", "Night-Out", "Streetwear", "Winter", "חלק")
));

// 36. מכנסי צמר מרינו דקים - Elegant/Office
productService.addProductToDB(new Product(
    "M-PA-336", "מכנסי צמר מרינו Slim", "https://api.placeholder.com/mens-wool-slim",
    "בד: 100% צמר מרינו. גזרה מחויטת ויוקרתית, דוחה קמטים ומתאימה ליום עבודה ארוך או לאירוע.",
    3899, // 389.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "צמר מרינו", "Elegant", "Office", "Winter", "חלק")
));

// 37. מכנסי דגמ"ח (Cargo) שחורים - Streetwear/Night-Out
productService.addProductToDB(new Product(
    "M-PA-337", "מכנסי קרגו Black Urban", "https://api.placeholder.com/mens-cargo-black",
    "בד: 100% כותנה. גזרת Oversize עם כיסים נפוחים, מראה אורבני חזק שמתאים גם ליציאה בלילה.",
    2699, // 269.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "שחור", "Oversize", "100% כותנה", "Streetwear", "Night-Out", "Autumn", "חלק")
));

// 38. שורטס פסים כחול-לבן - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-338", "שורטס פשתן פסים", "https://api.placeholder.com/mens-striped-shorts",
    "בד: 100% פשתן. דוגמת פסים כחולים על רקע לבן, מראה Vacation קלאסי ומרענן.",
    1699, // 169.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "פסים")
));

// 39. ג'ינס כחול נייבי - Classic/Casual
productService.addProductToDB(new Product(
    "M-PA-339", "ג'ינס Indigo Denim", "https://api.placeholder.com/mens-indigo-jeans",
    "בד: דנים 100% כותנה (13oz). גזרה ישרה וקלאסית, צבע כחול עמוק שמתאים לכל יום.",
    2799, // 279.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Classic", "דנים", "Casual", "All-Season", "חלק")
));

// 40. מכנסיים מחויטים לבנים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-340", "מכנסי ערב פשתן לבנים", "https://api.placeholder.com/mens-white-linen-pants",
    "בד: 100% פשתן איכותי. גזרה מחויטת לאירועי צהריים או חתונות חוף.",
    2999, // 299.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Wedding", "Elegant", "Summer", "חלק")
));

// 41. מכנסי טרנינג ירוק זית - Gym/Casual
productService.addProductToDB(new Product(
    "M-PA-341", "מכנסי ג'וגר Olive", "https://api.placeholder.com/mens-olive-sweatpants",
    "בד: 100% כותנה. נוחות מקסימלית לאימון או ללוק יומיומי משוחרר.",
    1599, // 159.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק זית", "Relaxed-Fit", "100% כותנה", "Gym", "Casual", "Spring-Essentials", "חלק")
));

// 42. מכנסיים משובצים אפורים - Minimalist/Office
productService.addProductToDB(new Product(
    "M-PA-342", "מכנסי צ'ינו משובצים", "https://api.placeholder.com/mens-grey-check",
    "בד: כותנה עבה. דוגמת משבצות עדינה ומינימליסטית למראה משרדי מתוחכם.",
    2499, // 249.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "100% כותנה", "Minimalist", "Office", "Autumn", "משובץ")
));

// 43. שורטס דנים שטופים - Streetwear/Summer
productService.addProductToDB(new Product(
    "M-PA-343", "שורטס ג'ינס Washed Blue", "https://api.placeholder.com/mens-denim-shorts",
    "בד: דנים 100% כותנה. מראה משופשף וקולי, מתאים לסטריטוור קיצי.",
    1399, // 139.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "כחול נייבי", "Regular-Fit", "דנים", "Streetwear", "Summer", "חלק")
));

// 44. מכנסיים מחויטים - Elegant/Wedding
productService.addProductToDB(new Product(
    "M-PA-344", "מכנסי ערב צמר מרינו Slim", "https://api.placeholder.com/mens-wool-wedding",
    "מכנסיים יוקרתיים לגזרה צמודה. בד: 100% צמר מרינו. מתאים לחתונה או אירוע רשמי.",
    4199, // 419.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "צמר מרינו", "Elegant", "Wedding", "Winter", "חלק")
));

// 45. מכנסי קרגו (מראה דגמ"ח) - Streetwear/Oversize
productService.addProductToDB(new Product(
    "M-PA-345", "מכנסי קרגו Streetwear Cotton", "https://api.placeholder.com/mens-cargo-street",
    "מכנסיים עם כיסי צד גדולים. בד: 100% כותנה. גזרה רחבה למראה סטריטוור מודרני.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק זית", "Oversize", "100% כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 46. מכנסי פשתן קלים - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-346", "מכנסי פשתן לבנים Relaxed", "https://api.placeholder.com/mens-linen-summer",
    "מכנסיים אווריריים לחופשה. בד: 100% פשתן. גזרה משוחררת עם שרוך.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Relaxed-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));

// 47. ג'ינס אפור - Streetwear/Night-Out
productService.addProductToDB(new Product(
    "M-PA-347", "ג'ינס אפור שטוף", "https://api.placeholder.com/mens-grey-denim",
    "ג'ינס בגזרה ישרה. בד: דנים 100% כותנה. מתאים ליציאה בערב או ליומיום.",
    2399, // 239.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "דנים", "Streetwear", "Night-Out", "All-Season", "חלק")
));

// 48. מכנסי אימון - Gym/Casual
productService.addProductToDB(new Product(
    "M-PA-348", "מכנסי ג'וגר Active Gym", "https://api.placeholder.com/mens-gym-pants",
    "מכנסיים נוחים לאימון. בד: 100% כותנה. גזרה נוחה עם גומי בקרסול.",
    1799, // 179.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול נייבי", "Regular-Fit", "100% כותנה", "Gym", "Casual", "Spring-Essentials", "חלק")
));

// 49. מכנסי צ'ינו בז' - Office/Minimalist
productService.addProductToDB(new Product(
    "M-PA-349", "מכנסי צ'ינו Minimalist Beige", "https://api.placeholder.com/mens-chinos-minimal",
    "מראה נקי למשרד. בד: 100% כותנה. גזרה ישרה ומחויטת.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "בז'", "Regular-Fit", "100% כותנה", "Minimalist", "Office", "Spring-Essentials", "חלק")
));

// 50. שורטס משובצים - Casual/Summer
productService.addProductToDB(new Product(
    "M-PA-350", "שורטס משובצים Classic Check", "https://api.placeholder.com/mens-plaid-shorts",
    "מכנסיים קצרים ליומיום. בד: 100% כותנה. דוגמת משבצות עדינה.",
    1499, // 149.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "אפור", "Regular-Fit", "100% כותנה", "Casual", "Summer", "משובץ")
));
}

private void seedWomensPants() {
    // 1. ג'ינס רחב High-Waist - מתאים ל-Streetwear/Minimalist
    productService.addProductToDB(new Product(
        "W-PA-401", "ג'ינס Wide-Leg רטרו", "https://api.placeholder.com/womens-wide-jeans",
        "גזרה גבוהה ורחבה מאוד. בד: דנים שטוף. מראה מודרני ונקי.",
        2199, // 219.90 ₪
        Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Wide-Leg", "דנים", "Streetwear", "Spring-Essentials", "חלק")
    ));

    // 2. מכנסיים מחויטים - מתאים ל-Office/Wedding
    productService.addProductToDB(new Product(
        "W-PA-402", "מכנסי סיגנר מחויטים", "https://api.placeholder.com/womens-tailored",
        "מכנסיים אלגנטיים עם כפלים. בד סאטן יוקרתי למגע משי.",
        2799, // 279.90 ₪
        Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "סאטן", "Elegant", "Wedding", "Winter", "חלק")
    ));

    // 3. שורטס ג'ינס Mom-Fit - מתאים ל-Casual/Summer
    productService.addProductToDB(new Product(
        "W-PA-403", "שורטס דנים Mom-Fit", "https://api.placeholder.com/womens-shorts",
        "מכנסיים קצרים ונוחים ליומיום. בד: 100% כותנה קשיחה.",
        1299, // 129.90 ₪
        Arrays.asList("נשים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "100% כותנה", "Casual", "Vacation", "Summer", "חלק")
    ));

    // 4. מכנסי פשתן רחבים - לקיץ
productService.addProductToDB(new Product(
    "W-PA-404", "מכנסי פשתן Wide-Leg", "https://api.placeholder.com/women-linen",
    "בד: 100% פשתן איכותי. גזרה גבוהה ורחבה מאוד, מושלם למראה נקי ואוורירי.",
    1999, // 199.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "100% פשתן", "Minimalist", "Vacation", "Summer", "חלק")
));

// 5. טייץ Seamless מחטב - ספורט
productService.addProductToDB(new Product(
    "W-PA-405", "טייץ אימון Seamless", "https://api.placeholder.com/yoga-leggings",
    "בד: ניילון וספנדקס בטכנולוגיית נידוף זיעה. גזרה צמודה ללא תפרים צדדיים.",
    1499, // 149.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק זית", "Slim-Fit", "סאטן", "Gym", "Active", "All-Season", "חלק")
));

// 6. מכנסי דגמ"ח Streetwear
productService.addProductToDB(new Product(
    "W-PA-406", "מכנסי קרגו נשים", "https://api.placeholder.com/women-cargo",
    "בד: 100% כותנת טוויל עמידה. ריבוי כיסי צד, גזרת Loose מחמיאה.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "אפור", "Oversize", "100% כותנה", "Streetwear", "Autumn", "חלק")
));

// 7. מכנסי סאטן לבנים - אירוע/חתונה
productService.addProductToDB(new Product(
    "W-PA-407", "מכנסי סאטן Wide-Leg לבנים", "https://api.placeholder.com/womens-satin-white",
    "בד: סאטן מבריק ויוקרתי. גזרה רחבה ונשפכת, מראה אלגנטי לאירועים.",
    2899, // 289.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Wide-Leg", "סאטן", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 8. ג'ינס Mom-Fit שחור - יומיום
productService.addProductToDB(new Product(
    "W-PA-408", "ג'ינס Mom-Fit שחור", "https://api.placeholder.com/womens-mom-jeans",
    "בד: דנים קשיח. גזרה גבוהה ונוחה, מראה רטרו קלאסי.",
    1999, // 199.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "דנים", "Casual", "Streetwear", "Winter", "חלק")
));

// 9. שורטס פשתן בז' - חופשה
productService.addProductToDB(new Product(
    "W-PA-409", "שורטס פשתן קלילים", "https://api.placeholder.com/womens-linen-shorts",
    "בד: 100% פשתן נושם. גזרה נינוחה עם גומי במותן, מושלם לים ולחופשות.",
    1299, // 129.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "בז'", "Relaxed-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));

// 10. טייץ נייבי - אימון
productService.addProductToDB(new Product(
    "W-PA-410", "טייץ High-Rise Performance", "https://api.placeholder.com/womens-gym-leggings",
    "בד: פוליאסטר אלסטי מנדף. גזרה צמודה ומחטבת לפעילות ספורטיבית.",
    1699, // 169.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Skinny", "סאטן", "Gym", "Streetwear", "All-Season", "חלק")
));

// 11. מכנסיים משובצים למשרד
productService.addProductToDB(new Product(
    "W-PA-411", "מכנסי סיגר משובצים", "https://api.placeholder.com/womens-plaid-pants",
    "בד: כותנה עבה. דוגמת משבצות עדינה בגווני אפור, גזרה ישרה.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "100% כותנה", "Office", "Classic", "Autumn", "משובץ")
));

// 12. מכנסי קרגו (דגמ"ח) בז' - סטריטוור
productService.addProductToDB(new Product(
    "W-PA-412", "מכנסי קרגו נשים Street", "https://api.placeholder.com/womens-cargo-beige",
    "בד: 100% כותנת טוויל. גזרה רחבה עם כיסי צד גדולים, מראה אורבני.",
    2599, // 259.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "בז'", "Oversize", "100% כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 13. מכנסי סאטן שחורים - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-PA-413", "מכנסי סאטן Flowy", "https://api.placeholder.com/womens-satin-black",
    "בד: סאטן רך עם ברק עדין. גזרה רחבה ונשפכת, מושלם לערב או לאירוע חגיגי.",
    2699, // 269.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "Winter", "חלק")
));

// 14. מכנסי פסים קלילים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-414", "מכנסי פסים Vertical Stripe", "https://api.placeholder.com/womens-striped-pants",
    "בד: תערובת כותנה ופשתן. דוגמת פסי אורך דקים בכחול-לבן, מראה קיצי ומרענן.",
    1799, // 179.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Relaxed-Fit", "100% פשתן", "Vacation", "Summer", "פסים")
));

// 15. מכנסי צמר למשרד - Office/Winter
productService.addProductToDB(new Product(
    "W-PA-415", "מכנסי צמר מרינו מחויטים", "https://api.placeholder.com/womens-wool-office",
    "בד: 100% צמר מרינו קל. גזרה ישרה עם כפל קדמי (Crease), מתאים לימי חורף במשרד.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Classic", "Winter", "חלק")
));

// 16. שורטס פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-416", "שורטס פרחוניים Floral Bloom", "https://api.placeholder.com/womens-floral-shorts",
    "בד: ויסקוזה נעימה. הדפס פרחים צבעוני על רקע לבן, גזרה גבוהה עם גומי במותן.",
    1199, // 119.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "לבן", "Relaxed-Fit", "100% כותנה", "Vacation", "Summer", "פרחוני")
));

// 17. ג'ינס Wide-Leg אפור - Streetwear
productService.addProductToDB(new Product(
    "W-PA-417", "ג'ינס אפור Wide-Leg", "https://api.placeholder.com/womens-grey-wide",
    "בד: דנים קשיח בטכנולוגיית Eco-Wash. גזרה רחבה מאוד מהמותן ומטה, מראה סטריטוור עדכני.",
    2299, // 229.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Wide-Leg", "דנים", "Streetwear", "Autumn", "חלק")
));

// 18. מכנסי דגמ"ח לבנים - Minimalist/Casual
productService.addProductToDB(new Product(
    "W-PA-418", "מכנסי קרגו נקיים", "https://api.placeholder.com/womens-white-cargo",
    "בד: 100% כותנה. כיסים שטוחים למראה מינימליסטי, מתאים ליומיום בקיץ ובאביב.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "לבן", "Regular-Fit", "100% כותנה", "Minimalist", "Spring-Essentials", "חלק")
));

// 19. מכנסי סאטן כחול נייבי - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-PA-419", "מכנסי סאטן Midnight Navy", "https://api.placeholder.com/womens-navy-satin",
    "בד: סאטן יוקרתי ונשפך. גזרה רחבה מאוד (Wide-Leg), מראה זוהר ליציאה בלילה.",
    2999, // 299.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 20. ג'ינס Wide-Leg לבן - Minimalist/Spring
productService.addProductToDB(new Product(
    "W-PA-420", "ג'ינס לבן רחב High-Rise", "https://api.placeholder.com/womens-white-denim",
    "בד: דנים 100% כותנה. גזרה גבוהה ומחמיאה, מתאים למראה אביבי נקי.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Wide-Leg", "דנים", "Minimalist", "Spring-Essentials", "חלק")
));

// 21. טייץ ספורט בז' - Gym
productService.addProductToDB(new Product(
    "W-PA-421", "טייץ אימון Sand Seamless", "https://api.placeholder.com/womens-sand-leggings",
    "בד: ניילון אלסטי מחטב. ללא תפרים, גזרה צמודה (Skinny) לאימונים אינטנסיביים.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Slim-Fit", "סאטן", "Gym", "Active", "Summer", "חלק")
));

// 22. מכנסי פשתן משובצים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-422", "מכנסי פשתן משבצות Vichy", "https://api.placeholder.com/womens-check-linen",
    "בד: 100% פשתן. דוגמת משבצות קטנות בשחור-לבן, מראה כפרי וקליל לחופשה.",
    2199, // 219.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "משובץ")
));

// 23. מכנסיים מחויטים ירוק זית - Office/Elegant
productService.addProductToDB(new Product(
    "W-PA-423", "מכנסי סיגר Olive Tailored", "https://api.placeholder.com/womens-olive-pants",
    "בד: כותנה וצמר קל. גזרה מחויטת עם כפל קדמי, מתאים למשרד או לאירוע צהריים.",
    2699, // 269.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק זית", "Regular-Fit", "100% כותנה", "Office", "Elegant", "Autumn", "חלק")
));

// 24. שורטס ג'ינס שחור - Casual/Streetwear
productService.addProductToDB(new Product(
    "W-PA-424", "שורטס דנים Washed Black", "https://api.placeholder.com/womens-black-shorts",
    "בד: 100% כותנה. קצוות פרומים ומראה משופשף, פריט חובה לקיץ.",
    1399, // 139.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "שחור", "Regular-Fit", "דנים", "Casual", "Streetwear", "Summer", "חלק")
));

// 25. מכנסי סאטן פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-425", "מכנסי סאטן Floral Breeze", "https://api.placeholder.com/womens-floral-satin",
    "בד: סאטן רך. הדפס פרחים עדין על רקע בז', גזרה רחבה וקלילה.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "סאטן", "Vacation", "Summer", "פרחוני")
));

// 26. מכנסי דגמ"ח אפורים - Streetwear/Autumn
productService.addProductToDB(new Product(
    "W-PA-426", "מכנסי קרגו Grey Urban", "https://api.placeholder.com/womens-grey-cargo",
    "בד: כותנה עמידה. ריבוי כיסים וגזרת Oversize למראה סטריטוור מודרני.",
    2599, // 259.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "אפור", "Oversize", "100% כותנה", "Streetwear", "Autumn", "חלק")
));

// 27. מכנסי סאטן בצבע בז' - Wedding/Night-Out
productService.addProductToDB(new Product(
    "W-PA-427", "מכנסי סאטן Champagne", "https://api.placeholder.com/womens-beige-satin",
    "בד: סאטן מבריק נשפך. גזרה רחבה ויוקרתית, מושלם לאירועי ערב או חתונות.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "סאטן", "Wedding", "Night-Out", "Spring-Essentials", "חלק")
));

// 28. ג'ינס Wide-Leg שחור - Streetwear/Minimalist
productService.addProductToDB(new Product(
    "W-PA-428", "ג'ינס שחור רחב High-Rise", "https://api.placeholder.com/womens-black-wide",
    "בד: דנים 100% כותנה. גזרה גבוהה ומחמיאה, מראה אורבני נקי שמתאים לכל דבר.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Wide-Leg", "דנים", "Streetwear", "Minimalist", "Autumn", "חלק")
));

// 29. מכנסי פשתן לבנים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-429", "מכנסי פשתן קיציים White", "https://api.placeholder.com/womens-white-linen",
    "בד: 100% פשתן אוורירי. גזרה ישרה ונינוחה, פריט חובה לכל חופשה בקיץ.",
    1899, // 189.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));

// 30. טייץ Gym ירוק זית - Gym/Active
productService.addProductToDB(new Product(
    "W-PA-430", "טייץ אימון Olive Seamless", "https://api.placeholder.com/womens-olive-gym",
    "בד: ניילון אלסטי מחטב. ללא תפרים, תמיכה גבוהה לפעילות ספורטיבית אינטנסיבית.",
    1699, // 169.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק זית", "Slim-Fit", "סאטן", "Gym", "Spring-Essentials", "חלק")
));

// 31. מכנסיים מחויטים משובצים - Office/Classic
productService.addProductToDB(new Product(
    "W-PA-431", "מכנסי סיגר משובצים Grey Check", "https://api.placeholder.com/womens-grey-plaid",
    "בד: תערובת כותנה. דוגמת משבצות עדינה באפור, גזרת Slim-Fit מקצועית למשרד.",
    2599, // 259.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "100% כותנה", "Office", "Classic", "Winter", "משובץ")
));

// 32. שורטס פרחוניים קלילים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-432", "שורטס ויסקוזה Floral", "https://api.placeholder.com/womens-floral-shorts",
    "בד: ויסקוזה נעימה. הדפס פרחים צבעוני, גזרה גבוהה עם שרוך קשירה במותן.",
    1299, // 129.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "לבן", "Relaxed-Fit", "100% כותנה", "Vacation", "Summer", "פרחוני")
));

// 33. מכנסי דגמ"ח כחול נייבי - Streetwear/Casual
productService.addProductToDB(new Product(
    "W-PA-433", "מכנסי קרגו Navy Urban", "https://api.placeholder.com/womens-navy-cargo",
    "בד: 100% כותנת טוויל עמידה. כיסי צד גדולים וגזרת Oversize מודרנית.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "כחול נייבי", "Oversize", "100% כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 34. מכנסי צמר מרינו אפורים - Office/Winter
productService.addProductToDB(new Product(
    "W-PA-434", "מכנסי צמר מרינו Tailored", "https://api.placeholder.com/womens-grey-wool",
    "בד: 100% צמר מרינו דק. גזרה ישרה עם כפל קדמי, מראה אלגנטי ומחמם לחורף.",
    3599, // 359.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Elegant", "Winter", "חלק")
));

// 35. מכנסי סאטן ירוק זית - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-PA-435", "מכנסי סאטן Olive Glow", "https://api.placeholder.com/womens-olive-satin",
    "בד: סאטן רך. גזרה רחבה ונשפכת, מראה יוקרתי ליציאות ערב.",
    2799, // 279.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק זית", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 36. מכנסי צמר מרינו מחויטים - Office/Winter
productService.addProductToDB(new Product(
    "W-PA-436", "מכנסי צמר מרינו Grey Office", "https://api.placeholder.com/womens-grey-wool",
    "בד: 100% צמר מרינו. גזרה ישרה עם כפל קדמי, פריט קלאסי ומחמם למשרד.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Classic", "Winter", "חלק")
));

// 37. ג'ינס Mom-Fit כחול - Casual/Autumn
productService.addProductToDB(new Product(
    "W-PA-437", "ג'ינס Mom-Fit Classic", "https://api.placeholder.com/womens-blue-mom",
    "בד: דנים 100% כותנה. גזרה גבוהה ונוחה במיוחד ליומיום.",
    2199, // 219.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Regular-Fit", "דנים", "Casual", "Autumn", "חלק")
));

// 38. מכנסי פשתן בז' - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-438", "מכנסי פשתן Summer Breeze", "https://api.placeholder.com/womens-beige-linen",
    "בד: 100% פשתן אוורירי. גזרה רחבה (Wide-Leg), הכי נוח לחופשה בקיץ.",
    1899, // 189.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "100% פשתן", "Vacation", "Summer", "חלק")
));

// 39. שורטס משובצים - Casual/Summer
productService.addProductToDB(new Product(
    "W-PA-439", "שורטס משובצים Vichy", "https://api.placeholder.com/womens-plaid-shorts",
    "בד: 100% כותנה. דוגמת משבצות שחור-לבן קלאסית, גזרה מחמיאה.",
    1299, // 129.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "שחור", "Regular-Fit", "100% כותנה", "Casual", "Summer", "משובץ")
));

// 40. מכנסי דגמ"ח לבנים - Streetwear/Spring
productService.addProductToDB(new Product(
    "W-PA-440", "מכנסי קרגו White Utility", "https://api.placeholder.com/womens-white-cargo",
    "בד: 100% כותנה. גזרת Oversize עם כיסי צד, מראה סטריטוור נקי.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "לבן", "Oversize", "100% כותנה", "Streetwear", "Spring-Essentials", "חלק")
));

// 41. טייץ Gym שחור - Gym/Active
productService.addProductToDB(new Product(
    "W-PA-441", "טייץ אימון High-Waist", "https://api.placeholder.com/womens-gym-black",
    "בד: סאטן אלסטי ומחטב. גזרה צמודה (Skinny) המיועדת לפעילות ספורטיבית.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "סאטן", "Gym", "All-Season", "חלק")
));

// 42. מכנסיים פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-442", "מכנסי ויסקוזה Floral", "https://api.placeholder.com/womens-floral-pants",
    "בד: 100% כותנה (ויסקוזה). הדפס פרחים צבעוני על רקע כחול נייבי, קליל ונשי.",
    2299, // 229.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Wide-Leg", "100% כותנה", "Vacation", "Summer", "פרחוני")
));

// 43. מכנסי סאטן יוקרתיים - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-PA-443", "מכנסי סאטן Ivory Wide", "https://api.placeholder.com/womens-satin-wedding",
    "מכנסיים נשפכים ויוקרתיים. בד: סאטן מבריק. גזרה רחבה מאוד לאירועים.",
    3299, // 329.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Wide-Leg", "סאטן", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 44. ג'ינס רחב - Streetwear/Oversize
productService.addProductToDB(new Product(
    "W-PA-444", "ג'ינס Wide-Leg Street", "https://api.placeholder.com/womens-wide-denim",
    "גזרה גבוהה ורחבה. בד: דנים 100% כותנה. מראה סטריטוור עדכני.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Wide-Leg", "דנים", "Streetwear", "Oversize", "Autumn", "חלק")
));

// 45. מכנסי פשתן שחורים - Vacation/Minimalist
productService.addProductToDB(new Product(
    "W-PA-445", "מכנסי פשתן Minimal Black", "https://api.placeholder.com/womens-black-linen",
    "מכנסיים קלילים ואלגנטיים. בד: 100% פשתן. גזרה ישרה ונקייה.",
    1999, // 199.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "100% פשתן", "Vacation", "Minimalist", "Summer", "חלק")
));

// 46. טייץ אימון - Gym/Active
productService.addProductToDB(new Product(
    "W-PA-446", "טייץ Gym Performance", "https://api.placeholder.com/womens-gym-leggings",
    "טייץ מחטב לאימון. בד: סאטן אלסטי (ניילון). גזרה צמודה מנדפת זיעה.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "סאטן", "Gym", "All-Season", "חלק")
));

// 47. מכנסיים מחויטים - Office/Classic
productService.addProductToDB(new Product(
    "W-PA-447", "מכנסי סיגר Navy Office", "https://api.placeholder.com/womens-navy-office",
    "מכנסיים מקצועיים למשרד. בד: 100% כותנה. גזרה ישרה ומחויטת.",
    2699, // 269.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול נייבי", "Regular-Fit", "100% כותנה", "Office", "Classic", "Winter", "חלק")
));

// 48. שורטס פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-448", "שורטס פרחוניים Summer Bloom", "https://api.placeholder.com/womens-floral-shorts",
    "מכנסיים קצרים וקלילים. בד: 100% כותנה. הדפס פרחים צבעוני.",
    1199, // 119.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "בז'", "Regular-Fit", "100% כותנה", "Vacation", "Summer", "פרחוני")
));

// 49. מכנסי קרגו (מראה דגמ"ח) - Streetwear/Casual
productService.addProductToDB(new Product(
    "W-PA-449", "מכנסי קרגו Urban Beige", "https://api.placeholder.com/womens-cargo-beige",
    "מכנסיים עם כיסים. בד: 100% כותנה. מראה קז'ואל משוחרר.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "בז'", "Oversize", "100% כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 50. מכנסי צמר מרינו - Elegant/Winter
productService.addProductToDB(new Product(
    "W-PA-450", "מכנסי צמר מרינו Grey Luxury", "https://api.placeholder.com/womens-wool-winter",
    "מכנסיים חמים ויוקרתיים. בד: 100% צמר מרינו. גזרה מחויטת לחורף.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Elegant", "Classic", "Winter", "חלק")
));
}

private void seedShoes() 
{
    // 1. נעלי אוקספורד - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SH-501", "נעלי Oxford עור יוקרתיות", "https://api.placeholder.com/mens-oxford",
    "נעלי ערב קלאסיות מעור איכותי. גימור מבריק וסוליה עמידה, מושלם לחתונה.",
    4499, // 449.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Wedding", "Winter", "חלק")
));

// 2. סניקרס לבנות - Minimalist/Casual
productService.addProductToDB(new Product(
    "M-SH-502", "סניקרס Clean White", "https://api.placeholder.com/mens-sneakers",
    "סניקרס בעיצוב מינימליסטי ונקי. מתאימות לכל אאוטפיט, מיום עבודה ועד יציאה.",
    3299, // 329.90 ₪
    Arrays.asList("גברים", "נעליים", "לבן", "Minimalist", "Casual", "Spring-Essentials", "חלק")
));

// 3. נעלי ריצה - Gym
productService.addProductToDB(new Product(
    "M-SH-503", "נעלי ריצה Performance", "https://api.placeholder.com/mens-running",
    "סוליית שיכוך מתקדמת ובד נושם. תמיכה מקסימלית לכף הרגל בזמן אימון.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 4. לואפרס (Loafers) - Office/Elegant
productService.addProductToDB(new Product(
    "M-SH-504", "נעלי לואפרס זמש", "https://api.placeholder.com/mens-loafers",
    "נעלי סליפ-און במראה אירופאי. נוחות מאוד ומתאימות למשרד או לאירועי צהריים.",
    3899, // 389.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Elegant", "Office", "Spring-Essentials", "חלק")
));

// 5. מגפי צ'לסי - Classic/Winter
productService.addProductToDB(new Product(
    "M-SH-505", "מגפי צ'לסי עור", "https://api.placeholder.com/mens-chelsea",
    "מגפיים קלאסיים עם גומי בצדדים. הגנה מצוינת מגשם ומראה מחוספס אך אלגנטי.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Classic", "Winter", "Autumn", "חלק")
));

// 6. סניקרס High-Top - Streetwear
productService.addProductToDB(new Product(
    "M-SH-506", "סניקרס גבוהות Retro", "https://api.placeholder.com/mens-hightop",
    "מראה סטריטוור נוסטלגי. שילוב צבעים של כחול ולבן, מתאים לג'ינס רחב.",
    3999, // 399.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול נייבי", "Streetwear", "Night-Out", "All-Season", "חלק")
));

// 7. נעלי סירה (Boat Shoes) - Vacation/Summer
productService.addProductToDB(new Product(
    "M-SH-507", "נעלי סירה קיציות", "https://api.placeholder.com/mens-boat-shoes",
    "נעלי בד קלילות עם סוליית גומי לבנה. מושלם לחופשות בקיץ או לים.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול נייבי", "Classic", "Vacation", "Summer", "חלק")
));

// 8. נעלי צ'אקה (Chukka) - Casual/Autumn
productService.addProductToDB(new Product(
    "M-SH-508", "מגפי צ'אקה זמש אפור", "https://api.placeholder.com/mens-chukka",
    "מגפיים נמוכים עם שרוכים. מראה יומיומי משודרג שמתאים לעונות המעבר.",
    3699, // 369.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Minimalist", "Casual", "Autumn", "חלק")
));

// 9. נעלי ספורט מעוצבות - Night-Out
productService.addProductToDB(new Product(
    "M-SH-509", "סניקרס עור שחורות", "https://api.placeholder.com/mens-black-sneakers",
    "סניקרס אלגנטיות בצע שחור מט. מתאימות ליציאה בערב עם מכנסי צ'ינו.",
    3499, // 349.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Night-Out", "All-Season", "חלק")
));

// 10. סנדלי עור - Vacation/Summer
productService.addProductToDB(new Product(
    "M-SH-510", "סנדלי עור רצועות", "https://api.placeholder.com/mens-sandals",
    "סנדלים איכותיים ונוחים. מראה טבעי שמתאים לחופשות ארוכות ומזג אוויר חם.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Minimalist", "Vacation", "Summer", "חלק")
));

// 11. נעלי מוקסין עור - Office/Elegant
productService.addProductToDB(new Product(
    "M-SH-511", "נעלי מוקסין Classic Navy", "https://api.placeholder.com/mens-loafers-navy",
    "נעליים אלגנטיות ללא שרוכים. מתאימות במיוחד ליום עבודה במשרד או לאירוע חגיגי.",
    3999, // 399.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול נייבי", "Elegant", "Office", "Spring-Essentials", "חלק")
));

// 12. סניקרס בד - Casual/Minimalist
productService.addProductToDB(new Product(
    "M-SH-512", "סניקרס בד Minimalist White", "https://api.placeholder.com/mens-canvas-white",
    "נעלי בד קלילות ונושמות. עיצוב נקי שמשתלב מעולה עם ג'ינס או מכנסי צ'ינו.",
    2799, // 279.90 ₪
    Arrays.asList("גברים", "נעליים", "לבן", "Minimalist", "Casual", "Summer", "חלק")
));

// 13. נעלי ריצה מקצועיות - Gym
productService.addProductToDB(new Product(
    "M-SH-513", "נעלי ריצה Grey Performance", "https://api.placeholder.com/mens-runners-grey",
    "נעלי ספורט עם שיכוך זעזועים מתקדם. תמיכה מלאה לאימונים אינטנסיביים במכון.",
    4999, // 499.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 14. מגפיים חומים - Classic/Winter
productService.addProductToDB(new Product(
    "M-SH-514", "מגפי עור Autumn Beige", "https://api.placeholder.com/mens-boots-beige",
    "מגפיים עמידים למזג אוויר קר. מראה קלאסי ומחוספס שמתאים לעונת החורף.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Classic", "Winter", "Autumn", "חלק")
));

// 15. סניקרס שחורות - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-SH-515", "סניקרס עור Black Street", "https://api.placeholder.com/mens-sneakers-black",
    "סניקרס אלגנטיות בצע שחור. מתאימות ליציאה בערב או ללוק סטריטוור מודרני.",
    3599, // 359.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Streetwear", "Night-Out", "All-Season", "חלק")
));

// 16. סנדלי חוף - Vacation/Summer
productService.addProductToDB(new Product(
    "M-SH-516", "סנדלי Vacation Olive", "https://api.placeholder.com/mens-sandals-olive",
    "סנדלים נוחים ועמידים למים. מושלם לחופשות בקיץ או לטיולים בטבע.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "נעליים", "ירוק זית", "Minimalist", "Vacation", "Summer", "חלק")
));

// 17. נעלי ערב - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SH-517", "נעלי עור Formal Black", "https://api.placeholder.com/mens-formal-black",
    "נעלי אוקספורד יוקרתיות. הכי אלגנטי שיש לחתונה או לאירוע רשמי מאוד.",
    4799, // 479.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Wedding", "Winter", "חלק")
));

// 18. סניקרס גבוהות - Streetwear/Oversize
productService.addProductToDB(new Product(
    "M-SH-518", "סניקרס High-Top Retro", "https://api.placeholder.com/mens-hightop-navy",
    "נעליים במראה רטרו עם תמיכה בקרסול. משתלבות בול עם מכנסי קרגו וגזרות רחבות.",
    4299, // 429.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול נייבי", "Streetwear", "Oversize", "Autumn", "חלק")
));

// 19. נעלי קז'ואל - Casual/Spring-Essentials
productService.addProductToDB(new Product(
    "M-SH-519", "נעלי Canvas Grey", "https://api.placeholder.com/mens-casual-grey",
    "נעליים יומיומיות נוחות. עיצוב פשוט ונקי שמתאים לכל יום בעונת המעבר.",
    3199, // 319.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Minimalist", "Casual", "Spring-Essentials", "חלק")
));

// 20. נעלי עבודה מעוצבות - Office/Classic
productService.addProductToDB(new Product(
    "M-SH-520", "נעלי עור Derby Beige", "https://api.placeholder.com/mens-derby-beige",
    "נעליים חצי-רשמיות. מראה קלאסי שמתאים לפגישות עסקיות או ליום עבודה משרדי.",
    4199, // 419.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Classic", "Office", "Spring-Essentials", "חלק")
));

// 21. נעלי ספורט קלות - Gym/Summer
productService.addProductToDB(new Product(
    "M-SH-521", "נעלי אימון White Air", "https://api.placeholder.com/mens-gym-white",
    "נעליים קלות משקל עם אוורור מקסימלי. אידיאליות לאימון בקיץ.",
    4599, // 459.90 ₪
    Arrays.asList("גברים", "נעליים", "לבן", "Streetwear", "Gym", "Summer", "חלק")
));

// 22. מגפי צ'לסי - Night-Out/Elegant
productService.addProductToDB(new Product(
    "M-SH-522", "מגפי צ'לסי Black Suede", "https://api.placeholder.com/mens-chelsea-black",
    "מגפיים אלגנטיים ללא שרוכים. מראה יוקרתי ומתוחכם ליציאה בערב.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Night-Out", "Winter", "חלק")
));

// 23. נעלי שייט - Vacation/Classic
productService.addProductToDB(new Product(
    "M-SH-523", "נעלי שייט Navy Classic", "https://api.placeholder.com/mens-boat-navy",
    "נעליים קלאסיות לחופשות. סוליית גומי לבנה למניעת החלקה ומראה Vacation מושלם.",
    3399, // 339.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול נייבי", "Classic", "Vacation", "Summer", "חלק")
));

// 24. סניקרס בעיצוב נקי - Minimalist/Office
productService.addProductToDB(new Product(
    "M-SH-524", "סניקרס Minimalist Olive", "https://api.placeholder.com/mens-minimal-olive",
    "סניקרס בצבע זית עמוק. שילוב בין נוחות של נעלי ספורט למראה שמתאים למשרד.",
    3699, // 369.90 ₪
    Arrays.asList("גברים", "נעליים", "ירוק זית", "Minimalist", "Office", "Autumn", "חלק")
));

// 25. נעלי חורף עמידות - Winter/Classic
productService.addProductToDB(new Product(
    "M-SH-525", "מגפי עור Heavy Winter", "https://api.placeholder.com/mens-winter-boots",
    "מגפיים חזקים ועמידים למים. תמיכה מקסימלית והגנה מהקור בימי החורף הקשים.",
    5899, // 589.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Classic", "Winter", "חלק")
));

// 1. נעלי עקב - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-SH-601", "נעלי עקב Stiletto", "https://api.placeholder.com/womens-heels",
    "נעלי עקב קלאסיות ויוקרתיות. מראה נשי ואלגנטי שחובה לכל חתונה או אירוע ערב.",
    4299, // 429.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Elegant", "Wedding", "All-Season", "חלק")
));

// 2. סניקרס פלטפורמה - Streetwear
productService.addProductToDB(new Product(
    "W-SH-602", "סניקרס פלטפורמה לבנות", "https://api.placeholder.com/womens-platform",
    "סניקרס עם סוליה עבה וטרנדית. מוסיפות גובה וסטייל לכל לוק יומיומי.",
    3599, // 359.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Streetwear", "Casual", "Spring-Essentials", "חלק")
));

// 3. נעלי בובה (Flats) - Office/Minimalist
productService.addProductToDB(new Product(
    "W-SH-603", "נעלי בובה קלאסיות", "https://api.placeholder.com/womens-flats",
    "נעליים שטוחות ונוחות במיוחד. עיצוב נקי שמתאים ליום עבודה ארוך במשרד.",
    2299, // 229.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Minimalist", "Office", "Spring-Essentials", "חלק")
));

// 4. מגפיים גבוהים - Winter/Classic
productService.addProductToDB(new Product(
    "W-SH-604", "מגפי עור גבוהים", "https://api.placeholder.com/womens-boots",
    "מגפיים שמגיעים עד הברך. מחממים מאוד ומשדרגים כל הופעה חורפית.",
    5999, // 599.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Classic", "Winter", "חלק")
));

// 5. סנדלי עקב - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SH-605", "סנדלי עקב רצועות דקות", "https://api.placeholder.com/womens-strappy-heels",
    "סנדלים עדינים ליציאה בלילה. מראה זוהר שמתאים לשמלות ומכנסי סאטן.",
    3899, // 389.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Elegant", "Night-Out", "Summer", "חלק")
));

// 6. נעלי אימון - Gym
productService.addProductToDB(new Product(
    "W-SH-606", "נעלי אימון קלות", "https://api.placeholder.com/womens-running",
    "נעליים גמישות עם בלימת זעזועים. מושלמות לשיעורי סטודיו או ריצה במכון.",
    4599, // 459.90 ₪
    Arrays.asList("נשים", "נעליים", "אפור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 7. כפכפי פלטפורמה - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SH-607", "כפכפי נוחות לקיץ", "https://api.placeholder.com/womens-slides",
    "כפכפים מעוצבים עם סוליה אנטומית. אידיאליים לחופשה, לים או לטיול בעיר.",
    1899, // 189.90 ₪
    Arrays.asList("נשים", "נעליים", "ירוק זית", "Minimalist", "Vacation", "Summer", "חלק")
));

// 8. מגפוני קרסול (Ankle Boots) - Autumn
productService.addProductToDB(new Product(
    "W-SH-608", "מגפוני זמש חומים", "https://api.placeholder.com/womens-ankle-boots",
    "מגפונים עם עקב קטן ונוח. משתלבים מעולה עם ג'ינס או חצאית בעונת הסתיו.",
    4199, // 419.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Classic", "Autumn", "חלק")
));

// 9. סניקרס צבעוניות - Streetwear
productService.addProductToDB(new Product(
    "W-SH-609", "סניקרס רטרו צבעוניות", "https://api.placeholder.com/womens-retro-sneakers",
    "עיצוב נועז עם שילוב צבעים. מוסיפות עניין וסטייל לכל לוק יומיומי משוחרר.",
    3799, // 379.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול נייבי", "Streetwear", "Casual", "All-Season", "חלק")
));

// 10. נעלי מוקסין - Office/Elegant
productService.addProductToDB(new Product(
    "W-SH-610", "נעלי מוקסין עור", "https://api.placeholder.com/womens-moccasins",
    "מראה מקצועי ומתוחכם. נעליים נוחות שמתאימות במיוחד לפגישות עסקיות.",
    3399, // 339.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Elegant", "Office", "Winter", "חלק")
));

// 11. נעלי עקב לבנות - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-SH-611", "נעלי עקב Ivory Pearl", "https://api.placeholder.com/womens-heels-white",
    "נעלי עקב יוקרתיות בגימור פנינה. הבחירה המושלמת לכלה או לאירוע ערב אלגנטי.",
    4599, // 459.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Elegant", "Wedding", "Spring-Essentials", "חלק")
));

// 12. סניקרס יומיומיות - Casual/Minimalist
productService.addProductToDB(new Product(
    "W-SH-612", "סניקרס Clean Beige", "https://api.placeholder.com/womens-sneakers-beige",
    "נעליים נוחות לכל יום. עיצוב מינימליסטי בצבע בז' שמתאים לכל אאוטפיט.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Minimalist", "Casual", "Autumn", "חלק")
));

// 13. נעלי אימון - Gym/Streetwear
productService.addProductToDB(new Product(
    "W-SH-613", "נעלי אימון Black Performance", "https://api.placeholder.com/womens-gym-black",
    "נעלי ספורט גמישות ומאווררות. מתאימות לאימוני כושר או ללוק סטריטוור יומיומי.",
    4299, // 429.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 14. כפכפי נוחות - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SH-614", "כפכפי Vacation White", "https://api.placeholder.com/womens-slides-white",
    "כפכפים מעוצבים וקלילים. אידיאליים לים, לבריכה או לחופשת הקיץ שלך.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Minimalist", "Vacation", "Summer", "חלק")
));

// 15. מגפיים גבוהים - Winter/Classic
productService.addProductToDB(new Product(
    "W-SH-615", "מגפי עור גבוהים Navy", "https://api.placeholder.com/womens-boots-navy",
    "מגפיים שמגיעים עד הברך. מחממים מאוד ומשדרגים כל הופעה חורפית אלגנטית.",
    5799, // 579.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול נייבי", "Classic", "Winter", "חלק")
));

// 16. נעלי יציאה - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SH-616", "סנדלי עקב Black Night", "https://api.placeholder.com/womens-sandals-black",
    "סנדלים עדינים עם עקב דק. מראה זוהר ומרשים שמתאים ליציאה בערב.",
    3899, // 389.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Elegant", "Night-Out", "Summer", "חלק")
));

// 17. סניקרס פלטפורמה - Streetwear/Oversize
productService.addProductToDB(new Product(
    "W-SH-617", "סניקרס פלטפורמה Grey", "https://api.placeholder.com/womens-platform-grey",
    "סניקרס טרנדיות עם סוליה עבה. מוסיפות סטייל וגובה ללוק אורבני משוחרר.",
    3699, // 369.90 ₪
    Arrays.asList("נשים", "נעליים", "אפור", "Streetwear", "Oversize", "Spring-Essentials", "חלק")
));

// 18. נעלי בובה - Office/Minimalist
productService.addProductToDB(new Product(
    "W-SH-618", "נעלי בובה Classic Black", "https://api.placeholder.com/womens-flats-black",
    "נעליים שטוחות ונוחות. פתרון מעולה ליום עבודה ארוך במשרד שדורש מראה נקי.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Minimalist", "Office", "All-Season", "חלק")
));

// 19. מגפוני קרסול - Autumn/Classic
productService.addProductToDB(new Product(
    "W-SH-619", "מגפוני זמש Beige Autumn", "https://api.placeholder.com/womens-ankle-beige",
    "מגפונים עם עקב קטן. עיצוב קלאסי שמתאים במיוחד לעונות המעבר ולמזג אוויר קריר.",
    4399, // 439.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Classic", "Autumn", "חלק")
));

// 20. נעלי מוקסין - Office/Elegant
productService.addProductToDB(new Product(
    "W-SH-620", "נעלי מוקסין Navy Patent", "https://api.placeholder.com/womens-moccasin-navy",
    "נעליים מקצועיות בגימור מבריק. מראה מתוחכם ויוקרתי שמתאים לפגישות עסקיות.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול נייבי", "Elegant", "Office", "Winter", "חלק")
));

// 21. סנדלי חוף צבעוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SH-621", "סנדלי Vacation Olive Green", "https://api.placeholder.com/womens-sandals-olive",
    "סנדלים קלילים עם רצועות. צבע ירוק זית מודרני שמתאים לחופשות קיץ מרעננות.",
    1799, // 179.90 ₪
    Arrays.asList("נשים", "נעליים", "ירוק זית", "Minimalist", "Vacation", "Summer", "חלק")
));

// 22. נעלי ספורט לבנות - Gym/Casual
productService.addProductToDB(new Product(
    "W-SH-622", "סניקרס White Sporty", "https://api.placeholder.com/womens-sporty-white",
    "נעליים המשלבות מראה ספורטיבי ונוחות של יומיום. מושלמות להליכה או לאימון קל.",
    3399, // 339.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Streetwear", "Gym", "Spring-Essentials", "חלק")
));

// 23. נעלי ערב - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SH-623", "נעלי עקב Grey Suede", "https://api.placeholder.com/womens-heels-grey",
    "נעלי עקב מזמש יוקרתי. מראה עדין ומלוטש ליציאות ערב חגיגיות.",
    4199, // 419.90 ₪
    Arrays.asList("נשים", "נעליים", "אפור", "Elegant", "Night-Out", "Autumn", "חלק")
));

// 24. מגפיים חסיני מים - Winter/Classic
productService.addProductToDB(new Product(
    "W-SH-624", "מגפי חורף Black Shield", "https://api.placeholder.com/womens-winter-black",
    "מגפיים עמידים ונוחים במיוחד לימים גשומים. שומרים על כף הרגל חמה ויבשה.",
    5299, // 529.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Classic", "Winter", "חלק")
));

// 25. סניקרס מעוצבות - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "W-SH-625", "סניקרס Navy Glam", "https://api.placeholder.com/womens-glam-navy",
    "סניקרס בעיצוב ייחודי עם נגיעות מבריקות. הבחירה המנצחת למי שרוצה סטייל ונוחות בלילה.",
    3999, // 399.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול נייבי", "Streetwear", "Night-Out", "All-Season", "חלק")
));
}

private void seedWinterCoatsAndSuits() 
{
    // 1. מעיל טרנץ' קלאסי - Office/Elegant
productService.addProductToDB(new Product(
    "M-CO-401", "מעיל טרנץ' בריטי", "https://api.placeholder.com/m-trench",
    "מעיל ארוך דוחה מים, בד כותנה גבדין איכותי. מראה מחויט ויוקרתי.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "בז'", "Regular-Fit", "100% כותנה", "Office", "Elegant", "Winter", "חלק")
));

// 2. מעיל פאפר (Puffer) - Streetwear/Winter
productService.addProductToDB(new Product(
    "M-CO-402", "מעיל פאפר נפוח Heavy", "https://api.placeholder.com/m-puffer",
    "מעיל מרופד בבידוד תרמי מקסימלי. בד חיצוני אטום לרוח.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Oversize", "100% כותנה", "Streetwear", "Winter", "חלק")
));

// 3. חליפת שלושה חלקים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SU-501", "חליפת Premium תלת-חלקית", "https://api.placeholder.com/m-suit-3p",
    "סט הכולל ז'קט, וסט ומכנסיים. בד צמר מרינו דק ונושם.",
    8999, // 899.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול נייבי", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "All-Season", "חלק")
));

// 4. מעיל עור (Biker) - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-CO-403", "ג'קט עור Raw Style", "https://api.placeholder.com/m-leather",
    "מעיל עור איכותי עם רוכסנים מוכספים. מראה מחוספס ליציאה.",
    7599, // 759.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Slim-Fit", "דנים", "Night-Out", "Streetwear", "Autumn", "חלק")
));

// 5. חליפת פשתן קלילה - Vacation/Elegant
productService.addProductToDB(new Product(
    "M-SU-502", "חליפת פשתן Cuban-White", "https://api.placeholder.com/m-linen-suit",
    "חליפה לאירועי צהריים או חופשות. בד פשתן אוורירי.",
    5299, // 529.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Elegant", "Summer", "חלק")
));

// --- מעילים לגברים ---

// 6. מעיל פאפר נפוח - Streetwear/Winter
productService.addProductToDB(new Product(
    "M-CO-404", "מעיל פאפר Oversize שחור", "https://api.placeholder.com/m-puffer-black",
    "מעיל חורף נפוח עם בידוד תרמי. בד חיצוני אטום, גזרה רחבה ונוחה.",
    4999, // 499.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Oversize", "100% כותנה", "Streetwear", "Winter", "חלק")
));

// 7. מעיל רוח קל - Casual/Autumn
productService.addProductToDB(new Product(
    "M-CO-405", "מעיל רוח Storm-Shell", "https://api.placeholder.com/m-rain-navy",
    "מעיל דק חסין מים ורוח. עיצוב מינימליסטי שמתאים לעונת המעבר.",
    3299, // 329.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "כחול נייבי", "Minimalist", "100% כותנה", "Casual", "Autumn", "חלק")
));

// 8. מעיל צמר אלגנטי - Elegant/Winter
productService.addProductToDB(new Product(
    "M-CO-406", "מעיל צמר מרינו אפור", "https://api.placeholder.com/m-wool-grey",
    "מעיל ארוך מחויט מצמר מרינו איכותי. מראה יוקרתי ומחמם מאוד.",
    6899, // 689.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "אפור", "Elegant", "צמר מרינו", "Classic", "Winter", "חלק")
));

// 9. ג'קט דנים מרופד - Streetwear/Autumn
productService.addProductToDB(new Product(
    "M-CO-407", "ג'קט ג'ינס עם בטנת פליז", "https://api.placeholder.com/m-denim-winter",
    "ג'קט דנים קלאסי עם ריפוד פנימי מחמם. מראה מחוספס ויומיומי.",
    3899, // 389.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "כחול נייבי", "Regular-Fit", "דנים", "Streetwear", "Autumn", "חלק")
));

// 10. פארקה חורפית - Casual/Winter
productService.addProductToDB(new Product(
    "M-CO-408", "מעיל פארקה Arctic", "https://api.placeholder.com/m-parka-olive",
    "מעיל חורף ארוך עם כובע פרווה (סינתטית). בד עמיד במיוחד.",
    5999, // 599.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "ירוק זית", "Oversize", "100% כותנה", "Casual", "Winter", "חלק")
));

// 11. מעיל עור ליציאה - Night-Out/Elegant
productService.addProductToDB(new Product(
    "M-CO-409", "ג'קט עור Slim-Fit", "https://api.placeholder.com/m-leather-black",
    "ג'קט עור יוקרתי בגזרה צמודה. מתאים להופעה מרשימה בלילה.",
    7999, // 799.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Slim-Fit", "דנים", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 12. מעיל בומבר (Bomber) - Streetwear/Spring
productService.addProductToDB(new Product(
    "M-CO-410", "ג'קט בומבר קלאסי", "https://api.placeholder.com/m-bomber-olive",
    "ג'קט קל עם מנג'טים בצווארון ובשרוולים. מראה צבאי-אורבני.",
    3599, // 359.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "ירוק זית", "Regular-Fit", "100% כותנה", "Streetwear", "Spring-Essentials", "חלק")
));

// 13. מעיל גשם מינימליסטי - Minimalist/Autumn
productService.addProductToDB(new Product(
    "M-CO-411", "מעיל גשם Clean Cut", "https://api.placeholder.com/m-rain-beige",
    "עיצוב נקי ופשוט בצבע בז'. דוחה מים וקל משקל.",
    3199, // 319.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "בז'", "Minimalist", "100% כותנה", "Casual", "Autumn", "חלק")
));

// 14. חליפת טוקסידו - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SU-503", "חליפת טוקסידו Black-Tie", "https://api.placeholder.com/m-tuxedo",
    "חליפת ערב רשמית עם דש סאטן. הכי אלגנטי שיש לחתונה.",
    9499, // 949.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "שחור", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 15. חליפת עסקים אפורה - Office/Elegant
productService.addProductToDB(new Product(
    "M-SU-504", "חליפת צמר מרינו אפורה", "https://api.placeholder.com/m-suit-grey",
    "חליפה מחויטת למשרד. בד צמר דק שמתאים לכל עונות השנה.",
    7299, // 729.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Elegant", "All-Season", "חלק")
));

// 16. חליפה משובצת - Wedding/Classic
productService.addProductToDB(new Product(
    "M-SU-505", "חליפת משבצות Glen Plaid", "https://api.placeholder.com/m-suit-check",
    "מראה קלאסי ומתוחכם עם דוגמת משבצות עדינה.",
    8199, // 819.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "אפור", "Classic", "100% כותנה", "Wedding", "Classic", "Autumn", "משובץ")
));

// 17. חליפת פשתן כחולה - Vacation/Elegant
productService.addProductToDB(new Product(
    "M-SU-506", "חליפת פשתן Navy Vacation", "https://api.placeholder.com/m-suit-linen",
    "חליפה קלילה לאירועי קיץ או חופשות יוקרתיות.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול נייבי", "Regular-Fit", "100% פשתן", "Vacation", "Elegant", "Summer", "חלק")
));

// 18. חליפת Slim-Fit מודרנית - Night-Out/Elegant
productService.addProductToDB(new Product(
    "M-SU-507", "חליפת Midnight Blue", "https://api.placeholder.com/m-suit-night",
    "חליפה צמודה ומחמיאה ליציאות ערב חגיגיות.",
    6899, // 689.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול נייבי", "Slim-Fit", "100% כותנה", "Night-Out", "Elegant", "Spring-Essentials", "חלק")
));

// 19. חליפה מינימליסטית לבנה - Wedding/Summer
productService.addProductToDB(new Product(
    "M-SU-508", "חליפת קיץ לבנה", "https://api.placeholder.com/m-suit-white",
    "חליפה למראה נקי ומרשים, מתאימה במיוחד לחתונות חוף.",
    5799, // 579.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Wedding", "Minimalist", "Summer", "חלק")
));

// 20. חליפת קורדרוי חורפית - Classic/Winter
productService.addProductToDB(new Product(
    "M-SU-509", "חליפת קורדרוי חומה", "https://api.placeholder.com/m-suit-cord",
    "בד עבה ומחמם במראה רטרו קלאסי.",
    6299, // 629.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "בז'", "Classic", "100% כותנה", "Classic", "Winter", "חלק")
));

// 21. מעיל צמר יוקרתי - Elegant/Winter
productService.addProductToDB(new Product(
    "M-CO-412", "מעיל צמר מרינו Double-Breasted", "https://api.placeholder.com/m-wool-black",
    "מעיל רציני ומחמם עם כפתרה כפולה. בד: 100% צמר מרינו. גזרה מחויטת ויוקרתית.",
    7499, // 749.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Regular-Fit", "צמר מרינו", "Elegant", "Winter", "חלק")
));

// 22. חליפת חתונה כחולה - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SU-510", "חליפת Navy Royal Wedding", "https://api.placeholder.com/m-suit-royal",
    "חליפה מרשימה בצבע כחול עמוק. בד: צמר מרינו דק. גזרת Slim-Fit מחמיאה במיוחד.",
    8599, // 859.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול נייבי", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 23. ג'קט רוח אורבני - Streetwear/Autumn
productService.addProductToDB(new Product(
    "M-CO-413", "מעיל רוח Street-Utility", "https://api.placeholder.com/m-wind-olive",
    "מעיל קל ונוח לימים קרירים. בד: 100% כותנה בטכנולוגיית דחיית מים. מראה סטריטוור מודרני.",
    3799, // 379.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "ירוק זית", "Oversize", "100% כותנה", "Streetwear", "Autumn", "חלק")
));

// 24. חליפת משרד אפורה - Office/Classic
productService.addProductToDB(new Product(
    "M-SU-511", "חליפת צ'ק Classic Office", "https://api.placeholder.com/m-suit-grey-check",
    "חליפה מקצועית עם דוגמת משבצות עדינה. בד: 100% כותנה עמידה.",
    6999, // 699.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "אפור", "Regular-Fit", "100% כותנה", "Office", "Classic", "Autumn", "משובץ")
));

// 25. מעיל אימון קל - Gym/Spring
productService.addProductToDB(new Product(
    "M-CO-414", "מעיל Performance Gym Jacket", "https://api.placeholder.com/m-gym-black",
    "ג'קט ספורטיבי קליל וגמיש. בד מנדף זיעה, מושלם לאימון בחוץ בעונת המעבר.",
    2899, // 289.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Slim-Fit", "100% כותנה", "Gym", "Spring-Essentials", "חלק")
));

// 1. מעיל צמר ארוך - Elegant/Winter
productService.addProductToDB(new Product(
    "W-CO-601", "מעיל צמר מרינו מלטון", "https://api.placeholder.com/w-wool-coat",
    "מעיל ארוך ומחמם עם חגורת מותן. מראה יוקרתי ונצחי.",
    7299, // 729.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Elegant", "Office", "Winter", "חלק")
));

// 2. חליפת בלייזר ומכנסיים - Office/Minimalist
productService.addProductToDB(new Product(
    "W-SU-701", "חליפת Power Suit נשית", "https://api.placeholder.com/w-suit-office",
    "סט בלייזר מחויט ומכנסי סיגר. בד כותנה עמיד.",
    6199, // 619.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "כחול נייבי", "Regular-Fit", "100% כותנה", "Office", "Minimalist", "Autumn", "חלק")
));

// 3. מעיל רוח קרופ (Crop) - Gym/Streetwear
productService.addProductToDB(new Product(
    "W-CO-602", "מעיל רוח Crop Performance", "https://api.placeholder.com/w-windbreaker",
    "מעיל קל משקל לאימונים או יומיום. בד מנדף זיעה.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "ירוק זית", "Slim-Fit", "סאטן", "Gym", "Streetwear", "Spring-Essentials", "חלק")
));

// 4. חליפת סאטן לאירועים - Wedding/Night-Out
productService.addProductToDB(new Product(
    "W-SU-702", "חליפת סאטן Champagne", "https://api.placeholder.com/w-satin-suit",
    "סט בלייזר ומכנסיים רחבים מבד סאטן מבריק.",
    6899, // 689.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "בז'", "Wide-Leg", "סאטן", "Wedding", "Night-Out", "Spring-Essentials", "חלק")
));

// 5. מעיל פרווה מלאכותית - Winter/Night-Out
productService.addProductToDB(new Product(
    "W-CO-603", "מעיל Faux-Fur יוקרתי", "https://api.placeholder.com/w-fur",
    "מעיל פרווה סינתטית רך ומחמם מאוד ליציאות בערב.",
    5999, // 599.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "שחור", "Oversize", "צמר מרינו", "Night-Out", "Winter", "חלק")
));

// --- מעילים לנשים ---

// 6. מעיל טרנץ' קלאסי - Office/Minimalist
productService.addProductToDB(new Product(
    "W-CO-604", "מעיל טרנץ' Beige Classic", "https://api.placeholder.com/w-trench-beige",
    "מעיל ארוך עם חגורה, דוחה מים. פריט חובה לעונת המעבר.",
    5299, // 529.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "בז'", "Regular-Fit", "100% כותנה", "Office", "Minimalist", "Spring-Essentials", "חלק")
));

// 7. מעיל פאפר קצר - Streetwear/Winter
productService.addProductToDB(new Product(
    "W-CO-605", "מעיל פאפר Crop שחור", "https://api.placeholder.com/w-puffer-crop",
    "גזרה קצרה ומודרנית, מחמם מאוד עם מילוי תרמי.",
    4499, // 449.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "שחור", "Oversize", "100% כותנה", "Streetwear", "Winter", "חלק")
));

// 8. מעיל צמר יוקרתי - Elegant/Winter
productService.addProductToDB(new Product(
    "W-CO-606", "מעיל צמר מרינו ארוך", "https://api.placeholder.com/w-wool-long",
    "מעיל נשפך ומחמם עם צווארון רחב. מראה אלגנטי ומרשים.",
    7599, // 759.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Elegant", "צמר מרינו", "Classic", "Winter", "חלק")
));

// 9. מעיל פרווה מלאכותית - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-CO-607", "מעיל Faux-Fur יוקרתי", "https://api.placeholder.com/w-fur-white",
    "מעיל פרווה סינתטית בלבן בוהק. מושלם ליציאות ערב בחורף.",
    6299, // 629.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "לבן", "Oversize", "צמר מרינו", "Night-Out", "Elegant", "Winter", "חלק")
));

// 10. מעיל רוח ספורטיבי - Gym/Spring
productService.addProductToDB(new Product(
    "W-CO-608", "ג'קט רוח Performance", "https://api.placeholder.com/w-rain-olive",
    "מעיל קל משקל לאימונים בחוץ או ליומיום.",
    3399, // 339.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "ירוק זית", "Slim-Fit", "סאטן", "Gym", "Spring-Essentials", "חלק")
));

// 11. בלייזר ארוך (Oversize) - Streetwear/Minimalist
productService.addProductToDB(new Product(
    "W-CO-609", "בלייזר Oversize אפור", "https://api.placeholder.com/w-blazer-grey",
    "ז'קט רחב במראה מודרני. יכול לשמש כמעיל קל או כחלק מחליפה.",
    4199, // 419.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Oversize", "100% כותנה", "Streetwear", "Minimalist", "Autumn", "חלק")
));

// 12. מעיל דנים מעוצב - Casual/Autumn
productService.addProductToDB(new Product(
    "W-CO-610", "ג'קט ג'ינס High-End", "https://api.placeholder.com/w-denim-blue",
    "ג'קט דנים כחול עם שטיפה ייחודית וגזרה מחמיאה.",
    3699, // 369.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "כחול נייבי", "Regular-Fit", "דנים", "Casual", "Autumn", "חלק")
));

// 13. מעיל גשם צהוב/בז' - Casual/Autumn
productService.addProductToDB(new Product(
    "W-CO-611", "מעיל גשם Urban Rain", "https://api.placeholder.com/w-rain-beige",
    "מעיל פרקטי ואופנתי לימים גשומים בעיר.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "בז'", "Regular-Fit", "100% כותנה", "Casual", "Autumn", "חלק")
));

// --- חליפות לנשים ---

// 14. חליפת סאטן לערב - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SU-703", "חליפת סאטן Midnight", "https://api.placeholder.com/w-satin-suit",
    "סט בלייזר ומכנסיים נשפכים מבד סאטן מבריק.",
    6999, // 699.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "כחול נייבי", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 15. חליפת פשתן קיצית - Vacation/Minimalist
productService.addProductToDB(new Product(
    "W-SU-704", "חליפת פשתן White Sands", "https://api.placeholder.com/w-linen-suit",
    "חליפה לבנה ונושמת, מושלמת לחופשות או לאירועי צהריים.",
    5899, // 589.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "לבן", "Wide-Leg", "100% פשתן", "Vacation", "Minimalist", "Summer", "חלק")
));

// 16. חליפה מחויטת למשרד - Office/Elegant
productService.addProductToDB(new Product(
    "W-SU-705", "חליפת Power Suit אפורה", "https://api.placeholder.com/w-office-suit",
    "בלייזר ומכנסי סיגר תואמים מצמר מרינו דק.",
    7499, // 749.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Elegant", "Autumn", "חלק")
));

// 17. חליפת חתונה לבנה - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-SU-706", "חליפת כלה מחויטת", "https://api.placeholder.com/w-bridal-suit",
    "חליפה לבנה יוקרתית ומרשימה, אלטרנטיבה מודרנית לשמלה.",
    8999, // 899.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "לבן", "Slim-Fit", "סאטן", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 18. חליפה משובצת - Classic/Office
productService.addProductToDB(new Product(
    "W-SU-707", "חליפת משבצות Heritage", "https://api.placeholder.com/w-suit-check",
    "מראה בריטי קלאסי עם דוגמת משבצות עדינה.",
    6599, // 659.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "בז'", "Regular-Fit", "100% כותנה", "Office", "Classic", "Winter", "משובץ")
));

// 19. חליפה פרחונית - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SU-708", "חליפת ויסקוזה פרחונית", "https://api.placeholder.com/w-floral-suit",
    "חליפה קלילה וצבעונית עם הדפס פרחים מרענן.",
    4899, // 489.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "לבן", "Wide-Leg", "100% כותנה", "Vacation", "Summer", "פרחוני")
));

// 20. חליפת קטיפה (סאטן) - Night-Out/Winter
productService.addProductToDB(new Product(
    "W-SU-709", "חליפת סאטן שחורה", "https://api.placeholder.com/w-black-satin-suit",
    "חליפה יוקרתית ומבריקה ליציאות ערב בחורף.",
    7199, // 719.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "שחור", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "Winter", "חלק")
));

// 21. מעיל טרנץ' יוקרתי - Office/Elegant
productService.addProductToDB(new Product(
    "W-CO-612", "מעיל טרנץ' Camel Wool", "https://api.placeholder.com/w-trench-camel",
    "מעיל צמר ארוך בצבע בז' קלאסי. גזרה נצחית עם חגורה במותן, מחמם ומחמיא.",
    7999, // 799.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "בז'", "Regular-Fit", "צמר מרינו", "Elegant", "Winter", "חלק")
));

// 22. חליפת ערב מבריקה - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SU-710", "חליפת סאטן Jet Black", "https://api.placeholder.com/w-suit-night-black",
    "סט בלייזר ומכנסי סאטן מבריקים ליציאה. מראה עוצמתי וזוהר ללילה.",
    6799, // 679.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "שחור", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "Winter", "חלק")
));

// 23. מעיל פאפר מודרני - Streetwear/Winter
productService.addProductToDB(new Product(
    "W-CO-613", "מעיל פאפר Grey Cloud", "https://api.placeholder.com/w-puffer-grey",
    "מעיל נפוח ורך במיוחד. בד חיצוני דוחה מים, גזרת Oversize טרנדית.",
    4699, // 469.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Oversize", "100% כותנה", "Streetwear", "Winter", "חלק")
));

// 24. חליפת עסקים כחולה - Office/Elegant
productService.addProductToDB(new Product(
    "W-SU-711", "חליפת Power Navy Tailored", "https://api.placeholder.com/w-suit-navy",
    "בלייזר מחויט ומכנסיים תואמים. בד: 100% כותנה איכותית. מראה מקצועי ונקי.",
    7299, // 729.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "כחול נייבי", "Regular-Fit", "100% כותנה", "Office", "Elegant", "Spring-Essentials", "חלק")
));

// 25. מעיל חופשה קליל - Vacation/Summer
productService.addProductToDB(new Product(
    "W-CO-614", "מעיל קנבס Summer White", "https://api.placeholder.com/w-coat-white",
    "ג'קט ארוך ודק מבד נושם. מושלם לערבים קרירים בחופשה או על החוף.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "לבן", "Regular-Fit", "100% פשתן", "Vacation", "Summer", "חלק")
));
}
}