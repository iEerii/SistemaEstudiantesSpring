package mx.com.emer.estudiantes.repositorio;

import mx.com.emer.estudiantes.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {

}