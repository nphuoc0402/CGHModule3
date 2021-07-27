package service;

import model.Customer;
import utils.MySQLConnUtils;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICusstomerDAO{

    private final String INSERT_CUSTOMER_SQL = "INSERT INTO customer" + "(name,email,salary) VALUES" + "(?,?,?)";
    private final String SELECT_CUSTOMER_BY_ID = "SELECT id,name,email,salary FROM customer WHERE id=?";
    private final String SELECT_ALL_CUSTOMER  = "SELECT id,name,email,salary FROM customer";
    private final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE id = ?;";
    private final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET name = ?, email = ?, salary = ? WHERE id = ?;";

    public CustomerDAO(){

    }

    Connection connection = MySQLConnUtils.getConnection();

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        System.out.println(INSERT_CUSTOMER_SQL);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setInt(3,customer.getSalary());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Customer selectCustomerById(int id) {
        Customer customer = null;
        try(

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery() ;

        while (rs.next()){
            String name = rs.getString("name");
            String email= rs.getString("email");
            int salary = rs.getInt("salary");
            customer = new Customer(name,email,salary);
        }
        }catch(SQLException e){
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt ("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int salary = rs.getInt("salary");
                customers.add(new Customer(id,name,email,salary));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return customers;
    }

    @Override
    public boolean isDeleteCustomer(int id) throws SQLException {
        boolean rowDeleted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL);
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate()  > 0;
        }catch (SQLException e){
            printSQLException(e);
        }
        return rowDeleted;
    }


    @Override
    public boolean isUpdateCustomer(Customer customer) throws SQLException {
        boolean rowUpdate;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setInt(3, customer.getSalary());
            preparedStatement.setInt(4, customer.getId());

            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return  rowUpdate;
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
