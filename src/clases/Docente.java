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

    public Docente() {
    }

    @Override
    public void crearUsuario(Map<String, Object> usuario) throws Exception {
        super.crearUsuario(usuario);
        this.profesion = usuario.get("profesion").toString();
        this.grado = usuario.get("grado").toString();
    }

    @Override
    public String toString() {
        return super.toString()
                + ", profesion=" + profesion
                + ", grado=" + grado;
    }
}
