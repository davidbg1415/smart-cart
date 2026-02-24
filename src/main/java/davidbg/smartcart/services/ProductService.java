package davidbg.smartcart.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.repositories.ProductRepository;

/**
 * @author DAVID BEN GIGI
 */

@Service
public class ProductService 
{
   private ProductRepository productRepository;

   public ProductService(ProductRepository productRepository)
   {
      this.productRepository = productRepository;
   }

   /// C (Create)
   public boolean addProductToDB(Product product)
   {
      productRepository.insert(product);
      return true;
   }

   /// R (Read)
   public ArrayList<Product> getAllProducts()
   {
      return (ArrayList<Product>)productRepository.findAll();
   }

   public ArrayList<Product> getProductsByTag(String tag)
   {
      return (ArrayList<Product>)productRepository.findByTagsContaining(tag);
   }

   /// D (Delete)
   public void deleteProduct(String id)
   {
      productRepository.deleteById(id);
   }
}