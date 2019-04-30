package ResourceBatch;

public interface IResourceBatchDTO {
  public int getBatchID();

  public void setBatchID(int ID);

  public int getIngredientID();

  public void setIngredientID(int ID);

  public String getManufacturer();

  public void setManufacturer(String manufacturer);

  public int getAmount();

  public void setAmount(int amount);

  public int getRemainder();

  public void setRemainder(int remainder);
}
