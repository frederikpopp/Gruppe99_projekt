package Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utilities.ConnectionHandler.createConnection;

public class RecipeDAO implements IRecipeDAO{

    @Override
    public void createRecipe(IRecipeDTO recipe) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmtRecIn = c.prepareStatement(
                    "INSERT INTO recipe VALUES (?,?,?)");
            stmtRecIn.setInt(1, recipe.getRecipeID());
            stmtRecIn.setString(2, recipe.getRecipeName());
            stmtRecIn.setString(3, recipe.getManufacturer());

            stmtRecIn.executeUpdate();


        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


    @Override
    public IRecipeDTO getRecipe(int recipeID) throws DALException {
        try (Connection c = createConnection()){
            // Select recipe with matching recipeID
            IRecipeDTO rec = new RecipeDTO();
            PreparedStatement stmtRec = c.prepareStatement(
                    "SELECT * FROM recipe WHERE recipe_ID = ?");
            stmtRec.setInt(1, recipeID);

            ResultSet recSet = stmtRec.executeQuery();

            // If there is a matching userID, insert into user object
            if(recSet.next()){
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));
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

            // ********** GET RECIPE FROM 'recipe' **********
            PreparedStatement stmtRecGet = c.prepareStatement(
                    "SELECT * FROM recipe WHERE recipe_ID = ?");
            stmtRecGet.setInt(1, recipeID);

            ResultSet recSet = stmtRecGet.executeQuery();

            if(recSet.next()){
                rec.setRecipeID(recipeID);
                rec.setRecipeName(recSet.getString("r_name"));
                rec.setManufacturer(recSet.getString("manufacturer"));
            }

            // ********** INSERT RECIPE INTO 'recipe_OLD' **********
            PreparedStatement stmtRecIn = c.prepareStatement(
                    "INSERT INTO recipe_OLD VALUES (?,?,?)");
            stmtRecIn.setInt(1, recipeID);
            stmtRecIn.setString(2, rec.getRecipeName());
            stmtRecIn.setTimestamp(3, getCurrentTimeStamp());

            stmtRecIn.executeUpdate();


            // ********** DELETE RECIPE FROM 'recipe' **********
            PreparedStatement stmtRecDel = c.prepareStatement(
                    "DELETE FROM recipe WHERE recipe_ID = ?");
            stmtRecDel.setInt(1, recipeID);

            stmtRecDel.executeUpdate();



        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    private Timestamp getCurrentTimeStamp() {
        java.util.Date now = new java.util.Date();
        return new Timestamp(now.getTime());
    }

}
