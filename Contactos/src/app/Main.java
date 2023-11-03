/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import app.controllers.CRUD_View_Controller;
import app.views.CRUD_View;

/**
 *
 * @author Vespertino
 */
public class Main {

    public static void main(String[] args) {
        CRUD_View crudView = new CRUD_View();
        CRUD_View_Controller crudCon = new CRUD_View_Controller(crudView);
        crudCon.innitView();
        crudView.setVisible(true);
    }
    
}
