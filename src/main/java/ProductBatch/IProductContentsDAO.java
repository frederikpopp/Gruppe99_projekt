package ProductBatch;

import ResourceBatch.IResourceBatchDAO;
import ResourceBatch.IResourceBatchDTO;
import Utilities.DAO;

import java.util.List;


public interface IProductContentsDAO extends DAO {

    public List<IResourceBatchDTO> getResourceBatches(int productBatchID) throws DALException;

    public void addResourceBatch(IProductContentsDTO resource) throws DALException;

    public void updateResourceBatch(IProductContentsDTO resource) throws DALException;

    public void deleteResourceBatch(IProductContentsDTO resource) throws DALException;

    public void deleteProductBatch(int productBatchID) throws DALException;
}
