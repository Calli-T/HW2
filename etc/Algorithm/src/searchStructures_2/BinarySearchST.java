package searchStructures_2;

import java.util.ArrayList;

public class BinarySearchST<K extends Comparable<K>, V> {
	private static final int INIT_CAPACITY = 10;
	private K[] keys;
	private V[] vals;
	private int N;
	
	//-------------------------------생성자----------------
	public BinarySearchST() {
		keys = (K[]) new Comparable[INIT_CAPACITY];
		vals = (V[]) new Object[INIT_CAPACITY];
	}
	
	public BinarySearchST(int capacity) {
		keys = (K[]) new Comparable[capacity];
		vals = (V[]) new Object[capacity];
	}
	
	//--------------------------------public한 함수들-------
	
	public V get(K key) {
		if(isEmpty()) return null;
		int i = search(key); //이진 조이고
		
		// 없으면 -1이 아닌 lo의 값이 나온다 그래서 한 번 더 비교를 해주는 것같다
		if(i < N && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	}
	public void put(K key, V value) {
		int i = search(key);
		if(i < N && keys[i].compareTo(key) == 0) { //lo가 튀어나올 수 있으므로 비교다시 하는듯
			vals[i] = value; return;
			//있으면, 값만 변경
		}
		if(N == keys.length)
			resize(2*keys.length);
		
		for(int j = N; j> i; j--) {
			keys[j] = keys[j-1]; vals[j] = vals[j-1];
			//추가될 곳의 공간확보, j>i인 곳까지 쓱 밀어버리는듯
			//내리는건지 올리는건지 생각을 좀 해보자
		}
		keys[i] = key; vals[i] = value; N++;
	}
	public void delete(K key) {
		if(isEmpty()) return;
		int i = search(key); // PPT에서, 모든 키는 이진으로 찾으라고한다
		if (i == N || keys[i].compareTo(key) != 0) return;
		
		for(int j = i; j<N-1; j++) {
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
		keys[N] = null;
		vals[N] = null;
		
		if(N > INIT_CAPACITY && N == keys.length/4)
			resize(keys.length/2);
	}
	public Iterable<K> keys() {
		ArrayList<K> keyList = new ArrayList<K>(N);
		for(int i = 0; i < N; i++)
			keyList.add(keys[i]);
		return keyList;
	}
	
	public boolean contains(K key) {
		return get(key) != null;
	}
	public boolean isEmpty() {return N==0; }
	public int size() { return N; }
	
	
	//--------이하 내부 함수들---------------------------------
	//@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		K[] tempk = (K[])new Comparable[capacity];
		V[] tempv = (V[])new Object[capacity];
		
		for(int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] =vals[i];
		}
		vals = tempv;
		keys = tempk;
	}
	
	private int search(K key) {
		int lo = 0;
		int hi = N - 1;
		
		while(lo <= hi) {
			int mid = (hi + lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			
			if(cmp < 0) hi = mid - 1;
			else if(cmp > 0) lo = mid + 1;
			else return mid;
		}
		
		// 키가 없는 경우, -1이 아닌 lo를 반환 (=추가할 위치)
		return lo;
	}
}
