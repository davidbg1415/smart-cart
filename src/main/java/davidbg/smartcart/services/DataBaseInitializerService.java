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
        "חולצה אוורירית מחקר שוק זארה. בד: פשתן אירופאי מעובד. גזרת Relaxed, צווארון פתוח.",
        1699, // 169.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "בז'", "Relaxed-Fit", "פשתן", "Summer", "Vacation", "קליל")
    ));

    // 2. טי-שירט Oversize כבדה - סטריטוור
    productService.addProductToDB(new Product(
        "M-SH-202", "טי-שירט Heavyweight שטופה", "images/heavy-tee.jpg",
        "חולצת סטריטוור במשקל 300 GSM. בד: כותנה אורגנית. מראה Vintage Wash עם כתפיים שמוטות.",
        1299, // 129.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "אפור ", "Oversize", "כותנה", "Streetwear", "Vintage", "יומיום")
    ));

    // 3. חולצת אוקספורד Slim-Fit - למשרד/אירוע קל
    productService.addProductToDB(new Product(
        "M-SH-203", "חולצת אוקספורד יוקרתית", "images/oxford-slim.jpg",
        "חולצה מחויטת לעבודה. בד: 98% כותנה, 2% אלסטן (Stretch). גזרה צמודה, צווארון קשיח.",
        1999, // 199.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "כותנה 100%", "Elegant", "Office", "Classic", "חלק")
    ));

    // 4. סריג פולו ריב - מראה "Old Money"
    productService.addProductToDB(new Product(
        "M-SH-204", "חולצת פולו סרוגה בטקסטורת ריב", "images/knit-polo.jpg",
        "מראה אירופאי קלאסי. בד: ויסקוזה וניילון למגע רך וקריר. שרוול קצר עם סיומת מנג'ט.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "כחול ", "Regular-Fit", "ויסקוזה", "Smart-Casual", "Luxury", "פסים עדינים")
    ));

    // 5. חולצת ג'ינס Western - מראה מחוספס
    productService.addProductToDB(new Product(
        "M-SH-205", "חולצת ג'ינס דנים Western", "images/denim-shirt.jpg",
        "חולצת ג'ינס איכותית. בד: כותנה קשיחה (Indigo Denim). שני כיסי חזה, סגירת תיקתקים.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "ג'ינס", "כחול דנים", "Regular-Fit", "כותנה", "Casual", "Outdoor", "חלק")
    ));

    // 6. חולצת שרוול ארוך Henley - בייסיק משודרג
    productService.addProductToDB(new Product(
        "M-SH-206", "חולצת הנלי ריב ארוכה", "images/henley-grey.jpg",
        "חולצה נוחה ליומיום. בד: 60% כותנה, 40% פוליאסטר. מפתח צוואר עם 3 כפתורים, בד טקסטורלי.",
        1199, // 119.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "אפור מלנז'", "Slim-Fit", "כותנה-פוליאסטר", "Basic", "Home-Lounge", "יומיום")
    ));

    // 7. חולצת פלאנל משובצת - מראה "לומברג'ק"
    productService.addProductToDB(new Product(
        "M-SH-207", "חולצת פלאנל משובצת Heavy", "images/flannel-shirt.jpg",
        "חולצה חמה לחורף. בד: כותנה עבה מוברשת. משבצות בגווני אדום ושחור, גזרה רחבה.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "אדום-משובץ", "Oversize", "כותנה", "Winter", "Streetwear", "משובץ")
    ));

    // 8. גופיית ספורט מנדפת - Gym Wear
    productService.addProductToDB(new Product(
        "M-SH-208", "גופיית אימון Performance", "images/gym-tank.jpg",
        "לביצועים מקסימליים. בד: 100% פוליאסטר מנדף זיעה (Dry-Fit). גזרת גב שחיין לתנועה חופשית.",
        899, // 89.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "שחור", "Active", "פוליאסטר", "Gym", "Sport", "חלק")
    ));

    // 9. חולצת פולו סרוגה (Knit Polo)
    productService.addProductToDB(new Product(
        "M-SH-209", "חולצת פולו סרוגה בטקסטורת מעויינים", "images/knit-polo-tan.jpg",
        "מראה יוקרתי ושקט. בד: תערובת כותנה ו-ויסקוזה. גזרה ישרה עם סיומת מנג'ט בקיפול השרוול.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "בז'", "Regular-Fit", "ויסקוזה", "Old-Money", "Luxury", "Smart-Casual")
    ));

    // 10. חולצת טי "מוסל פיט" (Muscle Fit)
    productService.addProductToDB(new Product(
        "M-SH-210", "טי-שירט Muscle Fit שחורה", "images/muscle-tee.jpg",
        "בד אלסטי במיוחד המדגיש את מבנה הגוף. בד: 95% כותנה, 5% לייקרה. שרוולים קצרים וצמודים.",
        899, // 89.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "שחור", "Muscle-Fit", "כותנה-לייקרה", "Gym", "Basic", "יומיום")
    ));

    // 11. חולצת צווארון סיני (Mandarin Collar)
    productService.addProductToDB(new Product(
        "M-SH-211", "חולצת כפתורים צווארון סיני לבנה", "images/mandarin-shirt.jpg",
        "לוק נקי ומודרני. בד: 100% כותנת פופלין דקה. ללא צווארון מסורתי, כפתורים נסתרים.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "כותנה", "Minimalist", "Modern", "חלק")
    ));

    // 12. חולצת פלאנל משובצת "Over-Shirt"
    productService.addProductToDB(new Product(
        "M-SH-212", "ג'קט חולצה (Shacket) משובץ כחול", "images/shacket-blue.jpg",
        "חולצה עבה המשמשת כג'קט קל. בד: פלנל צמר סינתטי. גזרת Oversize, כיסים גדולים בחזה.",
        2599, // 259.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "כחול-משובץ", "Oversize", "צמר-סינתטי", "Winter", "Streetwear", "משובץ")
    ));

    // 13. חולצת פופלין בגזרת Boxy
    productService.addProductToDB(new Product(
        "M-SH-213", "חולצת פופלין Boxy-Fit קצרה", "images/boxy-poplin.jpg",
        "מראה אורבני מודרני. בד: 100% כותנת פופלין פריכה. גזרה רחבה וקצרה עם כתפיים שמוטות.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "ירוק מרווה", "Boxy-Fit", "כותנה", "Streetwear", "Minimalist", "קיץ")
    ));

    // 14. חולצת פולו עם רוכסן (Zip Polo)
    productService.addProductToDB(new Product(
        "M-SH-214", "חולצת פולו עם סגירת רוכסן", "images/zip-polo.jpg",
        "סטייל Smart-Casual נקי. בד: כותנה בטקסטורת Pike. רוכסן מתכת איכותי במקום כפתורים.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "אפור גרפיט", "Slim-Fit", "כותנה", "Office", "Classic", "חלק")
    ));

    // 15. חולצת "גרנדד" (Grandad Collar) מפשתן
    productService.addProductToDB(new Product(
        "M-SH-215", "חולצת פשתן צווארון סיני ארוכה", "images/grandad-linen.jpg",
        "חולצה קלילה ונושמת. בד: תערובת פשתן וכותנה. צווארון עגול ללא קיפול, שרוולים מתקפלים עם כפתור.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "לבן", "Relaxed-Fit", "פשתן-כותנה", "Boho", "Vacation", "קליל")
    ));

    // 16. טי-שירט מנדפת זיעה (Compression)
    productService.addProductToDB(new Product(
        "M-SH-216", "חולצת אימון Compression צמודה", "images/active-tee.jpg",
        "מיועדת לאימונים עצימים. בד: 88% פוליאסטר, 12% אלסטן. טכנולוגיית מנדפת זיעה, בד גמיש לארבעה כיוונים.",
        1299, // 129.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "שחור", "Compression", "פוליאסטר-אלסטן", "Gym", "Active", "ספורט")
    ));

    // 17. חולצת פלאנל משובצת (Buffalo Check)
    productService.addProductToDB(new Product(
        "M-SH-217", "חולצת פלאנל משבצות באפלו", "images/buffalo-plaid.jpg",
        "סטייל Workwear קלאסי. בד: כותנה מוברשת עבה. משבצות אדום-שחור גדולות.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "אדום-שחור", "Regular-Fit", "כותנה", "Winter", "Casual", "משובץ")
    ));

    // 18. חולצת "ריזורט" (Resort Shirt) מודפסת
    productService.addProductToDB(new Product(
        "M-SH-218", "חולצת ריזורט בהדפס טרופי", "images/resort-print.jpg",
        "חולצה לחופשה. בד: 100% ויסקוזה נשפכת. הדפס עלי דקל, צווארון פתוח, כפתורי עץ.",
        1499, // 149.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "צבעוני", "Relaxed-Fit", "ויסקוזה", "Summer", "Vacation", "הדפס")
    ));

    // 19. חולצת גולף (Turtleneck) צמר
    productService.addProductToDB(new Product(
        "M-SH-219", "סריג גולף צמר דק", "images/turtleneck.jpg",
        "מראה אירופאי אלגנטי. בד: 100% צמר מרינו. צווארון גבוה מתקפל, בד רך ונעים למגע.",
        2999, // 299.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "כחול", "Slim-Fit", "צמר מרינו", "Luxury", "Winter", "Elegant")
    ));

    // 20. גופיית Racerback לאימון
    productService.addProductToDB(new Product(
        "M-SH-220", "גופיית Racerback אתלטית", "images/active-tank.jpg",
        "חופש תנועה מלא בכתפיים. בד: מיקרופייבר קל משקל. בד מנדף מהיר יבוש.",
        799, // 79.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "ניאון", "Athletic-Fit", "מיקרופייבר", "Gym", "Running", "ספורט")
    ));

    // 21. קפוצ'ון Heavyweight שטוף - Streetwear
    productService.addProductToDB(new Product(
        "M-SH-221", "קפוצ'ון Oversize וינטג' אפור", "images/hoodie-vintage.jpg",
        "קפוצ'ון כבד (450 GSM). בד: כותנה עם בטנת פליז רכה. מראה שטוף, כיס קנגורו גדול.",
        2499, // 249.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "אפור", "Oversize", "כותנה", "Streetwear", "Winter", "חם")
    ));

    // 22. חולצת קורדרוי (Corduroy) - Casual
    productService.addProductToDB(new Product(
        "M-SH-222", "חולצת קורדרוי חום כאמל", "images/corduroy-shirt.jpg",
        "חולצה בטקסטורת פסים דקה. בד: כותנה קורדרוי. גזרת Regular, שני כיסי חזה עם כפתור.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "חום", "Regular-Fit", "קורדרוי", "Autumn", "Casual", "טקסטורה")
    ));

    // 23. סוודר V-Neck צמר מרינו - Elegant
    productService.addProductToDB(new Product(
        "M-SH-223", "סריג וי צמר מרינו דק", "images/v-neck-wool.jpg",
        "למראה אלגנטי מעל חולצה מכופתרת. בד: 100% צמר מרינו איטלקי. בד נושם ודק במיוחד.",
        2999, // 299.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "ירוק זית", "Slim-Fit", "צמר מרינו", "Luxury", "Office", "Elegant")
    ));

    // 24. חולצת פופלין פסים - Business
    productService.addProductToDB(new Product(
        "M-SH-224", "חולצת פופלין פסים כחול-לבן", "images/stripe-poplin.jpg",
        "חולצת משרד קלאסית. בד: 100% כותנת פופלין. גזרת Tailored, פסי סיכה דקים, צווארון נוקשה.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "פסים", "Tailored-Fit", "כותנה", "Office", "Classic", "Business")
    ));

    // 25. טי-שירט "בוקסי" בייסיק - Minimalist
    productService.addProductToDB(new Product(
        "M-SH-225", "טי-שירט Boxy-Fit לבן בייסיק", "images/boxy-white.jpg",
        "גזרה רבועה ומודרנית. בד: כותנה עבה. שרוולים רחבים וקצרים, מפתח צוואר סגור.",
        999, // 99.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "לבן", "Boxy-Fit", "כותנה", "Minimalist", "Basic", "יומיום")
    ));

    // 26. חולצת ג'ינס שחורה - Rugged
    productService.addProductToDB(new Product(
        "M-SH-226", "חולצת דנים שחורה שטופה", "images/black-denim.jpg",
        "מראה אורבני מחוספס. בד: 100% דנים כותנה. כפתורי תיקתק ממתכת, תפרים בולטים.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "ג'ינס", "שחור", "Regular-Fit", "דנים", "Streetwear", "Bold", "חלק")
    ));

    // 27. סוודר "קולור-בלוק" - Trendy
    productService.addProductToDB(new Product(
        "M-SH-227", "סריג קולור-בלוק גווני אדמה", "images/colorblock-knit.jpg",
        "עיצוב גאומטרי מודרני. בד: תערובת צמר ואקריליק. שילוב צבעי חום, בז' וקרם.",
        2399, // 239.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "צבעוני", "Oversize", "צמר", "Trendy", "Winter", "גאומטרי")
    ));

    // 28. חולצת "שאקט" (Shacket) משובצת - Winter
    productService.addProductToDB(new Product(
        "M-SH-228", "ג'קט חולצה משובץ עבה", "images/shacket-plaid.jpg",
        "פריט מעבר מושלם. בד: פלנל צמר עבה עם בטנת סאטן. כיסי צד נסתרים.",
        3299, // 329.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "משובץ", "Oversize", "צמר", "Winter", "Outdoor", "חם")
    ));

    // 29. חולצת קומפרשן לאימון
    productService.addProductToDB(new Product(
        "M-SH-229", "חולצת Compression אקטיב", "images/compression-black.jpg",
        "חולצת אימון צמודה במיוחד התומכת בשרירים. בד: 88% פוליאסטר ממוחזר, 12% אלסטן. מנדפת זיעה ומתייבשת במהירות.",
        1399, // 139.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "שחור", "Compression", "Active", "Gym", "Performance", "חלק")
    ));

    // 30. סוודר צמות קלאסי (Cable Knit)
    productService.addProductToDB(new Product(
        "M-SH-230", "סוודר Cable Knit צמר", "images/cable-knit-cream.jpg",
        "סריג חורף כבד במראה אירופאי קלאסי. בד: 70% כותנה, 30% צמר. טקסטורת צמות בולטת, צווארון עגול.",
        2899, // 289.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "לבן", "Regular-Fit", "Cotton-Wool", "Winter", "Classic", "Old-Money")
    ));

    // 31. חולצת הוואי מודפסת (Rayon)
    productService.addProductToDB(new Product(
        "M-SH-231", "חולצת ריזורט רייון מודפסת", "images/hawaiian-shirt.jpg",
        "חולצה קלילה לחופשה. בד: 100% רייון (ויסקוזה איכותית). הדפס עלים טרופי, גזרה רחבה, כפתורי קליפה.",
        1599, // 159.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "צבעוני", "Relaxed-Fit", "Rayon", "Summer", "Vacation", "הדפס")
    ));

    // 32. טי-שירט וינטג' להקה
    productService.addProductToDB(new Product(
        "M-SH-232", "טי-שירט Graphic Vintage", "images/band-tee.jpg",
        "מראה מכובס ומשופשף. בד: כותנה כבדה. הדפס גרפי בסגנון רוק משנות ה-90, צווארון ריב עבה.",
        1199, // 119.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "אפור פחם", "Oversize", "כותנה", "Streetwear", "Graphic", "Vintage")
    ));

    // 33. חולצת פולו רוכסן מודרנית
    productService.addProductToDB(new Product(
        "M-SH-233", "חולצת פולו עם רוכסן מתכת", "images/zip-polo2.jpg",
        "סטייל נקי לעבודה. בד: כותנה בטקסטורת Pike. רוכסן כסוף במקום כפתורים, ללא כיס.",
        1799, // 179.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "כחול", "Slim-Fit", "כותנה", "Office", "Modern", "Minimalist")
    ));

    // 34. חולצת שמברה (Chambray)
    productService.addProductToDB(new Product(
        "M-SH-234", "חולצת שמברה כחולה", "images/chambray-shirt.jpg",
        "מראה דנים קליל. בד: 100% כותנת שמברה. בד דק דמוי ג'ינס, תפרים לבנים בולטים, כפתורי פנינה.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "כחול בהיר", "Regular-Fit", "Chambray", "Casual", "Workwear", "חלק")
    ));

    // 35. גופיית כדורסל רשת
    productService.addProductToDB(new Product(
        "M-SH-235", "גופיית Mesh אתלטית", "images/mesh-tank.jpg",
        "מתאימה לאימונים ולמראה רחוב. בד: 100% פוליאסטר רשת נושם. גזרה רחבה, סיומת פסים בצוואר.",
        999, // 99.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "שחור-לבן", "Athletic-Fit", "Polyester", "Sport", "Streetwear", "Basic")
    ));

    // 36. חולצת סאטן לערב
    productService.addProductToDB(new Product(
        "M-SH-236", "חולצת סאטן יוקרתית שחורה", "images/satin-men.jpg",
        "למראה ערב נוצץ ומתוחכם. בד: 100% סאטן משי סינתטי. בד נשפך עם ברק עדין, כפתורים נסתרים.",
        2499, // 249.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "שחור", "Slim-Fit", "Satin", "Evening", "Night-Out", "Luxury")
    ));

    // 37. פלאנל משובצת כבדה
    productService.addProductToDB(new Product(
        "M-SH-237", "חולצת פלאנל משובצת Heavy", "images/heavy-flannel.jpg",
        "חולצה מחממת שמתפקדת כג'קט. בד: כותנה מוברשת עבה. משבצות בגווני ירוק זית ושחור.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "ירוק-משובץ", "Regular-Fit", "כותנה", "Winter", "Outdoor", "Rough")
    ));

    // 38. חולצת מעטפת בוהו
    productService.addProductToDB(new Product(
        "M-SH-238", "חולצת קימונו/מעטפת פשתן", "imagesrap-linen.jpg",
        "מראה ייחודי ונינוח. בד: 50% פשתן, 50% כותנה. סגירת קשירה פנימית, שרוולים רחבים.",
        2399, // 239.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Relaxed-Fit", "Linen-Blend", "Boho", "Vacation", "Unique")
    ));

    // 39. טי-שירט עם כיס (Pocket Tee)
    productService.addProductToDB(new Product(
        "M-SH-239", "טי-שירט Slub עם כיס", "images/pocket-tee.jpg",
        "בייסיק עם טקסטורה מעניינת. בד: 100% כותנת Slub (טקסטורה לא אחידה). כיס בחזה שמאל.",
        899, // 89.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "בורדו", "Regular-Fit", "כותנה", "Basic", "Casual", "חלק")
    ));

    // 40. גולף צמר מרינו דק
    productService.addProductToDB(new Product(
        "M-SH-240", "סריג גולף Merino פרימיום", "images/merino-turtle.jpg",
        "פריט חובה למראה שכבות יוקרתי. בד: 100% צמר מרינו דק במיוחד. בד גמיש, רך ולא מגרד.",
        3199, // 319.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "אפור מלנז'", "Slim-Fit", "Merino-Wool", "Winter", "Luxury", "Elegant")
    ));

    // 41. חולצת טוקסידו רשמית
    productService.addProductToDB(new Product(
        "M-SH-241", "חולצת טוקסידו Wing-Collar", "images/tux-shirt.jpg",
        "החולצה הרשמית ביותר לאירועי ערב. בד: כותנה מצרית איכותית. צווארון כנף לעניבת פרפר, חזית פיקה נוקשה.",
        3499, // 349.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "Luxury", "Formal", "Wedding", "Premium")
    ));

    // 42. חולצת ריצה תרמית
    productService.addProductToDB(new Product(
        "M-SH-242", "חולצת ריצה תרמית ארוכה", "images/thermal-run.jpg",
        "לפעילות גופנית במזג אוויר קר. בד: 92% פוליאסטר תרמי, 8% אלסטן. פנים מורשת (Brushed) לשמירת חום.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "שחור", "Athletic-Fit", "Thermal", "Active", "Sport", "Winter")
    ));

    // 43. חולצת "גרנדד" פסים
    productService.addProductToDB(new Product(
        "M-SH-243", "חולצת Grandad פסי סיכה", "images/grandad-stripes.jpg",
        "מראה אירופאי נינוח. בד: 100% כותנת פופלין. צווארון סיני עגול, פסי תכלת-לבן דקים, גזרה ישרה.",
        1699, // 169.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "פסים", "Regular-Fit", "כותנה", "Smart-Casual", "Classic", "יומיום")
    ));

    // 44. טי-שירט "רייזר" (Racerback)
    productService.addProductToDB(new Product(
        "M-SH-244", "גופיית Racerback מקצועית", "images/gym-racer.jpg",
        "חיתוך עמוק בגב להדגשת השרירים. בד: תערובת כותנה ומודל למגע משי. בד נושם וקל במיוחד.",
        799, // 79.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "אפור גרפיט", "Muscle-Fit", "Cotton-Modal", "Gym", "Active", "Basic")
    ));

    // 45. סוודר פולו "Old Money"
    productService.addProductToDB(new Product(
        "M-SH-245", "סריג פולו צמר ומשי", "images/silk-wool-polo.jpg",
        "פריט פרימיום למראה יוקרתי שקט. בד: 70% צמר מרינו, 30% משי. גזרה צמודה עם צווארון פולו סרוג.",
        4299, // 429.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "כחול", "Slim-Fit", "Silk-Wool", "Luxury", "Old-Money", "Elegant")
    ));

    // 46. חולצת עבודה (Workshirt) עמידה
    productService.addProductToDB(new Product(
        "M-SH-246", "חולצת עבודה Canvas כבדה", "images/canvas-shirt.jpg",
        "עמידות מקסימלית לתנאי חוץ. בד: 100% כותנת קנבס עבה. תפרים כפולים מחוזקים, כיסי דגמ\"ח בחזה.",
        2199, // 219.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "חקי", "Regular-Fit", "Canvas", "Outdoor", "Workwear", "Rough")
    ));

    // 47. קפוצ'ון "שרפה" (Sherpa)
    productService.addProductToDB(new Product(
        "M-SH-247", "קפוצ'ון בטנת פרווה Sherpa", "images/sherpa-hoodie.jpg",
        "הפריט הכי חם בקולקציה. בד: פליז עבה עם בטנת פרווה סינתטית בתוך הכובע והגוף. שרוולי ריב.",
        3199, // 319.90 NIS
        Arrays.asList("גברים", "חולצות", "ארוך", "שחור מלנז'", "Oversize", "Sherpa", "Winter", "Warm", "Casual")
    ));

    // 48. גופיית כדורסל רטרו
    productService.addProductToDB(new Product(
        "M-SH-248", "גופיית רשת Basketball Vintage", "images/retro-jersey.jpg",
        "מראה רחוב נוסטלגי. בד: 100% פוליאסטר רשת כפול. מספר מודפס בחזית ובגב, סיומת פסים צבעונית.",
        1499, // 149.90 NIS
        Arrays.asList("גברים", "חולצות", "גופיות", "צבעוני", "Athletic-Fit", "Mesh", "Streetwear", "Vintage", "Sport")
    ));

    // 49. חולצת פופלין "סטרצ'" צבעונית
    productService.addProductToDB(new Product(
        "M-SH-249", "חולצת כפתורים Stretch בורדו", "images/stretch-shirt.jpg",
        "נוחות מקסימלית למראה מחויט. בד: 96% כותנה, 4% אלסטן. בד גמיש במיוחד המאפשר תנועה חופשית.",
        1899, // 189.90 NIS
        Arrays.asList("גברים", "חולצות", "מכופתרות", "בורדו", "Slim-Fit", "Cotton-Stretch", "Office", "Elegant", "חלק")
    ));

    // 50. טי-שירט "בוקסי" (Boxy) עם הדפס
    productService.addProductToDB(new Product(
        "M-SH-250", "טי-שירט Boxy גב מודפס", "images/boxy-graphic.jpg",
        "גזרת רחוב עדכנית. בד: כותנה אורגנית במשקל בינוני. הדפס אומנותי גדול על כל הגב.",
        1199, // 119.90 NIS
        Arrays.asList("גברים", "חולצות", "קצר", "לבן", "Boxy-Fit", "כותנה", "Streetwear", "Graphic", "Trendy")
    ));

    // --- חולצות פשתן (Linen) ---
    productService.addProductToDB(new Product(
        "M-SH-251", "חולצת פשתן קובה כתומה", "images/orange-linen.jpg",
        "חולצה אוורירית וקלילה לימי הקיץ החמים. גזרה משוחררת ונוחה.",
        1499, Arrays.asList("גברים", "חולצות", "פשתן", "כתום", "Summer", "קצר", "מכופתרות", "Vacation")
    ));

    productService.addProductToDB(new Product(
        "M-SH-252", "חולצת פשתן לבנה חוף ארוכה", "images/white-long-linen.jpg",
        "לוק חופשה קלאסי קליל ונושם. שרוולים ניתנים לקיפול.",
        1899, Arrays.asList("גברים", "חולצות", "פשתן", "לבן", "Summer", "ארוך", "מכופתרות", "Vacation")
    ));

    productService.addProductToDB(new Product(
        "M-SH-253", "חולצת פשתן אפורה קצר חלק", "images/gray-linen-short.jpg",
        "לוק מינימליסטי נקי ועדין המתאים ליומיום ולעבודה.",
        1399, Arrays.asList("גברים", "חולצות", "פשתן", "אפור", "Summer", "קצר", "חלק", "Minimalist")
    ));

    productService.addProductToDB(new Product(
        "M-SH-254", "חולצת פשתן בז' ארוכה משובצת", "images/beige-plaid-linen.jpg",
        "שילוב של בד פשתן קריר עם דוגמת משבצות עדינה לעונות המעבר.",
        1699, Arrays.asList("גברים", "חולצות", "פשתן", "בז'", "Spring", "ארוך", "משובץ", "Smart-Casual")
    ));

    productService.addProductToDB(new Product(
        "M-SH-255", "חולצת פשתן כחולה פרחונית", "images/blue-floral-linen.jpg",
        "חולצת ריזורט מעוצבת עם הדפס עלים טרופי וצבעוני.",
        1599, Arrays.asList("גברים", "חולצות", "פשתן", "כחול", "Summer", "קצר", "פרחוני", "Vacation")
    ));

    productService.addProductToDB(new Product(
        "M-SH-256", "חולצת פשתן ירוקה קצרה חלקה", "images/green-linen-plain.jpg",
        "צבע ירוק מרווה טרנדי. מראה נקי ואוורירי.",
        1299, Arrays.asList("גברים", "חולצות", "פשתן", "ירוק", "Summer", "קצר", "חלק", "Minimalist")
    ));

    productService.addProductToDB(new Product(
        "M-SH-257", "חולצת פשתן חומה ארוכה חלקה", "images/brown-linen-long.jpg",
        "סגנון בוהו-שיק אופנתי עם צווארון סיני פתוח.",
        1799, Arrays.asList("גברים", "חולצות", "פשתן", "חום", "Autumn", "ארוך", "חלק", "Boho")
    ));

    productService.addProductToDB(new Product(
        "M-SH-258", "חולצת פשתן אדומה קצרה פסים", "images/red-stripe-linen.jpg",
        "פסי רטרו אנכיים דקים למראה קיצי בולט וייחודי.",
        1499, Arrays.asList("גברים", "חולצות", "פשתן", "אדום", "Summer", "קצר", "פסים", "Bright")
    ));

    productService.addProductToDB(new Product(
        "M-SH-259", "חולצת פשתן צבעונית פרחונית", "images/colorful-floral-linen.jpg",
        "הדפס פסטיבלים נועז וצבעוני לחופשות קיץ עצימות.",
        1699, Arrays.asList("גברים", "חולצות", "פשתן", "צבעוני", "Summer", "קצר", "פרחוני", "Festival")
    ));

    productService.addProductToDB(new Product(
        "M-SH-260", "חולצת פשתן שחורה ארוכה דגמח", "images/black-cargo-linen.jpg",
        "שילוב מיוחד של בד קליל עם כיסי דגמח בחזה למראה אורבני.",
        1999, Arrays.asList("גברים", "חולצות", "פשתן", "שחור", "Spring", "ארוך", "דגמח", "Modern")
    ));

    // --- סריגי צמר מרינו (Merino Wool) ---
    productService.addProductToDB(new Product(
        "M-SH-261", "סוודר צמר מרינו ירוק ארוך", "images/green-merino-sweater.jpg",
        "סריג פרימיום מחמם ונעים למגע מחומרים מבודדים איכותיים.",
        2799, Arrays.asList("גברים", "חולצות", "צמר מרינו", "ירוק", "Winter", "ארוך", "חלק", "Elegant")
    ));

    productService.addProductToDB(new Product(
        "M-SH-262", "סריג פולו צמר מרינו חום פסים", "images/brown-merino-polo.jpg",
        "מראה אולד-מאני יוקרתי ושקט עם פסים עדינים במנג'ט.",
        2999, Arrays.asList("גברים", "חולצות", "צמר מרינו", "חום", "Autumn", "ארוך", "פסים", "Luxury")
    ));

    productService.addProductToDB(new Product(
        "M-SH-263", "סוודר גולף צמר מרינו שחור", "images/black-turtleneck-merino.jpg",
        "גולף קלאסי צמוד למראה מחויט ואירופאי אצילי.",
        3299, Arrays.asList("גברים", "חולצות", "צמר מרינו", "שחור", "Winter", "ארוך", "חלק", "Luxury")
    ));

    productService.addProductToDB(new Product(
        "M-SH-264", "סוודר צמר מרינו אפור פסים", "images/gray-stripe-merino.jpg",
        "מתאים מעל חולצה מכופתרת למשרד או לימי החורף.",
        2599, Arrays.asList("גברים", "חולצות", "צמר מרינו", "אפור", "Winter", "ארוך", "פסים", "Office")
    ));

    productService.addProductToDB(new Product(
        "M-SH-265", "סוודר צמר מרינו אדום חלק", "images/red-merino-plain.jpg",
        "צבע אדום עמוק ועשיר, סריגה דקה ועמידה במיוחד.",
        2699, Arrays.asList("גברים", "חולצות", "צמר מרינו", "אדום", "Winter", "ארוך", "חלק", "Warm")
    ));

    productService.addProductToDB(new Product(
        "M-SH-266", "סריג צמר מרינו בז' חלק יוקרתי", "images/beige-merino-luxury.jpg",
        "גוון שמנת טבעי ויוקרתי המעניק מגע רך ומבודד.",
        3499, Arrays.asList("גברים", "חולצות", "צמר מרינו", "בז'", "Winter", "ארוך", "חלק", "Old-Money")
    ));

    productService.addProductToDB(new Product(
        "M-SH-267", "סוודר צמר מרינו צהוב חלק", "images/yellow-merino-cozy.jpg",
        "סריג אופנתי וצעיר בצבע חרדל בולט לחורף שמח יותר.",
        2499, Arrays.asList("גברים", "חולצות", "צמר מרינו", "צהוב", "Winter", "ארוך", "חלק", "Trendy")
    ));

    productService.addProductToDB(new Product(
        "M-SH-268", "סריג צמר מרינו ורוד חלק", "images/pink-merino-spring.jpg",
        "צבע ורוד עתיק עדין המתאים במיוחד לעונות המעבר הרכות.",
        2399, Arrays.asList("גברים", "חולצות", "צמר מרינו", "ורוד", "Spring", "ארוך", "חלק", "Minimalist")
    ));

    productService.addProductToDB(new Product(
        "M-SH-269", "סוודר צמר מרינו כתום משובץ", "images/orange-plaid-merino.jpg",
        "דוגמת משבצות גאומטרית ייחודית בצבעי שלכת חמימים.",
        2899, Arrays.asList("גברים", "חולצות", "צמר מרינו", "כתום", "Winter", "ארוך", "משובץ", "Vintage")
    ));

    productService.addProductToDB(new Product(
        "M-SH-270", "סוודר צמר מרינו כחול דגמח כבד", "images/blue-cargo-merino.jpg",
        "עיצוב צבאי/אורבני מחוספס עם כיסי דגמח ארוגים בצידי הזרוע.",
        3199, Arrays.asList("גברים", "חולצות", "צמר מרינו", "כחול", "Winter", "ארוך", "דגמח", "Sporty")
    ));

    // --- חולצות דנים / ג'ינס (Denim) ---
    productService.addProductToDB(new Product(
        "M-SH-271", "חולצת ג'ינס שחורה קצרה", "images/black-denim-short.jpg",
        "מראה רוקסטאר קיצי עם שטיפת וינטג' קלה וכפתורי תיקתק.",
        1599, Arrays.asList("גברים", "חולצות", "דנים", "שחור", "Spring", "קצר", "מכופתרות", "Casual")
    ));

    productService.addProductToDB(new Product(
        "M-SH-272", "חולצת דנים כחולה פסים", "images/blue-stripe-denim.jpg",
        "חולצת ג'ינס ייחודית בטקסטורת פסי סיכה לבנים אנכיים.",
        1999, Arrays.asList("גברים", "חולצות", "דנים", "כחול", "Spring", "ארוך", "פסים", "Casual")
    ));

    productService.addProductToDB(new Product(
        "M-SH-273", "חולצת דנים ירוקה דגמח", "images/green-cargo-denim.jpg",
        "בד דנים עמיד במראה צבאי פונקציונלי עם כיסי חזה גדולים.",
        2199, Arrays.asList("גברים", "חולצות", "דנים", "ירוק", "Autumn", "ארוך", "דגמח", "Outdoor")
    ));

    productService.addProductToDB(new Product(
        "M-SH-274", "חולצת דנים לבנה קצרה מכופתרת", "images/white-denim-short.jpg",
        "בד ג'ינס לבן פריך דק ונוח למראה סטריטוור מודרני.",
        1699, Arrays.asList("גברים", "חולצות", "דנים", "לבן", "Summer", "קצר", "מכופתרות", "Modern")
    ));

    productService.addProductToDB(new Product(
        "M-SH-275", "חולצת דנים חומה ארוכה חלקה", "images/brown-denim-long.jpg",
        "צבע חום אדמה כהה, לוק מחוספס ועמיד לעבודה ויומיום.",
        1899, Arrays.asList("גברים", "חולצות", "דנים", "חום", "Autumn", "ארוך", "חלק", "Casual")
    ));

    productService.addProductToDB(new Product(
        "M-SH-276", "חולצת דנים אדומה משובצת", "images/red-plaid-denim.jpg",
        "שילוב חומרים של דנים ופלאנל בעיצוב משבצות באפלו קלאסי.",
        1799, Arrays.asList("גברים", "חולצות", "דנים", "אדום", "Autumn", "ארוך", "משובץ", "Casual")
    ));

    productService.addProductToDB(new Product(
        "M-SH-277", "חולצת דנים אפורה ארוכה דגמח", "images/gray-cargo-denim.jpg",
        "גזרת ג'קט חולצה עבה עם שטיפה אסידת חזקה וכיסים שימושיים.",
        2299, Arrays.asList("גברים", "חולצות", "דנים", "אפור", "Winter", "ארוך", "דגמח", "Workwear")
    ));

    productService.addProductToDB(new Product(
        "M-SH-278", "חולצת דנים צהובה ארוכה", "images/yellow-denim-street.jpg",
        "צבע צהוב חרדל שטוף המעניק ביטוי אישי חזק לחובבי אופנת רחוב.",
        1999, Arrays.asList("גברים", "חולצות", "דנים", "צהוב", "Spring", "ארוך", "חלק", "Streetwear")
    ));

    productService.addProductToDB(new Product(
        "M-SH-279", "חולצת דנים ורודה קצרה חלקה", "images/pink-denim-short.jpg",
        "לוק מרענן ונועז בצבע ורוד פסטל עדין לימי הקיץ המאוחרים.",
        1499, Arrays.asList("גברים", "חולצות", "דנים", "ורוד", "Summer", "קצר", "חלק", "Trendy")
    ));

    productService.addProductToDB(new Product(
        "M-SH-280", "חולצת דנים אפורה קצר חלק מכופתרת", "images/gray-denim-boxy.jpg",
        "גזרת בוקסי מרובעת בגוון אפור פחם נקי עם כפתורי מתכת כהים.",
        1599, Arrays.asList("גברים", "חולצות", "דנים", "אפור", "Summer", "קצר", "חלק", "מכופתרות")
    ));

    // --- חולצות סאטן (Satin) ---
    productService.addProductToDB(new Product(
        "M-SH-281", "חולצת סאטן אדומה ארוכה לערב", "images/red-satin-shirt.jpg",
        "בד נשפך בעל ברק עשיר המותאם למסיבות, אירועים וחיי לילה.",
        2399, Arrays.asList("גברים", "חולצות", "סאטן", "אדום", "Autumn", "ארוך", "מכופתרות", "Night-Out")
    ));

    productService.addProductToDB(new Product(
        "M-SH-282", "חולצת סאטן לבנה קצרה מינימליסטית", "images/white-satin-short.jpg",
        "לוק מודרני קליל ונקי בעל מגע משי קריר ומלטף לשעות הערב החמות.",
        1999, Arrays.asList("גברים", "חולצות", "סאטן", "לבן", "Summer", "קצר", "מכופתרות", "Minimalist")
    ));

    productService.addProductToDB(new Product(
        "M-SH-283", "חולצת סאטן כחולה ארוכה חלקה", "images/blue-satin-plain.jpg",
        "גוון כחול רויאל מלכותי, כפתורים נסתרים לחזית חלקה ואלגנטית.",
        2499, Arrays.asList("גברים", "חולצות", "סאטן", "כחול", "Spring", "ארוך", "חלק", "Elegant")
    ));

    productService.addProductToDB(new Product(
        "M-SH-284", "חולצת סאטן צהובה קצרה", "images/yellow-satin-party.jpg",
        "צבע צהוב זהב בוהק ונשפך המבטיח להשאיר רושם בכל מועדון.",
        1899, Arrays.asList("גברים", "חולצות", "סאטן", "צהוב", "Summer", "קצר", "מכופתרות", "Bold")
    ));

    // --- חולצות סאטן המשך הגיוון לחבילות ---
    productService.addProductToDB(new Product(
        "M-SH-285", "חולצת סאטן ורודה ארוכה אלגנטית", "images/pink-satin-luxury.jpg",
        "ורוד פנינה יוקרתי, גזרה צמודה ומחטבת התואמת לחליפות ערב.",
        2599, Arrays.asList("גברים", "חולצות", "סאטן", "ורוד", "Spring", "ארוך", "מכופתרות", "Luxury")
    ));

    productService.addProductToDB(new Product(
        "M-SH-286", "חולצת סאטן אפורה קצרה חלק", "images/gray-satin-short.jpg",
        "צבע כסף מטאלי עדין עם שרוולים רחבים בסגנון עכשיווי סלים-פיט.",
        1799, Arrays.asList("גברים", "חולצות", "סאטן", "אפור", "Summer", "קצר", "חלק", "Night-Out")
    ));

    productService.addProductToDB(new Product(
        "M-SH-287", "חולצת סאטן ירוקה ארוכה משובצת", "images/green-satin-plaid.jpg",
        "טקסטורת משבצות פנימית מרומזת בגוון ירוק בקבוק עמוק ואופנתי.",
        2699, Arrays.asList("גברים", "חולצות", "סאטן", "ירוק", "Autumn", "ארוך", "משובץ", "Fashion")
    ));

    productService.addProductToDB(new Product(
        "M-SH-288", "חולצת סאטן כתומה ארוכה חלקה", "images/orange-satin-long.jpg",
        "צבע תפוז שרוף מבריק ונשפך, צווארון רחב בסגנון שנות ה-70.",
        2299, Arrays.asList("גברים", "חולצות", "סאטן", "כתום", "Autumn", "ארוך", "חלק", "Luxury")
    ));

    productService.addProductToDB(new Product(
        "M-SH-289", "חולצת סאטן חומה ארוכה פסים", "images/brown-satin-stripe.jpg",
        "גוון שוקולד כהה עשיר בשילוב פסי אורך דקים המעניקים אשליה גבוהה.",
        2499, Arrays.asList("גברים", "חולצות", "סאטן", "חום", "Autumn", "ארוך", "פסים", "Classic")
    ));

    productService.addProductToDB(new Product(
        "M-SH-290", "חולצת סאטן שחורה קצרה פרחונית", "images/black-floral-satin.jpg",
        "חולצת בארוק מודפסת פרחים מוזהבים על רקע סאטן שחור עמוק.",
        2199, Arrays.asList("גברים", "חולצות", "סאטן", "שחור", "Summer", "קצר", "פרחוני", "Luxury")
    ));

    // --- חולצות כותנה (Cotton) ---
    productService.addProductToDB(new Product(
        "M-SH-291", "טי-שירט כותנה צהובה חלק", "images/yellow-cotton-tee.jpg",
        "בייסיק איכותי ונוח ליומיום מכותנה סרוקה ונושמת.",
        899, Arrays.asList("גברים", "חולצות", "כותנה", "צהוב", "Summer", "קצר", "חלק", "Basic")
    ));

    productService.addProductToDB(new Product(
        "M-SH-292", "קפוצ'ון כותנה ורוד משוחרר", "images/pink-cotton-hoodie.jpg",
        "בד פוטר עבה ומפנק לחורף, גזרת אוברסייז רחבה וטרנדית.",
        2299, Arrays.asList("גברים", "חולצות", "כותנה", "ורוד", "Winter", "ארוך", "חלק", "Oversize")
    ));

    productService.addProductToDB(new Product(
        "M-SH-293", "חולצה משובצת כותנה כחולה ארוכה", "images/blue-cotton-plaid.jpg",
        "חולצת פלאנל יומיומית רכה ומחממת בדוגמת משבצות נצחית.",
        1499, Arrays.asList("גברים", "חולצות", "כותנה", "כחול", "Winter", "ארוך", "משובץ", "Casual")
    ));

    productService.addProductToDB(new Product(
        "M-SH-294", "טי-שירט כותנה צבעונית פרחונית", "images/colorful-cotton-print.jpg",
        "הדפס גרפי פרחוני מודרני בחזית, מתאימה לחופש ולמראה רחוב.",
        1199, Arrays.asList("גברים", "חולצות", "כותנה", "צבעוני", "Summer", "קצר", "פרחוני", "Trendy")
    ));

    productService.addProductToDB(new Product(
        "M-SH-295", "חולצת כותנה כתומה קצרה חלקה", "images/orange-cotton-plain.jpg",
        "צבע כתום בהיר וקיצי, מתאימה לשילוב קל עם שורטים וג'ינסים.",
        999, Arrays.asList("גברים", "חולצות", "כותנה", "כתום", "Summer", "קצר", "חלק", "Basic")
    ));

    productService.addProductToDB(new Product(
        "M-SH-296", "חולצת כותנה שחורה ארוכה דגמח", "images/black-cargo-cotton.jpg",
        "סגנון צבאי/טקטי מעודכן עם 4 כיסי דגמח פונקציונליים בחזית.",
        1899, Arrays.asList("גברים", "חולצות", "כותנה", "שחור", "Winter", "ארוך", "דגמח", "Streetwear")
    ));

    productService.addProductToDB(new Product(
        "M-SH-297", "חולצת כותנה לבנה קצרה פסים", "images/white-stripe-cotton.jpg",
        "פסי מלחים כחולים קלאסיים על רקע לבן, מראה נקי לחלוטין.",
        1099, Arrays.asList("גברים", "חולצות", "כותנה", "לבן", "Summer", "קצר", "פסים", "Basic")
    ));

    // --- השלמת הפיזור הסטטיסטי למילוי כל קומבינציה אפשרית בשאלון ---
    productService.addProductToDB(new Product(
        "M-SH-298", "חולצת כותנה לבנה ארוכה פסים", "images/white-long-stripe-cotton.jpg",
        "חולצת פופלין יוקרתית למשרד ולעסקים עם פסי סיכה ארוכים.",
        1699, Arrays.asList("גברים", "חולצות", "כותנה", "לבן", "Spring", "ארוך", "פסים", "Office")
    ));

    productService.addProductToDB(new Product(
        "M-SH-299", "חולצת כותנה בז' ארוכה פסים", "images/beige-stripe-cotton.jpg",
        "צבעי חול טבעיים עם פסים לבנים דקים, מראה אירופאי מעודן.",
        1599, Arrays.asList("גברים", "חולצות", "כותנה", "בז'", "Spring", "ארוך", "פסים", "Elegant")
    ));

    productService.addProductToDB(new Product(
        "M-SH-300", "חולצת כותנה ירוקה ארוכה משובצת", "images/green-plaid-cotton.jpg",
        "חולצת משבצות פלאנל ירוק זית כהה מוברש היטב לעונת החורף.",
        1499, Arrays.asList("גברים", "חולצות", "כותנה", "ירוק", "Winter", "ארוך", "משובץ", "Flannel")
    ));

    // 1. חולצת כותנה בסגנון גותי/אלטרנטיבי
    productService.addProductToDB(new Product(
        "M-SH-301", "חולצת טי גותית אפלה", "images/gothic-black-tee.jpg",
        "חולצת טי כבדה עם הדפס גולגולות ואלמנטים מטאליים.",
        1399, Arrays.asList(
            "גברים", "חולצות", 
            "כותנה", "שחור", "Summer", "קצר", "חלק", // תגיות השאלון
            "Gothic", "Alternative", "Oversize"       
        )
    ));

    // 2. חולצת פשתן ידידותית לסביבה (Eco-Friendly)
    productService.addProductToDB(new Product(
        "M-SH-302", "חולצת פשתן אורגנית ירוקה", "images/eco-green-linen.jpg",
        "חולצה המיוצרת בתהליך ירוק ומחומרים ממוחזרים בלבד.",
        1999, Arrays.asList(
            "גברים", "חולצות", 
            "פשתן", "ירוק", "Spring", "ארוך", "מכופתרות", // תגיות השאלון
            "Eco-Friendly", "Organic", "Sustainable"  
        )
    ));

    // 3. חולצת דנים בסגנון אופנוענים (Biker Wear)
    productService.addProductToDB(new Product(
        "M-SH-303", "ג'קט חולצה דנים Biker", "images/biker-denim.jpg",
        "חולצת ג'ינס קשיחה ועמידה עם הגנות כפולות במרפקים ורוכסני מתכת.",
        2499, Arrays.asList(
            "גברים", "חולצות", 
            "דנים", "כחול", "Autumn", "ארוך", "חלק", // תגיות השאלון
            "Biker", "Rugged", "Vintage-Wash"         
        )
    ));

    // 4. סריג צמר מרינו בסגנון מינימליסטי יפני
    productService.addProductToDB(new Product(
        "M-SH-304", "סריג מרינו אצילי בעיצוב יפני", "images/japanese-merino.jpg",
        "חיתוך א-סימטרי נקי בהשראת אופנת רחוב מטוקיו.",
        3499, Arrays.asList(
            "גברים", "חולצות", 
            "צמר מרינו", "לבן", "Winter", "ארוך", "חלק", // תגיות השאלון
            "Japanese-Style", "Avant-Garde", "Zen"     
        )
    ));

    // 5. חולצת טיי-דאי פסיכדלית לפסטיבלים
    productService.addProductToDB(new Product(
        "M-SH-305", "טי-שירט Tie-Dye פסיכדלית", "images/tie-dye-tee.jpg",
        "צביעה ידנית ייחודית לכל פריט. חולצה משוחררת ואופנתית.",
        1199, Arrays.asList(
            "גברים", "חולצות", 
            "כותנה", "צבעוני", "Summer", "קצר", "פרחוני", // תגיות השאלון
            "Tie-Dye", "Psychedelic", "Hippie"          
        )
    ));

    // 6. חולצת סאטן מנומרת לחיי לילה (Retro 80s)
    productService.addProductToDB(new Product(
        "M-SH-306", "חולצת סאטן רטרו מנומרת", "images/leopard-satin.jpg",
        "הדפס נועז ומבריק בהשראת שנות השמונים וחיי הלילה הסוערים.",
        2199, Arrays.asList(
            "גברים", "חולצות", 
            "סאטן", "חום", "Spring", "ארוך", "מכופתרות", // תגיות השאלון
            "Retro-80s", "Disco", "Bold-Print"          
        )
    ));

    // 7. חולצת כותנה טכנולוגית עתידנית (Techwear)
    productService.addProductToDB(new Product(
        "M-SH-307", "חולצת אימון טכנולוגית אפורה", "images/techwear-gray.jpg",
        "בד עתידני דוחה מים וכתמים בעל כיסים נסתרים בלייזר קאט.",
        1699, Arrays.asList(
            "גברים", "חולצות", 
            "כותנה", "אפור", "Summer", "קצר", "דגמח", // תגיות השאלון
            "Techwear", "Waterproof", "Futuristic"   
        )
    ));

    // 8. חולצת פשתן בסגנון סיני מסורתי
    productService.addProductToDB(new Product(
        "M-SH-308", "חולצת פשתן אדומה Grandad", "images/mandarin-red.jpg",
        "צווארון סיני סגור עם כפתורי אריגה מסורתיים ומראה נשפך קליל.",
        1599, Arrays.asList(
            "גברים", "חולצות", 
            "פשתן", "אדום", "Summer", "ארוך", "חלק", // תגיות השאלון
            "Traditional", "Mandarin-Collar", "Orient" 
        )
    ));

    // 9. חולצת דנים קרועה בסגנון פאנק (Distressed Punk)
    productService.addProductToDB(new Product(
        "M-SH-309", "חולצת דנים שחורה משופשפת", "images/punk-denim.jpg",
        "קרעים עדינים בעבודת יד ומראה אסיד-ווש מחוספס במיוחד.",
        2299, Arrays.asList(
            "גברים", "חולצות", 
            "דנים", "שחור", "Winter", "ארוך", "משובץ", // תגיות השאלון
            "Distressed", "Punk", "Heavy-Metal"        
        )
    ));

    // 10. סוודר צמר מרינו בסגנון נורדי כפרי (Cozy Nordic)
    productService.addProductToDB(new Product(
        "M-SH-310", "סוודר מרינו דפוס נורדי", "images/nordic-orange.jpg",
        "סריגה עבה ומחממת בעיצוב פתיתי שלג גאומטריים מסורתיים.",
        2899, Arrays.asList(
            "גברים", "חולצות", 
            "צמר מרינו", "כתום", "Winter", "ארוך", "משובץ", // תגיות השאלון
            "Nordic-Pattern", "Cozy-Vibe", "Heritage"    
        )
    ));

    // 11. חולצת כותנה בגזרת בטן מודרנית (Crop Fit)
    productService.addProductToDB(new Product(
        "M-SH-311", "טי-שירט כותנה קרופ חומה", "images/crop-brown-tee.jpg",
        "גזרה קצרה ורחבה המתאימה למראה ספורטיבי עדכני ונועז.",
        1299, Arrays.asList(
            "גברים", "חולצות", 
            "כותנה", "חום", "Summer", "קצר", "חלק", // תגיות השאלון
            "Crop-Fit", "High-Fashion", "Gen-Z"       
        )
    ));

    // 12. חולצת כותנה בסגנון רודיאו מערב פרוע (Western)
    productService.addProductToDB(new Product(
        "M-SH-312", "חולצת כותנה קאנטרי צהובה", "images/rodeo-yellow.jpg",
        "משבצות גדולות בגווני צהוב ושחור עם רקמה בכתפיים בסגנון טקסס.",
        1799, Arrays.asList(
            "גברים", "חולצות", 
            "כותנה", "צהוב", "Autumn", "ארוך", "משובץ", // תגיות השאלון
            "Rodeo", "Western-Style", "Cowboy"          
        )
    ));
}

private void seedWomensShirts() {
    // 1. חולצת סאטן נשפכת - למראה ערב
    productService.addProductToDB(new Product(
        "W-SH-301", "חולצת סאטן בגזרת מעטפת", "images/satin-wrap.jpg",
        "חולצה אלגנטית ליציאה. בד: סאטן משי סינתטי (Polyester Satin). מפתח וי, קשירה במותן.",
        1899, // 189.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "ירוק ", "Regular-Fit", "סאטן", "Evening", "Night-Out", "יוקרתי")
    ));

    // 2. חולצת קרופ (Crop Top) ריב - בייסיק יומיומי
    productService.addProductToDB(new Product(
        "W-SH-302", "חולצת קרופ Seamless ריב", "images/crop-rib.jpg",
        "חולצת בייסיק צמודה ללא תפרים. בד: מיקרופייבר נמתח. גזרה קצרה מעל הפופיק.",
        699, // 69.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "ורוד ", "Slim-Fit", "מיקרופייבר", "Casual", "Summer", "Basic")
    ));

    // 3. חולצת כפתורים "Boyfriend" - אוברסייז
    productService.addProductToDB(new Product(
        "W-SH-303", "חולצת פופלין Oversize לבנה", "images/poplin-white.jpg",
        "חולצה גדולה וקלילה בסגנון גברי. בד: 100% כותנת פופלין פריכה. מתאימה מעל בגד ים או עם ג'ינס.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Oversize", " כותנה", "Minimalist", "Beachwear", "Office")
    ));

    // 4. חולצת תחרה בוהו-שיק
    productService.addProductToDB(new Product(
        "W-SH-304", "חולצת תחרה רקוקה (Embroidery)", "images/boho-lace.jpg",
        "חולצה רומנטית לחופשה. בד: כותנה עם רקמת חורים (Eyelet). שרוולים תפוחים.",
        2199, // 219.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Regular-Fit", "כותנה ", "Romantic", "Vacation", "Boho")
    ));

    // 5. חולצת שיפון רומנטית - Boho Chic
    productService.addProductToDB(new Product(
        "W-SH-305", "חולצת שיפון שקופה למחצה", "images/chiffon-blouse.jpg",
        "מראה נשי ועדין. בד: 100% פוליאסטר שיפון. שרוולים תפוחים עם סיומת תחרה, הדפס פרחים עדין.",
        2199, // 219.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "Regular-Fit", "פוליאסטר", "Romantic", "Date-Night", "פרחוני")
    ));

    // 6. בגד גוף (Bodysuit) צמוד - מראה נקי
    productService.addProductToDB(new Product(
        "W-SH-306", "בגד גוף Seamless בייסיק", "images/bodysuit.jpg",
        "מחטב ומחמיא. בד: 90% ניילון, 10% אלסטן. סגירת תיקתקים בתחתית, בד סטרץ' חזק.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "בגד גוף", "בז' ", "Slim-Fit", "ניילון-אלסטן", "Basic", "Minimalist", "חלק")
    ));

    // 7. חולצת "סטרפלס" סרוגה - מראה קיץ יוקרתי
    productService.addProductToDB(new Product(
        "W-SH-307", "טופ סטרפלס סרוג ריב", "images/tube-top.jpg",
        "טרנד הקיץ. בד: תערובת כותנה וגומי. גזרה צמודה ללא כתפיות, טקסטורת פסים אנכיים.",
        999, // 99.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "לבן", "Slim-Fit", "כותנה", "Summer", "Vacation", "קליל")
    ));

    // 8. חולצת קטיפה (Velvet) - חורף וערב
    productService.addProductToDB(new Product(
        "W-SH-308", "חולצת קטיפה עם שרוול ארוך", "images/velvet-top.jpg",
        "מראה עשיר ויוקרתי. בד: קטיפה סינתטית רכה. צווארון גולף נמוך, ברק עדין בבד.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "בורדו", "Regular-Fit", "קטיפה", "Evening", "Luxury", "Winter")
    ));

    // 9. חולצת מחוך (Corset Top)
    productService.addProductToDB(new Product(
        "W-SH-309", "טופ מחוך בטקסטורת ז'אקרד", "images/corset-top.jpg",
        "טרנד מסיבות וערב. בד: פוליאסטר קשיח ומחטב. עצמות פנימיות לעיצוב המותן, רוכסן אחורי.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "שחור", "Slim-Fit", "ז'אקרד", "Night-Out", "Sexy", "יוקרתי")
    ));

    // 10. חולצת אוף-שולדר (Off-the-Shoulder)
    productService.addProductToDB(new Product(
        "W-SH-310", "חולצת אוף-שולדר עם כיווצים", "images/off-shoulder.jpg",
        "מראה רומנטי וקליל. בד: 100% ויסקוזה נושמת. סיומת גומי בכתפיים ובשרוולים.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "לבן", "Relaxed-Fit", "ויסקוזה", "Romantic", "Summer", "Boho")
    ));

    // 11. חולצת גולף (Turtleneck) ללא שרוולים
    productService.addProductToDB(new Product(
        "W-SH-311", "סריג גולף דק ללא שרוול", "images/sleeveless-turtle.jpg",
        "מראה אלגנטי ומתוחכם. בד: תערובת כותנה ומודל. צווארון גבוה, בד ריב גמיש.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "אפור", "Slim-Fit", "כותנה", "Office", "Elegant", "Classic")
    ));

    // 12. חולצת מעטפת (Wrap Shirt) מודפסת
    productService.addProductToDB(new Product(
        "W-SH-312", "חולצת מעטפת בהדפס מנומר", "imagesrap-animal.jpg",
        "פריט הצהרה אופנתי. בד: קרפ פוליאסטר נשפך. קשירה צידית, מחשוף וי עמוק.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "מנומר", "Regular-Fit", "קרפ", "Bold", "Evening", "הדפס")
    ));

    // 13. חולצת משי (Silk Blouse) יוקרתית
    productService.addProductToDB(new Product(
        "W-SH-313", "חולצת משי עם עניבת פרפר", "images/silk-blouse.jpg",
        "מראה יוקרתי לעבודה או אירוע. בד: 100% משי טבעי. צווארון גבוה עם קשירת פרפר, שרוולים תפוחים.",
        3499, // 349.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "לבן", "Regular-Fit", "משי", "Luxury", "Office", "יוקרתי")
    ));

    // 14. חולצת קרופ (Crop Top) עם קשירה
    productService.addProductToDB(new Product(
        "W-SH-314", "טופ קרופ עם קשירה קדמית", "images/tie-crop.jpg",
        "טרנד הקיץ. בד: כותנה ופוליאסטר. שרוולים קצרים, קשירה במרכז החזה, מראה צעיר.",
        999, // 99.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "צהוב ", "Slim-Fit", "כותנה", "Summer", "Casual", "צעיר")
    ));

    // 15. חולצת "מחוך" (Corset Style) סרוגה
    productService.addProductToDB(new Product(
        "W-SH-315", "סריג גופייה בגזרת מחוך", "images/knit-corset.jpg",
        "שילוב של נוחות וסטייל. בד: סריג ריב עבה ומחטב. גזרת מחוך ללא עצמות, כתפיות דקות.",
        1599, // 159.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "שחור", "Slim-Fit", "סריג", "Night-Out", "Sexy", "חלק")
    ));

    // 16. חולצת בייסיק שרוול ארוך (Second Skin)
    productService.addProductToDB(new Product(
        "W-SH-316", "חולצת בייסיק Second Skin", "images/basic-long.jpg",
        "פריט חובה לשכבות. בד: מודל ואלסטן רך במיוחד. בד דק ונצמד כמו עור שני.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "חום ", "Slim-Fit", "מודל-אלסטן", "Basic", "Minimalist", "חלק")
    ));

    // 17. חולצת פופלין עם שרוולים נפוחים
    productService.addProductToDB(new Product(
        "W-SH-317", "חולצת פופלין שרוול נפוח (Puff)", "images/puff-sleeve.jpg",
        "מראה דרמטי ומעוצב. בד: 100% כותנת פופלין. שרוולי בלון נפוחים, מותן מודגשת.",
        2299, // 229.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "תכלת", "Regular-Fit", " כותנה", "Modern", "Office", "פסים")
    ));

    // 18. גופיית ספורט עם חזייה פנימית
    productService.addProductToDB(new Product(
        "W-SH-318", "גופיית אימון עם Built-in Bra", "images/active-bra.jpg",
        "תמיכה ונוחות באימון. בד: ניילון ממוחזר ואלסטן. בד מחטב, מנדף זיעה, גב פתוח.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "סגול ", "Athletic-Fit", "ניילון", "Gym", "Yoga", "ספורט")
    ));

    // 19. חולצת תחרה (Lace) שקופה
    productService.addProductToDB(new Product(
        "W-SH-319", "חולצת תחרה שחורה רומנטית", "images/lace-shirt.jpg",
        "מראה ערב מתוחכם. בד: תחרה סינתטית עדינה. דוגמת פרחים, שקופה (דורשת גופיה מתחת).",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "שחור", "Regular-Fit", "תחרה", "Evening", "Romantic", "יוקרתי")
    ));

    // 20. חולצת "טרנץ'" (Trench Style) ללא שרוולים
    productService.addProductToDB(new Product(
        "W-SH-320", "חולצת ג'קט ללא שרוול", "images/vest-trench.jpg",
        "מראה מחויט ומודרני. בד: תערובת כותנה וגברדין. צווארון דש רחב, כפתרה כפולה, חגורת מותן.",
        2599, // 259.90 NIS
        Arrays.asList("נשים", "חולצות", "אלגנט", "בז' ", "Tailored-Fit", "גברדין", "Minimalist", "Office", "Classic")
    ));

    // 21. סוודר "קייבל" קרופ - Trendy
    productService.addProductToDB(new Product(
        "W-SH-321", "סריג קייבל קרופ שמנת", "images/cable-knit.jpg",
        "סריגת צמות קלאסית בגזרה קצרה. בד: תערובת כותנה רכה. שרוולים נפוחים מעט.",
        2199, // 219.90 NIS
        Arrays.asList("נשים", "חולצות", "סריגים", "לבן", "Crop-Fit", "כותנה", "Winter", "Trendy", "צמות")
    ));

    // 22. חולצת סאטן קשירה - Night Out
    productService.addProductToDB(new Product(
        "W-SH-322", "טופ סאטן עם קשירה בצוואר", "images/satin-tie.jpg",
        "מראה ערב סקסי ויוקרתי. בד: 100% סאטן משי סינתטי. גב פתוח למחצה, קשירת סרט רחבה.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "שחור", "Slim-Fit", "סאטן", "Night-Out", "Luxury", "חלק")
    ));

    // 23. חולצת "סמאק" (Smocked) - Boho
    productService.addProductToDB(new Product(
        "W-SH-323", "חולצת סמאק עם הדפס פרחים", "images/smocked-floral.jpg",
        "מראה רומנטי לחופשה. בד: ויסקוזה קלילה. כיווצי גומי בחזה (Smocking), שרוולי מלמלה.",
        1599, // 159.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "פרחוני", "Regular-Fit", "ויסקוזה", "Boho", "Romantic", "Summer")
    ));

    // 24. גופיית ספורט "מצלבה" - Active
    productService.addProductToDB(new Product(
        "W-SH-324", "גופיית אימון Cross-Back", "images/active-cross.jpg",
        "תמיכה גבוהה ועיצוב מודרני. בד: ניילון ואלסטן מחטב. כתפיות דקות מוצלבות בגב.",
        1299, // 129.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "כחול ", "Athletic-Fit", "ניילון", "Gym", "Active", "ספורט")
    ));

    // 25. חולצת "פיטר פן" - Vintage
    productService.addProductToDB(new Product(
        "W-SH-325", "חולצת פופלין צווארון בובה", "images/peter-pan.jpg",
        "מראה רטרו מתוחכם. בד: כותנה פופלין. צווארון רחב עם רקמת תחרה, כפתורי פנינה.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Regular-Fit", "כותנה", "Vintage", "Elegant", "חלק")
    ));

    // 26. סריג גולף ללא שרוול - Modern Office
    productService.addProductToDB(new Product(
        "W-SH-326", "טופ גולף סרוג ללא שרוול", "images/sleeveless-turtle.jpg",
        "מתאים מתחת לג'קט או כפריט עצמאי. בד: כותנה ו-מודל. גזרה צמודה ונוחה.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "אפור", "Slim-Fit", "כותנה", "Office", "Minimalist", "Basic")
    ));

    // 27. חולצת "באטו" (צווארון סירה) - Classic
    productService.addProductToDB(new Product(
        "W-SH-327", "חולצת פסים צווארון סירה", "images/boat-neck.jpg",
        "מראה פריזאי קלאסי. בד: כותנה עבה. פסי רוחב כחול-לבן, שרוול 3/4.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "פסים", "Regular-Fit", "כותנה", "Classic", "Parisian", "יומיום")
    ));

    // 28. טופ "סטרפלס" סרוג - Sexy Summer
    productService.addProductToDB(new Product(
        "W-SH-328", "טופ סטרפלס ריב צמוד", "images/strapless-rib.jpg",
        "גזרה נקייה ומחמיאה. בד: סריג ריב אלסטי. ללא כתפיות, פס סיליקון פנימי למניעת החלקה.",
        999, // 99.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "בז' ", "Slim-Fit", "סריג", "Summer", "Minimalist", "חלק")
    ));

    // 29. חולצת רשת (Mesh) למסיבות
    productService.addProductToDB(new Product(
        "W-SH-329", "טופ רשת מודפס שקוף", "images/mesh-top.jpg",
        "מראה ערב נועז. בד: 100% ניילון רשת אלסטי. הדפס פסיכדלי בגווני סגול, צווארון גבוה.",
        1299, // 129.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "סגול", "Slim-Fit", "Mesh", "Night-Out", "Party", "Bold")
    ));

    // 30. טופ מחוך (Corset Top)
    productService.addProductToDB(new Product(
        "W-SH-330", "טופ מחוך בטקסטורת דנים", "images/corset-denim.jpg",
        "מראה מחטב ומעוצב. בד: דנים כותנה עם אלסטן. עצמות פנימיות, סגירת רוכסן אחורי כסוף.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "כחול ", "Slim-Fit", "דנים", "Sexy", "Night-Out", "Trendy")
    ));

    // 31. חולצת פופלין שרוול נפוח
    productService.addProductToDB(new Product(
        "W-SH-331", "חולצת פופלין שרוול Puff", "images/puff-poplin.jpg",
        "מראה דרמטי ומחויט. בד: 100% כותנת פופלין פריכה. שרוולים נפוחים מהכתף, חזית כפתורים.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Regular-Fit", "כותנה", "Modern", "Office", "Elegant")
    ));

    // 32. גופיית קשירה קדמית
    productService.addProductToDB(new Product(
        "W-SH-332", "גופיית פשתן קשירה מקדימה", "images/linen-tie.jpg",
        "מושלמת לקיץ הישראלי. בד: פשתן מכובס. שתי קשירות סרט בחזית, מפתח וי עמוק.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "צהוב ", "Relaxed-Fit", "פשתן", "Summer", "Vacation", "קליל")
    ));

    // 33. חולצת עטלף (Batwing)
    productService.addProductToDB(new Product(
        "W-SH-333", "חולצת סריג עטלף נשפכת", "images/batwing-top.jpg",
        "גזרה נוחה ומחמיאה לכל גוף. בד: ויסקוזה ופוליאסטר. שרוולים רחבים המתהדקים בפרק כף היד.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "שחור", "Oversize", "Viscose", "Casual", "Comfort", "חלק")
    ));

    // 34. סוודר קרופ (Crop Sweater)
    productService.addProductToDB(new Product(
        "W-SH-334", "סריג קרופ בטקסטורת ריב", "images/crop-knit.jpg",
        "מראה צעיר ועדכני. בד: אקריליק רך במיוחד. גזרה קצרה מעל המותן, צווארון גולף נמוך.",
        1899, // 189.90 NIS
        Arrays.asList("נשים", "חולצות", "סריגים", "ורוד ", "Crop-Fit", "Acrylic", "Trendy", "Winter", "Streetwear")
    ));

    // 35. חולצת לורקס מנצנצת
    productService.addProductToDB(new Product(
        "W-SH-335", "טופ לורקס מנצנץ לערב", "images/lurex-top.jpg",
        "מראה מטאלי זוהר. בד: תערובת פוליאסטר וחוטי מתכת (Lurex). שרוולים קצרים, בד נמתח.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "אפור", "Slim-Fit", "Lurex", "Night-Out", "Party", "Metallic")
    ));

    // 36. חולצת סמאק (Smocked)
    productService.addProductToDB(new Product(
        "W-SH-336", "חולצת סמאק פרחונית", "images/smocked-floral.jpg",
        "מראה רומנטי וכפרי. בד: ויסקוזה דקה. כיווצי גומי (Smocking) לאורך כל הגוף, שרוולים קצרים.",
        1599, // 159.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "פרחוני", "Slim-Fit", "Viscose", "Romantic", "Boho", "Summer")
    ));

    // 37. טופ אסימטרי (One Shoulder)
    productService.addProductToDB(new Product(
        "W-SH-337", "טופ כתף אחת מחטב", "images/one-shoulder.jpg",
        "מראה מודרני ונקי. בד: 90% ניילון, 10% אלסטן. בד כפול למניעת שקיפות, גזרה צמודה.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "בז' ", "Slim-Fit", "Nylon-Spandex", "Minimalist", "Night-Out", "Modern")
    ));

    // 38. חולצת פליז חמה
    productService.addProductToDB(new Product(
        "W-SH-338", "חולצת פליז Half-Zip", "images/fleece-top.jpg",
        "לפעילות חוץ או לבית. בד: פליז תרמי רך. רוכסן בחצי הגובה, כיס קנגורו, מחמם במיוחד.",
        1799, // 179.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "ירוק ", "Regular-Fit", "Fleece", "Winter", "Active", "Comfort")
    ));

    // 39. חולצת קשירה קדמית (Front Tie)
    productService.addProductToDB(new Product(
        "W-SH-339", "חולצת קרופ קשירה פרפר", "images/butterfly-tie.jpg",
        "טרנד שנות ה-2000. בד: שיפון פוליאסטר. קשירה במרכז החזה, שרוולי פעמון מתרחבים.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "לבן", "Regular-Fit", "Chiffon", "Y2K", "Trendy", "Summer")
    ));

    // 40. חולצת ג'ינס קלאסית
    productService.addProductToDB(new Product(
        "W-SH-340", "חולצת דנים Western נשים", "images/denim-shirt2.jpg",
        "פריט נצחי. בד: כותנה דנים שטופה. תיקתקים ממתכת, שני כיסי חזה, גזרה מעט צמודה.",
        1999, // 199.90 NIS
        Arrays.asList("נשים", "חולצות", "ג'ינס", "כחול ", "Regular-Fit", "דנים", "Casual", "Classic", "חלק")
    ));

    // 41. חולצת סאטן קשירה (Wrap)
    productService.addProductToDB(new Product(
        "W-SH-341", "חולצת סאטן מעטפת יוקרתית", "images/satin-wrap2",
        "מראה ערב נשי ונשפך. בד: סאטן משי סינתטי ברמה גבוהה. קשירה במותן, מחשוף וי מחמיא.",
        2299, // 229.90 NIS
        Arrays.asList("נשים", "חולצות", "ערב", "כחול ", "Regular-Fit", "סאטן", "Evening", "Night-Out", "Luxury")
    ));

    // 42. טופ קרופ מנדף זיעה
    productService.addProductToDB(new Product(
        "W-SH-342", "טופ אימון Crop Performance", "images/active-crop-top",
        "מיועד ליוגה ואימוני כוח. בד: ניילון ואלסטן Seamless. בד מחטב ונושם עם חורים לאוורור מתחת לחזה.",
        1399, // 139.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "ורוד ", "Slim-Fit", "Seamless", "Active", "Gym", "Sport")
    ));

    // 43. חולצת "ויקטוריאנית" עם תחרה
    productService.addProductToDB(new Product(
        "W-SH-343", "חולצת תחרה צווארון גבוה", "images/victorian-lace",
        "מראה רטרו רומנטי. בד: כותנה משולבת עם תחרה עדינה. כפתורי פנינה קטנים, שרוולים נפוחים.",
        2599, // 259.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Regular-Fit", "כותנה", "Romantic", "Vintage", "Elegant")
    ));

    // 44. גופיית "הולטר" (Halter Neck)
    productService.addProductToDB(new Product(
        "W-SH-344", "טופ הולטר סרוג גב פתוח", "images/halter-knit",
        "מראה קיץ נועז. בד: סריג ויסקוזה קריר. קשירה מאחורי הצוואר, חושפת כתפיים וגב.",
        1199, // 119.90 NIS
        Arrays.asList("נשים", "חולצות", "גופיות", "שחור", "Slim-Fit", "Viscose", "Summer", "Night-Out", "Sexy")
    ));

    // 45. סוודר "צ'אנקי" (Chunky Knit)
    productService.addProductToDB(new Product(
        "W-SH-345", "סוודר צ'אנקי Oversize כבד", "images/chunky-sweater",
        "להרגיש בתוך ענן. בד: 100% אקריליק רך בסריגה עבה מאוד. צווארון גולף ענק, גזרה רחבה במיוחד.",
        2799, // 279.90 NIS
        Arrays.asList("נשים", "חולצות", "סריגים", "בז' '", "Oversize", "Chunky-Knit", "Winter", "Warm", "Comfort")
    ));

    // 46. חולצת פשתן קרופ (Crop Linen)
    productService.addProductToDB(new Product(
        "W-SH-346", "חולצת פשתן קצרה מעוצבת", "images/crop-linen",
        "קרירה ומתוחכמת. בד: פשתן איכותי. כפתורים גדולים מצופים בד, שרוול קצר מתקפל.",
        1699, // 169.90 NIS
        Arrays.asList("נשים", "חולצות", "מכופתרות", "ירוק ", "Crop-Fit", "פשתן", "Summer", "Minimalist", "Vacation")
    ));

    // 47. חולצת בייסיק צווארון מרובע
    productService.addProductToDB(new Product(
        "W-SH-347", "טופ ריב צווארון מרובע (Square)", "images/square-neck",
        "גזרה מחמיאה לקו הצוואר. בד: 95% כותנה, 5% לייקרה. בד ריב כפול למראה נקי.",
        899, // 89.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "לבן", "Slim-Fit", "Ribbed", "Basic", "Minimalist", "יומיום")
    ));

    // 48. חולצת "מעיל" (Shacket) נשים
    productService.addProductToDB(new Product(
        "W-SH-348", "ג'קט חולצה פלאנל ורוד", "images/pink-shacket",
        "שילוב מושלם בין חולצה לג'קט. בד: פלאנל כותנה רך. משבצות ורוד-אפור, כיסי צד גדולים.",
        2399, // 239.90 NIS
        Arrays.asList("נשים", "חולצות", "ארוך", "משובץ", "Oversize", "Flannel", "Autumn", "Casual", "Streetwear")
    ));

    // 49. בגד גוף (Bodysuit) א-סימטרי
    productService.addProductToDB(new Product(
        "W-SH-349", "בגד גוף כתף אחת מחטב", "images/asymmetric-body",
        "מראה מודרני וערבי. בד: ניילון ואלסטן בעל ברק עדין. גזרה צמודה מאוד, סגירת תיקתקים.",
        1499, // 149.90 NIS
        Arrays.asList("נשים", "חולצות", "בגד גוף", "בורדו", "Slim-Fit", "Nylon-Spandex", "Modern", "Night-Out", "Trendy")
    ));

    // 50. חולצת "סמאק" (Smocked) קרופ
    productService.addProductToDB(new Product(
        "W-SH-350", "טופ סמאק פרחוני קצר", "images/floral-smock",
        "מראה צעיר ורומנטי. בד: ויסקוזה נשפכת. גומי מכווץ לאורך כל הגוף, שרוולי מלמלה תפוחים.",
        1299, // 129.90 NIS
        Arrays.asList("נשים", "חולצות", "קצר", "פרחוני", "Slim-Fit", "Viscose", "Romantic", "Summer", "Boho")
    ));

    // === חולצות כותנה (Cotton) ===
    productService.addProductToDB(new Product(
        "W-SH-351", "גופיית קרופ כותנה שחורה", "images/black-grunge-crop.jpg",
        "חולצת בייסיק קצרה בעיצוב קשוח ואופנתי לחובבי מראה אלטרנטיבי.",
        1199, Arrays.asList("נשים", "חולצות", "כותנה", "שחור", "Summer", "קצר", "חלק", "Streetwear", "Grunge", "Y2K")
    ));

    productService.addProductToDB(new Product(
        "W-SH-352", "חולצת כותנה לבנה רומנטית ארוכה", "images/white-puff-cotton.jpg",
        "חולצה מעוצבת עם שרוולי בלון תפוחים וצווארון וי עדין.",
        1899, Arrays.asList("נשים", "חולצות", "כותנה", "לבן", "Spring", "ארוך", "חלק", "Cottagecore", "Vintage", "Elegant")
    ));

    productService.addProductToDB(new Product(
        "W-SH-353", "חולצת פסים כחולה Boyfriend", "images/blue-stripe-boyfriend.jpg",
        "גזרה רחבה ומשוחררת במראה גברי קלאסי, מתאימה במיוחד לעבודה ולמשרד.",
        1599, Arrays.asList("נשים", "חולצות", "כותנה", "כחול", "Spring", "ארוך", "פסים", "Oversize", "Boyfriend-Fit", "Office")
    ));

    productService.addProductToDB(new Product(
        "W-SH-354", "חולצת כותנה משובצת אדומה", "images/red-flannel-women.jpg",
        "חולצת פלאנל רכה ומחממת בגזרת אוברסייז למראה יומיומי זרוק.",
        1499, Arrays.asList("נשים", "חולצות", "כותנה", "אדום", "Autumn", "ארוך", "משובץ", "Grunge", "90s", "Flannel")
    ));

    productService.addProductToDB(new Product(
        "W-SH-355", "טי-שירט כותנה צהובה פרחונית", "images/yellow-retro-floral.jpg",
        "הדפס פרחי רטרו שמח וצבעוני בחזית הבגד, כותנה סרוקה ונושמת.",
        999, Arrays.asList("נשים", "חולצות", "כותנה", "צהוב", "Summer", "קצר", "פרחוני", "Retro", "Boho", "Bright")
    ));

    productService.addProductToDB(new Product(
        "W-SH-356", "טופ כותנה ירוק בסגנון דגמח", "images/green-utility-crop.jpg",
        "חולצה קצרה בעלת אלמנטים צבאיים וכיסי חזה מובלטים.",
        1299, Arrays.asList("נשים", "חולצות", "כותנה", "ירוק", "Autumn", "קצר", "דגמח", "Techwear", "Utility", "Streetwear")
    ));

    productService.addProductToDB(new Product(
        "W-SH-357", "חולצת כותנה אפורה מכופתרת קלאסית", "images/gray-office-cotton.jpg",
        "גזרה מחויטת נקייה ועדינה, מתאימה לשילוב קל עם מכנסיים רשמיים.",
        1699, Arrays.asList("נשים", "חולצות", "כותנה", "אפור", "Spring", "ארוך", "מכופתרות", "Office", "Minimalist", "Basic")
    ));

    productService.addProductToDB(new Product(
        "W-SH-358", "חולצת פולו כותנה צבעונית פסים", "images/colorful-stripe-polo.jpg",
        "מראה ספורטיבי מעודכן עם פסי רוחב בולטים וצווארון קשיח אופנתי.",
        1399, Arrays.asList("נשים", "חולצות", "כותנה", "צבעוני", "Summer", "קצר", "פסים", "Retro", "Preppy", "Sporty")
    ));

    // === חולצות פשתן (Linen) ===
    productService.addProductToDB(new Product(
        "W-SH-359", "טוניקת פשתן בז' ארוכה חלקה", "images/beige-linen-tunic.jpg",
        "גזרה ארוכה ונשפכת מחומרים טבעיים ואקולוגיים, מושלמת לחופשות קיץ.",
        1999, Arrays.asList("נשים", "חולצות", "פשתן", "בז'", "Summer", "ארוך", "חלק", "Boho", "Eco-Friendly", "Resort")
    ));

    productService.addProductToDB(new Product(
        "W-SH-360", "חולצת פשתן לבנה קצרה מכופתרת", "images/white-short-linen.jpg",
        "מראה קיצי פריך ואוורירי, כפתורי עץ טבעיים למראה טבעי ונקי.",
        1499, Arrays.asList("נשים", "חולצות", "פשתן", "לבן", "Summer", "קצר", "מכופתרות", "Minimalist", "Beachwear", "Classic")
    ));

    productService.addProductToDB(new Product(
        "W-SH-361", "חולצת פשתן חומה ארוכה דגמח", "images/brown-safari-linen.jpg",
        "עיצוב ספארי קלאסי בעל כיסים שימושיים בחזית וחגורת קשירה תואמת במותן.",
        2199, Arrays.asList("נשים", "חולצות", "פשתן", "חום", "Autumn", "ארוך", "דגמח", "Safari", "Utility", "Adventure")
    ));

    productService.addProductToDB(new Product(
        "W-SH-362", "חולצת פשתן ורודה קצרה פרחונית", "images/pink-tropical-linen.jpg",
        "הדפס בוטני עדין בגווני פסטל מרעננים, מגע נושם ונוח לאורך כל היום.",
        1599, Arrays.asList("נשים", "חולצות", "פשתן", "ורוד", "Summer", "קצר", "פרחוני", "Romantic", "Tropical", "Fresh")
    ));

    productService.addProductToDB(new Product(
        "W-SH-363", "בלאוזת פשתן כחולה ארוכה פסים", "images/blue-linen-stripe.jpg",
        "פסי אורך דקים בסגנון ים-תיכוני נינוח, אידיאלית לעונות המעבר.",
        1699, Arrays.asList("נשים", "חולצות", "פשתן", "כחול", "Spring", "ארוך", "פסים", "Mediterranean", "Casual", "Light")
    ));

    productService.addProductToDB(new Product(
        "W-SH-364", "חולצת פשתן שחורה ארוכה מכופתרת", "images/black-urban-linen.jpg",
        "לוק אורבני מודרני מבד נשפך ונוח, מתאימה ללבוש יומיומי מתוחכם.",
        1799, Arrays.asList("נשים", "חולצות", "פשתן", "שחור", "Summer", "ארוך", "מכופתרות", "Chic", "Urban", "Cool")
    ));

    productService.addProductToDB(new Product(
        "W-SH-365", "טופ פשתן כתום קצר חלק", "images/orange-sunset-linen.jpg",
        "צבע כתום תוסע בעל גזרה קצרה מעוגלת, מעולה לפסטיבלים ואירועי קיץ.",
        1199, Arrays.asList("נשים", "חולצות", "פשתן", "כתום", "Summer", "קצר", "חלק", "Vibrant", "Sunset", "Festival")
    ));

    productService.addProductToDB(new Product(
        "W-SH-366", "חולצת פשתן צבעונית ארוכה משובצת", "images/colorful-check-linen.jpg",
        "שילוב צבעים אמנותי וגאומטרי ליצירת לוק ייחודי ומושך עין.",
        1799, Arrays.asList("נשים", "חולצות", "פשתן", "צבעוני", "Spring", "ארוך", "משובץ", "Playful", "Artistic", "Quirky")
    ));

    // === חולצות סאטן (Satin) ===
    productService.addProductToDB(new Product(
        "W-SH-367", "טופ סאטן אדום קצר חלק", "images/red-glam-satin.jpg",
        "צווארון נשפך (Cowl Neck) בעל ברק עמוק ויוקרתי המותאם במיוחד לחיי הלילה.",
        1699, Arrays.asList("נשים", "חולצות", "סאטן", "אדום", "Winter", "קצר", "חלק", "Glam", "Sexy", "Evening")
    ));

    productService.addProductToDB(new Product(
        "W-SH-368", "חולצת סאטן שחורה ארוכה מכופתרת", "images/black-luxury-satin.jpg",
        "חזית כפתורים נסתרת, מראה חלק ויוקרתי המתאים לאירועים רשמיים ובילוי ערב.",
        2299, Arrays.asList("נשים", "חולצות", "סאטן", "שחור", "Autumn", "ארוך", "מכופתרות", "Gothic-Luxury", "Night-Out", "Formal")
    ));

    productService.addProductToDB(new Product(
        "W-SH-369", "חולצת סאטן ירוקה ארוכה פרחונית", "images/green-baroque-satin.jpg",
        "הדפס בארוק עשיר בגווני ירוק בקבוק וזהב, שרוולים נפוחים ואלגנטיים.",
        2499, Arrays.asList("נשים", "חולצות", "סאטן", "ירוק", "Spring", "ארוך", "פרחוני", "Baroque", "Vintage-Glam", "Sophisticated")
    ));

    productService.addProductToDB(new Product(
        "W-SH-370", "חולצת סאטן ורודה ארוכה פסים", "images/pink-pajama-satin.jpg",
        "עיצוב בהשראת בגדי פנאי יוקרתיים עם פסי אורך דקים ומגע רך ומלטף.",
        1899, Arrays.asList("נשים", "חולצות", "סאטן", "ורוד", "Spring", "ארוך", "פסים", "Loungewear", "Boudoir", "Chic")
    ));

    productService.addProductToDB(new Product(
        "W-SH-371", "טופ סאטן כתום קצר מכופתר", "images/orange-cocktail-satin.jpg",
        "מחשוף וי נועז עם קשירה אסימטרית, מותאמת למסיבות קוקטייל קיציות.",
        1599, Arrays.asList("נשים", "חולצות", "סאטן", "כתום", "Summer", "קצר", "מכופתרות", "Party", "Cocktail", "Daring")
    ));

    productService.addProductToDB(new Product(
        "W-SH-372", "חולצת סאטן אפורה ארוכה חלקה", "images/silver-metallic-satin.jpg",
        "גוון כסף מטאלי נשפך ודרמטי המעניק מראה עתידני, מתוחכם וחדשני.",
        2399, Arrays.asList("נשים", "חולצות", "סאטן", "אפור", "Winter", "ארוך", "חלק", "Futuristic-Chic", "Red-Carpet", "Metallic")
    ));

    productService.addProductToDB(new Product(
        "W-SH-373", "חולצת סאטן צהובה קצרה חלקה", "images/yellow-dainty-satin.jpg",
        "גזרה קומפקטית עם שרוולי פאף קטנים, מתאימה לאירועים חגיגיים ביום ובערב.",
        1499, Arrays.asList("נשים", "חולצות", "סאטן", "צהוב", "Summer", "קצר", "חלק", "Joyful", "Dainty", "Celebration")
    ));

    productService.addProductToDB(new Product(
        "W-SH-374", "ג'קט חולצה סאטן כחול ארוך דגמח", "images/blue-utility-satin.jpg",
        "שילוב חומרים לא שגרתי של בד סאטן עדין בגזרת דגמח משוחררת ואורבנית.",
        2199, Arrays.asList("נשים", "חולצות", "סאטן", "כחול", "Autumn", "ארוך", "דגמח", "Street-Luxury", "Avant-Garde", "Modern")
    ));

    // === חולצות דנים / ג'ינס (Denim) ===
    productService.addProductToDB(new Product(
        "W-SH-375", "מחוך דנים כחול קצר חלק", "images/blue-denim-corset.jpg",
        "טופ מחוך מחטב מבד ג'ינס אלסטי, כפתורי מתכת בולטים לאורך החזית.",
        1699, Arrays.asList("נשים", "חולצות", "דנים", "כחול", "Summer", "קצר", "חלק", "Y2K", "Cowgirl", "Trendy")
    ));

    productService.addProductToDB(new Product(
        "W-SH-376", "חולצת דנים שחורה ארוכה דגמח", "images/black-punk-denim.jpg",
        "גזרת ג'קט חולצה עבה עם שטיפת וינטג' כהה, קרעים עדינים וכיסים מרובים.",
        2299, Arrays.asList("נשים", "חולצות", "דנים", "שחור", "Winter", "ארוך", "דגמח", "Punk", "Distressed", "Grunge")
    ));

    productService.addProductToDB(new Product(
        "W-SH-377", "חולצת דנים לבנה ארוכה מכופתרת", "images/white-street-denim.jpg",
        "בד ג'ינס לבן פריך עם תיקוני בד (Patches) מעוצבים בסגנון אופנת רחוב נועזת.",
        1999, Arrays.asList("נשים", "חולצות", "דנים", "לבן", "Spring", "ארוך", "מכופתרות", "Custom", "Edgy", "Streetwear")
    ));

    productService.addProductToDB(new Product(
        "W-SH-378", "חולצת דנים אפורה ארוכה חלקה", "images/gray-acid-denim.jpg",
        "שטיפת אסיד חזקה בסגנון שנות ה-90, בד קשיח ועמיד ללוק מחוספס ויומיומי.",
        1899, Arrays.asList("נשים", "חולצות", "דנים", "אפור", "Autumn", "ארוך", "חלק", "90s-Rock", "Vintage-Wash", "Casual")
    ));

    productService.addProductToDB(new Product(
        "W-SH-379", "חולצת דנים אדומה ארוכה פרחונית", "images/red-western-denim.jpg",
        "צבע בורדו עמוק משולב עם רקמת פרחים צבעונית בכתפיים בסגנון מערב פרוע.",
        2099, Arrays.asList("נשים", "חולצות", "דנים", "אדום", "Autumn", "ארוך", "פרחוני", "Western", "Boho", "Artisan")
    ));

    productService.addProductToDB(new Product(
        "W-SH-380", "ווסט דנים כחול קצר פסים", "images/blue-nautical-denim.jpg",
        "גופיית ג'ינס ללא שרוולים בדוגמת פסי סיכה לבנים עדינים, מראה קיצי וצעיר.",
        1399, Arrays.asList("נשים", "חולצות", "דנים", "כחול", "Summer", "קצר", "פסים", "Retro", "Nautical", "Cute")
    ));

    productService.addProductToDB(new Product(
        "W-SH-381", "חולצת דנים חומה ארוכה משובצת", "images/brown-cargo-denim2.jpg",
        "בד דנים חום עבה במיוחד בעיצוב משבצות מטושטש, משמשת כשכבה עליונה בחורף.",
        2399, Arrays.asList("נשים", "חולצות", "דנים", "חום", "Winter", "ארוך", "משובץ", "Workwear", "Lumberjack", "Heavy")
    ));

    productService.addProductToDB(new Product(
        "W-SH-382", "חולצת דנים צבעונית ארוכה משובצת", "images/patchwork-denim.jpg",
        "עיצוב טלאים (Patchwork) צבעוני ונועז המשלב כמה סוגי ג'ינס ממוחזרים.",
        2599, Arrays.asList("נשים", "חולצות", "דנים", "צבעוני", "Spring", "ארוך", "משובץ", "Patchwork", "90s-HipHop", "Bold")
    ));

    // === סריגי צמר מרינו (Merino Wool) ===
    productService.addProductToDB(new Product(
        "W-SH-383", "סוודר צמר מרינו שחור ארוך חלק", "images/black-scandi-wool.jpg",
        "סריגת ריב צפופה ומחממת בסגנון סקנדינבי נקי המעניק בידוד תרמי מושלם.",
        2999, Arrays.asList("נשים", "חולצות", "צמר מרינו", "שחור", "Winter", "ארוך", "חלק", "Minimalist", "Scandinavian", "Cozy")
    ));

    productService.addProductToDB(new Product(
        "W-SH-384", "סוודר צמר מרינו אפור ארוך פסים", "images/gray-preppy-wool.jpg",
        "צווארון וי עמוק בעיצוב פסים אקדמי, מתאים מעל חולצה מכופתרת למראה משרדי קלאסי.",
        2799, Arrays.asList("נשים", "חולצות", "צמר מרינו", "אפור", "Winter", "ארוך", "פסים", "Preppy", "Classic", "Office")
    ));

    productService.addProductToDB(new Product(
        "W-SH-385", "סריג צמר מרינו בז' קצר חלק", "images/beige-quiet-luxury.jpg",
        "סריג דק בעל צווארון מוק-נק נמוך ושרוול מרפק, משדר מראה יוקרתי ושקט.",
        3199, Arrays.asList("נשים", "חולצות", "צמר מרינו", "בז'", "Autumn", "קצר", "חלק", "Quiet-Luxury", "Parisian", "Chic")
    ));

    productService.addProductToDB(new Product(
        "W-SH-386", "ווסט צמר מרינו אדום ארוך משובץ", "images/red-vintage-wool.jpg",
        "סריג ללא שרוולים בדוגמת משבצות וינטג' המעניק נפח ועניין ללבוש השכבות.",
        2499, Arrays.asList("נשים", "חולצות", "צמר מרינו", "אדום", "Winter", "ארוך", "משובץ", "Academic", "Vintage", "Grandmacore")
    ));

    productService.addProductToDB(new Product(
        "W-SH-387", "קרדיגן צמר מרינו כחול ארוך פרחוני", "images/blue-sweet-wool.jpg",
        "סוודר כפתורים פתוח עם רקמת פרחי סחלבים בעבודת יד למראה עדין ומתוק.",
        2899, Arrays.asList("נשים", "חולצות", "צמר מרינו", "כחול", "Winter", "ארוך", "פרחוני", "Cottagecore", "Handmade-Look", "Sweet")
    ));

    productService.addProductToDB(new Product(
        "W-SH-388", "סריג פולו צמר מרינו צהוב ארוך מכופתר", "images/yellow-70s-wool.jpg",
        "עיצוב בהשראת שנות ה-70 עם צווארון פולו ארוך וכפתרה מלאה בגוון חרדל חם.",
        2699, Arrays.asList("נשים", "חולצות", "צמר מרינו", "צהוב", "Autumn", "ארוך", "מכופתרות", "Retro-70s", "Bright", "Trendy")
    ));

    productService.addProductToDB(new Product(
        "W-SH-389", "סוודר צמר מרינו ירוק ארוך דגמח", "images/green-utility-wool.jpg",
        "סריג צבאי מחוספס ועבה במיוחד הכולל תוספת כיסי בד ארוגים על הזרועות.",
        2999, Arrays.asList("נשים", "חולצות", "צמר מרינו", "ירוק", "Winter", "ארוך", "דגמח", "Military-Chic", "Urban-Explorer", "Functional")
    ));

    productService.addProductToDB(new Product(
        "W-SH-390", "חולצת צמר מרינו ורודה ארוכה פסים", "images/pink-soft-wool.jpg",
        "סריגה דקה ועדינה במיוחד בגווני ורוד עתיק ולבן, מגע רך ומלטף שאינו מגרד.",
        2599, Arrays.asList("נשים", "חולצות", "צמר מרינו", "ורוד", "Spring", "ארוך", "פסים", "Dainty", "Soft-Girl", "Minimalist")
    ));
}
private void seedMensPants() 
{
    // 1. ג'ינס דנים קלאסי - מתאים ל-Casual/Autumn
    productService.addProductToDB(new Product(
        "M-PA-301", "ג'ינס Selvedge Denim", "images/bluejeans",
        "ג'ינס איכותי ועמיד. בד: דנים 14oz. גזרת Straight, חמישה כיסים.",
        2499, // 249.90 ₪
        Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Classic", "דנים", "Casual", "Autumn", "חלק")
    ));

    // 2. מכנסי צ'ינו Slim-Fit - מתאים ל-Office/Elegant
    productService.addProductToDB(new Product(
        "M-PA-302", "מכנסי צ'ינו Stretch", "images/chinos",
        "מכנסיים מחויטים למשרד. בד: 98% כותנה, 2% לייקרה. גזרה צמודה ומחמיאה.",
        1999, // 199.90 ₪
        Arrays.asList("גברים", "מכנסיים", "ארוך", "בז'", "Slim-Fit", "כותנה", "Elegant", "Office", "Spring-Essentials", "חלק")
    ));

    // 3. שורטס פשתן קלילים - מתאים ל-Vacation/Summer
    productService.addProductToDB(new Product(
        "M-PA-303", "שורטס פשתן Cuban Style", "images/shortslinen",
        "מכנסיים קצרים ואווריריים לקיץ. בד: פשתן נושם. שרוך קשירה במותן.",
        1499, // 149.90 ₪
        Arrays.asList("גברים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "פשתן", "Minimalist", "Vacation", "Summer", "חלק")
    ));

    // 4. מכנסי דגמ"ח Cargo - מתאים ל-Streetwear/Gym
    productService.addProductToDB(new Product(
        "M-PA-304", "מכנסי קרגו Utility", "images/cargo",
        "מכנסיים פונקציונליים עם כיסים גדולים. בד: כותנה עמידה בטקסטורת ריפסטופ.",
        2299, // 229.90 ₪
        Arrays.asList("גברים", "מכנסיים", "דגמח", "ירוק ", "Oversize", "כותנה", "Streetwear", "Gym", "Autumn", "חלק")
    ));

    // 5. מכנסי פשתן Relaxed - לחופשה/קיץ
    productService.addProductToDB(new Product(
    "M-PA-305", "מכנסי פשתן Relaxed Fit", "images/linen-pants",
    "בד: פשתן אירופאי נושם. גזרה רחבה ומשוחררת, שרוך קשירה פנימי במותן.",
    1799, // 179.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Relaxed-Fit", "פשתן", "Vacation", "Summer", "חלק")
    ));

    // 6. מכנסי צ'ינו Slim-Fit אפורים - למשרד
    productService.addProductToDB(new Product(
    "M-PA-306", "מכנסי צ'ינו Stretch Slim", "images/chino-grey",
    "בד: 97% כותנה סרוקה, 3% לייקרה (280 GSM). גזרה צמודה ומחמיאה לעבודה.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "כותנה", "Office", "Classic", "Spring-Essentials", "חלק")
    ));

    // 7. מכנסי טרנינג Heavyweight - לחדר כושר/סטריטוור
    productService.addProductToDB(new Product(
    "M-PA-307", "מכנסי ג'וגר כותנה כבדה", "images/heavy-sweatpants",
    "בד: כותנה (450 GSM). סיומת מנג'ט בקרסול, כיסים עמוקים עם רוכסן.",
    1699, // 169.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Oversize", "כותנה", "Gym", "Streetwear", "Winter", "חלק")
    ));

    // 8. מכנסי צ'ינו Slim-Fit כחול נייבי - למשרד
    productService.addProductToDB(new Product(
    "M-PA-308", "מכנסי צ'ינו Slim Navy", "images/chinos-navy",
    "בד: 98% כותנה, 2% אלסטן. גזרה צמודה ומחויטת, מתאים למראה רשמי או יומיומי משודרג.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Slim-Fit", "% כותנה", "Office", "Classic", "Spring-Essentials", "חלק")
    ));

    // 9. מכנסי דגמ"ח (Cargo) ירוק זית - סטריטוור
    productService.addProductToDB(new Product(
    "M-PA-309", "מכנסי קרגו Utility Olive", "images/cargo-olive",
    "בד: 100% כותנת טוויל עמידה. גזרת Relaxed עם 6 כיסים פונקציונליים.",
    2499, // 249.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "ירוק ", "Regular-Fit", "כותנה", "Streetwear", "Casual", "Autumn", "חלק")
    ));

    // 10. מכנסי פשתן שחורים - קיץ/ערב
    productService.addProductToDB(new Product(
    "M-PA-310", "מכנסי פשתן שחורים Relaxed", "images/linen-black",
    "בד: פשתן אירופאי. גזרה רחבה וקלילה, מושלם לערבי קיץ או חופשות.",
    1899, // 189.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Relaxed-Fit", "פשתן", "Minimalist", "Vacation", "Summer", "חלק")
    ));

    // 11. ג'ינס אפור Slim - יציאה בלילה
    productService.addProductToDB(new Product(
    "M-PA-311", "ג'ינס סקיני אפור", "images/grey-jeans",
    "בד: דנים אלסטי. גזרה צמודה במראה מודרני, שטיפה אפורה כהה.",
    2299, // 229.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "דנים", "Night-Out", "Streetwear", "Winter", "חלק")
    ));

    // 12. שורטס כותנה ירוק זית - יומיום
    productService.addProductToDB(new Product(
    "M-PA-312", "מכנסי שורטס Chino Short", "images/shorts-olive",
    "בד: כותנה קלה. גזרה ישרה מעל הברך, מושלם ליומיום בקיץ הישראלי.",
    1399, // 139.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "ירוק ", "Regular-Fit", "כותנה", "Casual", "Summer", "חלק")
    ));

    // 13. מכנסי אלגנט צמר מרינו - לאירועים
    productService.addProductToDB(new Product(
    "M-PA-313", "מכנסי צמר מרינו מחויטים", "images/wool-pants",
    "בד: 100% צמר מרינו דק. גזרה קלאסית עם כפלים, מתאים לחתונה או אירוע רשמי.",
    3999, // 399.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
    ));

    // 14. מכנסי קורדרוי קלאסיים - Winter/Classic
    productService.addProductToDB(new Product(
    "M-PA-314", "מכנסי קורדרוי חומים", "images/corduroy",
    "בד: כותנה בטקסטורת קורדרוי עדינה (320 GSM). גזרה ישרה, מראה חורפי קלאסי ומחמם.",
    2699, // 269.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "בז'", "Regular-Fit", "כותנה", "Classic", "Winter", "חלק")
    ));

    // 15. מכנסיים משובצים מחויטים - Office/Elegant
    productService.addProductToDB(new Product(
    "M-PA-315", "מכנסי צ'ינו משובצים", "images/plaid-chinos",
    "בד: תערובת כותנה וצמר. דוגמת משבצות 'Windowpane' עדינה, מושלם למראה משרדי מתוחכם.",
    2899, // 289.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "כותנה", "Elegant", "Office", "Autumn", "משובץ")
    ));

    // 16. שורטס ריצה טכניים - Gym
    productService.addProductToDB(new Product(
    "M-PA-316", "מכנסי אימון Short-Running", "images/gym-shorts",
    "בד: פוליאסטר קל ומנדף (Dry-Fit). גזרה קצרה עם שסעים בצדדים לתנועה חופשית במכון.",
    1199, // 119.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "שחור", "Regular-Fit", "כותנה", "Gym", "Summer", "חלק")
    ));

    // 17. מכנסיים מחויטים כחול נייבי - Wedding/Elegant
    productService.addProductToDB(new Product(
    "M-PA-317", "מכנסי ערב Tuxedo-Style", "imagesens-formal-navy",
    "בד: 100% צמר מרינו דק ואיכותי. גזרה מחויטת עם פס צד מעודן, מתאים לאירועים רשמיים.",
    4299, // 429.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "All-Season", "חלק")
    ));

    // 18. ג'ינס שחור פרום - Night-Out/Streetwear
    productService.addProductToDB(new Product(
    "M-PA-318", "ג'ינס סקיני שחור Ripped", "imagesens-black-ripped",
    "בד: דנים אלסטי שטוף. קרעים עדינים בברכיים, מראה מחוספס ליציאה בלילה.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "דנים", "Night-Out", "Streetwear", "All-Season", "חלק")
    ));

    // 19. מכנסי פשתן ירוק זית - Vacation/Summer
    productService.addProductToDB(new Product(
    "M-PA-319", "מכנסי פשתן Olive Summer", "imagesens-linen-olive",
    "בד: פשתן מכובס למגע רך. גזרה משוחררת, שרוך קשירה במותן למראה נינוח בחופשה.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק ", "Relaxed-Fit", "פשתן", "Vacation", "Summer", "חלק")
    ));

    // 20. מכנסי צ'ינו Slim-Fit שחורים - Night-Out/Minimalist
    productService.addProductToDB(new Product(
    "M-PA-320", "מכנסי צ'ינו Jet Black", "imagesens-black-chinos",
    "בד: 98% כותנה סרוקה, 2% אלסטן. מראה נקי ומינימליסטי המתאים ליציאה בערב או לפגישות.",
    2299, // 229.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "כותנה", "Night-Out", "Minimalist", "All-Season", "חלק")
    ));

    // 21. מכנסיים מחויטים משובצים - Office/Classic
    productService.addProductToDB(new Product(
    "M-PA-321", "מכנסיים מחויטים Glen Check", "imagesens-plaid-office",
    "בד: תערובת כותנה וצמר קל. דוגמת משבצות קלאסית בגווני אפור וכחול נייבי.",
    3199, // 319.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "כותנה", "Office", "Classic", "Autumn", "משובץ")
    ));

    // 22. מכנסי פשתן קצרים כחול נייבי - Vacation/Summer
    productService.addProductToDB(new Product(
    "M-PA-322", "שורטס פשתן Navy Summer", "imagesens-navy-shorts",
    "בד: פשתן נושם. גזרה ישרה מעל הברך עם שרוך קשירה, אידיאלי לחופשות ולים.",
    1599, // 159.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "כחול ", "Regular-Fit", "פשתן", "Vacation", "Summer", "חלק")
    ));

    // 23. ג'ינס אפור כהה שטוף - Streetwear/Casual
    productService.addProductToDB(new Product(
    "M-PA-323", "ג'ינס Grey Wash Denim", "imagesens-grey-denim",
    "בד: דנים 12oz עם מעט סטרץ'. מראה אורבני משופשף, גזרת Slim-Fit נוחה ליומיום.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "דנים", "Streetwear", "Casual", "Winter", "חלק")
    ));

// 24. מכנסי דגמ"ח לבנים - Minimalist/Summer
productService.addProductToDB(new Product(
    "M-PA-324", "מכנסי קרגו לבנים Lightweight", "imagesens-white-cargo",
    "בד: כותנת פופלין דקה. כיסי צד שטוחים למראה נקי, מתאים למזג אוויר חם.",
    2399, // 239.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "לבן", "Regular-Fit", "כותנה", "Minimalist", "Summer", "חלק")
));

// 25. מכנסי צמר מרינו יוקרתיים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-325", "מכנסי ערב צמר מרינו", "imagesens-luxury-wool",
    "בד: 100% צמר מרינו משובח (Super 120s). גזרה מחויטת צמודה, הכי אלגנטי שיש לחתונה.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 26. מכנסי ריצה (Joggers) ירוק זית - Gym/Streetwear
productService.addProductToDB(new Product(
    "M-PA-326", "ג'וגר סריג Olive Active", "imagesens-olive-jogger",
    "בד: תערובת כותנה ופוליאסטר (320 GSM). גומי בקרסול וסגירת שרוך, למכון או ללוק יומיומי.",
    1899, // 189.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק ", "Relaxed-Fit", "כותנה", "Gym", "Streetwear", "Spring-Essentials", "חלק")
));

// 27. מכנסי פסים דקים - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-327", "מכנסי פשתן פסים סיכה", "imagesens-striped-linen",
    "בד: פשתן וכותנה. דוגמת פסי אורך כחול-לבן, מראה Vacation קלאסי וקליל.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "פשתן", "Vacation", "Summer", "פסים")
));

// 28. מכנסי צ'ינו Slim-Fit ירוק זית - Office/Autumn
productService.addProductToDB(new Product(
    "M-PA-328", "מכנסי צ'ינו Olive Slim", "imagesens-olive-chinos",
    "בד: 98% כותנה סרוקה, 2% אלסטן. מראה מחויט ומודרני, מושלם לשילוב עם חולצת אוקספורד.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק ", "Slim-Fit", "כותנה", "Office", "Classic", "Autumn", "חלק")
));

// 29. מכנסי פשתן כחול נייבי - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-329", "מכנסי פשתן Navy Relaxed", "imagesens-navy-linen",
    "בד: פשתן טבעי. גזרה רחבה ונושמת, אידיאלי לאירועי צהריים או חופשות בקיץ.",
    2499, // 249.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Relaxed-Fit", "פשתן", "Vacation", "Elegant", "Summer", "חלק")
));

// 30. מכנסי דגמ"ח משובצים - Streetwear/Autumn
productService.addProductToDB(new Product(
    "M-PA-330", "מכנסי קרגו משובצים Grey", "imagesens-plaid-cargo",
    "בד: כותנה עמידה בטקסטורת משבצות עדינה. 6 כיסים וגזרת Oversize למראה אורבני בולט.",
    2699, // 269.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "אפור", "Oversize", "כותנה", "Streetwear", "Autumn", "משובץ")
));

// 31. ג'ינס לבן נקי - Minimalist/Spring
productService.addProductToDB(new Product(
    "M-PA-331", "ג'ינס לבן Slim-Fit", "imagesens-white-denim",
    "בד: דנים לבן איכותי. מראה נקי ומינימליסטי שמתאים בול לעונת המעבר.",
    2399, // 239.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Slim-Fit", "דנים", "Minimalist", "Spring-Essentials", "חלק")
));

// 32. מכנסי טרנינג אפורים - Gym/Casual
productService.addProductToDB(new Product(
    "M-PA-332", "מכנסי פליז Grey Heather", "imagesens-grey-sweatpants",
    "בד: כותנה מוברשת (350 GSM). גזרה נוחה לאימון או לבית, רך ומחמם.",
    1599, // 159.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "כותנה", "Gym", "Casual", "Winter", "חלק")
));

// 33. מכנסי צמר מרינו שחורים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-333", "מכנסי חליפה צמר מרינו", "imagesens-black-wool",
    "בד: 100% צמר מרינו Super 100s. גזרה קלאסית מחויטת, מתאים לאירועים רשמיים מאוד.",
    4599, // 459.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 34. שורטס פסים כחול-לבן - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-334", "שורטס פשתן פסים סיכה", "imagesens-striped-shorts",
    "בד: תערובת פשתן וכותנה. דוגמת פסים קלאסית, קליל ואופנתי לחופשת קיץ.",
    1699, // 169.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "פשתן", "Vacation", "Summer", "פסים")
));

// 35. ג'ינס דנים Western כהה - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-PA-335", "ג'ינס Raw Denim כהה", "imagesens-dark-denim",
    "בד: כותנה קשיחה. צבע אינדיגו עמוק, גזרת Straight מחוספסת ליציאה בערב.",
    2899, // 289.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Regular-Fit", "דנים", "Night-Out", "Streetwear", "Winter", "חלק")
));

// 36. מכנסי צמר מרינו דקים - Elegant/Office
productService.addProductToDB(new Product(
    "M-PA-336", "מכנסי צמר מרינו Slim", "imagesens-wool-slim",
    "בד: 100% צמר מרינו. גזרה מחויטת ויוקרתית, דוחה קמטים ומתאימה ליום עבודה ארוך או לאירוע.",
    3899, // 389.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "צמר מרינו", "Elegant", "Office", "Winter", "חלק")
));

// 37. מכנסי דגמ"ח (Cargo) שחורים - Streetwear/Night-Out
productService.addProductToDB(new Product(
    "M-PA-337", "מכנסי קרגו Black Urban", "imagesens-cargo-black",
    "בד: כותנה. גזרת Oversize עם כיסים נפוחים, מראה אורבני חזק שמתאים גם ליציאה בלילה.",
    2699, // 269.90 ₪
    Arrays.asList("גברים", "מכנסיים", "דגמח", "שחור", "Oversize", "כותנה", "Streetwear", "Night-Out", "Autumn", "חלק")
));

// 38. שורטס פסים כחול-לבן - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-338", "שורטס פשתן פסים", "imagesens-striped-shorts",
    "בד: פשתן. דוגמת פסים כחולים על רקע לבן, מראה Vacation קלאסי ומרענן.",
    1699, // 169.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "פשתן", "Vacation", "Summer", "פסים")
));

// 39. ג'ינס כחול נייבי - Classic/Casual
productService.addProductToDB(new Product(
    "M-PA-339", "ג'ינס Indigo Denim", "imagesens-indigo-jeans",
    "בד: דנים כותנה (13oz). גזרה ישרה וקלאסית, צבע כחול עמוק שמתאים לכל יום.",
    2799, // 279.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Classic", "דנים", "Casual", "All-Season", "חלק")
));

// 40. מכנסיים מחויטים לבנים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-PA-340", "מכנסי ערב פשתן לבנים", "imagesens-white-linen-pants",
    "בד: פשתן איכותי. גזרה מחויטת לאירועי צהריים או חתונות חוף.",
    2999, // 299.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "פשתן", "Wedding", "Elegant", "Summer", "חלק")
));

// 41. מכנסי טרנינג ירוק זית - Gym/Casual
productService.addProductToDB(new Product(
    "M-PA-341", "מכנסי ג'וגר Olive", "imagesens-olive-sweatpants",
    "בד: כותנה. נוחות מקסימלית לאימון או ללוק יומיומי משוחרר.",
    1599, // 159.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק ", "Relaxed-Fit", "כותנה", "Gym", "Casual", "Spring-Essentials", "חלק")
));

// 42. מכנסיים משובצים אפורים - Minimalist/Office
productService.addProductToDB(new Product(
    "M-PA-342", "מכנסי צ'ינו משובצים", "imagesens-grey-check",
    "בד: כותנה עבה. דוגמת משבצות עדינה ומינימליסטית למראה משרדי מתוחכם.",
    2499, // 249.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "כותנה", "Minimalist", "Office", "Autumn", "משובץ")
));

// 43. שורטס דנים שטופים - Streetwear/Summer
productService.addProductToDB(new Product(
    "M-PA-343", "שורטס ג'ינס Washed Blue", "imagesens-denim-shorts",
    "בד: דנים כותנה. מראה משופשף וקולי, מתאים לסטריטוור קיצי.",
    1399, // 139.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "כחול ", "Regular-Fit", "דנים", "Streetwear", "Summer", "חלק")
));

// 44. מכנסיים מחויטים - Elegant/Wedding
productService.addProductToDB(new Product(
    "M-PA-344", "מכנסי ערב צמר מרינו Slim", "imagesens-wool-wedding",
    "מכנסיים יוקרתיים לגזרה צמודה. בד: 100% צמר מרינו. מתאים לחתונה או אירוע רשמי.",
    4199, // 419.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "צמר מרינו", "Elegant", "Wedding", "Winter", "חלק")
));

// 45. מכנסי קרגו (מראה דגמ"ח) - Streetwear/Oversize
productService.addProductToDB(new Product(
    "M-PA-345", "מכנסי קרגו Streetwear Cotton", "imagesens-cargo-street",
    "מכנסיים עם כיסי צד גדולים. בד: כותנה. גזרה רחבה למראה סטריטוור מודרני.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "ירוק ", "Oversize", "כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 46. מכנסי פשתן קלים - Vacation/Summer
productService.addProductToDB(new Product(
    "M-PA-346", "מכנסי פשתן לבנים Relaxed", "imagesens-linen-summer",
    "מכנסיים אווריריים לחופשה. בד: פשתן. גזרה משוחררת עם שרוך.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "לבן", "Relaxed-Fit", "פשתן", "Vacation", "Summer", "חלק")
));

// 47. ג'ינס אפור - Streetwear/Night-Out
productService.addProductToDB(new Product(
    "M-PA-347", "ג'ינס אפור שטוף", "imagesens-grey-denim",
    "ג'ינס בגזרה ישרה. בד: דנים כותנה. מתאים ליציאה בערב או ליומיום.",
    2399, // 239.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "דנים", "Streetwear", "Night-Out", "All-Season", "חלק")
));

// 48. מכנסי אימון - Gym/Casual
productService.addProductToDB(new Product(
    "M-PA-348", "מכנסי ג'וגר Active Gym", "imagesens-gym-pants",
    "מכנסיים נוחים לאימון. בד: כותנה. גזרה נוחה עם גומי בקרסול.",
    1799, // 179.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "כחול ", "Regular-Fit", "כותנה", "Gym", "Casual", "Spring-Essentials", "חלק")
));

// 49. מכנסי צ'ינו בז' - Office/Minimalist
productService.addProductToDB(new Product(
    "M-PA-349", "מכנסי צ'ינו Minimalist Beige", "imagesens-chinos-minimal",
    "מראה נקי למשרד. בד: כותנה. גזרה ישרה ומחויטת.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "מכנסיים", "ארוך", "בז'", "Regular-Fit", "כותנה", "Minimalist", "Office", "Spring-Essentials", "חלק")
));

// 50. שורטס משובצים - Casual/Summer
productService.addProductToDB(new Product(
    "M-PA-350", "שורטס משובצים Classic Check", "imagesens-plaid-shorts",
    "מכנסיים קצרים ליומיום. בד: כותנה. דוגמת משבצות עדינה.",
    1499, // 149.90 ₪
    Arrays.asList("גברים", "מכנסיים", "קצר", "אפור", "Regular-Fit", "כותנה", "Casual", "Summer", "משובץ")
));
}

private void seedWomensPants() {
    // 1. ג'ינס רחב High-Waist - מתאים ל-Streetwear/Minimalist
    productService.addProductToDB(new Product(
        "W-PA-401", "ג'ינס Wide-Leg רטרו", "imagesomens-wide-jeans",
        "גזרה גבוהה ורחבה מאוד. בד: דנים שטוף. מראה מודרני ונקי.",
        2199, // 219.90 ₪
        Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Wide-Leg", "דנים", "Streetwear", "Spring-Essentials", "חלק")
    ));

    // 2. מכנסיים מחויטים - מתאים ל-Office/Wedding
    productService.addProductToDB(new Product(
        "W-PA-402", "מכנסי סיגנר מחויטים", "imagesomens-tailored",
        "מכנסיים אלגנטיים עם כפלים. בד סאטן יוקרתי למגע משי.",
        2799, // 279.90 ₪
        Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "סאטן", "Elegant", "Wedding", "Winter", "חלק")
    ));

    // 3. שורטס ג'ינס Mom-Fit - מתאים ל-Casual/Summer
    productService.addProductToDB(new Product(
        "W-PA-403", "שורטס דנים Mom-Fit", "imagesomens-shorts",
        "מכנסיים קצרים ונוחים ליומיום. בד: כותנה קשיחה.",
        1299, // 129.90 ₪
        Arrays.asList("נשים", "מכנסיים", "קצר", "לבן", "Regular-Fit", "כותנה", "Casual", "Vacation", "Summer", "חלק")
    ));

    // 4. מכנסי פשתן רחבים - לקיץ
productService.addProductToDB(new Product(
    "W-PA-404", "מכנסי פשתן Wide-Leg", "imagesomen-linen",
    "בד: פשתן איכותי. גזרה גבוהה ורחבה מאוד, מושלם למראה נקי ואוורירי.",
    1999, // 199.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "פשתן", "Minimalist", "Vacation", "Summer", "חלק")
));

// 5. טייץ Seamless מחטב - ספורט
productService.addProductToDB(new Product(
    "W-PA-405", "טייץ אימון Seamless", "images/yoga-leggings",
    "בד: ניילון וספנדקס בטכנולוגיית נידוף זיעה. גזרה צמודה ללא תפרים צדדיים.",
    1499, // 149.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק ", "Slim-Fit", "סאטן", "Gym", "Active", "All-Season", "חלק")
));

// 6. מכנסי דגמ"ח Streetwear
productService.addProductToDB(new Product(
    "W-PA-406", "מכנסי קרגו נשים", "imagesomen-cargo",
    "בד: 100% כותנת טוויל עמידה. ריבוי כיסי צד, גזרת Loose מחמיאה.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "אפור", "Oversize", "כותנה", "Streetwear", "Autumn", "חלק")
));

// 7. מכנסי סאטן לבנים - אירוע/חתונה
productService.addProductToDB(new Product(
    "W-PA-407", "מכנסי סאטן Wide-Leg לבנים", "imagesomens-satin-white",
    "בד: סאטן מבריק ויוקרתי. גזרה רחבה ונשפכת, מראה אלגנטי לאירועים.",
    2899, // 289.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Wide-Leg", "סאטן", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 8. ג'ינס Mom-Fit שחור - יומיום
productService.addProductToDB(new Product(
    "W-PA-408", "ג'ינס Mom-Fit שחור", "imagesomens-mom-jeans",
    "בד: דנים קשיח. גזרה גבוהה ונוחה, מראה רטרו קלאסי.",
    1999, // 199.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "דנים", "Casual", "Streetwear", "Winter", "חלק")
));

// 9. שורטס פשתן בז' - חופשה
productService.addProductToDB(new Product(
    "W-PA-409", "שורטס פשתן קלילים", "imagesomens-linen-shorts",
    "בד: פשתן נושם. גזרה נינוחה עם גומי במותן, מושלם לים ולחופשות.",
    1299, // 129.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "בז'", "Relaxed-Fit", "פשתן", "Vacation", "Summer", "חלק")
));

// 10. טייץ נייבי - אימון
productService.addProductToDB(new Product(
    "W-PA-410", "טייץ High-Rise Performance", "imagesomens-gym-leggings",
    "בד: פוליאסטר אלסטי מנדף. גזרה צמודה ומחטבת לפעילות ספורטיבית.",
    1699, // 169.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Skinny", "סאטן", "Gym", "Streetwear", "All-Season", "חלק")
));

// 11. מכנסיים משובצים למשרד
productService.addProductToDB(new Product(
    "W-PA-411", "מכנסי סיגר משובצים", "imagesomens-plaid-pants",
    "בד: כותנה עבה. דוגמת משבצות עדינה בגווני אפור, גזרה ישרה.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "כותנה", "Office", "Classic", "Autumn", "משובץ")
));

// 12. מכנסי קרגו (דגמ"ח) בז' - סטריטוור
productService.addProductToDB(new Product(
    "W-PA-412", "מכנסי קרגו נשים Street", "imagesomens-cargo-beige",
    "בד: 100% כותנת טוויל. גזרה רחבה עם כיסי צד גדולים, מראה אורבני.",
    2599, // 259.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "בז'", "Oversize", "כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 13. מכנסי סאטן שחורים - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-PA-413", "מכנסי סאטן Flowy", "imagesomens-satin-black",
    "בד: סאטן רך עם ברק עדין. גזרה רחבה ונשפכת, מושלם לערב או לאירוע חגיגי.",
    2699, // 269.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "Winter", "חלק")
));

// 14. מכנסי פסים קלילים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-414", "מכנסי פסים Vertical Stripe", "imagesomens-striped-pants",
    "בד: תערובת כותנה ופשתן. דוגמת פסי אורך דקים בכחול-לבן, מראה קיצי ומרענן.",
    1799, // 179.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Relaxed-Fit", "פשתן", "Vacation", "Summer", "פסים")
));

// 15. מכנסי צמר למשרד - Office/Winter
productService.addProductToDB(new Product(
    "W-PA-415", "מכנסי צמר מרינו מחויטים", "imagesomens-wool-office",
    "בד: 100% צמר מרינו קל. גזרה ישרה עם כפל קדמי (Crease), מתאים לימי חורף במשרד.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Classic", "Winter", "חלק")
));

// 16. שורטס פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-416", "שורטס פרחוניים Floral Bloom", "imagesomens-floral-shorts",
    "בד: ויסקוזה נעימה. הדפס פרחים צבעוני על רקע לבן, גזרה גבוהה עם גומי במותן.",
    1199, // 119.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "לבן", "Relaxed-Fit", "כותנה", "Vacation", "Summer", "פרחוני")
));

// 17. ג'ינס Wide-Leg אפור - Streetwear
productService.addProductToDB(new Product(
    "W-PA-417", "ג'ינס אפור Wide-Leg", "imagesomens-grey-wide",
    "בד: דנים קשיח בטכנולוגיית Eco-Wash. גזרה רחבה מאוד מהמותן ומטה, מראה סטריטוור עדכני.",
    2299, // 229.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Wide-Leg", "דנים", "Streetwear", "Autumn", "חלק")
));

// 18. מכנסי דגמ"ח לבנים - Minimalist/Casual
productService.addProductToDB(new Product(
    "W-PA-418", "מכנסי קרגו נקיים", "imagesomens-white-cargo",
    "בד: כותנה. כיסים שטוחים למראה מינימליסטי, מתאים ליומיום בקיץ ובאביב.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "לבן", "Regular-Fit", "כותנה", "Minimalist", "Spring-Essentials", "חלק")
));

// 19. מכנסי סאטן כחול נייבי - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-PA-419", "מכנסי סאטן Midnight Navy", "imagesomens-navy-satin",
    "בד: סאטן יוקרתי ונשפך. גזרה רחבה מאוד (Wide-Leg), מראה זוהר ליציאה בלילה.",
    2999, // 299.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 20. ג'ינס Wide-Leg לבן - Minimalist/Spring
productService.addProductToDB(new Product(
    "W-PA-420", "ג'ינס לבן רחב High-Rise", "imagesomens-white-denim",
    "בד: דנים כותנה. גזרה גבוהה ומחמיאה, מתאים למראה אביבי נקי.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Wide-Leg", "דנים", "Minimalist", "Spring-Essentials", "חלק")
));

// 21. טייץ ספורט בז' - Gym
productService.addProductToDB(new Product(
    "W-PA-421", "טייץ אימון Sand Seamless", "imagesomens-sand-leggings",
    "בד: ניילון אלסטי מחטב. ללא תפרים, גזרה צמודה (Skinny) לאימונים אינטנסיביים.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Slim-Fit", "סאטן", "Gym", "Active", "Summer", "חלק")
));

// 22. מכנסי פשתן משובצים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-422", "מכנסי פשתן משבצות Vichy", "imagesomens-check-linen",
    "בד: פשתן. דוגמת משבצות קטנות בשחור-לבן, מראה כפרי וקליל לחופשה.",
    2199, // 219.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "פשתן", "Vacation", "Summer", "משובץ")
));

// 23. מכנסיים מחויטים ירוק זית - Office/Elegant
productService.addProductToDB(new Product(
    "W-PA-423", "מכנסי סיגר Olive Tailored", "imagesomens-olive-pants",
    "בד: כותנה וצמר קל. גזרה מחויטת עם כפל קדמי, מתאים למשרד או לאירוע צהריים.",
    2699, // 269.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק ", "Regular-Fit", "כותנה", "Office", "Elegant", "Autumn", "חלק")
));

// 24. שורטס ג'ינס שחור - Casual/Streetwear
productService.addProductToDB(new Product(
    "W-PA-424", "שורטס דנים Washed Black", "imagesomens-black-shorts",
    "בד: כותנה. קצוות פרומים ומראה משופשף, פריט חובה לקיץ.",
    1399, // 139.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "שחור", "Regular-Fit", "דנים", "Casual", "Streetwear", "Summer", "חלק")
));

// 25. מכנסי סאטן פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-425", "מכנסי סאטן Floral Breeze", "imagesomens-floral-satin",
    "בד: סאטן רך. הדפס פרחים עדין על רקע בז', גזרה רחבה וקלילה.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "סאטן", "Vacation", "Summer", "פרחוני")
));

// 26. מכנסי דגמ"ח אפורים - Streetwear/Autumn
productService.addProductToDB(new Product(
    "W-PA-426", "מכנסי קרגו Grey Urban", "imagesomens-grey-cargo",
    "בד: כותנה עמידה. ריבוי כיסים וגזרת Oversize למראה סטריטוור מודרני.",
    2599, // 259.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "אפור", "Oversize", "כותנה", "Streetwear", "Autumn", "חלק")
));

// 27. מכנסי סאטן בצבע בז' - Wedding/Night-Out
productService.addProductToDB(new Product(
    "W-PA-427", "מכנסי סאטן Champagne", "imagesomens-beige-satin",
    "בד: סאטן מבריק נשפך. גזרה רחבה ויוקרתית, מושלם לאירועי ערב או חתונות.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "סאטן", "Wedding", "Night-Out", "Spring-Essentials", "חלק")
));

// 28. ג'ינס Wide-Leg שחור - Streetwear/Minimalist
productService.addProductToDB(new Product(
    "W-PA-428", "ג'ינס שחור רחב High-Rise", "imagesomens-black-wide",
    "בד: דנים כותנה. גזרה גבוהה ומחמיאה, מראה אורבני נקי שמתאים לכל דבר.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Wide-Leg", "דנים", "Streetwear", "Minimalist", "Autumn", "חלק")
));

// 29. מכנסי פשתן לבנים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-429", "מכנסי פשתן קיציים White", "imagesomens-white-linen",
    "בד: פשתן אוורירי. גזרה ישרה ונינוחה, פריט חובה לכל חופשה בקיץ.",
    1899, // 189.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Regular-Fit", "פשתן", "Vacation", "Summer", "חלק")
));

// 30. טייץ Gym ירוק זית - Gym/Active
productService.addProductToDB(new Product(
    "W-PA-430", "טייץ אימון Olive Seamless", "imagesomens-olive-gym",
    "בד: ניילון אלסטי מחטב. ללא תפרים, תמיכה גבוהה לפעילות ספורטיבית אינטנסיבית.",
    1699, // 169.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק ", "Slim-Fit", "סאטן", "Gym", "Spring-Essentials", "חלק")
));

// 31. מכנסיים מחויטים משובצים - Office/Classic
productService.addProductToDB(new Product(
    "W-PA-431", "מכנסי סיגר משובצים Grey Check", "imagesomens-grey-plaid",
    "בד: תערובת כותנה. דוגמת משבצות עדינה באפור, גזרת Slim-Fit מקצועית למשרד.",
    2599, // 259.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "כותנה", "Office", "Classic", "Winter", "משובץ")
));

// 32. שורטס פרחוניים קלילים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-432", "שורטס ויסקוזה Floral", "imagesomens-floral-shorts",
    "בד: ויסקוזה נעימה. הדפס פרחים צבעוני, גזרה גבוהה עם שרוך קשירה במותן.",
    1299, // 129.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "לבן", "Relaxed-Fit", "כותנה", "Vacation", "Summer", "פרחוני")
));

// 33. מכנסי דגמ"ח כחול נייבי - Streetwear/Casual
productService.addProductToDB(new Product(
    "W-PA-433", "מכנסי קרגו Navy Urban", "imagesomens-navy-cargo",
    "בד: 100% כותנת טוויל עמידה. כיסי צד גדולים וגזרת Oversize מודרנית.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "כחול ", "Oversize", "כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 34. מכנסי צמר מרינו אפורים - Office/Winter
productService.addProductToDB(new Product(
    "W-PA-434", "מכנסי צמר מרינו Tailored", "imagesomens-grey-wool",
    "בד: 100% צמר מרינו דק. גזרה ישרה עם כפל קדמי, מראה אלגנטי ומחמם לחורף.",
    3599, // 359.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Elegant", "Winter", "חלק")
));

// 35. מכנסי סאטן ירוק זית - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-PA-435", "מכנסי סאטן Olive Glow", "imagesomens-olive-satin",
    "בד: סאטן רך. גזרה רחבה ונשפכת, מראה יוקרתי ליציאות ערב.",
    2799, // 279.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "ירוק ", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 36. מכנסי צמר מרינו מחויטים - Office/Winter
productService.addProductToDB(new Product(
    "W-PA-436", "מכנסי צמר מרינו Grey Office", "imagesomens-grey-wool",
    "בד: 100% צמר מרינו. גזרה ישרה עם כפל קדמי, פריט קלאסי ומחמם למשרד.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Classic", "Winter", "חלק")
));

// 37. ג'ינס Mom-Fit כחול - Casual/Autumn
productService.addProductToDB(new Product(
    "W-PA-437", "ג'ינס Mom-Fit Classic", "imagesomens-blue-mom",
    "בד: דנים כותנה. גזרה גבוהה ונוחה במיוחד ליומיום.",
    2199, // 219.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Regular-Fit", "דנים", "Casual", "Autumn", "חלק")
));

// 38. מכנסי פשתן בז' - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-438", "מכנסי פשתן Summer Breeze", "imagesomens-beige-linen",
    "בד: פשתן אוורירי. גזרה רחבה (Wide-Leg), הכי נוח לחופשה בקיץ.",
    1899, // 189.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "בז'", "Wide-Leg", "פשתן", "Vacation", "Summer", "חלק")
));

// 39. שורטס משובצים - Casual/Summer
productService.addProductToDB(new Product(
    "W-PA-439", "שורטס משובצים Vichy", "imagesomens-plaid-shorts",
    "בד: כותנה. דוגמת משבצות שחור-לבן קלאסית, גזרה מחמיאה.",
    1299, // 129.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "שחור", "Regular-Fit", "כותנה", "Casual", "Summer", "משובץ")
));

// 40. מכנסי דגמ"ח לבנים - Streetwear/Spring
productService.addProductToDB(new Product(
    "W-PA-440", "מכנסי קרגו White Utility", "imagesomens-white-cargo",
    "בד: כותנה. גזרת Oversize עם כיסי צד, מראה סטריטוור נקי.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "לבן", "Oversize", "כותנה", "Streetwear", "Spring-Essentials", "חלק")
));

// 41. טייץ Gym שחור - Gym/Active
productService.addProductToDB(new Product(
    "W-PA-441", "טייץ אימון High-Waist", "imagesomens-gym-black",
    "בד: סאטן אלסטי ומחטב. גזרה צמודה (Skinny) המיועדת לפעילות ספורטיבית.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Slim-Fit", "סאטן", "Gym", "All-Season", "חלק")
));

// 42. מכנסיים פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-442", "מכנסי ויסקוזה Floral", "imagesomens-floral-pants",
    "בד: כותנה (ויסקוזה). הדפס פרחים צבעוני על רקע כחול נייבי, קליל ונשי.",
    2299, // 229.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Wide-Leg", "כותנה", "Vacation", "Summer", "פרחוני")
));

// 43. מכנסי סאטן יוקרתיים - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-PA-443", "מכנסי סאטן Ivory Wide", "imagesomens-satin-wedding",
    "מכנסיים נשפכים ויוקרתיים. בד: סאטן מבריק. גזרה רחבה מאוד לאירועים.",
    3299, // 329.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "לבן", "Wide-Leg", "סאטן", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 44. ג'ינס רחב - Streetwear/Oversize
productService.addProductToDB(new Product(
    "W-PA-444", "ג'ינס Wide-Leg Street", "imagesomens-wide-denim",
    "גזרה גבוהה ורחבה. בד: דנים כותנה. מראה סטריטוור עדכני.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Wide-Leg", "דנים", "Streetwear", "Oversize", "Autumn", "חלק")
));

// 45. מכנסי פשתן שחורים - Vacation/Minimalist
productService.addProductToDB(new Product(
    "W-PA-445", "מכנסי פשתן Minimal Black", "imagesomens-black-linen",
    "מכנסיים קלילים ואלגנטיים. בד: פשתן. גזרה ישרה ונקייה.",
    1999, // 199.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "שחור", "Regular-Fit", "פשתן", "Vacation", "Minimalist", "Summer", "חלק")
));

// 46. טייץ אימון - Gym/Active
productService.addProductToDB(new Product(
    "W-PA-446", "טייץ Gym Performance", "imagesomens-gym-leggings",
    "טייץ מחטב לאימון. בד: סאטן אלסטי (ניילון). גזרה צמודה מנדפת זיעה.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Slim-Fit", "סאטן", "Gym", "All-Season", "חלק")
));

// 47. מכנסיים מחויטים - Office/Classic
productService.addProductToDB(new Product(
    "W-PA-447", "מכנסי סיגר Navy Office", "imagesomens-navy-office",
    "מכנסיים מקצועיים למשרד. בד: כותנה. גזרה ישרה ומחויטת.",
    2699, // 269.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "כחול ", "Regular-Fit", "כותנה", "Office", "Classic", "Winter", "חלק")
));

// 48. שורטס פרחוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-PA-448", "שורטס פרחוניים Summer Bloom", "imagesomens-floral-shorts",
    "מכנסיים קצרים וקלילים. בד: כותנה. הדפס פרחים צבעוני.",
    1199, // 119.90 ₪
    Arrays.asList("נשים", "מכנסיים", "קצר", "בז'", "Regular-Fit", "כותנה", "Vacation", "Summer", "פרחוני")
));

// 49. מכנסי קרגו (מראה דגמ"ח) - Streetwear/Casual
productService.addProductToDB(new Product(
    "W-PA-449", "מכנסי קרגו Urban Beige", "imagesomens-cargo-beige",
    "מכנסיים עם כיסים. בד: כותנה. מראה קז'ואל משוחרר.",
    2399, // 239.90 ₪
    Arrays.asList("נשים", "מכנסיים", "דגמח", "בז'", "Oversize", "כותנה", "Streetwear", "Casual", "Autumn", "חלק")
));

// 50. מכנסי צמר מרינו - Elegant/Winter
productService.addProductToDB(new Product(
    "W-PA-450", "מכנסי צמר מרינו Grey Luxury", "imagesomens-wool-winter",
    "מכנסיים חמים ויוקרתיים. בד: 100% צמר מרינו. גזרה מחויטת לחורף.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מכנסיים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Elegant", "Classic", "Winter", "חלק")
));
}

private void seedShoes() 
{
    // 1. נעלי אוקספורד - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SH-501", "נעלי Oxford עור יוקרתיות", "imagesens-oxford",
    "נעלי ערב קלאסיות מעור איכותי. גימור מבריק וסוליה עמידה, מושלם לחתונה.",
    4499, // 449.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Wedding", "Winter", "חלק")
));

// 2. סניקרס לבנות - Minimalist/Casual
productService.addProductToDB(new Product(
    "M-SH-502", "סניקרס Clean White", "imagesens-sneakers",
    "סניקרס בעיצוב מינימליסטי ונקי. מתאימות לכל אאוטפיט, מיום עבודה ועד יציאה.",
    3299, // 329.90 ₪
    Arrays.asList("גברים", "נעליים", "לבן", "Minimalist", "Casual", "Spring-Essentials", "חלק")
));

// 3. נעלי ריצה - Gym
productService.addProductToDB(new Product(
    "M-SH-503", "נעלי ריצה Performance", "imagesens-running",
    "סוליית שיכוך מתקדמת ובד נושם. תמיכה מקסימלית לכף הרגל בזמן אימון.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 4. לואפרס (Loafers) - Office/Elegant
productService.addProductToDB(new Product(
    "M-SH-504", "נעלי לואפרס זמש", "imagesens-loafers",
    "נעלי סליפ-און במראה אירופאי. נוחות מאוד ומתאימות למשרד או לאירועי צהריים.",
    3899, // 389.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Elegant", "Office", "Spring-Essentials", "חלק")
));

// 5. מגפי צ'לסי - Classic/Winter
productService.addProductToDB(new Product(
    "M-SH-505", "מגפי צ'לסי עור", "imagesens-chelsea",
    "מגפיים קלאסיים עם גומי בצדדים. הגנה מצוינת מגשם ומראה מחוספס אך אלגנטי.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Classic", "Winter", "Autumn", "חלק")
));

// 6. סניקרס High-Top - Streetwear
productService.addProductToDB(new Product(
    "M-SH-506", "סניקרס גבוהות Retro", "imagesens-hightop",
    "מראה סטריטוור נוסטלגי. שילוב צבעים של כחול ולבן, מתאים לג'ינס רחב.",
    3999, // 399.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול ", "Streetwear", "Night-Out", "All-Season", "חלק")
));

// 7. נעלי סירה (Boat Shoes) - Vacation/Summer
productService.addProductToDB(new Product(
    "M-SH-507", "נעלי סירה קיציות", "imagesens-boat-shoes",
    "נעלי בד קלילות עם סוליית גומי לבנה. מושלם לחופשות בקיץ או לים.",
    2599, // 259.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול ", "Classic", "Vacation", "Summer", "חלק")
));

// 8. נעלי צ'אקה (Chukka) - Casual/Autumn
productService.addProductToDB(new Product(
    "M-SH-508", "מגפי צ'אקה זמש אפור", "imagesens-chukka",
    "מגפיים נמוכים עם שרוכים. מראה יומיומי משודרג שמתאים לעונות המעבר.",
    3699, // 369.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Minimalist", "Casual", "Autumn", "חלק")
));

// 9. נעלי ספורט מעוצבות - Night-Out
productService.addProductToDB(new Product(
    "M-SH-509", "סניקרס עור שחורות", "imagesens-black-sneakers",
    "סניקרס אלגנטיות בצע שחור מט. מתאימות ליציאה בערב עם מכנסי צ'ינו.",
    3499, // 349.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Night-Out", "All-Season", "חלק")
));

// 10. סנדלי עור - Vacation/Summer
productService.addProductToDB(new Product(
    "M-SH-510", "סנדלי עור רצועות", "imagesens-sandals",
    "סנדלים איכותיים ונוחים. מראה טבעי שמתאים לחופשות ארוך ומזג אוויר חם.",
    2199, // 219.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Minimalist", "Vacation", "Summer", "חלק")
));

// 11. נעלי מוקסין עור - Office/Elegant
productService.addProductToDB(new Product(
    "M-SH-511", "נעלי מוקסין Classic Navy", "imagesens-loafers-navy",
    "נעליים אלגנטיות ללא שרוכים. מתאימות במיוחד ליום עבודה במשרד או לאירוע חגיגי.",
    3999, // 399.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול ", "Elegant", "Office", "Spring-Essentials", "חלק")
));

// 12. סניקרס בד - Casual/Minimalist
productService.addProductToDB(new Product(
    "M-SH-512", "סניקרס בד Minimalist White", "imagesens-canvas-white",
    "נעלי בד קלילות ונושמות. עיצוב נקי שמשתלב מעולה עם ג'ינס או מכנסי צ'ינו.",
    2799, // 279.90 ₪
    Arrays.asList("גברים", "נעליים", "לבן", "Minimalist", "Casual", "Summer", "חלק")
));

// 13. נעלי ריצה מקצועיות - Gym
productService.addProductToDB(new Product(
    "M-SH-513", "נעלי ריצה Grey Performance", "imagesens-runners-grey",
    "נעלי ספורט עם שיכוך זעזועים מתקדם. תמיכה מלאה לאימונים אינטנסיביים במכון.",
    4999, // 499.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 14. מגפיים חומים - Classic/Winter
productService.addProductToDB(new Product(
    "M-SH-514", "מגפי עור Autumn Beige", "imagesens-boots-beige",
    "מגפיים עמידים למזג אוויר קר. מראה קלאסי ומחוספס שמתאים לעונת החורף.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Classic", "Winter", "Autumn", "חלק")
));

// 15. סניקרס שחורות - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-SH-515", "סניקרס עור Black Street", "imagesens-sneakers-black",
    "סניקרס אלגנטיות בצע שחור. מתאימות ליציאה בערב או ללוק סטריטוור מודרני.",
    3599, // 359.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Streetwear", "Night-Out", "All-Season", "חלק")
));

// 16. סנדלי חוף - Vacation/Summer
productService.addProductToDB(new Product(
    "M-SH-516", "סנדלי Vacation Olive", "imagesens-sandals-olive",
    "סנדלים נוחים ועמידים למים. מושלם לחופשות בקיץ או לטיולים בטבע.",
    1999, // 199.90 ₪
    Arrays.asList("גברים", "נעליים", "ירוק זית", "Minimalist", "Vacation", "Summer", "חלק")
));

// 17. נעלי ערב - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SH-517", "נעלי עור Formal Black", "imagesens-formal-black",
    "נעלי אוקספורד יוקרתיות. הכי אלגנטי שיש לחתונה או לאירוע רשמי מאוד.",
    4799, // 479.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Wedding", "Winter", "חלק")
));

// 18. סניקרס גבוהות - Streetwear/Oversize
productService.addProductToDB(new Product(
    "M-SH-518", "סניקרס High-Top Retro", "imagesens-hightop-navy",
    "נעליים במראה רטרו עם תמיכה בקרסול. משתלבות בול עם מכנסי קרגו וגזרות רחבות.",
    4299, // 429.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול", "Streetwear", "Oversize", "Autumn", "חלק")
));

// 19. נעלי קז'ואל - Casual/Spring-Essentials
productService.addProductToDB(new Product(
    "M-SH-519", "נעלי Canvas Grey", "imagesens-casual-grey",
    "נעליים יומיומיות נוחות. עיצוב פשוט ונקי שמתאים לכל יום בעונת המעבר.",
    3199, // 319.90 ₪
    Arrays.asList("גברים", "נעליים", "אפור", "Minimalist", "Casual", "Spring-Essentials", "חלק")
));

// 20. נעלי עבודה מעוצבות - Office/Classic
productService.addProductToDB(new Product(
    "M-SH-520", "נעלי עור Derby Beige", "imagesens-derby-beige",
    "נעליים חצי-רשמיות. מראה קלאסי שמתאים לפגישות עסקיות או ליום עבודה משרדי.",
    4199, // 419.90 ₪
    Arrays.asList("גברים", "נעליים", "בז'", "Classic", "Office", "Spring-Essentials", "חלק")
));

// 21. נעלי ספורט קלות - Gym/Summer
productService.addProductToDB(new Product(
    "M-SH-521", "נעלי אימון White Air", "imagesens-gym-white",
    "נעליים קלות משקל עם אוורור מקסימלי. אידיאליות לאימון בקיץ.",
    4599, // 459.90 ₪
    Arrays.asList("גברים", "נעליים", "לבן", "Streetwear", "Gym", "Summer", "חלק")
));

// 22. מגפי צ'לסי - Night-Out/Elegant
productService.addProductToDB(new Product(
    "M-SH-522", "מגפי צ'לסי Black Suede", "imagesens-chelsea-black",
    "מגפיים אלגנטיים ללא שרוכים. מראה יוקרתי ומתוחכם ליציאה בערב.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Elegant", "Night-Out", "Winter", "חלק")
));

// 23. נעלי שייט - Vacation/Classic
productService.addProductToDB(new Product(
    "M-SH-523", "נעלי שייט Navy Classic", "imagesens-boat-navy",
    "נעליים קלאסיות לחופשות. סוליית גומי לבנה למניעת החלקה ומראה Vacation מושלם.",
    3399, // 339.90 ₪
    Arrays.asList("גברים", "נעליים", "כחול", "Classic", "Vacation", "Summer", "חלק")
));

// 24. סניקרס בעיצוב נקי - Minimalist/Office
productService.addProductToDB(new Product(
    "M-SH-524", "סניקרס Minimalist Olive", "imagesens-minimal-olive",
    "סניקרס בצבע זית עמוק. שילוב בין נוחות של נעלי ספורט למראה שמתאים למשרד.",
    3699, // 369.90 ₪
    Arrays.asList("גברים", "נעליים", "ירוק זית", "Minimalist", "Office", "Autumn", "חלק")
));

// 25. נעלי חורף עמידות - Winter/Classic
productService.addProductToDB(new Product(
    "M-SH-525", "מגפי עור Heavy Winter", "imagesens-winter-boots",
    "מגפיים חזקים ועמידים למים. תמיכה מקסימלית והגנה מהקור בימי החורף הקשים.",
    5899, // 589.90 ₪
    Arrays.asList("גברים", "נעליים", "שחור", "Classic", "Winter", "חלק")
));

// 1. נעלי עקב - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-SH-601", "נעלי עקב Stiletto", "imagesomens-heels",
    "נעלי עקב קלאסיות ויוקרתיות. מראה נשי ואלגנטי שחובה לכל חתונה או אירוע ערב.",
    4299, // 429.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Elegant", "Wedding", "All-Season", "חלק")
));

// 2. סניקרס פלטפורמה - Streetwear
productService.addProductToDB(new Product(
    "W-SH-602", "סניקרס פלטפורמה לבנות", "imagesomens-platform",
    "סניקרס עם סוליה עבה וטרנדית. מוסיפות גובה וסטייל לכל לוק יומיומי.",
    3599, // 359.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Streetwear", "Casual", "Spring-Essentials", "חלק")
));

// 3. נעלי בובה (Flats) - Office/Minimalist
productService.addProductToDB(new Product(
    "W-SH-603", "נעלי בובה קלאסיות", "imagesomens-flats",
    "נעליים שטוחות ונוחות במיוחד. עיצוב נקי שמתאים ליום עבודה ארוך במשרד.",
    2299, // 229.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Minimalist", "Office", "Spring-Essentials", "חלק")
));

// 4. מגפיים גבוהים - Winter/Classic
productService.addProductToDB(new Product(
    "W-SH-604", "מגפי עור גבוהים", "imagesomens-boots",
    "מגפיים שמגיעים עד הברך. מחממים מאוד ומשדרגים כל הופעה חורפית.",
    5999, // 599.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Classic", "Winter", "חלק")
));

// 5. סנדלי עקב - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SH-605", "סנדלי עקב רצועות דקות", "imagesomens-strappy-heels",
    "סנדלים עדינים ליציאה בלילה. מראה זוהר שמתאים לשמלות ומכנסי סאטן.",
    3899, // 389.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Elegant", "Night-Out", "Summer", "חלק")
));

// 6. נעלי אימון - Gym
productService.addProductToDB(new Product(
    "W-SH-606", "נעלי אימון קלות", "imagesomens-running",
    "נעליים גמישות עם בלימת זעזועים. מושלמות לשיעורי סטודיו או ריצה במכון.",
    4599, // 459.90 ₪
    Arrays.asList("נשים", "נעליים", "אפור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 7. כפכפי פלטפורמה - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SH-607", "כפכפי נוחות לקיץ", "imagesomens-slides",
    "כפכפים מעוצבים עם סוליה אנטומית. אידיאליים לחופשה, לים או לטיול בעיר.",
    1899, // 189.90 ₪
    Arrays.asList("נשים", "נעליים", "ירוק זית", "Minimalist", "Vacation", "Summer", "חלק")
));

// 8. מגפוני קרסול (Ankle Boots) - Autumn
productService.addProductToDB(new Product(
    "W-SH-608", "מגפוני זמש חומים", "imagesomens-ankle-boots",
    "מגפונים עם עקב קטן ונוח. משתלבים מעולה עם ג'ינס או חצאית בעונת הסתיו.",
    4199, // 419.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Classic", "Autumn", "חלק")
));

// 9. סניקרס צבעוניות - Streetwear
productService.addProductToDB(new Product(
    "W-SH-609", "סניקרס רטרו צבעוניות", "imagesomens-retro-sneakers",
    "עיצוב נועז עם שילוב צבעים. מוסיפות עניין וסטייל לכל לוק יומיומי משוחרר.",
    3799, // 379.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול", "Streetwear", "Casual", "All-Season", "חלק")
));

// 10. נעלי מוקסין - Office/Elegant
productService.addProductToDB(new Product(
    "W-SH-610", "נעלי מוקסין עור", "imagesomens-moccasins",
    "מראה מקצועי ומתוחכם. נעליים נוחות שמתאימות במיוחד לפגישות עסקיות.",
    3399, // 339.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Elegant", "Office", "Winter", "חלק")
));

// 11. נעלי עקב לבנות - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-SH-611", "נעלי עקב Ivory Pearl", "imagesomens-heels-white",
    "נעלי עקב יוקרתיות בגימור פנינה. הבחירה המושלמת לכלה או לאירוע ערב אלגנטי.",
    4599, // 459.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Elegant", "Wedding", "Spring-Essentials", "חלק")
));

// 12. סניקרס יומיומיות - Casual/Minimalist
productService.addProductToDB(new Product(
    "W-SH-612", "סניקרס Clean Beige", "imagesomens-sneakers-beige",
    "נעליים נוחות לכל יום. עיצוב מינימליסטי בצבע בז' שמתאים לכל אאוטפיט.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Minimalist", "Casual", "Autumn", "חלק")
));

// 13. נעלי אימון - Gym/Streetwear
productService.addProductToDB(new Product(
    "W-SH-613", "נעלי אימון Black Performance", "imagesomens-gym-black",
    "נעלי ספורט גמישות ומאווררות. מתאימות לאימוני כושר או ללוק סטריטוור יומיומי.",
    4299, // 429.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Streetwear", "Gym", "All-Season", "חלק")
));

// 14. כפכפי נוחות - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SH-614", "כפכפי Vacation White", "imagesomens-slides-white",
    "כפכפים מעוצבים וקלילים. אידיאליים לים, לבריכה או לחופשת הקיץ שלך.",
    1599, // 159.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Minimalist", "Vacation", "Summer", "חלק")
));

// 15. מגפיים גבוהים - Winter/Classic
productService.addProductToDB(new Product(
    "W-SH-615", "מגפי עור גבוהים Navy", "imagesomens-boots-navy",
    "מגפיים שמגיעים עד הברך. מחממים מאוד ומשדרגים כל הופעה חורפית אלגנטית.",
    5799, // 579.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול", "Classic", "Winter", "חלק")
));

// 16. נעלי יציאה - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SH-616", "סנדלי עקב Black Night", "imagesomens-sandals-black",
    "סנדלים עדינים עם עקב דק. מראה זוהר ומרשים שמתאים ליציאה בערב.",
    3899, // 389.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Elegant", "Night-Out", "Summer", "חלק")
));

// 17. סניקרס פלטפורמה - Streetwear/Oversize
productService.addProductToDB(new Product(
    "W-SH-617", "סניקרס פלטפורמה Grey", "imagesomens-platform-grey",
    "סניקרס טרנדיות עם סוליה עבה. מוסיפות סטייל וגובה ללוק אורבני משוחרר.",
    3699, // 369.90 ₪
    Arrays.asList("נשים", "נעליים", "אפור", "Streetwear", "Oversize", "Spring-Essentials", "חלק")
));

// 18. נעלי בובה - Office/Minimalist
productService.addProductToDB(new Product(
    "W-SH-618", "נעלי בובה Classic Black", "imagesomens-flats-black",
    "נעליים שטוחות ונוחות. פתרון מעולה ליום עבודה ארוך במשרד שדורש מראה נקי.",
    2499, // 249.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Minimalist", "Office", "All-Season", "חלק")
));

// 19. מגפוני קרסול - Autumn/Classic
productService.addProductToDB(new Product(
    "W-SH-619", "מגפוני זמש Beige Autumn", "imagesomens-ankle-beige",
    "מגפונים עם עקב קטן. עיצוב קלאסי שמתאים במיוחד לעונות המעבר ולמזג אוויר קריר.",
    4399, // 439.90 ₪
    Arrays.asList("נשים", "נעליים", "בז'", "Classic", "Autumn", "חלק")
));

// 20. נעלי מוקסין - Office/Elegant
productService.addProductToDB(new Product(
    "W-SH-620", "נעלי מוקסין Navy Patent", "imagesomens-moccasin-navy",
    "נעליים מקצועיות בגימור מבריק. מראה מתוחכם ויוקרתי שמתאים לפגישות עסקיות.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול", "Elegant", "Office", "Winter", "חלק")
));

// 21. סנדלי חוף צבעוניים - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SH-621", "סנדלי Vacation Olive Green", "imagesomens-sandals-olive",
    "סנדלים קלילים עם רצועות. צבע ירוק זית מודרני שמתאים לחופשות קיץ מרעננות.",
    1799, // 179.90 ₪
    Arrays.asList("נשים", "נעליים", "ירוק זית", "Minimalist", "Vacation", "Summer", "חלק")
));

// 22. נעלי ספורט לבנות - Gym/Casual
productService.addProductToDB(new Product(
    "W-SH-622", "סניקרס White Sporty", "imagesomens-sporty-white",
    "נעליים המשלבות מראה ספורטיבי ונוחות של יומיום. מושלמות להליכה או לאימון קל.",
    3399, // 339.90 ₪
    Arrays.asList("נשים", "נעליים", "לבן", "Streetwear", "Gym", "Spring-Essentials", "חלק")
));

// 23. נעלי ערב - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SH-623", "נעלי עקב Grey Suede", "imagesomens-heels-grey",
    "נעלי עקב מזמש יוקרתי. מראה עדין ומלוטש ליציאות ערב חגיגיות.",
    4199, // 419.90 ₪
    Arrays.asList("נשים", "נעליים", "אפור", "Elegant", "Night-Out", "Autumn", "חלק")
));

// 24. מגפיים חסיני מים - Winter/Classic
productService.addProductToDB(new Product(
    "W-SH-624", "מגפי חורף Black Shield", "imagesomens-winter-black",
    "מגפיים עמידים ונוחים במיוחד לימים גשומים. שומרים על כף הרגל חמה ויבשה.",
    5299, // 529.90 ₪
    Arrays.asList("נשים", "נעליים", "שחור", "Classic", "Winter", "חלק")
));

// 25. סניקרס מעוצבות - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "W-SH-625", "סניקרס Navy Glam", "imagesomens-glam-navy",
    "סניקרס בעיצוב ייחודי עם נגיעות מבריקות. הבחירה המנצחת למי שרוצה סטייל ונוחות בלילה.",
    3999, // 399.90 ₪
    Arrays.asList("נשים", "נעליים", "כחול", "Streetwear", "Night-Out", "All-Season", "חלק")
));
}

private void seedWinterCoatsAndSuits() 
{
    // 1. מעיל טרנץ' קלאסי - Office/Elegant
productService.addProductToDB(new Product(
    "M-CO-401", "מעיל טרנץ' בריטי", "images-trench",
    "מעיל ארוך דוחה מים, בד כותנה גבדין איכותי. מראה מחויט ויוקרתי.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "בז'", "Regular-Fit", "כותנה", "Office", "Elegant", "Winter", "חלק")
));

// 2. מעיל פאפר (Puffer) - Streetwear/Winter
productService.addProductToDB(new Product(
    "M-CO-402", "מעיל פאפר נפוח Heavy", "images-puffer",
    "מעיל מרופד בבידוד תרמי מקסימלי. בד חיצוני אטום לרוח.",
    4899, // 489.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Oversize", "כותנה", "Streetwear", "Winter", "חלק")
));

// 3. חליפת שלושה חלקים - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SU-501", "חליפת Premium תלת-חלקית", "images-suit-3p",
    "סט הכולל ז'קט, וסט ומכנסיים. בד צמר מרינו דק ונושם.",
    8999, // 899.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "All-Season", "חלק")
));

// 4. מעיל עור (Biker) - Night-Out/Streetwear
productService.addProductToDB(new Product(
    "M-CO-403", "ג'קט עור Raw Style", "images-leather",
    "מעיל עור איכותי עם רוכסנים מוכספים. מראה מחוספס ליציאה.",
    7599, // 759.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Slim-Fit", "דנים", "Night-Out", "Streetwear", "Autumn", "חלק")
));

// 5. חליפת פשתן קלילה - Vacation/Elegant
productService.addProductToDB(new Product(
    "M-SU-502", "חליפת פשתן Cuban-White", "images-linen-suit",
    "חליפה לאירועי צהריים או חופשות. בד פשתן אוורירי.",
    5299, // 529.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "לבן", "Regular-Fit", "פשתן", "Vacation", "Elegant", "Summer", "חלק")
));

// --- מעילים לגברים ---

// 6. מעיל פאפר נפוח - Streetwear/Winter
productService.addProductToDB(new Product(
    "M-CO-404", "מעיל פאפר Oversize שחור", "images-puffer-black",
    "מעיל חורף נפוח עם בידוד תרמי. בד חיצוני אטום, גזרה רחבה ונוחה.",
    4999, // 499.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Oversize", "כותנה", "Streetwear", "Winter", "חלק")
));

// 7. מעיל רוח קל - Casual/Autumn
productService.addProductToDB(new Product(
    "M-CO-405", "מעיל רוח Storm-Shell", "images-rain-navy",
    "מעיל דק חסין מים ורוח. עיצוב מינימליסטי שמתאים לעונת המעבר.",
    3299, // 329.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "כחול", "Minimalist", "כותנה", "Casual", "Autumn", "חלק")
));

// 8. מעיל צמר אלגנטי - Elegant/Winter
productService.addProductToDB(new Product(
    "M-CO-406", "מעיל צמר מרינו אפור", "images-wool-grey",
    "מעיל ארוך מחויט מצמר מרינו איכותי. מראה יוקרתי ומחמם מאוד.",
    6899, // 689.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "אפור", "Elegant", "צמר מרינו", "Classic", "Winter", "חלק")
));

// 9. ג'קט דנים מרופד - Streetwear/Autumn
productService.addProductToDB(new Product(
    "M-CO-407", "ג'קט ג'ינס עם בטנת פליז", "images-denim-winter",
    "ג'קט דנים קלאסי עם ריפוד פנימי מחמם. מראה מחוספס ויומיומי.",
    3899, // 389.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "כחול", "Regular-Fit", "דנים", "Streetwear", "Autumn", "חלק")
));

// 10. פארקה חורפית - Casual/Winter
productService.addProductToDB(new Product(
    "M-CO-408", "מעיל פארקה Arctic", "images-parka-olive",
    "מעיל חורף ארוך עם כובע פרווה (סינתטית). בד עמיד במיוחד.",
    5999, // 599.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "ירוק זית", "Oversize", "כותנה", "Casual", "Winter", "חלק")
));

// 11. מעיל עור ליציאה - Night-Out/Elegant
productService.addProductToDB(new Product(
    "M-CO-409", "ג'קט עור Slim-Fit", "images-leather-black",
    "ג'קט עור יוקרתי בגזרה צמודה. מתאים להופעה מרשימה בלילה.",
    7999, // 799.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Slim-Fit", "דנים", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 12. מעיל בומבר (Bomber) - Streetwear/Spring
productService.addProductToDB(new Product(
    "M-CO-410", "ג'קט בומבר קלאסי", "images-bomber-olive",
    "ג'קט קל עם מנג'טים בצווארון ובשרוולים. מראה צבאי-אורבני.",
    3599, // 359.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "ירוק זית", "Regular-Fit", "כותנה", "Streetwear", "Spring-Essentials", "חלק")
));

// 13. מעיל גשם מינימליסטי - Minimalist/Autumn
productService.addProductToDB(new Product(
    "M-CO-411", "מעיל גשם Clean Cut", "images-rain-beige",
    "עיצוב נקי ופשוט בצבע בז'. דוחה מים וקל משקל.",
    3199, // 319.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "בז'", "Minimalist", "כותנה", "Casual", "Autumn", "חלק")
));

// 14. חליפת טוקסידו - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SU-503", "חליפת טוקסידו Black-Tie", "images-tuxedo",
    "חליפת ערב רשמית עם דש סאטן. הכי אלגנטי שיש לחתונה.",
    9499, // 949.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "שחור", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "Winter", "חלק")
));

// 15. חליפת עסקים אפורה - Office/Elegant
productService.addProductToDB(new Product(
    "M-SU-504", "חליפת צמר מרינו אפורה", "images-suit-grey",
    "חליפה מחויטת למשרד. בד צמר דק שמתאים לכל עונות השנה.",
    7299, // 729.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Elegant", "All-Season", "חלק")
));

// 16. חליפה משובצת - Wedding/Classic
productService.addProductToDB(new Product(
    "M-SU-505", "חליפת משבצות Glen Plaid", "images-suit-check",
    "מראה קלאסי ומתוחכם עם דוגמת משבצות עדינה.",
    8199, // 819.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "אפור", "Classic", "כותנה", "Wedding", "Classic", "Autumn", "משובץ")
));

// 17. חליפת פשתן כחולה - Vacation/Elegant
productService.addProductToDB(new Product(
    "M-SU-506", "חליפת פשתן Navy Vacation", "images-suit-linen",
    "חליפה קלילה לאירועי קיץ או חופשות יוקרתיות.",
    5499, // 549.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול", "Regular-Fit", "פשתן", "Vacation", "Elegant", "Summer", "חלק")
));

// 18. חליפת Slim-Fit מודרנית - Night-Out/Elegant
productService.addProductToDB(new Product(
    "M-SU-507", "חליפת Midnight Blue", "images-suit-night",
    "חליפה צמודה ומחמיאה ליציאות ערב חגיגיות.",
    6899, // 689.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול", "Slim-Fit", "כותנה", "Night-Out", "Elegant", "Spring-Essentials", "חלק")
));

// 19. חליפה מינימליסטית לבנה - Wedding/Summer
productService.addProductToDB(new Product(
    "M-SU-508", "חליפת קיץ לבנה", "images-suit-white",
    "חליפה למראה נקי ומרשים, מתאימה במיוחד לחתונות חוף.",
    5799, // 579.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "לבן", "Regular-Fit", "פשתן", "Wedding", "Minimalist", "Summer", "חלק")
));

// 20. חליפת קורדרוי חורפית - Classic/Winter
productService.addProductToDB(new Product(
    "M-SU-509", "חליפת קורדרוי חומה", "images-suit-cord",
    "בד עבה ומחמם במראה רטרו קלאסי.",
    6299, // 629.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "בז'", "Classic", "כותנה", "Classic", "Winter", "חלק")
));

// 21. מעיל צמר יוקרתי - Elegant/Winter
productService.addProductToDB(new Product(
    "M-CO-412", "מעיל צמר מרינו Double-Breasted", "images-wool-black",
    "מעיל רציני ומחמם עם כפתרה כפולה. בד: 100% צמר מרינו. גזרה מחויטת ויוקרתית.",
    7499, // 749.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Regular-Fit", "צמר מרינו", "Elegant", "Winter", "חלק")
));

// 22. חליפת חתונה כחולה - Wedding/Elegant
productService.addProductToDB(new Product(
    "M-SU-510", "חליפת Navy Royal Wedding", "images-suit-royal",
    "חליפה מרשימה בצבע כחול עמוק. בד: צמר מרינו דק. גזרת Slim-Fit מחמיאה במיוחד.",
    8599, // 859.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "כחול", "Slim-Fit", "צמר מרינו", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 23. ג'קט רוח אורבני - Streetwear/Autumn
productService.addProductToDB(new Product(
    "M-CO-413", "מעיל רוח Street-Utility", "images-wind-olive",
    "מעיל קל ונוח לימים קרירים. בד: כותנה בטכנולוגיית דחיית מים. מראה סטריטוור מודרני.",
    3799, // 379.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "ירוק זית", "Oversize", "כותנה", "Streetwear", "Autumn", "חלק")
));

// 24. חליפת משרד אפורה - Office/Classic
productService.addProductToDB(new Product(
    "M-SU-511", "חליפת צ'ק Classic Office", "images-suit-grey-check",
    "חליפה מקצועית עם דוגמת משבצות עדינה. בד: כותנה עמידה.",
    6999, // 699.90 ₪
    Arrays.asList("גברים", "חליפות", "ארוך", "אפור", "Regular-Fit", "כותנה", "Office", "Classic", "Autumn", "משובץ")
));

// 25. מעיל אימון קל - Gym/Spring
productService.addProductToDB(new Product(
    "M-CO-414", "מעיל Performance Gym Jacket", "images-gym-black",
    "ג'קט ספורטיבי קליל וגמיש. בד מנדף זיעה, מושלם לאימון בחוץ בעונת המעבר.",
    2899, // 289.90 ₪
    Arrays.asList("גברים", "מעילים", "ארוך", "שחור", "Slim-Fit", "כותנה", "Gym", "Spring-Essentials", "חלק")
));

// 1. מעיל צמר ארוך - Elegant/Winter
productService.addProductToDB(new Product(
    "W-CO-601", "מעיל צמר מרינו מלטון", "images-wool-coat",
    "מעיל ארוך ומחמם עם חגורת מותן. מראה יוקרתי ונצחי.",
    7299, // 729.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Elegant", "Office", "Winter", "חלק")
));

// 2. חליפת בלייזר ומכנסיים - Office/Minimalist
productService.addProductToDB(new Product(
    "W-SU-701", "חליפת Power Suit נשית", "images-suit-office",
    "סט בלייזר מחויט ומכנסי סיגר. בד כותנה עמיד.",
    6199, // 619.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "כחול", "Regular-Fit", "כותנה", "Office", "Minimalist", "Autumn", "חלק")
));

// 3. מעיל רוח קרופ (Crop) - Gym/Streetwear
productService.addProductToDB(new Product(
    "W-CO-602", "מעיל רוח Crop Performance", "images-windbreaker",
    "מעיל קל משקל לאימונים או יומיום. בד מנדף זיעה.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "ירוק זית", "Slim-Fit", "סאטן", "Gym", "Streetwear", "Spring-Essentials", "חלק")
));

// 4. חליפת סאטן לאירועים - Wedding/Night-Out
productService.addProductToDB(new Product(
    "W-SU-702", "חליפת סאטן Champagne", "images-satin-suit",
    "סט בלייזר ומכנסיים רחבים מבד סאטן מבריק.",
    6899, // 689.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "בז'", "Wide-Leg", "סאטן", "Wedding", "Night-Out", "Spring-Essentials", "חלק")
));

// 5. מעיל פרווה מלאכותית - Winter/Night-Out
productService.addProductToDB(new Product(
    "W-CO-603", "מעיל Faux-Fur יוקרתי", "images-fur",
    "מעיל פרווה סינתטית רך ומחמם מאוד ליציאות בערב.",
    5999, // 599.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "שחור", "Oversize", "צמר מרינו", "Night-Out", "Winter", "חלק")
));

// --- מעילים לנשים ---

// 6. מעיל טרנץ' קלאסי - Office/Minimalist
productService.addProductToDB(new Product(
    "W-CO-604", "מעיל טרנץ' Beige Classic", "images-trench-beige",
    "מעיל ארוך עם חגורה, דוחה מים. פריט חובה לעונת המעבר.",
    5299, // 529.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "בז'", "Regular-Fit", "כותנה", "Office", "Minimalist", "Spring-Essentials", "חלק")
));

// 7. מעיל פאפר קצר - Streetwear/Winter
productService.addProductToDB(new Product(
    "W-CO-605", "מעיל פאפר Crop שחור", "images-puffer-crop",
    "גזרה קצרה ומודרנית, מחמם מאוד עם מילוי תרמי.",
    4499, // 449.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "שחור", "Oversize", "כותנה", "Streetwear", "Winter", "חלק")
));

// 8. מעיל צמר יוקרתי - Elegant/Winter
productService.addProductToDB(new Product(
    "W-CO-606", "מעיל צמר מרינו ארוך", "images-wool-long",
    "מעיל נשפך ומחמם עם צווארון רחב. מראה אלגנטי ומרשים.",
    7599, // 759.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Elegant", "צמר מרינו", "Classic", "Winter", "חלק")
));

// 9. מעיל פרווה מלאכותית - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-CO-607", "מעיל Faux-Fur יוקרתי", "images-fur-white",
    "מעיל פרווה סינתטית בלבן בוהק. מושלם ליציאות ערב בחורף.",
    6299, // 629.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "לבן", "Oversize", "צמר מרינו", "Night-Out", "Elegant", "Winter", "חלק")
));

// 10. מעיל רוח ספורטיבי - Gym/Spring
productService.addProductToDB(new Product(
    "W-CO-608", "ג'קט רוח Performance", "images-rain-olive",
    "מעיל קל משקל לאימונים בחוץ או ליומיום.",
    3399, // 339.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "ירוק זית", "Slim-Fit", "סאטן", "Gym", "Spring-Essentials", "חלק")
));

// 11. בלייזר ארוך (Oversize) - Streetwear/Minimalist
productService.addProductToDB(new Product(
    "W-CO-609", "בלייזר Oversize אפור", "images-blazer-grey",
    "ז'קט רחב במראה מודרני. יכול לשמש כמעיל קל או כחלק מחליפה.",
    4199, // 419.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Oversize", "כותנה", "Streetwear", "Minimalist", "Autumn", "חלק")
));

// 12. מעיל דנים מעוצב - Casual/Autumn
productService.addProductToDB(new Product(
    "W-CO-610", "ג'קט ג'ינס High-End", "images-denim-blue",
    "ג'קט דנים כחול עם שטיפה ייחודית וגזרה מחמיאה.",
    3699, // 369.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "כחול", "Regular-Fit", "דנים", "Casual", "Autumn", "חלק")
));

// 13. מעיל גשם צהוב/בז' - Casual/Autumn
productService.addProductToDB(new Product(
    "W-CO-611", "מעיל גשם Urban Rain", "images-rain-beige",
    "מעיל פרקטי ואופנתי לימים גשומים בעיר.",
    3199, // 319.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "בז'", "Regular-Fit", "כותנה", "Casual", "Autumn", "חלק")
));

// --- חליפות לנשים ---

// 14. חליפת סאטן לערב - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SU-703", "חליפת סאטן Midnight", "images-satin-suit",
    "סט בלייזר ומכנסיים נשפכים מבד סאטן מבריק.",
    6999, // 699.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "כחול", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "All-Season", "חלק")
));

// 15. חליפת פשתן קיצית - Vacation/Minimalist
productService.addProductToDB(new Product(
    "W-SU-704", "חליפת פשתן White Sands", "images-linen-suit",
    "חליפה לבנה ונושמת, מושלמת לחופשות או לאירועי צהריים.",
    5899, // 589.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "לבן", "Wide-Leg", "פשתן", "Vacation", "Minimalist", "Summer", "חלק")
));

// 16. חליפה מחויטת למשרד - Office/Elegant
productService.addProductToDB(new Product(
    "W-SU-705", "חליפת Power Suit אפורה", "images-office-suit",
    "בלייזר ומכנסי סיגר תואמים מצמר מרינו דק.",
    7499, // 749.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "אפור", "Regular-Fit", "צמר מרינו", "Office", "Elegant", "Autumn", "חלק")
));

// 17. חליפת חתונה לבנה - Wedding/Elegant
productService.addProductToDB(new Product(
    "W-SU-706", "חליפת כלה מחויטת", "images-bridal-suit",
    "חליפה לבנה יוקרתית ומרשימה, אלטרנטיבה מודרנית לשמלה.",
    8999, // 899.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "לבן", "Slim-Fit", "סאטן", "Wedding", "Elegant", "Spring-Essentials", "חלק")
));

// 18. חליפה משובצת - Classic/Office
productService.addProductToDB(new Product(
    "W-SU-707", "חליפת משבצות Heritage", "images-suit-check",
    "מראה בריטי קלאסי עם דוגמת משבצות עדינה.",
    6599, // 659.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "בז'", "Regular-Fit", "כותנה", "Office", "Classic", "Winter", "משובץ")
));

// 19. חליפה פרחונית - Vacation/Summer
productService.addProductToDB(new Product(
    "W-SU-708", "חליפת ויסקוזה פרחונית", "images-floral-suit",
    "חליפה קלילה וצבעונית עם הדפס פרחים מרענן.",
    4899, // 489.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "לבן", "Wide-Leg", "כותנה", "Vacation", "Summer", "פרחוני")
));

// 20. חליפת קטיפה (סאטן) - Night-Out/Winter
productService.addProductToDB(new Product(
    "W-SU-709", "חליפת סאטן שחורה", "images-black-satin-suit",
    "חליפה יוקרתית ומבריקה ליציאות ערב בחורף.",
    7199, // 719.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "שחור", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "Winter", "חלק")
));

// 21. מעיל טרנץ' יוקרתי - Office/Elegant
productService.addProductToDB(new Product(
    "W-CO-612", "מעיל טרנץ' Camel Wool", "images-trench-camel",
    "מעיל צמר ארוך בצבע בז' קלאסי. גזרה נצחית עם חגורה במותן, מחמם ומחמיא.",
    7999, // 799.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "בז'", "Regular-Fit", "צמר מרינו", "Elegant", "Winter", "חלק")
));

// 22. חליפת ערב מבריקה - Night-Out/Elegant
productService.addProductToDB(new Product(
    "W-SU-710", "חליפת סאטן Jet Black", "images-suit-night-black",
    "סט בלייזר ומכנסי סאטן מבריקים ליציאה. מראה עוצמתי וזוהר ללילה.",
    6799, // 679.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "שחור", "Wide-Leg", "סאטן", "Night-Out", "Elegant", "Winter", "חלק")
));

// 23. מעיל פאפר מודרני - Streetwear/Winter
productService.addProductToDB(new Product(
    "W-CO-613", "מעיל פאפר Grey Cloud", "images-puffer-grey",
    "מעיל נפוח ורך במיוחד. בד חיצוני דוחה מים, גזרת Oversize טרנדית.",
    4699, // 469.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "אפור", "Oversize", "כותנה", "Streetwear", "Winter", "חלק")
));

// 24. חליפת עסקים כחולה - Office/Elegant
productService.addProductToDB(new Product(
    "W-SU-711", "חליפת Power Navy Tailored", "images-suit-navy",
    "בלייזר מחויט ומכנסיים תואמים. בד: כותנה איכותית. מראה מקצועי ונקי.",
    7299, // 729.90 ₪
    Arrays.asList("נשים", "חליפות", "ארוך", "כחול", "Regular-Fit", "כותנה", "Office", "Elegant", "Spring-Essentials", "חלק")
));

// 25. מעיל חופשה קליל - Vacation/Summer
productService.addProductToDB(new Product(
    "W-CO-614", "מעיל קנבס Summer White", "images-coat-white",
    "ג'קט ארוך ודק מבד נושם. מושלם לערבים קרירים בחופשה או על החוף.",
    3499, // 349.90 ₪
    Arrays.asList("נשים", "מעילים", "ארוך", "לבן", "Regular-Fit", "פשתן", "Vacation", "Summer", "חלק")
));
}
}