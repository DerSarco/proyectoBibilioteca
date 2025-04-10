package clases;

public class Docente extends Usuario {
    private String profesion;
    private String grado;

    public Docente(String nombre_completo, String rut, char sexo, String carrera, String profesion, String grado) {
        super(nombre_completo, rut, sexo, carrera);
        this.profesion = profesion;
        this.grado = grado;
    }
}
