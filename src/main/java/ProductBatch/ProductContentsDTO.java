package ProductBatch;

public class ProductContentsDTO implements IProductContentsDTO {

    private int productBatchID;
    private int resourceBatchID;

    public ProductContentsDTO() {
        // Empty for now
    }

    @Override
    public int getProductBatch() {
        return productBatchID;
    }

    @Override
    public void setProductBatch(int batchID) {
        productBatchID = batchID;
    }

    @Override
    public int getResourceBatch() {
        return resourceBatchID;
    }

    @Override
    public void setResourceBatch(int batchID) {
        resourceBatchID = batchID;
    }
}
