package Recipe;

public class RecipeContentsDTO implements IRecipeContentsDTO {
  private int ingredientID;                 //Maybe ingredientDTO object instead?
  private double amount;
  private String usecase;

  public RecipeContentsDTO() {
    //Empty for now
  }

  public int getIngredientID() {
    return ingredientID;
  }

  public void setIngredientID(int ID) {
    ingredientID = ID;
  }

  public double getAmount() {
    return Amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getUseCase() {
    return usecase;
  }

  public void setUseCase(String usecase) {
    this.usecase = usecase;
  }
}
