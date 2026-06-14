package cpp.lab4.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class ObservableStudent {
    private final int id;
    private final String book;
    private final SimpleStringProperty state;

    public ObservableStudent(int id, String book, String state) {
        this.id = id;
        this.book = book;
        this.state = new SimpleStringProperty(state);
    }

    public ObservableValue<String> stateProperty() {
        return state;
    }

    public String getBook() {
        return book;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String newState) {
        state.set(newState);
    }

//    public void updateState(Student who, Student.State newState) {
//        setState(newState);
//    }
}
