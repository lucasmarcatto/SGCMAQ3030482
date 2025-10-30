<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Usuario</title>
    </head>
    <body>
        
        <%@include file="/home/app/modulos.jsp" %>
        
        <%
            ArrayList<TipoUsuario> dados = new TipoUsuario().getAllTableEntities();
        %>
        <h1>Tipo Usuario</h1>
        <table border="1">

            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Administrativo</th>
                <th>Agendamento</th>
                <th>Atendimento</th>
                <th></th>
                <th></th>
            </tr>

            <%for (TipoUsuario tp : dados) {%>

            <tr>
                <td><%= tp.getId()%></td>
                <td><%= tp.getNome()%></td>
                <td><%= tp.getModuloAdministrativo()%></td>
                <td><%= tp.getModuloAgendamento()%></td>
                <td><%= tp.getModuloAtendimento()%></td>
                
                <td><a href="<%= request.getContextPath()%>/home/app/tipousuario_form.jsp?action=update&id=<%= tp.getId()%>">Alterar</a></td>
                <td><a href="<%= request.getContextPath()%>/home?action=delete&id=<%= tp.getId()%>&task=tipousuario" onclick="return confirm('Deseja realmente excluir Tipo Usuario <%= tp.getId() %> (<%= tp.getNome() %>) ?')">Excluir</a></td>
            </tr>

            <%}%>


        </table> <br><br>

        <a href="<%= request.getContextPath()%>/home/app/tipousuario_form.jsp?action=create">Adicionar</a>

    </body>
</html>
