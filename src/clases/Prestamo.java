package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Prestamo {
    private String isbn_libro;
    private String rut_usuario;
    private LocalDateTime fecha_devolucion;
    private LocalDateTime fecha_prestamo;
    private int dias_prestamo;
    private int multa;

    public Prestamo(String isbn_libro, String rut_usuario, LocalDateTime fecha_devolucion, LocalDateTime fecha_prestamo, int dias_prestamo, int multa) {
        this.isbn_libro = isbn_libro;
        this.rut_usuario = rut_usuario;
        this.fecha_devolucion = fecha_devolucion;
        this.fecha_prestamo = LocalDateTime.now();
        this.dias_prestamo = dias_prestamo;
        this.multa = multa;
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

    public void setFecha_prestamo(LocalDateTime fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
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

    public void calcularDiasPrestamo(Usuario usuario) {
        if (usuario instanceof Docente) {
            this.dias_prestamo = 20;
        } else {
            this.dias_prestamo = 10;
        }
    }

    //TODO sacaría esta función de acá y la meteria en otro lado.
    private void crearTarjetaPrestamo() {
        try {
            FileWriter file = new FileWriter("tarjetaUser.txt");
            String texto = String.format("""
                     Fecha Prestamo: %s \n
                     Fecha devolución: %s \n
                     ISBN: %s \n
                     RUT Usuario: %s \n
                    """, fecha_prestamo, fecha_devolucion, isbn_libro, rut_usuario);
            file.write(texto);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
