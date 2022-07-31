package userInterface.serverUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entidades.Contacto;
import entidades.SmtpPop3Server;
import exeptions.ServiceContactoException;
import exeptions.ServiceSmptPop3ServerException;
import service.ContactoService;
import service.SmptPop3ServerService;
import userInterface.PanelManager;
import userInterface.contactosUI.ContactoTableModel;

public class TablaServerPanel extends JPanel implements ActionListener{
    
    private  SmptPop3ServerService serverService = new SmptPop3ServerService();

    private PanelManager panelManager;

    private List<SmtpPop3Server> listaDeServidores = null;

    private JTable tablaServidores;

    private JPanel botonera = new JPanel();

    private SmptPop3ServerTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonBorrar;
    private JButton botonRefresh;
    private JButton botonUpdate;

    public TablaServerPanel(PanelManager panelManager) {
	super();
	this.panelManager = panelManager;
	armarPanel();
    }

    private void armarPanel() {

	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	modelo = new SmptPop3ServerTableModel();

	tablaServidores = new JTable(modelo);

	scrollPaneParaTabla = new JScrollPane(tablaServidores);

	this.add(scrollPaneParaTabla);
	
	try {
	    listaDeServidores = serverService.listaDeServidores();
	} catch (ServiceSmptPop3ServerException e) {
	    JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	}

	botonBorrar = new JButton("ELIMINAR");
	botonBorrar.addActionListener(this);
	botonera.add(botonBorrar);


	botonRefresh = new JButton("REFRESH TABLE");
	botonRefresh.addActionListener(this);
	botonera.add(botonRefresh);


	botonUpdate = new JButton("MODIFICAR DATOS");
	botonUpdate.addActionListener(this);
	botonera.add(botonUpdate);


	this.add(botonera);

	modelo.setContenido(listaDeServidores);

	modelo.fireTableDataChanged();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == botonBorrar) {
	    int column = 0;
	    int filaSeleccionada = this.tablaServidores.getSelectedRow();
	    if (filaSeleccionada != -1) {
		String userName = this.tablaServidores.getModel().getValueAt(filaSeleccionada, column).toString();
		try {
		    serverService.bajaServidor(userName);
		    this.modelo.getContenido().remove(filaSeleccionada);
		    modelo.fireTableDataChanged();
		} catch (ServiceSmptPop3ServerException ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	    } else {
		JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila");
	    }

	} else if (e.getSource() == botonRefresh) {
	    try {
		listaDeServidores = serverService.listaDeServidores();
		modelo.setContenido(listaDeServidores);
		modelo.fireTableDataChanged();
	    } catch (ServiceSmptPop3ServerException exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	    }
	} else if (e.getSource() == botonUpdate) {
	    int column = 0;
	    int filaSeleccionada = this.tablaServidores.getSelectedRow();
	    if (filaSeleccionada != -1) {
		String userName = this.tablaServidores.getModel().getValueAt(filaSeleccionada, column).toString();
		try {
		    SmtpPop3Server servidorAModificarDatos = serverService.obtenerServidor(userName);
		    this.panelManager.mostrarPanelFormularioUpdateServer(servidorAModificarDatos);
		} catch (ServiceSmptPop3ServerException | NumberFormatException exption) {
		    JOptionPane.showMessageDialog(this, exption.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		} 
	    } else {
		JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila");
	    }
	}

    }


}
