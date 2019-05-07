package ProductBatch;

import java.sql.Timestamp;

public interface IProductBatchDTO {

  public int getBatchID();

  public void setBatchID(int ID);

  public int getRecipeID();

  public void setRecipeID(int ID);

  public int getBatchAmount();

  public void setBatchAmount(int amount);

  public Timestamp getOrderDate();

  public void setOrderDate(Timestamp date);

  public Timestamp getBeginDate();

  public void setBeginDate(Timestamp date);

  public Timestamp getDoneDate();

  public void setDoneDate(Timestamp date);

  public String getBatchStatus();

  public void setBatchStatus(String status);

}
