package com.example.actividadparejas5.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblMensaje;

    @FXML
    private void onIniciarSesion(ActionEvent event) {
        if (txtUsuario.getText().isBlank() || txtContrasena.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Los campos no pueden estar vacíos.");
            lblMensaje.setText("Faltan credenciales.");
        } else {
            lblMensaje.setText("Acceso correcto.");
            abrirVentanaPrincipal();
        }
    }

    @FXML
    private void onSalir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro que deseas salir del sistema?");
        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    private void onEnterUsuario(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            txtContrasena.requestFocus();
        }
    }

    @FXML
    private void onEnterContrasena(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onIniciarSesion(new ActionEvent());
        }
    }

    private void abrirVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/actividadparejas5/principal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana principal.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}