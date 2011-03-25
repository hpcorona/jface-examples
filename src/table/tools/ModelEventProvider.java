package table.tools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class ModelEventProvider {
	
	protected Set<IModelListener> modelListeners = new HashSet<IModelListener>();
	
	public void addModelListener(IModelListener listener) {
		modelListeners.add(listener);
	}
	
	public void removeModelListener(IModelListener listener) {
		modelListeners.remove(listener);
	}
	
	public void notifyNew(Object obj) {
		Iterator<IModelListener> iterator = modelListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().notifyNew(obj);
		}
	}
	
	public void notifyChange(Object obj) {
		Iterator<IModelListener> iterator = modelListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().notifyChange(obj);
		}
	}
	
	public void notifyDelete(Object obj) {
		Iterator<IModelListener> iterator = modelListeners.iterator();
		while (iterator.hasNext()) {
			iterator.next().notifyDelete(obj);
		}
	}
	
}
