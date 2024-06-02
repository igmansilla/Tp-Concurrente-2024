package algoritmos;

public class QuickSortSecuencial {

	public static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(array, low, high);
			quickSort(array, low, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, high);
		}
	}

	private static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i + 1, high);
		return i + 1;
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		int[] array = { 10, 7, 8, 9, 1, 5 };
		int n = array.length;

		System.out.println("Array original:");
		printArray(array);

		quickSort(array, 0, n - 1);

		System.out.println("Array ordenado:");
		printArray(array);
	}

	private static void printArray(int[] array) {
		for (int value : array) {
			System.out.print(value + " ");
		}
		System.out.println();
	}
}
