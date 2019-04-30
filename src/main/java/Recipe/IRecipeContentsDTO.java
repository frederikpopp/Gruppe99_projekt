package Recipe;

public interface IRecipeContentsDTO {
  public int getIngredientID();

  public void setIngredientID(int ID);

  public double getAmount();

  public void setAmount(double amount);

  public String getUseCase();

  public void setUseCase(String usecase);
}
