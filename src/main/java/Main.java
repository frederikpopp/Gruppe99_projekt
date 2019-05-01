import User.*;
import Ingredient.*;
import Utilities.DAO;

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
        IIngredientDAO iDAO = new IngredientDAO();
        IIngredientDTO ingredient1 = new IngredientDTO();
        IIngredientDTO ingredient2 = new IngredientDTO();
        List<IIngredientDTO> allIngredients = new ArrayList<>();
        List<IIngredientDTO> reorderList = new ArrayList<>();

        System.out.println(" ----- TEST STARTING -----");

        /*UserDTO person = new UserDTO();
        person.setUserID(2223);
        person.setRole("Fool");
        person.setAdminStatus(0);

        UserDTO person1 = new UserDTO();
        person1.setUserID(4445);
        person1.setRole("Goat");
        person1.setAdminStatus(1);
        */
        ingredient1.setIngredientID(20);
        ingredient1.setName("Kodein");
        ingredient1.setOrderStatus(true);

        ingredient2.setIngredientID(30);
        ingredient2.setName("Magnesiumphosphat");
        ingredient2.setOrderStatus(true);


        try {
            /*DB.createUser(person);
            DB.createUser(person1);

            persons = DB.getUserList();

            for (int i = 0; i < persons.size(); i++) {
                System.out.println(persons.get(i).toString());
            }*/

            iDAO.createIngredient(ingredient1);
            iDAO.createIngredient(ingredient2);

            allIngredients = iDAO.getIngredientList();
            reorderList = iDAO.getReorderList();

            for (IIngredientDTO i : allIngredients) {
              System.out.println("All:");
              System.out.println(i.toString());
            }

            for (IIngredientDTO i : reorderList) {
              System.out.println("Reorder:");
              System.out.println(i.toString());
            }

            iDAO.deleteIngredient(ingredient1.getIngredientID());
            iDAO.deleteIngredient(ingredient2.getIngredientID());


        } catch (DAO.DALException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(" -----  END OF TEST  -----");
    }
}
