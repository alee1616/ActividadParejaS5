package com.example.actividadparejas5.controllers;

import com.example.actividadparejas5.models.Cliente;
import com.example.actividadparejas5.models.GestorDatos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsultaClientesController {

    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, String> colNombreCompleto;
    @FXML
    private TableColumn<Cliente, String> colTipoCliente;
    @FXML
    private TableColumn<Cliente, String> colCiudad;
    @FXML
    private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML
    private TableColumn<Cliente, String> colTipoSolicitud;

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnVerDetalle;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblTotalRegistros;

    @FXML
    public void initialize() {
        colNombreCompleto.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colTipoSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));

        tblClientes.setItems(GestorDatos.getClientes());
        lblTotalRegistros.setText("Total de registros: " + GestorDatos.getClientes().size());
    }

    @FXML
    private void onVerDetalle(ActionEvent event) {
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Debe seleccionar un cliente de la tabla.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/actividadparejas5/detalleCliente.fxml"));
            Parent root = loader.load();

            DetalleClienteController controladorDetalle = loader.getController();
            controladorDetalle.recibirCliente(clienteSeleccionado);

            Stage stage = new Stage();
            stage.setTitle("Detalle del Cliente");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de detalles.");
        }
    }

    @FXML
    private void onRegresar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/actividadparejas5/principal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEliminar(ActionEvent event) {
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            GestorDatos.getClientes().remove(clienteSeleccionado);
            lblTotalRegistros.setText("Total de registros: " + GestorDatos.getClientes().size());
        }
    }

    @FXML
    private void onClicTabla(MouseEvent event) {
        // ejecuta la acción de ver detalle si se hace doble clic en un registro
        if (event.getClickCount() == 2) {
            onVerDetalle(new ActionEvent());
        }
    }

    @FXML
    private void onBuscar(KeyEvent event) {
        String filtro = txtBuscar.getText().toLowerCase();
        ObservableList<Cliente> filtrados = FXCollections.observableArrayList();

        for (Cliente c : GestorDatos.getClientes()) {
            if (c.getNombreCompleto().toLowerCase().contains(filtro) ||
                    c.getCiudad().toLowerCase().contains(filtro)) {
                filtrados.add(c);
            }
        }

        // este muestra solo los clientes que coinciden con el texto ingresado
        tblClientes.setItems(filtrados);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}