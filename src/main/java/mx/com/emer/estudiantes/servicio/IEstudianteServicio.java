package mx.com.emer.estudiantes.servicio;

import mx.com.emer.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {
    public List<Estudiante> listarEstudiantes();

    public Estudiante buscarEstudiantePorID(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);
}
