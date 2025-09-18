package aulas.servlet.ciclo;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebServlet(name = "Ciclo", urlPatterns = {"/aulas/servlet/ciclo"})
public class Ciclo extends HttpServlet {

    @Override
    public void init() throws ServletException {
        /*
        Inicialização dos recursos
        
        Criar/Atribuir as variáveis globais (como a conexão a banco dde dados)
        */
        
        super.init(); 
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Recebimento de requisições HTTP enquanto servlet estiver ativo.
        
        super.service(...); encaminha as requisições aos métodos HTTP implementado
        */
        
//        super.service(req, resp); 
        
        System.out.println("\n--- HaderNames Resquest ---");
        Enumeration<String> headerNames = request.getHeaderNames();
        while( headerNames.hasMoreElements() ) {
            String name = headerNames.nextElement();
            System.out.println(name + " : " + request.getHeader(name));
        }
        System.out.println("--- HaderNames Resquest ---\n");
        
        
        String html = "<!DOCTYPE html>";
        
        html += "<html>";
        html += "<head>";
        html += "<title>Servlet Ciclo de Vida</title>";
        html += "</head>";
        html += "<body>";
        html += "<h1>Ciclo de vida de um Servlet</h1>";
        html += "</body>";
        html += "</html>";
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter pw = response.getWriter();
        pw.write(html);
        pw.close();
        
        System.out.println("\n--- HaderNames Response ---");
        for (String name : response.getHeaderNames() ){
            System.out.println(name + " : " + response.getHeader(name));
        }
        
        System.out.println("--- HaderNames Response ---\n");
    }
    
    @Override
    public void destroy() {
        /*
        Encerramento de Servlet, liberação dos recursos.
        
        Chamado pelo SeervletContainer.
        */
        
        super.destroy();
    }

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            html += "<!DOCTYPE html>");
//            html += "<html>");
//            html += "<head>");
//            html += "<title>Servlet Ciclo</title>");
//            html += "</head>");
//            html += "<body>");
//            html += "<h1>Servlet Ciclo at " + request.getContextPath() + "</h1>");
//            html += "</body>");
//            html += "</html>");
//        }
//    }

      

}
