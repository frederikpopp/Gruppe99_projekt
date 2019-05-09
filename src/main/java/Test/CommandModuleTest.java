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
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommandModuleTest {
  CommandModule cm = new CommandModule();

  private final static int admin = 4444;
  private final static int farma = 3333;
  private final static int plead = 5555;
  private final static int ltech = 6666;




    //@Test
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

    //@Test
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

    //@Test
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

    //@Test
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

    //@Test
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

    //@Test
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

    //@Test
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

    //@Test
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
        try {
            IRecipeDTO r1 = new RecipeDTO();
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
            r1.setIngredients(iList);
            r1.setRecipeID(rID);
            r1.setRecipeName("Test");
            r1.setManufacturer("Popp");
            cm.farmaCreateRecipe(farma, r1);
            IRecipeDTO r2 = r1;
            r2.setRecipeID(rID+1);
            r2.setRecipeName("Test2");
            r2.setManufacturer("Benjamin");
            cm.farmaCreateRecipe(farma, r2);
            List<IRecipeDTO> rList = cm.farmaGetRecipes(farma);
            int count = 0;
            for (IRecipeDTO r : rList) {
                if ((r.getRecipeID() == r1.getRecipeID() &&
                        r.getRecipeName().equals(r1.getRecipeName()) &&
                        r.getManufacturer().equals(r1.getManufacturer())))
                    count++;
                if (r.getRecipeID() == r2.getRecipeID() &&
                        r.getRecipeName().equals(r2.getRecipeName()) &&
                        r.getManufacturer().equals(r2.getManufacturer()))
                    count++;
            }
            assertEquals(2, count);
            cm.farmaDeleteRecipe(farma, rID);
            cm.farmaDeleteRecipe(farma, r2.getRecipeID());
        } catch(DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void leaderCreateBatch() {
        try {
            IProductBatchDAO pDAO = new ProductBatchDAO();
            IResourceBatchDAO rDAO = new ResourceBatchDAO();
            IProductContentsDAO pcDAO = new ProductContentsDAO();
            List<IResourceBatchDTO> listIngredientBatch1;
            List<IResourceBatchDTO> listIngredientBatch2;
            HashMap<Integer, Double> batchAmountsBefore = new HashMap<>();
            //HashMap<Integer, Double> batchAmountsAfter = new HashMap<>();
            HashMap<Integer, Double> ingAmounts = new HashMap<>();
            IProductBatchDTO batch = new ProductBatchDTO();
            final int bID = 10;
            final int rID = 10;
            final int amount = 10;
            IRecipeDTO rec = cm.farmaGetRecipe(farma, rID);
            for (IRecipeContentsDTO r : rec.getIngredients()) {
                ingAmounts.put(r.getIngredientID(), r.getAmount());
            }
            listIngredientBatch1 = rDAO.getIngredientBatches(rec.getIngredients().get(0).getIngredientID());
            listIngredientBatch2 = rDAO.getIngredientBatches(rec.getIngredients().get(1).getIngredientID());
            IResourceBatchDTO max = new ResourceBatchDTO();
            max.setAmount(0.0);
            // Get biggest resourcebatch of ingredient 1
            for (IResourceBatchDTO b : listIngredientBatch1) {
                if(b.getAmount() > max.getAmount()) {
                    max = b;
                }
            }
            final int batchID1 = max.getBatchID();
            batchAmountsBefore.put(max.getBatchID(), max.getAmount());
            // Get biggest resourcebatch for ingredient 2
            max.setAmount(0.0);
            for (IResourceBatchDTO b : listIngredientBatch2) {
                if(b.getAmount() > max.getAmount()) {
                    max = b;
                }
            }
            final int batchID2 = max.getBatchID();
            batchAmountsBefore.put(max.getBatchID(), max.getAmount());
            batch.setBatchID(bID);
            batch.setRecipeID(rID);
            batch.setBatchAmount(amount);
            cm.leaderCreateBatch(plead, batch);
            IProductBatchDTO recvBatch = cm.searchBatch(ltech, bID);

            // Check if data is uploaded correctly
            if (recvBatch.getBatchID() != batch.getBatchID() &&
                    recvBatch.getRecipeID() != batch.getRecipeID() &&
                    recvBatch.getBatchAmount() != batch.getBatchAmount())
                fail();

            // Check if resourcebatches have been added to product contents
            List<IProductContentsDTO> rList = pcDAO.getProductContents(bID);
            if (rList.size() != rec.getIngredients().size())
                fail();

            List<Double> actual = new ArrayList<>();
            List<Double> expected = new ArrayList<>();
            for (int i = 0; i < rList.size(); i++) {
                actual.add(rList.get(i).getAmount());
                expected.add((ingAmounts.get(rec.getIngredients().get(i).getIngredientID())+
                        ingAmounts.get(rec.getIngredients().get(i).getIngredientID())*0.02)*batch.getBatchAmount());

                assertEquals(expected, actual);
            }

            // Check if resourcebatch amounts have been updated
            double actual1 = rDAO.getBatch(batchID1).getAmount();
            double actual2 = rDAO.getBatch(batchID2).getAmount();
            double expected1 = batchAmountsBefore.get(batchID1)-expected.get(0);
            double expected2 = batchAmountsBefore.get(batchID2)-expected.get(1);
            assertEquals(expected1, actual1);
            assertEquals(expected2, actual2);

            pDAO.deleteProductBatch(bID, "Ordered");


        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    //@Test
    void searchBatch() {
        try {
            IProductBatchDAO pbDAO = new ProductBatchDAO();
            IProductBatchDTO testbatch = new ProductBatchDTO();
            testbatch.setBatchID(20);
            testbatch.setRecipeID(10);
            testbatch.setBatchAmount(100);
            cm.leaderCreateBatch(plead, testbatch);
            IProductBatchDTO recvBatch = cm.searchBatch(ltech, testbatch.getBatchID());
            if (recvBatch.getBatchID() != testbatch.getBatchID() &&
                    recvBatch.getRecipeID() != testbatch.getRecipeID() &&
                    recvBatch.getBatchAmount() != testbatch.getBatchAmount())
                fail();
            pbDAO.deleteProductBatch(testbatch.getBatchID(), "Ordered");
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
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
