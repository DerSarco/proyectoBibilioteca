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
            if (crearUsuario(tipo)) {
                System.out.println("Usuario creado con exito!");
                imprimirUsuarios();
            } else {
                System.out.println("Usuario no creado...");
            }
            System.out.println(continuar_string);
            sc.nextLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean crearUsuario(String tipo) {
        Map<String, Object> usuarios = new HashMap<>();
        try {
            String tipoUsuario = Objects.equals(tipo, "1") ? "Docente" : "Estudiante";
            Usuario usuarioACrear = Objects.equals(tipo, "1") ? new Docente() : new Estudiante();
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
            if (usuarioACrear.estaDuplicado(rut, usuarioMock)) {
                throw new Exception("Rut ya existe");
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
            switch (tipo) {
                case "1":
                    System.out.println("Ingrese la profesión del docente: ");
                    profesion = sc.nextLine();
                    usuarios.put("profesion", profesion);
                    System.out.println("Ingrese el grado del Docente: ");
                    grado = sc.nextLine();
                    usuarios.put("grado", grado);
                    usuarioACrear.crearUsuario(usuarios);
                    usuarioMock.addUserToList(usuarioACrear);
                    break;
                case "2":
                    System.out.println("Ingrese la carrera que cursa actualmente el estudiante: ");
                    carrera = sc.nextLine();
                    usuarios.put("carrera_cursando", carrera);
                    usuarioACrear.crearUsuario(usuarios);
                    usuarioMock.addUserToList(usuarioACrear);
                    break;
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static void editarUsuario() {
        System.out.println("Ingrese rut de usuario a editar sin puntos y con guión: ");
        System.out.println("Ej: 10887987-1 \n");
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
                usuario.editarUsuario(usuarioMock, rut, nombreCompleto, sexo, carrera, prestamo);
                System.out.println(continuar_string);
                sc.nextLine();
                return;
            }
        }
        System.out.println("Usuario con RUT " + rut + " no encontrado.");
    }

    public static void eliminarUsuario() {
        System.out.println("Ingrese rut de usuario a eliminar sin puntos y con guión: ");
        System.out.println("Ej: 10887987-1 \n");
        String rut;
        rut = sc.nextLine();
        if (RutValidador.validarRut(rut)) {
            System.out.println("Estas seguro quiere eliminar el usuario? (1 - Si | 2 - No): )");
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    ArrayList<Usuario> usuarios = usuarioMock.getUsers();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getRut().equals(rut)) {
                            usuario.eliminarUsuario(usuario, usuarioMock);
                        }
                    }
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
            if (usuario instanceof Docente docente) {
                System.out.println(docente + " " + docente.getClass().getCanonicalName());
            } else if (usuario instanceof Estudiante estudiante) {
                System.out.println(estudiante + " " + estudiante.getClass().getCanonicalName());
            }
        }
        System.out.println("Aprete Enter para continuar...");
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
                if (libro.estaDisponible()) {
                    System.out.println("Ingrese RUT del usuario sin puntos y con guión: ");
                    System.out.println("Ej: 10887987-1 \n");
                    String rut = sc.nextLine();
                    ArrayList<Usuario> usuarios = usuarioMock.getUsers();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getRut().equals(rut)) {
                            usuarioEncontrado = true;
                            if (usuario.getPrestamo().equals("0")) {
                                prestamo.realizarPrestamo(libro, usuario);
                                prestamoMock.prestamos.add(prestamo);
                                prestamoMock.getPrestamos().forEach(System.out::println);
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
        System.out.println("Ingrese ISBN del libro a devolver: ");
        String isbnLibroDevuelto = sc.nextLine();
        ArrayList<Prestamo> prestamos = prestamoMock.getPrestamos();
        Libro libroADevolver = null;
        boolean devolucionRealizada = false;
        boolean usuarioEncontrado = false;
        boolean libroEncontrado = false;

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIsbn_libro().equals(isbnLibroDevuelto)) {
                for (Libro libro : librosMock.getLibros()) {
                    if (libro.getIsbn_libro().equals(isbnLibroDevuelto)) {
                        libroEncontrado = true;
                        libroADevolver = libro;
                        break;
                    }
                }
            }
            if (libroEncontrado && !devolucionRealizada) {
                System.out.println("Ingrese RUT del usuario sin puntos y con guión: ");
                System.out.println("Ej: 10887987-1 \n");
                String rutUsuario = sc.nextLine();
                ArrayList<Usuario> usuarios = usuarioMock.getUsers();
                for (Usuario usuario : usuarios) {
                    if (usuario.getRut().equals(rutUsuario) && usuario.getPrestamo().equals(isbnLibroDevuelto)) {
                        prestamo.realizarDevolucion(libroADevolver, usuario);
                        usuarioEncontrado = true;
                        devolucionRealizada = true;
                        break;
                    }
                }
            }
        }

        if (!libroEncontrado) {
            System.out.println("Libro no encontrado");
        }

        if (!usuarioEncontrado) {
            System.out.println("Usuario no encontrado");
        }

        if (libroEncontrado && devolucionRealizada) {
            System.out.println("Devolución realizada con éxito \n");
        }

        System.out.println("Aprete Enter para continuar...");
        sc.nextLine();
    }

    public static void imprimirPrestamos() {
        ArrayList<Prestamo> prestamos = prestamoMock.getPrestamos();
        prestamos.forEach(System.out::println);
        System.out.println("Aprete Enter para continuar...");
        sc.nextLine();
    }

    public static void crearLibro() {
        try {
            Map<String, Object> libroData = new HashMap<>();
            Libro libro = new Libro();
            System.out.println("Ingrese el codigo (ISBN) del libro: ");
            String isbn = sc.nextLine();
            if (libro.estaDuplicado(isbn, librosMock)){
                throw new Exception("El libro ya existe");
            }
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
            if (cantidad_biblioteca <= 0){
                throw new Exception("La cantidad de disponibles debe ser mayor a 0");
            }
            libroData.put("cantidad_en_biblioteca", cantidad_biblioteca);
            System.out.println("Ingrese la cantidad disponible para prestamo (Solo números): ");
            int cantidad_prestamo = sc.nextInt();
            libroData.put("cantidad_disponible_prestamo", cantidad_prestamo);
            libro.crearLibro(libroData, librosMock);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(continuar_string);
            sc.nextLine();
        }
    }

    public static void eliminarLibro() {
        try {
            System.out.println("Ingrese el codigo (ISBN) del libro a eliminar: ");
            String isbn = sc.nextLine();
            ArrayList<Libro> libros = librosMock.getLibros();
            for (Libro libro : libros) {
                if (libro.getIsbn_libro().equals(isbn)) {
                    libro.eliminarLibro(isbn, librosMock);
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
