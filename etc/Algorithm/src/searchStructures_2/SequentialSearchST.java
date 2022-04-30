package searchStructures_2;

import java.util.ArrayList;

class Node<K, V> { //��������̴�
	K key; V value; Node<K, V> next;
	
	public Node(K key, V value, Node<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
}

public class SequentialSearchST<K, V> {
	private Node<K, V> first; //���
	int N;
	
	public V get(K key) { 
		for(Node<K, V> x = first; x != null; x = x.next)// ���Ḯ��Ʈ �б�
			if(key.equals(x.key))
				return x.value; //ã�����
		
		return null; //�ƴ� ���
	}
	public void put(K key, V value) {
		for(Node<K, V> x = first; x != null; x = x.next)
			if(key.equals(x.key)) { // Ű�� ���� ���, ���� ����
				x.value = value;
				return;
			}
			first = new Node<K, V>(key, value, first);
			// ������, �ش� ���� �տ� ���� �߰��Ѵ�
			N++;
	}
	public void delete(K key) {
		// �⺻������ ������ ���� ������ �÷��Ϳ� �ñ�½�
		if(key.equals(first.key)) {
			first = first.next; N--; return;
		}
		for(Node<K, V> x = first; x != null; x = x.next) {
			if(key.equals(x.next.key)) { // ���� ���� ��ġ�Ѵٸ�
				x.next = x.next.next; // ���� ���� ���� ���� ��Ʈ�� ��ü�Ǿ� ������
				N--;
				return;
			}
		}
	}
	
	public Iterable<K> keys(){ // Ű����� �����ش�
		ArrayList<K> keyList = new ArrayList<K>(N);
		for(Node<K, V> x = first; x!=null; x = x.next)
			keyList.add(x.key); //Ű�� �� ��� �ش�
		return keyList;
	}
	
	public boolean contains(K key) { return get(key) != null; }
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }
}
