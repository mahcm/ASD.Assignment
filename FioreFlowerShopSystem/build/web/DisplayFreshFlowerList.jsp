<%@page import="java.util.List"%>
<%@page import="Entity.Item" %>
<link href="css/list.css" rel="stylesheet" type="text/css"/>

<%
  List<Item> itemList = (List)session.getAttribute("itemList");
%>
<h1>Display the Fresh Flower Details</h1>
<table  cellspacing="8" class="event">
    <form action="SearchFreshFlower.jsp">
        <tr>
        <th>ID</th>
        <th>Fresh Flower Name</th>
        <th>Fresh Flower Type</th>
        <th>Fresh Flower Description</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>
    <% for (Item item: itemList){ %>
        <tr>
            <td><%= item.getItemId() %></td>
            <td><%= item.getItemName() %> </td>
            <td><%= item.getItemType() %></td>
            <td><%= item.getItemDesc() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getDefaultPrice() %></td>
            
        </tr>
    <% } %>
</table>
<h1>Search Fresh Flower Id </h1>
<table border="0" cellspacing="10">
    <tr>
        <td>Fresh Flower Name</td>
        <td><select name="id">
                <% for (Item item: itemList){%>
                <option value="<%=item.getItemId()%>">
                    <%=item.getItemId()%>
                <%}%>
            </select>
            <br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Delete" name="button"/>
            <input type="submit" value="Update" name="button"/>
            <a href="ProductMenu.jsp"><input type="button" value="Back" name="button"/></a>
        </td>
    </tr>
</form>
</table>
</html>
