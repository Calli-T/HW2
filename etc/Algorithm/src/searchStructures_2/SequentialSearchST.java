package searchStructures_2;

import java.util.ArrayList;

class Node<K, V> { //개별노드이다
	K key; V value; Node<K, V> next;
	
	public Node(K key, V value, Node<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
}

public class SequentialSearchST<K, V> {
	private Node<K, V> first; //헤드
	int N;
	
	public V get(K key) { 
		for(Node<K, V> x = first; x != null; x = x.next)// 연결리스트 읽기
			if(key.equals(x.key))
				return x.value; //찾은경우
		
		return null; //아닌 경우
	}
	public void put(K key, V value) {
		for(Node<K, V> x = first; x != null; x = x.next)
			if(key.equals(x.key)) { // 키가 있을 경우, 값만 변경
				x.value = value;
				return;
			}
			first = new Node<K, V>(key, value, first);
			// 없으면, 해당 값을 앞에 노드로 추가한다
			N++;
	}
	public void delete(K key) {
		// 기본적으로 연결을 끊어 가비지 컬렉터에 맡기는식
		if(key.equals(first.key)) {
			first = first.next; N--; return;
		}
		for(Node<K, V> x = first; x != null; x = x.next) {
			if(key.equals(x.next.key)) { // 다음 노드와 일치한다면
				x.next = x.next.next; // 다음 노드는 다음 다음 노트로 대체되어 빠진다
				N--;
				return;
			}
		}
	}
	
	public Iterable<K> keys(){ // 키값모아 던져준다
		ArrayList<K> keyList = new ArrayList<K>(N);
		for(Node<K, V> x = first; x!=null; x = x.next)
			keyList.add(x.key); //키만 싹 모아 준다
		return keyList;
	}
	
	public boolean contains(K key) { return get(key) != null; }
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }
}
