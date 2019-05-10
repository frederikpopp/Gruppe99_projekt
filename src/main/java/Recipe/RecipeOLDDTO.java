package Recipe;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RecipeOLDDTO implements IRecipeDTO {

    private int recipeID;
    private String name;
    private String manufacturer;
    private List<IRecipeContentsDTO> ingredients;
    private Timestamp date_replaced;

    public RecipeOLDDTO() {
        ingredients = new ArrayList<>();
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
        return ingredients;
    }

    @Override
    public void setIngredients(List<IRecipeContentsDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public Timestamp getDateReplaced() {
        return date_replaced;
    }

    public void setDateReplaced(Timestamp date) {
        date_replaced = date;
    }
}
