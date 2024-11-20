// Clase Docente
// Representa a un docente con atributos como nombre, ID, antigüedad, conocimiento de inglés y calificación.
public class Docente implements Calificable {
    private String nombre; // Nombre del docente
    private String id; // Identificador único del docente
    private int antiguedad; // Antigüedad del docente en años
    private boolean conocimientoIngles; // Indica si el docente tiene conocimientos de inglés
    private int calificacion; // Calificación asignada al docente

    // Constructor
    // Inicializa un docente con los datos básicos: nombre, ID, antigüedad y conocimiento de inglés.
    // La calificación inicial se establece en 0.
    public Docente(String nombre, String id, int antiguedad, boolean conocimientoIngles) {
        this.nombre = nombre;
        this.id = id;
        this.antiguedad = antiguedad;
        this.conocimientoIngles = conocimientoIngles;
        this.calificacion = 0;
    }

    // Metodo tieneConocimientoIngles
    // Devuelve true si el docente tiene conocimientos de inglés, false en caso contrario.
    public boolean tieneConocimientoIngles() {
        return conocimientoIngles;
    }

    // Métodos Getters
    // Permiten acceder a los atributos privados del docente.

    // Obtiene el nombre del docente.
    public String getNombre() {
        return nombre;
    }

    // Obtiene el ID del docente.
    public String getId() {
        return id;
    }

    // Obtiene la antigüedad del docente.
    public int getAntiguedad() {
        return antiguedad;
    }

    public int getCalificacion(){
        return calificacion;
    }

    // Implementación del metodo calificar de la interfaz
    // Asigna una calificación al docente, validando que esté en el rango de 0 a 100.

    @Override
    public void calificar(int nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100.");
        }
        this.calificacion = nota;
        System.out.println("Docente " + nombre + " calificado con " + nota + ".");
    }
}
