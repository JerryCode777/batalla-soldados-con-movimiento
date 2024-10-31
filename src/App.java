import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    private static final int TAM_TABLERO = 10;

    public static void main(String[] args) {
        Soldado[] ejercito1 = new Soldado[10];
        Soldado[] ejercito2 = new Soldado[10];
        Soldado[][] tablero = new Soldado[TAM_TABLERO][TAM_TABLERO];

        crearSoldados(ejercito1, 1, tablero);
        crearSoldados(ejercito2, 2, tablero);

        mostrarTablero(tablero);

        // Muestra el soldado con mayor vida y el promedio de puntos de vida para cada ejército
        mostrarEstadisticas(ejercito1, 1);
        mostrarEstadisticas(ejercito2, 2);

        // Ordenar y mostrar ranking de poder por dos algoritmos
        ordenarEjercitoPorVidaBubbleSort(ejercito1);
        ordenarEjercitoPorVidaQuickSort(ejercito2, 0, ejercito2.length - 1);

        System.out.println("\nRanking de poder del Ejército 1 (Bubble Sort):");
        mostrarRanking(ejercito1);

        System.out.println("\nRanking de poder del Ejército 2 (Quick Sort):");
        mostrarRanking(ejercito2);

        juego(ejercito1, ejercito2, tablero);
    }

    public static void crearSoldados(Soldado[] ejercito, int n, Soldado[][] tablero) {
        for (int i = 0; i < ejercito.length; i++) {
            int fila, col;
            do {
                fila = (int) (Math.random() * TAM_TABLERO);
                col = (int) (Math.random() * TAM_TABLERO);
            } while (tablero[fila][col] != null);

            ejercito[i] = new Soldado("Soldado" + i + "X" + n, fila, col);
            tablero[fila][col] = ejercito[i];
        }
    }

    public static void mostrarTablero(Soldado[][] tablero) {
        System.out.println("Tablero:");
        for (Soldado[] fila : tablero) {
            for (Soldado s : fila) {
                if (s != null) {
                    System.out.print(s.getNombre().charAt(9) == '1' ? "E1-" + s.getNombre().charAt(7) + "\t" : "E2-" + s.getNombre().charAt(7) + "\t");
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }

    public static void mostrarEstadisticas(Soldado[] ejercito, int numEjercito) {
        Soldado soldadoConMayorVida = Arrays.stream(ejercito).max(Comparator.comparingInt(Soldado::getVidaActual)).orElse(null);
        double promedioVida = Arrays.stream(ejercito).mapToInt(Soldado::getVidaActual).average().orElse(0);
        
        System.out.println("\nEstadísticas del Ejército " + numEjercito + ":");
        System.out.println("Soldado con mayor vida: " + soldadoConMayorVida.getNombre() + " (Vida: " + soldadoConMayorVida.getVidaActual() + ")");
        System.out.println("Promedio de puntos de vida: " + promedioVida);
    }

    public static void ordenarEjercitoPorVidaBubbleSort(Soldado[] ejercito) {
        for (int i = 0; i < ejercito.length - 1; i++) {
            for (int j = 0; j < ejercito.length - 1 - i; j++) {
                if (ejercito[j].getVidaActual() < ejercito[j + 1].getVidaActual()) {
                    Soldado temp = ejercito[j];
                    ejercito[j] = ejercito[j + 1];
                    ejercito[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenarEjercitoPorVidaQuickSort(Soldado[] ejercito, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(ejercito, low, high);
            ordenarEjercitoPorVidaQuickSort(ejercito, low, pivotIndex - 1);
            ordenarEjercitoPorVidaQuickSort(ejercito, pivotIndex + 1, high);
        }
    }

    private static int partition(Soldado[] ejercito, int low, int high) {
        int pivot = ejercito[high].getVidaActual();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (ejercito[j].getVidaActual() > pivot) {
                i++;
                Soldado temp = ejercito[i];
                ejercito[i] = ejercito[j];
                ejercito[j] = temp;
            }
        }
        Soldado temp = ejercito[i + 1];
        ejercito[i + 1] = ejercito[high];
        ejercito[high] = temp;
        return i + 1;
    }

    public static void mostrarRanking(Soldado[] ejercito) {
        for (Soldado s : ejercito) {
            System.out.println(s.getNombre() + " - Vida: " + s.getVidaActual());
        }
    }

    public static void juego(Soldado[] ejercito1, Soldado[] ejercito2, Soldado[][] tablero) {
        Scanner scanner = new Scanner(System.in);
        boolean juegoActivo = true;

        while (juegoActivo) {
            for (int i = 0; i < 2; i++) {
                Soldado[] ejercitoActual = (i == 0) ? ejercito1 : ejercito2;
                System.out.println("Turno del Ejército " + (i + 1));

                for (int j = 0; j < ejercitoActual.length; j++) {
                    if (ejercitoActual[j].isVive()) {
                        System.out.println(j + ": " + ejercitoActual[j].getNombre() + " (Vida: " + ejercitoActual[j].getVidaActual() + ")");
                    }
                }
               
                int soldadoIndex;
                do {
                    System.out.print("Seleccione el índice del soldado a mover: ");
                    soldadoIndex = scanner.nextInt();
                } while (soldadoIndex < 0 || soldadoIndex >= ejercitoActual.length || !ejercitoActual[soldadoIndex].isVive());

                String direccion;
                do {
                    System.out.print("Ingrese la dirección (arriba, abajo, izquierda, derecha): ");
                    direccion = scanner.next();
                } while (!direccion.equals("arriba") && !direccion.equals("abajo") && !direccion.equals("izquierda") && !direccion.equals("derecha"));

                moverSoldado(ejercitoActual[soldadoIndex], tablero, direccion);

                juegoActivo = comprobarJuego(ejercito1, ejercito2);
                if (!juegoActivo) {
                    System.out.println("El Ejército " + (i + 1) + " ha ganado!");
                    break;
                }

                mostrarTablero(tablero);
            }
        }
    }

    public static void moverSoldado(Soldado soldado, Soldado[][] tablero, String direccion) {
        int nuevaFila = soldado.getFila();
        int nuevaColumna = soldado.getColumna();

        switch (direccion) {
            case "arriba":
                nuevaFila--;
                break;
            case "abajo":
                nuevaFila++;
                break;
            case "izquierda":
                nuevaColumna--;
                break;
            case "derecha":
                nuevaColumna++;
                break;
        }

        if (nuevaFila >= 0 && nuevaFila < TAM_TABLERO && nuevaColumna >= 0 && nuevaColumna < TAM_TABLERO) {
            if (tablero[nuevaFila][nuevaColumna] != null && tablero[nuevaFila][nuevaColumna].getNombre().charAt(7) != soldado.getNombre().charAt(7)) {
                System.out.println(soldado.getNombre() + " ataca a " + tablero[nuevaFila][nuevaColumna].getNombre());
                if (soldado.getVidaActual() > tablero[nuevaFila][nuevaColumna].getVidaActual()) {
                    System.out.println(soldado.getNombre() + " gana la batalla!");
                    tablero[nuevaFila][nuevaColumna].morir();
                    tablero[nuevaFila][nuevaColumna] = soldado;
                    tablero[soldado.getFila()][soldado.getColumna()] = null;
                    soldado.setFila(nuevaFila);
                    soldado.setColumna(nuevaColumna);
                } else {
                    System.out.println(tablero[nuevaFila][nuevaColumna].getNombre() + " gana la batalla!");
                    soldado.serAtacado(tablero[nuevaFila][nuevaColumna].getNivelAtaque());
                    tablero[nuevaFila][nuevaColumna].morir();
                }
            } else if (tablero[nuevaFila][nuevaColumna] == null) {
                tablero[nuevaFila][nuevaColumna] = soldado;
                tablero[soldado.getFila()][soldado.getColumna()] = null;
                soldado.setFila(nuevaFila);
                soldado.setColumna(nuevaColumna);
            }
        } else {
            System.out.println("Movimiento inválido, fuera del tablero.");
        }
    }
 

    public static boolean comprobarJuego(Soldado[] ejercito1, Soldado[] ejercito2) {
        boolean ejercito1Vivo = Arrays.stream(ejercito1).anyMatch(Soldado::isVive);
        boolean ejercito2Vivo = Arrays.stream(ejercito2).anyMatch(Soldado::isVive);
        return ejercito1Vivo && ejercito2Vivo;
    }

    public static void ordenarEjercitoPorVida(Soldado[] ejercito) {
        Arrays.sort(ejercito, Comparator.comparingInt(Soldado::getVidaActual).reversed());
    }
}
