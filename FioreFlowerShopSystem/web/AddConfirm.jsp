<%@page import="Entity.Item"%>
<h3>
    <%
        boolean success = (Boolean) session.getAttribute("success");
        Item item = (Item)session.getAttribute("item");
        String name = item.getItemName();
        if (success)
            out.println("The fresh flower: "+name+". Page will be returned to previous page.");
        else
            out.println("Error: Unable to add new fresh flower.");
    %>
</h3>
<script>
    setTimeout(function () {
        document.location = "AddFreshFlower.jsp";
    }, 3000); // <-- this is the delay in milliseconds
</script>

<p><a href="index.html">Back to home page</a></p>