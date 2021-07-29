package controller;

import model.Customer;
import service.CustomerDAO;
import utils.CheckTools;

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
                    break;
                case "edit":
                    updateCustomer(request, response);
                    break;
                case "sent":
                    transfer(request,response);
//                    transferUsingSP(request,response);
                    break;
                default:
                    insertCustomer(request, response);
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
//                    showCreateCustomer(request,response);
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
        RequestDispatcher dis = request.getRequestDispatcher("transfer.jsp");
        dis.forward(request,response);
    }

    public void insertCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sal = request.getParameter("salary");
        if (name == ""  || email == "" || sal == "") {
            request.setAttribute("success", null);
            request.setAttribute("error", "All field is required");
            listCustomer(request,response);
        } else {
            if (!CheckTools.isNumeric(sal) || !CheckTools.isEmail(email)) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Invalid transfer information");
                listCustomer(request,response);
            }
            else {
                int salary = Integer.parseInt(sal);
                if (salary < 0){
                    request.setAttribute("success", null);
                    request.setAttribute("error", "Salary require greater than 0");
                    listCustomer(request,response);
                } else {
                    Customer newCustomer = new Customer(name, email, salary);
                    customerDAO.insertCustomer(newCustomer);
                    request.setAttribute("success", "Customer information was created");
                    request.setAttribute("error", null);
                    listCustomer(request,response);
                }
            }

        }
//        response.sendRedirect("/customersManage");
//        RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
//        dis.forward(request,response);
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sal = request.getParameter("salary");
        Customer editCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if (name == ""  || email == "" || sal == ""){
            request.setAttribute("success", null);
            request.setAttribute("error", "All field is required");
            showEditCustomer(request, response);
        }else {
            if (!CheckTools.isNumeric(sal) && !CheckTools.isNumeric(email))  {
                request.setAttribute("success", null);
                request.setAttribute("error", "Invalid transfer information");
                showEditCustomer(request, response);
            }
            else {
                int salary = Integer.parseInt(sal);
                if (salary <= 0 || salary > 99999 ){
                    request.setAttribute("success", null);
                    request.setAttribute("error", "Salary require greater than 0 and less than 100000");
                    showEditCustomer(request, response);
                } else {
                    if( editCustomer == null){
                        dis = request.getRequestDispatcher("error-404.jsp");
                    }else{
                        editCustomer.setId(id);
                        editCustomer.setName(name);
                        editCustomer.setEmail(email);
                        editCustomer.setSalary(salary);

                        request.setAttribute("error",null);
                        request.setAttribute("success","Customer was edit success");
                        customerDAO.Update(editCustomer);
//        response.sendRedirect("/customersManage");
                        showEditCustomer(request, response);
                    }
                }
            }
        }
    }

    public void transfer(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException, ServletException{
      int id = Integer.parseInt(request.getParameter("id"));
      String id2 = request.getParameter("id2");
      String sal = request.getParameter("salary");
        Customer Customersent = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if(!CheckTools.isNumeric(sal) || !CheckTools.isNumeric(id2)){
            request.setAttribute("error","Transfer isn't successful");
            request.setAttribute("success",null);
            showSentSalry(request, response);
        }else{
            int idReceive = Integer.parseInt(id2);
            int salaryreceive = Integer.parseInt(sal);
            Customer Customerreceive = customerDAO.selectCustomerById(idReceive);
            if(salaryreceive > Customersent.getSalary() || salaryreceive < 0 || Customerreceive == null){
                request.setAttribute("error","Transfer isn't successful");
                request.setAttribute("success",null);
                showSentSalry(request, response);
            }else {
                customerDAO.isUpdateCustomer(Customersent,Customerreceive,salaryreceive);
                request.setAttribute("success","TranSfer Successful");
                request.setAttribute("error",null);
//                dis = request.getRequestDispatcher("transfer.jsp");
//                dis.forward(request,response);
                showSentSalry(request, response);
            }
        }

//       response.sendRedirect("/customersManage");

    }

    public void transferUsingSP(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        int salary = Integer.parseInt(request.getParameter("salarysent"));
        int id2 = Integer.parseInt(request.getParameter("id2"));
        int salaryreceive = Integer.parseInt(request.getParameter("salary"));

        request.setAttribute("success","Transfer Success!");
        request.setAttribute("error",null);
        customerDAO.spUpdate(id,id2,salaryreceive);
//        response.sendRedirect("/customersManage");
        listCustomer(request,response);

    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if(customer == null){
            dis = request.getRequestDispatcher("error-404.jsp");
        }else{
        customerDAO.isDeleteCustomer(id);
            request.setAttribute("error",null);
            request.setAttribute("success","Customer was deleted");
        List<Customer> customerList = customerDAO.selectAllCustomer();
        request.setAttribute("customers",customerList);
//        response.sendRedirect("/customersManage");
        listCustomer(request,response);
        }
    }
}
