package searchStructures_2;

import java.util.ArrayList;

class BST<K extends Comparable<K>, V> {

	//아예 노드를 BST의 서브 클래스로 작성
	//generic때문인지 꼴보기싫은 밑줄을 @로 지움
	@SuppressWarnings("hiding")
	class Node<K, V>{
		K key;
		V value;
		Node<K, V> left, right;
		
		//생성자
		public Node(K key, V val) {
			this.key = key; this.value = val;
			// 추가 구조용 초기화
			this.N = 1;
		}
		
		int N;
		Node<K, V> parent; // AVL or RB
		
		
		//Order operation, Balanced Tree용 추가 구조
		int aux;
		public int getAux() { return aux; }
		public void setAux(int value) { aux = value; }
	}
	
	protected Node<K, V> root;

	//키를 입력받아 그 키를 갖는 노드 혹은 마지막 노드 반환
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
		resetSize(x.parent, 1); //root까지 조상 노드들읜 size를 1증가
	}
	protected void rebalanceDelete(Node<K, V>p) {//, Node<K, V> deleted) {
		resetSize(p, -1); //root까지 조상 노드들의 size 1 감소, deleted는 왜 있는지 모르겠음
	}
	//
	public void resetSize(Node<K, V> x, int value) {
		for(; x!= null; x = x.parent)
			x.N += value;
	}
	
	//중위순회로 돌면서 키값 모아옴
	private void inorder(Node<K, V>x, ArrayList<K> keyList){
		if(x != null) {
			inorder(x.left, keyList);
			keyList.add(x.key);
			inorder(x.right, keyList);
		}
	}
	
	//검색후 있으면 키 있으면 반환 없으면 x
	public V get(K key) {
		if(root == null) return null;
		Node<K,V> x = treeSearch(key);
		if(key.equals(x.key))
			return x.value;
		else
			return null;
	}
	// 검색후 키 있으면 reset 없으면 추가
	public void put(K key, V val) {
		//루트인지 확인
		if(root == null) { root = new Node<K, V>(key, val); return; }
		//비교
		Node<K, V> x = treeSearch(key);
		int cmp = key.compareTo(x.key);
		
		//찾았다면
		if(cmp == 0) x.value = val;
		else {
			Node<K, V> newNode = new Node<K, V>(key, val);
			//좌우 맞는 위치에 붙여준다
			if(cmp < 0) x.left = newNode;
			else x.right = newNode;
			//부모를 찾아분다
			newNode.parent = x;
			
			//크기 변수 조절
			rebalanceInsert(newNode);
		}
	}
	
	//키의 inorder로  순서반환
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
		
		// key없는 경우
		if(!key.equals(x.key)) return;
		
		//루트이거나 자식이 두 개인 노드
		if(x==root || isTwoNode(x)) {
			if(isLeaf(x)) {
				//루트, 무 자녀(루트이자 리프)
				root = null; return;
			}
			else if(!isTwoNode(x)) { // 루트, 한 자녀
				//자식을 루트로
				root = (x.right == null) ? x.left:x.right;
				root.parent = null;
				return;
			}
			else { //자식이 둘인 리프(루트포함) 
				y = min(x.right); //우측 자식의 가장 왼쪽(중위 계승자)
				x.key = y.key;
				x.value = y.value;
				p = y.parent;
				// y의 자식을 p의 자식으로 (y삭제)
				relink(p, y.right, y == p.left);
				//y의 조상 노드들의 size를 감소
				rebalanceDelete(p);//, y);
			}
		}
		else { //루트가 아니고 자식이 하나 이하인 노드
			p = x.parent;
			//x의 왼쪽 자식이건 오른쪽 자식이건 있는 쪽을 x가 원래 p에 붙어있던 자리에 붙여준다
			if(x.right == null)
				relink(p, x.left, x==p.left);
			else if(x.left == null)
				relink(p,x.right, x==p.left);
			
			rebalanceDelete(p);
		}
	}
	
	//inline과 protected함수들
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
	//주어진 키의 후손 중 가장 작은 키를 반환
	protected Node<K,V> min(Node<K, V> x) {
		while(x.left!=null) x = x.left; return x;
	}
	
	

	//뭔갈 테스트 해보고싶다면 이쪽이다
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
