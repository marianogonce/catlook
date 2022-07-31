package userInterface.contactosUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import userInterface.PanelManager;

public class PanelLista extends JPanel {

    private PanelManager panelManager;
    private JButton crearBtn;
    private JLabel label;
    private TablaContactoPanel tabla;
 
    

    public PanelLista(PanelManager m) {
	this.panelManager = m;
	this.tabla = new TablaContactoPanel(this.panelManager);
    }

    public void armarPanelLista() {

	this.setLayout(new BorderLayout());

	crearBtn = new JButton("ALTA CONTACTO");


	label = new JLabel("Lista de Contactos");
	label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
	label.setFont(new Font("Verdana", Font.PLAIN, 19));

	tabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

	add(label, BorderLayout.NORTH);
	add(tabla, BorderLayout.CENTER);
	add(crearBtn, BorderLayout.SOUTH);

	crearBtn.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		panelManager.mostrarPanelFormularioAlta();
	    }
	});
    }

    public TablaContactoPanel getTabla() {
        return tabla;
    }

    public void setTabla(TablaContactoPanel tabla) {
        this.tabla = tabla;
    }
    

}
