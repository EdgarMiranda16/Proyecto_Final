package controladores;

import modelos.Perfil;
import modelos.dto.Response;
import persistencia.impl.PerfilImpl;
import vistas.modales.ModalPerfil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorPerfil implements ActionListener {

    public PerfilImpl perfilImpl = new PerfilImpl();
    public ModalPerfil modalPerfil;

    public ControladorPerfil(ModalPerfil modal) {
        this.modalPerfil = modal;

        List<Perfil> datos = this.registros();

        this.modalPerfil.pintar(datos);
        this.modalPerfil.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public List<Perfil> registros() {
        Response<List<Perfil>> respuesta = this.perfilImpl.list();

        switch (respuesta.getStatus()) {
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(null, respuesta.getMessage());
            }
        }

        return respuesta.getData();
    }
}
