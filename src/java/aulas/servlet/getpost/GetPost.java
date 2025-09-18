package aulas.servlet.getpost;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetPost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String campoA = request.getParameter("campoA"); 
        String opcaoA = request.getParameter("opcaoA");
        String opcaoB = request.getParameter("opcaoB");
        
        System.out.println("Campo A: " + campoA);
        System.out.println("Opção A: " + opcaoA);
        System.out.println("Opção B: " + opcaoB);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
