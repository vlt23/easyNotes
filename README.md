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
* Mandar de forma automática un email cuando se actualice una asignatura
* Mandar de forma automática un email cuando el documento de ha descargado más de (5) veces


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

## Diagrama de clases
![](resREADME/diagramaClases.png)
Debido a problemas de espacio, la relación entre templates y Controller no figura en la gráfica,
sino que se describe a continuación:
* SearchController: buscar_asignatura.html, buscar_carrera.html, buscar_universidad.html, index.html,
resultado_busqueda.html
* SaveController: anadir.html, anadir_asignatura.html, anadir_asignatura_carrera.html, anadir_carrera.html,
anadir_universidad.html, index.html, resultado_guardar,html, subir_asignatura.html, subir_carrera.html,
subir_universidad.html
* DownloaderController: resultado_busqueda.html, resultado_guardar.html
* LoginController: index.html, login_template.html, login_error.html, registro.html
* DeleteController: borrar_apuntes.html, borrar_ok.html

## Intrucciones para desplegar la aplicación
1. Ejecutamos 'mvn -Dmaven.test.skip=true package' para generar tanto
el jar de la aplicación web como del servicio interno.
2. Copiamos los dos jars en una máquina virtual Ubuntu 18.04 (Bionic) mediante
la compartición de carpetas de Virtualbox.
3. En la máquina virtual instalamos los paquetes necesarios para ejecutar los dos jars:
    * 'sudo apt update' para actualizar los índices del repositorio de Ubuntu
    * 'sudo apt install openjdk-8-jre' para instalar la máquina virtual de Java 8
    * 'sudo apt install mysql-server-5.7' para instalar el servidor de mysql 5.7
4. Ejecutamos la aplicación web con 'java -jar easyNotes.jar'.
5. Cambiamos a otro tty y ejecutamos el servicio interno con 'java -jar mailRest.jar'.

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
