package cpp.lab1;

import cpp.lab1.gui.ConverterGUI;
import org.eclipse.swt.widgets.Display;

//TODO: set vertical alignment
public class Source {

    public static void main(String[] args) {
        Display display = new Display();
        ConverterGUI shell = new ConverterGUI(display);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

}
