import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "CalculateServlet", value = "/calculate")
public class CalculateServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Calculator cal = new Calculator();
        int foperand = Integer.parseInt(request.getParameter("f-operand"));
        int soperand = Integer.parseInt(request.getParameter("s-operand"));
        String operate = request.getParameter("operator");

        PrintWriter writer = response.getWriter();
//        writer.println(foperand)
        if(request.getParameter("f-operand")==null || request.getParameter("s-operand")==null ){
            writer.println("<html>");
            writer.println("<h1>404 NOT FOUND</h1>");
            writer.println("</html>");
        }else{
        writer.println("<html>");
        writer.println("<head><title>Calculate</title></head>");
        writer.println("<body>");
        writer.println("<h1>Result:</h1>");
        try{
            int result = cal.calculate(foperand,operate,soperand);
            writer.printf("<p>%d %s %d = %d</p>\n",foperand,operate,soperand,result);

        }catch (Exception e){
            writer.println("Error:"+ e.getMessage());
        }
        writer.println("</body>");
        writer.println("</html>");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
