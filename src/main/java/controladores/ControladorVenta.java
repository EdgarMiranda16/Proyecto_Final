package controladores;

import modelos.DetalleVenta;
import modelos.Producto;
import modelos.Venta;
import modelos.dto.Response;
import persistencia.impl.DetalleVentaImpl;
import persistencia.impl.ProductoImpl;
import persistencia.impl.VentaImpl;
import utilidades.Exportar;
import vistas.secundarias.VistaVenta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import modelos.Usuario;
import vistas.secundarias.VistaReporteVenta;

public class ControladorVenta implements ActionListener {

    private VistaVenta vistaVenta;
    private VistaReporteVenta vistaReporteVenta;
    private VentaImpl ventaImpl = new VentaImpl();
    private DetalleVentaImpl detalleVentaImpl = new DetalleVentaImpl();
    private ProductoImpl productoImpl = new ProductoImpl();
    private ControladorProducto controladorProducto = new ControladorProducto();
    private Usuario usuario;

    public ControladorVenta(VistaVenta view, Usuario usuario) {
        this.vistaVenta = view;
        this.vistaVenta.btnRegistrar.addActionListener(this);
        this.usuario = usuario;

        List<Producto> datos = controladorProducto.registros();
        this.vistaVenta.pintar(datos);
        this.vistaVenta.setVisible(true);
    }

    public ControladorVenta(VistaReporteVenta view) {
        this.vistaReporteVenta = view;
        this.vistaReporteVenta.btnExportar.addActionListener(this);

        List<DetalleVenta> datos = this.registros();
        this.vistaReporteVenta.pintar(datos);
        this.vistaReporteVenta.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.vistaVenta != null && e.getSource() == this.vistaVenta.btnRegistrar) {
            Optional<Venta> salida = this.vistaVenta.agregar();

            if (salida.isPresent()) {
                this.agregar(salida.get());
            }
        }

        if (this.vistaReporteVenta != null && e.getSource() == this.vistaReporteVenta.btnExportar) {
            Predicate<DetalleVenta> filtro = this.vistaReporteVenta.exportar();
            this.exportar(filtro);
        }
    }

    public List<DetalleVenta> registros() {
        Response<List<DetalleVenta>> respuesta = this.detalleVentaImpl.list();

        switch (respuesta.getStatus()) {
            case WARNING, ERROR -> {
                JOptionPane.showMessageDialog(this.vistaVenta, respuesta.getMessage());
            }
        }

        return respuesta.getData();
    }

    public void agregar(Venta venta) {
        venta.setUsuario(usuario);
        Response<Venta> respuesta = this.ventaImpl.insert(venta);
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

    public void exportar(Predicate<DetalleVenta> filtro) {
        List<DetalleVenta> datos = this.registros();
        List<DetalleVenta> filtrados = datos.stream().filter(filtro).toList();
        Exportar.exportarACSV(filtrados);
    }

}
