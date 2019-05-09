import Recipe.*;
import User.*;
import Ingredient.*;
import ResourceBatch.*;
import ProductBatch.*;
import Utilities.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args)
    {
        // USER TEST
        UserDAO DB = new UserDAO();
        List<IUserDTO> users = new ArrayList<>();
        List<IUserDTO> persons = new ArrayList<>();

        // INGREDIENT TEST
        IIngredientDAO iDAO = new IngredientDAO();
        IIngredientDTO ingredient1 = new IngredientDTO();
        IIngredientDTO ingredient2 = new IngredientDTO();
        List<IIngredientDTO> allIngredients = new ArrayList<>();
        List<IIngredientDTO> reorderList = new ArrayList<>();

        // RECIPE TEST
        IRecipeDAO rDAO = new RecipeDAO();
        IRecipeDTO rec1 = new RecipeDTO();
        IRecipeDTO rec2 = new RecipeDTO();
        List<IRecipeDTO> allRecipes = new ArrayList<>();

        // RESOURCE TEST
        IResourceBatchDTO resource = new ResourceBatchDTO();
        IResourceBatchDAO resDAO = new ResourceBatchDAO();
        List<IResourceBatchDTO> allResources = new ArrayList<>();

        IProductBatchDAO pBatchDAO = new ProductBatchDAO();
        IProductBatchDTO batch = new ProductBatchDTO();
        IProductContentsDAO pContentsDAO = new ProductContentsDAO();
        IRecipeContentsDAO recContentsDAO = new RecipeContentsDAO();
        IResourceBatchDAO resourceDAO = new ResourceBatchDAO();

        final int bID = 50;
        final int rID = 10;
        batch.setBatchID(bID);
        batch.setRecipeID(rID);
        batch.setBatchAmount(10);


        System.out.println(" ----- TEST STARTING -----");


        try {
          List<IProductContentsDTO> reservationList = new ArrayList<>();
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



        } catch (DAO.DALException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(" -----  END OF TEST  -----");
    }
}
