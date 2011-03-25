package table.samples.conditional_editing;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import table.samples.model.Person;
import table.samples.model.PersonList;
import table.tools.DefaultColumn;
import table.tools.DefaultTable;

public class ConditionalEditableColumnsTable extends DefaultTable<Person> {
	
	protected PersonList list;

	public ConditionalEditableColumnsTable(Composite parent, int style, PersonList list) {
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
				new AddressColumn(),
				new OtherColumn(),
				new ActiveColumn()
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
			super("name", "Person's Name", 200, SWT.LEFT, true, new TextCellEditor(table));
		}
		
		@Override
		public boolean canModify(Person element) {
			return element.isActive();
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
			super("address", "Address", 150, SWT.LEFT, true, new TextCellEditor(table));
		}
		
		@Override
		public boolean canModify(Person element) {
			return element.isActive();
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

	class OtherColumn extends BaseColumn {
		public OtherColumn() {
			super("other", "Other Information", 150, SWT.LEFT, true, new TextCellEditor(table));
		}
		
		@Override
		public boolean canModify(Person element) {
			return element.isActive();
		}

		@Override
		public Object getValue(Person element) {
			return element.getOther();
		}

		@Override
		public String getText(Person element) {
			return element.getOther();
		}

		@Override
		public void modify(Person element, Object value) {
			element.setOther((String)value);
			list.changePerson(element);
		}
	}
	
	class ActiveColumn extends BaseColumn {
		public ActiveColumn() {
			super("active", "Active", 80, SWT.CENTER, true, new CheckboxCellEditor(table));
		}

		@Override
		public Object getValue(Person element) {
			return new Boolean(element.isActive());
		}

		@Override
		public String getText(Person element) {
			return element.isActive() ? "Active" : "Inactive";
		}

		@Override
		public void modify(Person element, Object value) {
			element.setActive(((Boolean)value).booleanValue());
			list.changePerson(element);
		}
	}

}
