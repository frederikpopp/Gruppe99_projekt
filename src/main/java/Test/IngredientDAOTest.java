package Test;

import Ingredient.IIngredientDAO;
import Ingredient.IIngredientDTO;
import Ingredient.IngredientDAO;
import Ingredient.IngredientDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

class IngredientDAOTest {

    IIngredientDAO ingredientDAO = new IngredientDAO();

    @Test
    void jUnitIngredient() {
        try{
            // TEST createIngredient, getIngredient and getIngredientList
            IngredientDTO testIngredient = new IngredientDTO();
            testIngredient.setIngredientID(535);
            testIngredient.setName("Knust Panodil");
            testIngredient.setOrderStatus(false);

            ingredientDAO.createIngredient(testIngredient);
            IIngredientDTO receivedIngredient = ingredientDAO.getIngredient(535);
            assertEquals(testIngredient.getIngredientID(), receivedIngredient.getIngredientID());
            assertEquals(testIngredient.getName(), receivedIngredient.getName());
            assertEquals(testIngredient.getOrderStatus(), receivedIngredient.getOrderStatus());

            List<IIngredientDTO> allIng = ingredientDAO.getIngredientList();
            boolean found = false;
            for (IIngredientDTO ing : allIng) {
                if(ing.getIngredientID() == testIngredient.getIngredientID()){
                    assertEquals(testIngredient.getName(),ing.getName());
                    assertEquals(testIngredient.getOrderStatus(), ing.getOrderStatus());
                    found = true;
                }
            }
            if(!found){fail();}

            // TEST UPDATE
            testIngredient.setName("Knust Paracetamol");
            testIngredient.setOrderStatus(true);
            ingredientDAO.updateIngredient(testIngredient);

            receivedIngredient = ingredientDAO.getIngredient(535);
            assertEquals(testIngredient.getIngredientID(), receivedIngredient.getIngredientID());
            assertEquals(testIngredient.getName(), receivedIngredient.getName());
            assertEquals(testIngredient.getOrderStatus(), receivedIngredient.getOrderStatus());

            // TEST getReorderList
            allIng = ingredientDAO.getReorderList();
            found = false;
            for (IIngredientDTO ing : allIng) {
                if(ing.getIngredientID() == testIngredient.getIngredientID()){
                    assertEquals(testIngredient.getName(),ing.getName());
                    assertEquals(testIngredient.getOrderStatus(), ing.getOrderStatus());
                    found = true;
                }
            }
            if(!found){fail();}

            // TEST DELETE
            ingredientDAO.deleteIngredient(testIngredient.getIngredientID());
            allIng = ingredientDAO.getIngredientList();

            for (IIngredientDTO ing : allIng) {
                if(ing.getIngredientID() == testIngredient.getIngredientID()){
                    fail();
                }
            }


        } catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }

    }
}