package controladores;

import modelos.DetalleVenta;
import modelos.Producto;
import modelos.Venta;
import modelos.dto.Response;
import persistencia.impl.DetalleVentaImpl;
import persistencia.impl.ProductoImpl;
import persistencia.impl.VentaImpl;
import vistas.secundarias.VistaUsuario;
import vistas.secundarias.VistaVenta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ControladorVenta implements ActionListener {

    private VistaVenta vistaVenta;
    private VentaImpl ventaImpl = new VentaImpl();
    private DetalleVentaImpl detalleVentaImpl = new DetalleVentaImpl();
    private ProductoImpl productoImpl = new ProductoImpl();
    private ControladorProducto controladorProducto = new ControladorProducto();

    public ControladorVenta(VistaVenta view) {
        this.vistaVenta = view;
        this.vistaVenta.btnRegistrar.addActionListener(this);

        List<Producto> datos = controladorProducto.registros();
        this.vistaVenta.pintar(datos);
        this.vistaVenta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaVenta.btnRegistrar) {
            Optional<Venta> salida = this.vistaVenta.agregar();

            if (salida.isPresent()) {
                this.agregar(salida.get());
            }
        }
    }

    public List<Venta> registros() {
        Response<List<Venta>> respuesta = this.ventaImpl.list();

        switch (respuesta.getStatus()) {
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaVenta, respuesta.getMessage());
            }
        }

        return respuesta.getData();
    }

    public void agregar(Venta producto) {
        Response<Venta> respuesta = this.ventaImpl.insert(producto);
        switch (respuesta.getStatus()) {
            case SUCCESS -> {
                respuesta.getData().getDetalleVenta().forEach(detalleVenta -> {
                    detalleVenta.setVenta(respuesta.getData());
                    this.detalleVentaImpl.insert(detalleVenta);
                    this.productoImpl.updateStock(detalleVenta.getProducto());
                });

                VistaVenta.isSelectedCliente = Optional.empty();
                VistaVenta.txtCliente.setText("");

                List<Producto> datos = controladorProducto.registros();
                this.vistaVenta.pintar(datos);
                JOptionPane.showMessageDialog(this.vistaVenta, respuesta.getMessage());
            }
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaVenta, respuesta.getMessage());
            }
        }
    }
}
