package userInterface.receiverUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Email;
import entidades.SmtpPop3Server;

public class MailsTableModel extends AbstractTableModel {
    
    private static final int COLUMNA_SENDER= 0;
    private static final int COLUMNA_RECIPIENT = 1;
    private static final int COLUMNA_SUBJECT = 2;
    private static final int COLUMNA_SENDDATE = 3;
    private static final int COLUMNA_CONTENT = 4;


    private String[] nombresColumnas = { "Sender", "Recipient", "Subject", "Send Date", "Content" };

    private Class[] tiposColumnas = { String.class, String.class, String.class, String.class, String.class};

    private List<Email> contenido;

    public MailsTableModel() {
	contenido = new ArrayList<Email>();
    }

    public MailsTableModel(List<Email> contenidoInicial) {
	contenido = contenidoInicial;
    }

    // Métodos a sobrescribir de AbstractTableModel

    @Override
    public int getRowCount() {
	return contenido.size();
    }
    
    public String getColumnName(int col) {
	return this.nombresColumnas[col];
    }
    
    
    public Class getColumnClass(int col) {
	return this.tiposColumnas[col];
    }

    @Override
    public int getColumnCount() {
	return this.nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

	Email email = contenido.get(rowIndex);

	Object result = null;

	switch (columnIndex) {
	case COLUMNA_SENDER:
	    result = email.getSender();
	    break;
	case COLUMNA_RECIPIENT:
	    result = email.getRecipient();
	    break;
	case COLUMNA_SUBJECT:
	    result = email.getSubject();
	    break;
	case COLUMNA_SENDDATE:
	    result = email.getSentDate();
	    break;
	case COLUMNA_CONTENT:
	    result = email.getContent();
	    break;
	 default:
	     result= new String("");
	}

	return result;
    }

    public List<Email> getContenido() {
	return this.contenido;
    }

    public void setContenido(List<Email> contenido) {
	this.contenido = contenido;
    }

}
