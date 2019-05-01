import User.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args)
    {
        UserDAO DB = new UserDAO();
        List<IUserDTO> users = new ArrayList<>();
        List<IUserDTO> persons = new ArrayList<>();

        System.out.println(" ----- TEST STARTING -----");

        UserDTO person = new UserDTO();
        person.setUserID(2223);
        person.setRole("Fool");
        person.setAdminStatus(0);

        UserDTO person1 = new UserDTO();
        person1.setUserID(4445);
        person1.setRole("Goat");
        person1.setAdminStatus(1);
        try {
            DB.createUser(person);
            DB.createUser(person1);

            persons = DB.getUserList();

            for (int i = 0; i < persons.size(); i++) {
                System.out.println(persons.get(i).toString());
            }
        } catch (IUserDAO.DALException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(" -----  END OF TEST  -----");
    }
}
