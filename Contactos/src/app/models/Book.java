/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vespertino
 */
public class Book extends AbstractTableModel{
    
    List<Contact> contacts;
    private String[] columnNames = {"ID", "Nombre", "Teléfono"};

    public Book(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    public List<Contact> getContacts(){
        return contacts;
    }
    
    public boolean addContact(Contact c){
        if(c == null) return false;
        if(contacts.contains(c)) return false;
        boolean success = contacts.add(c);
        if(success) fireTableDataChanged();
        return success;
    }
    
    public Contact getContact(int number){
        return contacts.get(number);
    }
    
    public boolean removeContact(Contact c){
        if(c == null) return false;
        boolean success = contacts.remove(c);
        if(success) fireTableDataChanged();
        return success;
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contacto = contacts.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return contacto.getId();
            case 1:
                return contacto.getName();
            case 2:
                return contacto.getNumber();
            default:
                return null;
        }
    }
    
}
