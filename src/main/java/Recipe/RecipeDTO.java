package Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO implements IRecipeDTO{
  private int recipeID;
  private String name;
  private String manufacturer;
  private List<IRecipeContentsDTO> ingredientsList;

  public RecipeDTO() {
    ingredientsList = new ArrayList<IRecipeContentsDTO>();
  }

  @Override
  public int getRecipeID() {
    return recipeID;
  }

  @Override
  public void setRecipeID(int ID) {
    recipeID = ID;
  }

  @Override
  public String getRecipeName() {
    return name;
  }

  @Override
  public void setRecipeName(String name) {
    this.name = name;
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
  public List<IRecipeContentsDTO> getIngredients() {
    return ingredientsList;
  }

  @Override
  public void setIngredients(List<IRecipeContentsDTO> ingredients) {
    ingredientsList = ingredients;
  }
}
