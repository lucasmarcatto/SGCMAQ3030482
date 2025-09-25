package aulas.servlet.getpost;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetPost extends HttpServlet {
    
    ArrayList<InformacaoFormulario> dados;
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        dados = new ArrayList<>();
    }        
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String campoA = request.getParameter("campoA"); 
        String opcaoA = request.getParameter("opcaoA");
        String opcaoB = request.getParameter("opcaoB");
        
        System.out.println("Campo A: " + campoA);
        System.out.println("Opção A: " + opcaoA);
        System.out.println("Opção B: " + opcaoB);
        
        if( campoA != null ) {
            InformacaoFormulario info = new InformacaoFormulario();
            info.setCampoA(campoA);
            info.setOpcaoA(opcaoA);
            info.setOpcaoB(opcaoB);
            
            dados.add(info);
        }
        
//        response.sendRedirect("/SGCMAQ3030482/aulas/servlet/getpost/getpost_form.html");
//        response.sendRedirect("www.palmeiras.com");

        //Vai gerar uma nova requisição HTTP vinda do cliente (novos cabeçalhos de requisição e resposta
        response.setContentType("text/html;charset=UTF-8");
        
        String html = "<!DOCTYPE html>";        
        html += "<html>";
        html += "<head>";
        html += "<title>Dados do Formulário</title>";
        html += "</head>";
        html += "<body>";
        html += "<h1>Dados do Formulário</h1>";
        html += "</body>";
        html += "</html>";
        
        html += "<table>";   
        
        html += "<tr>";        
        html += "<th>Campo A</th>";
        html += "<th>Opção A</th>";
        html += "<th>Opção B</th>";        
        html += "</tr>";    
        
        for( InformacaoFormulario info : dados ) {
            html += "<tr>";        
            html += "<td>" + info.getCampoA() + "</td>";       
            html += "<td>" + info.getOpcaoA()+ "</td>";       
            html += "<td>" + info.getOpcaoB()+ "</td>";       
            html += "</tr>"; 
        }
        
        html += "</table></br></br>";
        
        html += "<a href=\"/SGCMAQ3030482/aulas/servlet/getpost/getpost_form.html\">Adicionar novas informações</a>";
        
        PrintWriter pw = response.getWriter();
        pw.write(html);
        pw.close();
        
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

    @Override
    public void destroy() {
        super.destroy(); 
        dados.clear();
        dados = null;
    }
    
}
