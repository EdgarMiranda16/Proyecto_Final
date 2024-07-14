package controladores;

import modelos.Usuario;
import modelos.dto.Response;
import persistencia.impl.UsuarioImpl;
import vistas.secundarias.VistaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ControladorUsuario implements ActionListener {

    private VistaUsuario vistaUsuario;
    private UsuarioImpl usuarioImpl = new UsuarioImpl();

    public ControladorUsuario(VistaUsuario view) {
        this.vistaUsuario = view;
        this.vistaUsuario.btnRegistrar.addActionListener(this);
        this.vistaUsuario.btnActualizar.addActionListener(this);
        this.vistaUsuario.btnEliminar.addActionListener(this);

        List<Usuario> datos = this.registros();
        this.vistaUsuario.pintar(datos);
        this.vistaUsuario.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaUsuario.btnRegistrar) {
            Optional<Usuario> salida = this.vistaUsuario.agregar();

            if (salida.isPresent()) {
                this.agregar(salida.get());
            }
        } else if (e.getSource() == this.vistaUsuario.btnActualizar) {
            Optional<Usuario> salida = this.vistaUsuario.actualizar();

            if (salida.isPresent()) {
                this.actualizar(salida.get());
            }
        } else if (e.getSource() == this.vistaUsuario.btnEliminar) {
            Optional<Usuario> salida = this.vistaUsuario.eliminar();

            if (salida.isPresent()) {
                this.eliminar(salida.get());
            }
        }
    }

    public List<Usuario> registros() {
        Response<List<Usuario>> respuesta = this.usuarioImpl.list();

        switch (respuesta.getStatus()) {
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaUsuario, respuesta.getMessage());
            }
        }

        return respuesta.getData();
    }

    public void agregar(Usuario usuario) {
        Response<Usuario> respuesta = this.usuarioImpl.insert(usuario);
        respuesta(respuesta);
    }

    public void actualizar(Usuario usuario) {
        Response<Usuario> respuesta = this.usuarioImpl.update(usuario);
        respuesta(respuesta);
    }

    public void eliminar(Usuario usuario) {
        Response<Usuario> respuesta = this.usuarioImpl.delete(usuario);
        respuesta(respuesta);
    }

    private void respuesta(Response<Usuario> respuesta) {
        switch (respuesta.getStatus()) {
            case SUCCESS -> {
                VistaUsuario.isSelectedPerfil = Optional.empty();
                VistaUsuario.txtPerfil.setText("");
                this.vistaUsuario.txtUsuario.setText("");
                this.vistaUsuario.txtPassword.setText("");
                this.vistaUsuario.txtEmail.setText("");

                List<Usuario> datos = this.registros();
                this.vistaUsuario.pintar(datos);
                JOptionPane.showMessageDialog(this.vistaUsuario, respuesta.getMessage());
            }
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaUsuario, respuesta.getMessage());
            }
        }
    }
}
