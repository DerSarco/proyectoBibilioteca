package clases;

import java.util.Map;

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
    public void crearUsuario(Map<String, Object> usuario) throws Exception {
        super.crearUsuario(usuario);
    }
}
