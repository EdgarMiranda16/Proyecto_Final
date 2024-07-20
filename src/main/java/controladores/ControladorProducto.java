package controladores;

import modelos.Producto;
import modelos.dto.Response;
import persistencia.impl.ProductoImpl;
import vistas.secundarias.VistaProducto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ControladorProducto implements ActionListener {

    private VistaProducto vistaProducto;
    private ProductoImpl productoImpl = new ProductoImpl();

    public ControladorProducto() {
    }

    public ControladorProducto(VistaProducto view) {
        this.vistaProducto = view;
        this.vistaProducto.btnRegistrar.addActionListener(this);
        this.vistaProducto.btnActualizar.addActionListener(this);
        this.vistaProducto.btnEliminar.addActionListener(this);

        List<Producto> datos = this.registros();
        this.vistaProducto.pintar(datos);
        this.vistaProducto.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaProducto.btnRegistrar) {
            Optional<Producto> salida = this.vistaProducto.agregar();

            if (salida.isPresent()) {
                this.agregar(salida.get());
            }
        } else if (e.getSource() == this.vistaProducto.btnActualizar) {
            Optional<Producto> salida = this.vistaProducto.actualizar();

            if (salida.isPresent()) {
                this.actualizar(salida.get());
            }
        } else if (e.getSource() == this.vistaProducto.btnEliminar) {
            Optional<Producto> salida = this.vistaProducto.eliminar();

            if (salida.isPresent()) {
                this.eliminar(salida.get());
            }
        }
    }

    public List<Producto> registros() {
        Response<List<Producto>> respuesta = this.productoImpl.list();

        switch (respuesta.getStatus()) {
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaProducto, respuesta.getMessage());
            }
        }

        return respuesta.getData();
    }

    public void agregar(Producto croducto) {
        Response<Producto> respuesta = this.productoImpl.insert(croducto);
        respuesta(respuesta);
    }

    public void actualizar(Producto croducto) {
        Response<Producto> respuesta = this.productoImpl.update(croducto);
        respuesta(respuesta);
    }

    public void eliminar(Producto croducto) {
        Response<Producto> respuesta = this.productoImpl.delete(croducto);
        respuesta(respuesta);
    }

    private void respuesta(Response<Producto> respuesta) {
        switch (respuesta.getStatus()) {
            case SUCCESS -> {
                this.vistaProducto.txtProducto.setText("");
                this.vistaProducto.txtPrecio.setText("");
                this.vistaProducto.txtStock.setText("");
                this.vistaProducto.txtDescripcion.setText("");

                List<Producto> datos = this.registros();
                this.vistaProducto.pintar(datos);
                JOptionPane.showMessageDialog(this.vistaProducto, respuesta.getMessage());
            }
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaProducto, respuesta.getMessage());
            }
        }
    }
}
