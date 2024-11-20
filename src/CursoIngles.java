// Clase CursoIngles que extiende la clase abstracta Curso
// Representa un curso especializado en inglés, con requisitos específicos para docentes.
public class CursoIngles extends Curso {

    // Constructor
    // Inicializa un curso de inglés con el nombre, código y límite de alumnos especificados.
    public CursoIngles(String nombre, String codigo, int limiteAlumnos) {
        super(nombre, codigo, limiteAlumnos);
    }

    // Método validarDocente
    // Implementación específica para cursos de inglés:
    // Valida que el docente tenga conocimientos de inglés utilizando el método correspondiente.
    @Override
    public boolean validarDocente(Docente docente) {
        return docente.tieneConocimientoIngles();
    }

    // Método inscribirAlumno
    // Sobrescribe el método de la clase base para validar que el alumno no esté duplicado.
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
