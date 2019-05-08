package Test;

import CommandModule.*;
import Ingredient.*;
import ProductBatch.*;
import Recipe.*;
import ResourceBatch.*;
import User.*;

import Utilities.DAO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandModuleTest {
  CommandModule cm = new CommandModule();

  private final static int admin = 4444;
  private final static int farma = 3333;
  private final static int ltech = 6666;



    @Test
    void adminstratorCreateUser() {
        try {
            IUserDTO testUser = new UserDTO();
            testUser.setUserID(100);
            testUser.setRole("Praktikant");
            testUser.setAdminStatus(0);
            cm.adminstratorCreateUser(admin, testUser);
            IUserDTO recvUser = cm.adminstratorGetUser(admin, testUser.getUserID());
            if (!(recvUser.getUserID() == testUser.getUserID() &&
                recvUser.getAdminStatus() == testUser.getAdminStatus() &&
                recvUser.getRole().equals(testUser.getRole())))
                    fail();
            cm.adminstratorDeleteUser(admin, testUser.getUserID());
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void adminstratorUpdateUser() {
        try {
            IUserDTO testUser = new UserDTO();
            testUser.setUserID(100);
            testUser.setRole("CEO");
            testUser.setAdminStatus(1);
            cm.adminstratorUpdateUser(admin, testUser);
            IUserDTO recvUser = cm.adminstratorGetUser(admin, testUser.getUserID());
            if (!(recvUser.getUserID() == testUser.getUserID() &&
                recvUser.getAdminStatus() == testUser.getAdminStatus() &&
                recvUser.getRole().equals(testUser.getRole())))
                    fail();
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void adminstratorDeleteUser() {
        try {
            IUserDTO testUser = new UserDTO();
            testUser.setUserID(1000);
            testUser.setRole("Temp");
            testUser.setAdminStatus(0);
            cm.adminstratorCreateUser(admin, testUser);
            cm.adminstratorDeleteUser(admin, testUser.getUserID());
            testUser = cm.adminstratorGetUser(admin, testUser.getUserID());
            if (!(testUser.getRole() == null &&
                testUser.getUserID() == 0 &&
                testUser.getAdminStatus(1) == 0))
                    fail();
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void adminstratorGetUser() {
        try {
            IUserDTO testUser = new UserDTO();
            testUser.setUserID(100);
            testUser.setRole("Praktikant");
            testUser.setAdminStatus(0);
            cm.adminstratorCreateUser(admin, testUser);
            IUserDTO recvUser = cm.adminstratorGetUser(admin, testUser.getUserID());
            if (!(recvUser.getUserID() == testUser.getUserID() &&
                    recvUser.getAdminStatus() == testUser.getAdminStatus() &&
                    recvUser.getRole().equals(testUser.getRole())))
                fail();
            cm.adminstratorDeleteUser(admin, testUser.getUserID());
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void farmaCreateRecipe() {
        try {
            IRecipeDTO testRec = new RecipeDTO();
            IRecipeContentsDTO i1 = new RecipeContentsDTO();
            IRecipeContentsDTO i2 = new RecipeContentsDTO();
            IRecipeContentsDTO i3 = new RecipeContentsDTO();
            List<IRecipeContentsDTO> iList = new ArrayList<>();
            int rID = 50;
            i1.setRecipeID(rID); i2.setRecipeID(rID); i3.setRecipeID(rID);
            i1.setIngredientID(1); i2.setIngredientID(2); i3.setIngredientID(3);
            i1.setAmount(10.0); i2.setAmount(20.0); i3.setAmount(30.0);
            i1.setUseCase("Tablet"); i2.setUseCase("Aktivt"); i3.setUseCase("Filmovertræk");
            iList.add(i1); iList.add(i2); iList.add(i3);
            testRec.setIngredients(iList);
            testRec.setRecipeID(rID);
            testRec.setRecipeName("Test");
            testRec.setManufacturer("Popp");
            cm.farmaCreateRecipe(farma, testRec);
            IRecipeDTO recvRec = cm.farmaGetRecipe(farma, rID);
            if (!(recvRec.getRecipeID() == testRec.getRecipeID() &&
                    //recvRec.getIngredients() == testRec.getIngredients() &&
                    recvRec.getRecipeName().equals(testRec.getRecipeName()) &&
                    recvRec.getManufacturer().equals(testRec.getManufacturer())))
                fail();
            cm.farmaDeleteRecipe(farma, rID);
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void farmaUpdateRecipe() {
        try {
            IRecipeDTO testRec = new RecipeDTO();
            IRecipeContentsDTO i1 = new RecipeContentsDTO();
            IRecipeContentsDTO i2 = new RecipeContentsDTO();
            IRecipeContentsDTO i3 = new RecipeContentsDTO();
            List<IRecipeContentsDTO> iList = new ArrayList<>();
            int rID = 50;
            i1.setRecipeID(rID); i2.setRecipeID(rID); i3.setRecipeID(rID);
            i1.setIngredientID(1); i2.setIngredientID(2); i3.setIngredientID(3);
            i1.setAmount(10.0); i2.setAmount(20.0); i3.setAmount(30.0);
            i1.setUseCase("Tablet"); i2.setUseCase("Aktivt"); i3.setUseCase("Filmovertræk");
            iList.add(i1); iList.add(i2); iList.add(i3);
            testRec.setIngredients(iList);
            testRec.setRecipeID(rID);
            testRec.setRecipeName("Test");
            testRec.setManufacturer("Popp");
            cm.farmaCreateRecipe(farma, testRec);
            testRec.setRecipeName("Test2");
            testRec.setManufacturer("Robert");
            cm.farmaUpdateRecipe(farma, testRec);
            IRecipeDTO recvRec = cm.farmaGetRecipe(farma, rID);
            if (!(recvRec.getRecipeID() == testRec.getRecipeID() &&
                    //recvRec.getIngredients() == testRec.getIngredients() &&
                    recvRec.getRecipeName().equals(testRec.getRecipeName()) &&
                    recvRec.getManufacturer().equals(testRec.getManufacturer())))
                fail();
            cm.farmaDeleteRecipe(farma, rID);

        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void farmaDeleteRecipe() {
        try {
            IRecipeDTO testRec = new RecipeDTO();
            IRecipeContentsDTO i1 = new RecipeContentsDTO();
            IRecipeContentsDTO i2 = new RecipeContentsDTO();
            IRecipeContentsDTO i3 = new RecipeContentsDTO();
            List<IRecipeContentsDTO> iList = new ArrayList<>();
            int rID = 50;
            i1.setRecipeID(rID); i2.setRecipeID(rID); i3.setRecipeID(rID);
            i1.setIngredientID(1); i2.setIngredientID(2); i3.setIngredientID(3);
            i1.setAmount(10.0); i2.setAmount(20.0); i3.setAmount(30.0);
            i1.setUseCase("Tablet"); i2.setUseCase("Aktivt"); i3.setUseCase("Filmovertræk");
            iList.add(i1); iList.add(i2); iList.add(i3);
            testRec.setIngredients(iList);
            testRec.setRecipeID(rID);
            testRec.setRecipeName("Test");
            testRec.setManufacturer("Popp");
            cm.farmaCreateRecipe(farma, testRec);
            cm.farmaDeleteRecipe(farma, rID);
            IRecipeDTO recvRec = cm.farmaGetRecipe(farma, rID);
            if (!(recvRec.getRecipeID() == 0 &&
                    recvRec.getManufacturer() == null &&
                    recvRec.getRecipeName() == null))
                fail();
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void farmaGetRecipe() {
        try {
            IRecipeDTO testRec = new RecipeDTO();
            IRecipeContentsDTO i1 = new RecipeContentsDTO();
            IRecipeContentsDTO i2 = new RecipeContentsDTO();
            IRecipeContentsDTO i3 = new RecipeContentsDTO();
            List<IRecipeContentsDTO> iList = new ArrayList<>();
            int rID = 50;
            i1.setRecipeID(rID); i2.setRecipeID(rID); i3.setRecipeID(rID);
            i1.setIngredientID(1); i2.setIngredientID(2); i3.setIngredientID(3);
            i1.setAmount(10.0); i2.setAmount(20.0); i3.setAmount(30.0);
            i1.setUseCase("Tablet"); i2.setUseCase("Aktivt"); i3.setUseCase("Filmovertræk");
            iList.add(i1); iList.add(i2); iList.add(i3);
            testRec.setIngredients(iList);
            testRec.setRecipeID(rID);
            testRec.setRecipeName("Test");
            testRec.setManufacturer("Popp");
            cm.farmaCreateRecipe(farma, testRec);
            IRecipeDTO recvRec = cm.farmaGetRecipe(farma, rID);
            if (!(recvRec.getRecipeID() == testRec.getRecipeID() &&
                    //recvRec.getIngredients() == testRec.getIngredients() &&
                    recvRec.getRecipeName().equals(testRec.getRecipeName()) &&
                    recvRec.getManufacturer().equals(testRec.getManufacturer())))
                fail();
            cm.farmaDeleteRecipe(farma, rID);
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    //@Test
    void farmaGetRecipes() {
    }

    //@Test
    void leaderCreateBatch() {
    }

    //@Test
    void searchBatch() {
    }

    //@Test
    void searchBatchStatus() {
    }

    //@Test
    void statusTable() {
    }

    //@Test
    void labtechUpdateStatus() {
    }
}
