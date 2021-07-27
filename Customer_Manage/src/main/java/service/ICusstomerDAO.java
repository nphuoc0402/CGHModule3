package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICusstomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;
    public Customer selectCustomerById(int id);
    public List<Customer> selectAllCustomer();
    public boolean isDeleteCustomer(int id ) throws SQLException;
    public boolean isUpdateCustomer(Customer customer) throws SQLException;

}
