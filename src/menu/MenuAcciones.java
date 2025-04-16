package menu;

import clases.*;
import mock.LibrosMock;
import mock.PrestamoMock;
import mock.UsuarioMock;
import utils.RutValidador;

import java.util.*;

public class MenuAcciones {
    static UsuarioMock usuarioMock = UsuarioMock.getInstance();
    static LibrosMock librosMock = LibrosMock.getInstance();
    static PrestamoMock prestamoMock = PrestamoMock.getInstance();
    private static String nombre;
    private static String rut;
    private static char sexo;
    private static String carrera;
    private static String profesion;
    private static String grado;
    private static Scanner sc = new Scanner(System.in);
    private final static String continuar_string = "Presione 'Enter' para continuar...";

    public static void crearUsuario() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Que tipo de usuario quiere crear? (1 - Docente | 2 - Estudiante | 3 - Cancelar): ");
            String tipo = sc.nextLine();
            if (tipo.equals("3")) {
                System.out.println("Creación de usuario cancelada!");
                System.out.println(continuar_string);
                sc.nextLine();
                return;
            }
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
            System.out.println(continuar_string);
            sc.nextLine();
        } catch (Exception e) {
            System.err.println("Something went wrong.");
            System.err.println(e.getMessage());
        }
    }

    public static Map<String, Object> crearUsuarioDatosGenerico(String tipo) {
        Map<String, Object> usuarios = new HashMap<>();
        try {
            String tipoUsuario = Objects.equals(tipo, "1") ? "Docente" : "Estudiante";
            System.out.println("Ingrese nombre y apellido del " + tipoUsuario + ": ");
            nombre = sc.nextLine();
            String[] nombreSplit = nombre.split(" ");
            if (nombreSplit.length == 1) {
                throw new Exception("El nombre debe tener nombre y apellido.");
            }
            System.out.println("Ingrese su rut sin puntos y con guión: \n");
            System.out.println("Ej: 10887987-1 \n");
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
        System.out.println("Ingrese rut de usuario a editar: ");
        rut = sc.nextLine();
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                System.out.println("Usuario encontrado: ");
                System.out.println(usuario);
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
                System.out.println(continuar_string);
                sc.nextLine();
                return;
            }
        }
        System.out.println("Usuario con RUT " + rut + " no encontrado.");
    }

    public static void eliminarUsuario() {
        System.out.println("Ingrese rut de usuario a eliminar: ");
        String rut;
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
            System.out.println(continuar_string);
            sc.nextLine();
        }
    }

    public static void imprimirUsuarios() {
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString() + usuario.getClass().getCanonicalName());
        }
        System.out.println(continuar_string);
        sc.nextLine();
    }

    public static void realizarPrestamo() {
        Prestamo prestamo = new Prestamo();
        System.out.println("Ingrese ISBN del libro: ");
        String isbnLibro = sc.nextLine();
        ArrayList<Libro> libros = librosMock.getLibros();
        boolean libroEncontrado = false;
        boolean usuarioEncontrado = false;

        for (Libro libro : libros) {
            if (libro.getIsbn_libro().equals(isbnLibro)) {
                libroEncontrado = true;
                if (libro.getCantidad_disponible_prestamo() >= 1) {
                    System.out.println("Ingrese RUT del usuario: ");
                    String rut = sc.nextLine();
                    ArrayList<Usuario> usuarios = usuarioMock.getUsers();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getRut().equals(rut)) {
                            usuarioEncontrado = true;
                            if (usuario.getPrestamo().equals("0")) {
                                Prestamo nuevoPrestamo = prestamo.realizarPrestamo(libro, usuario);
                                prestamoMock.prestamos.add(nuevoPrestamo);
                                System.out.println(prestamoMock.getPrestamos());
                            } else {
                                System.out.println("El usuario ya tiene un préstamo activo");
                            }
                            break;
                        }
                    }

                    if (!usuarioEncontrado) {
                        System.out.println("Usuario no encontrado");
                    }

                } else {
                    System.out.println("Libro no disponible para préstamo");
                }
            }
        }

        if (!libroEncontrado) {
            System.out.println("Libro no encontrado");
        }
    }

    public static void realizarDevolucion() {
        System.out.println("devolucion");
    }

    public static void crearLibro() {
        try {
            Map<String, Object> libroData = new HashMap<>();
            Libro libro = new Libro();
            System.out.println("Ingrese el codigo (ISBN) del libro: ");
            //TODO: Validar que no esté repedito el ISBN (Kenny)
            String isbn = sc.nextLine();
            libroData.put("isbn_libro", isbn);
            System.out.println("Ingrese el título del libro: ");
            String titulo = sc.nextLine();
            libroData.put("titulo", titulo);
            System.out.println("Ingrese el autor del libro: ");
            String autor = sc.nextLine();
            libroData.put("autor", autor);
            System.out.println("Ingrese la URL de la imagen del libro: ");
            String url = sc.nextLine();
            libroData.put("imagenLibro", url);
            System.out.println("Ingrese la cantidad de disponibles en biblioteca (Solo números): ");
            int cantidad_biblioteca = sc.nextInt();
            libroData.put("cantidad_en_biblioteca", cantidad_biblioteca);
            System.out.println("Ingrese la cantidad disponible para prestamo (Solo números): ");
            int cantidad_prestamo = sc.nextInt();
            libroData.put("cantidad_disponible_prestamo", cantidad_prestamo);
            libro.crearLibro(libroData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void eliminarLibro() {
        try {
            System.out.println("Ingrese el codigo (ISBN) del libro a eliminar: ");
            String isbn = sc.nextLine();
            ArrayList<Libro> libros = LibrosMock.getInstance().getLibros();
            for (Libro libro : libros) {
                if (libro.getIsbn_libro().equals(isbn)) {
                    libro.eliminarLibro(isbn);
                    System.out.println("Libro eliminado con exito!");
                    return;
                }
            }
            System.out.println("El libro no se ha eliminado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void imprimirLibros() {
        ArrayList<Libro> libros = LibrosMock.getInstance().getLibros();
        libros.forEach(libro -> System.out.println(libro.toString()));
        System.out.println(continuar_string);
        sc.nextLine();
    }
}
