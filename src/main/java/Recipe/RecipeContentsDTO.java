package Recipe;

public class RecipeContentsDTO implements IRecipeContentsDTO {
  private int recipeID;
  private int ingredientID;                 //Maybe ingredientDTO object instead?
  private double amount;
  private String usecase;

  public RecipeContentsDTO() {
    //Empty for now
  }

  public int getRecipeID() {
    return recipeID;
  }

  public void setRecipeID(int ID) {
    recipeID = ID;
  }

  public int getIngredientID() {
    return ingredientID;
  }

  public void setIngredientID(int ID) {
    ingredientID = ID;
  }

  public double getAmount() {
    return amount;
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

  @Override
  public String toString() {
    String x = "IngredientDTO [recipeID="+ recipeID+", ingredientID=" +ingredientID+", amount="+ amount + ", usecase=" +usecase +"]";
    return x;
  }

}
