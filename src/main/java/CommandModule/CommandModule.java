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

    // Produktionsleder: Oprettelse af produktbatch (bestilling)
    public void leaderCreateBatch(int leaderID, IProductBatchDTO batch) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(leaderID);
            if (employee.getRole().equals(productionLeader)) {
                return pBatchDAO.createProductBatch(batch);
            } else {
                System.err.println("The user trying to create a batch is not Production Leader");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter & Produktionsleder: Fremsøgning af produktbatch status ud fra ID
    public String searchBatch(int labtechID, int batchID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(labtechID);
            if (employee.getRole().equals(labTechnician) || employee.getRole().equals(productionLeader)) {
                return pBatchDAO.getProductBatch(batchID).getBatchStatus();
            } else {
                System.err.println("The user trying to get batch is not a Lab Technician / Production Leader");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter & Produktionsleder: Fremsøgning af produktbatch ud fra ID
    public IProductBatchDTO searchBatchStatus(int labtechID, int batchID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(labtechID);
            if (employee.getRole().equals(labTechnician) || employee.getRole().equals(productionLeader)) {
                return pBatchDAO.getProductBatch(batchID);
            } else {
                System.err.println("The user trying to get batch status is not a Lab Technician / Production Leader");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Farmaceuter & Produktionsleder: Fremsøgning af produktbatches ud fra STATUS
    public List<IProductBatchDTO> statusTable(int labtechID, String status) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(labtechID);
            if (employee.getRole().equals(labTechnician) || employee.getRole().equals(productionLeader)) {
                return pBatchDAO.getAllProductBatchesWhere(status);
            } else {
                System.err.println("The user trying to get batches is not a Lab Technician / Production Leader");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    // Laboranter: Opdatering af igangsatte produktbatches
    public void labtechUpdateStatus(int labtechID, int batchID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(labtechID);
            if (employee.getRole().equals(labTechnician)) {
                if(pBatchDAO.getProductBatch(batchID).getBatchStatus().equals("Ordered")){
                    labtechUpdateResources(batchID);
                    System.out.println("The batch " +batchID+ " has now begun production");
                } else if (pBatchDAO.getProductBatch(batchID).getBatchStatus().equals("Progressing")) {
                    pBatchDAO.finishBatch(batchID);
                    System.out.println("The batch " +batchID+ " has now finished");
                } else if(pBatchDAO.getProductBatch(batchID).getBatchStatus().equals("Finished")) {
                    System.out.println("The batch " +batchID+ " has already finished");
                } else {
                    System.err.println("No product batch status found!");
                }
            } else {
                System.err.println("The user trying to update the batch status is not a Lab Technician");
                return;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /*
    // Lab Technician: Opdatering af råvarer lager
    private void labtechUpdateResources(int batchID) throws DALException {
        IProductBatchDTO tempProductBatch = pBatchDAO.getProductBatch(batchID);
        List<IResourceBatchDTO> tempResources = pContentsDAO.getResourceBatches(batchID);
        List<IRecipeContentsDTO> tempIngrediens = recContentsDAO.getIngredients(tempProductBatch.getRecipeID());

        for (int a = 0; a < tempIngrediens.size(); a++){
            for (int b = 0; b < tempResources.size(); b++) {

                int resourceID = tempResources.get(a).getIngredientID();
                int ingridentID = tempIngrediens.get(b).getIngredientID();

                if (ingridentID == resourceID) {

                }
            }
        }

        pBatchDAO.beginBatch(batchID);
    }
    */
}
