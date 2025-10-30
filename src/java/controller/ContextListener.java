package controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import logtrack.ExceptionLogTrack;
import model.framework.DataBaseConnections;

//import jakarta.servlet.annotation.WebListener;
//@WebListener

public class ContextListener implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        System.out.println( AppConfig.getInstance() );
        System.out.println( ExceptionLogTrack.getInstance() );
        System.out.println( DataBaseConnections.getInstace() );
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    
        try {
            
            DataBaseConnections.getInstace().closeAllConnection();
            
        } catch (Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
        }
        
    }  
    
}
