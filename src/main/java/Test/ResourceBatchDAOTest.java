package Test;

import ResourceBatch.IResourceBatchDAO;
import ResourceBatch.IResourceBatchDTO;
import ResourceBatch.ResourceBatchDAO;
import ResourceBatch.ResourceBatchDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

class ResourceBatchDAOTest {

    IResourceBatchDAO rbDAO = new ResourceBatchDAO();

    @Test
    void jUnitResourceBatch() {
        try {
            ResourceBatchDTO testRB = new ResourceBatchDTO();
            testRB.setBatchID(545);
            testRB.setIngredientID(1);
            testRB.setManufacturer("NotNovo");
            testRB.setAmount(42.6);
            testRB.setRemainder(10.2);

            rbDAO.addBatch(testRB);
            IResourceBatchDTO receivedRB = rbDAO.getBatch(545);
            assertEquals(testRB.getBatchID(), receivedRB.getBatchID());
            assertEquals(testRB.getIngredientID(), receivedRB.getIngredientID());
            assertEquals(testRB.getManufacturer(), receivedRB.getManufacturer());
            assertEquals((Double)testRB.getAmount(), (Double)receivedRB.getAmount());
            assertEquals((Double)testRB.getRemainder(), (Double)receivedRB.getRemainder());

            List<IResourceBatchDTO> allRB = rbDAO.getAllBatches();
            boolean found = false;
            for (IResourceBatchDTO rb : allRB) {
                if(rb.getBatchID() == testRB.getBatchID()){
                    assertEquals(testRB.getIngredientID(), rb.getIngredientID());
                    assertEquals(testRB.getManufacturer(), rb.getManufacturer());
                    assertEquals((Double)testRB.getAmount(), (Double)rb.getAmount());
                    assertEquals((Double)testRB.getRemainder(), (Double)rb.getRemainder());
                    found = true;
                }
            }
            if(!found){fail();}


            // TEST UPDATE
            testRB.setIngredientID(2);
            testRB.setManufacturer("DefNotNovo");
            testRB.setAmount(42.7);
            testRB.setRemainder(10.1);
            rbDAO.updateBatch(testRB);

            receivedRB = rbDAO.getBatch(545);
            assertEquals(testRB.getBatchID(), receivedRB.getBatchID());
            assertEquals(testRB.getIngredientID(), receivedRB.getIngredientID());
            assertEquals(testRB.getManufacturer(), receivedRB.getManufacturer());
            assertEquals((Double)testRB.getAmount(), (Double)receivedRB.getAmount());
            assertEquals((Double)testRB.getRemainder(), (Double)receivedRB.getRemainder());

            // TEST DELETE
            rbDAO.deleteBatch(testRB.getBatchID());
            allRB = rbDAO.getAllBatches();

            for (IResourceBatchDTO rb : allRB) {
                if (rb.getIngredientID() == testRB.getBatchID()) {
                    fail();
                }
            }


        } catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

}