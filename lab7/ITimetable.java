package lab7;

public interface ITimetable {
    boolean canBeTransferredTo(Term term, boolean full_time);
    boolean busy(Term term);
    boolean put(Lesson lesson);
    void perform(Action[] actions);
    Object get(Term term);
}
