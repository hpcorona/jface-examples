package table.tools;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public abstract class DefaultSorter<T> extends ViewerSorter {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		return compareElements(viewer, (T)e1, (T)e2);
	}

	public abstract int compareElements(Viewer viewer, T t1, T t2);
	
}
