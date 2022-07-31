package service;

import java.util.List;

import Dao.DaoContacto;
import Dao.DaoContactoImp;
import entidades.Contacto;
import exeptions.DaoException;
import exeptions.ServiceContactoException;

public class ContactoService {

    DaoContacto daoContacto = new DaoContactoImp();
    
    public List<Contacto> listaDeContactos() throws ServiceContactoException {
	try {
	    return daoContacto.obtenerContactos();
	} catch (DaoException e) {
          throw new ServiceContactoException("No se pudo obtener la lista de Contactos" + e.getMessage());

	}
    }
    
    public void altaContacto(Contacto contacto) throws ServiceContactoException {
	try {
	    daoContacto.agregarNuevoContacto(contacto);
	} catch (DaoException e) {
	   throw new ServiceContactoException("No se pudo dar de alta  el contacto" + e.getMessage() );
	}
    }
    
    
    public void bajaContacto(int id) throws ServiceContactoException {
	try {
	    daoContacto.eliminarContacto(id);
	} catch (DaoException e) {
	   throw new ServiceContactoException("No se pudo eliminar el contacto"+ e.getMessage());
	}
    }
    
    public void modificarContacto(Contacto contacto) throws ServiceContactoException {
	try {
	    daoContacto.modificarContacto(contacto);
	} catch (DaoException e) {
	   throw new ServiceContactoException("No se pudo modificar el contacto"+ e.getMessage() );
	}
    }
    
    public Contacto obtenerContacto(int id) throws ServiceContactoException {
	try {
	    return daoContacto.obtenerContacto(id);
	} catch (DaoException e) {
	   throw new ServiceContactoException("No se pudo obtener el contacto"+ e.getMessage() );
	}
    }
    
}
