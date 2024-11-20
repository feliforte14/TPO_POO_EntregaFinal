import java.util.*;

// Clase abstracta Curso que implementa la interfaz Calificable
// Representa un curso con un nombre, un código, un límite de alumnos, un conjunto de alumnos inscritos,
// un docente asignado y una calificación promedio.

public abstract class Curso implements Calificable {
    protected String nombre; // Nombre del curso
    protected String codigo; // Código único del curso
    protected int limiteAlumnos; // Número máximo de alumnos permitidos
    protected Set<Alumno> alumnosInscritos; // Conjunto de alumnos inscritos en el curso
    protected Docente docenteAsignado; // Docente asignado al curso
    protected int calificacionPromedio; // Calificación promedio del curso

    // Constructor
    // Inicializa el curso con el nombre, código y límite de alumnos especificados.
    // Crea un conjunto vacío para los alumnos inscritos y establece la calificación promedio en 0.

    public Curso(String nombre, String codigo, int limiteAlumnos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.limiteAlumnos = limiteAlumnos;
        this.alumnosInscritos = new HashSet<>();
        this.calificacionPromedio = 0;
    }

    // Metodo abstracto validarDocente
    // Debe ser implementado por las subclases para definir los criterios de validación de un docente.

    public abstract boolean validarDocente(Docente docente);

    // Metodo asignarDocente
    // Asigna un docente al curso, validando primero que cumple con los requisitos.

    public void asignarDocente(Docente docente) {
        if (!validarDocente(docente)) {
            throw new ExcepcionOperacionInvalida("El docente no cumple con los requisitos para este curso.");
        }
        this.docenteAsignado = docente;
    }

    // Metodo inscribirAlumno
    // Inscribe a un alumno en el curso, validando que haya cupos disponibles y que no esté ya inscrito.

    public void inscribirAlumno(Alumno alumno) {
        if (alumnosInscritos.size() >= limiteAlumnos) {
            throw new ExcepcionOperacionInvalida("No hay cupos disponibles en este curso.");
        }
        if (alumnosInscritos.contains(alumno)) {
            throw new ExcepcionOperacionInvalida("El alumno ya está inscrito en este curso.");
        }
        alumnosInscritos.add(alumno);
        // Agregar el curso al historial del alumno.
        alumno.agregarCursoCompletado(this.codigo);
    }

    // Metodo eliminarAlumno
    // Elimina un alumno del curso.
    public void eliminarAlumno(Alumno alumno) {
        alumnosInscritos.remove(alumno);
    }

    // Metodo eliminarDocente
    // Elimina la asignación del docente al curso.
    public void eliminarDocente() {
        this.docenteAsignado = null;
    }

    // Implementación del metodo calificar de la interfaz Calificable
    // Asigna una calificación promedio al curso, validando que esté en el rango de 0 a 100.

    @Override
    public void calificar(int nota) {
        if (nota < 0 || nota > 100) {
            throw new ExcepcionOperacionInvalida("La calificación debe estar entre 0 y 100.");
        }
        this.calificacionPromedio = nota;
        System.out.println("El curso " + nombre + " recibió una calificación promedio de " + nota + ".");
    }
}
