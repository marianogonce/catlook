package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dataBaseLibrary.DataBaseLibrary;
import databaseExceptions.DataBaseException;
import entidades.SmtpPop3Server;
import exeptions.DaoServerException;

public class DaoSmptPop3ServerImp extends DaoAbstracto implements DaoSmptPop3Server {

    @Override
    public List<SmtpPop3Server> obtenerServidoresSmtpPop3() throws DaoServerException {

	DataBaseLibrary.setDb(db);
	
	try {

	    List<SmtpPop3Server> listaDeServidores = new ArrayList<SmtpPop3Server>();

	    List<Map<String, Object>> servidores = DataBaseLibrary.queryStatement("SELECT * FROM SMPTPOP3SERVERS;");
 
	    
	    for (int i = 0; i < servidores.size(); i++) {
		Map<String, Object> servidor = servidores.get(i);
		listaDeServidores.add(new SmtpPop3Server(servidor.get("USERNAME").toString(), servidor.get("PASSWORD").toString(), servidor.get("SMPTHOST").toString(), servidor.get("SMPTPORT").toString(), servidor.get("POP3HOST").toString(), servidor.get("POP3PORT").toString()));
	    }

	    return listaDeServidores;

	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
	}
	
    }

    @Override
    public SmtpPop3Server obtenerServidorSmtpPop3(String userName) throws DaoServerException {
	DataBaseLibrary.setDb(db);
	try {
	    List<SmtpPop3Server> listaDeServidores = new ArrayList<SmtpPop3Server>();
	    List<Map<String, Object>> servidores = DataBaseLibrary.queryStatement("SELECT * FROM SMPTPOP3SERVERS WHERE USERNAME='" + userName  + "';");
	    for (int i = 0; i < servidores.size(); i++) {
	  		Map<String, Object> servidor = servidores.get(i);
	  		listaDeServidores.add(new SmtpPop3Server(servidor.get("USERNAME").toString(), servidor.get("PASSWORD").toString(), servidor.get("SMPTHOST").toString(), servidor.get("SMPTPORT").toString(), servidor.get("POP3HOST").toString(), servidor.get("POP3PORT").toString()));
	  	    }
	    
	    if (!listaDeServidores.isEmpty()) {
		return listaDeServidores.get(0);
	    }
	    return null;
	    
	    
	
	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
    }

    @Override
    public void agregarNuevoServidorSmtpPop3(SmtpPop3Server smtpPop3Server) throws DaoServerException {
	try {
		
		String statement1 = "INSERT INTO SMPTPOP3SERVERS VALUES ('" + smtpPop3Server.getUserName().toString() + "','" + smtpPop3Server.getPassword().toString()  + "','" + smtpPop3Server.getSmptHost().toString() + "','" + smtpPop3Server.getSmptPort().toString() + "','" + smtpPop3Server.getPop3Host().toString() + "','" + smtpPop3Server.getPop3Port().toString()  + "');";
		
		DataBaseLibrary.updateStatement(statement1);
		  
		} catch (DataBaseException e) {
		    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
		}
	
    }

    @Override
    public void eliminarServidorSmtpPop3(String userName) throws DaoServerException {
	DataBaseLibrary.setDb(db);
	
	try {
	    DataBaseLibrary.updateStatement("DELETE FROM SMPTPOP3SERVERS WHERE USERNAME='" + userName + "';");
	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
	
    }

    @Override
    public void modificarServidorSmtpPop3(SmtpPop3Server smtpPop3Server) throws DaoServerException {
	DataBaseLibrary.setDb(db);
	try {
	    DataBaseLibrary.updateStatement("UPDATE SMPTPOP3SERVERS SET PASSWORD='" + smtpPop3Server.getPassword() + "',SMPTHOST='" + smtpPop3Server.getSmptHost() + "', SMPTPORT= '" + smtpPop3Server.getSmptPort()  + "', POP3HOST= '" + smtpPop3Server.getPop3Host() + "', POP3PORT= '" + smtpPop3Server.getPop3Port() +"' WHERE USERNAME='" + smtpPop3Server.getUserName()  + "';");
	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
	
    }

}
