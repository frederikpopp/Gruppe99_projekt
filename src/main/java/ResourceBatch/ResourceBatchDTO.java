package ResourceBatch;

public class ResourceBatchDTO implements IResourceBatchDTO {
  private int resourceBatchID;
  private int ingredientID;
  private String manufacturer;
  private double amount;
  private double remainder;

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
  public double getAmount() {
    return amount;
  }

  @Override
  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public double getRemainder() {
    return remainder;
  }

  @Override
  public void setRemainder(double remainder) {
    this.remainder = remainder;
  }

  public String toString() {
    return "ResourceBatchDTO [batchID="+resourceBatchID+"ingredientID="+ingredientID+", manufacturer=" +manufacturer+", amount="+amount+", remainder="+remainder+"]";
  }

}
