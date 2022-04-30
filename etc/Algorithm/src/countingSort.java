
public class countingSort {

	public static int[] sort(int[] A, int K) {
		int i, N = A.length; //�ݺ����� ����, �迭����
		int[] C = new int[K], B = new int[N]; // K�� ���� ��������, B�� ���� �����迭
		
		for (i = 0; i < N; i++) C[A[i]]++; // ��
		for (i = 1; i < K; i++) C[i] += C[i-1]; // ����
		//C�ȿ� ������ �ش簪���� ���� �ٷ� ���� ĭ�� ����Ű���ִ�
		//���� --�� ó��
		
		for(i = 0 ; i<K; i++)
			System.out.print(C[i]+ " ");
		System.out.println();
		
		for (i = N-1; i >= 0; i--) { // stable������ ���� �ڿ�������
			System.out.print(A[i] + " ");
			System.out.println(C[A[i]]);
			B[--C[A[i]]] = A[i]; // ���ĵ� �迭�� ����
			
		}
		
		return B;
	}
		
	public static void main(String[] args) {
		int[] A = {10, 4, 5, 8, 1, 8, 3, 6}, B;
		
		B = countingSort.sort(A, 11);
		for (int i = 0; i < B.length; i++)
			System.out.print(B[i] + " ");
		System.out.println();
	}
	
}
