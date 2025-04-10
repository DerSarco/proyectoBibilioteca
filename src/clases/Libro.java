package clases;

public class Libro {
    private String isbn_libro;
    private String titulo;
    private String autor;
    private String imagenLibro;
    private int cantidad_en_biblioteca;
    private int cantidad_disponible_prestamo;

    public Libro(String isbn_libro, String titulo, String autor, String imagenLibro, int cantidad_en_biblioteca, int cantidad_disponible_prestamo) {
        this.isbn_libro = isbn_libro;
        this.titulo = titulo;
        this.autor = autor;
        this.imagenLibro = imagenLibro;
        this.cantidad_en_biblioteca = cantidad_en_biblioteca;
        this.cantidad_disponible_prestamo = cantidad_disponible_prestamo;
    }

    public String getIsbn_libro() {
        return isbn_libro;
    }

    public void setIsbn_libro(String isbn_libro) {
        this.isbn_libro = isbn_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagenLibro() {
        return imagenLibro;
    }

    public void setImagenLibro(String imagenLibro) {
        this.imagenLibro = imagenLibro;
    }

    public int getCantidad_en_biblioteca() {
        return cantidad_en_biblioteca;
    }

    public void setCantidad_en_biblioteca(int cantidad_en_biblioteca) {
        this.cantidad_en_biblioteca = cantidad_en_biblioteca;
    }

    public int getCantidad_disponible_prestamo() {
        return cantidad_disponible_prestamo;
    }

    public void setCantidad_disponible_prestamo(int cantidad_disponible_prestamo) {
        this.cantidad_disponible_prestamo = cantidad_disponible_prestamo;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn_libro='" + isbn_libro + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", imagenLibro='" + imagenLibro + '\'' +
                ", cantidad_en_biblioteca=" + cantidad_en_biblioteca +
                ", cantidad_disponible_prestamo=" + cantidad_disponible_prestamo +
                '}';
    }
    
    public void crearLibro() {
        System.out.println("crear libro");
    }

    public void eliminarLibro() {
        System.out.println("eliminando");
    }
    
    public Boolean estaDisponible() {
        return null;
    }

}
