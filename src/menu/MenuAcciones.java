package menu;

import clases.Docente;
import clases.Estudiante;
import clases.Usuario;
import mock.UsuarioMock;
import clases.Usuario;
import utils.RutValidador;

import java.util.*;

public class MenuAcciones {
    static UsuarioMock usuarioMock = UsuarioMock.getInstance();
    private static String nombre;
    private static String rut;
    private static char sexo;
    private static String carrera;
    private static String profesion;
    private static String grado;

    public static void crearUsuario() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Que tipo de usuario quiere crear? (1 - Docente | 2 - Estudiante): ");
            String tipo = sc.nextLine();
            Map<String, Object> user = crearUsuarioDatosGenerico(tipo);
            if (user.isEmpty()) {
                throw new Exception("Datos de usuario vacios.");
            }
            switch (tipo) {
                case "1":
                    System.out.println("Ingrese la profesión del docente: ");
                    profesion = sc.nextLine();
                    System.out.println("Ingrese el grado del Docente: ");
                    grado = sc.nextLine();
                    Docente docente = new Docente(profesion, grado);
                    docente.crearUsuario(user);
                    usuarioMock.addUserToList(docente);
                    break;
                case "2":
                    System.out.println("Ingrese la carrera que cursa actualmente el estudiante: ");
                    carrera = sc.nextLine();
                    Estudiante estudiante = new Estudiante(carrera);
                    estudiante.crearUsuario(user);
                    usuarioMock.addUserToList(estudiante);
                    break;
            }
            System.out.println("Usuario creado con exito!");
            imprimirUsuarios();
        } catch (Exception e) {
            System.err.println("Something went wrong.");
            System.err.println(e.getMessage());
        }
    }

    public static Map<String, Object> crearUsuarioDatosGenerico(String tipo) {
        Scanner sc = new Scanner(System.in);
        Map<String, Object> usuarios = new HashMap<>();
        try {
            String tipoUsuario = Objects.equals(tipo, "1") ? "Docente" : "Estudiante";
            System.out.println("Ingrese su nombre y apellido del " + tipoUsuario + ": ");
            nombre = sc.nextLine();
            String[] nombreSplit = nombre.split(" ");
            if (nombreSplit.length == 1) {
                throw new Exception("El nombre debe tener nombre y apellido.");
            }
            System.out.println("Ingrese su rut sin puntos y con guión: ");
            rut = sc.nextLine();
            if (!RutValidador.validarRut(rut)) {
                throw new Exception("Rut no valido");
            }
            System.out.println("Ingrese su sexo (F o M): ");
            sexo = sc.next().charAt(0);
            sexo = Character.toUpperCase(sexo);
            sc.nextLine();
            System.out.println("Ingrese su carrera: ");
            carrera = sc.nextLine();
            usuarios.put("nombre_completo", nombre);
            usuarios.put("rut", rut);
            usuarios.put("sexo", sexo);
            usuarios.put("carrera", carrera);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    public static void editarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese rut de usuario a editar: ");
        rut = sc.nextLine();
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                System.out.println("Usuario encontrado: ");
                System.out.println(usuario.toString());
                System.out.println("----Datos a editar----");
                System.out.println("Nombre y apellido: ");
                String nombreCompleto = sc.nextLine();
                System.out.println("Sexo (F o M): ");
                String sexoInput = sc.nextLine();
                char sexo = sexoInput.charAt(0);
                System.out.println("Carrera: ");
                String carrera = sc.nextLine();
                System.out.println("¿Tiene un préstamo asociado?: ");
                String prestamo = sc.nextLine();
                usuario.editarUsuario(rut, nombreCompleto, sexo, carrera, prestamo);
                return;
            }
        }
        System.out.println("Usuario con RUT " + rut + " no encontrado.");
    }

    public static void eliminarUsuario() {
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

    public static void imprimirUsuarios() {
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString() + usuario.getClass().getCanonicalName());
        }
    }

    public static void realizarPrestamo() {
        System.out.println("préstamo");
    }

    public static void realizarDevolucion() {
        System.out.println("devolucion");
    }

    public static void crearLibro() {
        System.out.println("libro");
    }

    public static void eliminarLibro() {
        System.out.println("Eliminando libro");
    }

    public static void imprimirLibros() {
        System.out.println("Eliminando libro");
    }



}
