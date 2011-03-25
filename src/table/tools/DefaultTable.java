package table.tools;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public abstract class DefaultTable<T> extends LabelProvider implements ITableLabelProvider, ICellModifier, IStructuredContentProvider, IModelListener {
	
	protected Composite parent;
	protected int style;
	protected Table table;
	protected TableViewer viewer;
	protected DefaultColumn<T>[] columns;
	protected String[] columnProperties;
	protected CellEditor[] editors;
	
	public DefaultTable(Composite parent, int style) {
		this.parent = parent;
		this.style = style;
	}
	
	public void build() {
		table = createTable(parent, style);
		createColumns();
		viewer = createViewer(table);
	}
	
	protected abstract Table createTable(Composite parent, int style);
	
	protected abstract ViewerSorter getDefaultSorter();
		
	protected void createColumns() {
		columns = getColumns();
		columnProperties = new String[columns.length];
		editors = new CellEditor[columns.length];
		
		int idx = 0;
		for (DefaultColumn<T> col : columns) {
			TableColumn column = new TableColumn(table, col.getStyle(), idx);
			column.setText(col.getTitle());
			column.setWidth(col.getWidth());
			column.setMoveable(true);
			column.setResizable(true);
			
			final ViewerSorter sorter = col.getSorter();
			if (sorter != null) {
				column.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						viewer.setSorter(sorter);
					}
				});
			}
			
			col.configureColumn(column);
			
			columnProperties[idx] = col.getProperty();
			editors[idx] = col.getEditor();
			
			idx += 1;
		}
	}
	
	protected TableViewer createViewer(Table table) {
		viewer = new TableViewer(table);
		viewer.setUseHashlookup(true);
		
		viewer.setColumnProperties(columnProperties);
		viewer.setCellEditors(editors);
		viewer.setCellModifier(this);
		
		viewer.setSorter(getDefaultSorter());
		
		viewer.setContentProvider(this);
		viewer.setLabelProvider(this);
		viewer.setInput(this);
		
		return viewer;
	}
	
	public Table getTable() {
		return table;
	}
	
	protected abstract DefaultColumn<T>[] getColumns();
	
	public int seekForIndex(String property) {
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].getProperty().equals(property)) return i;
		}
		
		return -1;
	}
	
	public DefaultColumn<T> seekForColumn(String property) {
		int idx = seekForIndex(property);
		
		if (idx < 0) return null;
		
		return columns[idx];
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean canModify(Object obj, String property) {
		DefaultColumn<T> col = seekForColumn(property);
		
		return col.canModify((T)obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue(Object obj, String property) {
		return seekForColumn(property).getValue((T)obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void modify(Object obj, String property, Object value) {
		TableItem itm = (TableItem)obj;
		
		seekForColumn(property).modify(((T)itm.getData()), value);
		
		notifyChange(itm.getData());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Image getColumnImage(Object obj, int idx) {
		return columns[idx].getImage((T)obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getColumnText(Object obj, int idx) {
		return columns[idx].getText(((T)obj));
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object input) {
		return getElements();
	}
	
	public abstract Object[] getElements();
	
	public void notifyChange(Object obj) {
		notifyChange(obj, null);
	}

	public void notifyChange(Object obj, String[] properties) {
		viewer.update(obj, properties);
	}
	
	public void notifyNew(Object obj) {
		viewer.add(obj);
	}
	
	public void notifyDelete(Object obj) {
		viewer.remove(obj);
	}
	
}
