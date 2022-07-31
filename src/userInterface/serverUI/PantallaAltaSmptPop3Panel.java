package userInterface.serverUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entidades.Contacto;
import entidades.SmtpPop3Server;
import exeptions.ServiceContactoException;
import exeptions.ServiceSmptPop3ServerException;
import service.ContactoService;
import service.SmptPop3ServerService;
import userInterface.AbstactPantallaFormularioPanel;
import userInterface.PanelManager;
import userInterface.contactosUI.CamposContactosPanel;
import validationException.ValidationException;
import validationLibrary.Validations;

public class PantallaAltaSmptPop3Panel  extends AbstactPantallaFormularioPanel  {
    
    private SmptPop3ServerService servidorService = new SmptPop3ServerService();
    
    private SmtpPop3Server nuevoServidor;  
   
    public PantallaAltaSmptPop3Panel(PanelManager panelManager) 
    { 
	super(panelManager); 
	this.armarPantallaPanel();
	
	
	this.botoneraPanel.getOkBtn().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ejecutarAccionOk();

	    }
	});

	this.botoneraPanel.getCancelBtn().addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ejecutarAccionCancel();

	    }
	});
    } 
    
    @Override
    public void setCamposPanel() 
    { 
	this.camposPanel = new CamposConnectionPanel(panelManager);     
    } 
    
    @Override
    public void ejecutarAccionOk() 
    { 
	
	nuevoServidor = new SmtpPop3Server();
	llenarDatos();
	try {
	  Validations.NotIsVoid(nuevoServidor.getUserName());
	  Validations.NotIsVoid(nuevoServidor.getPassword());
	  Validations.NotIsVoid(nuevoServidor.getSmptHost());
	  Validations.NotIsVoid(nuevoServidor.getSmptPort());
	  Validations.NotIsVoid(nuevoServidor.getPop3Host());
	  Validations.NotIsVoid(nuevoServidor.getPop3Port());
	vaciarCampos();
	servidorService.altaServidor(nuevoServidor);
	panelManager.mostrarPanelServerLista();
	} catch( ServiceSmptPop3ServerException | ValidationException e) {
	    JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	}
	
    }
    
  
    @Override
    public void ejecutarAccionCancel() 
    { 
	vaciarCampos();
    } 
    
    private void vaciarCampos() {
	CamposConnectionPanel c = (CamposConnectionPanel) this.camposPanel; 
	c.getUser().setText("");
	c.getPassword().setText("");
	c.getSmptHost().setText("");
	c.getSmtpPort().setText("");
	c.getPop3Host().setText("");
	c.getPop3Port().setText("");

    }
    
    public void llenarDatos() 
    {  
	CamposConnectionPanel c = (CamposConnectionPanel) this.camposPanel; 

	String userName = c.getUser().getText();
	String password = c.getPassword().getText();
	String smpthost = c.getSmptHost().getText();
	String smptport = c.getSmtpPort().getText();
	String pop3host = c.getPop3Host().getText();
	String pop3port = c.getPop3Port().getText();
	
	nuevoServidor.setUserName(userName);
	nuevoServidor.setPassword(password);
	nuevoServidor.setSmptHost(smpthost);
	nuevoServidor.setSmptPort(smptport);
	nuevoServidor.setPop3Host(pop3host);
	nuevoServidor.setPop3Port(pop3port);
	
	
    } 
  
}
