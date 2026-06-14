package cpp.lab4.gui;

import cpp.lab4.StudentSubscriber;
import cpp.lab4.logic.MainThread;
import cpp.lab4.logic.Student;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javax.swing.event.ChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


public class LibraryScene implements StudentSubscriber {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 500;

    private MainThread mainThread;
    private Scene mainScene;
    private Text title;
    private Button getBookButton;
    private Button endThreadButton;
    private TableView<ObservableStudent> studentQueueTable;
    private HashMap<Student, ObservableStudent> studentMapInfo;
    private ObservableList<ObservableStudent> tableData;
    private ChoiceDialog<String> bookSelectDialog;

    public LibraryScene(MainThread mainThread) {

        this.mainThread = mainThread;
        BorderPane mainPane = new BorderPane();
        mainScene = new Scene(mainPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        title = new Text("My cool library reading room");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        mainPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(10));


        getBookButton = new Button("New student");
        getBookButton.setOnAction(event -> {
            if(bookSelectDialog == null){
                bookSelectDialog = new ChoiceDialog<>(
                        this.mainThread.getLibrary().getAvailableBooks()
                                       .getFirst() ,
                        this.mainThread.getLibrary().getAvailableBooks());
                bookSelectDialog.setContentText("Choose a book to read:");
                bookSelectDialog.setHeaderText(null);
                bookSelectDialog.setGraphic(null);
                bookSelectDialog.setTitle("New student");
            }
            Optional<String> result = bookSelectDialog.showAndWait();
            if(result.isPresent()){
                Student student = new Student(result.get(),
                        mainThread.getLibrary());
                ObservableStudent observableStudent = new
                        ObservableStudent(student.getId(), student.getBook(),
                        convertStateToString(student.getState()));
                studentMapInfo.put(student, observableStudent);
                tableData.add(observableStudent);
                student.subscribe(this);
                mainThread.startThread(student);
            }
        });
        mainPane.setRight(getBookButton);
        BorderPane.setAlignment(getBookButton, Pos.CENTER);
        BorderPane.setMargin(getBookButton, new Insets(5));

        endThreadButton = new Button("End threads");
        endThreadButton.setOnAction(event -> {
//            studentQueueTable.getSelectionModel().getSelectedItem()
        });

        studentQueueTable = new TableView<>();

        studentQueueTable.setRowFactory(e -> {
            TableRow<ObservableStudent> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    ObservableStudent rowData = row.getItem();
                    Student rowDataStudent = null;
                    for (Map.Entry<Student,ObservableStudent> entry : studentMapInfo.entrySet()) {
                        if (Objects.equals(rowData, entry.getValue())) {
                            rowDataStudent = entry.getKey();
                        }
                    }
                    if(rowDataStudent != null) {
                        tableData.remove(studentMapInfo.remove(rowDataStudent));
                        mainThread.interrupt(rowDataStudent);
                    }
                }
            });
            return row ;
        });

        studentQueueTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableData = FXCollections.observableArrayList();
        studentMapInfo = new HashMap<>();

        TableColumn<ObservableStudent, Integer> idColumn =
                new TableColumn<>("Student №");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ObservableStudent, String> bookColumn =
                new TableColumn<>("Book");
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));

        TableColumn<ObservableStudent, String> stateColumn =
                new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        studentQueueTable.setItems(tableData);

        studentQueueTable.getColumns().setAll(idColumn, bookColumn,
                stateColumn);
        mainPane.setCenter(studentQueueTable);
    }

    public Scene getScene() {
        return mainScene;
    }

    @Override
    public void updateState(Student who, Student.State newState) {
        if(newState == Student.State.ENDED) {
            tableData.remove(studentMapInfo.remove(who));
            mainThread.removeFromWatch(who);
        } else {
            studentMapInfo.get(who).setState(convertStateToString(newState));
        }
    }

    private String convertStateToString(Student.State state){
        switch(state) {
            case WAITING:
                return "Waiting";
            case IN_PROGRESS:
                return "In progress";
            case ENDED:
                return "Ended";
            default:
                return null;
        }
    }
}