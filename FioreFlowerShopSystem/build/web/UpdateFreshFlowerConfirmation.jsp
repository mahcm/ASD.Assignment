<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Entity.Item"%>
<link href="css/ud.css" rel="stylesheet" type="text/css"/>

<html>
    <head></head>
    <body>
        <%
            Item item = (Item) session.getAttribute("item");
            if (item != null) {
        %>
        <h1>Update Fresh Flower Details</h1>
        <form action="UpdateFreshFlower" method="post" onsubmit="return window.confirm('Are you sure you want to update this fresh flower details?')">
            <table border="0">
                <tr>
                    <td>Fresh Flower Id</td>
                    <td><input type="text" name="id"
                               value="<%=item.getItemId()%>" readonly></td>
                </tr>
                <tr>
                    <td>Fresh Flower Name</td>
                    <td><input type="text" name="name"
                               value="<%=item.getItemName()%>" readonly></td>
                </tr>
                <tr>
                    <td>Fresh Flower Type</td>
                    <td><input type="text" name="type"
                               value="<%=item.getItemType() %>" readonly></td>
                </tr>
                <tr>
                    <td>Fresh Flower Description</td>
                    <td><textarea name="desc" style="width: 250px; height:50px"><%=item.getItemDesc() %></textarea></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity"
                               value="<%=item.getQuantity()%>"></td>
                </tr>
                <tr>
                    <td>Price (RM)</td>
                    <td><input type="text" name="price"
                               value="<%=String.format("%.2f", item.getDefaultPrice())%>"></td>
                </tr>
            </table>
            <input type="submit" value="Update Event">
            <a href ="DisplayFreshFlowerList.jsp"><input type="button" value="Back"></a>

        </form>
        <%} else {%>
        Item ${item.name} not found
        <p><a href="ProductMenu.jsp">Back to home page</a></p>
        <%}%>
    </body>
</html>