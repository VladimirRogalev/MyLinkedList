package telran.list.model;

import java.util.Iterator;

import telran.list.interfaces.IList;

public class MyLinkedList<E> implements IList<E> {
	private Node<E> first;
	private Node<E> last;
	private int size;

	@Override
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			Node<E> node = first;
			

			@Override
			public boolean hasNext() {

				return node !=null ;
			}

			@Override
			public E next() {
				E data = node.data;
				node = node.next;

				return data;
			}
		};
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(int index, E element) {
		if (index == size) {
			Node<E> newNode = new Node<E>(element, null, last);
			if (last != null) {
				last.next = newNode;
			} else {
				first = newNode;
			}

			last = newNode;
		} else {
			Node<E> node = getNodeByIndex(index);
			Node<E> newNode = new Node<E>(element, node, node.prev);
			node.prev = newNode;
			if (index != 0) {
				newNode.prev.next = newNode;
			} else {
				first = newNode;
			}
		}
		size++;
		return true;
	}

	@Override
	public E get(int index) {

		return getNodeByIndex(index).data;
	}

	private Node<E> getNodeByIndex(int index) {
		checkIndex(index);
		Node<E> node;

		if (index < size / 2) {
			node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		} else {
			node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
		}

		return node;
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}

	}

	@Override
	public int indexOf(Object o) {
		// My decision
//		int index = 0;
//		Node<E> node = first;
//		while (node != null) {
//			if (o.equals(node.data)) {
//				return index;
//			}
//			node = node.next;
//			index++;
//		}
//		return -1;
		
		// Decision from Andrey
		int index = 0;
		if(o!=null) {
			for (Node<E>  node = first;  node!=null; node = node.next, index++) {
				if(o.equals(node.data)) {
					return index;
				}
			}
		} else {
			for (Node<E>  node = first;  node!=null; node = node.next, index++) {
				if(o==node.data) {
					return index;
				}
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		// My decision
//		int index = size - 1;
//		Node<E> node = last;
//		while (node != null) {
//			if (o.equals(node.data)) {
//				return index;
//			}
//			node = node.prev;
//			index--;
//		}
//		return -1;

		// Decision from Andrey
		int index = size-1;
		if(o!=null) {
			for (Node<E>  node = last;  node!=null; node = node.prev, index--) {
				if(o.equals(node.data)) {
					return index;
				}
			}
		} else {
			for (Node<E>  node = last;  node!=null; node = node.prev, index--) {
				if(o==node.data) {
					return index;
				}
			}
		}
		return -1;
	}

	@Override
	public E remove(int index) {
		Node<E> victim = getNodeByIndex(index);
		return unlink(victim);
	}

	private E unlink(Node<E> victim) {
		E res = victim.data;
		Node <E> prev = victim.prev;
		Node <E> next = victim.next;
		if(prev != null) {
			prev.next = next;
			victim.prev = null;
		} else {
			first = next;
		}
		if(next != null) {
			prev.next = prev;
			victim.next = null;
		} else {
			last = next;
		}
		victim.data = null;
		size--;
		return res;
	}

	@Override
	public E set(int index, E element) {
		Node <E> victim = getNodeByIndex(index);
		E res = victim.data;
		victim.data = element;
		return res;
	}

}
