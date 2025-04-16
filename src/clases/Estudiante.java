package clases;

import java.util.Map;

public class Estudiante extends Usuario {
    private String carrera_cursando;

    public Estudiante(String nombre_completo, String rut, char sexo, String carrera, String carrera_cursando) {
        super(nombre_completo, rut, sexo, carrera);
        this.carrera_cursando = carrera_cursando;
    }

    public Estudiante() {
    }


    @Override
    public void crearUsuario(Map<String, Object> usuario) throws Exception {
        super.crearUsuario(usuario);
        this.carrera_cursando = usuario.get("carrera_cursando").toString();
    }

    @Override
    public String toString() {
        return super.toString()
                + ", carrera_cursando=" + carrera_cursando;
    }
}
