package service;

import SQLUtils.MySQLUtils;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO implements IProductDAO{
    public ProductDAO(){

    }

    Connection connection = MySQLUtils.getConnection();

    @Override
    public void InsertProduct(Product product) throws SQLException {
        String SQL_INSERT_PRODUCT = "INSERT INTO product (name,price,quantity,color,description, cat_id) VALUES (?,?,?,?,?,?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCat_id());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Product SelectProductById(int id) {
        String SQL_SELECTPRODUCT_BYID = "SELECT id, name, quantity, price, color, description, cat_id FROM product WHERE id = ?;";
        Product product = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECTPRODUCT_BYID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int quantity = rs.getInt(4);
                String color = rs.getString(5);
                String description = rs.getString(6);
                int  cat_id = rs.getInt(7);
                product = new Product(id,name,quantity,price,color,description,cat_id);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return product;
    }

    @Override
    public boolean isUpdateProduct(Product product) throws SQLException {
        boolean update = false;
        String SQL_UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ?, quantity = ?, color = ?, description = ?, cat_id = ? WHERE id = ?;";
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCat_id());
            preparedStatement.setInt(7,product.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            update = true;
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return  update;
    }

    @Override
    public boolean isDeleteProduct(int id) throws SQLException {
        boolean delete = false;
        String SQL_DELETE_PRODUCT = "DELETE FROM product WHERE id = ?;";
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            delete = true;
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return delete;
    }

    @Override
    public List<Product> SelectAllProduct() {
        List<Product> productList = new ArrayList<>();
        String SQL_SELECTALL_PRODUCT = "SELECT p.id, p.name, p.price, p.quantity, p.color, p.description, p.cat_id FROM product p;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECTALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("p.id");
                String name =rs.getString("p.name");
                int price = rs.getInt("p.price");
                int quantity = rs.getInt("p.quantity");
                String color = rs.getString("color");
                String description = rs.getString ("p.description");
                int cat_id = rs.getInt("p.cat_id");
                productList.add(new Product(id,name,price,quantity,color,description,cat_id));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return productList;
    }

    @Override
    public List<Product> SelectSearchProduct(String  nameSearch) {
        List<Product> productList = new ArrayList<>();
        String SQL_SEARCH_PRODUCT = "SELECT p.id, p.name, p.price, p.quantity, p.color, p.description, p.cat_id FROM product p WHERE p.name LIKE ? ;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SEARCH_PRODUCT);
            preparedStatement.setString(1,"%" +  nameSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("p.id");
                String name =rs.getString("p.name");
                int price = rs.getInt("p.price");
                int quantity = rs.getInt("p.quantity");
                String color = rs.getString("color");
                String description = rs.getString ("p.description");
                int cat_id = rs.getInt("p.cat_id");
                productList.add(new Product(id,name,price,quantity,color,description,cat_id));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return productList;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
