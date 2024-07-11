package controladores;

import constantes.EstadoResponse;
import persistencia.dto.Response;
import utilidades.Validacion;
import vistas.principales.VistaMainAdministrador;
import vistas.principales.VistaMainGerente;
import vistas.principales.VistaMainVendedor;
import vistas.secundarias.VistaLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {

    public VistaLogin vistaLogin;

    public ControladorLogin() {
        this(new VistaLogin());
        this.vistaLogin.setLocationRelativeTo(null);
        this.vistaLogin.setResizable(false);
    }

    public ControladorLogin(VistaLogin view) {
        this.vistaLogin = view;
        this.vistaLogin.btnIngresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaLogin.btnIngresar) {
            this.iniciarSesion();
        }
    }

    public void iniciarSesion() {
        String usuario = this.vistaLogin.txtUsuario.getText();
        String password = String.valueOf(this.vistaLogin.txtPassword.getPassword());

        if (!Validacion.validarVacios(usuario, password)) {
            JOptionPane.showMessageDialog(this.vistaLogin, "Faltan datos...!");
            return;
        }

        var respuesta = Response.builder()
                .status(EstadoResponse.SUCCESS)
                .build();

        switch (respuesta.getStatus()) {
            case SUCCESS -> {
                var perfil = "ADMINISTRADOR";

                switch (perfil) {
                    case "ADMINISTRADOR" ->
                        new VistaMainAdministrador().setVisible(true);
                    case "GERENTE" ->
                        new VistaMainGerente().setVisible(true);
                    case "VENDEDOR" ->
                        new VistaMainVendedor().setVisible(true);
                    default ->
                        JOptionPane.showMessageDialog(this.vistaLogin, "Perfil no contemplado...!");
                }
                this.vistaLogin.dispose();
            }
            case WARNING, ERROR ->
                JOptionPane.showMessageDialog(this.vistaLogin, respuesta.getMessage());
        }
    }
}
