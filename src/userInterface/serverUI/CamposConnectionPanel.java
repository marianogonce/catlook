package userInterface.serverUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import userInterface.CamposPanel;
import userInterface.PanelManager;

public class CamposConnectionPanel extends CamposPanel {
    
    private JTextField userTxt;
    
    private JTextField passwordTxt;
    
    private JTextField smptHostTxt;
    private JTextField smtpPortTxt;
    private JTextField pop3HostTxt;
    private JTextField pop3PortTxt;
    
    public JTextField getSmptHost() {
	return smptHostTxt;
    }

    public void setSmptHost(JTextField smptHost) {
	this.smptHostTxt = smptHost;
    }

    public JTextField getSmtpPort() {
	return smtpPortTxt;
    }

    public void setSmtpPort(JTextField smtpPort) {
	this.smtpPortTxt = smtpPort;
    }

    public JTextField getPop3Port() {
	return pop3PortTxt;
    }

    public void setPop3Port(JTextField pop3Port) {
	this.pop3PortTxt = pop3Port;
    }

    public JTextField getPassword() {
	return passwordTxt;
    }

    public void setPassword(JTextField password) {
	this.passwordTxt = password;
    }

    public JTextField getUser() {
	return userTxt;
    }

    public void setUser(JTextField user) {
	this.userTxt = user;
    }
    
    public JTextField getPop3Host() {
	return pop3HostTxt;
    }

    public void setPop3Host(JTextField pop3Host) {
	this.pop3HostTxt = pop3Host;
    }

    
    
    public CamposConnectionPanel(PanelManager panelManager) { 
	super(panelManager);    
    }

    public void armarFormulario() {
	this.setLayout(new GridLayout(12, 1));
	
	
	JLabel userLabel = new JLabel("User: ");
	userLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	JLabel passwordLabel = new JLabel("Password: ");
	passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	JLabel smtpHostLabel = new JLabel("SMPT HOST: ");
	smtpHostLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	JLabel smtpPortLabel = new JLabel("SMPT PORT: ");
	smtpPortLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	JLabel pop3HostLabel = new JLabel("POP3 HOST: ");
	pop3HostLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	JLabel pop3PortLabel = new JLabel("POP3 PORT: ");
	pop3PortLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	
	
	
	userTxt = new JTextField("");

	    
	passwordTxt = new JTextField("");
	    
	smptHostTxt = new JTextField("");
	
        smtpPortTxt = new JTextField("");
	
	pop3HostTxt = new JTextField("");
	
	pop3PortTxt = new JTextField("");
	
	this.add(userLabel);
	this.add(userTxt);
	this.add(passwordLabel);
	this.add(passwordTxt);
	this.add(smtpHostLabel);
	this.add(smptHostTxt);
	this.add(smtpPortLabel);
	this.add(smtpPortTxt);
	this.add(pop3HostLabel);
	this.add(pop3HostTxt);
	this.add(pop3PortLabel);
	this.add(pop3PortTxt);


    }
    
    public void armarFormularioUpdate() {
	armarFormulario();
	this.getUser().setEnabled(false);
    }


}
