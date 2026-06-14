package cpp.lab4.logic;

import java.util.HashMap;

public class MainThread {
    private Library library;
    private HashMap<Student, Thread> studentThreads;

    public MainThread(Library library) {
        studentThreads = new HashMap<>();
        this.library = library;
    }

    public void startThread(Student student) {
        Thread thread  = new Thread(student);
        studentThreads.put(student, thread);
        thread.start();
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }


    public void interrupt(Student student){
        studentThreads.get(student).interrupt();
        removeFromWatch(student);
    }

    public void removeFromWatch(Student student){
        studentThreads.remove(student);
    }

}
