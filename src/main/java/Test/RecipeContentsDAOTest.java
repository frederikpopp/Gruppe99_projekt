package Test;

import Recipe.*;
import Utilities.DAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

class RecipeContentsDAOTest {

    IRecipeContentsDAO rDAO = new RecipeContentsDAO();

    @Test
    void jUnitRecipeContents() {
        try {
            // Test ADD ingredient and GET ingredients
            IRecipeContentsDTO testRC = new RecipeContentsDTO();
            testRC.setRecipeID(11);
            testRC.setIngredientID(16);
            testRC.setAmount(10.0);
            testRC.setUseCase("Tablet");

            rDAO.addIngredient(testRC);
            List<IRecipeContentsDTO> allIngredients = rDAO.getIngredients(testRC.getRecipeID());
            boolean found = false;
            for (IRecipeContentsDTO r : allIngredients) {
              if (isEqual(r, testRC)) found = true;
            }
            if (!found) fail();

            // Test UPDATE ingredient
            testRC.setAmount(5.0);
            rDAO.updateIngredient(testRC);
            allIngredients = rDAO.getIngredients(testRC.getRecipeID());
            found = false;
            for (IRecipeContentsDTO r : allIngredients) {
                if (isEqual(r, testRC)) found = true;
            }
            if (!found) fail();

            // Test DELETE ingredient
            rDAO.deleteIngredient(testRC.getIngredientID(), testRC.getRecipeID());
            allIngredients = rDAO.getIngredients(testRC.getRecipeID());
            found = false;
            for (IRecipeContentsDTO r : allIngredients) {
                if (isEqual(r, testRC)) found = true;
            }
            if (found) fail();

        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    private boolean isEqual(IRecipeContentsDTO r1, IRecipeContentsDTO r2) {
        boolean equals = false;
        if(r1.getRecipeID() == r2.getRecipeID() &&
              r1.getIngredientID() == r2.getIngredientID() &&
              r1.getAmount() == r2.getAmount() &&
              r1.getUseCase().equals(r2.getUseCase()))
            equals = true;
        return equals;
    }
}
