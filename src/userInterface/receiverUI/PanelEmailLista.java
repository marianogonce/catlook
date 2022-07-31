package userInterface.receiverUI;

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

public class PanelEmailLista extends JPanel {
    
    private PanelManager panelManager;
    private JButton crearBtn;
    private JLabel label;
    private TablaMailsPanel tabla;
 
    

    public PanelEmailLista(PanelManager m) {
	this.panelManager = m;
	this.tabla = new TablaMailsPanel(this.panelManager);
    }

    public void armarPanelLista() {

	this.setLayout(new BorderLayout());


	add(tabla, BorderLayout.CENTER);


    }

    public TablaMailsPanel getTabla() {
        return tabla;
    }

    public void setTabla(TablaMailsPanel tabla) {
        this.tabla = tabla;
    }
}
