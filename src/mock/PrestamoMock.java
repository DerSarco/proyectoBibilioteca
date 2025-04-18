package mock;

import clases.Prestamo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PrestamoMock {
    private static final PrestamoMock INSTANCE = new PrestamoMock();
    public ArrayList<Prestamo> prestamos = new ArrayList<>();

    private PrestamoMock() {
        prestamos.add(new Prestamo(
                "978-9876543210",            // ISBN: Cien años de soledad
                "13456789-5",                // RUT: Docente
                LocalDateTime.now(),        // Fecha devolución (hoy)
                LocalDateTime.now().minusDays(25), // Fecha préstamo (25 días atrás, atraso de 5 días para docente)
                20,                         // Días permitidos
                0                           // Multa inicial
        ));

        prestamos.add(new Prestamo(
                "978-0140449136",           // ISBN: La Odisea
                "22345678-2",               // RUT: Estudiante
                LocalDateTime.now(),       // Fecha devolución (hoy)
                LocalDateTime.now().minusDays(13), // Fecha préstamo (13 días atrás, atraso de 3 días para estudiante)
                10,                         // Días permitidos
                0                           // Multa inicial
        ));
    }

    public static PrestamoMock getInstance() {
        return INSTANCE;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }
}
