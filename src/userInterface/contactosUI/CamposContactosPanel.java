package userInterface.contactosUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import userInterface.CamposPanel;
import userInterface.PanelManager;

public class CamposContactosPanel extends CamposPanel {



    private JTextField nombreYApellidoTxt;
    
    private JTextField emailTxt;
    
    private JTextField telefonoTxt;
    
    
    public JTextField getNombreYApellidoTxt() {
        return nombreYApellidoTxt;
    }

    public void setNombreYApellidoTxt(JTextField nombreYApellidoTxt) {
        this.nombreYApellidoTxt = nombreYApellidoTxt;
    }

    public JTextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(JTextField emailTxt) {
        this.emailTxt = emailTxt;
    }
    
    public JTextField getTelefonoTxt() {
	return telefonoTxt;
    }

    public void setTelefonoTxt(JTextField telefonoTxt) {
	this.telefonoTxt = telefonoTxt;
    }

    
    
    public CamposContactosPanel(PanelManager panelManager) { 
	super(panelManager);    
    }

    public void armarFormulario() {
	this.setLayout(new GridLayout(6, 1));
	
	
	JLabel nombreYApellidoLbl = new JLabel("Nombre y Apellido: ");

	nombreYApellidoLbl.setFont(new Font("Verdana", Font.PLAIN, 15));
	JLabel emailLbl = new JLabel("Email : ");
	emailLbl.setFont(new Font("Verdana", Font.PLAIN, 15));
	JLabel telefonoLbl = new JLabel("Telefono : ");
	telefonoLbl.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	nombreYApellidoTxt = new JTextField("");
	emailTxt = new JTextField("");
	telefonoTxt = new JTextField("");
	
	
	this.add(nombreYApellidoLbl);
	this.add(nombreYApellidoTxt);
	this.add(emailLbl);
	this.add(emailTxt);
	this.add(telefonoLbl);
	this.add(telefonoTxt);

    }

}
