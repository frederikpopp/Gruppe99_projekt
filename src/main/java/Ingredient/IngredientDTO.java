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

  public int getOrderStatus(int input) {
    if (reorder) return 1;
    else return 0;
  }

  @Override
  public void setOrderStatus(boolean status) {
    reorder = status;
  }

  public String toString() {
    return "IngredientDTO [ingredientID="+ingredientID+", name=" +name+", reorder="+ reorder +"]";
  }
}
