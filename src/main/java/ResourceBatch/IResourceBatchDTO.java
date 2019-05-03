package ResourceBatch;

public interface IResourceBatchDTO {
  public int getBatchID();

  public void setBatchID(int ID);

  public int getIngredientID();

  public void setIngredientID(int ID);

  public String getManufacturer();

  public void setManufacturer(String manufacturer);

  public double getAmount();

  public void setAmount(double amount);

  public double getRemainder();

  public void setRemainder(double remainder);
}
