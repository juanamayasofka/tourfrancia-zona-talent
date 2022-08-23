
# Tour de Francia - Zona de talentos

Como parte del equipo de tecnología que apoya a la competición, se te ha encargado 
la tarea de desarrollar una aplicación o servicio que permita el registro de los equipos
 y sus respectivos ciclistas.

 



## Authors

- [@Juan.amaya](https://github.com/juanamayasofka/tourfrancia-zona-talente)


## Documentation

Se plantea una solución, construyendo una API con spring boot, con gestion de dependencias 
maven, se crean los paquetes para separar por capas, la aplicación.  

### Docker - MongoBD
Se implemento Docker con una imagen de MongoBD 

![image](https://user-images.githubusercontent.com/111831040/186249134-efea5257-5aa0-454c-b1ff-c61fba440963.png)


### Se crean los endPoints para crear country, team y Cyclist 

![image](https://user-images.githubusercontent.com/111831040/186248035-5c402ff0-c062-43e7-8498-f48fe64647d1.png)

![image](https://user-images.githubusercontent.com/111831040/186248102-5f218a4f-a974-4c27-9d21-ffcd90b8c8c2.png)

![image](https://user-images.githubusercontent.com/111831040/186248175-9ba90722-366d-4ae6-914d-1a41745cdb6e.png)


### Otras consultas
se construyen un endPoint para consultar todos los ciclistas de un pais, o de un equipo, 
haciendo uso de la interfaz MongoRepository.

![image](https://user-images.githubusercontent.com/111831040/186248859-50576e65-6dad-4c27-94a1-be1190567bd4.png)

![image](https://user-images.githubusercontent.com/111831040/186248983-159644c2-8892-4a6e-b5af-f38279879c7e.png)


## Pruebas
Se crean pruebas unitarias para probar los motodos y reglas de neogcio, tales como
que los codigos no sean mayores a 3 (team, cyclist), las pruebas se construyen desde el controlador,
para cubrir todo el proceso

![image](https://user-images.githubusercontent.com/111831040/186249922-d2ffa2e9-578e-4dde-8a41-d4393d16c306.png)

