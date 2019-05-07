package ProductBatch;

import Utilities.DAO;

public interface IProductBatchDAO extends DAO {

  IProductBatchDTO getProductBatches(int productBacthID) throws DALException;

  List<IProductBatchDTO> getAllProductBatches() throws DALException;

}
