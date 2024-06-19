# TPI Algoritmos I

## Descripción

Este proyecto consiste en la creación de un Cubo OLAP para la materia Algoritmos I de la Licenciatura en Ciencias de Datos de la UNSAM. Permite cargar múltiples dimensiones y hechos, y aplicar operaciones OLAP como drill down, roll up, dice y slice. Los resultados de estas operaciones se visualizan en una tabla en la consola de Java.

[Documentación de Análisis](https://docs.google.com/document/d/17gkBlP2OFvUuh3LAdyCTUxtuTJLbi6ITmLkvFtz1Jv8/edit?usp=sharing)

[Diagrama de Clases](https://lucid.app/lucidchart/4beb9cb3-b053-48d1-bf2b-58a7db6d44af/edit?viewport_loc=-13%2C164%2C2219%2C1087%2CHWEp-vi-RSFO&invitationId=inv_5564bca7-7fdb-4465-8d7c-a9653e1434a6)

[Trello](https://trello.com/b/wCeHfzTI/tpi-algortimos-i)


## Requisitos

- Java 8 o superior

## Instalación

- Clona este repositorio.

## Uso

Para utilizar la biblioteca seguí estos pasos:

1. **Configuración del Cubo**

   Antes de comenzar, asegurate de tener los archivos CSV necesarios en la ruta especificada (`datasets\`). Estos archivos contienen las dimensiones y los datos de hechos que serán utilizados para construir el cubo.

   ```java
   // Configuración inicial del cubo
   Config cuboConfig = Config.crearConfigCubo("cuboConfig");

   // Agregar dimensiones desde archivos CSV
   cuboConfig.agregarDimension("fechas", path + "fechas.csv", 2);
   cuboConfig.agregarDimension("productos", path + "productos.csv", 0);
   cuboConfig.agregarDimension("puntos_venta", path + "puntos_venta.csv", 1);

   // Agregar datos de hechos desde archivo CSV
   cuboConfig.agregarHechos(path + "ventas.csv");
   ```

2. **Creación y Manipulación del Cubo**

   Una vez configurado, podes crear el cubo y realizar operaciones OLAP como drill down, roll up, slice y dice. Acá tenés ejemplos de cómo utilizar estas operaciones:

   ```java
   // Crear el cubo a partir de la configuración
   Cubo cubo = Cubo.crearCubo(cuboConfig);

   // Ejemplos de operaciones OLAP
   cubo.drillDown("puntos_venta");
   cubo.rollUp("puntos_venta");

   // Filtrado por fecha y proyección de valor unitario
   Cubo cubo2018 = cubo.slice("fechas", 4, "2018.0");
   cubo2018.proyectar("valor_unitario", "suma");

   // Filtrado múltiple por punto de venta y fecha
   ConfigDice configDice = ConfigDice.crearConfigDice();
   configDice.agregarFiltro("puntos_venta", 4, "Europe");
   configDice.agregarFiltro("fechas", 4, "2018.0");
   Cubo cuboDice = cubo.dice("cubito", configDice);
   cuboDice.proyectar("valor_unitario", "suma");

   // proyectar() muestra el cubo en formato tabular, informacion() muestra información del cubo, como el nombre, sus dimensiones y respectivos niveles y los hechos.
   ```

3. **Ejecución y Resultados**

   Ejecuta el programa y sigue las instrucciones en la consola para cargar dimensiones y hechos, así como para realizar las operaciones OLAP deseadas. Los resultados se mostrarán en tablas multi-índices en la consola de Java.

## Contribución

Este proyecto está cerrado a contribuciones externas. Solo los participantes del Trabajo Práctico pueden realizar commits.
