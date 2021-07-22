import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CaculatorDiscountServlet", value = "/display-discount")
public class CaculatorDiscountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  description = request.getParameter("decription");
        float   price = Float.parseFloat(request.getParameter("price"));
        float   percent = Float.parseFloat(request.getParameter("discount"));
        float   Discount_Amount = price * percent * 0.01f;
        float   Discount_Price = price - Discount_Amount;

        request.setAttribute("description",description);
        request.setAttribute("price",price);
        request.setAttribute("percent",percent);
        request.setAttribute("discount",Discount_Amount);
        request.setAttribute("discountPrice",Discount_Price);

        RequestDispatcher dis;
        dis = request.getRequestDispatcher("display-discount.jsp");
        dis.forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
