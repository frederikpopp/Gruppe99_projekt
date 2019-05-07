package ProductBatch;

import Utilities.DAO;

import java.util.List;

public interface IProductBatchDAO extends DAO {

  IProductBatchDTO getProductBatch(int productBacthID) throws DALException;

  List<IProductBatchDTO> getAllProductBatches() throws DALException;

  public void updateProductBatch(IProductBatchDTO pb) throws DALException;

  public void deleteProductBatch(int productBatchID) throws DALException;

  public void orderBatch(int productBatchID) throws DALException;

  public void beginBatch(int productBatchID) throws DALException;

  public void finishBatch(int productBatchID) throws DALException;

}


// Update productBatchID, orderBatch, beginBatch, finishBatch, deleteBatch
