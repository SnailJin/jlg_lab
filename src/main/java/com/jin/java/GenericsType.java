package com.jin.java;

public class GenericsType {
	
	class Lhist<V>{
		private V[] array;
		private int size;
		public Lhist(int capacity){
			this.array= (V[]) new Object[capacity];
		}
		public void add(V value){
			if(size==array.length){
				throw new IndexOutOfBoundsException(Integer.toString(size));
			}else if(value== null){
				throw new NullPointerException();
			}else{
				array[size++]=value;
			}
		}
		public void remove(V value){
			int removeCount = 0;
			if(this.size==0){
				throw new NullPointerException();
			}else{
				for(int i=0;i<size;i++){
					if(this.array[i].equals(value))
						removeCount++;
					else if(removeCount>0){
						 array[i-removeCount] = array[i];
			              array[i] = null;
					}
				}
				size -= removeCount;;
			}
		}
		public V get(int i){
			if (i >= size)
	            throw new IndexOutOfBoundsException(Integer.toString(i));
	        return array[i];
		}
		public int size(){
			return this.size;
		}
	}
	Lhist<Integer> li = new Lhist<Integer>(30);
	public static void main(String args[]){
		GenericsType genT= new GenericsType();
		Lhist<Integer> li=genT.li;
		li.add(2);
		li.add(1);
		li.add(2);
		li.remove(2);
		for(int i=0;i<li.size();i++){
			System.out.println(li.get(i));
		}
		
	}
}
