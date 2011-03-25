package table.samples;

import org.eclipse.swt.widgets.Display;

import table.samples.view.Application;

public class Main {
	public static void main(String[] args) {
		Application app = new Application();
		app.setBlockOnOpen(true);
		app.open();
		Display.getCurrent().dispose();
	}
}
