package com.example.actividadparejas5.controllers;

import com.example.actividadparejas5.models.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class DetalleClienteController {

    @FXML
    private BorderPane panelRaiz;

    @FXML
    private Label lblNombres;
    @FXML
    private Label lblApellidos;
    @FXML
    private Label lblTipoCliente;
    @FXML
    private Label lblCiudad;
    @FXML
    private Label lblFechaNacimiento;
    @FXML
    private Label lblTipoSolicitud;

    @FXML
    private ImageView imgFotografia;
    @FXML
    private ListView<String> lstServicios;

    @FXML
    private Button btnGuardarImagen;
    @FXML
    private Button btnCerrar;

    // No lleva @FXML: lo llama ConsultaClientesController despues de loader.load()
    public void recibirCliente(Cliente cliente) {
        lblNombres.setText(cliente.getNombres());
        lblApellidos.setText(cliente.getApellidos());
        lblTipoCliente.setText(cliente.getTipoCliente());
        lblCiudad.setText(cliente.getCiudad());
        lblFechaNacimiento.setText(cliente.getFechaNacimiento().toString());
        lblTipoSolicitud.setText(cliente.getTipoSolicitud());
    }

    @FXML
    private void onGuardarImagen(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccione la carpeta de destino");
        File carpeta = directoryChooser.showDialog(btnGuardarImagen.getScene().getWindow());

        if (carpeta != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Carpeta seleccionada");
            alert.setHeaderText(null);
            alert.setContentText("Se guardara en: " + carpeta.getAbsolutePath());
            alert.showAndWait();
        }
    }

    @FXML
    private void onTeclaPresionada(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            cerrarVentana();
        }
    }

    @FXML
    private void onCerrar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}
