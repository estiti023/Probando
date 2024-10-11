package Inventario;

import java.util.ArrayList;

public class GestorInventario {

    private ArrayList<Producto> productos;

    public GestorInventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) throws EntradaInvalidaException {
        if (producto.getId() <= 0 || producto.getCantidad() < 0 || producto.getPrecio() < 0) {
            throw new EntradaInvalidaException("ID, cantidad o precio no pueden ser negativos.");
        }
        this.productos.add(producto);
    }

    public Producto buscarProducto(int id) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado.");
    }

    public void modificarProducto(int id, String nombre, int cantidad, double precio) throws ProductoNoEncontradoException, EntradaInvalidaException {
        Producto producto = buscarProducto(id); // Lanza `ProductoNoEncontradoException` si no se encuentra
        if (cantidad < 0 || precio < 0) {
            throw new EntradaInvalidaException("Cantidad o precio no pueden ser negativos.");
        }
        producto.setNombre(nombre);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);
    }

    public void aplicarDescuento(int id, double descuento) throws ProductoNoEncontradoException, EntradaInvalidaException {
        if (descuento < 0 || descuento > 100) {
            throw new EntradaInvalidaException("El descuento debe estar entre 0 y 100.");
        }
        Producto producto = buscarProducto(id);
        double nuevoPrecio = producto.getPrecio() * (1 - descuento / 100);
        producto.setPrecio(nuevoPrecio);
    }

    public void eliminarProducto(int id) throws ProductoNoEncontradoException {
        Producto producto = buscarProducto(id);
        productos.remove(producto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Productos:\n");
        for (Producto producto : productos) {
            sb.append(producto.toString()).append("\n"); // Agregar cada producto a la cadena
        }
        return sb.toString(); // Devuelve la lista de productos en formato de texto
    }
}
