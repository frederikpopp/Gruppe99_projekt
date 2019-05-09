package ResourceBatch;

import Utilities.DAO;
import java.util.List;

public interface IResourceBatchDAO  extends DAO {

  public void addBatch(IResourceBatchDTO batch) throws DALException;

  public IResourceBatchDTO getBatch(int batchID) throws DALException;

  public List<IResourceBatchDTO> getAllBatches() throws DALException;

  public void updateBatch(IResourceBatchDTO batch) throws DALException;

  public void deleteBatch(int batchID) throws DALException;

  public List<IResourceBatchDTO> getIngredientBatches(int ingredientID) throws DALException;

  public double getTotalIngredientAmount(int ingredientID) throws DALException

}
