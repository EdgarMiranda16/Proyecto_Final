/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelos.DetalleVenta;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.swing.*;

/**
 * @author kei40
 */
public class Exportar {

    public static void exportarACSV(List<DetalleVenta> productos) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo CSV");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (FileWriter out = new FileWriter(fileChooser.getSelectedFile() + ".csv");
                 CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Cliente", "Precio", "Cantidad", "Total", "Usuario Registro", "Fecha Venta"))) {
                for (DetalleVenta producto : productos) {
                    printer.printRecord(producto.getVenta().getId(),
                            producto.getVenta().getCliente().getApellidos().concat(" ").concat(producto.getVenta().getCliente().getNombres()),
                            producto.getPrecioUnitario(),
                            producto.getCantidad(),
                            producto.getSubTotal(),
                            producto.getVenta().getUsuario().getUsuario(),
                            producto.getVenta().getFechaVenta());
                }
                JOptionPane.showMessageDialog(null, "Reporte exportado correctamente");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al escribir el archivo: " + e.getMessage());
            }
        }
    }

}
