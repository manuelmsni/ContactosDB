/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.utils;

/**
 *
 * @author Vespertino
 */
import app.models.Contact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConection {
    String bdUrl = Const.DBURL;
    Connection con;
    public DBConection(){
        try {
            con = DriverManager.getConnection(bdUrl);
            System.out.println("Connected!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT id, nombre, telefono FROM contactos";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int telefono = resultSet.getInt("telefono");
                Contact contact = new Contact(id, nombre, telefono);
                contacts.add(contact);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contacts;
    }
}
