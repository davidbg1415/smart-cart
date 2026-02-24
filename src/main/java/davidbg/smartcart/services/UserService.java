package davidbg.smartcart.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.repositories.UserRepository;

/**
 * @author DAVID BEN GIGI
 */

@Service
public class UserService 
{
   private UserRepository userRepository;

   public UserService(UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }

   /// C (Create)
   public boolean addUserToDB(User user)
   {
      // בדיקה אם המשתמש כבר קיים לפי ה-ID
      if (userRepository.existsById(user.getId()))
         return false;

      userRepository.insert(user);
      return true;
   }

   /// R (Read/Retrieve)
   public ArrayList<User> getAllUsers()
   {
      return (ArrayList<User>)userRepository.findAll();
   }

   public boolean isUserExists(String email, String password)
   {
      User user = userRepository.findOneByEmailAndPassword(email, password);
      return user != null;
   }

   public User getUser(String email, String password)
   {
      return userRepository.findOneByEmailAndPassword(email, password);
   }

   /// D (Delete)
   public void deleteUser(User user)
   {
      userRepository.delete(user);
   }
}