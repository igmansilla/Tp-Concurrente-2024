package algoritmos;

import java.util.concurrent.RecursiveTask;

public class QuickSortConcurrente extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    int inicio, fin;
    int[] arreglo;

    // Constructor
    public QuickSortConcurrente(int inicio, int fin, int[] arreglo) {
        this.arreglo = arreglo; // Guarda el arreglo a ordenar
        this.inicio = inicio;   // Guarda el índice inicial del segmento del arreglo a ordenar
        this.fin = fin;         // Guarda el índice final del segmento del arreglo a ordenar
    }

    // Método para particionar el arreglo
    private int particion(int inicio, int fin, int[] arreglo) {
        int pivote = arreglo[inicio + (fin - inicio) / 2]; // Establece el elemento medio como pivote
        int izquierda = inicio - 1; // Inicializa el índice izquierdo para la exploración
        int derecha = fin + 1; // Inicializa el índice derecho para la exploración

        while (true) {
            do {
                izquierda++; // Incrementa el índice izquierdo hasta encontrar un elemento mayor que el pivote
            } while (arreglo[izquierda] < pivote);

            do {
                derecha--; // Decrementa el índice derecho hasta encontrar un elemento menor que el pivote
            } while (arreglo[derecha] > pivote);

            if (izquierda >= derecha) {
                return derecha; // Devuelve el índice de partición donde los elementos están divididos
            }

            // Intercambia los elementos en índices izquierda y derecha
            int temp = arreglo[izquierda];
            arreglo[izquierda] = arreglo[derecha];
            arreglo[derecha] = temp;
        }
    }

    // Método compute que define la lógica concurrente del algoritmo QuickSort
    @Override
    protected Integer compute() {
        if (inicio >= fin) { // Si el segmento es trivial (0 o 1 elemento), no hace falta ordenar
            return null;
        }

        int p = particion(inicio, fin, arreglo); // Particiona el arreglo y obtiene el índice del pivote

        // Crea tareas concurrentes para ordenar las subpartes
        QuickSortConcurrente izquierda = new QuickSortConcurrente(inicio, p - 1, arreglo);
        QuickSortConcurrente derecha = new QuickSortConcurrente(p + 1, fin, arreglo);

        izquierda.fork(); // Ejecuta la tarea de ordenar la subparte izquierda en un hilo separado
        derecha.compute(); // Ejecuta la tarea de ordenar la subparte derecha en el hilo actual

        izquierda.join(); // Espera a que la tarea izquierda termine antes de continuar

        return null; // Retorna null ya que el método no necesita devolver un valor específico
    }
}
