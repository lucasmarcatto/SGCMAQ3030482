<%-- 
    Document   : formulario
    Created on : 25 de set. de 2025, 10:24:13
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        h1 {
            text-align: center;
            color: #343a40;
            margin-bottom: 20px;
        }

        form label {
            display: block;
            margin-bottom: 6px;
            font-weight: 500;
            color: #495057;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        .checkbox-group {
            margin-bottom: 15px;
        }

        .checkbox-group input[type="checkbox"] {
            margin-right: 8px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #0d6efd;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0b5ed7;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Formulário</h1>
        <form action="<%= request.getContextPath()%>/aulas/jsp/formulariojspservlet" method="post">
            
            <label for="campoA">Campo A:</label>
            <input type="text" id="campoA" name="campoA" pattern="\d+" title="Apenas números" required>

            <div class="checkbox-group">
                <input type="checkbox" id="opcaoA" name="opcaoA" value="opcaoA"/>
                <label for="opcaoA">Opção A</label>
            </div>

            <div class="checkbox-group">
                <input type="checkbox" id="opcaoB" name="opcaoB" value="opcaoB"/>
                <label for="opcaoB">Opção B</label>
            </div>

            <input type="submit" name="btEnviar" value="Salvar"/>
        </form>
    </div>
</body>
</html>
