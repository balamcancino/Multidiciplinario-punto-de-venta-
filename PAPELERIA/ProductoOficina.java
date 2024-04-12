import java.util.ArrayList;
   
public class ProductoOficina extends Producto {
    private String categoria;
    private static ArrayList<ProductoOficina> productos = new ArrayList<>();
    
    public ProductoOficina(int codigo, String nombre, int precio, int cantidadStock, String tipo, String categoria) {
        super(codigo, nombre, precio, cantidadStock, tipo);
        this.categoria = categoria;
        productos.add(this);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void verInventario() {
        System.out.println("Inventario de productos de oficina:");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-7s | %-15s | %-6s | %-5s | %-12s |\n", "Código", "Nombre", "Precio", "Stock", "Categoría");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < productos.size(); i++) {
            ProductoOficina producto = productos.get(i);
            System.out.printf("| %-7d | %-15s | %-6d | %-5d | %-12s |\n", producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getCantidadStock(), producto.getCategoria());
        }
        System.out.println("--------------------------------------------------------------");
    }
}