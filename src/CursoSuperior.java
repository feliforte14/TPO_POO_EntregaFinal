// Clase CursoSuperior que extiende la clase abstracta Curso
// Representa un curso avanzado que requiere un curso básico completado como prerrequisito y un docente con experiencia mínima.
public class CursoSuperior extends Curso {
    private final String codigoBasicoRequerido; // Código del curso básico que los alumnos deben haber completado

    // Constructor
    // Inicializa un curso superior con el nombre, código, límite de alumnos y el código del curso básico requerido.
    public CursoSuperior(String nombre, String codigo, int limiteAlumnos, String codigoBasicoRequerido) {
        super(nombre, codigo, limiteAlumnos);
        this.codigoBasicoRequerido = codigoBasicoRequerido;
    }

    // Método validarDocente
    // Implementación específica para cursos superiores:
    // Valida que el docente tenga al menos 3 años de antigüedad para impartir el curso.
    @Override
    public boolean validarDocente(Docente docente) {
        return docente.getAntiguedad() >= 3;
    }

    // Método inscribirAlumno
    // Sobrescribe el método de la clase base para incluir validaciones adicionales:
    // - El alumno debe haber completado el curso básico requerido.
    // - El alumno no debe estar ya inscrito.
    // Si cumple los requisitos, delega la inscripción al método de la clase base.
    @Override
    public void inscribirAlumno(Alumno alumno) {
        // Validar que el alumno haya completado el curso básico requerido
        if (!alumno.haCompletadoCurso(codigoBasicoRequerido)) {
            throw new ExcepcionOperacionInvalida(
                    "El alumno no ha completado el curso básico requerido (" + codigoBasicoRequerido + ")."
            );
        }
        // Validar que el alumno no esté duplicado en la lista de inscritos
        if (alumnosInscritos.contains(alumno)) {
            throw new ExcepcionEntidadDuplicada("El alumno ya está inscrito en este curso.");
        }
        // Delegar la inscripción a la clase base
        super.inscribirAlumno(alumno);
    }
}
