package table.samples.view;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import table.samples.action.NewPersonAction;
import table.samples.conditional_editing.ConditionalEditableColumnsTable;
import table.samples.editable_columns.EditableColumnsTable;
import table.samples.editable_images.EditableImageColumnsTable;
import table.samples.model.PersonList;
import table.samples.simple_columns.SimpleColumnsTable;
import table.tools.DefaultTable;

public class Application extends ApplicationWindow {

	protected PersonList list;
	protected Shell shell;
	protected TabFolder folder;

	public Application() {
		this(null);
	}

	public Application(Shell parentShell) {
		super(parentShell);

		this.shell = parentShell;

		list = new PersonList();
		list.generateSampleData();

		addToolBar(SWT.FLAT | SWT.WRAP);
	}

	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager tbm = new ToolBarManager(style);
		tbm.add(new NewPersonAction(shell, list));

		return tbm;
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Easy Tables with JFace");
	}

	protected void initializeBounds() {
		getShell().setSize(640, 480);
		getShell().setLocation(40, 40);
	}

	protected Control createContents(Composite parent) {
		folder = new TabFolder(parent, SWT.BORDER);

		SimpleColumnsTable simple = new SimpleColumnsTable(folder,
				DefaultTable.DEFAULT_STYLE, list);
		createTabItem(simple.getTable(), "Simple");

		EditableColumnsTable editable = new EditableColumnsTable(folder,
				DefaultTable.DEFAULT_STYLE, list);
		createTabItem(editable.getTable(), "Editable");

		ConditionalEditableColumnsTable conditional = new ConditionalEditableColumnsTable(
				folder, DefaultTable.DEFAULT_STYLE, list);
		createTabItem(conditional.getTable(), "Conditional");

		EditableImageColumnsTable images = new EditableImageColumnsTable(
				folder, DefaultTable.DEFAULT_STYLE, list);
		createTabItem(images.getTable(), "Images");

		return folder;
	}

	protected void createTabItem(Control control, String title) {
		TabItem item = new TabItem(folder, SWT.NONE);
		item.setText(title);
		item.setControl(control);
	}
}
