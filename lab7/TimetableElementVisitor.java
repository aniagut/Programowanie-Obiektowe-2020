package lab7;

public interface TimetableElementVisitor {

    void visit(Lesson lesson);
    void visit(Break przerwa);

    void visit(Timetable2 timetable);
}
