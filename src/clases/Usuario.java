package clases;
import com.sun.security.auth.UnixNumericUserPrincipal;
import utils.RutValidador;
import mock.UsuarioMock;

import java.util.ArrayList;
import java.util.Map;

public abstract class Usuario {
    private String nombre_completo;
    private String rut;
    private char sexo;
    private String carrera;
    private String prestamo = "0";
    private static final UsuarioMock usuarioMock = UsuarioMock.getInstance();

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

    public void crearUsuario(Map<String, Object> usuario) throws Exception {
        this.nombre_completo = usuario.get("nombre_completo").toString();
        this.rut = usuario.get("rut").toString();
        this.sexo = usuario.get("sexo").toString().charAt(0);
        this.carrera = usuario.get("carrera").toString();
    }
  
    //TODO: Hay un tema, para poder llamar esta función tenemos que a juro
    // tener una clase Usuario, cualquiera para poder llamar a esto, no seria mejor
    // dejar la llamada a imprimir usuario directamente?
    public static void imprimirUsuarios() {
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString() + usuario.getClass().getCanonicalName());
        }
    }
  
    public void editarUsuario(String rut, String nombre_completo, char sexo, String carrera, String prestamo) {
        ArrayList<Usuario> usuarios = usuarioMock.getUsers();
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                usuario.setNombre_completo(nombre_completo);
                usuario.setSexo(sexo);
                usuario.setCarrera(carrera);
                usuario.setPrestamo(prestamo);
                System.out.println("------------------------------");
                System.out.println("Usuario actualizado con éxito");
                System.out.println("------------------------------");
                System.out.println(usuario.toString());
            }
        }
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioMock.eliminarUsuarioDeLista(usuario.getRut());
    }
}
