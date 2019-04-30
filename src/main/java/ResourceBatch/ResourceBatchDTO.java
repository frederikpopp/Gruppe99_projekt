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

  public int getBatchID() {
    return resourceBatchID;
  }

  public void setBatchID(int ID) {
    resourceBatchID = ID;
  }

  public int getIngredientID() {
    return ingredientID;
  }

  public void setIngredientID(int ID) {
    ingredientID = ID;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getRemainder() {
    return remainder;
  }

  public void setRemainder(int remainder) {
    this.remainder = remainder;
  }

}
