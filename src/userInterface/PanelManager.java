package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import entidades.ConnectionConfig;
import entidades.Contacto;
import entidades.SmtpPop3Server;
import exeptions.ServiceSmptPop3ServerException;
import service.SmptPop3ServerService;
import userInterface.contactosUI.PanelLista;
import userInterface.contactosUI.PantallaAltaContactoPanel;
import userInterface.contactosUI.PantallaUpdateContactoPanel;
import userInterface.receiverUI.PanelEmailLista;
import userInterface.receiverUI.TablaMailsPanel;
import userInterface.senderUI.SwingEmailSender;
import userInterface.serverUI.PanelListaServer;
import userInterface.serverUI.PantallaAltaSmptPop3Panel;
import userInterface.serverUI.PantallaUpdateSmtpPop3ServerPanel;

public class PanelManager {

    private JFrame frame;
    private JTabbedPane tabbedPane;

    private StatusLabel statusPanel;
    
    private TreeEmails tree;
    
    private JSplitPane splitPane2;
    
    private JSplitPane splitPane1;

    private SwingEmailSender pantallaEmailSender;
    
    private PanelEmailLista panelEmailLista;

    private PanelLista panelLista;

    private PanelListaServer panelListaServer;

    private PantallaAltaContactoPanel pantallaAltaContactoPanel;
    private PantallaUpdateContactoPanel pantallaUpdateContactoPanel;

    private PantallaUpdateSmtpPop3ServerPanel pantallaUpdateserverPanel;
    private PantallaAltaSmptPop3Panel pantallaAltaServerPanel;

    public PanelManager() {

    }

    public void armarManager() {
	
	frame = new JFrame("Catlook");
	Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\src\\resources\\catlookIcon.png");  
	frame.setIconImage(icon);  
	frame.setBounds(410, 140, 1100, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	

	
	JMenuBar menuBar = new JMenuBar();
	
	// Config Menu, C - Mnemonic
	JMenu fileMenu = new JMenu("Configuración (Alt+C)");
	fileMenu.setMnemonic(KeyEvent.VK_C);
	
	
	// Config->SMPT, S - Mnemonic
	JMenuItem smtpConfigItem = new JMenuItem("Configurar Conexión SMPT/POP3 (Alt+S)", KeyEvent.VK_S);
	
	smtpConfigItem.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		
		SmptPop3ServerService serverService = new SmptPop3ServerService();
		
		 List<SmtpPop3Server> servidores = null;
		 
		 Object[] mapped = null;
		 
		 try {
		    servidores = serverService.listaDeServidores();
		    
		    mapped = servidores.stream().map(servidor -> servidor.getUserName()).collect(Collectors.toList()).toArray();
		    
			 String serverElegidoUserName = (String) JOptionPane.showInputDialog(
				 null,
		                "Which server do you want to connect to?",
		                "Set Connection Config",
		                JOptionPane.QUESTION_MESSAGE,
		                null,
		                mapped,
		                null);
			
		     if (serverElegidoUserName!=null) {
			 SmtpPop3Server serverElegido = serverService.obtenerServidor(serverElegidoUserName);
			 JOptionPane.showMessageDialog(null, "Se ha establecido el Servidor '" + serverElegido.getUserName() +"'", "Configuración del Server SMTP/POP3", JOptionPane.INFORMATION_MESSAGE);
			 try {
			    ConnectionConfig.setConnection(serverElegido);
			    statusPanel.setContent();
			} catch (Exception e1) {
			    JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
			}
		     }
		 
		} catch (ServiceSmptPop3ServerException e1) {
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
		 
		 
	    }
	    
	    
	});
	
	
	fileMenu.add(smtpConfigItem);
	
	
	menuBar.add(fileMenu);
	
	frame.setJMenuBar(menuBar);

	
	tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	
	panelLista = new PanelLista(this);
	panelLista.armarPanelLista();
	
	panelListaServer = new PanelListaServer(this);
	panelListaServer.armarPanelLista();
	
	pantallaAltaContactoPanel = new PantallaAltaContactoPanel(this);
	pantallaAltaContactoPanel.armarPantallaPanel();

	pantallaUpdateContactoPanel = new PantallaUpdateContactoPanel(this);
	pantallaUpdateContactoPanel.armarPantallaPanel();
	
	pantallaAltaServerPanel = new PantallaAltaSmptPop3Panel(this);
	pantallaAltaServerPanel.armarPantallaPanel();
	
	pantallaUpdateserverPanel = new PantallaUpdateSmtpPop3ServerPanel(this);
	pantallaUpdateserverPanel.armarPantallaPanel();
	
	pantallaEmailSender = new SwingEmailSender();
	pantallaEmailSender.setVisible(true);
	
	// create the status bar panel and shove it down the bottom of the frame
	statusPanel = new StatusLabel();
	statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
	frame.add(statusPanel, BorderLayout.SOUTH);
	statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 20));
	statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));	
	
	
	panelEmailLista = new PanelEmailLista(this);
	panelEmailLista.armarPanelLista();
	
        addToTabbedPane();

	
    }

    public void showFrame() {
	frame.setVisible(true);
    }

    public void mostrarPanelBuzon() {
	frame.getContentPane().removeAll();
	frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
	frame.getContentPane().add(tabbedPane);
	tabbedPane.setSelectedComponent(splitPane2);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }

    public void mostrarPanelLista() {
	frame.getContentPane().removeAll();
	frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
	frame.getContentPane().add(tabbedPane);
	tabbedPane.setSelectedComponent(panelLista);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }

    public void mostrarPanelServerLista() {
	frame.getContentPane().removeAll();
	frame.getContentPane().add(tabbedPane);
	tabbedPane.setSelectedComponent(panelListaServer);
	frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }

    public void mostrarPanelFormularioAlta() {
	frame.getContentPane().removeAll();
	frame.getContentPane().add(pantallaAltaContactoPanel);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }

    public void mostrarPanelServerFormularioAlta() {
	frame.getContentPane().removeAll();
	frame.getContentPane().add(pantallaAltaServerPanel);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }

    public void mostrarPanelFormularioUpdate(Contacto contactoAModificarDatos) {
	pantallaUpdateContactoPanel.setContactoAModificar(contactoAModificarDatos);
	frame.getContentPane().removeAll();
	frame.getContentPane().add(pantallaUpdateContactoPanel);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }

    public void mostrarPanelFormularioUpdateServer(SmtpPop3Server datosServerAModificar) {
	pantallaUpdateserverPanel.setServerAModificar(datosServerAModificar);
	frame.getContentPane().removeAll();
	frame.getContentPane().add(pantallaUpdateserverPanel);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
    }
    
	
	private ImageIcon createImageIcon(String path,
            String description) {
      java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
    return new ImageIcon(imgURL, description);
    } else {
    System.err.println("Couldn't find file: " + path);
  return null;
  }
   }
	
	
	
	public TablaMailsPanel getPanelEmailLista() {
	    return this.panelEmailLista.getTabla();
	}
	
	
	public void actualizarArbol() {
	    addToTabbedPane();
	    mostrarPanelBuzon();
	}
	
	private void addToTabbedPane() {
	         tree = new TreeEmails();
	         tabbedPane.removeAll();
	            
	         JScrollPane treeWithScrollPane = tree.armarArbol();
	            
	            
	    	splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelEmailLista, pantallaEmailSender);
	    	splitPane1.setDividerLocation(250);

	    	splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeWithScrollPane, splitPane1);
	    	splitPane2.setDividerLocation(200);
	    	
	    	
	    	tabbedPane.addTab("Administrar Buzón",  splitPane2);
	    	tabbedPane.addTab("Administrar Contactos",  panelLista);
	    	tabbedPane.addTab("Administrar Servidores SMTP/POP3",  panelListaServer);
	    	
	    	ImageIcon mailboxIcon = createImageIcon("/resources/mailboxIcon.png", "Icon Buzón");
	    	tabbedPane.setIconAt(0, mailboxIcon);
	    	ImageIcon contactoIcon = createImageIcon("/resources/contacIcon.png", "Icon Buzón");
	    	tabbedPane.setIconAt(1, contactoIcon);
	    	ImageIcon conexionIcon = createImageIcon("/resources/conexionIcon.png", "Icon Buzón");
	    	tabbedPane.setIconAt(2, conexionIcon);
	    	
	    	frame.add(tabbedPane);
	        }


}
