package lab7;

public interface VisitableTimetableElement {
    void accept(TimetableElementVisitor visitor);
}
