package ResourceBatch;

public class ResourceBatchDTO {
  private int resourceBatchID;
  private int ingredientID;
  private String manufacturer;
  private int amount;
  private int remainder;

  public ResourceBatchDTO() {
    //Empty for now
  }
  @Override
  public int getBatchID() {
    return resourceBatchID;
  }

  @Override
  public void setBatchID(int ID) {
    resourceBatchID = ID;
  }

  @Override
  public int getIngredientID() {
    return ingredientID;
  }

  @Override
  public void setIngredientID(int ID) {
    ingredientID = ID;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public int getAmount() {
    return amount;
  }

  @Override
  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public int getRemainder() {
    return remainder;
  }

  @Override
  public void setRemainder(int remainder) {
    this.remainder = remainder;
  }

}
