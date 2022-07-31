package userInterface.contactosUI;

import java.awt.Color;
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
import exeptions.ServiceContactoException;
import service.ContactoService;
import userInterface.PanelManager;

public class TablaContactoPanel extends JPanel implements ActionListener {

    private ContactoService contactoService = new ContactoService();

    private PanelManager panelManager;

    private List<Contacto> listaDeContactos = null;

    private JTable tablaContactos;

    private JPanel botonera = new JPanel();

    private ContactoTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonBorrar;
    private JButton botonRefresh;
    private JButton botonUpdate;

    public TablaContactoPanel(PanelManager panelManager) {
	super();
	this.panelManager = panelManager;
	armarPanel();
    }

    private void armarPanel() {

	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	modelo = new ContactoTableModel();

	tablaContactos = new JTable(modelo);

	scrollPaneParaTabla = new JScrollPane(tablaContactos);

	this.add(scrollPaneParaTabla);
	
	try {
	    listaDeContactos = contactoService.listaDeContactos();
	} catch (ServiceContactoException e) {
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

	modelo.setContenido(listaDeContactos);

	modelo.fireTableDataChanged();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == botonBorrar) {
	    int column = 0;
	    int filaSeleccionada = this.tablaContactos.getSelectedRow();
	    if (filaSeleccionada != -1) {
		String id = this.tablaContactos.getModel().getValueAt(filaSeleccionada, column).toString();
		try {
		    contactoService.bajaContacto(Integer.parseInt(id));
		    this.modelo.getContenido().remove(filaSeleccionada);
		    modelo.fireTableDataChanged();
		} catch (ServiceContactoException ex) {
		    JOptionPane.showMessageDialog(this, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	    } else {
		JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila");
	    }

	} else if (e.getSource() == botonRefresh) {
	    try {
		listaDeContactos = contactoService.listaDeContactos();
		modelo.setContenido(listaDeContactos);
		modelo.fireTableDataChanged();
	    } catch (ServiceContactoException exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "", JOptionPane.ERROR_MESSAGE);
	    }
	} else if (e.getSource() == botonUpdate) {
	    int column = 0;
	    int filaSeleccionada = this.tablaContactos.getSelectedRow();
	    if (filaSeleccionada != -1) {
		String id = this.tablaContactos.getModel().getValueAt(filaSeleccionada, column).toString();
		try {
		    Contacto contactoAModificarDatos = contactoService.obtenerContacto(Integer.parseInt(id));
		    this.panelManager.mostrarPanelFormularioUpdate(contactoAModificarDatos);
		} catch (ServiceContactoException | NumberFormatException exption) {
		    JOptionPane.showMessageDialog(this, exption.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		} 
	    } else {
		JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila");
	    }
	}

    }

}
