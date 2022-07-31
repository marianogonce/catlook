package userInterface.senderUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import entidades.ConnectionConfig;
import entidades.Contacto;
import entidades.SmtpPop3Server;
import exeptions.ConnectionSmtpPop3ConfigException;
import exeptions.ServiceContactoException;
import exeptions.ServiceSmptPop3ServerException;
import javaMailUtils.ConfigUtility;
import javaMailUtils.EmailUtility;
import service.ContactoService;
import service.SmptPop3ServerService;
 
/**
 * A Swing application that allows sending e-mail messages from a SMTP server.
 * @author www.codejava.net
 *
 */
public class SwingEmailSender extends JPanel{
    
    
    
    private ConfigUtility configUtil = new ConfigUtility();
    
     
    private JLabel labelTo = new JLabel("Enviar a: ");
    private JLabel labelSubject = new JLabel("Asunto: ");
    
    String contactoElegido = "";
     
    private JTextField fieldTo = new JTextField(30);
    private JTextField fieldSubject = new JTextField(30);
     
    private JButton buttonSend = new JButton("ENVIAR");
     
    private JFilePicker filePicker = new JFilePicker("Attached", "Attach File...");
     
    private JTextArea textAreaMessage = new JTextArea(10, 30);
     
    private GridBagConstraints constraints = new GridBagConstraints();
     
    public SwingEmailSender() {
         
        // set up layout
        setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
     
        setupForm();
 
    }
 

    private void setupForm() {
	
	
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelTo, constraints);
         
        fieldTo.addFocusListener(new FocusListener() {
            
	    
	    public void focusGained(FocusEvent e) {
		
		ContactoService contactoService = new ContactoService();
		
		 List<Contacto> contactos = null;
		 
		 Object[] mapped = null;
		 
		 try {
		     
		     if (contactoElegido.equals("")) {
			     contactos = contactoService.listaDeContactos();
				    
				    mapped = contactos.stream().map(contacto -> contacto.getNombre()).collect(Collectors.toList()).toArray();
				    
					 contactoElegido = (String) JOptionPane.showInputDialog(
						 null,
				                "Quieres enviar a algún contacto?",
				                "Elegir un contacto",
				                JOptionPane.QUESTION_MESSAGE,
				                null,
				                mapped,
				                null);
					
				     if (contactoElegido!=null) {
					 contactos.removeIf(contacto -> !contacto.getNombre().equals(contactoElegido));
					 fieldTo.setText(contactos.get(0).getEmail());		
				     }
			 
		     }
		     
		     contactoElegido = " ";

		 
		} catch (ServiceContactoException e1) {
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
		 
		 
	    }

	    @Override
	    public void focusLost(FocusEvent e) {
		contactoElegido = "";
		
	    }

	    
	    
	});
        
        
        
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldTo, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelSubject, constraints);
         
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldSubject, constraints);
         
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        buttonSend.setFont(new Font("Arial", Font.BOLD, 16));
        add(buttonSend, constraints);
         
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonSendActionPerformed(event);
            }
        });
         
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        filePicker.setMode(JFilePicker.MODE_OPEN);
        add(filePicker, constraints);
         
        constraints.gridy = 3;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        add(new JScrollPane(textAreaMessage), constraints);    
    }
     
    private void buttonSendActionPerformed(ActionEvent event) {
        if (!validateFields()) {
            return;
        }
         
        String toAddress = fieldTo.getText();
        String subject = fieldSubject.getText();
        String message = textAreaMessage.getText();
         
        File[] attachFiles = null;
         
        if (!filePicker.getSelectedFilePath().equals("")) {
            File selectedFile = new File(filePicker.getSelectedFilePath());
            attachFiles = new File[] {selectedFile};
        }
         
        try {
            Properties smtpProperties = configUtil.loadServerProperties();
            
	    JOptionPane.showMessageDialog(this, "Se conectará con el Servidor SMTP. Esto puede tardar unos segundos y no podrá realizar operaciones" , "", JOptionPane.INFORMATION_MESSAGE);

            EmailUtility.sendEmail(smtpProperties, toAddress, subject, message, attachFiles);
             
            JOptionPane.showMessageDialog(this,
                    "The e-mail has been sent successfully!");
             
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error while sending the e-mail: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    private boolean validateFields() {
        if (fieldTo.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter To address!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            fieldTo.requestFocus();
            return false;
        }
         
        if (fieldSubject.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter subject!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            fieldSubject.requestFocus();
            return false;
        }
         
        if (textAreaMessage.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter message!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            textAreaMessage.requestFocus();
            return false;
        }
         
        return true;
    }
}