import User.IUserDAO;
import User.IUserDTO;
import User.UserDAO;
import User.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args) {
      UserDAO DB = new UserDAO();
      List<IUserDTO> users = new ArrayList<>();
      List<IUserDTO> persons = new ArrayList<>();
      UserDTO person = new UserDTO();
      person.setUserID(1234);
      person.setRole("Projektleder");
      person.setAdminStatus(0);
      try {
          DB.createUser(person);

          persons = DB.getUserList();

          for (int i = 0; i < persons.size(); i++) {
              System.out.println(persons.get(i).toString());
          }
          //person = DB.getUser(1);
          //System.out.println(person.toString());

      } catch (IUserDAO.DALException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
