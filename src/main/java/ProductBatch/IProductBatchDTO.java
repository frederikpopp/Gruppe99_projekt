package ProductBatch;

import java.sql.Timestamp;

public interface IProductBatchDTO {
  
  public int getBatchID();

  public void setBatchID(int ID);

  public int getRecipeID();

  public void setRecipeID(int ID);

  public int getBatchStatus();

  public void setBatchStatus(int status);

  public Timestamp getDate();

  public void setDate(Timestamp date);
}
