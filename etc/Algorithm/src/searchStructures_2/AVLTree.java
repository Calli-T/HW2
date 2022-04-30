package searchStructures_2;


class AVLTree<K extends Comparable<K>, V> extends BST <K, V>{
	
	//-------------------------이하 AVL Tree용 함수들
	protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
		if(child != null) child.parent = parent;
		if(makeLeft) parent.left = child;
		else parent.right = child;
	}
	
	protected void rotate(Node<K, V> x) {
		Node<K, V> y = x.parent;
		Node<K, V> z = y.parent;
		
		if(z == null) { 
			root = x;
			x.parent = null;
		}
		else relink(z, x, y == z.left);
		
		if(x == y.left) {
			relink(y, x.right, true);
			relink(x, y, false);
		}
		else { 
			relink(y, x.left, false);
			relink(x,y,true);
		}
	}
	
	protected Node<K, V> restructure(Node<K, V> x){
		Node<K, V> y = x.parent;
		Node<K, V> z = y.parent;
		
		if((z.left == y) && (y.left == x)) {
			rotate(x);
			return(x);
		}
		else {
			rotate(y); rotate(y);
			return x;
		}
	}

	private int height(Node<K,V> x) {
		return (x==null) ? 0 : x.getAux();
	}
	private void setHeight(Node<K, V> x, int height) { x.setAux(height); }
	private void recomputeHeight(Node<K, V> x) {
		setHeight(x, 1+Math.max(height(x.left), height(x.right)));
	}
	private boolean isBalanced(Node<K, V> x){
		return Math.abs(height(x.left)-height(x.right)) <= 1;
	}
	private Node<K, V> tallerChild(Node<K, V> x){
		if(height(x.left) > height(x.right)) return x.left;
		if(height(x.left) < height(x.right)) return x.right;
		if(x==root) return x.left;
		if(x==x.parent.left) return x.left;
		else return x.right;
	}
	
	private void rebalance(Node<K, V> x) {
		do {
			if(!isBalanced(x)) {
				x = restructure(tallerChild(tallerChild(x)));
				recomputeHeight(x.left);
				recomputeHeight(x.right);
				for(Node<K, V> p = x; p != null; p = p.parent)
					recomputeHeight(p);
			}
			
			x = x.parent;
		}while(x!=null);
	}
	
	@Override
	protected void rebalanceInsert(Node<K, V> x) {
		rebalance(x);
	}
	@Override
	protected void rebalanceDelete(Node<K, V> x) {
		rebalance(x);
	}
}
