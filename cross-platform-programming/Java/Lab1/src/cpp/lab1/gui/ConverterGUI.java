package cpp.lab1.gui;

import cpp.lab1.logic.Converter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cpp.lab1.utils.StringAsNumberUtils;
/**
 * Provides GUI for a {@link Converter} class.
 * <p>
 * Contents a shell with 2 {@link Label}, 2 {@link Text} and 2 {@link Button}
 * on it that gives user a possibility to convert metres to inches
 * and backwards.
 */
public class ConverterGUI {
    /**
     * Number of columns in GridLayout.
     */
    public static final int N_COLUMNS = 2;
    /**
     * Array of label names.
     */
    public static final String[] VALUE_NAMES = {"Метры", "Дюймы"};
    /**
     * Indent between button and text field.
     */
    public static final int BUTTONS_VERTICAL_INDENT = 10;
    /**
     * Minimum width of GUI shell.
     */
    public static final int MINIMUM_WIDTH = 500;
    /**
     * Minimum height of GUI shell.
     */
    public static final int MINIMUM_HEIGHT = 150;



    private Shell shell;
    private Label metreLabel;
    private Label inchLabel;
    private Text metreTextField;
    private Text inchTextField;
    private Button metreToInchConvertButton;
    private Button inchToMetreConvertButton;


    /**
     * Construct a new instance of a class on a display.
     *
     * @param display a display object to create a shell on it
     */
    public ConverterGUI(final Display display) {
        shell = new Shell(display);
        GridLayout shellLayout = new GridLayout(N_COLUMNS, true);
        shell.setLayout(shellLayout);
        buildLabels();
        buildTextFields();
        buildConvertButtons();
        shell.setMinimumSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        shell.setSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
    }

    /**
     * Calls {@code Shell.open()} method.
     */
    public void open() {
        shell.open();
    }

    /**
     * Calls {@code Shell.setMinimumSize(int, int)} method.
     *
     * @param width  specifies shell width in pixels
     * @param height specifies shell width in pixels
     */
    public void setMinimumSize(final int width, final int height) {
        shell.setMinimumSize(width, height);
    }

    /**
     * Calls {@code Shell.isDisposed()<} method.
     *
     * @return true if this widget has been disposed, and false otherwise
     */
    public boolean isDisposed() {
        return shell.isDisposed();
    }

    /**
     * Creates, customizes and adds labels on interface.
     */
    private void buildLabels() {
        metreLabel = new Label(shell, SWT.HORIZONTAL);
        buildLabel(metreLabel, VALUE_NAMES[0]);
        inchLabel = new Label(shell, SWT.HORIZONTAL);
        buildLabel(inchLabel, VALUE_NAMES[1]);
    }

    /**
     * Customizes and adds single label on interface.
     *
     * @param valueLabel a label that should be built
     * @param text       a label's text
     */
    private void buildLabel(final Label valueLabel, final String text) {
        valueLabel.setText(text);
        GridData gridData = new GridData();
        valueLabel.setLayoutData(gridData);
    }

    /**
     * Creates, customizes and adds text input fields on interface.
     */
    private void buildTextFields() {
        metreTextField = new Text(shell, SWT.SINGLE | SWT.BORDER);
        buildTextField(metreTextField);
        inchTextField = new Text(shell, SWT.SINGLE | SWT.BORDER);
        buildTextField(inchTextField);
    }

    /**
     * Customizes and adds single text field on interface.
     *
     * @param textField a text field that should be built
     */
    private void buildTextField(final Text textField) {
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        textField.setLayoutData(gridData);
    }

    /**
     * Creates, customizes and adds buttons on interface.
     */
    private void buildConvertButtons() {
        metreToInchConvertButton = new Button(shell, SWT.PUSH);
        buildConvertButton(metreToInchConvertButton,
                VALUE_NAMES[0] + " в " + VALUE_NAMES[1]);
        metreToInchConvertButton.addListener(SWT.Selection,
                event -> metreToInchListener());

        inchToMetreConvertButton = new Button(shell, SWT.PUSH);
        buildConvertButton(inchToMetreConvertButton,
                VALUE_NAMES[1] + " в " + VALUE_NAMES[0]);
        inchToMetreConvertButton.addListener(SWT.Selection,
                event -> inchToMetreListener());
    }

    /**
     * Is an action listener for {@code metreToInchButton}.
     * Do nothing if {@code metreTextField} is empty.
     * Otherwise parse text inside {@code metreTextField} to double, on success
     * call convert this number from metres to inches,
     * print error message otherwise.
     */
    private void metreToInchListener() {
        if (metreTextField.getText().isEmpty()) {
            return;
        }
        String metreStringValue = metreTextField.getText();
        try {
            double metreLength = Double.parseDouble(metreStringValue);
            StringAsNumberUtils stringAsNumberUtils =
                    new StringAsNumberUtils();
            inchTextField.setText(stringAsNumberUtils
                    .getTextDecimalFormat(metreStringValue)
                    .format(Converter.metreToInch(metreLength)));
        } catch (NumberFormatException e) {
            inchTextField.setText("Некорректное значение в метрах!");
        }
    }

    /**
     * Is an action listener for {@code inchToMetreButton}.
     * Do nothing if {@code inchTextField} is empty.
     * Otherwise parse text inside {@code inchTextField} to double, on success
     * call convert this number from inches to metres,
     * print error message otherwise.
     */
    private void inchToMetreListener() {
        if (inchTextField.getText().isEmpty()) {
            return;
        }
        String inchStringValue = inchTextField.getText();
        try {
            double inchLength = Double.parseDouble(inchStringValue);
            StringAsNumberUtils stringAsNumberUtils =
                    new StringAsNumberUtils();
            metreTextField.setText(stringAsNumberUtils
                    .getTextDecimalFormat(inchStringValue)
                    .format(Converter.inchToMetre(inchLength)));
        } catch (NumberFormatException e) {
            metreTextField.setText("Некоректное значение в дюймах!");
        }
    }

    /**
     * Customizes and adds single button on interface.
     *
     * @param convertButton a button that should be built
     * @param text          a button's text
     */
    private void buildConvertButton(final Button convertButton,
                                    final String text) {
        convertButton.setText(text);
        GridData gridData = new GridData();
        gridData.verticalIndent = BUTTONS_VERTICAL_INDENT;
        convertButton.setLayoutData(gridData);
    }

}