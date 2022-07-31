package userInterface;

import javax.swing.JPanel;

public abstract class CamposPanel extends JPanel {
    
    private PanelManager panelManager; 
    
    public CamposPanel(PanelManager panelManager) 
    { 
	this.panelManager = panelManager; 
	armarFormulario(); 
    } 
    
    public abstract void armarFormulario(); 

}
