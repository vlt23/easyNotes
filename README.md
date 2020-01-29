# easyNotes

 Este repositorio contiene una aplicación web que trata sobre un repositorio de apuntes y exámenes.
 Los usuarios guest (invitados), es decir, los que no están logueados, pueden mirar un preview de los apuntes.
 Para poder descargar los apuntes / exámenes en su totalidad es necesario estar logueado.
 Para poder acceder a examenes se creará un sistema de micropagos. Estos se podrán conseguir de varias maneras:
 en el primer registro se regalarán y subiendo apuntes o examenes a la web
 
## Funcionalidades públicas (los usuarios guest):
* Leer las primeras páginas (preview) de un documento 
* Buscar por universdad, asignatura o carrera

## Funcionalidades públicas (usuarios logueados):
* Descargar apuntes en formato original
* Comprar examenes
* Publicar apuntes o exámenes
* Seguir asignaturas (notificaciones por mail cuando se publique nuevo contenido)
* Añadir una asignatura (tiene que ser calidad por el administrador)

## Funcionalidades privadas (solo los administradores):
* El administrador puede eliminar cualquier documento
* El administrador puede banear a cualquier usuario
* Tiene acceso a todos los datos de contacto de cada usuario
* Validar asignaturas creadas por usuarios

## Entidades
* Apunte: categoria (teoria, ejercicios), tipo de dato, tamaño, fecha, opiniones, valoraciones, número de descargas, tags
* Examen: asignatura, profesor, tamaño, fecha, opiniones, valoraciones, tags
* Asignatura: universidad, carrera, profesores
* Usuario: tipo de usuario, nombre, apellidos, correo electrónico, puntuación, número de descargas
* Universidad: nombre, campus

## Servicio interino
* [TODO]

## Integrantes
* Guillermo De Azcarate Acosta --> g.deazcarate.2016@alumnos.urjc.es
* Pablo López Parrilla --> p.lopezpar@alumnos.urjc.es
* Valentino Lin --> v.lin.2016@alumnos.urjc.es
