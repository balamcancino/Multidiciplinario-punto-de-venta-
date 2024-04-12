import java.util.ArrayList;
import java.util.Scanner;

public class Historial {
    private static ArrayList<Venta> ventas = new ArrayList<>();

    public static ArrayList<Venta> getVentas() {
        return ventas;
    }

    public static void setVentas(ArrayList<Venta> ventas) {
        Historial.ventas = ventas;
    }

    public static void MostrarVentas() {
        ArrayList<Venta> copiaVentas = new ArrayList<>(ventas);
        
        System.out.println("Historial de ventas:");
        for (int i = 0; i < copiaVentas.size(); i++) {
            Venta venta = copiaVentas.get(i);
            System.out.println("- Número: " + venta.getNumeroVenta() + ", Fecha: " + venta.getFecha() + ", Total: " + venta.getTotal());
        }
    }

    public static void cancelarVenta(Scanner scanner) {
        System.out.println("Ingrese el número de venta que desea cancelar:");
        int numeroVenta = scanner.nextInt();
        scanner.nextLine();
    
        if (numeroVenta >= 1 && numeroVenta <= ventas.size()) {
            Venta ventaCancelada = ventas.remove(numeroVenta - 1);
            System.out.println("Venta cancelada exitosamente:");
            System.out.println("- Número: " + ventaCancelada.getNumeroVenta() + ", Fecha: " + ventaCancelada.getFecha() + ", Total: " + ventaCancelada.getTotal());
        } else {
            System.out.println("Número de venta inválido.");
        }
    }
    
    public static void RealizarCorte(ArrayList<Venta> historialVentas) {
        int totalVentas = 0;
        for (int i = 0; i < historialVentas.size(); i++) {
            Venta venta = historialVentas.get(i);
            totalVentas += venta.getTotal();
        }
        System.out.println("¡Corte de caja realizado con éxito! Total de ganancias del día: " + totalVentas);

    }
}
