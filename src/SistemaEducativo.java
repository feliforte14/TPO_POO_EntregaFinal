import java.util.*;

public class SistemaEducativo {

    private final Scanner scanner;
    private final List<Alumno> alumnos;
    private final List<Docente> docentes;
    private final List<Curso> cursos;

    public SistemaEducativo() {
        this.scanner = new Scanner(System.in);
        this.alumnos = new ArrayList<>();
        this.docentes = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            try {
                mostrarMenu();
                int opcion = leerOpcion();

                switch (opcion) {
                    case 1 -> crearAlumno();
                    case 2 -> crearDocente();
                    case 3 -> crearCurso();
                    case 4 -> mostrarAlumnos();
                    case 5 -> mostrarDocentes();
                    case 6 -> listarCursos();
                    case 7 -> mostrarHistorialAlumno();
                    case 8 -> verCalificaciones();
                    case 9 -> consultarDetallesCurso();
                    case 10 -> asignarDocenteACurso();
                    case 11 -> inscribirAlumnoEnCurso();
                    case 12 -> calificarEntidad();
                    case 13 -> eliminarAlumno();
                    case 14 -> eliminarDocente();
                    case 15 -> eliminarCurso();
                    case 16 -> continuar = confirmarSalida();
                    default -> System.out.println("Opción no válida.");
                }
            } catch (ExcepcionEntidadNoEncontrada | ExcepcionOperacionInvalida | ExcepcionEntidadDuplicada e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=======================================");
        System.out.println("         SISTEMA EDUCATIVO             ");
        System.out.println("=======================================");
        System.out.println("[CREACIÓN]");
        System.out.println(" 1. Crear Alumno");
        System.out.println(" 2. Crear Docente");
        System.out.println(" 3. Crear Curso");
        System.out.println("---------------------------------------");
        System.out.println("[CONSULTA]");
        System.out.println(" 4. Mostrar Todos los Alumnos");
        System.out.println(" 5. Mostrar Todos los Docentes");
        System.out.println(" 6. Listar Todos los Cursos");
        System.out.println(" 7. Mostrar Historial de un Alumno");
        System.out.println(" 8. Ver Calificaciones");
        System.out.println(" 9. Consultar Detalles de un Curso");
        System.out.println("---------------------------------------");
        System.out.println("[MODIFICACIÓN]");
        System.out.println("10. Asignar Docente a Curso");
        System.out.println("11. Inscribir Alumno en Curso");
        System.out.println("12. Calificar Entidad");
        System.out.println("---------------------------------------");
        System.out.println("[ELIMINACIÓN]");
        System.out.println("13. Eliminar Alumno");
        System.out.println("14. Eliminar Docente");
        System.out.println("15. Eliminar Curso");
        System.out.println("---------------------------------------");
        System.out.println("[SALIR]");
        System.out.println(" 0. Salir");
        System.out.println("=======================================");
        System.out.print("Seleccione una opción: ");
    }


    public int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // CREACIÓN

    private void crearAlumno() {
        try {
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese nombre del alumno: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese ID del alumno: ");
            String id = scanner.nextLine();
            // Verificar si ya existe un alumno con el mismo ID
            if (alumnos.stream().anyMatch(alumno -> alumno.getId().equals(id))) {
                throw new ExcepcionEntidadDuplicada("Ya existe un alumno con ese ID.");
            }
            // Crear y agregar el nuevo alumno
            alumnos.add(new Alumno(nombre, id));
            System.out.println("Alumno creado con éxito.");
        } catch (ExcepcionEntidadDuplicada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void crearDocente() {
        try {
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese nombre del docente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese ID del docente: ");
            String id = scanner.nextLine();
            // Verificar si ya existe un docente con el mismo ID
            if (docentes.stream().anyMatch(docente -> docente.getId().equals(id))) {
                throw new ExcepcionEntidadDuplicada("Ya existe un docente con ese ID.");
            }
            // Solicitar antigüedad del docente
            System.out.print("Ingrese antigüedad del docente (en años): ");
            int antiguedad = leerOpcion();
            // Solicitar conocimiento de inglés
            System.out.print("¿El docente tiene conocimiento de inglés? (true/false): ");
            boolean conocimientoIngles = scanner.nextBoolean();
            // Crear y agregar el nuevo docente
            docentes.add(new Docente(nombre, id, antiguedad, conocimientoIngles));
            System.out.println("Docente creado con éxito.");
        } catch (ExcepcionEntidadDuplicada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Asegúrese de ingresar datos en el formato correcto.");
            scanner.nextLine(); // Limpiar buffer después de una entrada inválida
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void crearCurso() {
        try {
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese nombre del curso: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese código del curso: ");
            String codigo = scanner.nextLine();
            // Verificar si ya existe un curso con el mismo código
            if (cursos.stream().anyMatch(curso -> curso.codigo.equals(codigo))) {
                throw new ExcepcionEntidadDuplicada("Ya existe un curso con ese código.");
            }
            // Solicitar límite de alumnos
            System.out.print("Ingrese límite de alumnos: ");
            int limite = leerOpcion();
            if (limite <= 0) {
                throw new IllegalArgumentException("El límite de alumnos debe ser mayor a 0.");
            }
            // Selección del tipo de curso
            System.out.println("Seleccione el tipo de curso:");
            System.out.println("1. Curso Básico");
            System.out.println("2. Curso Superior");
            System.out.println("3. Curso de Inglés");
            int tipo = leerOpcion();
            scanner.nextLine(); // Limpiar el buffer después de leer la opción

            // Crear el curso según el tipo seleccionado
            switch (tipo) {
                case 1 -> cursos.add(new CursoBasico(nombre, codigo, limite));
                case 2 -> {
                    System.out.print("Ingrese código del curso básico requerido: ");
                    String codigoBasicoRequerido = scanner.nextLine();
                    cursos.add(new CursoSuperior(nombre, codigo, limite, codigoBasicoRequerido));
                }
                case 3 -> cursos.add(new CursoIngles(nombre, codigo, limite));
                default -> throw new ExcepcionOperacionInvalida("Tipo de curso no válido.");
            }
            // Confirmar creación exitosa
            System.out.println("Curso creado con éxito.");
        } catch (ExcepcionEntidadDuplicada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Asegúrese de ingresar datos en el formato correcto.");
            scanner.nextLine(); // Limpiar buffer después de una entrada inválida
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // CONSULTA

    private void mostrarAlumnos() {
        try {
            System.out.println("Lista de Alumnos:");
            // Validar si la lista de alumnos está inicializada y no está vacía
            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos registrados.");
            } else {
                // Iterar por la lista de alumnos y mostrar sus detalles
                alumnos.forEach(alumno -> {
                    if (alumno != null) {
                        System.out.println("- " + alumno.getNombre() + " (ID: " + alumno.getId() + ")");
                    } else {
                        System.out.println("Error: Se encontró un registro de alumno nulo.");
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error inesperado al mostrar los alumnos: " + e.getMessage());
        }
    }

    private void mostrarDocentes() {
        try {
            System.out.println("Lista de Docentes:");
            // Validar si la lista de docentes está inicializada y no está vacía
            if (docentes.isEmpty()) {
                System.out.println("No hay docentes registrados.");
            } else {
                // Iterar por la lista de docentes y mostrar sus detalles
                docentes.forEach(docente -> {
                    if (docente != null) {
                        System.out.println("- " + docente.getNombre() + " (ID: " + docente.getId() + ")");
                    } else {
                        System.out.println("Error: Se encontró un registro de docente nulo.");
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error inesperado al mostrar los docentes: " + e.getMessage());
        }
    }

    private void listarCursos() {
        try {
            System.out.println("Lista de Cursos:");
            // Validar si la lista de cursos está inicializada y no está vacía
            if (cursos.isEmpty()) {
                System.out.println("No hay cursos registrados.");
            } else {
                // Iterar por la lista de cursos y mostrar sus detalles
                cursos.forEach(curso -> {
                    if (curso != null) {
                        System.out.println("- " + curso.nombre + " (Código: " + curso.codigo + ")");
                    } else {
                        System.out.println("Error: Se encontró un registro de curso nulo.");
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error inesperado al listar los cursos: " + e.getMessage());
        }
    }

    private void mostrarHistorialAlumno() {
        try {
            Alumno alumno = seleccionarAlumno();
            if (alumno == null) {
                System.out.println("No se seleccionó ningún alumno.");
                return;
            }
            System.out.println("Historial del alumno " + alumno.getNombre() + ":");
            Set<String> historial = alumno.consultarHistorial();
            if (historial == null || historial.isEmpty()) {
                System.out.println("El alumno no tiene cursos completados en su historial.");
            } else {
                historial.forEach(curso -> System.out.println("- " + curso));
            }
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al mostrar el historial del alumno: " + e.getMessage());
        }
    }

    private void verCalificaciones() {
        try {
            System.out.println("\n=== Calificaciones de Alumnos ===");
            // Validar si la lista de alumnos está inicializada y no está vacía
            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos registrados.");
            } else {
                // Iterar por la lista de alumnos y mostrar sus calificaciones
                alumnos.forEach(alumno -> {
                    if (alumno != null) {
                        System.out.println("- " + alumno.getNombre() + " (ID: " + alumno.getId() + ") | Calificación: " + alumno.getCalificacion());
                    } else {
                        System.out.println("Error: Se encontró un registro de alumno nulo.");
                    }
                });
            }
            System.out.println("\n=== Calificaciones de Docentes ===");
            // Validar si la lista de docentes está inicializada y no está vacía
            if (docentes.isEmpty()) {
                System.out.println("No hay docentes registrados.");
            } else {
                // Iterar por la lista de docentes y mostrar sus calificaciones
                docentes.forEach(docente -> {
                    if (docente != null) {
                        System.out.println("- " + docente.getNombre() + " (ID: " + docente.getId() + ") | Calificación: " + docente.getCalificacion());
                    } else {
                        System.out.println("Error: Se encontró un registro de docente nulo.");
                    }
                });
            }
            System.out.println("\n=== Calificaciones de Cursos ===");
            // Validar si la lista de cursos está inicializada y no está vacía
            if (cursos.isEmpty()) {
                System.out.println("No hay cursos registrados.");
            } else {
                // Iterar por la lista de cursos y mostrar sus calificaciones
                cursos.forEach(curso -> {
                    if (curso != null) {
                        System.out.println("- " + curso.nombre + " (Código: " + curso.codigo + ") | Calificación Promedio: " + curso.calificacionPromedio);
                    } else {
                        System.out.println("Error: Se encontró un registro de curso nulo.");
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error inesperado al mostrar las calificaciones: " + e.getMessage());
        }
    }

    private void consultarDetallesCurso() {
        try {
            Curso curso = seleccionarCurso();
            if (curso == null) {
                System.out.println("No se seleccionó ningún curso.");
                return;
            }
            System.out.println("\n--- Detalles del Curso ---");
            System.out.println("Nombre: " + curso.nombre);
            System.out.println("Código: " + curso.codigo);
            System.out.println("Límite de alumnos: " + curso.limiteAlumnos);
            // Mostrar alumnos inscritos
            System.out.println("Alumnos inscritos:");
            if (curso.alumnosInscritos == null || curso.alumnosInscritos.isEmpty()) {
                System.out.println("No hay alumnos inscritos en este curso.");
            } else {
                curso.alumnosInscritos.forEach(alumno -> {
                    if (alumno != null) {
                        System.out.println("- " + alumno.getNombre() + " (ID: " + alumno.getId() + ")");
                    } else {
                        System.out.println("Error: Se encontró un registro de alumno nulo en este curso.");
                    }
                });
            }
            // Mostrar docente asignado
            System.out.println("Docente asignado: " +
                    (curso.docenteAsignado != null ? curso.docenteAsignado.getNombre() : "No asignado"));
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al consultar los detalles del curso: " + e.getMessage());
        }
    }

    // ELIMINAR

    private void eliminarAlumno() {
        mostrarAlumnos();
        System.out.print("Ingrese el ID del alumno a eliminar: ");
        String id = scanner.next();
        // Buscar al alumno
        Alumno alumno = alumnos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ExcepcionEntidadNoEncontrada("Alumno no encontrado."));
        // Eliminar al alumno de todos los cursos
        cursos.forEach(curso -> curso.eliminarAlumno(alumno));
        // Eliminar al alumno de la lista principal
        alumnos.remove(alumno);
        System.out.println("Alumno eliminado con éxito.");
    }

    private void eliminarDocente() {
        mostrarDocentes();
        System.out.print("Ingrese el ID del docente a eliminar: ");
        String id = scanner.next();
        // Buscar al docente
        Docente docente = docentes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ExcepcionEntidadNoEncontrada("Docente no encontrado."));
        // Eliminar al docente de todos los cursos
        cursos.forEach(curso -> {
            if (curso.docenteAsignado != null && curso.docenteAsignado.equals(docente)) {
                curso.eliminarDocente();
            }
        });

        // Eliminar al docente de la lista principal
        docentes.remove(docente);
        System.out.println("Docente eliminado con éxito.");
    }

    private void eliminarCurso() {
        listarCursos();
        System.out.print("Ingrese el código del curso a eliminar: ");
        String codigo = scanner.next();
        boolean eliminado = cursos.removeIf(curso -> curso.codigo.equals(codigo));
        System.out.println(eliminado ? "Curso eliminado con éxito." : "Curso no encontrado.");
    }

// Modificacion

    private void asignarDocenteACurso() {
        try {
            // Seleccionar el curso
            Curso curso = seleccionarCurso();
            if (curso == null) {
                System.out.println("No se seleccionó ningún curso.");
                return;
            }
            // Seleccionar el docente
            Docente docente = seleccionarDocente();
            if (docente == null) {
                System.out.println("No se seleccionó ningún docente.");
                return;
            }
            // Intentar asignar el docente al curso
            try {
                curso.asignarDocente(docente);
                System.out.println("Docente asignado al curso con éxito.");
            } catch (ExcepcionOperacionInvalida e) {
                System.out.println("Error al asignar el docente: " + e.getMessage());
            }
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al intentar asignar el docente al curso: " + e.getMessage());
        }
    }

    private void inscribirAlumnoEnCurso() {
        try {
            // Seleccionar el curso
            Curso curso = seleccionarCurso();
            if (curso == null) {
                System.out.println("No se seleccionó ningún curso.");
                return;
            }
            // Seleccionar el alumno
            Alumno alumno = seleccionarAlumno();
            if (alumno == null) {
                System.out.println("No se seleccionó ningún alumno.");
                return;
            }
            // Intentar inscribir al alumno en el curso
            try {
                curso.inscribirAlumno(alumno);
                System.out.println("Alumno inscrito en el curso con éxito.");
            } catch (ExcepcionOperacionInvalida e) {
                System.out.println("Error al inscribir al alumno: " + e.getMessage());
            }
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al intentar inscribir al alumno en el curso: " + e.getMessage());
        }
    }

    private void calificarEntidad() {
        try {
            // Mostrar opciones al usuario
            System.out.println("Seleccione una entidad para calificar:");
            System.out.println("1. Alumno");
            System.out.println("2. Curso");
            System.out.println("3. Docente");
            int opcion = leerOpcion();
            // Seleccionar la entidad a calificar
            Calificable calificable = switch (opcion) {
                case 1 -> seleccionarAlumno();
                case 2 -> seleccionarCurso();
                case 3 -> seleccionarDocente();
                default -> null;
            };
            if (calificable == null) {
                System.out.println("Opción inválida o entidad no seleccionada.");
                return;
            }
            // Solicitar la calificación
            System.out.print("Ingrese la calificación (0-100): ");
            int nota = leerOpcion();
            // Validar que la calificación esté dentro del rango permitido
            if (nota < 0 || nota > 100) {
                throw new IllegalArgumentException("La calificación debe estar entre 0 y 100.");
            }
            // Calificar la entidad
            calificable.calificar(nota);
            System.out.println("La entidad ha sido calificada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al calificar la entidad: " + e.getMessage());
        }
    }


    private boolean confirmarSalida() {
        System.out.print("¿Está seguro de que desea salir? (s/n): ");
        String respuesta = scanner.next().toLowerCase();
        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.print("Respuesta inválida. Ingrese 's' para sí o 'n' para no: ");
            respuesta = scanner.next().toLowerCase();
        }
        return !respuesta.equals("s");
    }

    // Métodos para seleccionar entidades

    private Curso seleccionarCurso() {
        try {
            listarCursos();

            // Solicitar el código del curso
            System.out.print("Ingrese el código del curso: ");
            String codigo = scanner.next();
            // Validar si el código ingresado no es nulo o vacío
            if (codigo == null || codigo.trim().isEmpty()) {
                throw new ExcepcionOperacionInvalida("El código del curso no puede estar vacío.");
            }
            // Buscar el curso
            return cursos.stream()
                    .filter(curso -> curso.codigo.equals(codigo))
                    .findFirst()
                    .orElseThrow(() -> new ExcepcionEntidadNoEncontrada("Curso no encontrado."));
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error inesperado al seleccionar el curso: " + e.getMessage());
            return null;
        }
    }

    private Alumno seleccionarAlumno() {
        try {
            mostrarAlumnos();
            // Solicitar el ID del alumno
            System.out.print("Ingrese el ID del alumno: ");
            String id = scanner.next();
            // Validar si el ID ingresado no es nulo o vacío
            if (id == null || id.trim().isEmpty()) {
                throw new ExcepcionOperacionInvalida("El ID del alumno no puede estar vacío.");
            }
            // Buscar el alumno
            return alumnos.stream()
                    .filter(alumno -> alumno.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ExcepcionEntidadNoEncontrada("Alumno no encontrado."));
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error inesperado al seleccionar el alumno: " + e.getMessage());
            return null;
        }
    }

    private Docente seleccionarDocente() {
        try {
            mostrarDocentes();

            // Solicitar el ID del docente
            System.out.print("Ingrese el ID del docente: ");
            String id = scanner.next();
            // Validar si el ID ingresado no es nulo o vacío
            if (id == null || id.trim().isEmpty()) {
                throw new ExcepcionEntidadNoEncontrada("El ID del docente no puede estar vacío.");
            }
            // Buscar al docente
            return docentes.stream()
                    .filter(docente -> docente.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ExcepcionEntidadNoEncontrada("Docente no encontrado."));
        } catch (ExcepcionEntidadNoEncontrada e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error inesperado al seleccionar al docente: " + e.getMessage());
            return null;
        }
    }


}
