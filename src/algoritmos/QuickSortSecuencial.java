package algoritmos;

public class QuickSortSecuencial {

	// Una función de utilidad para intercambiar dos elementos
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Esta función toma el último elemento como pivote,
	// coloca el elemento pivote en su posición correcta
	// en el array ordenado, y coloca todos los elementos
	// menores a la izquierda del pivote y todos los mayores
	// a la derecha del pivote
	static int partition(int[] arr, int low, int high) {
		// Eligiendo el pivote
		int pivot = arr[high];

		// Índice del elemento menor e indica
		// la posición correcta del pivote encontrada hasta ahora
		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {
			// Si el elemento actual es menor que el pivote
			if (arr[j] < pivot) {
				// Incrementar el índice del elemento menor
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	// La función principal que implementa QuickSort
	// arr[] --> Array a ordenar,
	// low --> Índice inicial,
	// high --> Índice final
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			// pi es el índice de partición, arr[pi]
			// ahora está en el lugar correcto
			int pi = partition(arr, low, high);

			// Ordenar por separado los elementos antes
			// de la partición y después de la partición
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

}
