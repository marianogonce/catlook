package userInterface;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import entidades.Email;
import exeptions.ServiceEmailException;
import userInterface.receiverUI.JOptionPaneScrollTextMessage;

public class SelectionListener implements TreeSelectionListener  {
    
            private PanelManager panelManager;
    

            @Override
            public void valueChanged(TreeSelectionEvent se) {
	    JTree tree = (JTree) se.getSource();
	    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
	        .getLastSelectedPathComponent();
	    
	    if (selectedNode!=null) {
		String selectedNodeName = selectedNode.toString();
		    if (selectedNode.isLeaf()) {
			     
			Email email = (Email)selectedNode.getUserObject();
			new JOptionPaneScrollTextMessage(email.getContent().replaceAll("\\n\\n","\\n"));
			

		    }
	    
	    
	    }
	    
	    

	  }
}


