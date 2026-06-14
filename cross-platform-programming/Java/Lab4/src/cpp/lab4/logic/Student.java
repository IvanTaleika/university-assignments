package cpp.lab4.logic;

import cpp.lab4.StudentSubscriber;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Student implements Runnable {



    public enum State {
        WAITING,
        IN_PROGRESS,
        ENDED
    }

    private LinkedList<StudentSubscriber> studentSubscribers;
    private State state;
    public static final int READ_TIME_MS = 10000;
    private String book;
    private Library library;
    private final int id;
    private static int idMemory = 0;

    public Student(String book, Library library) {
        studentSubscribers = new LinkedList<>();
        state = State.WAITING;
        id = idMemory;
        idMemory++;
        this.book = book;
        this.library = library;
    }

    @Override
    public void run() {
        ArrayBlockingQueue<String> bookQueue = library.getBookQueue(book);
        try {
            bookQueue.take();
            try {
                updateState(State.IN_PROGRESS);
                Thread.sleep(READ_TIME_MS);
                bookQueue.put(book);
                updateState(State.ENDED);
            } catch(InterruptedException e){
                bookQueue.put(book);
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            updateState(State.ENDED);
        }

    }

    public void subscribe(StudentSubscriber studentSubscriber) {
        studentSubscribers.push(studentSubscriber);
    }

    public void unsubscribe(StudentSubscriber studentSubscriber) {
        studentSubscribers.remove(studentSubscriber);
    }

    private void updateState(Student.State state) {
        this.state = state;
        for(StudentSubscriber studentSubscriber : studentSubscribers){
            studentSubscriber.updateState(this, state);
        }
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public State getState() {
        return state;
    }

}
