import java.util.*;

// Clase Alumno
// Representa un alumno con atributos como nombre, ID, historial de cursos y una calificación general.

public class Alumno implements Calificable {

    private String nombre; // Nombre del alumno
    private String id; // Identificador único del alumno
    private Set<String> cursosCompletados; // Historial de códigos de cursos completados
    private int calificacion; // Calificación general del alumno

    // Constructor
    // Inicializa el alumno con su nombre, ID y estructuras vacías.
    // La calificación inicial se establece en 0.

    public Alumno(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.cursosCompletados = new HashSet<>();
        this.calificacion = 0;
    }

    // Metodo agregarCursoCompletado
    // Agrega el código de un curso al conjunto de cursos completados por el alumno.

    public void agregarCursoCompletado(String codigoCurso) {
        cursosCompletados.add(codigoCurso);
    }

    // Metodo consultarHistorial
    // Devuelve una copia del conjunto de códigos de los cursos completados por el alumno.

    public Set<String> consultarHistorial() {
        return new HashSet<>(cursosCompletados);
    }

    // Metodo haCompletadoCurso
    // Verifica si el alumno ha completado un curso específico, identificado por su código.

    public boolean haCompletadoCurso(String codigoCurso) {
        return cursosCompletados.contains(codigoCurso);
    }

    // Implementación del metodo calificar de la interfaz Calificable
    // Asigna una calificación general al alumno, validando que esté entre 0 y 100.

    @Override
    public void calificar(int nota) {
        if (nota < 0 || nota > 100) {
            throw new ExcepcionOperacionInvalida("La calificación debe estar entre 0 y 100.");
        }
        this.calificacion = nota;
        System.out.println("Alumno " + nombre + " calificado con " + nota + ".");
    }

    // Métodos Getters
    // Permiten acceder a los atributos privados del alumno.

    // Obtiene el nombre del alumno.

    public String getNombre() {
        return nombre;
    }

    // Obtiene el ID del alumno.

    public String getId() {
        return id;
    }

    // Obtiene la calificación general del alumno.

    public int getCalificacion() {
        return calificacion;
    }
}
