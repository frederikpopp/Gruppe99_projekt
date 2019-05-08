package Recipe;

import java.util.List;

public interface IRecipeDTO {
  public int getRecipeID();

  public void setRecipeID(int ID);

  public String getRecipeName();

  public void setRecipeName(String name);

  public String getManufacturer();

  public void setManufacturer(String manufacturer);

  public List<IRecipeContentsDTO> getIngredients();

  public void setIngredients(List<IRecipeContentsDTO> ingredients);

}

