package Dao;

import java.util.List;

import entidades.SmtpPop3Server;
import exeptions.DaoServerException;


public interface DaoSmptPop3Server {

    List<SmtpPop3Server> obtenerServidoresSmtpPop3() throws DaoServerException;
    
    SmtpPop3Server obtenerServidorSmtpPop3(String userName) throws DaoServerException;
    
    void agregarNuevoServidorSmtpPop3(SmtpPop3Server smtpPop3Server) throws DaoServerException;
    
    void eliminarServidorSmtpPop3(String userName) throws DaoServerException;
    
    void modificarServidorSmtpPop3(SmtpPop3Server smtpPop3Server) throws DaoServerException;
}
