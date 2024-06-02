package test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import algoritmos.QuickSortConcurrente;
import algoritmos.QuickSortSecuencial;

public class Test {

	public static void main(String[] args) {
		int[] sizes = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 5000, 10000, 50000, 100000, 500000, 1000000,
				10000000, 100000000};
		System.out.println("Tamaño,TiempoSecuencial,TiempoConcurrente");

		for (int size : sizes) {
			int[] array1 = generateRandomArray(size);
			int[] array2 = array1.clone();

			// Test QuickSortSecuencial
			long startTime = System.nanoTime();
			QuickSortSecuencial.quickSort(array1, 0, array1.length - 1);
			long endTime = System.nanoTime();
			long secuencialDuration = endTime - startTime;

			// Test QuickSortConcurrente
			ForkJoinPool pool = new ForkJoinPool();
			QuickSortConcurrente task = new QuickSortConcurrente(0, array2.length - 1, array2);
			startTime = System.nanoTime();
			pool.invoke(task);
			endTime = System.nanoTime();
			long concurrenteDuration = endTime - startTime;

			// Print results in CSV format
			System.out.println(size + "," + secuencialDuration + "," + concurrenteDuration);
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
