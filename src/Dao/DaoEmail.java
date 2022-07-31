package Dao;

import java.util.List;

import entidades.Email;
import exeptions.DaoException;

public interface DaoEmail {
    
    List<Email> obtenerEmails() throws DaoException;
    
    Email obtenerEmail(String sender, String Subject, String Recipient, String SendDate) throws DaoException;
    
    void agregarNuevoEmail(Email email) throws DaoException;
    
    void eliminarEmail(String sender, String Subject, String Recipient, String SendDate) throws DaoException;
    
    //void modificarEmail(Email email) throws DaoException;

}
