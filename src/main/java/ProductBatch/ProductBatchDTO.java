package ProductBatch;

import java.util.*;
import java.sql.Timestamp;

public class ProductBatchDTO implements IProductBatchDTO {
  private int batchID;
  private int recipeID;
  private int batchStatus;
  private Timestamp date;

  public ProductBatchDTO() {
    //Empty for now
  }

  @Override
  public int getBatchID() {
    return batchID;
  }

  @Override
  public void setBatchID(int ID) {
    batchID = ID;
  }

  @Override
  public int getRecipeID() {
    return recipeID;
  }

  @Override
  public void setRecipeID(int ID) {
    recipeID = ID;
  }

  @Override
  public int getBatchStatus() {
    return batchStatus;
  }

  @Override
  public void setBatchStatus(int status) {
    batchStatus = status;
  }

  @Override
  public Timestamp getDate() {
    return date;
  }

  @Override
  public void setDate(Timestamp date) {
    this.date = date;
  }
}
