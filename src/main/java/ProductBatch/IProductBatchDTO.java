package ProductBatch;

public interface IProductBatchDTO {
  
  public int getBatchID();

  public void setBatchID(int ID);

  public int getRecipeID();

  public void setRecipeID(int ID);

  public int getBatchStatus();

  public void setBatchStatus(int status);

  public Date getDate();

  public void setDate(Date date);
}
