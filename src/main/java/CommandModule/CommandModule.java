package CommandModule;

import Ingredient.*;
import ProductBatch.*;
import Recipe.*;
import ResourceBatch.*;
import User.*;

public class CommandModule{
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

    // Adminstrator: Oprettelse af brugere
    // Adminstrator: Opdatering af brugere
    // Adminstrator: Sletning af brugere

    // Farmaceuter: Opretter opskrift
    // Farmaceuter: Opdaterer opskrift
    // Farmaceuter: Sletter opskrift

    // Fremsøgning af produktbatch ud fra ID
    // Fremsøgning af produktbatch ud fra STATUS(fx batches der afventer bestiiling)

    // Produktionsleder: Oprettelse af produktbatch (bestilling)
    // Produktionsleder: Opdatering af råvarer lager

    // Laboranter: Opdatering af igangsatte produktbatches
}