# Pom Hotel
Sistema de reservas del Pom Hotel


## Acordaros de hacer las modificaciones a traves de git flow:

Uso simple de git flow

```code
git flow init -d # Solo se debe hacer la primera vez para inicializar el git flow
git flow feature start <nombredelafeature>
Ahora modificamos/creamos lo necesario para la feature
git add .
git commit
git flow feature finish
git push

# Si al cerrar la feature da error (esto esta pendiente de comprobar)
git checkout develop
git pull
git checkout <nombredelafeature>
git flow feature finish
git push
````
