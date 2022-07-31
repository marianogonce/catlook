package Dao;

import dataBaseLibrary.DataBase;

public abstract class DaoAbstracto {
    
    protected String localDir = System.getProperty("user.dir");
    
    private Boolean dataStructureCreated = false; 
    
    protected DataBase db = new DataBase("org.h2.Driver", "jdbc:h2:" + localDir + "\\lib\\dbCatlook", "mariano", "");

}
