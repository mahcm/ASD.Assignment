<%@page import="java.util.List"%>
<%@page import="Entity.Item"%>

<!-- retrieve session object, itemList -->
<%
  List<Item> itemList = (List)session.getAttribute("itemList");
%>
<link href="css/view.css" rel="stylesheet" type="text/css"/>
<h1>View Fresh Flower Details</h1>

<!-- Display items -->
<table border="1">
    <tr>
        <th>Fresh Flower Name</th>
        <th>Fresh Flower Type</th>
        <th>Fresh Flower Description</th>
        <th>Quantity</th>
        <th>Price</th>
        
    </tr>
    <% for (Item item: itemList){ %>
        <tr>
            <td><%= item.getItemName() %> </td>
            <td><%= item.getItemType() %></td>
            <td><%= item.getItemDesc() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getDefaultPrice() %></td>
            
        </tr>
    <% } %>
</table>
<br><br>
<a href="ProductMenu.jsp"><input type="button" value="Back"></a>