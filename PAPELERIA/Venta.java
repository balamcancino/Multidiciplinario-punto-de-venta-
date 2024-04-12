import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Venta {
    private static int contadorVentas = 0;
    private int numeroVenta;
    private Date fecha;
    private int total;

    public Venta(Date fecha) {
        this.numeroVenta = ++contadorVentas;
        this.fecha = fecha;
        this.total = 0;
    }

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getTotal() {
        return total;
    }

    public static void realizarVenta(ArrayList<Venta> historialVentas, Inventario inventario) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productosVendidos = new ArrayList<>();
        boolean continuar = true;
        while (continuar) {
          
            try {
                System.out.println("Ingrese el código o nombre del producto que desea vender:");
                String entrada = scanner.nextLine();
            
                ArrayList<Producto> listaProductos = inventario.obtenerProductos();
            
                Producto productoEncontrado = null;
                for (int i = 0; i < listaProductos.size(); i++) {
                    Producto producto = listaProductos.get(i);
                    if (Integer.toString(producto.getCodigo()).equals(entrada) || producto.getNombre().toLowerCase().contains(entrada.toLowerCase())) {
                        productoEncontrado = producto;
                        break;
                    }
                }
                if (productoEncontrado != null) {
                    productosVendidos.add(productoEncontrado);
                    System.out.println("Producto agregado a la venta.");
            
                    productoEncontrado.setCantidadStock(productoEncontrado.getCantidadStock() - 1);
    
                   
                    do {
                        System.out.println("¿Desea agregar otro producto? (si/no)");
                        String respuesta = scanner.nextLine().toLowerCase();
                        if (!respuesta.equals("si") && !respuesta.equals("no")) {
                            System.out.println("Respuesta inválida. Por favor ingrese 'si' o 'no'.");
                        } else {
                            continuar = respuesta.equals("si");
                            break;
                        }
                    } while (true);
                } else {
                    System.out.println("No se encontró ningún producto con ese código o nombre en el inventario.");
                    System.out.println("¿Desea agregar otro producto? (si/no)");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("no")) {
                        continuar = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    
        if (!productosVendidos.isEmpty()) {
            Venta venta = new Venta(new Date());
            venta.GenerarTotal(productosVendidos);
            venta.GenerarTicket(productosVendidos);
            historialVentas.add(venta);
            // Actualizar la lista de ventas
            Historial.setVentas(historialVentas);
            System.out.println("Venta realizada. Mostrando menú nuevamente.");
        } else {
            System.out.println("No se han vendido productos.");
        }
    }

    public void GenerarTotal(ArrayList<Producto> productosVendidos) {
        int totalVenta = 0;
        for (Producto producto : productosVendidos) {
            totalVenta += producto.getPrecio();
        }
        this.total = totalVenta;
    }

    public void GenerarTicket(ArrayList<Producto> productosVendidos) {
        System.out.println("---- Ticket de Venta ----");
        System.out.println("Número de venta: " + numeroVenta);
        System.out.println("Fecha: " + fecha);
        System.out.println("Total: $" + total);
        System.out.println("Productos:");

        for (int i = 0; i < productosVendidos.size(); i++) {
            Producto producto = productosVendidos.get(i);
            System.out.println(" - " + producto.getNombre() + " - $" + producto.getPrecio());
        }
        System.out.println("-------------------------");
    }
}
