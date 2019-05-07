package ProductBatch;

import Utilities.DAO;

import java.util.List;

public interface IProductBatchDAO extends DAO {

  IProductBatchDTO getProductBatch(int productBacthID) throws DALException;

  List<IProductBatchDTO> getAllProductBatches() throws DALException;

}


// Update productBatchID, orderBatch, beginBatch, finishBatch, deleteBatch
