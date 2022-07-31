package userInterface.serverUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entidades.ConnectionConfig;
import entidades.SmtpPop3Server;
import exeptions.ServiceContactoException;
import exeptions.ServiceSmptPop3ServerException;
import service.SmptPop3ServerService;
import userInterface.AbstactPantallaFormularioPanel;
import userInterface.PanelManager;
import userInterface.contactosUI.CamposContactosPanel;
import validationException.ValidationException;
import validationLibrary.Validations;

public class PantallaUpdateSmtpPop3ServerPanel extends AbstactPantallaFormularioPanel{
    
   private SmptPop3ServerService servidorService = new SmptPop3ServerService();
    
    private SmtpPop3Server servidorAModificar;
   
    public SmtpPop3Server getServidorAModificar() {
        return servidorAModificar;
    }

    public void setServerAModificar(SmtpPop3Server servidorAModificar) {
        this.servidorAModificar = servidorAModificar;
        CamposConnectionPanel c = (CamposConnectionPanel) this.camposPanel; 
	c.getUser().setText(servidorAModificar.getUserName());
	c.getUser().setEnabled(false);
	c.getPassword().setText(servidorAModificar.getPassword());
	c.getSmptHost().setText(servidorAModificar.getSmptHost());
	c.getSmtpPort().setText(servidorAModificar.getSmptPort());
	c.getPop3Host().setText(servidorAModificar.getPop3Host());
	c.getPop3Port().setText(servidorAModificar.getPop3Port());

    }

    public PantallaUpdateSmtpPop3ServerPanel(PanelManager panelManager) 
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
	
	llenarDatos();
	try {
	          Validations.NotIsVoid(servidorAModificar.getUserName());
		  Validations.NotIsVoid(servidorAModificar.getPassword());
		  Validations.NotIsVoid(servidorAModificar.getSmptHost());
		  Validations.NotIsVoid(servidorAModificar.getSmptPort());
		  Validations.NotIsVoid(servidorAModificar.getPop3Host());
		  Validations.NotIsVoid(servidorAModificar.getPop3Port());
		  vaciarCampos();
		servidorService.modificarServidor(servidorAModificar);
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
	
	servidorAModificar.setUserName(userName);
	servidorAModificar.setPassword(password);
	servidorAModificar.setSmptHost(smpthost);
	servidorAModificar.setSmptPort(smptport);
	servidorAModificar.setPop3Host(pop3host);
	servidorAModificar.setPop3Port(pop3port);
	
	
    } 

}
