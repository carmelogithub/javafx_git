package com.empresa.javafx_jdbc_sp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField nombre;
    @FXML
    private TextField ciudad;
    @FXML
    private TextField facturacion;


    @FXML
    protected void onHelloButtonClick() {
       // welcomeText.setText("Welcome to JavaFX Application!");
        String url = "jdbc:mysql://localhost:3306/test"; // Cambia esto si tu URL es diferente
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion= DriverManager.getConnection(url,user,password);
           //String insertar="insert into clientesfx (nombre,ciudad,facturacion) values (?,?,?)";
            String insertarsp="CALL `sp_add_clientesfx`(?, ?, ?);";
            //PreparedStatement ps=conexion.prepareStatement(insertar);
            CallableStatement cs=conexion.prepareCall(insertarsp);
            cs.setString(1, nombre.getText());
            cs.setString(2 ,ciudad.getText());
            cs.setString(3, facturacion.getText());
            int filas=cs.executeUpdate();
            System.out.println("registros guardados "+filas);
            welcomeText.setText("Cliente guardado!");

            nombre.clear();
            ciudad.clear();
            facturacion.clear();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}