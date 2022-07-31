package entidades;


import exeptions.ConnectionConfigException;
import javaMailUtils.ConfigUtility;
import service.SmptPop3ServerService;
import userInterface.PanelManager;

public abstract class ConnectionConfig {

    
    private static ConfigUtility configUtility = new ConfigUtility();
    
    private static PanelManager panelManager;


    public static SmtpPop3Server obtenerServidorConfiguradoParaConectar() throws ConnectionConfigException{

	try {
	    
	    SmptPop3ServerService serverService = new SmptPop3ServerService();
	    
	    String userName =  configUtility.loadServerProperties().getProperty("mail.user");
	   
	    return serverService.obtenerServidor(userName);
	    
	} catch (Exception e) {
	    throw new ConnectionConfigException("No se pudo obtener el server actualmente configurado para conectar");
	}
	
    }


    public static void setConnection(SmtpPop3Server server) throws ConnectionConfigException {
	try {
	    configUtility.setServerProperties(server);
	    panelManager.getPanelEmailLista().actualizarTabla();
	    panelManager.actualizarArbol();
	} catch (Exception e) {
	    throw new ConnectionConfigException("No se pudo setear la configuración de conexión al server");
	}

    }


    public static PanelManager getPanelManager() {
	return panelManager;
    }


    public static void setPanelManager(PanelManager panelManager) {
	ConnectionConfig.panelManager = panelManager;
    }
    
    

    

}
