package ProductBatch;

import java.util.*;
import java.sql.Timestamp;

public class ProductBatchDTO implements IProductBatchDTO {
  private int batchID;
  private int recipeID;
  private String batchAmount;
  private Timestamp orderDate;
  private Timestamp beginDate;
  private Timestamp doneDate;

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
  public String getBatchAmount() {
    return batchAmount;
  }

  @Override
  public void setBatchAmount(String amount) {
    batchAmount = amount;
  }

  @Override
  public Timestamp getOrderDate() {
    return orderDate;
  }

  @Override
  public void setOrderDate(Timestamp date) {
    this.orderDate = date;
  }

  @Override
  public Timestamp getBeginDate() {
    return beginDate;
  }

  @Override
  public void setBeginDate(Timestamp date) {
    this.beginDate = date;
  }

  @Override
  public Timestamp getDoneDate() {
    return doneDate;
  }

  @Override
  public void setDoneDate(Timestamp date) {
    this.doneDate = date;
  }
}
