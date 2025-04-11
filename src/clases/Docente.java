package clases;

public class Docente extends Usuario {
    private String profesion;
    private String grado;

    public Docente(String nombre_completo, String rut, char sexo, String carrera, String profesion, String grado) {
        super(nombre_completo, rut, sexo, carrera);
        this.profesion = profesion;
        this.grado = grado;
    }

    public Docente(String profesion, String grado) {
        this.profesion = profesion;
        this.grado = grado;
    }

    @Override
    public Usuario crearUsuario(String nombre_completo, String rut, char sexo, String carrera) throws Exception {
        this.setNombre_completo(nombre_completo);
        this.setSexo(sexo);
        this.setCarrera(carrera);
        this.setRut(rut);
        return Docente.this;
    }
}
