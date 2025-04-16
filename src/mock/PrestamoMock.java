package mock;
import clases.Prestamo;

import java.util.ArrayList;

public class PrestamoMock {
    private static final PrestamoMock INSTANCE = new PrestamoMock();
    public ArrayList<Prestamo> prestamos = new ArrayList<>();

    private PrestamoMock() {
    }

    public static PrestamoMock getInstance() {
        return INSTANCE;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }
}
