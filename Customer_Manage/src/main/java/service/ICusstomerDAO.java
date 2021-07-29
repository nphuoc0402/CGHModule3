package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICusstomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;
    public Customer selectCustomerById(int id);
    public Customer selectSalaryById(int id);
    public List<Customer> selectAllCustomer();
    public boolean isDeleteCustomer(int id ) throws SQLException;
    public boolean isUpdateCustomer(Customer customer, Customer customer2, int balance) throws SQLException;
    public boolean UpdateSalary(Customer customer) throws SQLException;
    public boolean Update(Customer customer) throws SQLException;
    public boolean spUpdate(int salaryNew,int senderId, int reciptentId)throws SQLException;


}
