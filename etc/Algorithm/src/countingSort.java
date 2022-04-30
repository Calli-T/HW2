
public class countingSort {

	public static int[] sort(int[] A, int K) {
		int i, N = A.length; //반복문용 변수, 배열길이
		int[] C = new int[K], B = new int[N]; // K는 값의 분포넓이, B는 값을 넣을배열
		
		for (i = 0; i < N; i++) C[A[i]]++; // 빈도
		for (i = 1; i < K; i++) C[i] += C[i-1]; // 누계
		//C안에 값들은 해당값들이 들어가고 바로 다음 칸을 가리키고있다
		//따라서 --로 처리
		
		for(i = 0 ; i<K; i++)
			System.out.print(C[i]+ " ");
		System.out.println();
		
		for (i = N-1; i >= 0; i--) { // stable유지를 위해 뒤에서부터
			System.out.print(A[i] + " ");
			System.out.println(C[A[i]]);
			B[--C[A[i]]] = A[i]; // 정렬된 배열을 생성
			
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
