# Juego de Batalla de Soldados

Este proyecto simula una batalla entre dos ejércitos de soldados en un tablero de juego de tamaño 10x10. Los soldados se colocan aleatoriamente en el tablero, y cada ejército compite para ser el último en pie.

## Descripción

La aplicación crea dos ejércitos de soldados, donde cada soldado tiene atributos como nombre, posición en el tablero y puntos de vida. Los soldados pueden moverse por el tablero y enfrentarse en batalla si se encuentran en la misma posición que un soldado enemigo.

La aplicación muestra estadísticas de los soldados de cada ejército, clasifica a los soldados según sus puntos de vida usando diferentes algoritmos de ordenación, y permite a los usuarios simular el juego a través de un sistema de turnos.

### Funcionalidades principales:

- Creación de soldados en posiciones aleatorias en el tablero.
- Visualización del tablero y posición de cada soldado.
- Cálculo de estadísticas como el soldado con mayor vida y el promedio de puntos de vida por ejército.
- Ordenación de los soldados en función de sus puntos de vida utilizando Bubble Sort y Quick Sort.
- Simulación de un juego en turnos, donde cada jugador elige a un soldado y lo mueve en una dirección específica.
- Resolución de combates cuando soldados enemigos se encuentran en la misma casilla.
- Determinación de la victoria de uno de los ejércitos cuando todos los soldados del otro ejército han sido derrotados.

## Estructura de Código

El código se organiza en las siguientes secciones:

1. **Creación de Soldados**: Se crean los soldados de cada ejército y se colocan en posiciones aleatorias del tablero.
2. **Visualización del Tablero**: Muestra el estado actual del tablero y la posición de los soldados de ambos ejércitos.
3. **Estadísticas del Ejército**: Calcula y muestra el soldado con mayor vida y el promedio de puntos de vida de cada ejército.
4. **Ordenación de Soldados por Vida**: Utiliza dos algoritmos de ordenación (Bubble Sort y Quick Sort) para mostrar el ranking de soldados de cada ejército según sus puntos de vida.
5. **Simulación del Juego**: Cada ejército tiene turnos donde los jugadores pueden mover a sus soldados en el tablero. Los soldados que se encuentran con soldados enemigos en la misma casilla luchan hasta que uno de los dos gane.
6. **Verificación de la Continuación del Juego**: Verifica si ambos ejércitos aún tienen soldados vivos para continuar el juego.

## Clases y Métodos

- **App**: La clase principal que contiene la lógica del juego y métodos para la creación de soldados, visualización del tablero, estadísticas, ordenación y simulación del juego.
- **crearSoldados(Soldado[] ejercito, int n, Soldado[][] tablero)**: Crea soldados con nombres únicos en posiciones aleatorias en el tablero.
- **mostrarTablero(Soldado[][] tablero)**: Muestra el tablero y la posición de cada soldado.
- **mostrarEstadisticas(Soldado[] ejercito, int numEjercito)**: Muestra estadísticas del ejército como el soldado con mayor vida y el promedio de vida.
- **ordenarEjercitoPorVidaBubbleSort(Soldado[] ejercito)**: Ordena los soldados de un ejército usando el algoritmo Bubble Sort.
- **ordenarEjercitoPorVidaQuickSort(Soldado[] ejercito, int low, int high)**: Ordena los soldados de un ejército usando el algoritmo Quick Sort.
- **juego(Soldado[] ejercito1, Soldado[] ejercito2, Soldado[][] tablero)**: Simula el juego en turnos, permitiendo a cada jugador seleccionar y mover a un soldado.
- **moverSoldado(Soldado soldado, Soldado[][] tablero, String direccion)**: Mueve un soldado en el tablero en una dirección especificada y gestiona combates si se encuentra con un enemigo.
- **comprobarJuego(Soldado[] ejercito1, Soldado[] ejercito2)**: Verifica si ambos ejércitos tienen soldados vivos para continuar el juego.

## Ejecución

Para ejecutar el programa, asegúrate de tener un entorno de desarrollo Java y utiliza el siguiente comando desde la terminal:

```bash
javac App.java
java App
