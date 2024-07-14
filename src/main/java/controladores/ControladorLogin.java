package controladores;

import modelos.Usuario;
import persistencia.impl.UsuarioImpl;
import utilidades.Validacion;
import vistas.principales.VistaMainAdministrador;
import vistas.principales.VistaMainGerente;
import vistas.principales.VistaMainVendedor;
import vistas.secundarias.VistaLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {

    private VistaLogin vistaLogin;
    private UsuarioImpl usuarioImpl = new UsuarioImpl();

    public ControladorLogin() {
        this(new VistaLogin());
        this.vistaLogin.setLocationRelativeTo(null);
        this.vistaLogin.setResizable(false);
        this.vistaLogin.setVisible(true);
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

        var respuesta = usuarioImpl.iniciarSesion(Usuario.builder()
                .usuario(usuario)
                .password(password)
                .build());

        switch (respuesta.getStatus()) {
            case SUCCESS -> {
                String perfil = respuesta.getData()
                        .getPerfil()
                        .getNombrePerfil()
                        .toUpperCase();

                switch (perfil) {
                    case "ADMINISTRADOR" -> new VistaMainAdministrador().setVisible(true);
                    case "GERENTE" -> new VistaMainGerente().setVisible(true);
                    case "VENDEDOR" -> new VistaMainVendedor().setVisible(true);
                    default -> JOptionPane.showMessageDialog(this.vistaLogin, "Perfil no contemplado...!");
                }
                this.vistaLogin.dispose();
            }
            case WARNING, ERROR -> JOptionPane.showMessageDialog(this.vistaLogin, respuesta.getMessage());
        }
    }
}
