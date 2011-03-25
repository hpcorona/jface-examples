package table.samples.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

import table.samples.model.Person;
import table.samples.model.PersonList;

public class NewPersonAction extends Action {

	protected Shell shell;
	protected PersonList list;

	public NewPersonAction(Shell shell, PersonList list) {
		this.shell = shell;
		this.list = list;

		setText("New Person");
	}

	public void run() {
		InputDialog dialog = new InputDialog(shell, "Create a new Person",
				"Enter the person's name", "Zoey", new NameValidator());
		
		if (dialog.open() == InputDialog.OK) {
			Person p = new Person(dialog.getValue());
			list.newPerson(p);
		}
	}

	class NameValidator implements IInputValidator {

		@Override
		public String isValid(String name) {
			if (name.length() == 0) {
				return "Name is too short";
			}

			return null;
		}

	}

}
