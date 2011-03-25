package table.tools;

public interface IModelListener {
	public void notifyNew(Object obj);
	public void notifyChange(Object obj);
	public void notifyDelete(Object obj);
}
