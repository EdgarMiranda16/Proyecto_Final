package controladores;

import modelos.Cliente;
import modelos.dto.Response;
import persistencia.impl.ClienteImpl;
import vistas.secundarias.VistaCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import vistas.modales.ModalCliente;

public class ControladorCliente implements ActionListener {

    private VistaCliente vistaCliente;
    private ClienteImpl clienteImpl = new ClienteImpl();
    
    public ModalCliente modalCliente;
    
    public ControladorCliente(ModalCliente modal) {
        this.modalCliente = modal;

        List<Cliente> datos = this.registros();

        this.modalCliente.pintar(datos);
        this.modalCliente.setVisible(true);
    }

    public ControladorCliente(VistaCliente view) {
        this.vistaCliente = view;
        this.vistaCliente.btnRegistrar.addActionListener(this);
        this.vistaCliente.btnActualizar.addActionListener(this);
        this.vistaCliente.btnEliminar.addActionListener(this);

        List<Cliente> datos = this.registros();
        this.vistaCliente.pintar(datos);
        this.vistaCliente.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaCliente.btnRegistrar) {
            Optional<Cliente> salida = this.vistaCliente.agregar();

            if (salida.isPresent()) {
                this.agregar(salida.get());
            }
        } else if (e.getSource() == this.vistaCliente.btnActualizar) {
            Optional<Cliente> salida = this.vistaCliente.actualizar();

            if (salida.isPresent()) {
                this.actualizar(salida.get());
            }
        } else if (e.getSource() == this.vistaCliente.btnEliminar) {
            Optional<Cliente> salida = this.vistaCliente.eliminar();

            if (salida.isPresent()) {
                this.eliminar(salida.get());
            }
        }
    }

    public List<Cliente> registros() {
        Response<List<Cliente>> respuesta = this.clienteImpl.list();

        switch (respuesta.getStatus()) {
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaCliente, respuesta.getMessage());
            }
        }

        return respuesta.getData();
    }

    public void agregar(Cliente cliente) {
        Response<Cliente> respuesta = this.clienteImpl.insert(cliente);
        respuesta(respuesta);
    }

    public void actualizar(Cliente cliente) {
        Response<Cliente> respuesta = this.clienteImpl.update(cliente);
        respuesta(respuesta);
    }

    public void eliminar(Cliente cliente) {
        Response<Cliente> respuesta = this.clienteImpl.delete(cliente);
        respuesta(respuesta);
    }

    private void respuesta(Response<Cliente> respuesta) {
        switch (respuesta.getStatus()) {
            case SUCCESS -> {
                this.vistaCliente.txtNombres.setText("");
                this.vistaCliente.txtApellidos.setText("");
                this.vistaCliente.txtTelefono.setText("");
                this.vistaCliente.txtEmail.setText("");
                this.vistaCliente.txtDireccion.setText("");

                List<Cliente> datos = this.registros();
                this.vistaCliente.pintar(datos);
                JOptionPane.showMessageDialog(this.vistaCliente, respuesta.getMessage());
            }
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaCliente, respuesta.getMessage());
            }
        }
    }
}
