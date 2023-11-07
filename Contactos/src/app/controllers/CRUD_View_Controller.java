/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controllers;

import app.models.Book;
import app.models.Contact;
import app.utils.DBConection;
import app.views.CRUD_View;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Vespertino
 */
public class CRUD_View_Controller{

    CRUD_View view;
    DBConection con;
    Book book;
    Contact selected;
    Integer selectedIndex;
    
    public CRUD_View_Controller(CRUD_View view){
        this.view = view;
        view.setLocationRelativeTo(null);
    }
    
    public void innitView(){
       initConectionButton();
    }
    
    private void flush(){
        view.getTxt_id().setText("");
        view.getTxt_nombre().setText("");
        view.getTxt_telefono().setText("");
        selected = null;
        selectedIndex = null;
    }
    
    private void refresh(){
        view.getTxt_id().setText(String.valueOf(selected.getId()));
        view.getTxt_nombre().setText(selected.getName());
        view.getTxt_telefono().setText(String.valueOf(selected.getNumber()));
    }
    
    private void initFunctionality(){
        initTable();
        initNewButton();
        initAddButton();
        initEditButton();
        initRemoveButton();
    }
    
    private void initTable(){
        JTable table = view.getTbl_contactos();
        table.setModel(book);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        view.getBtn_add().setEnabled(false);
                        view.getBtn_new().setEnabled(true);
                        selectedIndex = filaSeleccionada;
                        selected = book.getContact(filaSeleccionada);
                        refresh();
                    }
                }
            }
        });
    }
    
    private void initConectionButton(){
        view.getBtn_con().addActionListener((ActionEvent e) -> {
            if(con == null) con = new DBConection();
            List<Contact> contacts = con.getContacts();
            if(contacts != null) {
                view.getBtn_con().setEnabled(false);
                book = new Book(contacts);
                initFunctionality();
                view.getTxt_id().setText(String.valueOf(con.sice()+1));
            }
        });
    }
    
    private void initNewButton(){
        view.getBtn_new().addActionListener((ActionEvent e) -> {
            flush();
            view.getTxt_id().setText(String.valueOf(con.sice()+1));
            view.getBtn_add().setEnabled(true);
            view.getBtn_new().setEnabled(false);
        });
    }
    
    private void initAddButton(){
        view.getBtn_add().addActionListener((ActionEvent e) -> {
            try{
                int id = Integer.parseInt(view.getTxt_id().getText());
                String name = view.getTxt_nombre().getText();
                Integer number = Integer.parseInt(view.getTxt_telefono().getText());
                if (name != null && number != null){
                    if(con.addContact(name, number)) book.addContact(new Contact(id, name, number));
                    selectLast();
                    view.getBtn_add().setEnabled(false);
                    view.getBtn_new().setEnabled(true);
                }
            } catch (NumberFormatException ex){
                alert("No has introducido un número válido: \n" + ex.getMessage());
            }
        });
    }
    
    private void initEditButton(){
        view.getBtn_edit().addActionListener((ActionEvent e) -> {
            if (selected != null){
                String name = view.getTxt_nombre().getText();
                int number = Integer.parseInt(view.getTxt_telefono().getText());
                if(con.updateContact(selected.getId(), name, number)){
                    selected.setName(name);
                    selected.setNumber(number);
                    book.fireTableDataChanged();
                    view.getTbl_contactos().setRowSelectionInterval(selectedIndex, selectedIndex);
                }
            }
        });
    }
    
    private void selectLast(){
        JTable table = view.getTbl_contactos();
        int size = book.getRowCount() - 1;
        table.setRowSelectionInterval(size, size);
        selectedIndex = size;
        selected = book.getContact(size);
        refresh();
    }
    
    private void initRemoveButton(){
        view.getBtn_del().addActionListener((ActionEvent e) -> {
            if (selected != null){
                if(askYesNo("Vas a eliminar el contacto ¿Deseas continuar?")){
                    if(con.deleteContact(selected.getId())){
                        book.removeContact(selected);
                        flush();
                        selectLast();
                    }
                }
            }
        });
    }
    
    private boolean askYesNo(String messaje){
        int respuesta = JOptionPane.showConfirmDialog(null, messaje, "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    
    private void alert(String message){
        JOptionPane.showMessageDialog(null, message, "Alerta", JOptionPane.WARNING_MESSAGE);
    }
    

    
    
    
    
}
