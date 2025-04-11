package clases;

import utils.RutValidador;

public abstract class Usuario {
    private String nombre_completo;
    private String rut;
    private char sexo;
    private String carrera;
    private String prestamo = "0";

    public Usuario(String nombre_completo, String rut, char sexo, String carrera) {
        this.nombre_completo = nombre_completo;
        this.rut = rut;
        this.sexo = sexo;
        this.carrera = carrera;
    }

    //Constructor vacio para creación externa
    public Usuario() {

    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) throws Exception {
        if (validarRut(rut)) {
            this.rut = rut;
        } else {
            throw new Exception("Falló la creación del User, rut incorrecto.");
        }
    }

    public char getSexo() {
        return sexo;
    }

    @SuppressWarnings("ConditionAlwaysTrueOrFalse")
    public void setSexo(char sexo) {
        sexo = Character.toUpperCase(sexo);
        if (sexo != 'F' && sexo != 'M') {
            System.out.println("Ingrese un sexo válido");
        } else {
            this.sexo = sexo;
        }
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public void editarUsuario(String nombre_completo, String rut, char sexo){
            this.nombre_completo = nombre_completo;
            //TODO Validar si el rut no está repetido
            this.rut = validarRut(rut) ? rut : "";
            //TODO Validar si el genero se puede asignar
            setSexo(sexo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre_completo='" + nombre_completo + '\'' +
                ", rut='" + rut + '\'' +
                ", sexo=" + sexo +
                ", carrera='" + carrera + '\'' +
                ", prestamo='" + prestamo + '\'' +
                '}';
    }

    private Boolean validarRut(String rut) {
        if (RutValidador.validarRut(rut)) {
            this.rut = rut;
            return true;
        }
        System.out.println("Rut incorrecto");
        return false;
    }

    public Usuario crearUsuario(String nombre_completo, String rut, char sexo, String carrera) throws Exception {
        return this;
    }

    public void eliminarUsuario() {
        System.out.println("eliminando");
    }
}
