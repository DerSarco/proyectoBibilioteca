package menu;

import clases.Docente;
import clases.Estudiante;
import clases.Usuario;
import mock.UsuarioMock;
import utils.RutValidador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    UsuarioMock usuarioMock = UsuarioMock.getInstance();

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
                        crearUsuario();
                        break;
                    case 2:
                        eliminarUsuario();
                        break;
                    case 3:
                        imprimirUsuarios();
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

    private void crearUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de usuario quiere crear? (1 - Docente | 2 - Estudiante): ");
        String tipo = sc.nextLine();
        String nombre;
        String rut;
        String profesion;
        String grado;
        char sexo;
        String carrera;
        try {
            switch (tipo) {
                case "1":
                    System.out.println("Ingrese su nombre del Docente: ");
                    nombre = sc.nextLine();
                    System.out.println("Ingrese su rut con guión: ");
                    rut = sc.nextLine();
                    if (!RutValidador.validarRut(rut)) {
                        throw new InputMismatchException("Rut no valido");
                    }
                    System.out.println("Ingrese su profesion: ");
                    profesion = sc.nextLine();
                    System.out.println("Ingrese su grado: ");
                    grado = sc.nextLine();
                    System.out.println("Ingrese su sexo: ");
                    sexo = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println("Ingrese su carrera: ");
                    carrera = sc.nextLine();
                    Docente docente = new Docente(profesion, grado);
                    docente.crearUsuario(nombre, rut, sexo, carrera);
                    usuarioMock.addUserToList(docente);
                    break;
                case "2":
                    System.out.println("Ingrese su nombre del Estudiante: ");
                    nombre = sc.nextLine();
                    System.out.println("Ingrese su rut con guión: ");
                    rut = sc.nextLine();
                    if (!RutValidador.validarRut(rut)) {
                        throw new InputMismatchException("Rut no valido");
                    }
                    System.out.println("Ingrese su profesion: ");
                    profesion = sc.nextLine();
                    System.out.println("Ingrese su grado: ");
                    grado = sc.nextLine();
                    System.out.println("Ingrese su sexo: ");
                    sexo = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println("Ingrese su carrera: ");
                    carrera = sc.nextLine();
                    Estudiante estudiante = new Estudiante(carrera);
                    estudiante.crearUsuario(nombre, rut, sexo, carrera);
                    usuarioMock.addUserToList(estudiante);
                    break;
            }
            System.out.println("Usuario creado con exito!");
            imprimirUsuarios();
        } catch (InputMismatchException e) {

            System.err.println("Dato ingresado incorrectamente.");
            crearMenu();
        } catch (Exception e) {
            System.out.println("Algo malo pasó");
            System.err.println(e.getMessage());
            crearMenu();
        }
    }


    private void eliminarUsuario() {
        System.out.println("Ingrese rut de usuario a eliminar: ");
        String rut;
        Scanner sc = new Scanner(System.in);
        rut = sc.nextLine();
        if (RutValidador.validarRut(rut)) {
            System.out.println("Estas seguro quiere eliminar el usuario? (1 - Si | 2 - No): )");
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    usuarioMock.eliminarUsuarioDeLista(rut);
                    System.out.println("Usuario eliminado con exito!");
                    imprimirUsuarios();
                    break;
                case "2":
                    System.out.println("Usuario no se a eliminado");
                    break;
            }
        }
    }

    private void imprimirUsuarios() {
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString() + usuario.getClass().getCanonicalName());
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
        System.out.println("1. Crear Usuario");
        System.out.println("2. Eliminar Usuario");
        System.out.println("3. Ver Usuarios");
        System.out.println("4. Opcion 4 - Salir");
    }
}
