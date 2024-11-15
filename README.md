# TutotLink Web

## Descripción
Aplicación Web para gestionar tutorías académicas de distintas temáticas, en diversas asignaturas, impartidas en varias universidades. La aplicación cuenta con tres niveles de acceso (tipos de usuario): estudiante, tutor y administrador.

- **Estudiante**: Puede solicitar clases personalizadas sobre temas específicos y seleccionar tutores de su preferencia. También tiene la posibilidad de unirse a clases previamente creadas.
- **Tutor**: Puede registrar clases, asignar un cupo y definir temas a tratar de una asignatura específica. Cada tutor tiene un perfil que incluye feedback y una puntuación de estudiantes anteriores.
- **Administrador**: Tiene permisos para realizar ABM (alta, baja, modificación y consulta) sobre entidades independientes como universidades y asignaturas.

## Modelo de Datos
Clases principales:
- **Estudiante**
- **Tutor**
- **Administrador**
- **Facultad**
- **Asignatura**
- **Tema**
- **Clase**
- **Tipo_usuario**
- **Reseña**

## Alcance Funcional

### Regularidad
| Requerimiento | Cant. Min (1 o 2 integrantes) | Cant. Max (3 o 4 integrantes) | Detalle/Listado de casos incluidos |
| ------------- | ----------------------------- | ----------------------------- | ---------------------------------- |
| **ABMC simple** | 1 x integrante | 1 x integrante | Facultad, Asignatura, Tema |
| **ABMC dependiente** | 1 | 2 | Clase, Usuario |
| **CU NO-ABMC** | 1 | 2 | CU unirse a una clase (validando cupos, disponibilidad horaria), CU solicitar crear una clase |
| **Listado simple** | 1 | 3 (*) | Listado de clases, Listado de facultades, Listado de asignaturas |
| **Listado complejo** | 0 | 1 (*) | Listado de tutores (por facultad/asignatura/tema) |

(*) Grupos de 3 y 4 integrantes deben elegir entre 1 listado complejo o 3 simples para regularizar.

### Aprobación Directa
| Requerimiento | Cant. Min (1 o 2 integrantes) | Cant. Max (3 o 4 integrantes) | Detalle/Listado de casos incluidos |
| ------------- | ----------------------------- | ----------------------------- | ---------------------------------- |
| **ABMC** | Todos | Todos | Facultad, Asignatura, Tipo_usuario, Tema, Clase, Usuario |
| **CU Complejo (nivel resumen)** | 1 | 2 | CU crear clase a partir de una solicitud (estudiante envía solicitud al tutor, tutor decide crear clase y notifica al estudiante), CU realizar reseña de tutor (el estudiante deja una reseña después de cada clase) |
| **Listado Complejo** | 1 | 2 | Listado de tutores (por facultad/asignatura/tema), Listado de clases (por asignatura, facultad, cupo) |
| **Nivel de acceso** | 2 | 2 | Alumno, Tutor, Admin |
| **Manejo de errores** | Obligatorio | Obligatorio | No requiere detalle |
| **Requerimiento extra obligatorio (**)** | 0 | 1 | Notificar vía email las confirmaciones y cancelaciones de clases a tutores y alumnos |
| **Publicar el sitio** | Obligatorio | Obligatorio | No requiere detalle |

(**) Solo para grupos de 3 y 4 integrantes.

### Requerimientos Extra - AD
| Requerimiento | Detalle/Listado de casos incluidos |
| ------------- | ---------------------------------- |
| **Manejo de archivos** | - |
| **Custom exceptions** | - |
| **Log de errores** | - |
| **Envío de emails** | X |

- **Manejo de errores**: Notificar al usuario si no hay disponibilidad de tutorías en el horario seleccionado.
- **Requerimiento extra**: Envío de correo electrónico de confirmación de tutorías.
