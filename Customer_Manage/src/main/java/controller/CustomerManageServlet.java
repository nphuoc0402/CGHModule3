package controller;

import model.Customer;
import service.CustomerDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@WebServlet(name = "CustomerManageServlet", urlPatterns = "/customersManage")
public class CustomerManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init(){
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertCustomer(request, response);
                    break;
                case "edit":
                    updateCustomer(request, response);
                    break;
                case "sent":
                    sentSalary(request,response);
                    break;
                default:
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        try{
            switch (action){
                case "create":
                    showCreateCustomer(request,response);
                    break;
                case "edit":
                    showEditCustomer(request,response);
                    break;
                case "sent":
                    showSentSalry(request,response);
                    break;
                case "delete":
                    deleteCustomer(request,response);
                    break;
                default:
                    listCustomer(request,response);
                    break;
            }
        }catch (SQLException e){
            throw  new ServletException(e);
        }
    }

    public void listCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        List<Customer> customers = customerDAO.selectAllCustomer();
        System.out.println("customers: " + customers);
        request.setAttribute("customers",customers);
        RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
        dis.forward(request,response);
    }

    public void  showCreateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("create.jsp");
        dis.forward(request,response);
    }

    public void showEditCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("customers",existingCustomer);
        dis.forward(request,response);
    }

    public void showSentSalry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        request.setAttribute("customer",existingCustomer);
        RequestDispatcher dis = request.getRequestDispatcher("sent.jsp");
        dis.forward(request,response);
    }

    public void insertCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int salary = Integer.parseInt(request.getParameter("salary"));
        Customer newCustomer = new Customer(name,email,salary);
        request.setAttribute("message", "Customer information was updated");
        customerDAO.insertCustomer(newCustomer);
//        listCustomer(request,response);
        response.sendRedirect("/customersManage");
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int salary = Integer.parseInt(request.getParameter("salary"));

        Customer editCustomer = customerDAO.selectCustomerById(id);
        editCustomer.setId(id);
        editCustomer.setName(name);
        editCustomer.setEmail(email);
        editCustomer.setSalary(salary);
        request.setAttribute("message","New customer was edit");
//        customerDAO.isUpdateCustomer(editCustomer);
        response.sendRedirect("/customersManage");
    }

    public void sentSalary(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException, ServletException{
      int id = Integer.parseInt(request.getParameter("id"));
      int salary = Integer.parseInt(request.getParameter("salarysent"));
      int id2 = Integer.parseInt(request.getParameter("id2"));
      int salaryreceive = Integer.parseInt(request.getParameter("salary"));
        Customer Customersent = customerDAO.selectCustomerById(id);
        Customer Customerreceive = customerDAO.selectCustomerById(id2);
        Customersent.setSalary(salary - salaryreceive);

        Customerreceive.setSalary(Customerreceive.getSalary() + salaryreceive);
        customerDAO.isUpdateCustomer(Customersent,Customerreceive);
       response.sendRedirect("/customersManage");

    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.isDeleteCustomer(id);

        List<Customer> customerList = customerDAO.selectAllCustomer();
        request.setAttribute("customers",customerList);
        response.sendRedirect("/customersManage");
    }
}
