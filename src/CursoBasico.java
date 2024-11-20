// Clase CursoBasico que extiende la clase abstracta Curso
// Representa un curso básico sin requisitos adicionales para docentes o alumnos.
public class CursoBasico extends Curso {

    // Constructor
    // Inicializa un curso básico con el nombre, código y límite de alumnos especificados.
    public CursoBasico(String nombre, String codigo, int limiteAlumnos) {
        super(nombre, codigo, limiteAlumnos);
    }

    // Método validarDocente
    // Implementación específica para cursos básicos:
    // Cualquier docente puede impartir un curso básico, por lo que siempre devuelve true.
    @Override
    public boolean validarDocente(Docente docente) {
        return true;
    }

    // Método inscribirAlumno
    // Sobrescribe el método de la clase base para validar que un alumno no esté duplicado.
    // Si el alumno ya está inscrito, lanza una excepción personalizada.
    @Override
    public void inscribirAlumno(Alumno alumno) {
        if (alumnosInscritos.contains(alumno)) {
            throw new ExcepcionEntidadDuplicada("El alumno ya está inscrito en este curso.");
        }
        // Llama al método inscribirAlumno de la clase base para manejar la inscripción.
        super.inscribirAlumno(alumno);
    }
}
