package clases;

public abstract class Usuario {

    private String nombre;
    private String Apellido;
    private String RUT;

    public Usuario(String nombre, String apellido, String RUT) {
        this.nombre = nombre;
        Apellido = apellido;
        this.RUT = RUT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getRUT() {
        return RUT;
    }

    public void setRUT(String RUT) {
        this.RUT = RUT;
    }

    public void validarRut() {
        //TODO
    }
}
