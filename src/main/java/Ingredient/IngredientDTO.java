package Ingredient;

public class IngredientDTO implements IIngredientDTO{
  private int ingredientID;
  private String name;
  private boolean reorder;

  public IngredientDTO() {
    //Empty for now
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
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean getOrderStatus() {
    return reorder;
  }

  @Override
  public void setOrderStatus(boolean status) {
    reorder = status;
  }
}
