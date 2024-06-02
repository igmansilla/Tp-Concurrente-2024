package algoritmos;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class QuickSortConcurrente extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int start, end;
	int[] arr;

	/**
	 * Finding random pivoted and partition array on a pivot. There are many
	 * different partitioning algorithms.
	 * 
	 * @param start
	 * @param end
	 * @param arr
	 * @return
	 */
	 private int partition(int start, int end, int[] arr) {
	        int pivot = arr[start + (end - start) / 2];  // Use median of the segment as pivot
	        int left = start - 1;
	        int right = end + 1;
	        
	        while (true) {
	            do {
	                left++;
	            } while (arr[left] < pivot);
	            
	            do {
	                right--;
	            } while (arr[right] > pivot);
	            
	            if (left >= right) {
	                return right;
	            }
	            
	            int temp = arr[left];
	            arr[left] = arr[right];
	            arr[right] = temp;
	        }
	    }
	// Function to implement
	// QuickSort method
	public QuickSortConcurrente(int start, int end, int[] arr) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		// Base case
		if (start >= end)
			return null;

		// Find partition
		int p = partition(start, end, arr);

		// Divide array
		QuickSortConcurrente left = new QuickSortConcurrente(start, p - 1, arr);

		QuickSortConcurrente right = new QuickSortConcurrente(p + 1, end, arr);

		// Left subproblem as separate thread
		left.fork();
		right.compute();

		// Wait until left thread complete
		left.join();

		// We don't want anything as return
		return null;
	}

	// Driver Code
	public static void main(String args[]) {
		int n = 7;
		int[] arr = { 54, 64, 95, 82, 12, 32, 63 };

		// Forkjoin ThreadPool to keep
		// thread creation as per resources
		ForkJoinPool pool = ForkJoinPool.commonPool();

		// Start the first thread in fork
		// join pool for range 0, n-1
		pool.invoke(new QuickSortConcurrente(0, n - 1, arr));

		// Print shorted elements
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}
}
