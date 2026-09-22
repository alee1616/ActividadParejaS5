package com.example.actividadparejas5.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestorDatos {

    private static final ObservableList<Cliente> clientesLista = FXCollections.observableArrayList();
    public static ObservableList<Cliente> getClientes() {
        return clientesLista;
    }

    public static void agregarCliente(Cliente cliente) {
        clientesLista.add(cliente);
    }
}