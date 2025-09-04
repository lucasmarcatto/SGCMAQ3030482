package model.framework;

import java.util.HashMap;

public abstract class DataAccessObject {
    
    private String tableEntity;
    private boolean novelEntity;
    private boolean changedEntity;
    private HashMap<String, Object> dirtyFields;
    
    public DataAccessObject(String tableEntity) {
        setTableEntity(tableEntity);
        dirtyFields = new HashMap<>();
        setNovelEntity(true);
        setChangedEntity(false);        
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
        if( tableEntity != null && 
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
        if( this.changedEntity == false ) {
            dirtyFields.clear();
        }
    }
    
    //Unity Of Work
    protected void addChange(String field, Object value) {
        dirtyFields.put(field, value);
        setChangedEntity(true);
    }
    
    private void insert() {
        System.out.println("insert()");
    }
    
    private void update() {
        System.out.println("update()");
    }
    
    public void save() {
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
}
