package ProductBatch;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static Utilities.ConnectionHandler.createConnection;

public class ProductBatchDAO implements IProductBatchDAO{

    @Override
    public List<IProductBatchDTO> getAllProductBatches() throws DALException {
        List<IProductBatchDTO> batchList = new ArrayList<>();
        try(Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM productbatch");
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                IProductBatchDTO pb = new ProductBatchDTO();
                pb.setBatchID(results.getInt("p_batch_ID"));
                pb.setRecipeID(results.getInt("recipeID"));
                pb.setBatchAmount(results.getInt("amount"));
                batchList.add(pb);
            }

            for (IProductBatchDTO i : batchList) {
                stmt = c.prepareStatement("SELECT * FROM ordered_product WHERE p_batch_ID = ?");
                stmt.setInt(1, i.getBatchID());
                results = stmt.executeQuery();
                if(results.next()){
                  i.setBatchStatus("Ordered");
                  i.setOrderDate(results.getTimestamp("date_ordered"));
                }
                else{
                  stmt = c.prepareStatement("SELECT * FROM progressing_product WHERE p_batch_ID = ?");
                  stmt.setInt(1, i.getBatchID());
                  results = stmt.executeQuery();
                  if(results.next()){
                      i.setBatchStatus("Progressing");
                      i.setOrderDate(results.getTimestamp("date_ordered"));
                      i.setBeginDate(results.getTimestamp("date_begun"));
                  }
                  else{
                    stmt = c.prepareStatement("SELECT * FROM finished_product WHERE p_batch_ID = ?");
                    stmt.setInt(1, i.getBatchID());
                    results = stmt.executeQuery();
                    if(results.next()){
                        i.setBatchStatus("Finished");
                        i.setOrderDate(results.getTimestamp("date_ordered"));
                        i.setBeginDate(results.getTimestamp("date_begun"));
                        i.setDoneDate(results.getTimestamp("date_finished"));
                    }
                    else{
                        i.setBatchStatus("Not Found");
                    }
                  }
                }
            }

        }catch(SQLException e) {
            throw new DALException(e.getMessage());
        }
        return batchList;
    }

    @Override
    public IProductBatchDTO getProductBatches(int productBacthID) throws DALException {
      IProductBatchDTO pb = new ProductBatchDTO();
      try(Connection c = createConnection()) {
          PreparedStatement stmt = c.prepareStatement("SELECT * FROM productbatch");
          ResultSet results = stmt.executeQuery();
          if(results.next()){
              pb.setBatchID(results.getInt("p_batch_ID"));
              pb.setRecipeID(results.getInt("recipeID"));
              pb.setBatchAmount(results.getInt("amount"));

              stmt = c.prepareStatement("SELECT * FROM ordered_product WHERE p_batch_ID = ?");
              stmt.setInt(1, pb.getBatchID());
              results = stmt.executeQuery();
              if(results.next()){
                pb.setBatchStatus("Ordered");
                pb.setOrderDate(results.getTimestamp("date_ordered"));
              }
              else{
                stmt = c.prepareStatement("SELECT * FROM progressing_product WHERE p_batch_ID = ?");
                stmt.setInt(1, pb.getBatchID());
                results = stmt.executeQuery();
                if(results.next()){
                    pb.setBatchStatus("Progressing");
                    pb.setOrderDate(results.getTimestamp("date_ordered"));
                    pb.setBeginDate(results.getTimestamp("date_begun"));
                }
                else{
                  stmt = c.prepareStatement("SELECT * FROM finished_product WHERE p_batch_ID = ?");
                  stmt.setInt(1, pb.getBatchID());
                  results = stmt.executeQuery();
                  if(results.next()){
                      pb.setBatchStatus("Finished");
                      pb.setOrderDate(results.getTimestamp("date_ordered"));
                      pb.setBeginDate(results.getTimestamp("date_begun"));
                      pb.setDoneDate(results.getTimestamp("date_finished"));
                  }
                  else{
                      pb.setBatchStatus("Not Found");
                  }
                }
              }
          }

      }catch(SQLException e) {
          throw new DALException(e.getMessage());
      }
      return pb;
    }


}
