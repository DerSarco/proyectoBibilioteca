package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private String isbn_libro;
    private String rut_usuario;
    private LocalDateTime fecha_devolucion;
    private LocalDateTime fecha_prestamo = LocalDateTime.now();
    private int dias_prestamo;
    private int multa = 0;

    public Prestamo(String isbn_libro, String rut_usuario, LocalDateTime fecha_devolucion, LocalDateTime fecha_prestamo, int dias_prestamo, int multa) {
        //Al crear préstamo, se debería crear también la tarjeta asociada al préstamo
        this.isbn_libro = isbn_libro;
        this.rut_usuario = rut_usuario;
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_prestamo = fecha_prestamo;
        this.dias_prestamo = dias_prestamo;
        this.multa = multa;
    }

    public Prestamo() {
    }

    public String getIsbn_libro() {
        return isbn_libro;
    }

    public void setIsbn_libro(String isbn_libro) {
        this.isbn_libro = isbn_libro;
    }

    public String getRut_usuario() {
        return rut_usuario;
    }

    public void setRut_usuario(String rut_usuario) {
        this.rut_usuario = rut_usuario;
    }

    public LocalDateTime getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDateTime fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public LocalDateTime getFecha_prestamo() {
        return fecha_prestamo;
    }

    public int getDias_prestamo() {
        return dias_prestamo;
    }

    public void setDias_prestamo(int dias_prestamo) {
        this.dias_prestamo = dias_prestamo;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
            "isbn_libro='" + isbn_libro + '\'' +
            ", rut_usuario='" + rut_usuario + '\'' +
            ", fecha_devolucion=" + fecha_devolucion +
            ", fecha_prestamo=" + fecha_prestamo +
            ", dias_prestamo=" + dias_prestamo +
            ", multa=" + multa +
            '}';
    }

    public int calcularDiasPrestamo(Usuario usuario) {
        if (usuario instanceof Docente) {
            return 20;
        } else {
            return 10;
        }
    }

    //TODO: Probar esta función
    public void calcularMulta(){
        // Calcular la fecha límite
        LocalDateTime fechaLimite = calcularFechaDevolucion(this.fecha_prestamo, this.dias_prestamo);

        if (getFecha_devolucion().isAfter(fechaLimite)) {
            long diasRetraso = ChronoUnit.DAYS.between(fechaLimite, getFecha_devolucion());
            int multaTotal = (int) diasRetraso * 1000;

            setMulta(multaTotal);
        } else {
            setMulta(0);
        }
    }

    public void crearTarjetaPrestamo(Usuario usuario, Libro libro) {
        try {
            FileWriter file = new FileWriter("tarjetaPrestamo_"+isbn_libro+"_"+usuario.getRut()+".txt");
            String texto = String.format("""
                     Fecha Prestamo: %s \n
                     Fecha devolución: %s \n
                     ISBN Libro: %s \n
                     Nombre Libro: %s \n
                     RUT Usuario: %s \n
                     Nombre Usuario: %s \n
                    """, this.getFecha_prestamo(), this.getFecha_devolucion(), this.getIsbn_libro(), libro.getTitulo(), this.getRut_usuario(), usuario.getNombre_completo());
            file.write(texto);
            file.close();
            System.out.println("Tarjeta creada exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDateTime calcularFechaDevolucion(LocalDateTime fechaPrestamo, int diasPrestamo) {
        return fechaPrestamo.plusDays(diasPrestamo);
    }

    public void realizarPrestamo(Libro libro, Usuario usuario) {
        usuario.setPrestamo(libro.getIsbn_libro());

        libro.disminuirStock();

        int dias_prestamo = this.calcularDiasPrestamo(usuario);

        LocalDateTime fecha_prestamo = LocalDateTime.now();
        LocalDateTime fecha_devolucion = calcularFechaDevolucion(fecha_prestamo, dias_prestamo);

        setIsbn_libro(libro.getIsbn_libro());
        setRut_usuario(usuario.getRut());
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_prestamo = fecha_prestamo;
        this.dias_prestamo = dias_prestamo;

        crearTarjetaPrestamo(usuario, libro);
    }

    public void realizarDevolucion(Libro libro, Usuario usuario) {
        usuario.setPrestamo("0");
        int cantidad_disponible_libro = libro.getCantidad_disponible_prestamo() + 1;
        libro.setCantidad_disponible_prestamo(cantidad_disponible_libro);
        calcularMulta();
    }
}
