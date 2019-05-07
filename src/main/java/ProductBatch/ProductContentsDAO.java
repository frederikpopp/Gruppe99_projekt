package ProductBatch;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static Utilities.ConnectionHandler.createConnection;

public class ProductContentsDAO implements IProductContentsDAO {

    @Override
    public List<IProductContentsDTO> getResourceBatches(int productBatchID) throws DALException {
        List<IProductContentsDTO> resourceList = new ArrayList<>();
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "SELECT r_batch_ID FROM product_contents WHERE p_batch_ID = ?");
            stmt.setInt(1, productBatchID);
            ResultSet results = stmt.executeQuery();
            while(results.next()) {
                IProductContentsDTO pc = new ProductContentsDTO();
                pc.setProductBatch(productBatchID);
                pc.setResourceBatch(results.getInt("r_batch_ID"));
                resourceList.add(pc);
            }
        } catch(SQLException e) {
            throw new DALException(e.getMessage());
        }
        return resourceList;
    }

    @Override
    public void addResourceBatch(IProductContentsDTO resource) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "INSERT INTO product_contents VALUES(?,?)");
            stmt.setInt(1, resource.getProductBatch());
            stmt.setInt(1,resource.getResourceBatch());
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void updateResourceBatch(IProductContentsDTO resource) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "UPDATE product_contents SET r_batch_ID = ? WHERE p_batch_ID = ?");
            stmt.setInt(1, resource.getResourceBatch());
            stmt.setInt(2, resource.getProductBatch());
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void deleteResourceBatch(IProductContentsDTO resource) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "DELETE FROM product_contents WHERE p_batchID = ? AND r_batch_ID = ?");
            stmt.setInt(1, resource.getProductBatch());
            stmt.setInt(2, resource.getResourceBatch());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void deleteProductBatch(int productBatchID) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "DELETE FROM product_contents WHERE p_batchID = ?");
            stmt.setInt(1, productBatchID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }
}
