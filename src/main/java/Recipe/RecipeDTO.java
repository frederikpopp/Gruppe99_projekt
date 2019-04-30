package Recipe;

public class RecipeDTO {
  private int recipeID;
  private String name;
  private String manufacturer;
  private List<IRecipeContentsDTO> ingredientsList;

  public RecipeDTO() {
    ingredientsList = new ArrayList<IRecipeContentsDTO>;
  }

  public int getRecipeID() {
    return recipeID;
  }

  public void setRecipeID(int ID) {
    recipeID = ID;
  }

  public String getRecipeName() {
    return name;
  }

  public void setRecipeName(String name) {
    this.name = name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public List<IRecipeContentsDTO> getIngredients() {
    return ingredientsList;
  }

  public void setIngredients(List<IRecipeContentsDTO> ingredients) {
    ingredientsList = ingredients;
  }

}
