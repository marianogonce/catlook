package service;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import Dao.DaoEmail;
import Dao.DaoEmailImp;
import entidades.ConnectionConfig;
import entidades.Email;
import entidades.SmtpPop3Server;
import exeptions.DaoException;
import exeptions.ServiceEmailException;
import javaMailUtils.ConfigUtility;
import javaMailUtils.EmailUtility;


public class EmailService {
    
    DaoEmail daoEmail = new DaoEmailImp();
    
    public List<Email> listaDeEmailsFiltrada(String status, String server) throws ServiceEmailException {
	try {
	    List<Email> listaDeEmails = listaDeEmails();
	    listaDeEmails.removeIf(email -> (!email.getStatus().equals(status) || !email.getServer().equals(server)));
	    return listaDeEmails;
	} catch (ServiceEmailException e) {
          throw new ServiceEmailException("No se pudo obtener la lista de emails" + e.getMessage());

	}
    }
    
    public List<Email> listaDeEmails() throws ServiceEmailException {
	try {
	    List<Email> listaDeEmails = daoEmail.obtenerEmails();
	    Collections.reverse(listaDeEmails);
	    SmtpPop3Server servidorConfiguradoParaConectar = ConnectionConfig.obtenerServidorConfiguradoParaConectar();
	    if(servidorConfiguradoParaConectar !=null) {
		listaDeEmails.removeIf(email -> (!email.getServer().equals(servidorConfiguradoParaConectar.getUserName())));
	    } 
	    else {
		listaDeEmails.removeIf(email -> (!email.getServer().equals("")));
	    }
	    return listaDeEmails;
	} catch (Exception e) {
          throw new ServiceEmailException("No se pudo obtener la lista de emails" + e.getMessage());

	}
    }
    

    
    public void altaEmail(Email email) throws ServiceEmailException {
	try {
	    daoEmail.agregarNuevoEmail(email);;
	} catch (DaoException e) {
	   throw new ServiceEmailException("No se pudo dar de alta  el email" + e.getMessage() );
	}
    }
    
    
    public void bajaEmail(String sender, String subject, String recipient, String sendDate) throws ServiceEmailException {
	try {
	    daoEmail.eliminarEmail(sender, subject, recipient, sendDate);
	} catch (DaoException e) {
	   throw new ServiceEmailException("No se pudo eliminar el email "+ e.getMessage());
	}
    }
    
    
    public Email obtenerEmail(String sender, String subject, String recipient, String sendDate) throws ServiceEmailException {
	try {
	    return daoEmail.obtenerEmail(sender, subject, recipient, sendDate);
	} catch (DaoException e) {
	   throw new ServiceEmailException("No se pudo obtener el email"+ e.getMessage() );
	}
    }
    
    
   
    public void recibirMails() throws ServiceEmailException {
	ConfigUtility configUtility = new ConfigUtility();
	List<Email> listaDeMails;
	try {
	    if(ConnectionConfig.obtenerServidorConfiguradoParaConectar()==null) {
		throw new ServiceEmailException("No hay configurado un servidor" );

	    }
	    Properties pop3Properties = configUtility.loadServerProperties();
	    pop3Properties.setProperty("mail.user", "recent:" + pop3Properties.getProperty("mail.user"));
	    listaDeMails = EmailUtility.receiveEmail(pop3Properties);
	     for (int i = 0; i < listaDeMails.size(); i++) {
		 if(verificarExistencia(listaDeMails.get(i))==false) {
		     altaEmail(listaDeMails.get(i));
		 }
	    }
	} catch (Exception e) {
	    throw new ServiceEmailException("No se pudo recibir los mails del servidor y guardarlos en la DB " + e.getMessage() );
	}
	
    }
    
    public Boolean verificarExistencia(Email email) throws ServiceEmailException {
	Boolean existe = false; 
	List<Email> listaDeMails;
	try {
	    listaDeMails = listaDeEmails();
		for (int i = 0; i < listaDeMails.size(); i++) {
		    if (listaDeMails.get(i).getSender().equals(email.getSender()) && listaDeMails.get(i).getRecipient().equals(email.getRecipient()) && listaDeMails.get(i).getSubject().equals(email.getSubject()) && listaDeMails.get(i).getSentDate().equals(email.getSentDate())){
			existe = true;
			break;
		    }
		}
	} catch (ServiceEmailException e) {
	    throw e;
	}
	return existe;

    }
    
}
