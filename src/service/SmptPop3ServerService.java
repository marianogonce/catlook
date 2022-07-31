package service;

import java.util.List;

import Dao.DaoSmptPop3Server;
import Dao.DaoSmptPop3ServerImp;
import entidades.SmtpPop3Server;
import exeptions.DaoException;
import exeptions.ServiceSmptPop3ServerException;


public class SmptPop3ServerService {

    DaoSmptPop3Server daoServer = new DaoSmptPop3ServerImp();
    
    public List<SmtpPop3Server> listaDeServidores() throws ServiceSmptPop3ServerException {
	try {
	    return daoServer.obtenerServidoresSmtpPop3();
	} catch (DaoException e) {
          throw new ServiceSmptPop3ServerException("No se pudo obtener la lista de servidores" + e.getMessage());

	}
    }
    
    
    public void altaServidor(SmtpPop3Server servidor) throws ServiceSmptPop3ServerException {
	try {
	    
	    if(daoServer.obtenerServidorSmtpPop3(servidor.getUserName()) != null) {
		throw new ServiceSmptPop3ServerException("El servidor con ese nombre de usuario ya existe");
	    }
	    
	    daoServer.agregarNuevoServidorSmtpPop3(servidor); ;
	} catch (DaoException e) {
	   throw new ServiceSmptPop3ServerException("No se pudo dar de alta  el servidor" + e.getMessage() );
	}
    }
    
    public void bajaServidor(String userName) throws ServiceSmptPop3ServerException {
	try {
	    daoServer.eliminarServidorSmtpPop3(userName);
	} catch (DaoException e) {
	   throw new ServiceSmptPop3ServerException("No se pudo eliminar el servidor"+ e.getMessage());
	}
    }
    
    public void modificarServidor(SmtpPop3Server servidor) throws ServiceSmptPop3ServerException {
	try {
	    daoServer.modificarServidorSmtpPop3(servidor);
	} catch (DaoException e) {
	   throw new ServiceSmptPop3ServerException("No se pudo modificar el servidor"+ e.getMessage() );
	}
    }
    
    public SmtpPop3Server obtenerServidor(String userName) throws ServiceSmptPop3ServerException {
	try {
	    return daoServer.obtenerServidorSmtpPop3(userName);
	} catch (DaoException e) {
	   throw new ServiceSmptPop3ServerException("No se pudo obtener el servidor"+ e.getMessage() );
	}
    }
    
    
    
}
