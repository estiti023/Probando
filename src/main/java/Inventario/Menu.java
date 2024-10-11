package Inventario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        GestorInventario gestorInventario = new GestorInventario();
        int opcion = 0;

        do {
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Aplicar descuento");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Mostrar inventario");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = scanner.nextInt();
                // Limpiar el buffer solo después de leer un número
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        agregarProducto(scanner, gestorInventario);
                        break;
                    case 2:
                        buscarProducto(scanner, gestorInventario);
                        break;
                    case 3:
                        modificarProducto(scanner, gestorInventario);
                        break;
                    case 4:
                        aplicarDescuento(scanner, gestorInventario);
                        break;
                    case 5:
                        eliminarProducto(scanner, gestorInventario);
                        break;
                    case 6:
                        mostrarInventario(gestorInventario);
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero para seleccionar una opción.");
                scanner.next(); // Limpiar la entrada incorrecta
            }
        } while (opcion != 7);
    }

    private void agregarProducto(Scanner scanner, GestorInventario gestorInventario) {
        try {
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine(); // Leer la línea completa del nombre, incluyendo espacios

            System.out.print("Ingrese el ID del producto: ");
            int id = scanner.nextInt();

            System.out.print("Ingrese la cantidad del producto: ");
            int cantidad = scanner.nextInt();

            System.out.print("Ingrese el precio del producto: ");
            double precio = scanner.nextDouble();

            // Crear y agregar el producto
            Producto producto = new Producto(nombre, id, cantidad, precio);
            gestorInventario.agregarProducto(producto);
            System.out.println("Producto agregado correctamente.");

            // Limpiar buffer después de la última entrada numérica para evitar saltos de línea
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar los datos correctos.");
            scanner.nextLine(); // Limpiar el buffer de la entrada incorrecta
        } catch (EntradaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarProducto(Scanner scanner, GestorInventario gestorInventario) {
        try {
            System.out.print("Ingrese el ID del producto a buscar: ");
            int idBuscar = scanner.nextInt();
            Producto producto = gestorInventario.buscarProducto(idBuscar);
            System.out.println(producto);

            // Limpiar buffer después de la última entrada numérica
            scanner.nextLine();
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar un número entero.");
            scanner.nextLine(); // Limpiar el buffer de la entrada incorrecta
        }
    }

    private void modificarProducto(Scanner scanner, GestorInventario gestorInventario) {
        try {
            System.out.print("Ingrese el ID del producto a modificar: ");
            int idModificar = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de leer el ID

            System.out.print("Ingrese el nuevo nombre del producto: ");
            String nombreModificar = scanner.nextLine(); // Leer la línea completa del nombre, incluyendo espacios

            System.out.print("Ingrese la nueva cantidad del producto: ");
            int cantidadModificar = scanner.nextInt();

            System.out.print("Ingrese el nuevo precio del producto: ");
            double precioModificar = scanner.nextDouble();

            // Modificar el producto
            gestorInventario.modificarProducto(idModificar, nombreModificar, cantidadModificar, precioModificar);
            System.out.println("Producto modificado correctamente.");

            // Limpiar buffer después de la última entrada numérica
            scanner.nextLine();
        } catch (ProductoNoEncontradoException | EntradaInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar los datos correctos.");
            scanner.nextLine(); // Limpiar el buffer de la entrada incorrecta
        }
    }

    private void aplicarDescuento(Scanner scanner, GestorInventario gestorInventario) {
        try {
            System.out.print("Ingrese el ID del producto al que desea aplicar el descuento: ");
            int idDescuento = scanner.nextInt();

            System.out.print("Ingrese el porcentaje de descuento: ");
            double descuento = scanner.nextDouble();

            gestorInventario.aplicarDescuento(idDescuento, descuento);
            System.out.println("Descuento aplicado correctamente.");

            // Limpiar buffer después de la última entrada numérica
            scanner.nextLine();
        } catch (ProductoNoEncontradoException | EntradaInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar los datos correctos.");
            scanner.nextLine(); // Limpiar el buffer de la entrada incorrecta
        }
    }

    private void eliminarProducto(Scanner scanner, GestorInventario gestorInventario) {
        try {
            System.out.print("Ingrese el ID del producto a eliminar: ");
            int idEliminar = scanner.nextInt();
            gestorInventario.eliminarProducto(idEliminar);
            System.out.println("Producto eliminado correctamente.");

            // Limpiar buffer después de la última entrada numérica
            scanner.nextLine();
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar un número entero.");
            scanner.nextLine(); // Limpiar el buffer de la entrada incorrecta
        }
    }

    private void mostrarInventario(GestorInventario gestorInventario) {
        System.out.println(gestorInventario);
    }
}