<%-- 
    Document   : dados_formulario
    Created on : 25 de set. de 2025, 10:48:03
    Author     : aluno
--%>

<%@page import="aulas.servlet.getpost.InformacaoFormulario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dados do Formulário</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f8f9fa;
                margin: 40px;
                text-align: center;
            }

            h1 {
                color: #343a40;
            }

            table {
                margin: 0 auto;
                border-collapse: collapse;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            }

            th, td {
                padding: 12px 20px;
                border: 1px solid #dee2e6;
                text-align: center;
                white-space: nowrap;
            }

            th {
                background-color: #0d6efd;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f1f3f5;
            }

            tr:hover {
                background-color: #e2e6ea;
            }

            a {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 18px;
                background-color: #0d6efd;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }

            a:hover {
                background-color: #0b5ed7;
            }
        </style>
    </head>
    <body>
        <% ArrayList<InformacaoFormulario> dados = (ArrayList<InformacaoFormulario>) request.getAttribute("dados"); %>

        <h1>Dados do Formulário</h1>

        <table>
            <tr>
                <th>Campo A</th>
                <th>Opção A</th>
                <th>Opção B</th>
            </tr>

            <% if (dados != null) {%>
                <% for (InformacaoFormulario info : dados) {%>
                    <tr>
                        <td><%= info.getCampoA()%></td>
                        <td><%= info.getOpcaoA()%></td>
                        <td><%= info.getOpcaoB()%></td>
                    </tr>
                <% }%>
            <% }%>
        </table>

        <a href="<%= request.getContextPath()%>/aulas/jsp/formulario.jsp">
            Adicionar Informações
        </a>

    </body>
</html>
