import java.util.ArrayList;

class ProductoEscolar extends Producto {
    private String nivelEscolar;
    private static ArrayList<ProductoEscolar> productos = new ArrayList<>();
    
    public ProductoEscolar(int codigo, String nombre, int precio, int cantidadStock, String tipo, String nivelEscolar) {
        super(codigo, nombre, precio, cantidadStock, tipo);
        this.nivelEscolar = nivelEscolar;
        productos.add(this);
    }
    
    public String getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(String nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    public void verInventario() {
        System.out.println("Inventario de productos escolares disponibles:");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-7s | %-15s | %-6s | %-5s | %-15s |\n", "Código", "Nombre", "Precio", "Stock", "Nivel Escolar");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < productos.size(); i++) {
            ProductoEscolar producto = productos.get(i);
            System.out.printf("| %-7d | %-15s | %-6d | %-5d | %-15s |\n", producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getCantidadStock(), producto.getNivelEscolar());
        }
        System.out.println("--------------------------------------------------------------");
    }
}