# easyNotes

 Este repositorio contiene una aplicación web que trata sobre un repositorio de apuntes y exámenes.
 Los usuarios guest (invitados), es decir, los que no están logueados, pueden mirar un preview de los apuntes.
 Para poder descargar los apuntes / exámenes en su totalidad es necesario estar logueado.
 Para poder acceder a exámenes se creará un sistema de micropagos. Estos se podrán conseguir de varias maneras:
 en el primer registro se regalarán, y posteriormente subiendo apuntes o exámenes a la web.

## Funcionalidades públicas (los usuarios guest):
* Leer el documento
* Búsqueda por universdad, asignatura, carrera o tag
* Registrarse y loguearse

## Funcionalidades privadas (usuarios logueados):
* Funcionalidades públicas de los usuarios guest
* Descargar apuntes
* Comprar exámenes
* Publicar apuntes o exámenes en formato PDF
* Seguir asignaturas (notificaciones por mail cuando se publique nuevo contenido)
* Añadir una asignatura (tiene que ser validada por el administrador)(notificacion por mail)

## Funcionalidades privadas (solo los administradores):
* El administrador puede eliminar cualquier documento
* Tiene acceso a todos los datos de contacto de cada usuario
* Validar asignaturas creadas por usuarios

## Entidades
* Apunte: asignatura, carrera, universidad, autor, tamaño, fecha,  valoraciones,número de descargas, tags, tipo (apunte o examen)
* Usuario: tipo de usuario (administrador o registrado), nombre, apellidos, correo electrónico, créditos,
número de descargas
* Universidad: nombre, carreras, asignaturas
* Carrera: nombre, universidad, asignaturas
* Asignatura: nombre, universidad, carrera
* Tag: nombre, apuntes

## Servicio interino
* Mandar de forma automática un email cuando se registre un nuevo usuario. 
* Mandar de forma automática un email cuando el documento de ha descargado más de (5) veces
* Mandar un correo para confirmar que el apunte ha sido bien subido

## Capturas de pantalla
![](resREADME/pag_inicio.png)
Página de inicio, donde están todas las opciones.

![](resREADME/pag_busqueda_resultado.png)
Página de resultados tras buscar apuntes por tags.

![](resREADME/pag_buscar_por_asignatura.png)
Página para buscar todos los apuntes de un determinado asignatura.
Los otros dos páginas (Buscar por Carrera y Buscar por Universidad) tienen la misma estructura.

![](resREADME/pag_subir_apunte.png)
Página para subir los apuntes. Se selecciona la universidad, la titulación y la asignatura.
Se pueden añadir tags si lo desea.

![](resREADME/pag_borrar_apuntes.png)
Página para borrar los apuntes. Cada autor solo puede borrar sus apuntes,
salvo el admin, que puede borrar todos los apuntes.

![](resREADME/pag_login.png)
Página para hacer login.

![](resREADME/pag_registrar.png)
Página para registrar.

## Diagrama de navegación fase 2
![](resREADME/diagrama_navegacion_fase2.png)

## Diagrama de navegación fase 3
![](resREADME/diagrama_navegacion_fase3.png)

## Modelo de datos
![](resREADME/modeloDatos.png)

## Diagrama Entidad/Relación
![E/R](resREADME/diagramaER.png)

## Diagrama de clases y templates
![](resREADME/diagramaclasesPablo.png)


## Diagrama de clases completo
![](resREADME/diagramaClases.png)


## Requisitos para desplegar la aplicación
1. Disponer de una maquina de Ubuntu 18.04
2. Instalar JRE y JDK "apt-get install default-jre" y  "apt-get install default-jdk"
3. Instalar y configurar MySQL. "apt-get install mysql-server" y configurar: "mysql_secure_installation utility"
4. Configurar la direccion IP del SO

## Intrucciones para desplegar la aplicación
1. Crear schema en MySQL 'CREATE SCHEMA easynotes'
2. Compartir archivos *.jar desde SO host asi como el directorio Files
3. Ejecutar mediante java -jar ambos *.jar


## Integrantes
* Guillermo De Azcarate Acosta
    * Correo electrónico: g.deazcarate.2016@alumnos.urjc.es
    * Github: https://github.com/Guilleazca98
* Pablo López Parrilla
    * Correo electrónico: p.lopezpar@alumnos.urjc.es
    * Github: https://github.com/pavloXd
* Valentino Lin
    * Correo electrónico: v.lin.2016@alumnos.urjc.es
    * Github: https://github.com/vlt23
