package userInterface.contactosUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Contacto;

public class ContactoTableModel extends AbstractTableModel {

    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_EMAIL = 2;
    private static final int COLUMNA_TELEFONO = 3;

    private String[] nombresColumnas = { "Contactos ID", "Nombre del Contacto", "Email del Contacto", "Teléfono del Contacto" };

    private Class[] tiposColumnas = {Integer.class , String.class, String.class, String.class};

    private List<Contacto> contenido;

    public ContactoTableModel() {
	contenido = new ArrayList<Contacto>();
    }

    public ContactoTableModel(List<Contacto> contenidoInicial) {
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

	Contacto contacto = contenido.get(rowIndex);

	Object result = null;

	switch (columnIndex) {
	case COLUMNA_ID:
	    result = contacto.getId();
	    break;
	case COLUMNA_NOMBRE:
	    result = contacto.getNombre();
	    break;
	case COLUMNA_EMAIL:
	    result = contacto.getEmail();
	    break;
	case COLUMNA_TELEFONO:
	    result = contacto.getTelefono();
	    break;
	 default:
	     result= new String("");
	}

	return result;
    }

    public List<Contacto> getContenido() {
	return this.contenido;
    }

    public void setContenido(List<Contacto> contenido) {
	this.contenido = contenido;
    }

}
