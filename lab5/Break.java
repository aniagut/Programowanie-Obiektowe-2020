package lab5;

public class Break {

    private BasicTerm term;

    public void setterm(BasicTerm term) {
        this.term = term;
    }

    public BasicTerm getterm() {
        return term;
    }

    public Break(BasicTerm term){
        this.term=term;
    }

    @Override
    public String toString() {
        return "Przerwa";
    }
}
