package service;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public void InsertProduct(Product product)throws SQLException;
    public Product SelectProductById(int id);
    public boolean isUpdateProduct(Product product) throws SQLException;
    public boolean isDeleteProduct(int id) throws SQLException;
    public List<Product> SelectAllProduct();
    public List<Product> SelectSearchProduct(String nameSearch);
}
