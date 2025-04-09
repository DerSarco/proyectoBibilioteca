package menu;

import java.util.Scanner;

public class Menu {

    Integer opcion;

    public void crearMenu() {
        opcion = 0;
        try {
            while (opcion == 0) {
                ImprimirOpciones();

                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();

                if (opcion == 1) {
                    System.out.println("Opcion 1");
                }
                if (opcion == 2) {
                    System.out.println("Opcion 2");
                }
                if (opcion == 3) {
                    System.out.println("Opcion 3");
                }
                if (opcion == 4) {
                    System.out.println("Opcion 4");
                }
                if (opcion == 0) {
                    System.out.println("Saliendo!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        System.out.println("4. Opcion 4");
    }
}
