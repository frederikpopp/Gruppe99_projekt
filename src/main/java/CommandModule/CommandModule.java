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

    final private String farmaceut = "farmaceut";
    final private String adminstrator = "adminstrator";
    final private String productionLeader = "productionLeader";
    final private String labTechnician = "labtechnician";

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
                System.err.println("The user trying to update user information is not admin");
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
                System.err.println("The user trying to delete user for database is not admin");
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
                System.err.println("The user trying to get user information is not admin");
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
            if (employee.getRole().equals(farmaceut)) {
                recDAO.createRecipe(recipe);
            } else {
                System.err.println("The user trying to create recipe is not a farmaceut");
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
            if (employee.getRole().equals(farmaceut)) {
                recDAO.removeRecipe(recipe.getRecipeID());
                recDAO.createRecipe(recipe);
            } else {
                System.err.println("The user trying to update recipe is not a farmaceut");
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
            if (employee.getRole().equals(farmaceut)) {
                recDAO.removeRecipe(recipeID);
            } else {
                System.err.println("The user trying to delete recipe is not a farmaceut");
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
            if (employee.getRole().equals(farmaceut)) {
                return recDAO.getRecipe(recipeID);
            } else {
                System.err.println("The user trying to delete recipe is not a farmaceut");
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
                System.err.println("The user trying to fetch list of recipes is not a farmaceut");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Fremsøgning af produktbatch ud fra ID
    // Fremsøgning af produktbatch ud fra STATUS(fx batches der afventer bestiiling)

    // Produktionsleder: Oprettelse af produktbatch (bestilling)
    // Produktionsleder: Opdatering af råvarer lager

    // Laboranter: Opdatering af igangsatte produktbatches
    public void labtechUpdateStatus(int labtechID, int batchID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(labtechID);
            if (employee.getRole().equals(labTechnician) || employee.getRole().equals(productionLeader)) {
                if(pBatchDAO.getProductBatch(batchID).getBatchStatus().equals("Ordered")){

                } else if (pBatchDAO.getProductBatch(batchID).getBatchStatus().equals("Progressing")) {

                } else if(pBatchDAO.getProductBatch(batchID).getBatchStatus().equals("Finished")) {

                } else {
                    System.err.println("No product batch status found!");
                    return;
                }
            } else {
                System.err.println("The user trying to update the batch status is not a Lab Technician");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

}
