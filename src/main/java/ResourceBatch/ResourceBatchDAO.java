package ResourceBatch;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import static Utilities.ConnectionHandler.createConnection;


public class ResourceBatchDAO  implements IResourceBatchDAO {

  private HashMap<Integer, Double> ingredientMin;

  public ResourceBatchDAO() {
    initHashMap();
  }


  @Override
  public void addBatch(IResourceBatchDTO batch) throws DALException {
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "INSERT INTO resourcebatch VALUES(?,?,?,?,?)");
      stmt.setInt(1, batch.getBatchID());
      stmt.setInt(2, batch.getIngredientID());
      stmt.setString(4, batch.getManufacturer());
      if (batch.getAmount() < ingredientMin.get(batch.getIngredientID())) {
        batch.setRemainder(batch.getAmount());
        batch.setAmount(0);
      }
      stmt.setDouble(3, batch.getAmount());
      stmt.setDouble(5, batch.getRemainder());
      stmt.executeUpdate();

    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
    updateReorder(batch);
  }

  @Override
  public IResourceBatchDTO getBatch(int batchID) throws DALException {
    IResourceBatchDTO batch = new ResourceBatchDTO();
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "SELECT * FROM resourcebatch WHERE r_batch_ID = ?");
      stmt.setInt(1, batchID);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        batch.setBatchID(batchID);
        batch.setIngredientID(result.getInt("ingredient_ID"));
        batch.setManufacturer(result.getString("manufacturer"));
        batch.setAmount(result.getDouble("amount"));
        batch.setRemainder(result.getDouble("remainder"));
      }
      return batch;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  @Override
  public List<IResourceBatchDTO> getAllBatches() throws DALException {
    List<IResourceBatchDTO> batchList = new ArrayList<>();
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "SELECT * FROM resourcebatch");
      ResultSet results = stmt.executeQuery();
      while(results.next()) {
        IResourceBatchDTO batch = new ResourceBatchDTO();
        batch.setBatchID(results.getInt("r_batch_ID"));
        batch.setIngredientID(results.getInt("ingredient_ID"));
        batch.setManufacturer(results.getString("manufacturer"));
        batch.setAmount(results.getDouble("amount"));
        batch.setRemainder(results.getDouble("remainder"));
        batchList.add(batch);
      }
      return batchList;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  @Override
  public void updateBatch(IResourceBatchDTO batch) throws DALException {
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "UPDATE resourcebatch SET ingredient_ID = ?, manufacturer = ?, "+
        "amount = ?, remainder = ? WHERE r_batch_ID = ?");
      stmt.setInt(1, batch.getIngredientID());
      stmt.setString(2, batch.getManufacturer());
      stmt.setInt(5, batch.getBatchID());
      if (batch.getAmount() < ingredientMin.get(batch.getIngredientID())) {
        batch.setRemainder(batch.getAmount());
        batch.setAmount(0);
      }
      stmt.setDouble(3, batch.getAmount());
      stmt.setDouble(4, batch.getRemainder());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
    updateReorder(batch);
  }

  @Override
  public void deleteBatch(int batchID) throws DALException {
    IResourceBatchDTO batch = new ResourceBatchDTO();
    try (Connection c = createConnection()) {
      //First get the batch, to update total amount
      batch = getBatch(batchID);
      PreparedStatement stmt = c.prepareStatement(
        "DELETE FROM resourcebatch WHERE r_batch_ID = ?");
      stmt.setInt(1, batchID);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
    updateReorder(batch);
  }


/*
  @Override
  public List<IResourceBatchDTO> checkRemainder() throws DALException {
    List<IResourceBatchDTO> batchList = new ArrayList<>();
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
      "SELECT * FROM resoucebatch WHERE remainder > 0");
      ResultSet results = stmt.executeQuery();
      while (results.next()) {
        IResourceBatchDTO batch = new ResourceBatchDTO();
        batch.setBatchID(results.getInt("r_batch_ID"));
        batch.setIngredientID(results.getInt("ingredient_ID"));
        batch.setManufacturer(results.getString("manufacturer"));
        batch.setAmount(results.getInt("amount"));
        batch.setRemainder(results.getInt("remainder"));
        batchList.add(batch);
      }
      return batchList;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }
  */

  private int getTotalIngredientAmount(int ingredientID) throws DALException {
    int totalAmount = 0;
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "SELECT amount FROM resourcebatch WHERE ingredient_ID = ?");
      stmt.setInt(1, ingredientID);
      ResultSet results = stmt.executeQuery();
      while (results.next()) {
        totalAmount += results.getInt("amount");
      }
      return totalAmount;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  private void initHashMap() {
    ingredientMin = new HashMap<Integer, Double>();
    ingredientMin.put(1, 25.0);           //Minimum amount for ingredient with ID=1
    ingredientMin.put(2, 20.0);
    ingredientMin.put(3, 25.0);
    ingredientMin.put(4, 10.0);
    ingredientMin.put(5, 5.0);
    ingredientMin.put(6, 6.0);
    ingredientMin.put(7, 1.3);
    ingredientMin.put(8, 0.5);
    ingredientMin.put(9, 20.0);
    ingredientMin.put(10, 1.0);
    ingredientMin.put(11, 0.02);
    ingredientMin.put(12, 1.0);
    ingredientMin.put(13, 0.5);
    ingredientMin.put(14, 50.0);
    ingredientMin.put(15, 10.0);
    ingredientMin.put(16, 120.0);
  }

  private void updateReorder(IResourceBatchDTO r) throws DALException {
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "UPDATE ingredient SET reorder = ? WHERE ingredient_ID = ?");
      if (getTotalIngredientAmount(r.getIngredientID()) < ingredientMin.get(r.getIngredientID())*2) {
        stmt.setInt(1, 1);
      } else {
        stmt.setInt(1, 0);
      }
      stmt.setInt(2, r.getIngredientID());
      stmt.executeUpdate();
    } catch(SQLException e) {
      throw new DALException(e.getMessage());
    }
  }
}
