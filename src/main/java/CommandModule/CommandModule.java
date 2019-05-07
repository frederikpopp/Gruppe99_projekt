package CommandModule;

import Ingredient.*;
import ProductBatch.*;
import Recipe.*;
import ResourceBatch.*;
import User.*;
import Utilities.DAO;

import java.util.List;

public class CommandModule implements DAO {
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

    String farmaceut = "farmaceut";
    String adminstrator = "adminstrator";
    String productionLeader = "productionLeader";

    // Adminstrator: Oprettelse af bruger
    public void adminstratorCreateUser(int adminID, IUserDTO userObj) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(adminID);
            if (employee.getAdminStatus()) userDAO.createUser(userObj);
            else {
                System.out.println("The user trying to create new user is not admin");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Adminstrator: Opdatering af bruger
    public void adminstratorUpdateUser(int adminID, IUserDTO userObj) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(adminID);
            if (employee.getAdminStatus()) userDAO.updateUser(userObj);
            else {
                System.out.println("The user trying to update user information is not admin");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Adminstrator: Sletning af bruger
    public void adminstratorDeleteUser(int adminID, int userID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(adminID);
            if (employee.getAdminStatus()) {
                userDAO.deleteUser(userID);
            } else {
                System.out.println("The user trying to delete user for database is not admin");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Adminstrator: Hentning af bruger
    public IUserDTO adminstratorGetUser(int adminID, int userID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(adminID);
            if (employee.getAdminStatus()) {
                return userDAO.getUser(userID);
            } else {
                System.out.println("The user trying to get user information is not admin");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter: Opretter opskrift
    public void farmaCreateRecipe(int farmaID, IRecipeDTO recipe) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(farmaID);
            if (employee.getRole() == farmaceut) {
                recDAO.createRecipe(recipe);
            } else {
                System.out.println("The user trying to create recipe is not a farmaceut");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter: Opdaterer opskrift
    public void farmaUpdateRecipe(int farmaID, IRecipeDTO recipe) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(farmaID);
            if (employee.getRole() == farmaceut) {
                recDAO.removeRecipe(recipe.getRecipeID());
                recDAO.createRecipe(recipe);
            } else {
                System.out.println("The user trying to update recipe is not a farmaceut");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter: Sletter opskrift
    public void farmaDeleteRecipe(int farmaID, int recipeID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(farmaID);
            if (employee.getRole() == farmaceut) {
                recDAO.removeRecipe(recipeID);
            } else {
                System.out.println("The user trying to delete recipe is not a farmaceut");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter: Hent nuværende opskrift
    public IRecipeDTO farmaGetRecipe(int farmaID, int recipeID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(farmaID);
            if (employee.getRole() == farmaceut) {
                return recDAO.getRecipe(recipeID);
            } else {
                System.out.println("The user trying to delete recipe is not a farmaceut");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter: Hent liste af med alle opskrifter
    public List<IRecipeDTO> farmaGetRecipe(int farmaID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(farmaID);
            if (employee.getRole() == farmaceut) {
                return recDAO.getRecipeList();
            } else {
                System.out.println("The user trying to fetch list of recipes is not a farmaceut");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

}
