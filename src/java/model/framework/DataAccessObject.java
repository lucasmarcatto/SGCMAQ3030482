package model.framework;

import controller.AppConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

// Essa classe é abstrata porque serve só como base para outros DAOs.
// Não é usada diretamente, mas sim herdada pelas classes específicas.

public abstract class DataAccessObject {
    
    // Atributos de conexão
    private String tableEntity; //guarda o nome da tabela do banco
    private boolean novelEntity; //true se o objeto ainda não existe no banco (novo)
    private boolean changedEntity; //true se o objeto sofreu alterações
    private HashMap<String, Object> dirtyFields; //armazena os campos que mudaram e precisam ser persistidos
    
    public DataAccessObject(String tableEntity) { //construtor recebe o nome da tabela e já inicializa os controles
        setTableEntity(tableEntity);
        dirtyFields = new HashMap<>();
        
        setNovelEntity(true); //quando cria é considerado "novo"
        setChangedEntity(false); //ainda não tem alterações    
    }
    
    //get----------
    private String getTableEntity() {
        return tableEntity;
    }

    private boolean isNovelEntity() {
        return novelEntity;
    }

    private boolean isChangedEntity() {
        return changedEntity;
    }
    
    //set----------
    public void setTableEntity(String tableEntity) { 
        if( tableEntity != null && // garante que o nome da tabela não vai ser vazio ou nulo
                !tableEntity.isEmpty() && 
                !tableEntity.isBlank() ) {
            this.tableEntity = tableEntity;
        } else {
            throw new IllegalArgumentException("table MUST ba valid.");
        }        
    }

    protected void setNovelEntity(boolean novelEntity) {
        this.novelEntity = novelEntity;
    }

    protected void setChangedEntity(boolean changedEntity) {
        this.changedEntity = changedEntity;
        if( this.changedEntity == false ) { //se não tem alterações, limpa o map de dirtyFields
            dirtyFields.clear();
        }
    }
    
    //Unity Of Work
    protected void addChange(String field, Object value) { //marca um campo como alterado 
        dirtyFields.put(field, value);
        setChangedEntity(true);
    }
    
    private void insert() throws SQLException { //faz um INSERT com os campos que estão em dirtyFields
        String dml = "INSERT INTO " + getTableEntity();
        
        StringJoiner fields = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");
        
        for( String field: dirtyFields.keySet() ) {
//            System.out.println( field + " " + dirtyFields.get(field) );
            fields.add(field);
            values.add("?");
        }
        
        dml += " (" + fields + ") VALUES (" + values + ")";
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( dml );
        
        Connection con = DataBaseConnections.getInstace().getConnection();
        PreparedStatement pst = con.prepareStatement( dml );
        
        int index = 1;
        for( String field : dirtyFields.keySet() ) {
            pst.setObject(index, dirtyFields.get(field));
            index ++;
        }
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( pst );
        
        pst.execute();
        pst.close();
        
        DataBaseConnections.getInstace().closeConnection(con);
    }
    
    private void update() throws SQLException { //faz um UPDATE usando dirtyFields
        
        String dml = "UPDATE " + getTableEntity() + " SET ";
        
        StringJoiner changes = new StringJoiner(",");
        
        for( String field : dirtyFields.keySet() ) {
            changes.add(field + " = ? " );
        }
        
        dml += changes + " WHERE " + getWhereClauseForOneEntity();
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( dml );
        
        Connection con = DataBaseConnections.getInstace().getConnection();
        PreparedStatement pst = con.prepareStatement( dml );
        
        int index = 1;
        for( String field : dirtyFields.keySet() ) {
            pst.setObject(index, dirtyFields.get(field));
            index ++;
        }
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( pst );
        
        pst.execute();
        pst.close();
        
        DataBaseConnections.getInstace().closeConnection(con);
    }
    
    public void save() throws SQLException { //decide se vai dar insert ou update dependendo do estado do objeto
        if( isChangedEntity() ) {
            if( isNovelEntity() ) {
                insert();
                setNovelEntity(false);
            } else {
                update();
            }
            setChangedEntity(false);
        }
    }
    
    public void delete() throws SQLException { //remove o registro do banco
        String dml = "DELETE FROM " + getTableEntity() + " WHERE " + getWhereClauseForOneEntity();
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( dml );
        
        Connection con = DataBaseConnections.getInstace().getConnection();
        Statement st = con.createStatement();
        
        st.execute(dml);
        st.close();
        
        DataBaseConnections.getInstace().closeConnection(con);
    }
    
    public boolean load() throws SQLException { //carrega um registro do banco e preenche o objeto
        boolean resultado;
        
        String dql = "SELECT * FROM " + getTableEntity() + " WHERE " + getWhereClauseForOneEntity();
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( dql );
        
        Connection con = DataBaseConnections.getInstace().getConnection();
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(dql);
        
        resultado = rs.next();
        
        if(resultado){
            ArrayList<Object> data = new ArrayList();
            
            for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                data.add( rs.getObject(i) );
            }
            
            fill(data); //método abstrato que a classe filha implementa
            setNovelEntity(false);
        }
        
        return resultado;
    }
    
    //busca todos os registros da tabela e retorna em forma de lista
    public <T extends DataAccessObject> ArrayList<T> getAllTableEntities() throws SQLException {
        
        ArrayList<T> result = new ArrayList<>();
        
        String dql = "SELECT * FROM " + getTableEntity();
        
        if( AppConfig.getInstance().isVerbose() ) System.out.println( dql );
        
        Connection con = DataBaseConnections.getInstace().getConnection();
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(dql);
        
        while( rs.next() ) {
            ArrayList<Object> data = new ArrayList<>();
            
            for( int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                
                data.add( rs.getObject(i) );
                
            }
            
            result.add( fill(data).copy() );
        }
        
        st.close();
        
        DataBaseConnections.getInstace().closeConnection(con);
        
        return result;
    }
    
    //padrão Template Method
    protected abstract String getWhereClauseForOneEntity();
    protected abstract DataAccessObject fill(ArrayList<Object> data);
    protected abstract <T extends DataAccessObject> T copy();
    
    // ----------MÉTODOS ABSTRATOS----------
    // cada classe concreta precisa dizer:
    // - como identificar um único registro (WHERE)
    // - como preencher os atributos a partir de uma lista de dados
    // - como copiar o objeto 
}
