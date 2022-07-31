package outlookClientTp;

import java.awt.EventQueue;


import entidades.ConnectionConfig;
import userInterface.PanelManager;

public class MainClass {

    private PanelManager manager;

    public void iniciarManager() {
	this.manager = new PanelManager();
	this.manager.armarManager();
        ConnectionConfig.setPanelManager(this.manager);
    }

    public void showFrame() {
	this.manager.showFrame();
    }

    public static void main(String[] args) {
	Runnable runner = new Runnable() {

	    @Override
	    public void run() {

		MainClass ppal = new MainClass();
		ppal.iniciarManager();
		ppal.showFrame();

	    }

	};
	EventQueue.invokeLater(runner);
    }

}
