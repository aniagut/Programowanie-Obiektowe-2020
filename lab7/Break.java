package lab7;

public class Break implements VisitableTimetableElement{
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
    public void accept(TimetableElementVisitor visitor){
        visitor.visit(this);
    }
    @Override
    public String toString() {
        return "Przerwa";
    }

    public boolean equals(Break przerwa){
        if(this==przerwa){
            return true;
        }
        if(przerwa==null){
            return false;
        }
        if(getClass()!=przerwa.getClass()){
            return false;
        }
        if(this.term.gethour()!=przerwa.getterm().gethour()){
            return false;
        }
        if(this.term.getminute()!=przerwa.getterm().getminute()){
            return false;
        }
        if(this.getterm().getduration()!=przerwa.getterm().getduration()){
            return false;
        }
        return true;
    }
}
