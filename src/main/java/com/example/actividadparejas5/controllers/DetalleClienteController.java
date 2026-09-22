package com.example.actividadparejas5.controllers;

import com.example.actividadparejas5.models.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetalleClienteController {

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
    private Button btnCerrar;

    public void recibirCliente(Cliente cliente) {
        lblNombres.setText(cliente.getNombres());
        lblApellidos.setText(cliente.getApellidos());
        lblTipoCliente.setText(cliente.getTipoCliente());
        lblCiudad.setText(cliente.getCiudad());
        lblFechaNacimiento.setText(cliente.getFechaNacimiento().toString());
        lblTipoSolicitud.setText(cliente.getTipoSolicitud());
    }

    @FXML
    private void onCerrar(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}