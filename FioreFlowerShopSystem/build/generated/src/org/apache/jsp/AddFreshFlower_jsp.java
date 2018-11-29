package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;

public final class AddFreshFlower_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

            int countr = 0;
            try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/FioreFlowerShopSystem", "nbuser", "nbuser");
            Statement st = con.createStatement();
            String strQuery = "SELECT COUNT (*) FROM ITEM";
            ResultSet rs = st.executeQuery(strQuery);
            String Countrow = "";
            
            while (rs.next()) {
            Countrow = rs.getString(1);
            countr = Integer.parseInt(Countrow);
            }
            }catch (Exception e){
            e.printStackTrace();
            }
            
            int total = 100;
            countr += 1;
            total += countr;
        
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>ADD FRESH FLOWER DETAILS</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>ADD NEW FRESH FLOWER DETAILS</h1>\n");
      out.write("        <form action=\"AddFreshFlower\">\n");
      out.write("            <table border=\"0\" class=\"partRegistertable\">\n");
      out.write("                <tr>\n");
      out.write("                    <td>FRESH FLOWER ID</td>\n");
      out.write("                    <td><input type=\"text\" value=\"P");
      out.print(+total);
      out.write("\" name=\"id\" required ></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>FRESH FLOWER NAME</td>\n");
      out.write("                    <td><input type=\"text\" name=\"name\" placeholder=\"Aconite\" required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>FRESH FLOWER TYPE</td>\n");
      out.write("                    <td><input type=\"text\" name=\"type\" value=\"FRESH FLOWER\" readonly=\"readonly\" required blocked=\"blocked\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>FRESH FLOWER DESCRIPTION</td>\n");
      out.write("                    <td><textarea name=\"description\" required=\"required\" style=\"width: 250px; height:50px \"></textarea></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>QUANTITY</td>\n");
      out.write("                    <td><input type=\"number\" name=\"quantity\" value = '1' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>PRICE</td>\n");
      out.write("                    <td><input type=\"text\" name=\"price\" placeholder=\"10.00\" required></td>\n");
      out.write("                </tr>\n");
      out.write("                \n");
      out.write("                       \n");
      out.write("            </table>\n");
      out.write("            \n");
      out.write("                 <input type=\"submit\" value=\"ADD\">\n");
      out.write("                 <input type=\"reset\" value=\"RESET\">\n");
      out.write("                 <a href=\"ProductMenu.jsp\"><input type=\"button\" value=\"BACK\"></a>\n");
      out.write("            \n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
