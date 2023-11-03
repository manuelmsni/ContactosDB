/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.models;

/**
 *
 * @author Vespertino
 */
public class Contact {
    
    private int id;
    private String name;
    private int number;
    
    public Contact(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
    
    public Contact(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int telefono) {
        this.number = telefono;
    }
    
}
