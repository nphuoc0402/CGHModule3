package com.codegym;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "Servlet", value= "/result")
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String nameUser = request.getParameter("username");
        String password = request.getParameter("password");

        String result;;
        PrintWriter writer = response.getWriter();
//        writer.printf("<html>");
        if("admin".equalsIgnoreCase(nameUser) && "admin".equalsIgnoreCase(password)){
//            writer.printf("<h1>Welcome %s to Website</h1>",nameUser);
            result = "Welcome " + nameUser + "to Website";
        }else{
            result = "Login Error";
        }
        request.setAttribute("result",result);
        RequestDispatcher dis;
        dis = request.getRequestDispatcher("result.jsp");
        dis.forward(request,response);
//        writer.printf("</html>");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
