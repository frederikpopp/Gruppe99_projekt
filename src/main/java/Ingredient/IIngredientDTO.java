package Ingredient;

public interface IIngredientDTO {
  public int getIngredientID();

  public void setIngredientID(int ID);

  public String getName();

  public void setName(String name);

  public boolean getOrderStatus();

  public int getOrderStatus(int input);

  public void setOrderStatus(boolean status);
}
