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
                        MenuAcciones.eliminarUsuario();
                        break;
                    case 3:
                        MenuAcciones.imprimirUsuarios();
                        break;
                    case 4:
                        System.out.println("Imprimiendo opcion 4");
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
        System.out.println("Seleccione una opcion");
        System.out.println("\n");
        System.out.println("Menu");
        System.out.println("-----------------------");
        System.out.println("1. Crear Usuario");
        System.out.println("2. Eliminar Usuario");
        System.out.println("3. Ver Usuarios");
        System.out.println("4. Opcion 4 - Salir");
    }
}
