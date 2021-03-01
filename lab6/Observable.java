package lab6;

public interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(Term term1, Term term2);
}
