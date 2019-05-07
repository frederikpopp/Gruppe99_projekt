package ProductBatch;

import java.sql.Timestamp;

public interface IProductBatchDTO {

  public int getBatchID();

  public void setBatchID(int ID);

  public int getRecipeID();

  public void setRecipeID(int ID);

  public String getBatchAmount();

  public void setBatchAmount(String amount);

  public Timestamp getOrderDate();

  public void setOrderDate(Timestamp date);

  public Timestamp getBeginDate();

  public void setBeginDate(Timestamp date);

  public Timestamp getDoneDate();

  public void setDoneDate(Timestamp date);

}
