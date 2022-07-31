package userInterface.contactosUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entidades.Contacto;
import exeptions.DaoException;
import exeptions.ServiceContactoException;
import service.ContactoService;
import userInterface.AbstactPantallaFormularioPanel;
import userInterface.PanelManager;
import validationException.IsNotEmailBasicValidationException;
import validationException.ValidationException;
import validationLibrary.Validations;


public class PantallaAltaContactoPanel extends AbstactPantallaFormularioPanel {

    private ContactoService contactoService = new ContactoService();
    
    private Contacto nuevoContacto;  
   
    public PantallaAltaContactoPanel(PanelManager panelManager) 
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
	this.camposPanel = new CamposContactosPanel(panelManager);     
    } 
    
    @Override
    public void ejecutarAccionOk() 
    { 
	
	nuevoContacto = new Contacto();
	llenarDatos();
	try {
	Validations.isEmailBasicValidation(nuevoContacto.getEmail());
	Validations.NotIsVoid(nuevoContacto.getTelefono());
	Validations.NotIsVoid(nuevoContacto.getNombre());
	vaciarCampos();
	contactoService.altaContacto(nuevoContacto);
	panelManager.mostrarPanelLista();
	} catch( ServiceContactoException | ValidationException e) {
	    JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	}
	
    }
    
  
    @Override
    public void ejecutarAccionCancel() 
    { 
	vaciarCampos();
    } 
    
    private void vaciarCampos() {
	CamposContactosPanel c = (CamposContactosPanel) this.camposPanel; 
	c.getNombreYApellidoTxt().setText(""); 
	c.getEmailTxt().setText("");
	c.getTelefonoTxt().setText("");
    }
    
    public void llenarDatos() 
    {  
	CamposContactosPanel c = (CamposContactosPanel) this.camposPanel; 
	String nombreIngresado = c.getNombreYApellidoTxt().getText(); 
	String emailIngresado = c.getEmailTxt().getText();
	String telefonoIngresado = c.getTelefonoTxt().getText();

	
	nuevoContacto.setNombre(nombreIngresado);
	nuevoContacto.setEmail(emailIngresado);
	nuevoContacto.setTelefono(telefonoIngresado);
	
    } 
  
}
    
    

