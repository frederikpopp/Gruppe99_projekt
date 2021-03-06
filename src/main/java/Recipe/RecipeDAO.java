package Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utilities.ConnectionHandler.createConnection;

public class RecipeDAO implements IRecipeDAO{

    @Override
    public void createRecipe(IRecipeDTO recipe) throws DALException {
        try (Connection c = createConnection()) {
            c.setAutoCommit(false);
            PreparedStatement stmtRecIn = c.prepareStatement(
                    "INSERT INTO recipe VALUES (?,?,?)");
            stmtRecIn.setInt(1, recipe.getRecipeID());
            stmtRecIn.setString(2, recipe.getRecipeName());
            stmtRecIn.setString(3, recipe.getManufacturer());

            stmtRecIn.executeUpdate();

            // Insert all recipe ingredients
            PreparedStatement stmtRC = c.prepareStatement(
                    "INSERT INTO recipe_contents VALUES (?,?,?,?)");
            stmtRC.setInt(1, recipe.getRecipeID());
            for (int i = 0; i < recipe.getIngredients().size(); i++){
                stmtRC.setInt(2, recipe.getIngredients().get(i).getIngredientID());
                stmtRC.setDouble(3, recipe.getIngredients().get(i).getAmount());
                stmtRC.setString(4, recipe.getIngredients().get(i).getUseCase());
                stmtRC.executeUpdate();
            }
            c.commit();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


    @Override
    public IRecipeDTO getRecipe(int recipeID) throws DALException {
        try (Connection c = createConnection()){
            // Select recipe with matching recipeID
            IRecipeDTO rec = new RecipeDTO();
            List<IRecipeContentsDTO> ingList = new ArrayList<>();

            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe WHERE recipe_ID = ?");
            stmtRec.setInt(1, recipeID);

            PreparedStatement stmtIng = c.prepareStatement(
                    "SELECT * FROM recipe_contents WHERE recipe_ID = ?");
            stmtIng.setInt(1, recipeID);

            ResultSet recSet = stmtRec.executeQuery();

            if(recSet.next()){
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));

                ResultSet ingSet = stmtIng.executeQuery();

                while (ingSet.next()){   // Save roles as long as there are new to fetch
                    IRecipeContentsDTO ingredient = new RecipeContentsDTO();
                    ingredient.setIngredientID(ingSet.getInt("ingredient_ID"));
                    ingredient.setAmount(ingSet.getDouble("amount"));
                    ingredient.setUseCase(ingSet.getString("usecase"));
                    ingredient.setRecipeID(recipeID);
                    ingList.add(ingredient);
                }

                rec.setIngredients(ingList);

            }
            return rec;
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public List<IRecipeDTO> getRecipeList() throws DALException {
        try (Connection c = createConnection()){
            List<IRecipeDTO> recipes = new ArrayList<IRecipeDTO>();
            // Prepare statements for recipes
            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe");

            ResultSet recSet = stmtRec.executeQuery();

            // While new user save the values
            while(recSet.next()){
                IRecipeDTO recipe = new RecipeDTO();
                recipe.setRecipeID(recSet.getInt("recipe_ID"));
                recipe.setRecipeID(recSet.getInt("recipe_ID"));
                recipe.setRecipeName(recSet.getString("r_name"));
                recipe.setManufacturer(recSet.getString("manufacturer"));

                recipes.add(recipe);     // Add user to list
            }
            return recipes;
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void removeRecipe(int recipeID) throws DALException {
        try (Connection c = createConnection()){
            // Select recipe with matching recipeID
            IRecipeDTO rec = new RecipeDTO();
            List<IRecipeContentsDTO> ingList = new ArrayList<>();

            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe WHERE recipe_ID = ?");
            stmtRec.setInt(1, recipeID);

            PreparedStatement stmtIng = c.prepareStatement(
                    "SELECT * FROM recipe_contents WHERE recipe_ID = ?");
            stmtIng.setInt(1, recipeID);

            ResultSet recSet = stmtRec.executeQuery();

            if(recSet.next()){
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));

                ResultSet ingSet = stmtIng.executeQuery();


                while (ingSet.next()){   // Save roles as long as there are new to fetch
                    RecipeContentsDTO ingredient = new RecipeContentsDTO();
                    ingredient.setRecipeID(ingSet.getInt("recipe_ID"));
                    ingredient.setIngredientID(ingSet.getInt("ingredient_ID"));
                    ingredient.setAmount(ingSet.getDouble("amount"));
                    ingredient.setUseCase(ingSet.getString("usecase"));
                    ingList.add(ingredient);
                }

                rec.setIngredients(ingList);

            }

            // ********** DELETE RECIPE FROM 'recipe' **********
            c.setAutoCommit(false);
            PreparedStatement stmtRCDel = c.prepareStatement(
                    "DELETE FROM recipe_contents WHERE recipe_ID = ?");
            stmtRCDel.setInt(1, recipeID);

            stmtRCDel.executeUpdate();

            PreparedStatement stmtRecDel = c.prepareStatement(
                    "DELETE FROM recipe WHERE recipe_ID = ?");
            stmtRecDel.setInt(1, recipeID);

            stmtRecDel.executeUpdate();


            // ********** INSERT RECIPE INTO 'recipe_OLD' **********
            PreparedStatement stmtRecIn = c.prepareStatement(
                    "INSERT INTO recipe_OLD VALUES (?,?,?)");
            Timestamp currTime = getCurrentTimeStamp();
            stmtRecIn.setInt(1, rec.getRecipeID());
            stmtRecIn.setString(2, rec.getRecipeName());
            stmtRecIn.setTimestamp(3, currTime);

            stmtRecIn.executeUpdate();

            PreparedStatement stmtRCin = c.prepareStatement(
                    "INSERT INTO recipe_contents_OLD VALUES (?,?,?,?,?)");
            stmtRCin.setInt(1, rec.getRecipeID());
            for (int i = 0; i < rec.getIngredients().size(); i++){
                stmtRCin.setInt(2, rec.getIngredients().get(i).getIngredientID());
                stmtRCin.setDouble(3, rec.getIngredients().get(i).getAmount());
                stmtRCin.setString(4, rec.getIngredients().get(i).getUseCase());
                stmtRCin.setTimestamp(5, currTime);
                stmtRCin.executeUpdate();
            }
            c.commit();
            
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public List<IRecipeDTO> getOldVersions(int recipeID) throws DALException {
        List<IRecipeDTO> oldRecipes = new ArrayList<>();
        try(Connection c = createConnection()) {

            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe_OLD WHERE recipe_ID = ?");
            stmtRec.setInt(1, recipeID);

            PreparedStatement stmtIng = c.prepareStatement(
                    "SELECT * FROM recipe_contents_OLD WHERE recipe_ID = ?");
            stmtIng.setInt(1, recipeID);

            ResultSet recSet = stmtRec.executeQuery();

            while(recSet.next()) {
                RecipeOLDDTO rec = new RecipeOLDDTO();
                List<IRecipeContentsDTO> oldIngList = new ArrayList<>();
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));
                rec.setDateReplaced(recSet.getTimestamp("date_replaced"));

                ResultSet ingSet = stmtIng.executeQuery();

                while (ingSet.next()) {
                    RecipeContentsOLDDTO ingredient = new RecipeContentsOLDDTO();
                    ingredient.setIngredientID(ingSet.getInt("ingredient_ID"));
                    ingredient.setAmount(ingSet.getDouble("amount"));
                    ingredient.setUseCase(ingSet.getString("usecase"));
                    ingredient.setRecipeID(recipeID);
                    ingredient.setDateReplaced(ingSet.getTimestamp("date_replaced"));
                    oldIngList.add(ingredient);
                }
                rec.setIngredients(oldIngList);
                oldRecipes.add(rec);
            }
        } catch(SQLException e) {
            throw new DALException(e.getMessage());
        }

        return oldRecipes;
    }

    @Override
    public List<IRecipeDTO> getVersionsBefore(int recipeID, Timestamp date) throws DALException {
        List<IRecipeDTO> oldRecipes = new ArrayList<>();
        try(Connection c = createConnection()) {

            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe_OLD WHERE recipe_ID = ? AND date_replaced < ?");
            stmtRec.setInt(1, recipeID);
            stmtRec.setTimestamp(2, date);

            PreparedStatement stmtIng = c.prepareStatement(
                    "SELECT * FROM recipe_contents_OLD WHERE recipe_ID = ? AND date_replaced < ?");
            stmtIng.setInt(1, recipeID);
            stmtIng.setTimestamp(2, date);


            ResultSet recSet = stmtRec.executeQuery();

            while(recSet.next()) {
                RecipeOLDDTO rec = new RecipeOLDDTO();
                List<IRecipeContentsDTO> oldIngList = new ArrayList<>();
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));
                rec.setDateReplaced(recSet.getTimestamp("date_replaced"));

                ResultSet ingSet = stmtIng.executeQuery();

                while (ingSet.next()) {
                    RecipeContentsOLDDTO ingredient = new RecipeContentsOLDDTO();
                    ingredient.setIngredientID(ingSet.getInt("ingredient_ID"));
                    ingredient.setAmount(ingSet.getDouble("amount"));
                    ingredient.setUseCase(ingSet.getString("usecase"));
                    ingredient.setRecipeID(recipeID);
                    ingredient.setDateReplaced(ingSet.getTimestamp("date_replaced"));
                    oldIngList.add(ingredient);
                }
                rec.setIngredients(oldIngList);
                oldRecipes.add(rec);
            }
        } catch(SQLException e) {
            throw new DALException(e.getMessage());
        }

        return oldRecipes;
    }

    @Override
    public List<IRecipeDTO> getVersionsAfter(int recipeID, Timestamp date) throws DALException {
        List<IRecipeDTO> oldRecipes = new ArrayList<>();
        try(Connection c = createConnection()) {

            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe_OLD WHERE recipe_ID = ? AND date_replaced > ?");
            stmtRec.setInt(1, recipeID);
            stmtRec.setTimestamp(2, date);

            PreparedStatement stmtIng = c.prepareStatement(
                    "SELECT * FROM recipe_contents_OLD WHERE recipe_ID = ? AND date_replaced > ?");
            stmtIng.setInt(1, recipeID);
            stmtIng.setTimestamp(2, date);


            ResultSet recSet = stmtRec.executeQuery();

            while(recSet.next()) {
                RecipeOLDDTO rec = new RecipeOLDDTO();
                List<IRecipeContentsDTO> oldIngList = new ArrayList<>();
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));
                rec.setDateReplaced(recSet.getTimestamp("date_replaced"));

                ResultSet ingSet = stmtIng.executeQuery();

                while (ingSet.next()) {
                    RecipeContentsOLDDTO ingredient = new RecipeContentsOLDDTO();
                    ingredient.setIngredientID(ingSet.getInt("ingredient_ID"));
                    ingredient.setAmount(ingSet.getDouble("amount"));
                    ingredient.setUseCase(ingSet.getString("usecase"));
                    ingredient.setRecipeID(recipeID);
                    ingredient.setDateReplaced(ingSet.getTimestamp("date_replaced"));
                    oldIngList.add(ingredient);
                }
                rec.setIngredients(oldIngList);
                oldRecipes.add(rec);
            }
        } catch(SQLException e) {
            throw new DALException(e.getMessage());
        }

        return oldRecipes;
    }


    private Timestamp getCurrentTimeStamp() {
        java.util.Date now = new java.util.Date();
        return new Timestamp(now.getTime());
    }

}
