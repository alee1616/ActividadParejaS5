package com.example.actividadparejas5.controllers;

import com.example.actividadparejas5.models.Cliente;
import com.example.actividadparejas5.models.GestorDatos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class RegistroClienteController {

    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private ComboBox<String> cmbTipoCliente;
    @FXML
    private ComboBox<String> cmbCiudad;
    @FXML
    private DatePicker dtpFechaNacimiento;

    @FXML
    private ToggleGroup tgTipoSolicitud;
    @FXML
    private RadioButton rbNueva;
    @FXML
    private RadioButton rbRenovacion;
    @FXML
    private RadioButton rbReclamo;

    @FXML
    private CheckBox chkInternet;
    @FXML
    private CheckBox chkTelefonia;
    @FXML
    private CheckBox chkTelevision;
    @FXML
    private CheckBox chkSoporte;

    @FXML
    private ImageView imgFotografia;

    @FXML
    private Button btnSeleccionarFoto;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;

    @FXML
    public void initialize() {
        cmbTipoCliente.getItems().addAll("Residencial", "Corporativo", "Gubernamental");
        cmbCiudad.getItems().addAll("Managua", "León", "Granada", "Estelí");
    }

    @FXML
    private void onSeleccionarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File archivo = fileChooser.showOpenDialog(btnSeleccionarFoto.getScene().getWindow());

        if (archivo != null) {
            imgFotografia.setImage(new Image(archivo.toURI().toString()));
        }
    }

    @FXML
    private void onGuardar(ActionEvent event) {
        if (txtNombres.getText().isBlank() || txtApellidos.getText().isBlank() ||
                cmbTipoCliente.getValue() == null || cmbCiudad.getValue() == null ||
                dtpFechaNacimiento.getValue() == null || tgTipoSolicitud.getSelectedToggle() == null) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Complete todos los campos obligatorios.");
            return;
        }

        RadioButton rbSeleccionado = (RadioButton) tgTipoSolicitud.getSelectedToggle();

        Cliente nuevoCliente = new Cliente(
                txtNombres.getText(),
                txtApellidos.getText(),
                cmbTipoCliente.getValue(),
                cmbCiudad.getValue(),
                dtpFechaNacimiento.getValue(),
                rbSeleccionado.getText()
        );

        GestorDatos.agregarCliente(nuevoCliente);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente registrado correctamente.");
        onLimpiar(null);
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        txtNombres.clear();
        txtApellidos.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbCiudad.getSelectionModel().clearSelection();
        dtpFechaNacimiento.setValue(null);
        tgTipoSolicitud.selectToggle(null);
        chkInternet.setSelected(false);
        chkTelefonia.setSelected(false);
        chkTelevision.setSelected(false);
        chkSoporte.setSelected(false);
        imgFotografia.setImage(null);
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/actividadparejas5/principal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSoloLetras(KeyEvent event) {
        String caracter = event.getCharacter();
        // si el caracter ingresado no es una letra o un espacio, lo bloqueara
        if (!caracter.matches("[A-Za-zÑñáéíóúÁÉÍÓÚ ]")) {
            event.consume(); // Cancela la pulsación de la tecla
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