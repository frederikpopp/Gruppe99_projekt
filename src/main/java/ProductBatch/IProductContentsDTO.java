package ProductBatch;

public interface IProductContentsDTO {

    public int getProductBatch();

    public void setProductBatch(int batchID);

    public int getResourceBatch();

    public void setResourceBatch(int batchID);

    public double getAmount();

    public void setAmount(double amount);
}
