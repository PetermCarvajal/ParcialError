package Service;

import entity.Producto;
import Respository.ProductosRepository;

import javax.swing.*;
import java.math.BigDecimal;

public class Main {

    private static final ProductosRepository productosRepository = new ProductosRepository();

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(null, """
                    ---CRUD DE LAPTOPS---
                    1. Crear Laptop
                    2. Leer Laptop
                    3. Actualizar Laptop
                    4. Eliminar Laptop
                    5. Lista de Laptops
                    6. Salir
                    """);

            switch (opcion) {
                case "1" -> crearLaptop();
                case "2" -> leerLaptop();
                case "3" -> actualizarLaptop();
                case "4" -> eliminarLaptop();
                case "5" -> listaLaptops();
                case "6" -> salir = true;
                default -> JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }

        productosRepository.cerrar(); // Cerrar conexión cuando finaliza el programa
    }

    public static void crearLaptop() {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la laptop:");
            String procesador = JOptionPane.showInputDialog("Ingrese el procesador:");
            int ram = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de RAM (GB):"));
            int almacenamiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad de almacenamiento (GB):"));
            BigDecimal precio = new BigDecimal(JOptionPane.showInputDialog("Ingrese el precio:"));

            Producto laptop = new Producto();
            laptop.setNombre(nombre);
            laptop.setProcesador(procesador);
            laptop.setRam(ram);
            laptop.setAlmacenamiento(almacenamiento);
            laptop.setPrecio(precio);

            productosRepository.crear(laptop);
            JOptionPane.showMessageDialog(null, "Laptop creada exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la laptop: " + e.getMessage());
        }
    }

    public static void leerLaptop() {
        try {
            Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la laptop:"));
            Producto laptop = productosRepository.leer(id);

            if (laptop != null) {
                JOptionPane.showMessageDialog(null, laptop);
            } else {
                JOptionPane.showMessageDialog(null, "Laptop no encontrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer la laptop: " + e.getMessage());
        }
    }

    public static void actualizarLaptop() {
        try {
            Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la laptop a actualizar:"));
            Producto laptop = productosRepository.leer(id);

            if (laptop != null) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", laptop.getNombre());
                String procesador = JOptionPane.showInputDialog("Ingrese el nuevo procesador:", laptop.getProcesador());
                int ram = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva RAM (GB):", laptop.getRam()));
                int almacenamiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo almacenamiento (GB):", laptop.getAlmacenamiento()));
                BigDecimal precio = new BigDecimal(JOptionPane.showInputDialog("Ingrese el nuevo precio:", laptop.getPrecio()));

                laptop.setNombre(nombre);
                laptop.setProcesador(procesador);
                laptop.setRam(ram);
                laptop.setAlmacenamiento(almacenamiento);
                laptop.setPrecio(precio);

                productosRepository.actualizar(laptop);
                JOptionPane.showMessageDialog(null, "Laptop actualizada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Laptop no encontrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la laptop: " + e.getMessage());
        }
    }

    public static void eliminarLaptop() {
        try {
            Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la laptop a eliminar:"));
            Producto laptop = productosRepository.leer(id);

            if (laptop != null) {
                productosRepository.eliminar(id);
                JOptionPane.showMessageDialog(null, "Laptop eliminada.");
            } else {
                JOptionPane.showMessageDialog(null, "Laptop no encontrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la laptop: " + e.getMessage());
        }
    }

    public static void listaLaptops() {
        try {
            java.util.List<Producto> laptops = productosRepository.leerTodos();
            StringBuilder lista = new StringBuilder("--- Lista de Laptops ---\n");
            for (Producto p : laptops) {
                lista.append(p).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar las laptops: " + e.getMessage());
        }
    }
}
