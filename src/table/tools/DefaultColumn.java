package table.tools;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;

public abstract class DefaultColumn<T> {

	protected int style;
	protected int width;
	protected String property;
	protected String title;
	protected CellEditor editor;
	protected boolean editable;
	protected TableColumn column;
	protected ViewerSorter sorter;
	
	public DefaultColumn(String property, String title, int width, int style, boolean editable, CellEditor editor) {
		this.property = property;
		this.title = title;
		this.width = width;
		this.style = style;
		this.editable = editable;
		this.editor = editor;
	}
	
	public boolean canModify(T element) {
		return editable;
	}
	
	public abstract Object getValue(T element);
	
	public abstract String getText(T element);
	
	public abstract void modify(T element, Object value);
	
	public Image getImage(T element) {
		return null;
	}
	
	public void configureColumn(TableColumn column) {
		this.column = column;
	}

	public String getProperty() {
		return property;
	}

	public String getTitle() {
		return title;
	}

	public CellEditor getEditor() {
		return editor;
	}

	public boolean isEditable() {
		return editable;
	}

	public TableColumn getColumn() {
		return column;
	}

	public ViewerSorter getSorter() {
		return sorter;
	}

	public int getStyle() {
		return style;
	}

	public int getWidth() {
		return width;
	}
}
