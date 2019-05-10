package Recipe;

import java.sql.Timestamp;

public class RecipeContentsOLDDTO implements IRecipeContentsDTO {

    private int recipeID;
    private int ingredientID;
    private double amount;
    private String usecase;
    private Timestamp date_replaced;

    @Override
    public int getRecipeID() {
        return recipeID;
    }

    @Override
    public void setRecipeID(int ID) {
        recipeID = ID;
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
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String getUseCase() {
        return usecase;
    }

    @Override
    public void setUseCase(String usecase) {
        this.usecase = usecase;
    }

    public Timestamp getDateReplaced() {
        return date_replaced;
    }

    public void setDateReplaced(Timestamp date) {
        date_replaced = date;
    }
}
