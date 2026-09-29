# Spec NNN — <Warbands, campains and tournaments creator and editor and game tracker> 

## Contexto y objetivo
El problema es que los jugadores deben de tener una forma de crear y editar sus warbands de manera sencilla y rápida, ademas de que deben de poder guardar sus warbands para poder usarlas en futuras partidas, ademas de que deben de poder crear campañas y gestionarlas, ademas de que deben de poder tomar registro en vivo de cada juego por cada turno, sus puntos y glorius deaths realizados por cada jugador, ademas de que deben de poder leer las reglas tanto en una sección en especifico como en la de cada personaje arma y juego, ademas de que debe ser facil de mantener para un ingeniero junior, por lo que se debe seguir la estructura de carpetas y convenciones de nombres establecidas en la documentación y el libro de reglas de
su respectiva warband. esta debe de mostar todas las facciones disponibles sus reglas especificas, la cantidad de ducados y puntos de gloria disponibles al inicio de la campaña y debera permitir el nombre la faccion de los demas jugadores de la warband y la tabla de 
clasificacion de la camapaña. se debe de revisar la carpeta sources para entender las reglas del juego.

## Usuarios / actores
Lo usaran jugadores de Trench Crusade, que son personas que juegan el juego de mesa Trench Crusade y quieren tener una forma de crear, actualizar y editar sus warbands, guardar sus warbands, crear campañas y gestionarlas, tomar registro en vivo de cada juego por cada turno, sus puntos y glorius deaths realizados por cada jugador, ademas de que deben de poder leer las reglas tanto en una sección en especifico como en la de cada personaje arma y juego.

## Historias de usuario
- H1: Como <creador de la campaña> quiero <gestionar las fases de la camapaña> para <evitar alteraciones y cambios de la warband en medio de la ronda>.
- H2: Como <jugador> quiero <crear, actualizar y editar mi warband> para <poder jugar con ella en la campaña>.
- H3 : Como <jugador> quiero <guardar mi warband> para <poder usarla en futuras partidas>.
- H4: Como <jugador> quiero <crear campañas y gestionarlas> para <poder jugar con mis amigos y llevar un registro de los resultados>.
- H5: Como <jugador> quiero <tomar registro en vivo de cada juego por cada turno, sus puntos y glorius deaths realizados por cada jugador> para <poder llevar un control de la partida y ver los resultados en tiempo real>.
- H6: Como <jugador> quiero <leer las reglas tanto en una sección en especifico como en la de cada personaje arma y juego> para <poder entender las reglas del juego y aplicarlas correctamente en la partida>.
- H7 como <jugador> quiero <que la aplicacion sea facil de mantener para un ingeniero junior> para <poder agregar nuevas funcionalidades sin romper las existentes y seguir la arquitectura establecida en la documentación y tambien debe permitir los cambios que se hagan en nuevas ediciones del libro de reglas>.

## Requisitos funcionales (criterios de aceptación en EARS)
- RF-1: CUANDO <inicie la fase de cuartel>, EL SISTEMA <permita editar las warbands acorde a los resultados del juego y las reglas de la campaña> (salida/resultado solo permitir editar durante esta fase y no durante la ronda).
- RF-2: SI <se intenta editar o cambiar la warband en otra fase que no sea la fase de cuartel>, ENTONCES EL SISTEMA <no permitira edicion>.
- RF-3: MIENTRAS <se este en fase de cuartel no se podran leer las warbands de los demas participantes de la campaña>, EL SISTEMA <no permitira entrar a visualizar los cambios realizados en la warband rival pero podra seguir viendo el nombre del jugador, la warband y la faccion a la que pertenece>.
- RF-4: EL SISTEMA <debera de actuzalizar y llevar un registro de los partidos y los cambios realizados en la warband durante esa ronda para evitar trampas o errores>.

## Requisitos no funcionales

- la aplicacion debe de ser posible de utilizar en dispositivos moviles con android 10 o superior, ademas de que debe de ser facil de mantener para un ingeniero junior, por lo que se debe de seguir la estructura de carpetas y convenciones de nombres establecidas en la documentación y el libro de reglas de su respectiva warband. esta debe de mostar todas las facciones disponibles sus reglas especificas, la cantidad de ducados y puntos de gloria disponibles al inicio de la campaña y debera permitir el nombre la faccion de los demas jugadores de la warband y la tabla de clasificacion de la camapaña.
- Durante los juegos cuando se va a iniciar uno el jugador a debe presionar un voton y el jugador b tambien para confirmar el juego, luego dependiendo si la campaña ya definio escenario para la ronda monstar la imagen del escenario y su despliegue y reglas a menos que no este defininido el mapa, en dado caso se debedera de lanzar un numero aleatorio referente a los escenarios disponibles para esa ronda de campaña o partida amistosa (todos los escenarios disponibles en el libro de reglas general). una vez terminado el despliugue ambos jugadores deben de dar al boton de iniciar partida para que el sistema comience a llevar el registro de los puntos y glorius deaths (uno por jugador y partida) de cada jugador, y que permita contar los puntos de victoria por turno de juego, ademas de que debera de mostrar un boton para finalizar la partida y dar por terminada la ronda, una vez finalizada la ronda el sistema debera de mostrar un resumen de los resultados y cambios realizados en la warband de cada jugador, si se murio algun modelo, consiguio expereincia, alguna habilidad nueva o si se realizo exploracion o solicitud de refuerzos, ademas de que debera de permitir al creador de la campaña iniciar la fase de cuartel para que los jugadores puedan editar sus warbands acorde a los resultados del juego y las reglas de la campaña.
- El jugador nunca podra ver las warbands de los demas jugadores hasta que se termine la fase de cuartel, ademas de que no podra editar su warband en otra fase que no sea la fase de cuartel, ademas de que el sistema debera de actuzalizar y llevar un registro de los partidos y los cambios realizados en la warband durante esa ronda para evitar trampas o errores y los demas jugadores podran ver los resultados de las demas partidas.
- Los jugadores solo podran editar sus propias warbands, el creador de la campaña sera el unico que permita crear una nueva ronda o seleccionar escenario de la campaña, ademas que los emparejamientos se haran de manera aleatoria y el sistema debera de llevar un registro de los emparejamientos realizados para evitar que se repitan en la misma campaña a menos que las rondas superen el numero de jugadores disponibles, por lo cual se puede volver a emparejar a los mismos jugadores solamente cuando ya hayan jugado con todos los demas jugadores de la campaña, ademas de que el sistema debera de llevar un registro de los resultados de cada partida y los cambios realizados en la warband durante esa ronda para evitar trampas o errores y los demas jugadores podran ver los resultados de las demas partidas.

## Casos límite
<Vacíos, duplicados, datos corruptos, límites, concurrencia...>
- Las campañas se registraran en la base de datos y se podran crear, editar y eliminar, ademas de que se podran crear, editar y eliminar las warbands de cada jugador, ademas de que se podran crear, editar y eliminar los escenarios disponibles para cada ronda de campaña o partida amistosa (todos los escenarios disponibles en el libro de reglas general).
- No se pude tener dos warbands iguales en la misma campaña, ademas de que no se puede tener dos jugadores con el mismo nombre en la misma campaña, ademas de que no se puede tener dos jugadores con la misma faccion en la misma campaña, ademas de que no se puede tener dos jugadores con la misma warband en la misma campaña, ademas de que no se puede tener dos jugadores con la misma warband y faccion en la misma campaña, ademas de que no se puede tener dos jugadores con la misma warband y faccion y nombre en la misma campaña.
- En torneo una vez se haya iniciado la ronda ningun jugador podra editar su warband hasta que se termine el torneo y se hayan concluido todas las rondas del torneo y se revele el ganador de este

## Fuera de alcance
<Lo que explícitamente NO se hace en esta iteración.>

## Criterios de finalización
<Ej.: todos los RF con test en verde + demo manual del flujo principal.>

## Dudas abiertas
- [NECESITA ACLARACIÓN] <si no se entiende alguna instruccion dada por el programador o los usuarios>