package Dao;

import java.util.List;

import entidades.Contacto;
import exeptions.DaoException;





public interface DaoContacto {
    
    List<Contacto> obtenerContactos() throws DaoException;
    
    Contacto obtenerContacto(int id) throws DaoException;
    
    void agregarNuevoContacto(Contacto contacto) throws DaoException;
    
    void eliminarContacto(int id) throws DaoException;
    
    void modificarContacto(Contacto contacto) throws DaoException;

}
