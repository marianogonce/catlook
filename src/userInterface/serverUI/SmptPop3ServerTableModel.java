package userInterface.serverUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.SmtpPop3Server;

public class SmptPop3ServerTableModel extends AbstractTableModel {
    
    private static final int COLUMNA_USERNAME = 0;
    private static final int COLUMNA_PASSWORD = 1;
    private static final int COLUMNA_SMTPHOST = 2;
    private static final int COLUMNA_SMTPPORT = 3;
    private static final int COLUMNA_POP3HOST = 4;
    private static final int COLUMNA_POP3PORT = 5;

    private String[] nombresColumnas = { "Nombre de Usuario", "Password", "SMPT HOST", "SMPT PORT", "POP3 HOST", "POP3 PORT" };

    private Class[] tiposColumnas = { String.class, String.class, String.class, String.class, String.class, String.class};

    private List<SmtpPop3Server> contenido;

    public SmptPop3ServerTableModel() {
	contenido = new ArrayList<SmtpPop3Server>();
    }

    public SmptPop3ServerTableModel(List<SmtpPop3Server> contenidoInicial) {
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

	SmtpPop3Server servidor = contenido.get(rowIndex);

	Object result = null;

	switch (columnIndex) {
	case COLUMNA_USERNAME:
	    result = servidor.getUserName();
	    break;
	case COLUMNA_PASSWORD:
	    result = servidor.getPassword();
	    break;
	case COLUMNA_SMTPHOST:
	    result = servidor.getSmptHost();
	    break;
	case COLUMNA_SMTPPORT:
	    result = servidor.getSmptPort();
	    break;
	case COLUMNA_POP3HOST:
	    result = servidor.getPop3Host();
	    break;
	case COLUMNA_POP3PORT:
	    result = servidor.getPop3Port();
	    break;
	 default:
	     result= new String("");
	}

	return result;
    }

    public List<SmtpPop3Server> getContenido() {
	return this.contenido;
    }

    public void setContenido(List<SmtpPop3Server> contenido) {
	this.contenido = contenido;
    }

}
