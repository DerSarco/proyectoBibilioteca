package clases;

public class Estudiante extends Usuario {
    private String carrera_cursando;

    public Estudiante(String nombre_completo, String rut, char sexo, String carrera, String carrera_cursando) {
        super(nombre_completo, rut, sexo, carrera);
        this.carrera_cursando = carrera_cursando;
    }

    public Estudiante(String carrera_cursando) {
        this.carrera_cursando = carrera_cursando;
    }


    @Override
    public Usuario crearUsuario(String nombre_completo, String rut, char sexo, String carrera) throws Exception {
        this.setNombre_completo(nombre_completo);
        this.setSexo(sexo);
        this.setCarrera(carrera);
        this.setRut(rut);
        return Estudiante.this;
    }
}
