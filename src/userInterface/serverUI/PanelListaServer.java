package userInterface.serverUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import userInterface.PanelManager;
import userInterface.contactosUI.TablaContactoPanel;

public class PanelListaServer extends JPanel {
    
    private PanelManager panelManager;
    private JButton crearBtn;
    private JLabel label;
    private TablaServerPanel tabla;
 
    

    public PanelListaServer(PanelManager m) {
	this.panelManager = m;
	this.tabla = new TablaServerPanel(this.panelManager);
    }

    public void armarPanelLista() {

	this.setLayout(new BorderLayout());

	crearBtn = new JButton("ALTA SMPT/POP3 SERVER");


	label = new JLabel("Lista de Servidores SMPT/POP3");
	label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
	label.setFont(new Font("Verdana", Font.PLAIN, 19));

	tabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

	add(label, BorderLayout.NORTH);
	add(tabla, BorderLayout.CENTER);
	add(crearBtn, BorderLayout.SOUTH);

	crearBtn.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		panelManager.mostrarPanelServerFormularioAlta();
	    }
	});
    }

    public TablaServerPanel getTabla() {
        return tabla;
    }

    public void setTabla(TablaServerPanel tabla) {
        this.tabla = tabla;
    }
}
