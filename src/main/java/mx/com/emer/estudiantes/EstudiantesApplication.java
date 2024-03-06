package mx.com.emer.estudiantes;

import mx.com.emer.estudiantes.modelo.Estudiante;
import mx.com.emer.estudiantes.servicio.IEstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private IEstudianteServicio estudianteServicio;

	private static final Logger logger =
			LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		//Se levanta la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando metodo run de Spring" + nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir) {
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}

	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion: """);
	}

	private boolean ejecutarOpciones(Scanner consola) {
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion) {
			case 1 -> { //Listar estudiantes
				logger.info(nl + "Listado de Estudiantes" + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString() + nl ));
			}
			case 2 -> { //Buscar estudiantes
				logger.info("Introduce el ID del estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante =
						estudianteServicio.buscarEstudiantePorID(idEstudiante);
				if(estudiante != null)
					logger.info("Estudiante encontrado: " + estudiante + nl);
				else
					logger.info("Estudiante no encontrado con el ID: " + idEstudiante +nl);
			}
			case 3 -> { //Agregar estudiante
				logger.info("Agregar estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();
				//Se crea el objeto estudiante sin id
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: " + estudiante + nl);
			}
			case 4 -> { //Modificar estudiante
				logger.info("Modificar estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//Se busca el estudiante a modificar
				Estudiante estudiante =
						estudianteServicio.buscarEstudiantePorID(idEstudiante);
				if(estudiante != null) {
					logger.info("Nombre:");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: " + estudiante + nl);
				} else
					logger.info("Estudiante no encontrado con el id: " + idEstudiante + nl);
			}
			case 5 -> { //Eliminar estudiante
				logger.info("Eliminar estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//Buscar el id a eliminar
				var estudiante = estudianteServicio.buscarEstudiantePorID(idEstudiante);
				if(estudiante != null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: " + estudiante + nl);
				}
				else
					logger.info("Estudiante no encontrado con id" + idEstudiante);
			}
			case 6 -> { //Salir
				logger.info("¡Hasta Pronto!" + nl + nl);
				salir = true;
			}
			default -> logger.info("Opcion no reconocida: " + opcion + nl);
		}
		return salir;
	}
}
