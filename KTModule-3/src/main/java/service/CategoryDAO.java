package service;

import SQLUtils.MySQLUtils;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategory {
    public CategoryDAO(){

    }

    Connection connection = MySQLUtils.getConnection();

    @Override
    public List<Category> SelectAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        String SQL_SELECT_CATEGORY = "SELECT id,cat_name FROM category;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String cat_name = rs.getString("cat_name");
                categoryList.add(new Category(cat_name));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return categoryList;
    }

    @Override
    public Category SelectCategoryById(int id) {
        Category category = null;
        String SQL_SELECT_CATEGORY = "SELECT id,cat_name FROM category WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString("cat_name");
                category = new Category(name);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return category;
    }

    @Override
    public int findId(String name) {
        int id = 0;
        String SQL_FINDID = "SELECT id FROM category WHERE cat_name = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FINDID);
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return id;
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
