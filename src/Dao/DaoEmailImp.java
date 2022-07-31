package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dataBaseLibrary.DataBaseLibrary;
import databaseExceptions.DataBaseException;
import entidades.Email;
import exeptions.DaoException;
import exeptions.DaoServerException;

public class DaoEmailImp extends DaoAbstracto implements DaoEmail  {

    @Override
    public List<Email> obtenerEmails() throws DaoException {
	
	DataBaseLibrary.setDb(db);
	
	try {

	    List<Email> listaDeMails = new ArrayList<Email>();

	    List<Map<String, Object>> mails = DataBaseLibrary.queryStatement("SELECT * FROM EMAILS;");
 
	    
	    for (int i = 0; i < mails.size(); i++) {
		Map<String, Object> mail = mails.get(i);
		listaDeMails.add(new Email(mail.get("SUBJECT").toString(), mail.get("SENDER").toString(), mail.get("RECIPIENT").toString(), mail.get("SENTDATE").toString(), mail.get("CONTENT").toString(), mail.get("STATUS").toString(), mail.get("SERVER").toString()));
	    }

	    return listaDeMails;

	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
	}
	
    }

    @Override
    public Email obtenerEmail(String sender, String subject, String recipient, String sendDate) throws DaoException {

	DataBaseLibrary.setDb(db);
	
	try {

	    List<Email> listaDeMails = new ArrayList<Email>();

	    List<Map<String, Object>> mails = DataBaseLibrary.queryStatement("SELECT * FROM EMAILS WHERE SENDER='" + sender + "' AND SUBJECT = '" + subject + "' AND RECIPIENT ='" + recipient + "' AND SENTDATE ='" + sendDate+ "';");
 
	    
	    for (int i = 0; i < mails.size(); i++) {
		Map<String, Object> mail = mails.get(i);
		listaDeMails.add(new Email(mail.get("SUBJECT").toString(), mail.get("SENDER").toString(), mail.get("RECIPIENT").toString(), mail.get("SENTDATE").toString(), mail.get("CONTENT").toString(), mail.get("STATUS").toString(), mail.get("SERVER").toString()));
	    }

	    return listaDeMails.get(0);

	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
	}
	
    }

    @Override
    public void agregarNuevoEmail(Email email) throws DaoException {
	
	try {
	
	String statement1 = "INSERT INTO EMAILS VALUES ('" + email.getSender().toString() + "','" + email.getSubject() + "','" +  email.getRecipient() + "','" + email.getSentDate()  + "','" + email.getContent() + "','" +  email.getStatus() + "','" +  email.getServer() + "');";
	
	DataBaseLibrary.updateStatement(statement1);
	  
	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
	}
	
    }

    @Override
    public void eliminarEmail(String sender, String subject, String recipient, String sendDate) throws DaoException {
	DataBaseLibrary.setDb(db);
	
	try {
	    DataBaseLibrary.updateStatement("DELETE FROM EMAILS WHERE SENDER='" + sender + "' AND SUBJECT= '" +  subject + "' AND RECIPIENT='" + recipient + "' AND SENTDATE='" + sendDate +"';");
	} catch (DataBaseException e) {
	    throw new DaoServerException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
	
    }


}
