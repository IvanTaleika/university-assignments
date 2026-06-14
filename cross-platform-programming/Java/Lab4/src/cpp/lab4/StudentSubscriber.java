package cpp.lab4;

import cpp.lab4.logic.Student;

public interface StudentSubscriber {
    void updateState(Student who, Student.State newState);
}
