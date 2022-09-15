import java.util.Scanner;

public class MergeSort {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size n of array: ");
		int n = sc.nextInt();
		System.out.println("Enter an array of integers: ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		// print the list of arr =
		System.out.println("Before mergeSort()");
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");

		}
		System.out.println();
		mergeSort(arr, 0, n - 1);
		System.out.println("After mergeSort()");
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");

		}
		System.out.println();

	}

	public static int[] mergeSort(int[] arr, int start, int end) {
		int mid = (start + end) / 2;
		if (end - start <= 0)
			return arr;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid + 1, end);
		merge(arr, start, end);
		return arr;
	}

	public static int[] merge(int[] arr, int start, int end) {
		// remember that a and b will keep going right, so it doesn't make sense to
		// initialize b = m;
		int mid = (start + end) / 2, a = start, b = mid + 1, i, tmp, cmp;
		if (end - start <= 0)
			return arr;

		while (a <= mid && b <= end) {
			cmp = arr[a] - arr[b];

			// case 1: when slot[a] already < slot[b]
			if (cmp < 0) {
				a++;
			} else if (cmp > 0) {
				tmp = arr[b++];
				// shift everything and put the first ele of right array into the merged list.

				for (i = ++mid; i > a; i--) {
					arr[i] = arr[i - 1];
				}

				arr[a++] = tmp;

			} else {
				// if it's a single element
				if (a == mid && b == end)
					break;
				a++;
				tmp = arr[b++];
				for (i = ++mid; i > a; i--) {
					arr[i] = arr[i - 1];
				}
				arr[a++] = tmp;
			}

		}

		return arr;
	}

}
