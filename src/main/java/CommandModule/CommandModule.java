package CommandModule;

import Ingredient.*;
import ProductBatch.*;
import Recipe.*;
import ResourceBatch.*;
import User.*;

public class CommandModule {
    private IIngredientDAO ingrDAO;
    private IProductBatchDAO pBatchDAO;
    private IProductContentsDAO pContentsDAO;
    private IRecipeDAO recDAO;
    private IRecipeContentsDAO recContentsDAO;
    private IResourceBatchDAO resourceDAO;
    private IUserDAO userDAO;

    public CommandModule() {
        ingrDAO = new IngredientDAO();
        pBatchDAO = new ProductBatchDAO();
        pContentsDAO = new ProductContentsDAO();
        recDAO = new RecipeDAO();
        recContentsDAO = new RecipeContentsDAO();
        resourceDAO = new ResourceBatchDAO();
        userDAO = new UserDAO();
    }

    




}