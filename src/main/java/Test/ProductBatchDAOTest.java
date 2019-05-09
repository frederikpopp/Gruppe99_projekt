package Test;

import ProductBatch.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;




class ProductBatchDAOTest {

    IProductBatchDAO productbatchDAO = new ProductBatchDAO();

    @Test
    void jUnitRecipe(){
        try{

            IProductBatchDTO testProductBatch = new ProductBatchDTO();
            testProductBatch.setBatchID(4);
            testProductBatch.setRecipeID(2);
            productbatchDAO.createProductBatch(testProductBatch);
            productbatchDAO.orderBatch(4);

            testProductBatch = productbatchDAO.getProductBatch(4);
            IProductBatchDTO receivedProductBatch = productbatchDAO.getProductBatch(4);
            assertEquals(testProductBatch.getRecipeID(), receivedProductBatch.getRecipeID());
            assertEquals(testProductBatch.getOrderDate(), receivedProductBatch.getOrderDate());

            productbatchDAO.beginBatch(4);
            testProductBatch = productbatchDAO.getProductBatch(4);
            receivedProductBatch = productbatchDAO.getProductBatch(4);
            assertEquals(testProductBatch.getBeginDate(), receivedProductBatch.getBeginDate());

            testProductBatch.setRecipeID(1);
            productbatchDAO.updateProductBatch(testProductBatch);

            productbatchDAO.finishBatch(4);
            testProductBatch = productbatchDAO.getProductBatch(4);
            receivedProductBatch = productbatchDAO.getProductBatch(4);
            assertEquals(testProductBatch.getRecipeID(), receivedProductBatch.getRecipeID());
            assertEquals(testProductBatch.getDoneDate(), receivedProductBatch.getDoneDate());

            productbatchDAO.deleteProductBatch(4, "Finished");


        }
        catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getAllProductBatches() {
    }

    @Test
    void getProductBatch() {
    }

    @Test
    void updateProductBatch() {
    }

    @Test
    void deleteProductBatch() {
    }

    @Test
    void orderBatch() {
    }

    @Test
    void beginBatch() {
    }

    @Test
    void finishBatch() {
    }

    @Test
    void getAllProductBatchesWhere() {
    }

    @Test
    void createProductBatch() {
    }
}