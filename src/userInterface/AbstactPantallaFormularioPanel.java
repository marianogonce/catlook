package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class AbstactPantallaFormularioPanel extends JPanel {

    protected PanelManager panelManager;
    protected CamposPanel camposPanel;
    protected BotoneraPanel botoneraPanel;
    

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void setPanelManager(PanelManager panelManager) {
        this.panelManager = panelManager;
    }

    public CamposPanel getCamposPanel() {
        return camposPanel;
    }

    public void setCamposPanel(CamposPanel camposPanel) {
        this.camposPanel = camposPanel;
    }

    public BotoneraPanel getBotoneraPanel() {
        return botoneraPanel;
    }

    public void setBotoneraPanel(BotoneraPanel botoneraPanel) {
        this.botoneraPanel = botoneraPanel;
    }


    public AbstactPantallaFormularioPanel(PanelManager panelManager) {
	this.panelManager = panelManager;
	this.setCamposPanel();
	this.setBotoneraPanel();
	armarPantallaPanel();
    }

    public void armarPantallaPanel() {

	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	
	this.add(camposPanel);
	this.add(botoneraPanel);



    }

    public void setBotoneraPanel() {
	this.botoneraPanel = new BotoneraPanel(this.panelManager);
    }

    public abstract void setCamposPanel();

    public abstract void ejecutarAccionOk();

    public abstract void ejecutarAccionCancel();

}
