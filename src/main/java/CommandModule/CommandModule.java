package CommandModule;

import Ingredient.*;
import ProductBatch.*;
import Recipe.*;
import ResourceBatch.*;
import User.*;
import Utilities.DAO;

import java.util.ArrayList;
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

    final private String pharmacist = "pharmacist";
    final private String productionLeader = "productionLeader";
    final private String labTechnician = "labtechnician";

    /**
     * Admin : Creation of a user
     * @param adminID ID of the admin to perform the action
     * @param userObj The user to be uploaded
     * @throws DALException Extension of SQLException
     */
    public void adminstratorCreateUser(int adminID, IUserDTO userObj) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(adminID);
            if (employee.getAdminStatus()) userDAO.createUser(userObj);
            else {
                System.err.println("The user trying to create new user is not admin");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Admin : Update of a user
     * @param adminID ID of the admin to perform the action
     * @param userObj The user to be updated
     * @throws DALException Extension of SQLException
     */
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

    /**
     * Admin : Delete a user
     * @param adminID ID of admin to perform the action
     * @param userID ID of user to be deleted
     * @throws DALException Extension of SQLException
     */
    public void adminstratorDeleteUser(int adminID, int userID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(adminID);
            if (employee.getAdminStatus()) {
                userDAO.deleteUser(userID);
            } else {
                System.err.println("The user trying to delete user for database is not admin");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Admin : Get a user
     * @param adminID ID of admin to perform the action
     * @param userID ID of user to be fetched
     * @return IUserDTO of fetched user
     * @throws DALException Extension of SQLException
     */
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

    /**
     * Pharmacist : Create a new recipe
     * @param pharmaID ID of pharmacist to perform the action
     * @param recipe IRecipeDTO object of new recipe
     * @throws DALException Extension of SQLException
     */
    public void pharmaCreateRecipe(int pharmaID, IRecipeDTO recipe) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(pharmaID);
            if (employee.getRole().equals(pharmacist)) {
                recDAO.createRecipe(recipe);
            } else {
                System.err.println("The user trying to create recipe is not a pharmacist");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Pharmacist : Update a recipe
     * @param pharmaID ID of pharmacist to perform the action
     * @param recipe IRecipeDTO obj of updated recipe
     * @throws DALException Extension of SQLException
     */
    public void pharmaUpdateRecipe(int pharmaID, IRecipeDTO recipe) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(pharmaID);
            if (employee.getRole().equals(pharmacist)) {
                recDAO.removeRecipe(recipe.getRecipeID());
                recDAO.createRecipe(recipe);
            } else {
                System.err.println("The user trying to update recipe is not a pharmacist");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Pharmacist : Delete a recipe
     * @param pharmaID ID of pharmacist to perform the action
     * @param recipeID ID of recipe to be deleted
     * @throws DALException Extension of SQLException
     */
    public void pharmaDeleteRecipe(int pharmaID, int recipeID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(pharmaID);
            if (employee.getRole().equals(pharmacist)) {
                recDAO.removeRecipe(recipeID);
            } else {
                System.err.println("The user trying to delete recipe is not a pharmacist");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }
    /**
     * Pharmacist : Get a recipe
     * @param pharmaID ID of pharmacist to perform the action
     * @param recipeID ID of recipe to be fetched
     * @return IRecipeDTO obj of fetched recipe
     * @throws DALException Extension of SQLException
     */
    public IRecipeDTO pharmaGetRecipe(int pharmaID, int recipeID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(pharmaID);
            if (employee.getRole().equals(pharmacist)) {
                return recDAO.getRecipe(recipeID);
            } else {
                System.err.println("The user trying to delete recipe is not a pharmacist");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Pharmacist : Get all recipes
     * @param pharmaID ID of pharmacist to perform the action
     * @return List<IRecipeDTO> list of all recipes
     * @throws DALException Extension of SQLException
     */
    public List<IRecipeDTO> pharmaGetRecipes(int pharmaID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(pharmaID);
            if (employee.getRole().equals(pharmacist)) {
                return recDAO.getRecipeList();
            } else {
                System.err.println("The user trying to fetch list of recipes is not a pharmacist");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Production Leader : Create a batch (order)
     * @param leaderID ID of production leader to perform the action
     * @param batch IProductBatchDTO obj of batch to be ordered
     * @throws DALException Extension of SQLException
     */
    public void leaderCreateBatch(int leaderID, IProductBatchDTO batch) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(leaderID);
            List<IProductContentsDTO> reservationList = new ArrayList<>();
            if (employee.getRole().equals(productionLeader)) {
                pBatchDAO.createProductBatch(batch);
                List<IRecipeContentsDTO> ingredientList = recContentsDAO.getIngredients(batch.getRecipeID());
                for (IRecipeContentsDTO i : ingredientList) {
                    List<IResourceBatchDTO> resources = resourceDAO.getIngredientBatches(i.getIngredientID());
                    for (IResourceBatchDTO r : resources) {
                        double reqAmount = i.getAmount()*batch.getBatchAmount()+(i.getAmount()*batch.getBatchAmount())*0.02;
                        if (r.getAmount() >= reqAmount) {
                            IProductContentsDTO pc = new ProductContentsDTO();
                            pc.setProductBatch(batch.getBatchID());
                            pc.setResourceBatch(r.getBatchID());
                            pc.setAmount(reqAmount);
                            reservationList.add(pc);
                            break;
                        }
                    }
                }

                // If all ingredients are available in resourcebatches
                if (ingredientList.size() == reservationList.size()) {
                    // Add them to the list
                    pBatchDAO.orderBatch(batch.getBatchID());
                    for (IProductContentsDTO p : reservationList) {
                        pContentsDAO.addResourceBatch(p);
                        // And subtract amount from resourcebatch
                        IResourceBatchDTO r = resourceDAO.getBatch(p.getResourceBatch());
                        r.setAmount(r.getAmount()-p.getAmount());
                        resourceDAO.updateBatch(r);
                    }
                } else {
                    reservationList.clear();
                    System.err.println("Cannot order batch " +batch.getBatchID() +" due to lack of resources");
                }
            } else {
                System.err.println("The user trying to create a batch is not Production Leader");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Production Leader & Pharmacist : Get current status of a product batch
     * @param ID ID of production leader or pharmacist
     * @param batchID ID of product batch to be fetched
     * @return The status of the batch as a String
     * @throws DALException Extension of SQLException
     */
    public String searchBatchStatus(int ID, int batchID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(ID);
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

    /**
     * Production Leader & Pharmacist : Get a product batch
     * @param ID ID of production leader or pharmacist
     * @param batchID ID of product batch to get
     * @return IProductBatchDTO obj of the fetched product batch
     * @throws DALException Extension of SQLException
     */
    public IProductBatchDTO searchBatch(int ID, int batchID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(ID);
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

    /**
     * Production Leader & Pharmacist : Get all batches with a given status
     * @param ID ID of production leader or pharmacist
     * @param status Status of the batches to fetch
     * @return List<IProductBatchDTO> List of productbatch obj with given status
     * @throws DALException Extension of SQLException
     */
    public List<IProductBatchDTO> statusTable(int ID, String status) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(ID);
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

    /**
     * Lab Technician : Update status of a product batch
     * @param labtechID ID of lab technician
     * @param batchID ID of product batch status to update
     * @param usedResources List<IProductContents> List of actual used resources
     * @throws DALException Extension of SQLException
     */
    public void labtechUpdateStatus(int labtechID, int batchID, List<IProductContentsDTO> usedResources) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(labtechID);
            if (employee.getRole().equals(labTechnician)) {
                switch (pBatchDAO.getProductBatch(batchID).getBatchStatus()) {
                    case "Ordered":
                        pBatchDAO.beginBatch(batchID);
                        System.out.println("Batch #" + batchID + " has now begun production");
                        break;
                    case "Progressing":
                        // Correct the actual used amounts of resources
                        List<IProductContentsDTO> resources = pContentsDAO.getProductContents(batchID);
                        for (int i = 0; i < usedResources.size(); i++) {
                            double difference = resources.get(i).getAmount() - usedResources.get(i).getAmount();
                            pContentsDAO.updateResourceAmount(usedResources.get(i));
                            // Subtract amount from resourcebatch
                            IResourceBatchDTO r = resourceDAO.getBatch(usedResources.get(i).getResourceBatch());
                            r.setAmount(r.getAmount() - difference);
                            resourceDAO.updateBatch(r);
                        }
                        pBatchDAO.finishBatch(batchID);
                        System.out.println("Batch #" + batchID + " has now finished");
                        break;
                    case "Finished":
                        System.out.println("Batch #" + batchID + " has already finished");
                        break;
                    default:
                        System.err.println("No product batch status found!");
                        break;
                }
            } else {
                System.err.println("The user trying to update the batch status is not a Lab Technician");
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Production Leader : Get a list of all ingredients to reorder
     * @param leaderID ID of a production leader
     * @return List<IIngredientDTO> List of ingredient objs that need a new order
     * @throws DALException Extension of SQLException
     */
    public List<IIngredientDTO> getReorderList(int leaderID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(leaderID);
            if (employee.getRole().equals(productionLeader)) {
                return ingrDAO.getReorderList();
            }
            else {
                System.err.println("The user trying to get the reorder list is not a Production Leader");
                return null;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

    /**
     * Production Leader : Get the total amount stored in batches for a given ingredient
     * @param leaderID ID of production leader
     * @param ingredientID ID of ingredient to get amount of
     * @return the total amount across all batches of the given ingredient
     * @throws DALException Extension of SQLException
     */
    public double getTotalIngredientAmount(int leaderID, int ingredientID) throws DALException {
        try {
            IUserDTO employee = userDAO.getUser(leaderID);
            if (employee.getRole().equals(productionLeader)) {
                return resourceDAO.getTotalIngredientAmount(ingredientID);
            }
            else {
                System.err.println("The user trying to get the total ingredient amount is not a Production Leader");
                return 0;
            }
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }

}
