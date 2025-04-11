package mock;

import clases.Docente;
import clases.Estudiante;
import clases.Usuario;

import java.util.ArrayList;

public class UsuarioMock {
    private static final UsuarioMock INSTANCE = new UsuarioMock();
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private UsuarioMock() {
        usuarios.add(new Docente("Juan Pablo Sanchez", "13456789-5", 'M', "Ing. Informatica", "Arquitecto de Software", "Magister"));
        usuarios.add(new Docente("Maria del Pilar Ramirez", "17892345-K", 'M', "Medico", "Medico Cirujano", "Doctorado"));
        usuarios.add(new Estudiante("Juan Pablo Sanchez", "22345678-2", 'M', "Ing. Informatica", "Arquitectura"));
        usuarios.add(new Docente("Juan Pablo Sanchez", "10234567-3", 'M', "Ing. Informatica", "Arquitecto de Software", "Magister"));
        usuarios.add(new Estudiante("Juan Pablo Sanchez", "19876543-1", 'M', "Diseño Gráfico", "Diseño 3d"));
        usuarios.add(new Estudiante("Juan Pablo Sanchez", "15987654-0", 'M', "Gastronomia", "Chef Ejecutivo"));
    }

    public static UsuarioMock getInstance() {
        return INSTANCE;
    }

    public ArrayList<Usuario> getUsers() {
        return usuarios;
    }

    public void addUserToList(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminarUsuarioDeLista(String rut) {
        usuarios.removeIf(usuario -> usuario.getRut().equals(rut));
    }
}






