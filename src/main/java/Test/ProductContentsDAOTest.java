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
            //Test ADD and GET batch
            ProductContentsDTO testPC = new ProductContentsDTO();
            testPC.setProductBatch(2);
            testPC.setResourceBatch(2);
            testPC.setAmount(3.6);

            pcDAO.addResourceBatch(testPC);

            List<IProductContentsDTO> allProdRB = pcDAO.getProductContents(testPC.getProductBatch());
            boolean found = false;
            for (IProductContentsDTO p : allProdRB) {
              if (p.getResourceBatch() == testPC.getResourceBatch()
                      && p.getAmount() == testPC.getAmount())
                  found = true;
            }
            if (!found) fail();

            //Test UPDATE batch
            testPC.setAmount(10.0);
            pcDAO.updateResourceAmount(testPC);
            allProdRB = pcDAO.getProductContents(testPC.getProductBatch());
            found = false;
            for (IProductContentsDTO p : allProdRB) {
              if (p.getResourceBatch() == testPC.getResourceBatch()
                      && p.getAmount() == testPC.getAmount())
                  found = true;
            }
            if(!found) fail();


            //Test DELETE resourcebatch
            pcDAO.deleteResourceBatch(testPC);
            allProdRB = pcDAO.getProductContents(4);
            for (IProductContentsDTO p : allProdRB) {
              if (p.getResourceBatch() == testPC.getResourceBatch()
                      && p.getAmount() == testPC.getAmount())
                  fail();
            }

            //Test DELETE productbatch
            IProductContentsDTO pc1 = new ProductContentsDTO();
            IProductContentsDTO pc2 = new ProductContentsDTO();
            IProductContentsDTO pc3 = new ProductContentsDTO();
            pc1.setProductBatch(1);
            pc2.setProductBatch(1);
            pc3.setProductBatch(1);
            pc1.setResourceBatch(1);
            pc2.setResourceBatch(2);
            pc3.setResourceBatch(3);
            pc1.setAmount(15.0);
            pc2.setAmount(10.0);
            pc3.setAmount(5.0);

            pcDAO.addResourceBatch(pc1);
            pcDAO.addResourceBatch(pc2);
            pcDAO.addResourceBatch(pc3);

            pcDAO.deleteProductBatch(pc1.getProductBatch());
            allProdRB = pcDAO.getProductContents(pc1.getProductBatch());
            if(!allProdRB.isEmpty()) fail();


        } catch (Utilities.DAO.DALException e) {
            e.printStackTrace();
            fail();
        }
    }
}
