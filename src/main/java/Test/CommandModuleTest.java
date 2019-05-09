package Test;

import CommandModule.*;
import ProductBatch.*;
import Recipe.*;
import ResourceBatch.*;
import User.*;

import Utilities.DAO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandModuleTest {
  private CommandModule cm = new CommandModule();

  private final static int admin = 4444;
  private final static int pharma = 3333;
  private final static int plead = 5555;
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
            cm.adminstratorCreateUser(admin, testUser);
            testUser.setAdminStatus(0);
            cm.adminstratorUpdateUser(admin, testUser);
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
    void pharmaCreateRecipe() {
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
            cm.pharmaCreateRecipe(pharma, testRec);
            IRecipeDTO recvRec = cm.pharmaGetRecipe(pharma, rID);
            if (!(recvRec.getRecipeID() == testRec.getRecipeID() &&
                    //recvRec.getIngredients() == testRec.getIngredients() &&
                    recvRec.getRecipeName().equals(testRec.getRecipeName()) &&
                    recvRec.getManufacturer().equals(testRec.getManufacturer())))
                fail();
            cm.pharmaDeleteRecipe(pharma, rID);
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void pharmaUpdateRecipe() {
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
            cm.pharmaCreateRecipe(pharma, testRec);
            testRec.setRecipeName("Test2");
            testRec.setManufacturer("Robert");
            cm.pharmaUpdateRecipe(pharma, testRec);
            IRecipeDTO recvRec = cm.pharmaGetRecipe(pharma, rID);
            if (!(recvRec.getRecipeID() == testRec.getRecipeID() &&
                    //recvRec.getIngredients() == testRec.getIngredients() &&
                    recvRec.getRecipeName().equals(testRec.getRecipeName()) &&
                    recvRec.getManufacturer().equals(testRec.getManufacturer())))
                fail();
            cm.pharmaDeleteRecipe(pharma, rID);

        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void pharmaDeleteRecipe() {
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
            cm.pharmaCreateRecipe(pharma, testRec);
            cm.pharmaDeleteRecipe(pharma, rID);
            IRecipeDTO recvRec = cm.pharmaGetRecipe(pharma, rID);
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
    void pharmaGetRecipe() {
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
            cm.pharmaCreateRecipe(pharma, testRec);
            IRecipeDTO recvRec = cm.pharmaGetRecipe(pharma, rID);
            if (!(recvRec.getRecipeID() == testRec.getRecipeID() &&
                    //recvRec.getIngredients() == testRec.getIngredients() &&
                    recvRec.getRecipeName().equals(testRec.getRecipeName()) &&
                    recvRec.getManufacturer().equals(testRec.getManufacturer())))
                fail();
            cm.pharmaDeleteRecipe(pharma, rID);
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void pharmaGetRecipes() {
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
            cm.pharmaCreateRecipe(pharma, r1);
            IRecipeDTO r2 = r1;
            r2.setRecipeID(rID+1);
            r2.setRecipeName("Test2");
            r2.setManufacturer("Benjamin");
            cm.pharmaCreateRecipe(pharma, r2);
            List<IRecipeDTO> rList = cm.pharmaGetRecipes(pharma);
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
            cm.pharmaDeleteRecipe(pharma, rID);
            cm.pharmaDeleteRecipe(pharma, r2.getRecipeID());
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
            IRecipeDTO rec = cm.pharmaGetRecipe(pharma, rID);
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

    @Test
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

    @Test
    void searchBatchStatus() {
        try {
            IProductBatchDAO pDAO = new ProductBatchDAO();
            // Search for ordered batch
            IProductBatchDTO batch = new ProductBatchDTO();
            batch.setBatchID(20);
            batch.setRecipeID(10);
            batch.setBatchAmount(10);
            batch.setBatchStatus("Ordered");
            cm.leaderCreateBatch(plead, batch);
            String status = cm.searchBatchStatus(ltech, batch.getBatchID());
            assertEquals(batch.getBatchStatus(), status);

            // Search for progressing batch
            IProductContentsDTO pc1 = new ProductContentsDTO();
            IProductContentsDTO pc2 = new ProductContentsDTO();
            pc1.setProductBatch(batch.getBatchID()); pc2.setProductBatch(batch.getBatchID());
            pc1.setResourceBatch(1); pc2.setResourceBatch(2);
            pc1.setAmount(50); pc2.setAmount(100);
            List<IProductContentsDTO> pcList = new ArrayList<>();
            pcList.add(pc1);
            pcList.add(pc2);
            cm.labtechUpdateStatus(ltech, batch.getBatchID(),pcList);
            batch.setBatchStatus("Progressing");
            status = cm.searchBatchStatus(ltech, batch.getBatchID());
            assertEquals(batch.getBatchStatus(), status);

            // Search for finished batch
            cm.labtechUpdateStatus(ltech, batch.getBatchID(), pcList);
            batch.setBatchStatus("Finished");
            status = cm.searchBatchStatus(ltech, batch.getBatchID());
            assertEquals(batch.getBatchStatus(), status);

            pDAO.deleteProductBatch(batch.getBatchID(), batch.getBatchStatus());
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void statusTable() {
        try {
            // Get all ORDERED products
            IProductBatchDAO pDAO = new ProductBatchDAO();
            IProductBatchDTO batch1 = new ProductBatchDTO();
            IProductBatchDTO batch2 = new ProductBatchDTO();
            final int bID = 20;
            batch1.setBatchID(bID);
            batch1.setRecipeID(10);
            batch1.setBatchAmount(10);
            batch1.setBatchStatus("Ordered");
            cm.leaderCreateBatch(plead, batch1);
            batch2.setBatchID(bID+1);
            batch2.setRecipeID(batch1.getRecipeID());
            batch2.setBatchAmount(batch1.getBatchAmount());
            batch2.setBatchStatus(batch1.getBatchStatus());
            cm.leaderCreateBatch(plead, batch2);
            List<IProductBatchDTO> statusTable = cm.statusTable(ltech, "Ordered");
            int count = 0;
            for (IProductBatchDTO pb : statusTable) {
                if ((pb.getBatchID() == batch1.getBatchID() &&
                        pb.getBatchAmount() == batch1.getBatchAmount() &&
                        pb.getRecipeID() == batch1.getRecipeID() &&
                        pb.getBatchStatus().equals(batch1.getBatchStatus())) ||
                        (pb.getBatchID() == batch2.getBatchID() &&
                        pb.getBatchAmount() == batch2.getBatchAmount() &&
                        pb.getRecipeID() == batch2.getRecipeID() &&
                        pb.getBatchStatus().equals(batch2.getBatchStatus())))
                    count++;
            }
            assertEquals(2, count);


            // Get all PROGRESSING products
            IProductContentsDTO pc1 = new ProductContentsDTO();
            IProductContentsDTO pc2 = new ProductContentsDTO();
            IProductContentsDTO pc3 = new ProductContentsDTO();
            IProductContentsDTO pc4 = new ProductContentsDTO();
            pc1.setProductBatch(batch1.getBatchID()); pc2.setProductBatch(batch1.getBatchID());
            pc1.setResourceBatch(1); pc2.setResourceBatch(2);
            pc1.setAmount(50); pc2.setAmount(100);
            pc3.setProductBatch(batch2.getBatchID()); pc4.setProductBatch(batch2.getBatchID());
            pc3.setResourceBatch(1); pc4.setResourceBatch(2);
            pc3.setAmount(50); pc4.setAmount(100);
            List<IProductContentsDTO> pcList1 = new ArrayList<>();
            pcList1.add(pc1);
            pcList1.add(pc2);
            List<IProductContentsDTO> pcList2 = new ArrayList<>();
            pcList2.add(pc3);
            pcList2.add(pc4);
            cm.labtechUpdateStatus(ltech, batch1.getBatchID(), pcList1);
            batch1.setBatchStatus("Progressing");
            cm.labtechUpdateStatus(ltech, batch2.getBatchID(), pcList2);
            batch2.setBatchStatus("Progressing");

            statusTable = cm.statusTable(ltech, "Progressing");
            count = 0;
            for (IProductBatchDTO pb : statusTable) {
                if ((pb.getBatchID() == batch1.getBatchID() &&
                        pb.getBatchAmount() == batch1.getBatchAmount() &&
                        pb.getRecipeID() == batch1.getRecipeID() &&
                        pb.getBatchStatus().equals(batch1.getBatchStatus())) ||
                        (pb.getBatchID() == batch2.getBatchID() &&
                                pb.getBatchAmount() == batch2.getBatchAmount() &&
                                pb.getRecipeID() == batch2.getRecipeID() &&
                                pb.getBatchStatus().equals(batch2.getBatchStatus())))
                    count++;
                /*
                System.out.println("---------PROGRESSING---------");
                System.out.println("pb");
                System.out.println("ID="+pb.getBatchID()+", amount="+pb.getBatchAmount()+
                        ", rID="+pb.getRecipeID()+", status="+pb.getBatchStatus());
                System.out.println("batch1");
                System.out.println("ID="+batch1.getBatchID()+", amount="+batch1.getBatchAmount()+
                        ", rID="+batch1.getRecipeID()+", status="+batch1.getBatchStatus());
                System.out.println("batch2");
                System.out.println("ID="+batch2.getBatchID()+", amount="+batch2.getBatchAmount()+
                        ", rID="+batch2.getRecipeID()+", status="+batch2.getBatchStatus());*/
            }
            assertEquals(2, count);


            // Get all FINISHED
            cm.labtechUpdateStatus(ltech, batch1.getBatchID(), pcList1);
            batch1.setBatchStatus("Finished");
            cm.labtechUpdateStatus(ltech, batch2.getBatchID(), pcList2);
            batch2.setBatchStatus("Finished");

            statusTable = cm.statusTable(ltech, "Finished");
            count = 0;
            for (IProductBatchDTO pb : statusTable) {
                if ((pb.getBatchID() == batch1.getBatchID() &&
                        pb.getBatchAmount() == batch1.getBatchAmount() &&
                        pb.getRecipeID() == batch1.getRecipeID() &&
                        pb.getBatchStatus().equals(batch1.getBatchStatus())) ||
                        (pb.getBatchID() == batch2.getBatchID() &&
                                pb.getBatchAmount() == batch2.getBatchAmount() &&
                                pb.getRecipeID() == batch2.getRecipeID() &&
                                pb.getBatchStatus().equals(batch2.getBatchStatus())))
                    count++;
            }
            assertEquals(2, count);

            pDAO.deleteProductBatch(batch1.getBatchID(), batch1.getBatchStatus());
            pDAO.deleteProductBatch(batch2.getBatchID(), batch2.getBatchStatus());
        } catch (DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    /*Ignore this test, as it is essentially the same as searchBatchstatus
    @Test
    void labtechUpdateStatus() {

    }
    */

}
