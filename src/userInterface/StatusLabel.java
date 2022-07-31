package userInterface;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import entidades.ConnectionConfig;
import entidades.SmtpPop3Server;
import exeptions.ConnectionConfigException;

public class StatusLabel extends JLabel {
    
    
    JLabel statusLabel =  new JLabel();
    
    
    public StatusLabel() {
	setContent();
	statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
	this.add(statusLabel);
    } 
    
    public void setContent() {
	
	SmtpPop3Server serverConectado;
	
	    try {
		serverConectado = ConnectionConfig.obtenerServidorConfiguradoParaConectar();
		if (serverConectado!=null) {
			   

		    statusLabel.setText("Configurado para conectar a : " + serverConectado.getUserName() + "     SMTP Host :" + serverConectado.getSmptHost() + "      POP3 Host :" + serverConectado.getPop3Host() );
		    statusLabel.setForeground(Color.BLUE);
		}
		if (serverConectado==null) {
		    statusLabel.setText("No hay servidor SMTP/POP3 configurado para enviar/recibir msg --!");
		    statusLabel.setForeground(Color.RED);
		}
	    } catch (ConnectionConfigException e) {
		e.printStackTrace();
	    }
	

	
    }
}
