package userInterface;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotoneraPanel extends JPanel {
    
    private PanelManager panelmanager; 
    
    private JButton okBtn;
    private JButton cancelBtn;
    private JButton backBtn;
    
    public BotoneraPanel(PanelManager panelmanager) {
	this.panelmanager = panelmanager;
	armarBotoneraPanel();
	
    }
    
    public JButton getOkBtn() {
        return okBtn;
    }

    public void setOkBtn(JButton okBtn) {
        this.okBtn = okBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(JButton backBtn) {
        this.backBtn = backBtn;
    }

    public void armarBotoneraPanel() {
	
	this.setLayout(new FlowLayout());
	
	this.okBtn = new JButton("ACEPTAR");
	
	this.cancelBtn = new JButton("LIMPIAR CAMPOS");

	this.backBtn = new JButton("VOLVER");

	this.add(backBtn);
	this.add(okBtn);
	this.add(cancelBtn);
   
	this.backBtn.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		panelmanager.mostrarPanelBuzon();
	    }
	});
    
    }
    
    

    
    
    

}
