package userInterface;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import entidades.Email;
import exeptions.ServiceEmailException;
import service.EmailService;

public class TreeEmails extends JTree  {
    
        private DefaultMutableTreeNode bandeja;
	private DefaultMutableTreeNode enviado;
	DefaultTreeModel modelo;
	private DefaultMutableTreeNode recibido;
	
	private PanelManager panelManager;
	
	private EmailService emailService = new EmailService();
	
	private List<Email> listaDeEmails;
    
	public TreeEmails() {
	    super();
	    armarArbol();
	    
	}
    

	public JScrollPane armarArbol() {
	    
	        try {
		    setListaDeEmails(emailService.listaDeEmails());
		} catch (ServiceEmailException e) {
		    e.printStackTrace();
		}
	    
		DefaultMutableTreeNode bandeja = new DefaultMutableTreeNode("Bandejas");
		DefaultMutableTreeNode enviado = new DefaultMutableTreeNode("Enviados");
		DefaultMutableTreeNode recibido = new DefaultMutableTreeNode("Recibidos");
		
		
		modelo = new DefaultTreeModel(bandeja);
		
		 modelo.insertNodeInto(enviado, bandeja, 0);
		 
		 modelo.insertNodeInto(recibido, bandeja, 1);
		
		agregarNodos("recibido", recibido);
		agregarNodos("enviado", enviado);
		
		JTree tree = new JTree(modelo);
		
		tree.addTreeSelectionListener(new SelectionListener());
	        
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		JScrollPane scrollPaneParaArbol= new JScrollPane(tree);
		
	        return scrollPaneParaArbol;
	}
	
	private void agregarNodos(String status, DefaultMutableTreeNode node) {
	    int order = 0;
	    for (int i = 0; i < getListaDeEmails().size(); i++) {
		if (getListaDeEmails().get(i).getStatus().equals(status)) {
		    DefaultMutableTreeNode mail = new DefaultMutableTreeNode(getListaDeEmails().get(i));
		    modelo.insertNodeInto(mail, node, order);
		    order++;
		}
	    }
	}
	


	public List<Email> getListaDeEmails() {
	    return listaDeEmails;
	}


	public void setListaDeEmails(List<Email> listaDeEmails) {
	    this.listaDeEmails = listaDeEmails;
	}

	

}
