package controller;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TipoUsuario;

public class AutenticadorAdm implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession sessao = httpServletRequest.getSession(false);

        // Se não há sessão, redireciona
        
        // Recupera o tipo de usuário
        TipoUsuario tipoUsuario = (TipoUsuario) sessao.getAttribute("tipo_usuario");

        // Faz as verificações
        if (tipoUsuario.getModuloAdministrativo().equals("S") == false) {
            httpServletRequest.setAttribute("msg", "Você não tem acesso, somente pessoal autorizado");
            httpServletResponse.sendRedirect( httpServletRequest.getContextPath() + "/home/app/menu.jsp");            
            
//            httpServletRequest.getRequestDispatcher("/home/app/menu.jsp").forward(httpServletRequest, response);
        } else {
            chain.doFilter(request, response);
        }

    }

}
