package test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import algoritmos.QuickSortConcurrente;
import algoritmos.QuickSortSecuencial;

public class Test {

	public static void main(String[] args) {
		int[] sizes = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 5000, 10000, 50000, 100000, 500000, 1000000,
				10000000, 100000000, 1000000000 };
		System.out.println("Tamaño,TiempoSecuencial,TiempoConcurrente");

		for (int size : sizes) {
			int[] array1 = generateRandomArray(size);
			int[] array2 = array1.clone();

			// Testeo QuickSortSecuencial
			long tiempoInicial = System.nanoTime();
			QuickSortSecuencial.quickSort(array1, 0, array1.length - 1);
			long tiempoFinal = System.nanoTime();
			long duracionSecuencial = tiempoFinal - tiempoInicial;

			// Testeo QuickSortConcurrente
			ForkJoinPool pool = new ForkJoinPool();
			QuickSortConcurrente task = new QuickSortConcurrente(0, array2.length - 1, array2);
			tiempoInicial = System.nanoTime();
			pool.invoke(task);
			tiempoFinal = System.nanoTime();
			long duracionConcurrente = tiempoFinal - tiempoInicial;

			// Imprimo los resultados en formato csv para poder procesarlos 
			System.out.println(size + "," + duracionSecuencial + "," + duracionConcurrente);
		}
	}

	private static int[] generateRandomArray(int size) {
		Random random = new Random();
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(size);
		}
		return array;
	}
}
