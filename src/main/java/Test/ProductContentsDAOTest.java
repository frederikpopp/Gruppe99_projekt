package Test;

import ProductBatch.IProductContentsDAO;
import ProductBatch.IProductContentsDTO;
import ProductBatch.ProductContentsDAO;
import ProductBatch.ProductContentsDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.fail;

class ProductContentsDAOTest {

    IProductContentsDAO pcDAO = new ProductContentsDAO();

    @Test
    void jUnitProductContents() {
        try {
            ProductContentsDTO testPC = new ProductContentsDTO();
            testPC.setProductBatch(4);
            testPC.setResourceBatch(2);
            testPC.setAmount(3.6);

            pcDAO.addResourceBatch(testPC);
            List<IProductContentsDTO> allRB = pcDAO.getProductContents(4);

            System.out.println(allRB);




        } catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    void getResourceBatches() {
    }

    @Test
    void addResourceBatch() {
    }

    @Test
    void updateResourceBatch() {
    }

    @Test
    void deleteResourceBatch() {
    }

    @Test
    void deleteProductBatch() {
    }
}