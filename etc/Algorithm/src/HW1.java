import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HW1 {

	public static void main(String[] args) {		
		System.out.println("입력 파일 이름?");
		ArrayList<String> getList = new ArrayList<>();
		getList = getWord.makeArray();
		String[] list1 = getList.toArray(new String[getList.size()]);
		String[] list2 = getList.toArray(new String[getList.size()]);
		String[] list3 = getList.toArray(new String[getList.size()]);
		String[] list4 = getList.toArray(new String[getList.size()]);
		String[] list5 = getList.toArray(new String[getList.size()]);
		Instant start, end;
		
		System.out.println();
		System.out.println("1. 단어의 수 = " + getList.size());
		
		start = Instant.now();
		Selection.sort(list1);
		end = Instant.now();
		System.out.print("2. 선택정렬: ");
		System.out.print("정렬 여부 = " + Selection.isSorted(list1));
		System.out.println(", 소요 시간 = " + Duration.between(start, end).toMillis() + "ms");
		
		start = Instant.now();
		Insertion.sort(list2);
		end = Instant.now();
		System.out.print("3. 삽입정렬: ");
		System.out.print("정렬 여부 = " + Insertion.isSorted(list2));
		System.out.println(", 소요 시간 = " + Duration.between(start, end).toMillis() + "ms");
		
		start = Instant.now();
		Shell.sort(list3);
		end = Instant.now();
		System.out.print("4. Shell정렬: ");
		System.out.print("정렬 여부 = " + Shell.isSorted(list3));
		System.out.println(", 소요 시간 = " + Duration.between(start, end).toMillis() + "ms");
		
		start = Instant.now();
		mergeTD.sort(list4);
		end = Instant.now();
		System.out.print("5. Top Down 합병정렬: ");
		System.out.print("정렬 여부 = " + mergeTD.isSorted(list4));
		System.out.println(", 소요 시간 = " + Duration.between(start, end).toMillis() + "ms");
		
		start = Instant.now();
		mergeBU.sort(list5);
		end = Instant.now();
		System.out.print("6. Bottom Up 합병정렬: ");
		System.out.print("정렬 여부 = " + mergeBU.isSorted(list5));
		System.out.println(", 소요 시간 = " + Duration.between(start, end).toMillis() + "ms");
	}

}

class getWord{
	public static ArrayList<String> makeArray() {
		Scanner scanner = new Scanner(System.in);
		String fileName;
		ArrayList<String> word = new ArrayList<>();
		BufferedReader br = null;
		
		fileName = scanner.nextLine();
		
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
			String s;
			
			while((s = br.readLine())!= null) {
				if(!s.isEmpty()) {					
					StringTokenizer st = new StringTokenizer(s, " ");
					int n = st.countTokens();
					
					for(int i = 0; i < n; i++) {
						word.add(st.nextToken());
					}
				}
			}
			
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		scanner.close();
		
		return word;
	}
}


abstract class AbstractSort {
	public static void sort(Comparable[] a) {};
	
	protected static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	protected static void exch(Comparable[] a, int i, int j) {
		Comparable t= a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	protected static void show(Comparable[] a) {
		for(int i = 0 ; i < a.length; i++) System.out.println(a[i]);
	}
	
	protected static boolean isSorted(Comparable[] a) {
		if(a.length == 0) return false;
		for(int i = 1; i <a.length ; i++)
			if(less(a[i], a[i-1])) return false;
		return true;
	}
}

class Selection extends AbstractSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		
			for (int i = 0; i < N - 1; i++) {
				int min = i;
				for (int j = i+1; j < N; j++) {
					if (less(a[j], a[min]))
						min = j;
				}
				exch(a, i, min);
			}
		
			assert isSorted(a);
	};
}

class Insertion extends AbstractSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		
		for(int i = 1; i < N; i++) {
			for(int j = i; j> 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
		assert isSorted(a);
	};
}

class Shell extends AbstractSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h < N/3) h = 3*h + 1;
		
		while(h>=1) {
			for(int i = h; i<N; i++) {
				for(int j = i; j>=h && less(a[j], a[j-h]); j-=h){
					exch(a, j, j-h);
				}
			}
			h/=3;
		}
		
	}
}

class mergeTD extends AbstractSort{
	private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
		
		for(int k = low; k <= high; k++)
			aux[k] = a[k];
		
		int i = low, j = mid + 1;
		for(int k = low; k<= high; k++) {
			if(i>mid) a[k] =aux[j++];
			else if(j>high) a[k] = aux[i++];
			else if(less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
		if(high <= low) return;
		int mid = low + (high - low) / 2;
		
		sort(a, aux, low, mid);
		sort(a, aux, mid + 1, high);
		merge(a, aux, low, mid, high);
	}
}

class mergeBU extends AbstractSort {
	private static void merge(Comparable[] in, Comparable[] out, int low, int mid, int high) {
		int i = low, j = mid + 1;
		for(int k = low; k<= high; k++) {
			if(i>mid) out[k] = in[j++];
			else if(j>high) out[k] = in[i++];
			else if(less(in[j], in[i])) out[k] = in[j++];
			else out[k] = in[i++];
		}
	}
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		Comparable[] src = a, dst = new Comparable[a.length], tmp;
		
		for(int n = 1; n < N; n*=2) {
			for(int i = 0 ; i < N; i+=2*n) {
				merge(src, dst, i, i+n-1, Math.min(i+2*n-1, N - 1));
			}
			tmp = src; src= dst; dst = tmp;
		}
		if(src!= a) System.arraycopy(src, 0, a, 0 , N);
	}
}