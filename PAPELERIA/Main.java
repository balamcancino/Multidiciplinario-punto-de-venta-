import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static ArrayList<Venta> historialVentas = new ArrayList<>();
    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);
    private static Empleado empleado = new Empleado();
    private static Dueño dueño = new Dueño();
    private static Set<String> nombresUsuariosEmpleados = new HashSet<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("¿Qué tipo de usuario eres? (empleado/admin)");
            String tipoUsuario = leerInputTexto();

            switch (tipoUsuario) {
                case "empleado":
                    iniciarSesionEmpleado();
                    break;
                case "admin":
                    iniciarSesionDueño();
                    break;
                default:
                    System.out.println("Tipo de usuario no válido.");
                    break;
            }
        }

        System.out.println("¡Hasta luego!");
    }

    public static void iniciarSesionEmpleado() {
        System.out.println("Ingrese su nombre de usuario:");
        String nombreUsuario = leerInputTexto();
        System.out.println("Ingrese su contraseña:");
        String contraseña = leerInputContraseña();

        if (empleado.verificarLogin(nombreUsuario, contraseña)) {
            if (!nombresUsuariosEmpleados.contains(nombreUsuario)) {
                nombresUsuariosEmpleados.add(nombreUsuario);
                System.out.println("¡Bienvenido, " + nombreUsuario + "!");
                mostrarMenuEmpleado();
            } else {
                System.out.println("Este usuario ya ha iniciado sesión como empleado, vuelva a iniciar sesión.");
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    public static void iniciarSesionDueño() {
        System.out.println("Ingrese su nombre de usuario:");
        String nombreUsuario = leerInputTexto();
        System.out.println("Ingrese su contraseña:");
        String contraseña = leerInputContraseña();

        if (dueño.verificarLogin(nombreUsuario, contraseña)) {
            if (!nombresUsuariosEmpleados.contains(nombreUsuario)) {
                System.out.println("¡Bienvenido, Admin!");
                mostrarMenuDueño();
            } else {
                System.out.println("Este usuario ya ha iniciado sesión como empleado, vuelva a iniciar sesión.");
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    public static void mostrarMenuEmpleado() {
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("1. Agregar producto al inventario");
                System.out.println("2. Eliminar producto del inventario");
                System.out.println("3. Buscar producto en el inventario");
                System.out.println("4. Modificar producto en el inventario");
                System.out.println("5. Realizar venta");
                System.out.println("6. Cambiar de perfil de usuario");
                System.out.println("7. Salir del programa");
                System.out.println("Seleccione una opción:");

                int opcion = leerInputNumero();

                switch (opcion) {
                    case 1:
                        Inventario.AgregarProducto();
                        break;
                    case 2:
                        Inventario.EliminarProducto();
                        break;
                    case 3:
                        Inventario.BuscarProducto();
                        break;
                    case 4:
                        Inventario.ModificarProducto();
                        break;
                    case 5:
                        Venta.realizarVenta(historialVentas, inventario);
                        break;
                    case 6:
                        boolean salirMenuEmpleado = false;
                        while (!salirMenuEmpleado) {
                            System.out.println("¿Qué tipo de usuario eres? (Empleado/Admin)");
                            String tipoUsuario = leerInputTexto();
                    
                            switch (tipoUsuario) {
                                case "empleado":
                                    iniciarSesionEmpleado();
                                    salirMenuEmpleado = true;
                                    break;
                                case "admin":
                                    iniciarSesionDueño();
                                    salirMenuEmpleado = true;
                                    break;
                                default:
                                    System.out.println("Tipo de usuario no válido.");
                                    break;
                            }
                        }
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    public static void mostrarMenuDueño() {
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("1. Realizar corte de caja");
                System.out.println("2. Ver inventario de productos escolares");
                System.out.println("3. Ver inventario de productos de oficina");
                System.out.println("4. Mostrar ventas");
                System.out.println("5. Cancelar venta");
                System.out.println("6. Cambiar de perfil de usuario");
                System.out.println("7. Salir del programa");
                System.out.println("Seleccione una opción:");

                int opcion = leerInputNumero();

                switch (opcion) {
                    case 1:
                        Historial.RealizarCorte(historialVentas);
                        break;
                    case 2:
                        ProductoEscolar productoEscolar = new ProductoEscolar(0, "", 0, 0, "", "");
                        productoEscolar.verInventario();
                        break;
                    case 3:
                        ProductoOficina productoOficina = new ProductoOficina(0, "", 0, 0, "", "");
                        productoOficina.verInventario();
                        break;
                    case 4:
                        Historial.MostrarVentas();
                        break;
                    case 5:
                    Historial.cancelarVenta(scanner);
                    Historial.MostrarVentas(); 
                    break;
                    case 6:
                        boolean salirMenuDueño = false;
                        while (!salirMenuDueño) {
                            System.out.println("¿Qué tipo de usuario eres? (Empleado/Admin)");
                            String tipoUsuario = leerInputTexto();
                
                            switch (tipoUsuario) {
                                case "empleado":
                                    iniciarSesionEmpleado();
                                    salirMenuDueño = true;
                                    break;
                                case "admin":
                                    iniciarSesionDueño();
                                    salirMenuDueño = true;
                                    break;
                                default:
                                    System.out.println("Tipo de usuario no válido.");
                                    break;
                            }
                        }
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    public static String leerInputTexto() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || !input.matches("[a-zA-Z]+")) {
                System.out.println("No se permiten numeros solo ingrese solo letras.");
            } else {
                break;
            }
        } while (true);
        return input;
    }

    public static String leerInputContraseña() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || !input.matches("\\d+") || Integer.parseInt(input) < 0) {
                System.out.println("Contraseña inválida ingrese solo números positivos.");
            } else {
                break;
            }
        } while (true);
        return input;
    }

    public static int leerInputNumero() {
        int input;
        do {
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        } while (true);
        return input;
    }
}
