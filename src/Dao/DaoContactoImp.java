package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dataBaseLibrary.DataBaseLibrary;
import databaseExceptions.DataBaseException;
import entidades.Contacto;
import exeptions.DaoException;


public class DaoContactoImp extends DaoAbstracto  implements DaoContacto {
    
    
    @Override
    public List<Contacto> obtenerContactos() throws DaoException {

	DataBaseLibrary.setDb(db);
	
	try {

	    List<Contacto> listaDeContacto = new ArrayList<Contacto>();

	    List<Map<String, Object>> contactos = DataBaseLibrary.queryStatement("SELECT * FROM CONTACTOS;");
 
	    
	    for (int i = 0; i < contactos.size(); i++) {
		Map<String, Object> contacto = contactos.get(i);
		listaDeContacto.add(new Contacto((int) contacto.get("ID"), contacto.get("NOMBRE").toString(),
			contacto.get("MAIL").toString(), contacto.get("TELEFONO").toString()));
	    }

	    return listaDeContacto;

	} catch (DataBaseException e) {
	    throw new DaoException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
	}

    }
    
    
    
    @Override
    public void agregarNuevoContacto(Contacto contacto) throws DaoException {
	DataBaseLibrary.setDb(db);
	
	try {
	
	String id = DataBaseLibrary.queryStatement("SELECT * FROM SECUENCIAL;").get(0).get("NUMEROSECUENCIAL").toString();
	
	String statement1 = "INSERT INTO CONTACTOS VALUES (" + id + ",'" + contacto.getNombre().toString() + "','" + contacto.getEmail().toString() + "','" + contacto.getTelefono().toString()  + "');";
	
	String statement2 = "UPDATE SECUENCIAL SET NUMEROSECUENCIAL= "+ String.valueOf((Integer.parseInt(id)+1)) + ";";
	
	
	String[] sentenciasAejecutar = {statement1, statement2};
	
	DataBaseLibrary.updateStatements(sentenciasAejecutar);
	  

	} catch (DataBaseException e) {
	    throw new DaoException("No se pudo relizar la operación en la Base de Datos: " + e.getMessage());
	}
    
    }

    @Override
    public void eliminarContacto(int id) throws DaoException {
	DataBaseLibrary.setDb(db);
	
	try {
	    DataBaseLibrary.updateStatement("DELETE FROM CONTACTOS WHERE ID=" + String.valueOf(id) + ";");
	} catch (DataBaseException e) {
	    throw new DaoException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
    }



    @Override
    public void modificarContacto(Contacto contacto) throws DaoException {
	DataBaseLibrary.setDb(db);
	try {
	    DataBaseLibrary.updateStatement("UPDATE CONTACTOS SET NOMBRE='" + contacto.getNombre() + "',MAIL='" + contacto.getEmail() + "', TELEFONO= '" + contacto.getTelefono() + "' WHERE ID=" + contacto.getId()  + ";");
	} catch (DataBaseException e) {
	    throw new DaoException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
		
    }



    @Override
    public Contacto obtenerContacto(int id) throws DaoException {
	DataBaseLibrary.setDb(db);
	try {
	    List<Contacto> listaDeContacto = new ArrayList<Contacto>();
	    List<Map<String, Object>> contactos = DataBaseLibrary.queryStatement("SELECT * FROM CONTACTOS WHERE ID=" + String.valueOf(id)  + ";");
	    for (int i = 0; i < contactos.size(); i++) {
		Map<String, Object> contacto = contactos.get(i);
		listaDeContacto.add(new Contacto((int) contacto.get("ID"), contacto.get("NOMBRE").toString(),
			contacto.get("MAIL").toString(), contacto.get("TELEFONO").toString()));
	    }
	
	    return listaDeContacto.get(0);
	
	} catch (DataBaseException e) {
	    throw new DaoException("No se pudo relizar la operación en la Base de Datos:" + e.getMessage());
	}
    }

}
