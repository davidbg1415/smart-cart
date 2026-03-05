// package davidbg.smartcart.services;

// import davidbg.smartcart.datamodels.Product;
// import davidbg.smartcart.repositories.ProductRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import java.util.Arrays;

// @Component
/// public class DataBaseInitializerService implements CommandLineRunner 
// {
//     private final ProductService productService;
//     private final ProductRepository productRepository;

//     public DataBaseInitializerService(ProductService productService, ProductRepository productRepository) 
//     {
//         this.productService = productService;
//         this.productRepository = productRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception 
//     {
//         // הבדיקה הקריטית: אם יש כבר מוצרים, אל תעשה כלום!
//         if (productRepository.count() == 0) 
//         {
//             System.out.println(" מסד הנתונים ריק - מתחיל להזרים 100 חולצות...");
            
//             seedMensShirts();
//             seedWomensShirts();
           
            
            
//             System.out.println("✅ סיימתי! 100 חולצות מוכנות ב-Atlas.");
//         } 
//         else 
//         {
//             System.out.println(" כבר יש נתונים ( " + productRepository.count() + " מוצרים) - מדלג על האתחול.");
//         }
//     }

//    private void seedMensShirts() 
//    {
//     // 1. חולצת פשתן קובנית - הלהיט של הקיץ
//     productService.addProductToDB(new Product(
//         "M-SH-201", "חולצת פשתן Cuban Collar", "https://images.clothes.com/m/linen-cuban.jpg",
//         "חולצה אוורירית מחקר שוק זארה. בד: 100% פשתן אירופאי מעובד. גזרת Relaxed, צווארון פתוח.",
//         1699, // 169.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "בז'", "Relaxed-Fit", "100% פשתן", "Summer", "Vacation", "קליל")
//     ));

//     // 2. טי-שירט Oversize כבדה - סטריטוור
//     productService.addProductToDB(new Product(
//         "M-SH-202", "טי-שירט Heavyweight שטופה", "https://images.clothes.com/m/heavy-tee.jpg",
//         "חולצת סטריטוור במשקל 300 GSM. בד: 100% כותנה אורגנית. מראה Vintage Wash עם כתפיים שמוטות.",
//         1299, // 129.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "אפור גרפיט", "Oversize", "100% כותנה", "Streetwear", "Vintage", "יומיום")
//     ));

//     // 3. חולצת אוקספורד Slim-Fit - למשרד/אירוע קל
//     productService.addProductToDB(new Product(
//         "M-SH-203", "חולצת אוקספורד יוקרתית", "https://images.clothes.com/m/oxford-slim.jpg",
//         "חולצה מחויטת לעבודה. בד: 98% כותנה, 2% אלסטן (Stretch). גזרה צמודה, צווארון קשיח.",
//         1999, // 199.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "כותנה-אלסטן", "Elegant", "Office", "Classic", "חלק")
//     ));

//     // 4. סריג פולו ריב - מראה "Old Money"
//     productService.addProductToDB(new Product(
//         "M-SH-204", "חולצת פולו סרוגה בטקסטורת ריב", "https://images.clothes.com/m/knit-polo.jpg",
//         "מראה אירופאי קלאסי. בד: ויסקוזה וניילון למגע רך וקריר. שרוול קצר עם סיומת מנג'ט.",
//         1599, // 159.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "כחול נייבי", "Regular-Fit", "ויסקוזה", "Smart-Casual", "Luxury", "פסים עדינים")
//     ));

//     // 5. חולצת ג'ינס Western - מראה מחוספס
//     productService.addProductToDB(new Product(
//         "M-SH-205", "חולצת ג'ינס דנים Western", "https://api.placeholder.com/denim-shirt",
//         "חולצת ג'ינס איכותית. בד: 100% כותנה קשיחה (Indigo Denim). שני כיסי חזה, סגירת תיקתקים.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "ג'ינס", "כחול דנים", "Regular-Fit", "100% כותנה", "Casual", "Outdoor", "חלק")
//     ));

//     // 6. חולצת שרוול ארוך Henley - בייסיק משודרג
//     productService.addProductToDB(new Product(
//         "M-SH-206", "חולצת הנלי ריב ארוכה", "https://api.placeholder.com/henley-grey",
//         "חולצה נוחה ליומיום. בד: 60% כותנה, 40% פוליאסטר. מפתח צוואר עם 3 כפתורים, בד טקסטורלי.",
//         1199, // 119.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "אפור מלנז'", "Slim-Fit", "כותנה-פוליאסטר", "Basic", "Home-Lounge", "יומיום")
//     ));

//     // 7. חולצת פלאנל משובצת - מראה "לומברג'ק"
//     productService.addProductToDB(new Product(
//         "M-SH-207", "חולצת פלאנל משובצת Heavy", "https://api.placeholder.com/flannel-shirt",
//         "חולצה חמה לחורף. בד: 100% כותנה עבה מוברשת. משבצות בגווני אדום ושחור, גזרה רחבה.",
//         1599, // 159.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "אדום-משובץ", "Oversize", "100% כותנה", "Winter", "Streetwear", "משובץ")
//     ));

//     // 8. גופיית ספורט מנדפת - Gym Wear
//     productService.addProductToDB(new Product(
//         "M-SH-208", "גופיית אימון Performance", "https://api.placeholder.com/gym-tank",
//         "לביצועים מקסימליים. בד: 100% פוליאסטר מנדף זיעה (Dry-Fit). גזרת גב שחיין לתנועה חופשית.",
//         899, // 89.90 NIS
//         Arrays.asList("גברים", "חולצות", "גופיות", "שחור", "Active", "פוליאסטר", "Gym", "Sport", "חלק")
//     ));

//     // 9. חולצת פולו סרוגה (Knit Polo)
//     productService.addProductToDB(new Product(
//         "M-SH-209", "חולצת פולו סרוגה בטקסטורת מעויינים", "https://api.placeholder.com/knit-polo-tan",
//         "מראה יוקרתי ושקט. בד: תערובת כותנה ו-ויסקוזה. גזרה ישרה עם סיומת מנג'ט בקיפול השרוול.",
//         1799, // 179.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "בז'", "Regular-Fit", "ויסקוזה", "Old-Money", "Luxury", "Smart-Casual")
//     ));

//     // 10. חולצת טי "מוסל פיט" (Muscle Fit)
//     productService.addProductToDB(new Product(
//         "M-SH-210", "טי-שירט Muscle Fit שחורה", "https://api.placeholder.com/muscle-tee",
//         "בד אלסטי במיוחד המדגיש את מבנה הגוף. בד: 95% כותנה, 5% לייקרה. שרוולים קצרים וצמודים.",
//         899, // 89.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "שחור", "Muscle-Fit", "כותנה-לייקרה", "Gym", "Basic", "יומיום")
//     ));

//     // 11. חולצת צווארון סיני (Mandarin Collar)
//     productService.addProductToDB(new Product(
//         "M-SH-211", "חולצת כפתורים צווארון סיני לבנה", "https://api.placeholder.com/mandarin-shirt",
//         "לוק נקי ומודרני. בד: 100% כותנת פופלין דקה. ללא צווארון מסורתי, כפתורים נסתרים.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "100% כותנה", "Minimalist", "Modern", "חלק")
//     ));

//     // 12. חולצת פלאנל משובצת "Over-Shirt"
//     productService.addProductToDB(new Product(
//         "M-SH-212", "ג'קט חולצה (Shacket) משובץ כחול", "https://api.placeholder.com/shacket-blue",
//         "חולצה עבה המשמשת כג'קט קל. בד: פלנל צמר סינתטי. גזרת Oversize, כיסים גדולים בחזה.",
//         2599, // 259.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "כחול-משובץ", "Oversize", "צמר-סינתטי", "Winter", "Streetwear", "משובץ")
//     ));

//     // 13. חולצת פופלין בגזרת Boxy
//     productService.addProductToDB(new Product(
//         "M-SH-213", "חולצת פופלין Boxy-Fit קצרה", "https://api.placeholder.com/m/boxy-poplin",
//         "מראה אורבני מודרני. בד: 100% כותנת פופלין פריכה. גזרה רחבה וקצרה עם כתפיים שמוטות.",
//         1599, // 159.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "ירוק מרווה", "Boxy-Fit", "100% כותנה", "Streetwear", "Minimalist", "קיץ")
//     ));

//     // 14. חולצת פולו עם רוכסן (Zip Polo)
//     productService.addProductToDB(new Product(
//         "M-SH-214", "חולצת פולו עם סגירת רוכסן", "https://api.placeholder.com/m/zip-polo",
//         "סטייל Smart-Casual נקי. בד: 100% כותנה בטקסטורת Pike. רוכסן מתכת איכותי במקום כפתורים.",
//         1799, // 179.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "אפור גרפיט", "Slim-Fit", "100% כותנה", "Office", "Classic", "חלק")
//     ));

//     // 15. חולצת "גרנדד" (Grandad Collar) מפשתן
//     productService.addProductToDB(new Product(
//         "M-SH-215", "חולצת פשתן צווארון סיני ארוכה", "https://api.placeholder.com/m/grandad-linen",
//         "חולצה קלילה ונושמת. בד: תערובת פשתן וכותנה. צווארון עגול ללא קיפול, שרוולים מתקפלים עם כפתור.",
//         2199, // 219.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "לבן", "Relaxed-Fit", "פשתן-כותנה", "Boho", "Vacation", "קליל")
//     ));

//     // 16. טי-שירט מנדפת זיעה (Compression)
//     productService.addProductToDB(new Product(
//         "M-SH-216", "חולצת אימון Compression צמודה", "https://api.placeholder.com/m/active-tee",
//         "מיועדת לאימונים עצימים. בד: 88% פוליאסטר, 12% אלסטן. טכנולוגיית מנדפת זיעה, בד גמיש לארבעה כיוונים.",
//         1299, // 129.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "שחור", "Compression", "פוליאסטר-אלסטן", "Gym", "Active", "ספורט")
//     ));

//     // 17. חולצת פלאנל משובצת (Buffalo Check)
//     productService.addProductToDB(new Product(
//         "M-SH-217", "חולצת פלאנל משבצות באפלו", "https://api.placeholder.com/m/buffalo-plaid",
//         "סטייל Workwear קלאסי. בד: 100% כותנה מוברשת עבה. משבצות אדום-שחור גדולות.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "אדום-שחור", "Regular-Fit", "100% כותנה", "Winter", "Casual", "משובץ")
//     ));

//     // 18. חולצת "ריזורט" (Resort Shirt) מודפסת
//     productService.addProductToDB(new Product(
//         "M-SH-218", "חולצת ריזורט בהדפס טרופי", "https://api.placeholder.com/m/resort-print",
//         "חולצה לחופשה. בד: 100% ויסקוזה נשפכת. הדפס עלי דקל, צווארון פתוח, כפתורי עץ.",
//         1499, // 149.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "צבעוני", "Relaxed-Fit", "ויסקוזה", "Summer", "Vacation", "הדפס")
//     ));

//     // 19. חולצת גולף (Turtleneck) צמר
//     productService.addProductToDB(new Product(
//         "M-SH-219", "סריג גולף צמר דק", "https://api.placeholder.com/m/turtleneck",
//         "מראה אירופאי אלגנטי. בד: 100% צמר מרינו. צווארון גבוה מתקפל, בד רך ונעים למגע.",
//         2999, // 299.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "כחול נייבי", "Slim-Fit", "צמר מרינו", "Luxury", "Winter", "Elegant")
//     ));

//     // 20. גופיית Racerback לאימון
//     productService.addProductToDB(new Product(
//         "M-SH-220", "גופיית Racerback אתלטית", "https://api.placeholder.com/m/active-tank",
//         "חופש תנועה מלא בכתפיים. בד: מיקרופייבר קל משקל. בד מנדף מהיר יבוש.",
//         799, // 79.90 NIS
//         Arrays.asList("גברים", "חולצות", "גופיות", "ניאון", "Athletic-Fit", "מיקרופייבר", "Gym", "Running", "ספורט")
//     ));

//     // 21. קפוצ'ון Heavyweight שטוף - Streetwear
//     productService.addProductToDB(new Product(
//         "M-SH-221", "קפוצ'ון Oversize וינטג' אפור", "https://api.placeholder.com/m/hoodie-vintage",
//         "קפוצ'ון כבד (450 GSM). בד: 100% כותנה עם בטנת פליז רכה. מראה שטוף, כיס קנגורו גדול.",
//         2499, // 249.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "אפור", "Oversize", "100% כותנה", "Streetwear", "Winter", "חם")
//     ));

//     // 22. חולצת קורדרוי (Corduroy) - Casual
//     productService.addProductToDB(new Product(
//         "M-SH-222", "חולצת קורדרוי חום כאמל", "https://api.placeholder.com/m/corduroy-shirt",
//         "חולצה בטקסטורת פסים דקה. בד: 100% כותנה קורדרוי. גזרת Regular, שני כיסי חזה עם כפתור.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "חום", "Regular-Fit", "קורדרוי", "Autumn", "Casual", "טקסטורה")
//     ));

//     // 23. סוודר V-Neck צמר מרינו - Elegant
//     productService.addProductToDB(new Product(
//         "M-SH-223", "סריג וי צמר מרינו דק", "https://api.placeholder.com/m/v-neck-wool",
//         "למראה אלגנטי מעל חולצה מכופתרת. בד: 100% צמר מרינו איטלקי. בד נושם ודק במיוחד.",
//         2999, // 299.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "ירוק זית", "Slim-Fit", "צמר מרינו", "Luxury", "Office", "Elegant")
//     ));

//     // 24. חולצת פופלין פסים - Business
//     productService.addProductToDB(new Product(
//         "M-SH-224", "חולצת פופלין פסים כחול-לבן", "https://api.placeholder.com/m/stripe-poplin",
//         "חולצת משרד קלאסית. בד: 100% כותנת פופלין. גזרת Tailored, פסי סיכה דקים, צווארון נוקשה.",
//         1799, // 179.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "פסים", "Tailored-Fit", "100% כותנה", "Office", "Classic", "Business")
//     ));

//     // 25. טי-שירט "בוקסי" בייסיק - Minimalist
//     productService.addProductToDB(new Product(
//         "M-SH-225", "טי-שירט Boxy-Fit לבן בייסיק", "https://api.placeholder.com/m/boxy-white",
//         "גזרה רבועה ומודרנית. בד: 100% כותנה עבה. שרוולים רחבים וקצרים, מפתח צוואר סגור.",
//         999, // 99.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "לבן", "Boxy-Fit", "100% כותנה", "Minimalist", "Basic", "יומיום")
//     ));

//     // 26. חולצת ג'ינס שחורה - Rugged
//     productService.addProductToDB(new Product(
//         "M-SH-226", "חולצת דנים שחורה שטופה", "https://api.placeholder.com/m/black-denim",
//         "מראה אורבני מחוספס. בד: 100% דנים כותנה. כפתורי תיקתק ממתכת, תפרים בולטים.",
//         2199, // 219.90 NIS
//         Arrays.asList("גברים", "חולצות", "ג'ינס", "שחור", "Regular-Fit", "דנים", "Streetwear", "Bold", "חלק")
//     ));

//     // 27. סוודר "קולור-בלוק" - Trendy
//     productService.addProductToDB(new Product(
//         "M-SH-227", "סריג קולור-בלוק גווני אדמה", "https://api.placeholder.com/m/colorblock-knit",
//         "עיצוב גאומטרי מודרני. בד: תערובת צמר ואקריליק. שילוב צבעי חום, בז' וקרם.",
//         2399, // 239.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "צבעוני", "Oversize", "צמר", "Trendy", "Winter", "גאומטרי")
//     ));

//     // 28. חולצת "שאקט" (Shacket) משובצת - Winter
//     productService.addProductToDB(new Product(
//         "M-SH-228", "ג'קט חולצה משובץ עבה", "https://api.placeholder.com/m/shacket-plaid",
//         "פריט מעבר מושלם. בד: פלנל צמר עבה עם בטנת סאטן. כיסי צד נסתרים.",
//         3299, // 329.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "משובץ", "Oversize", "צמר", "Winter", "Outdoor", "חם")
//     ));

//     // 29. חולצת קומפרשן לאימון
//     productService.addProductToDB(new Product(
//         "M-SH-229", "חולצת Compression אקטיב", "https://api.placeholder.com/m/compression-black",
//         "חולצת אימון צמודה במיוחד התומכת בשרירים. בד: 88% פוליאסטר ממוחזר, 12% אלסטן. מנדפת זיעה ומתייבשת במהירות.",
//         1399, // 139.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "שחור", "Compression", "Active", "Gym", "Performance", "חלק")
//     ));

//     // 30. סוודר צמות קלאסי (Cable Knit)
//     productService.addProductToDB(new Product(
//         "M-SH-230", "סוודר Cable Knit צמר", "https://api.placeholder.com/m/cable-knit-cream",
//         "סריג חורף כבד במראה אירופאי קלאסי. בד: 70% כותנה, 30% צמר. טקסטורת צמות בולטת, צווארון עגול.",
//         2899, // 289.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "שמנת", "Regular-Fit", "Cotton-Wool", "Winter", "Classic", "Old-Money")
//     ));

//     // 31. חולצת הוואי מודפסת (Rayon)
//     productService.addProductToDB(new Product(
//         "M-SH-231", "חולצת ריזורט רייון מודפסת", "https://api.placeholder.com/m/hawaiian-shirt",
//         "חולצה קלילה לחופשה. בד: 100% רייון (ויסקוזה איכותית). הדפס עלים טרופי, גזרה רחבה, כפתורי קליפה.",
//         1599, // 159.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "צבעוני", "Relaxed-Fit", "Rayon", "Summer", "Vacation", "הדפס")
//     ));

//     // 32. טי-שירט וינטג' להקה
//     productService.addProductToDB(new Product(
//         "M-SH-232", "טי-שירט Graphic Vintage", "https://api.placeholder.com/m/band-tee",
//         "מראה מכובס ומשופשף. בד: 100% כותנה כבדה. הדפס גרפי בסגנון רוק משנות ה-90, צווארון ריב עבה.",
//         1199, // 119.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "אפור פחם", "Oversize", "100% כותנה", "Streetwear", "Graphic", "Vintage")
//     ));

//     // 33. חולצת פולו רוכסן מודרנית
//     productService.addProductToDB(new Product(
//         "M-SH-233", "חולצת פולו עם רוכסן מתכת", "https://api.placeholder.com/m/zip-polo",
//         "סטייל נקי לעבודה. בד: 100% כותנה בטקסטורת Pike. רוכסן כסוף במקום כפתורים, ללא כיס.",
//         1799, // 179.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "כחול נייבי", "Slim-Fit", "100% כותנה", "Office", "Modern", "Minimalist")
//     ));

//     // 34. חולצת שמברה (Chambray)
//     productService.addProductToDB(new Product(
//         "M-SH-234", "חולצת שמברה כחולה", "https://api.placeholder.com/m/chambray-shirt",
//         "מראה דנים קליל. בד: 100% כותנת שמברה. בד דק דמוי ג'ינס, תפרים לבנים בולטים, כפתורי פנינה.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "כחול בהיר", "Regular-Fit", "Chambray", "Casual", "Workwear", "חלק")
//     ));

//     // 35. גופיית כדורסל רשת
//     productService.addProductToDB(new Product(
//         "M-SH-235", "גופיית Mesh אתלטית", "https://api.placeholder.com/m/mesh-tank",
//         "מתאימה לאימונים ולמראה רחוב. בד: 100% פוליאסטר רשת נושם. גזרה רחבה, סיומת פסים בצוואר.",
//         999, // 99.90 NIS
//         Arrays.asList("גברים", "חולצות", "גופיות", "שחור-לבן", "Athletic-Fit", "Polyester", "Sport", "Streetwear", "Basic")
//     ));

//     // 36. חולצת סאטן לערב
//     productService.addProductToDB(new Product(
//         "M-SH-236", "חולצת סאטן יוקרתית שחורה", "https://api.placeholder.com/m/satin-men",
//         "למראה ערב נוצץ ומתוחכם. בד: 100% סאטן משי סינתטי. בד נשפך עם ברק עדין, כפתורים נסתרים.",
//         2499, // 249.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "שחור", "Slim-Fit", "Satin", "Evening", "Night-Out", "Luxury")
//     ));

//     // 37. פלאנל משובצת כבדה
//     productService.addProductToDB(new Product(
//         "M-SH-237", "חולצת פלאנל משובצת Heavy", "https://api.placeholder.com/m/heavy-flannel",
//         "חולצה מחממת שמתפקדת כג'קט. בד: 100% כותנה מוברשת עבה. משבצות בגווני ירוק זית ושחור.",
//         2199, // 219.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "ירוק-משובץ", "Regular-Fit", "100% כותנה", "Winter", "Outdoor", "Rough")
//     ));

//     // 38. חולצת מעטפת בוהו
//     productService.addProductToDB(new Product(
//         "M-SH-238", "חולצת קימונו/מעטפת פשתן", "https://api.placeholder.com/m/wrap-linen",
//         "מראה ייחודי ונינוח. בד: 50% פשתן, 50% כותנה. סגירת קשירה פנימית, שרוולים רחבים.",
//         2399, // 239.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Relaxed-Fit", "Linen-Blend", "Boho", "Vacation", "Unique")
//     ));

//     // 39. טי-שירט עם כיס (Pocket Tee)
//     productService.addProductToDB(new Product(
//         "M-SH-239", "טי-שירט Slub עם כיס", "https://api.placeholder.com/m/pocket-tee",
//         "בייסיק עם טקסטורה מעניינת. בד: 100% כותנת Slub (טקסטורה לא אחידה). כיס בחזה שמאל.",
//         899, // 89.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "בורדו", "Regular-Fit", "100% כותנה", "Basic", "Casual", "חלק")
//     ));

//     // 40. גולף צמר מרינו דק
//     productService.addProductToDB(new Product(
//         "M-SH-240", "סריג גולף Merino פרימיום", "https://api.placeholder.com/m/merino-turtle",
//         "פריט חובה למראה שכבות יוקרתי. בד: 100% צמר מרינו דק במיוחד. בד גמיש, רך ולא מגרד.",
//         3199, // 319.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "אפור מלנז'", "Slim-Fit", "Merino-Wool", "Winter", "Luxury", "Elegant")
//     ));

//     // 41. חולצת טוקסידו רשמית
//     productService.addProductToDB(new Product(
//         "M-SH-241", "חולצת טוקסידו Wing-Collar", "https://api.placeholder.com/m/tux-shirt",
//         "החולצה הרשמית ביותר לאירועי ערב. בד: 100% כותנה מצרית איכותית. צווארון כנף לעניבת פרפר, חזית פיקה נוקשה.",
//         3499, // 349.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "לבן", "Slim-Fit", "Luxury", "Formal", "Wedding", "Premium")
//     ));

//     // 42. חולצת ריצה תרמית
//     productService.addProductToDB(new Product(
//         "M-SH-242", "חולצת ריצה תרמית ארוכה", "https://api.placeholder.com/m/thermal-run",
//         "לפעילות גופנית במזג אוויר קר. בד: 92% פוליאסטר תרמי, 8% אלסטן. פנים מורשת (Brushed) לשמירת חום.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "שחור", "Athletic-Fit", "Thermal", "Active", "Sport", "Winter")
//     ));

//     // 43. חולצת "גרנדד" פסים
//     productService.addProductToDB(new Product(
//         "M-SH-243", "חולצת Grandad פסי סיכה", "https://api.placeholder.com/m/grandad-stripes",
//         "מראה אירופאי נינוח. בד: 100% כותנת פופלין. צווארון סיני עגול, פסי תכלת-לבן דקים, גזרה ישרה.",
//         1699, // 169.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "פסים", "Regular-Fit", "100% כותנה", "Smart-Casual", "Classic", "יומיום")
//     ));

//     // 44. טי-שירט "רייזר" (Racerback)
//     productService.addProductToDB(new Product(
//         "M-SH-244", "גופיית Racerback מקצועית", "https://api.placeholder.com/m/gym-racer",
//         "חיתוך עמוק בגב להדגשת השרירים. בד: תערובת כותנה ומודל למגע משי. בד נושם וקל במיוחד.",
//         799, // 79.90 NIS
//         Arrays.asList("גברים", "חולצות", "גופיות", "אפור גרפיט", "Muscle-Fit", "Cotton-Modal", "Gym", "Active", "Basic")
//     ));

//     // 45. סוודר פולו "Old Money"
//     productService.addProductToDB(new Product(
//         "M-SH-245", "סריג פולו צמר ומשי", "https://api.placeholder.com/m/silk-wool-polo",
//         "פריט פרימיום למראה יוקרתי שקט. בד: 70% צמר מרינו, 30% משי. גזרה צמודה עם צווארון פולו סרוג.",
//         4299, // 429.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "כחול נייבי", "Slim-Fit", "Silk-Wool", "Luxury", "Old-Money", "Elegant")
//     ));

//     // 46. חולצת עבודה (Workshirt) עמידה
//     productService.addProductToDB(new Product(
//         "M-SH-246", "חולצת עבודה Canvas כבדה", "https://api.placeholder.com/m/canvas-shirt",
//         "עמידות מקסימלית לתנאי חוץ. בד: 100% כותנת קנבס עבה. תפרים כפולים מחוזקים, כיסי דגמ\"ח בחזה.",
//         2199, // 219.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "חקי", "Regular-Fit", "Canvas", "Outdoor", "Workwear", "Rough")
//     ));

//     // 47. קפוצ'ון "שרפה" (Sherpa)
//     productService.addProductToDB(new Product(
//         "M-SH-247", "קפוצ'ון בטנת פרווה Sherpa", "https://api.placeholder.com/m/sherpa-hoodie",
//         "הפריט הכי חם בקולקציה. בד: פליז עבה עם בטנת פרווה סינתטית בתוך הכובע והגוף. שרוולי ריב.",
//         3199, // 319.90 NIS
//         Arrays.asList("גברים", "חולצות", "ארוכות", "שחור מלנז'", "Oversize", "Sherpa", "Winter", "Warm", "Casual")
//     ));

//     // 48. גופיית כדורסל רטרו
//     productService.addProductToDB(new Product(
//         "M-SH-248", "גופיית רשת Basketball Vintage", "https://api.placeholder.com/m/retro-jersey",
//         "מראה רחוב נוסטלגי. בד: 100% פוליאסטר רשת כפול. מספר מודפס בחזית ובגב, סיומת פסים צבעונית.",
//         1499, // 149.90 NIS
//         Arrays.asList("גברים", "חולצות", "גופיות", "צבעוני", "Athletic-Fit", "Mesh", "Streetwear", "Vintage", "Sport")
//     ));

//     // 49. חולצת פופלין "סטרצ'" צבעונית
//     productService.addProductToDB(new Product(
//         "M-SH-249", "חולצת כפתורים Stretch בורדו", "https://api.placeholder.com/m/stretch-shirt",
//         "נוחות מקסימלית למראה מחויט. בד: 96% כותנה, 4% אלסטן. בד גמיש במיוחד המאפשר תנועה חופשית.",
//         1899, // 189.90 NIS
//         Arrays.asList("גברים", "חולצות", "מכופתרות", "בורדו", "Slim-Fit", "Cotton-Stretch", "Office", "Elegant", "חלק")
//     ));

//     // 50. טי-שירט "בוקסי" (Boxy) עם הדפס
//     productService.addProductToDB(new Product(
//         "M-SH-250", "טי-שירט Boxy גב מודפס", "https://api.placeholder.com/m/boxy-graphic",
//         "גזרת רחוב עדכנית. בד: 100% כותנה אורגנית במשקל בינוני. הדפס אומנותי גדול על כל הגב.",
//         1199, // 119.90 NIS
//         Arrays.asList("גברים", "חולצות", "קצרות", "לבן", "Boxy-Fit", "100% כותנה", "Streetwear", "Graphic", "Trendy")
//     ));
// }

// private void seedWomensShirts() {
//     // 1. חולצת סאטן נשפכת - למראה ערב
//     productService.addProductToDB(new Product(
//         "W-SH-301", "חולצת סאטן בגזרת מעטפת", "https://images.clothes.com/w/satin-wrap.jpg",
//         "חולצה אלגנטית ליציאה. בד: סאטן משי סינתטי (Polyester Satin). מפתח וי, קשירה במותן.",
//         1899, // 189.90 NIS
//         Arrays.asList("נשים", "חולצות", "אלגנט", "ירוק בקבוק", "Regular-Fit", "סאטן", "Evening", "Night-Out", "יוקרתי")
//     ));

//     // 2. חולצת קרופ (Crop Top) ריב - בייסיק יומיומי
//     productService.addProductToDB(new Product(
//         "W-SH-302", "חולצת קרופ Seamless ריב", "https://images.clothes.com/w/crop-rib.jpg",
//         "חולצת בייסיק צמודה ללא תפרים. בד: מיקרופייבר נמתח. גזרה קצרה מעל הפופיק.",
//         699, // 69.90 NIS
//         Arrays.asList("נשים", "חולצות", "קצרות", "ורוד עתיק", "Slim-Fit", "מיקרופייבר", "Casual", "Summer", "Basic")
//     ));

//     // 3. חולצת כפתורים "Boyfriend" - אוברסייז
//     productService.addProductToDB(new Product(
//         "W-SH-303", "חולצת פופלין Oversize לבנה", "https://images.clothes.com/w/poplin-white.jpg",
//         "חולצה גדולה וקלילה בסגנון גברי. בד: 100% כותנת פופלין פריכה. מתאימה מעל בגד ים או עם ג'ינס.",
//         1499, // 149.90 NIS
//         Arrays.asList("נשים", "חולצות", "מכופתרות", "לבן", "Oversize", "100% כותנה", "Minimalist", "Beachwear", "Office")
//     ));

//     // 4. חולצת תחרה בוהו-שיק
//     productService.addProductToDB(new Product(
//         "W-SH-304", "חולצת תחרה רקוקה (Embroidery)", "https://images.clothes.com/w/boho-lace.jpg",
//         "חולצה רומנטית לחופשה. בד: 100% כותנה עם רקמת חורים (Eyelet). שרוולים תפוחים.",
//         2199, // 219.90 NIS
//         Arrays.asList("נשים", "חולצות", "מכופתרות", "קרם", "Regular-Fit", "כותנה רקוסה", "Romantic", "Vacation", "Boho")
//     ));
// }
// }