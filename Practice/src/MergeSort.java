
public class MergeSort {

	private void merge(int[] left, int[] right, int[] A) {
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				A[k] = left[i];
				i++;

			} else {
				A[k] = right[j];
				j++;
			}
			k++;
		}
		while (i < left.length) {
			A[k] = left[i];
			i++;
			k++;
		}
		while (j < right.length) {
			A[k] = right[j];
			j++;
			k++;
		}

	}

	public void mergeSort(int[] a) {
		if (a.length < 2) {
			return;
		}
		int mid = a.length / 2;
		int[] left = new int[mid];
		int[] right = new int[a.length - left.length];
		// System.out.println();
		for (int i = 0; i < mid; i++) {
			left[i] = a[i];
		}

		// System.out.println();
		int tmp = mid;
		for (int j = 0; j < right.length; j++) {

			right[j] = a[tmp];
			tmp++;
		}

		mergeSort(left);
		mergeSort(right);
		merge(left, right, a);
		System.out.println();

		// return a;

	}

	public static void main(String[] args) {
		int[] A = { 2, 9, 5, 10, 4, 0, 1, 8 };
		MergeSort m = new MergeSort();
		m.mergeSort(A);
		for (int i : A) {
			System.out.print(i + " ");
		}
	}

}
