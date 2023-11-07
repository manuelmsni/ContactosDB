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
    
    public boolean addContact(String name, int number){
        String query = "INSERT INTO contactos (nombre, telefono) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, number);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteContact(int id){
        String query = "DELETE FROM contactos WHERE id IS ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateContact(int id, String name, int number) {
        String query = "UPDATE contactos SET nombre = ?, telefono = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, number);
            preparedStatement.setInt(3, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
   
    public int sice() {
        String query = "SELECT c.id FROM contactos c ORDER BY c.id DESC LIMIT 1";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int lastId = resultSet.getInt("id");
                return lastId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
