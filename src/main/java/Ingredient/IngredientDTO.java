package Ingredient;

public class IngredientDTO {
  private int ingredientID;
  private String name;
  private boolean reorder;

  public IngredientDTO() {
    //Empty for now
  }

  public int getIngredientID() {
    return ingredientID;
  }

  public void setIngredientID(int ID) {
    ingredientID = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getOrderStatus() {
    return reorder;
  }

  public void setOrderStatus(boolean status) {
    reorder = status;
  }
}
