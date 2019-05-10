package Test;

import ProductBatch.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;




class ProductBatchDAOTest {

    IProductBatchDAO productbatchDAO = new ProductBatchDAO();

    @Test
    void jUnitProductBatchDAO(){
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
            productbatchDAO.deleteProductBatch(50, "Ordered");


        }
        catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }

}