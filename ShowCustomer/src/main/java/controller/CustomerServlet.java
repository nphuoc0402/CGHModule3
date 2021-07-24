package controller;

import data.CustomerDB;
import model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customerInformation")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersioUID = 1L;

    public CustomerServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> list = CustomerDB.queryCustomer();

//        PrintWriter writer = response.getWriter();
//        for(int i = 0; i < list.size();i++){
//            writer.println(list.get(i));
//        }
         request.setAttribute("customer", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("customer.jsp");

        // Forward (Chuyển tiếp) yêu cầu, để hiển thị dữ liệu trên trang JSP.
        dispatcher.forward(request, response);
    }
}
