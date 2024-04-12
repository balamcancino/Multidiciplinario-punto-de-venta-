import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventario {
    private static ArrayList<Producto> productos = new ArrayList<>();

    public static void AgregarProducto() {
        Scanner scanner = new Scanner(System.in);
        int codigo = 0;
        while (true) {
            try {
                System.out.println("Ingrese el código del producto:");
                codigo = scanner.nextInt();
                if (codigo < 0) {
                    throw new InputMismatchException("El código no puede ser negativo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        scanner.nextLine(); 
        System.out.println("Ingrese el nombre del producto:");
        String nombre = "";
        while (true) {
            try {
                nombre = scanner.nextLine();
                if (!nombre.matches("[a-zA-Z ]+")) {
                    throw new InputMismatchException("El nombre solo puede contener letras y espacios.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        int precio = 0;
        while (true) {
            try {
                System.out.println("Ingrese el precio del producto:");
                precio = scanner.nextInt();
                if (precio <= 0) {
                    throw new InputMismatchException("El precio debe ser un número positivo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        int cantidadStock = 0;
        while (true) {
            try {
                System.out.println("Ingrese la cantidad en stock:");
                cantidadStock = scanner.nextInt();
                if (cantidadStock < 0) {
                    throw new InputMismatchException("La cantidad en stock no puede ser negativa.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        scanner.nextLine(); 
        System.out.println("Ingrese el tipo de producto (Escolar/Oficina):");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("Escolar")) {
            System.out.println("Ingrese el nivel escolar:");
            String nivelEscolar = scanner.nextLine();
            Producto producto = new ProductoEscolar(codigo, nombre, precio, cantidadStock, tipo, nivelEscolar);
            productos.add(producto);
            System.out.println("Producto escolar agregado exitosamente al inventario.");
        } else if (tipo.equalsIgnoreCase("Oficina")) {
            System.out.println("Ingrese la categoría del producto:");
            String categoria = scanner.nextLine();
            Producto producto = new ProductoOficina(codigo, nombre, precio, cantidadStock, tipo, categoria);
            productos.add(producto);
            System.out.println("Producto de oficina agregado exitosamente al inventario.");
        } else {
            System.out.println("Tipo de producto inválido. No se agregó al inventario.");
        }
    }

    public static void EliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el código o nombre del producto a eliminar:");
        String entrada = scanner.nextLine();
    
        ArrayList<Producto> productosAEliminar = new ArrayList<>();
    
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (Integer.toString(producto.getCodigo()).equals(entrada) || producto.getNombre().equalsIgnoreCase(entrada)) {
                productosAEliminar.add(producto);
            }
        }
    
        if (!productosAEliminar.isEmpty()) {
            productos.removeAll(productosAEliminar);
            System.out.println("Productos eliminados del inventario.");
        } else {
            System.out.println("No se encontró ningún producto con ese código o nombre.");
        }
    }

    public static void BuscarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el código o nombre del producto a buscar:");
        String entrada = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (Integer.toString(producto.getCodigo()).equals(entrada) || producto.getNombre().equalsIgnoreCase(entrada)) {
                System.out.println("Producto encontrado:");
                System.out.println("Código: " + producto.getCodigo());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Cantidad en stock: " + producto.getCantidadStock());
                System.out.println("Tipo: " + producto.getTipo());
                if (producto instanceof ProductoEscolar) {
                    ProductoEscolar productoEscolar = (ProductoEscolar) producto;
                    System.out.println("Nivel Escolar: " + productoEscolar.getNivelEscolar());
                } else if (producto instanceof ProductoOficina) {
                    ProductoOficina productoOficina = (ProductoOficina) producto;
                    System.out.println("Categoría: " + productoOficina.getCategoria());
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado)
            System.out.println("No se encontró ningún producto con ese código o nombre.");
    }

    public static void ModificarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el código o nombre del producto a modificar:");
        String entrada = scanner.nextLine();

        boolean modificado = false;
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (Integer.toString(producto.getCodigo()).equals(entrada) || producto.getNombre().equalsIgnoreCase(entrada)) {
                System.out.println("Ingrese el nuevo código del producto:");
                int codigo = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese el nuevo nombre del producto:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese el nuevo precio del producto:");
                int precio = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese la nueva cantidad en stock:");
                int cantidadStock = scanner.nextInt();
                scanner.nextLine(); 
                System.out.println("Ingrese el nuevo tipo de producto (Escolar/Oficina):");
                String tipo = scanner.nextLine();

                if (producto instanceof ProductoEscolar && tipo.equalsIgnoreCase("Escolar")) {
                    System.out.println("Ingrese el nuevo nivel escolar:");
                    String nivelEscolar = scanner.nextLine();
                    producto.setCodigo(codigo);
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setCantidadStock(cantidadStock);
                    producto.setTipo(tipo);
                    ((ProductoEscolar) producto).setNivelEscolar(nivelEscolar);
                } else if (producto instanceof ProductoOficina && tipo.equalsIgnoreCase("Oficina")) {
                    System.out.println("Ingrese la nueva categoría:");
                    String categoria = scanner.nextLine();
                    producto.setCodigo(codigo);
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setCantidadStock(cantidadStock);
                    producto.setTipo(tipo);
                    ((ProductoOficina) producto).setCategoria(categoria);
                } else {
                    System.out.println("El tipo de producto no coincide con la clase del producto.");
                    continue;
                }

                System.out.println("Producto modificado exitosamente.");
                modificado = true;
                break;
            }
        }

        if (!modificado)
            System.out.println("No se encontró ningún producto con ese código o nombre.");
    }

    public ArrayList<Producto> obtenerProductos() {
        return productos;
    }
}