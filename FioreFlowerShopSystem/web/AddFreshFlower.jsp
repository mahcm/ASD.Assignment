<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
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
        %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD FRESH FLOWER DETAILS</title>
    </head>
    <body>
        <h1>ADD NEW FRESH FLOWER DETAILS</h1>
        <form action="AddFreshFlower">
            <table border="0" class="partRegistertable">
                <tr>
                    <td>FRESH FLOWER ID</td>
                    <td><input type="text" value="P<%=+total%>" name="id" required ></td>
                </tr>
                <tr>
                    <td>FRESH FLOWER NAME</td>
                    <td><input type="text" name="name" placeholder="Aconite" required></td>
                </tr>
                <tr>
                    <td>FRESH FLOWER TYPE</td>
                    <td><input type="text" name="type" value="FRESH FLOWER" readonly="readonly" required blocked="blocked"></td>
                </tr>
                <tr>
                    <td>FRESH FLOWER DESCRIPTION</td>
                    <td><textarea name="description" required="required" style="width: 250px; height:50px "></textarea></td>
                </tr>
                <tr>
                    <td>QUANTITY</td>
                    <td><input type="number" name="quantity" value = '1' required></td>
                </tr>
                <tr>
                    <td>PRICE</td>
                    <td><input type="text" name="price" placeholder="10.00" required></td>
                </tr>
                
                       
            </table>
            
                 <input type="submit" value="ADD">
                 <input type="reset" value="RESET">
                 <a href="ProductMenu.jsp"><input type="button" value="BACK"></a>
            
        </form>
    </body>
</html>
