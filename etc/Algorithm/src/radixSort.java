
public class radixSort {
	
	public static void main(String[] args) {
		int[] A = {10, 4, 5, 8, 1, 8, 3, 6, 3, 5}, B;
		int[] C = {179, 208, 306, 93, 859, 984, 55, 9, 271, 33};
		
		B = radixSort.sort(A);
		for (int i = 0; i < B.length; i++)
			System.out.print(B[i] + " ");
		System.out.println();
		
		B = radixSort.sort(C);
		for (int i = 0; i < B.length; i++)
			System.out.print(B[i] + " ");
		System.out.println();
	}

	public static int[] sort(int[] A) {
		int i, max = A[0], exp = 1, n = A.length;
		//���� �ݺ����� ����, �ִ밪, �迭�� ����
		int[] B = new int[n];
		
		for(i = 1; i< n; i++) //����ū��
			if(max < A[i]) max = A[i];
		
		while(max / exp > 0) { // �����ڸ�������
			int[] C = new int[10]; //��Ŷ
			
			for(i = 0; i < n; i++) // ���
				C[(A[i] / exp) % 10]++;
			for(i = 1; i < n; i++) // ����
				C[i] += C[i-1];
			for(i = n - 1; i>=0; i--) // ����
				B[--C[(A[i] / exp) % 10]] = A[i];
			for(i = 0; i < n; i++) // �� �ڸ� ������ ����
				A[i] = B[i];
			
			exp *= 10;
		}
		
		return B;
	}


}
