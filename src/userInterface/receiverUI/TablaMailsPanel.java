package userInterface.receiverUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import entidades.ConnectionConfig;
import entidades.Contacto;
import entidades.Email;
import entidades.SmtpPop3Server;
import exeptions.ServiceContactoException;
import exeptions.ServiceEmailException;
import exeptions.ServiceSmptPop3ServerException;
import service.ContactoService;
import service.EmailService;
import service.SmptPop3ServerService;
import userInterface.PanelManager;
import userInterface.contactosUI.ContactoTableModel;

public class TablaMailsPanel extends JPanel implements ActionListener{
    
    private  EmailService emailService = new EmailService();

    private PanelManager panelManager;

    private List<Email> listaDeEmails= null;
    
    private  String estadoFiltro = "enviado";

    private JTable tablaEmails;

    private JPanel botonera = new JPanel();

    private MailsTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    
    private JButton botonFiltroEnviadosRecibidos;
    private JButton botonRefresh;
    private JButton botonVerDetalle;

    public TablaMailsPanel(PanelManager panelManager) {
	super();
	this.panelManager = panelManager;
	armarPanel();
    }

    private void armarPanel() {

	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	modelo = new MailsTableModel();

	tablaEmails = new JTable(modelo);

	scrollPaneParaTabla = new JScrollPane(tablaEmails);
	
	try {
	    if (ConnectionConfig.obtenerServidorConfiguradoParaConectar()!=null) {
		 listaDeEmails = emailService.listaDeEmailsFiltrada("enviado", ConnectionConfig.obtenerServidorConfiguradoParaConectar().getUserName());
	    } else {
		 listaDeEmails = emailService.listaDeEmailsFiltrada("enviado", "");
	    }
	   
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	}

	botonFiltroEnviadosRecibidos = new JButton("VER RECIBIDOS");
	botonFiltroEnviadosRecibidos.addActionListener(this);
	botonera.add(botonFiltroEnviadosRecibidos);


	botonRefresh = new JButton("ACTUALIZAR TABLERO MAILS");
	botonRefresh.addActionListener(this);
	
	botonVerDetalle = new JButton("VER CONTENIDO");
	botonVerDetalle.addActionListener(this);
	botonera.add(botonVerDetalle);
	

	botonera.add(botonRefresh);
	
	this.add(botonera);
	this.add(scrollPaneParaTabla);

	modelo.setContenido(listaDeEmails);

	modelo.fireTableDataChanged();

    }
    
    public void actualizarTabla() {
	 try {
	if(ConnectionConfig.obtenerServidorConfiguradoParaConectar()!=null) {
		modelo.setContenido(emailService.listaDeEmailsFiltrada(estadoFiltro, ConnectionConfig.obtenerServidorConfiguradoParaConectar().getUserName()));

	} else {
	  modelo.setContenido(emailService.listaDeEmailsFiltrada(estadoFiltro, ""));
	}
	modelo.fireTableDataChanged();
    } catch (Exception exception) {
	JOptionPane.showMessageDialog(this, exception.getMessage(), "", JOptionPane.ERROR_MESSAGE);
    }
	 
    }
    
    
    public void verDetalleMails() {
	    int column = 0;
	    int filaSeleccionada = this.tablaEmails.getSelectedRow();
	    if (filaSeleccionada != -1) {
		String sender = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column).toString();
		String recipient = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column+1).toString();
		String subject = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column+2).toString();
		String sendDate = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column+3).toString();
		try {
		    Email emailADetallar = emailService.obtenerEmail(sender, subject, recipient, sendDate);
		    new JOptionPaneScrollTextMessage(emailADetallar.getContent().replaceAll("\\n\\n","\\n"));
		} catch (ServiceEmailException | NumberFormatException exption) {
		    JOptionPane.showMessageDialog(this, exption.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		} 
	    } else {
		JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila");
	    }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == botonRefresh) {
	    try {
		int listaDeEmailsInicial = emailService.listaDeEmails().size();
		JOptionPane.showMessageDialog(this, "Se conectará con el Servidor POP3. Esto puede tardar unos segundos y no podrá realizar operaciones" , "", JOptionPane.INFORMATION_MESSAGE);
		emailService.recibirMails();
		listaDeEmails = emailService.listaDeEmails();
		int listaDeEmailsFinal = listaDeEmails.size();
		if(ConnectionConfig.obtenerServidorConfiguradoParaConectar()!=null) {
			modelo.setContenido(emailService.listaDeEmailsFiltrada(estadoFiltro, ConnectionConfig.obtenerServidorConfiguradoParaConectar().getUserName()));

		} else {
		  modelo.setContenido(emailService.listaDeEmailsFiltrada(estadoFiltro, ""));
		  
		}
		modelo.fireTableDataChanged();
		panelManager.actualizarArbol();
		JOptionPane.showMessageDialog(this, "Se ha agregado " + (listaDeEmailsFinal-listaDeEmailsInicial) + " mensaje/s" , "Actualización de Emails", JOptionPane.INFORMATION_MESSAGE);
	    } catch (Exception exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	    }
	}
       if (e.getSource() == botonFiltroEnviadosRecibidos) {
	   List<Email> listaDeEmail;
	   try {
	       if (estadoFiltro=="recibido") {
		   listaDeEmail   = emailService.listaDeEmailsFiltrada("enviado", ConnectionConfig.obtenerServidorConfiguradoParaConectar().getUserName());
		   this.estadoFiltro = "enviado";
		   botonFiltroEnviadosRecibidos.setText("VER RECIBIDOS");
	       } else {
		   listaDeEmail = emailService.listaDeEmailsFiltrada("recibido", ConnectionConfig.obtenerServidorConfiguradoParaConectar().getUserName());
		   this.estadoFiltro = "recibido";
		   botonFiltroEnviadosRecibidos.setText("VER ENVIADOS");
	       }
	       modelo.setContenido(listaDeEmail);
	       modelo.fireTableDataChanged();
	    
	} catch (Exception e1) {
	    JOptionPane.showMessageDialog(this, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	}
	   
       }
       if (e.getSource() == botonVerDetalle) {
	    int column = 0;
	    int filaSeleccionada = this.tablaEmails.getSelectedRow();
	    if (filaSeleccionada != -1) {
		String sender = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column).toString();
		String recipient = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column+1).toString();
		String subject = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column+2).toString();
		String sendDate = this.tablaEmails.getModel().getValueAt(filaSeleccionada, column+3).toString();
		try {
		    Email emailADetallar = emailService.obtenerEmail(sender, subject, recipient, sendDate);
		    new JOptionPaneScrollTextMessage(emailADetallar.getContent().replaceAll("\\n\\n","\\n"));
		} catch (ServiceEmailException | NumberFormatException exption) {
		    JOptionPane.showMessageDialog(this, exption.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		} 
	    } else {
		JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila");
	    }
	}

    }


}
