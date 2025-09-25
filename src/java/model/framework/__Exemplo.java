package model.framework;

import model.TipoUsuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class __Exemplo {
    public static void main(String[] args) throws SQLException {
        
//        System.out.println(DataBaseConnections.getInstance().getConnection());
        
        TipoUsuario tp = new TipoUsuario();
        
//        tp.setId(51);
//        tp.setModuloAdministrativo("N");
//        tp.setModuloAgendamento("N");
//        tp.setModuloAtendimento("S");
//       
//        tp.save(); //insert
//      
//        tp.setModuloAdministrativo("S");
//        tp.setModuloAgendamento("S");
//        
//        tp.save(); //update

//        tp.setId(51);
//        boolean status = tp.load();
//        System.out.println(status);
//        System.out.println(tp);
//        
//        tp.setNome("tipo usuario 51");
//        tp.save(); //update
//        System.out.println(tp);
//        
//        tp.delete();
//        
        ArrayList<TipoUsuario> lst = new TipoUsuario().getAllTableEntities();
        System.out.println( lst );
            
    }
}
