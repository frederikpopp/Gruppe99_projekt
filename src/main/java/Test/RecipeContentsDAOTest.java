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

            // Test ADD Recipe
            allIngredients.clear();
            IRecipeContentsDTO rc1 = new RecipeContentsDTO();
            IRecipeContentsDTO rc2 = new RecipeContentsDTO();
            IRecipeContentsDTO rc3 = new RecipeContentsDTO();
            rc1.setRecipeID(11);
            rc2.setRecipeID(11);
            rc3.setRecipeID(11);
            rc1.setIngredientID(1);
            rc2.setIngredientID(2);
            rc3.setIngredientID(3);
            rc1.setAmount(10.0);
            rc2.setAmount(20.0);
            rc3.setAmount(30.0);
            rc1.setUseCase("Aktivt");
            rc2.setUseCase("Tablet");
            rc3.setUseCase("Filmovertræk");
            allIngredients.add(rc1);
            allIngredients.add(rc2);
            allIngredients.add(rc3);

            rDAO.addRecipe(allIngredients);
            List<IRecipeContentsDTO> receivedRecipe = rDAO.getIngredients(rc1.getRecipeID());
            found = true;
            for (int i = 0; i < receivedRecipe.size(); i++) {
                if (!isEqual(receivedRecipe.get(i),allIngredients.get(i)))
                    found = false;
            }
            if (!found) fail();

            // Test UPDATE recipe
            allIngredients.get(0).setAmount(40.0);
            allIngredients.get(1).setAmount(50.0);
            allIngredients.get(2).setAmount(60.0);

            rDAO.updateRecipe(allIngredients);
            receivedRecipe = rDAO.getIngredients(rc1.getRecipeID());
            found = true;
            for (int i = 0; i < receivedRecipe.size(); i++) {
                if (!isEqual(receivedRecipe.get(i),allIngredients.get(i)))
                    found = false;
            }
            if (!found) fail();

            // Test DELETE recipe
            rDAO.deleteRecipe(rc1.getRecipeID());
            receivedRecipe = rDAO.getIngredients(rc1.getRecipeID());
            if (!receivedRecipe.isEmpty()) fail();


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
