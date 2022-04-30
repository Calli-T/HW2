package searchStructures_2;

import java.util.ArrayList;

class BST<K extends Comparable<K>, V> {

	//�ƿ� ��带 BST�� ���� Ŭ������ �ۼ�
	//generic�������� �ú������ ������ @�� ����
	@SuppressWarnings("hiding")
	class Node<K, V>{
		K key;
		V value;
		Node<K, V> left, right;
		
		//������
		public Node(K key, V val) {
			this.key = key; this.value = val;
			// �߰� ������ �ʱ�ȭ
			this.N = 1;
		}
		
		int N;
		Node<K, V> parent; // AVL or RB
		
		
		//Order operation, Balanced Tree�� �߰� ����
		int aux;
		public int getAux() { return aux; }
		public void setAux(int value) { aux = value; }
	}
	
	protected Node<K, V> root;

	//Ű�� �Է¹޾� �� Ű�� ���� ��� Ȥ�� ������ ��� ��ȯ
	protected Node<K, V> treeSearch(K key){
		Node<K, V> x = root;
		while(true) {
			int cmp = key.compareTo(x.key);
			if(cmp == 0 ) return x;
			else if(cmp < 0) {
				if(x.left == null) return x;
				else x = x.left;
			}
			else {
				if(x.right== null) return x;
				else x = x.right;
			}
		}
	}
	
	protected void rebalanceInsert(Node<K, V> x) {
		resetSize(x.parent, 1); //root���� ���� ������ size�� 1����
	}
	protected void rebalanceDelete(Node<K, V>p) {//, Node<K, V> deleted) {
		resetSize(p, -1); //root���� ���� ������ size 1 ����, deleted�� �� �ִ��� �𸣰���
	}
	//
	public void resetSize(Node<K, V> x, int value) {
		for(; x!= null; x = x.parent)
			x.N += value;
	}
	
	//������ȸ�� ���鼭 Ű�� ��ƿ�
	private void inorder(Node<K, V>x, ArrayList<K> keyList){
		if(x != null) {
			inorder(x.left, keyList);
			keyList.add(x.key);
			inorder(x.right, keyList);
		}
	}
	
	//�˻��� ������ Ű ������ ��ȯ ������ x
	public V get(K key) {
		if(root == null) return null;
		Node<K,V> x = treeSearch(key);
		if(key.equals(x.key))
			return x.value;
		else
			return null;
	}
	// �˻��� Ű ������ reset ������ �߰�
	public void put(K key, V val) {
		//��Ʈ���� Ȯ��
		if(root == null) { root = new Node<K, V>(key, val); return; }
		//��
		Node<K, V> x = treeSearch(key);
		int cmp = key.compareTo(x.key);
		
		//ã�Ҵٸ�
		if(cmp == 0) x.value = val;
		else {
			Node<K, V> newNode = new Node<K, V>(key, val);
			//�¿� �´� ��ġ�� �ٿ��ش�
			if(cmp < 0) x.left = newNode;
			else x.right = newNode;
			//�θ� ã�ƺд�
			newNode.parent = x;
			
			//ũ�� ���� ����
			rebalanceInsert(newNode);
		}
	}
	
	//Ű�� inorder��  ������ȯ
	public Iterable<K> keys(){
		if(root ==null) return null;
		ArrayList<K> keyList = new ArrayList<K>(size());
		inorder(root, keyList);
		return keyList;
	}
	
	public int size() {return root.N; }
	
	public void delete(K key) {
		if(root == null) return;
		Node<K, V> x, y, p;
		x = treeSearch(key);
		
		// key���� ���
		if(!key.equals(x.key)) return;
		
		//��Ʈ�̰ų� �ڽ��� �� ���� ���
		if(x==root || isTwoNode(x)) {
			if(isLeaf(x)) {
				//��Ʈ, �� �ڳ�(��Ʈ���� ����)
				root = null; return;
			}
			else if(!isTwoNode(x)) { // ��Ʈ, �� �ڳ�
				//�ڽ��� ��Ʈ��
				root = (x.right == null) ? x.left:x.right;
				root.parent = null;
				return;
			}
			else { //�ڽ��� ���� ����(��Ʈ����) 
				y = min(x.right); //���� �ڽ��� ���� ����(���� �����)
				x.key = y.key;
				x.value = y.value;
				p = y.parent;
				// y�� �ڽ��� p�� �ڽ����� (y����)
				relink(p, y.right, y == p.left);
				//y�� ���� ������ size�� ����
				rebalanceDelete(p);//, y);
			}
		}
		else { //��Ʈ�� �ƴϰ� �ڽ��� �ϳ� ������ ���
			p = x.parent;
			//x�� ���� �ڽ��̰� ������ �ڽ��̰� �ִ� ���� x�� ���� p�� �پ��ִ� �ڸ��� �ٿ��ش�
			if(x.right == null)
				relink(p, x.left, x==p.left);
			else if(x.left == null)
				relink(p,x.right, x==p.left);
			
			rebalanceDelete(p);
		}
	}
	
	//inline�� protected�Լ���
	public boolean contain(K key) { return get(key) != null; }
	public boolean isEmpty() { return root == null; }
	
	protected boolean isLeaf(Node<K, V> x){
		return x.left==null && x.right == null;
	}
	protected boolean isTwoNode(Node<K,V> x) {
		return x.left!=null & x.right != null;
	}
	
	protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
		if(child != null) child.parent = parent;
		
		if(makeLeft) parent.left = child;
		else parent.right = child;
	}
	//�־��� Ű�� �ļ� �� ���� ���� Ű�� ��ȯ
	protected Node<K,V> min(Node<K, V> x) {
		while(x.left!=null) x = x.left; return x;
	}
	
	

	//���� �׽�Ʈ �غ���ʹٸ� �����̴�
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
