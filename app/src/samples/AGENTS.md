# AGENTS.md — <proyecto>

## Proyecto
- El proyecto es una aplicacion movil creada para  el juego de mesa trench crusade, esta debera permitir crear warbands, guardar warbans, crear camapañas y gestionarlas, tomar registro en vivo de cada juego por cada turno, sus puntos y glorius deaths realizados por cada jugador, ademas de incluir un apartado para leer las reglas tanto en un seccion en especifico como en la de cada personaje arma y juego, esta debe de ser facil de manterner para un ingeniero junior, por lo que se debe de seguir la estructura de carpetas y convenciones de nombres establecidas en la documentación.
- Ademas de que debe de tener la posibilidad de agregar nuevas funcionalidades sin romper las existentes, por lo que se debe de seguir la arquitectura establecida en la documentación y tambien debe permitir los cambios que se hagan en nuevas ediciones del libro de reglas .
- Las reglas que deben seguir se encuentran en la documentación y deben de ser respetadas para mantener la integridad del proyecto.

## Comandos
- Ejecutar: `orden66`
- Tests: `testealo`
- Lint/formato: `dejalobomnito`

## Estilo y convenciones
- Los comentarios se deben de escribir en español, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- Recuerda usar jetbackCompose para la interfaz de usuario, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- El codigo y la documentacion deben de seguir las convenciones establecidas en la documentación, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- el codigo utilizara el lenguaje kotlin, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- El codigo se escribira en ingles y la documentacion en español, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- Los colores usados en la aplicacion deben de seguir la paleta de colores establecida en la documentación, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- El color de los textos debe de seguir la paleta de colores establecida en la documentación, ademas de que se debe de mantener un estilo consistente en todo el proyecto (#e5d8c8).
- Para el fondo principal de la aplicacion se debe de usar el color #1A0B2E, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- Para las barras de navegacion se debe de usar el color #960018, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
- Para los botones se debe de usar el color #960018, ademas de que se debe de mantener un estilo consistente en todo el proyecto.
## Reglas
- Recuerda seguir los principios SOLID para mantener un código limpio y fácil de mantener.
- Recuerda siempre seguir la estructura de carpetas y convenciones de nombres establecidas en la documentación.
- Recuerda siempre leer la documentación antes de realizar cualquier cambio en el código.
- Recuerda que las reglas iran cambiando acorde a nuevos lanzamientos y modificaciones del libro de reglas de trench crusade, por lo que se debe de estar al tanto de los cambios y adaptarse a ellos.
- Recuerda que el proyecto debe de ser facil de mantener para un ingeniero junior, por lo que se debe de seguir la estructura de carpetas y convenciones de nombres establecidas en la documentación.
- Recuerda que el proyecto debe de tener la posibilidad de agregar nuevas funcionalidades sin romper las existentes, por lo que se debe de seguir la arquitectura establecida en la documentación y tambien debe permitir los cambios que se hagan en nuevas ediciones del libro de reglas.
- Recuerda que se debe de leer los libros de reglas de trench crusade antes de realizar cualquier cambio en el código, para entender como funciona el juego y como se deben de implementar las reglas en la aplicacion.
- Lee app/constitution.md y la spec activa antes de tocar código.
- Nunca borres código sin antes preguntar y explicar que es lo que se desea cambiar, además de que se debe de realizar una compilación del código para verificar que no se rompa nada.
- <Límites: qué no tocar: la estructura base antes de realizar testeos correspondientes y una compilaciondel codigo, qué no añadir sin preguntar nuevas funciones al proyecto sin antes realizar una pregunta y explicacion de que es lo que cambio o lo que se desea cambiar.>
- Crea un memori md cada vez que se realicen cambios en el código, para llevar un registro de los cambios realizados y poder volver a una versión anterior si es necesario.
## Al terminar cualquier tarea
- <Verificación obligatoria, ejecutar los tests en donde se han realizado los cambios o las que hayan sido afectadas por estos cambios.>