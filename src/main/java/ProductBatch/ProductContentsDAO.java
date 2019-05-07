package ProductBatch;

import java.util.List;
import java.sql.*;

import static Utilities.ConnectionHandler.createConnection;

public class ProductContentsDAO implements IProductContentsDAO {


    @Override
    public List<IProductContentsDTO> getResourceBatches(int productBatchID) throws DALException {
        return null;
    }

    @Override
    public void addResourceBatch(IProductContentsDTO resource) throws DALException {

    }

    @Override
    public void updateResourceBatch(IProductContentsDTO resource) throws DALException {

    }

    @Override
    public void deleteResourceBatch(int resourceBatchID) throws DALException {

    }
}
