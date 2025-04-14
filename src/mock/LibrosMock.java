package mock;

import clases.Docente;
import clases.Libro;
import clases.Usuario;

import javax.swing.plaf.ListUI;
import java.util.ArrayList;

public class LibrosMock {
    private static final LibrosMock INSTANCE = new LibrosMock();
    private ArrayList<Libro> libros = new ArrayList<>();

    private LibrosMock() {
        libros.add(new Libro("978-9876543210", "Cien años de soledad", "Gabriel García Márquez",
                "https://example.com/imagenes/cien_anos.jpg", 10, 4));
        libros.add(new Libro("978-0140449136", "La Odisea", "Homero",
                "https://example.com/imagenes/odisea.jpg", 7, 2));
        libros.add(new Libro("978-0061120084", "Matar a un ruiseñor", "Harper Lee",
                "https://example.com/imagenes/ruisenor.jpg", 5, 1));
        libros.add(new Libro("978-0307277671", "El código Da Vinci", "Dan Brown",
                "https://example.com/imagenes/da_vinci.jpg", 12, 8));
        libros.add(new Libro("978-0192802385", "El arte de la guerra", "Sun Tzu",
                "https://example.com/imagenes/arte_guerra.jpg", 3, 3));
    }

    public static LibrosMock getInstance() {
        return INSTANCE;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void agregarLibroALaLista(Libro libro) {
        libros.add(libro);
    }

    public void eliminarLibroLista(String isbn) {
        libros.removeIf(libro -> libro.getIsbn_libro().equals(isbn));
    }
}






