# Pom Hotel
Sistema de reservas del Pom Hotel



```code
/***
 *    _▄▄▄▄▄▄▄▄▄▄▄__▄▄▄▄▄▄▄▄▄▄▄__▄▄▄▄▄▄▄▄▄▄▄_______▄▄________▄__▄▄▄▄▄▄▄▄▄▄▄__▄▄▄▄▄▄▄▄▄▄▄__▄▄▄▄▄▄▄▄▄▄▄__▄▄▄▄▄▄▄▄▄▄▄_
 *    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌_____▐░░▌______▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌
 *    ▐░█▀▀▀▀▀▀▀▀▀__▀▀▀▀█░█▀▀▀▀__▀▀▀▀█░█▀▀▀▀______▐░▌░▌_____▐░▌▐░█▀▀▀▀▀▀▀█░▌_▀▀▀▀█░█▀▀▀▀_▐░█▀▀▀▀▀▀▀▀▀_▐░█▀▀▀▀▀▀▀▀▀_
 *    ▐░▌_______________▐░▌__________▐░▌__________▐░▌▐░▌____▐░▌▐░▌_______▐░▌_____▐░▌_____▐░▌__________▐░▌__________
 *    ▐░▌_▄▄▄▄▄▄▄▄______▐░▌__________▐░▌__________▐░▌_▐░▌___▐░▌▐░▌_______▐░▌_____▐░▌_____▐░█▄▄▄▄▄▄▄▄▄_▐░█▄▄▄▄▄▄▄▄▄_
 *    ▐░▌▐░░░░░░░░▌_____▐░▌__________▐░▌__________▐░▌__▐░▌__▐░▌▐░▌_______▐░▌_____▐░▌_____▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌
 *    ▐░▌_▀▀▀▀▀▀█░▌_____▐░▌__________▐░▌__________▐░▌___▐░▌_▐░▌▐░▌_______▐░▌_____▐░▌_____▐░█▀▀▀▀▀▀▀▀▀__▀▀▀▀▀▀▀▀▀█░▌
 *    ▐░▌_______▐░▌_____▐░▌__________▐░▌__________▐░▌____▐░▌▐░▌▐░▌_______▐░▌_____▐░▌_____▐░▌____________________▐░▌
 *    ▐░█▄▄▄▄▄▄▄█░▌_▄▄▄▄█░█▄▄▄▄______▐░▌__________▐░▌_____▐░▐░▌▐░█▄▄▄▄▄▄▄█░▌_____▐░▌_____▐░█▄▄▄▄▄▄▄▄▄__▄▄▄▄▄▄▄▄▄█░▌
 *    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌_____▐░▌__________▐░▌______▐░░▌▐░░░░░░░░░░░▌_____▐░▌_____▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌
 *    _▀▀▀▀▀▀▀▀▀▀▀__▀▀▀▀▀▀▀▀▀▀▀_______▀____________▀________▀▀__▀▀▀▀▀▀▀▀▀▀▀_______▀_______▀▀▀▀▀▀▀▀▀▀▀__▀▀▀▀▀▀▀▀▀▀▀_
 *    _____________________________________________________________________________________________________________
 */

 */

````

## Git Flow

Para usar git flow primero hay que inicializar el repo. ( Solo hay que hacerlo la primera vez)  

```code
git flow init -d

git flow feature start <nombredelafeature>  
````

Ahora modificamos/creamos lo necesario para la feature  

```code
git add .
git commit
git flow feature finish
git push
````

Si al cerrar la feature da error de que tu repo local no esta actualizado  

```code
git checkout develop
git pull
git checkout <nombredelafeature>
git flow feature finish
git push
````

## Ir a un commit especifico del repo  

```code
git log
git reset --hard <numero hexadecimal del commit>
````

## Descartar cambios

```code
git restore src/main/resources/hibernate.cfg.xml
````

Si estan en staged ( git add . ), primero hay que sacarlos  

```code
git restore --staged src/main/java/com/pomhotel/booking/ui/controllers/BookController.java

git clean -fd # Solo si los archivos no estan en staged
````

<!-- Regalito https://www.youtube.com/watch?v=Y6A_Czw8TFU -->

