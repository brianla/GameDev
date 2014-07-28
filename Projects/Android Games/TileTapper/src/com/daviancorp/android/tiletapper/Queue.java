package com.daviancorp.android.tiletapper;

public class Queue<T> {

	private Node<T> head, tail;
	
	public Queue() {
		head = tail = null;
	}
	
	public void add(T data) {
		Node<T>	newNode = new Node<T>(data);

		if (tail == null) {
			head = tail = newNode;
		}
		else {
			tail.setNext(newNode);
			tail = newNode;
		}
	}
	
	public Node<T> remove() {
		if (head == null) return null;
		
		Node<T> retNode = head;
		head = head.next();
		if (tail == retNode) {
			tail = null;
		}
		
		return retNode;
	}
	
	static class Node<T> {
		private T data;
		private Node<T>	next;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> next() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
}
