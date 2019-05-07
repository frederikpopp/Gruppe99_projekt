package ProductBatch;

import Utilities.DAO;

import java.util.List;


public interface IProductContentsDAO extends DAO {

    //Maybe change to be resourcebatchDTO objects instead
    public List<IProductContentsDTO> getResourceBatches(int productBatchID) throws DALException;

    public void addResourceBatch(IProductContentsDTO resource) throws DALException;

    public void updateResourceBatch(IProductContentsDTO resource) throws DALException;

    public void deleteResourceBatch(int resourceBatchID) throws DALException;
}
