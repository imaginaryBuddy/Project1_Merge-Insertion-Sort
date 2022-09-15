import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class HybridSort2 {

	public static int S;
	public static long keyComparisons = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Explore: 1 or Questions: 2?");
		int ch = sc.nextInt();
		long time, timeDiff;

		if (ch == 1) {
			System.out.println("Enter the size n of array: ");
			int n = sc.nextInt();
			int[] arr = new int[n];
			int[] arrCopy = new int[n];
			System.out.println("Enter S: ");
			S = sc.nextInt();
			System.out.println("1. Worst Case?");
			System.out.println("2. Best Case?");
			System.out.println("3. Random?");
			System.out.println("4. Self-input?");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				// sorted in decreasing order
				for (int i = 0; i < n; i++) {
					arr[i] = n - i;
				}
				System.out.println("------------WORST CASE-------------");
				break;
			case 2:
				// sorted in increasing order
				for (int i = 0; i < n; i++) {
					arr[i] = i;
				}

				System.out.println("------------BEST CASE-------------");
				break;
			case 3:
				Random rand = new Random();

				for (int i = 0; i < n; i++) {
					arr[i] = rand.nextInt(n);

				}
				System.out.println("------------RANDOM-------------");
				break;
			case 4:
				for (int i = 0; i < n; i++) {
					arr[i] = sc.nextInt();
				}
				System.out.println("------------INPUT-------------");
				break;
			default:
				System.out.println("invalid choice, program exiting...");
				System.exit(1);
			}

			do {
				System.out.println("Choice : ");
				System.out.println("-----------------");
				System.out.println("1. MergeSort()");
				System.out.println("2. InsertionSort()");
				System.out.println("3. MergeInsertionSort()");
				System.out.println("4. Change S Value");
				System.out.println();
				System.arraycopy(arr, 0, arrCopy, 0, n);
				choice = sc.nextInt();
//				System.out.println("Before Sorting: ");
//				printArr(arrCopy, n);
				switch (choice) {
				case 1:
					time = System.nanoTime();
					arrCopy = mergeSort(arrCopy, 0, n - 1);
					timeDiff = System.nanoTime() - time;
					System.out.println("Key Comparisons: " + keyComparisons + "---  time taken: "
							+ timeDiff * Math.pow(10, -6) + "ms");
					break;
				case 2:
					time = System.nanoTime();
					insertionSort(arrCopy, 0, n - 1);
					timeDiff = System.nanoTime() - time;
					System.out.println("Key Comparisons: " + keyComparisons + "---  time taken: "
							+ timeDiff * Math.pow(10, -6) + "ms");
					break;
				case 3:
					time = System.nanoTime();
					arrCopy = hybridSort(arrCopy, 0, n - 1);
					timeDiff = System.nanoTime() - time;
					System.out.println("Key Comparisons: " + keyComparisons + "---  time taken: "
							+ timeDiff * Math.pow(10, -6) + "ms");
					break;
				case 4:
					System.out.println("Enter S:");
					S = sc.nextInt();
					System.out.println("Changed S value successfully");
					break;

				default:
					System.out.println("invalid choice");
				}

				System.out.println();
//				System.out.println("After Sorting: ");
//				printArr(arrCopy, n);

				keyComparisons = 0;

			} while (choice < 5);
			keyComparisons = 0;
			System.out.println("Program Terminating...");
			System.exit(0);

		}

		else if (ch == 2) {
			int q1;
			do {
				System.out.println("1, 2, 3, Exit?");
				q1 = sc.nextInt();
				Random rand = new Random();
				FileWriter myWriter;
				switch (q1) {
				case 1:
					String str1 = "index,n,S,keyComparisons,timeTaken(ms)\n";
					System.out.println("Set S: ");
					S = sc.nextInt();
					System.out.println("fixed S=" + S + " with different n");
					int j;
					for (int i = 10; i <= 10000000; i *= 10) {
						int[] newArr = new int[i];
						// create array
						for (j = 0; j < i; j++) {
							newArr[j] = rand.nextInt(i);
						}
						j = 0;
						keyComparisons = 0;
						time = System.nanoTime();
						newArr = hybridSort(newArr, 0, i - 1);
						timeDiff = System.nanoTime() - time;
						System.out.println("Size " + i + " Key Comparisons: " + keyComparisons + "  ---  time taken: "
								+ timeDiff * Math.pow(10, -6) + "ms");
						str1 += j++ + "," + i + "," + S + "," + keyComparisons + "," + timeDiff * Math.pow(10, -9)
								+ "\n";
					}
					try {
						myWriter = new FileWriter("q1_fixed_S_" + S + ".csv");
						myWriter.write(str1);
						myWriter.close();
					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}
					break;
				case 2:
					String str = "index,n,S,keyComparisons,timeTaken(ms)\n";
					System.out.println("Enter n");
					int nSize = sc.nextInt();
					int[] newArr = new int[nSize];

					System.out.println("fixed n =" + nSize + " with different S");
					for (j = 0; j < nSize; j++) {
						newArr[j] = rand.nextInt(nSize);
					}

					for (int i = 1; i <= 100; i++) {
						S = i;
						int[] newArrCopy = new int[nSize];
						System.arraycopy(newArr, 0, newArrCopy, 0, nSize - 1);

						keyComparisons = 0;
						time = System.nanoTime();
						newArrCopy = hybridSort(newArrCopy, 0, nSize - 1);
						timeDiff = System.nanoTime() - time;
						System.out.println("S =  " + i + ", Key Comparisons: " + keyComparisons + "  ---  time taken: "
								+ timeDiff * Math.pow(10, -6) + "ms");
						str += i - 1 + "," + nSize + "," + S + "," + keyComparisons + "," + timeDiff * Math.pow(10, -9)
								+ "\n";
						time = 0;

					}

					try {
						myWriter = new FileWriter("q1_fixed_n_" + nSize + ".csv");
						myWriter.write(str);
						myWriter.close();
					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}

					break;

				case 3:
					System.out.println("Compared to MergeSort");
					System.out.println("refer to excel sheet generated.");
					break;

				case 4:
					System.out.println("Program terminating...");
					System.exit(0);

				default:
					break;

				}
				keyComparisons = 0;
			} while (q1 < 5);
		}
	}

	public static int[] hybridSort(int[] arr, int start, int end) {
//		int size = end - start + 1;
//
//		if (size <= 1)
//			return arr;
//		int[] sorted = new int[size];
//		if (size > S) {
//			int mid = (start + end) / 2;
//			int s1 = mid - start + 1;
//			int s2 = end - mid;
//			int[] arr1 = new int[s1];
//			arr1 = hybridSort(arr, start, mid);
//			int[] arr2 = new int[s2];
//			arr2 = hybridSort(arr, mid + 1, end);
//			sorted = merge(arr1, arr2, s1, s2);
//
//		} else {
//			System.arraycopy(arr, start, sorted, 0, size);
//			insertionSort(sorted, 0, size - 1);
//		}
//		return sorted;

		int size = end - start + 1;
		int[] sorted = new int[end - start + 1];
		if (size <= S) {
			sorted = insertionSort(arr, start, end);
			return sorted;
		}

		int mid = (start + end) / 2;
		int s1 = mid - start + 1;
		int s2 = end - mid;
		int[] arr1 = new int[s1];
		arr1 = hybridSort(arr, start, mid);
		int[] arr2 = new int[s2];
		arr2 = hybridSort(arr, mid + 1, end);
		sorted = merge(arr1, arr2, s1, s2);
		return sorted;
	}

	public static int[] insertionSort(int[] arr, int start, int end) {
		for (int i = start + 1; i <= end; i++) {
			for (int j = i; j > start; j--) {
				keyComparisons++;
				if (arr[j] < arr[j - 1]) {
					arr[j] = arr[j - 1] + arr[j];
					arr[j - 1] = arr[j] - arr[j - 1];
					arr[j] = arr[j] - arr[j - 1];
				} else
					break;

			}
		}

		int size = end - start + 1;
		int[] sorted = new int[size];
		System.arraycopy(arr, start, sorted, 0, size);
		return sorted;
	}

	public static int[] mergeSort(int[] arr, int start, int end) {
		int size = end - start + 1;
		int[] sorted = new int[end - start + 1];
		if (size <= 1) {
			sorted[0] = arr[start];
			return sorted;
		}

		int mid = (start + end) / 2;
		int s1 = mid - start + 1;
		int s2 = end - mid;
		int[] arr1 = new int[s1];
		arr1 = mergeSort(arr, start, mid);
		int[] arr2 = new int[s2];
		arr2 = mergeSort(arr, mid + 1, end);
		sorted = merge(arr1, arr2, s1, s2);
		return sorted;
	}

	public static int[] merge(int[] arr1, int[] arr2, int s1, int s2) {
		int[] sorted = new int[s2 + s1];
		if (s1 + s2 == 0) {
			return null;
		} else if (s1 == 0) {
			return arr2;
		} else if (s2 == 0) {
			return arr1;
		}

		int cmp, i = 0, j = 0, a = 0;
		while (i < s1 && j < s2) {
			cmp = arr1[i] - arr2[j];
			keyComparisons++;
			if (cmp < 0) {
				sorted[a++] = arr1[i++];
			} else if (cmp > 0) {
				sorted[a++] = arr2[j++];
			} else {
				sorted[a++] = arr1[i++];
				sorted[a++] = arr2[j++];
			}
		}
		int diff1 = s1 - i;
		int diff2 = s2 - j;
		// if there are leftovers in any, then just add to arr
		while (diff1 > 0) {
			sorted[a++] = arr1[i++];
			diff1--;
		}
		while (diff2 > 0) {
			sorted[a++] = arr2[j++];
			diff2--;
		}
		return sorted;
	}

	public static void printArr(int[] arr, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
