package tree.BST;

import java.io.IOException;
import java.io.PrintWriter;

//二叉检索树的结点类
public class BSTNode<K extends Comparable<K>,V> {

	K key;
	V value;
	BSTNode<K, V> parent = null;
	BSTNode<K, V> left = null;
	BSTNode<K, V> right = null;

	public BSTNode() {
		key = null;
		value = null;
	}

	public BSTNode(K k, V v) {
		key = k;
		value = v;
	}

	public BSTNode(K k, V v, BSTNode<K, V> p) {
		key = k;
		value = v;
		parent = p;
	}

	public BSTNode(K k, V v, BSTNode<K, V> l, BSTNode<K, V> r) {
		key = k;
		value = v;
		left = l;
		right = r;
	}

	public BSTNode(K k, V v, BSTNode<K, V> l, BSTNode<K, V> r, BSTNode<K, V> p) {
		key = k;
		value = v;
		left = l;
		right = r;
		parent = p;
	}

	public void insert(K k, V v) {
		if (this.value == null && this.key == null) {
			this.key = k;
			this.value = v;
		} else if (this.key.compareTo(k) == 0) this.value = v;
		else if (this.key.compareTo(k) < 0) {
			if (this.right == null) this.right = new BSTNode<K, V>(k, v, this);
			else this.right.insert(k, v);
		} else if (this.key.compareTo(k) > 0) {
			if (this.left == null) this.left = new BSTNode<K, V>(k, v, this);
			else this.left.insert(k, v);
		}
	}

	public V remove(K k) {
		BSTNode<K, V> n = this.searchNode(k);
		if (n == null) return null;
		V v = n.value;
		if (n.left != null && n.right != null) {
			// get the smallest Node of right subtree.
			BSTNode<K, V> tmp = n.right;
			while (tmp.left != null) tmp = tmp.left;
			n.key = tmp.key;
			n.value = tmp.value;
			tmp.key = k;
			tmp.value = v;
			n.right.remove(k);
		} else if (n.left != null) {
			if (n.parent == null) {
				n.key = n.left.key;
				n.value = n.left.value;
				n.left = null;
			} else {
				n.left.parent = n.parent;
				if (n.parent.left == n) n.parent.left = n.left;
				else n.parent.right = n.left;
			}
		} else if (n.right != null) {
			if (n.parent == null) {
				n.key = n.right.key;
				n.value = n.right.value;
				n.right = null;
			} else {
				n.right.parent = n.parent;
				if (n.parent.left == n) n.parent.left = n.right;
				else n.parent.right = n.right;
			}
		} else {
			if (n.parent.left == n) n.parent.left = null;
			else n.parent.right = null;
		}
		return v;
	}


	public V search(K k) {
		if (this.searchNode(k) != null) return this.searchNode(k).value;
		return null;
	}

	public BSTNode<K, V> searchNode(K k) {
		if (this.key == null) return null;
		else if (k.compareTo(this.key) == 0) return this;
		else if (k.compareTo(this.key) > 0 && this.right != null) return this.right.searchNode(k);
		else if (k.compareTo(this.key) < 0 && this.left != null) return this.left.searchNode(k);
		else return null;
	}

	public boolean update(K k, V newV) {
		BSTNode<K, V> tmp = searchNode(k);
		if (tmp == null) return false;
		tmp.value = newV;
		return true;
	}

	public boolean isEmpty() {
		return this.key == null;
	}

	public void clear() {
		// considering java has perfect gc, just assign objects as null
		this.left = null;
		this.right = null;
		this.remove(this.key);
	}

	public int getHeight() {
		if (this.key == null) return 0;
		if (this.left == null && this.right == null) return 1;
		else if (this.left == null) return this.right.getHeight() + 1;
		else if (this.right == null) return this.left.getHeight() + 1;
		else return Math.max(this.left.getHeight(), this.right.getHeight()) + 1;
	}

	public int getNodeCons() {
		if (this.left == null && this.right == null) return 1;
		else if (this.left == null) return this.right.getNodeCons() + 1;
		else if (this.right == null) return this.left.getNodeCons() + 1;
		else return this.left.getNodeCons() + this.right.getNodeCons() + 1;
	}

	public int getDepth() {
		if (this.parent != null) return 1 + this.parent.getDepth();
		else return 0;
	}
}
