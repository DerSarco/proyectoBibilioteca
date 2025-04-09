package menu;

import java.util.Scanner;

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
                        System.out.println("Imprimiendo opcion 1");
                        break;
                    case 2:
                        System.out.println("Imprimiendo opcion 2");
                        break;
                    case 3:
                        System.out.println("Imprimiendo opcion 3");
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
        } catch (Exception e) {
            e.printStackTrace();
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
        System.out.println("1. Opcion 1");
        System.out.println("2. Opcion 2");
        System.out.println("3. Opcion 3");
        System.out.println("4. Opcion 4 - Salir");
    }
}
