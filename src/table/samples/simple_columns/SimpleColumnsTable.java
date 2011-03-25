package table.samples.simple_columns;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import table.samples.model.Person;
import table.samples.model.PersonList;
import table.tools.DefaultColumn;
import table.tools.DefaultTable;

public class SimpleColumnsTable extends DefaultTable<Person> {
	
	protected PersonList list;

	public SimpleColumnsTable(Composite parent, int style, PersonList list) {
		super(parent, style);
		
		this.list = list;
		list.addModelListener(this);
		
		build();
	}

	@Override
	protected Table createTable(Composite parent, int style) {
		Table table = new Table(parent, style);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		return table;
	}

	@Override
	protected ViewerSorter getDefaultSorter() {
		return null;
	}

	@Override
	protected DefaultColumn<Person>[] getColumns() {
		return new BaseColumn[] {
				new NameColumn(),
				new AddressColumn()
		};
	}

	@Override
	public Object[] getElements() {
		return list.toArray();
	}
	
	abstract class BaseColumn extends DefaultColumn<Person> {
		public BaseColumn(String property, String title, int width, int style,
				boolean editable, CellEditor editor) {
			super(property, title, width, style, editable, editor);
		}
	}
	
	class NameColumn extends BaseColumn {
		public NameColumn() {
			super("name", "Person's Name", 300, SWT.LEFT, false, null);
		}

		@Override
		public Object getValue(Person element) {
			return element.getName();
		}

		@Override
		public String getText(Person element) {
			return element.getName();
		}

		@Override
		public void modify(Person element, Object value) {
			element.setName((String)value);
			list.changePerson(element);
		}
	}

	class AddressColumn extends BaseColumn {
		public AddressColumn() {
			super("address", "Address", 300, SWT.LEFT, false, null);
		}

		@Override
		public Object getValue(Person element) {
			return element.getAddress();
		}

		@Override
		public String getText(Person element) {
			return element.getAddress();
		}

		@Override
		public void modify(Person element, Object value) {
			element.setAddress((String)value);
			list.changePerson(element);
		}
	}
}
