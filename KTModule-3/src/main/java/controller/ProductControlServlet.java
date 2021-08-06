package controller;

import SQLUtils.CheckTools;
import model.Category;
import model.Product;
import service.CategoryDAO;
import service.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductControlServlet", urlPatterns = "/products")
public class ProductControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    InsertProduct(request, response);
                    break;
                case "edit":
                    UpdateProduct(request, response);
                    break;
                case "delete":
                    DeleteProduct(request,response);
                    break;
                case "search":
                    showProductListSearch(request, response);
                    break;
                default:
                    showProductList(request, response);
                    break;
            }
        } catch (ServletException | SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showFormCreate(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "delete":
                    showFormDelete(request, response);
                    break;
                default:
                    showProductList(request, response);
                    break;
            }
        } catch (ServletException e) {
            throw new ServletException(e);
        }

    }

    public void showProductList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> productList = productDAO.SelectAllProduct();
        List<Category> categoryList = categoryDAO.SelectAllCategory();
        request.setAttribute("products", productList);
        request.setAttribute("categories", categoryList);
        RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
        dis.forward(request, response);
    }

    public void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = categoryDAO.SelectAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dis = request.getRequestDispatcher("create.jsp");
        dis.forward(request, response);
    }

    public void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.SelectProductById(id);
        List<Category> categories = categoryDAO.SelectAllCategory();
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        RequestDispatcher dis = request.getRequestDispatcher("edit.jsp");
        dis.forward(request, response);
    }

    public void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.SelectProductById(id);
        List<Category> categories = categoryDAO.SelectAllCategory();
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        RequestDispatcher dis = request.getRequestDispatcher("delete.jsp");
        dis.forward(request, response);
    }

    public void showProductListSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("search");
        List<Product> productList = productDAO.SelectSearchProduct(name);
        List<Category> categoryList = categoryDAO.SelectAllCategory();
        request.setAttribute("products", productList);
        RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
        dis.forward(request, response);
    }

    public void InsertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String name = request.getParameter("name");
        String str_price = request.getParameter("price");
        String str_quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String str_cat_id = request.getParameter("category");
        Product newProduct = null;
        if (!CheckTools.isNumeric(str_price) || !CheckTools.isNumeric(str_quantity)) {
            request.setAttribute("success", null);
            request.setAttribute("error", "Price or Or Quantityisn't a number!");
            showFormCreate(request, response);
        } else {
            int price = Integer.parseInt(str_price);
            if (price <= 0) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Price required greater than 0 Or Cat_id is available");
                showFormCreate(request, response);
            } else {
                int id = categoryDAO.findId(str_cat_id);
                int quantity = Integer.parseInt(str_quantity);
                newProduct = new Product(name, price, quantity, color, description, id);
                productDAO.InsertProduct(newProduct);
                request.setAttribute("success", "Product was created");
                request.setAttribute("error", null);
                showFormCreate(request, response);
            }
        }
    }

    public void UpdateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String str_price = request.getParameter("price");
        String str_quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String str_cat_id = request.getParameter("category");
        Product EditProduct = productDAO.SelectProductById(id);
        RequestDispatcher dis;
        if (!CheckTools.isNumeric(str_price) || !CheckTools.isNumeric(str_quantity)) {
            request.setAttribute("success", null);
            request.setAttribute("error", "Price isn't a number Or Cat_id isn't a number");
            showFormEdit(request, response);
        } else {
            int price = Integer.parseInt(str_price);
            if (price <= 0) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Price required greater than 0 Or Cat_id is available");
                showFormEdit(request, response);
            } else {
                int idcat = categoryDAO.findId(str_cat_id);
                int quantity = Integer.parseInt(str_quantity);
                EditProduct.setId(id);
                EditProduct.setName(name);
                EditProduct.setPrice(price);
                EditProduct.setQuantity(quantity);
                EditProduct.setColor(color);
                EditProduct.setDescription(description);
                EditProduct.setCat_id(idcat);
                productDAO.isUpdateProduct(EditProduct);
                request.setAttribute("success", "Product was created");
                request.setAttribute("error", null);
                showFormEdit(request, response);
            }
        }
    }

    public void DeleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.SelectProductById(id);
        RequestDispatcher dis;
        if(product == null){
            dis = request.getRequestDispatcher("error.jsp");
        }else{
            productDAO.isDeleteProduct(id);
            request.setAttribute("error",null);
            request.setAttribute("success","Product was deleted");
            List<Product> productList = productDAO.SelectAllProduct();
            request.setAttribute("products",productList);
//        response.sendRedirect("/customersManage");
            showProductList(request,response);
        }

    }
}
