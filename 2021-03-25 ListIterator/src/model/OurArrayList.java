package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

@SuppressWarnings("serial")
public class OurArrayList<T> extends ArrayList<T> {
	
	// iterator
	
	@Override
	public Iterator<T> iterator(){
		return listIterator();
	}
	
	// list iterators
	
	public ListIterator<T> listIteratorImmutable(int index){
		return new ListIteratorRandom<T>(this, index, ListIteratorRestrictions.IMMUTABLE);
	}
	
	public ListIterator<T> listIteratorUnmodifiable(int index){
		return new ListIteratorRandom<T>(this, index, ListIteratorRestrictions.UNMODIFIABLE);
	}
	
	@Override
	public ListIterator<T> listIterator(int index){
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException("list iterator; index: "+ index + "; size:" + size());
		return new ListIteratorRandom<T>(this, index);
	}
	
	public ListIterator<T> listIterator(){
		return new ListIteratorRandom<T>(this);
	}
}
