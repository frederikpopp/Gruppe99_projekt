package ProductBatch;

import java.util.*;
import java.sql.Timestamp;

public class ProductBatchDTO {
  private int batchID;
  private int recipeID;
  private int batchStatus;
  private Timestamp date;

  public ProductBatchDTO() {
    //Empty for now
  }

  public int getBatchID() {
    return batchID;
  }

  public void setBatchID(int ID) {
    batchID = ID;
  }

  public int getRecipeID() {
    return recipeID;
  }

  public void setRecipeID(int ID) {
    recipeID = ID;
  }

  public int getBatchStatus() {
    return batchStatus;
  }

  public void setBatchStatus(int status) {
    batchStatus = status;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }
}
