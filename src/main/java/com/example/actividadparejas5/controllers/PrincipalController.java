package com.example.actividadparejas5.controllers;

import com.example.actividadparejas5.models.GestorDatos;
import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PrincipalController {

    @FXML
    private MenuBar menuBarPrincipal;
    @FXML
    private Menu menuArchivo;
    @FXML
    private MenuItem miRegistroCliente;
    @FXML
    private MenuItem miConsultaClientes;
    @FXML
    private MenuItem miCarpetaDestino;
    @FXML
    private MenuItem miCerrarSesion;
    @FXML
    private MenuItem miSalir;
    @FXML
    private Menu menuAyuda;
    @FXML
    private MenuItem miAcercaDe;

    @FXML
    private ToolBar toolBarPrincipal;
    @FXML
    private Button btnBarraRegistro;
    @FXML
    private Button btnBarraConsulta;

    @FXML
    private Button btnRegistroCliente;
    @FXML
    private Button btnConsultaClientes;
    @FXML
    private Label lblBienvenida;

    @FXML
    private ContextMenu ctxMenuPrincipal;
    @FXML
    private MenuItem cmiActualizar;
    @FXML
    private MenuItem cmiVerTotal;

    @FXML
    private VBox panelCentral;

    @FXML
    private void onAbrirRegistro(ActionEvent event) {
        cambiarEscena("registroCliente.fxml", "Registro de Cliente");
    }

    @FXML
    private void onAbrirConsulta(ActionEvent event) {
        cambiarEscena("consultaClientes.fxml", "Consulta de Clientes");
    }

    @FXML
    private void onSeleccionarCarpeta(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar la Carpeta Destino");
        File directorio = directoryChooser.showDialog(menuBarPrincipal.getScene().getWindow());

        if (directorio != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Carpeta Seleccionada", "Ruta: " + directorio.getAbsolutePath());
        }
    }

    @FXML
    private void onCerrarSesion(ActionEvent event) {
        cambiarEscena("login.fxml", "Inicio de Sesión");
    }

    @FXML
    private void onSalir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onAcercaDe(ActionEvent event) {
        mostrarAlerta(Alert.AlertType.INFORMATION, "Acerca de", "Sistema de Gestión de Clientes\nDesarrollado por Marian, Fabiola, Sara y Gabriela.");
    }

    @FXML
    private void onActualizar(ActionEvent event) {
        lblBienvenida.setText(lblBienvenida.getText());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Actualizado",
                "La informacion en pantalla fue actualizada.");
    }

    @FXML
    private void onVerTotalClientes(ActionEvent event) {
        int total = GestorDatos.getClientes().size();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Total de clientes",
                "Clientes registrados: " + total);
    }

    // El VBox no es un Control, asi que no tiene propiedad contextMenu:
    // hay que mostrarlo a mano con las coordenadas de pantalla del evento.
    @FXML
    private void onMostrarContextMenu(ContextMenuEvent event) {
        ctxMenuPrincipal.show(panelCentral, event.getScreenX(), event.getScreenY());
    }

    private void cambiarEscena(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/actividadparejas5/" + fxml));
            Parent root = loader.load();
            Stage stage = (Stage) menuBarPrincipal.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
        } catch (IOException e) {
            e.printStackTrace();
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