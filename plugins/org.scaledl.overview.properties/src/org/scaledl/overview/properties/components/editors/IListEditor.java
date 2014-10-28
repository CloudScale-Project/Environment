package org.scaledl.overview.properties.components.editors;

import java.util.Iterator;

public interface IListEditor<T> extends IEditor{

	public boolean canCreateDeleteEntry();
	
	public T createEntry();
	public void add(Object o);
	public void deleteEntry(Object o);
	public void removeEntry(Object o);
	
	public T getEntry(int index);
	public Iterator<T> getIterator();
	
	public void openDialog();
}
