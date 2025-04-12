package menu;

import java.util.*;

public class Menu {
    public void crearMenu() {

        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        try {
            while (continuar) {
                ImprimirOpciones();
                System.out.println("Ingrese una opcion: \n");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        MenuAcciones.crearUsuario();
                        break;
                    case 2:
                        MenuAcciones.editarUsuario();
                        break;
                    case 3:
                        MenuAcciones.eliminarUsuario();
                        break;
                    case 4:
                        MenuAcciones.imprimirUsuarios();
                        break;
                    case 5:
                        MenuAcciones.realizarPrestamo();
                        break;
                    case 6:
                        MenuAcciones.realizarDevolucion();
                        break;
                    case 7:
                        MenuAcciones.crearLibro();
                        break;
                    case 8:
                        MenuAcciones.eliminarLibro();
                        break;
                    case 9:
                        MenuAcciones.imprimirLibros();
                        break;
                    case 10:
                        System.out.println("Saliendo...");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta nuevamente.");
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Something went wrong.");
            crearMenu();
        }
        sc.close();
    }

    private static void ImprimirOpciones() {
        System.out.println("\n");
        System.out.println("-----------------------");
        System.out.println("Nueva Biblioteca UNAB");
        System.out.println("-----------------------");
        System.out.println("\n");
        System.out.println("Seleccione una opción");
        System.out.println("\n");
        System.out.println("Menu");
        System.out.println("--------Usuario--------");
        System.out.println("1. Crear Usuario");
        System.out.println("2. Editar Usuario");
        System.out.println("3. Eliminar Usuario");
        System.out.println("4. Ver usuarios existentes");
        System.out.println("--------Préstamo--------");
        System.out.println("5. Realizar Préstamo");
        System.out.println("6. Realizar Devolución");
        System.out.println("--------Libro--------");
        System.out.println("7. Crear Libro");
        System.out.println("8. Eliminar Libro");
        System.out.println("9. Ver libros existentes");
        System.out.println("10. Salir del programa");
    }
}
