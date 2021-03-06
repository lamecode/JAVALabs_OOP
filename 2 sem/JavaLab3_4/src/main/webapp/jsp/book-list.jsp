<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.JavaLab4.model.Book" %>
<%@ page import="com.example.JavaLab4.model.user.User" %>
<%@ page import="com.example.JavaLab4.model.user.UserType" %>
<%@ page import="com.example.JavaLab4.servlets.*" %><%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 06.01.2021
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<script>
    function getVal(param) {
        console.log(param.getAttribute("value"));
    }
    function clearDelete() {
        document.getElementById("delete").value = "";
    }
    function clearEdit() {
        document.getElementById("edit").value = "";
    }
</script>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    ArrayList<Book> booklist = (ArrayList<Book>) request.getAttribute("booklist");
    user = (User) request.getSession(false).getAttribute("user");
    if(booklist != null && booklist.size() > 0) {
%>
<%
    int j;
    for (int i = 0; i < booklist.size(); i = i + 3) {
%>
<div class="Row">
<%    j = i;
        for (; j == i || j > 0 && j % 3 != 0; j++) {
            if (j >= booklist.size()) {
                break;
            }
            if (booklist.get(j).getAmount() == 0) {
                continue;
            }%>
            <div class="Column" style="text-align: center; padding: 70px" >
            <div>Назва: <%=booklist.get(j).getName()%></div>
            <div>Автор: <%=booklist.get(j).getAuthor()%></div>
            <div>Видання: <%=booklist.get(j).getEdition()%></div>
            <div>Рік видання: <%=booklist.get(j).getDate().toString()%></div>
                <%
                    if (user != null && user.getRole() == UserType.READER) {%>
                <form>
                    <button onclick="getVal(document.getElementById(<%=booklist.get(j).getId()%>))">Замовити</button>
                </form>
                <% } else if (user != null && user.getRole() == UserType.ADMIN) { %>
               <form action="adminBooks" method="post">
                   <input type="hidden" id="edit" name="edit" value=<%=booklist.get(j).getId()%> />
                   <input type="button" name="bt" value="Редагувати" onclick="{
                       clearDelete();this.form.submit()}"/>
                   <input type="hidden" id="delete" name="delete" value=<%=booklist.get(j).getId()%> />
                   <input type="button" name="bt" value="Видалити" onclick="clearEdit();this.form.submit()"/>
               </form>
                <% } %>
            </div>
        <%        } %>
        </div>
<%

        }

    }else{

%>
<tr>

    <td> Записів не знайдено. </td>

</tr>

<%}%>
