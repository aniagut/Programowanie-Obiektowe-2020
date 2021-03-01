package lab7;
import java.util.TreeSet;
import java.util.AbstractCollection;
public class Timetable2 extends AbstractTimetable  implements VisitableTimetableElement, Observer{
    public TreeSet<BasicTerm> breaks = new TreeSet<BasicTerm>();

    public void setBreaks(TreeSet<BasicTerm> breaks) {
        this.breaks = breaks;
    }

    public TreeSet<BasicTerm> getBreaks() {
        return breaks;
    }

    public Timetable2(TreeSet<BasicTerm> breaks) {
        this.breaks = breaks;
    }

    @Override
    public void update(Term term1, Term term2) {

        int key1=term1.hashCode();
        int key2=term2.hashCode();
        this.map.put(key2, map.remove(key1));

    }

    @Override
    public void accept(TimetableElementVisitor visitor){
        visitor.visit(this);
    }
    @Override
    public boolean put( Lesson lesson) {

        Term term = lesson.getterm();
        if (!canBeTransferredTo(term, lesson.getfull_time()))
            throw new IllegalArgumentException("Lesson can not be transferred");
        else {
            TreeSet<BasicTerm> przerwy = this.breaks;
            if (przerwy.contains(new BasicTerm(term.gethour(), term.getminute()))) {
                if (!term.getskipBreaks()) {
                    throw new IllegalArgumentException("Lesson can not be transferred");
                } else {
                    lesson.getterm().sethour(przerwy.ceiling(new BasicTerm(term.gethour(), term.getminute())).endTerm().gethour());
                    lesson.getterm().setminute(przerwy.ceiling(new BasicTerm(term.gethour(), term.getminute())).endTerm().getminute());
                }

            }
            else if (przerwy.floor(new BasicTerm(term.endTerm().gethour(), term.endTerm().getminute())).endTerm().gethour() == term.endTerm().gethour() && przerwy.floor(new BasicTerm(term.endTerm().gethour(), term.endTerm().getminute())).endTerm().getminute() == term.endTerm().getminute()) {
                if (!term.getskipBreaks()) {
                    throw new IllegalArgumentException("Lesson can not be transferred");
                } else {
                    int min = term.inMinute() - przerwy.ceiling(new BasicTerm(term.endTerm().gethour(), term.endTerm().getminute())).duration;
                    int h = 0;
                    while (min >= 60) {
                        min -= 60;
                        h += 1;
                    }
                    lesson.getterm().sethour(h);
                    lesson.getterm().setminute(min);
                }

            }
            Term term1 = lesson.getterm();
            int key = term1.hashCode();
            this.map.put(key, lesson);
            return true;
        }
    }

    @Override
    public void perform(Action[] actions){

        int n=actions.length;

        int len=map.size();
        Lesson[] arr = map.values().toArray(new Lesson[0]);

        for(int j=0;j<n;j++){
            Lesson lesson=arr[(j%len)];
            Term term=lesson.getterm();
            TreeSet<BasicTerm> przerwy = this.breaks;
            switch(actions[j]){
                case TIME_EARLIER:
                    int h=0;
                    int min1=term.inMinute()- term.getduration();
                    int min=min1;
                    while(min>=60){
                        h+=1;
                        min-=60;
                    }
                    BasicTerm newterm=new BasicTerm(h,min);
                    newterm.setduration(term.getduration());
                    if (przerwy.floor(newterm.endTerm()).endTerm().gethour() == newterm.endTerm().gethour() && przerwy.floor(newterm.endTerm()).endTerm().getminute() == newterm.endTerm().getminute()){
                        if(!term.getskipBreaks()) throw new UnsupportedOperationException("Lesson can not be transferred");
                        else min1-=przerwy.floor(newterm.endTerm()).getduration();
                    }
                    h=0;
                    while(min1>=60){
                        h+=1;
                        min1-=60;
                    }

                    Term term1=new Term(h,min1,term.getday());
                    term1.setduration(term.getduration());
                    if(lesson.getTimetable().canBeTransferredTo(term1, lesson.getfull_time())){
                        lesson.notifyObservers(term,term1);
                    }
                    else{
                        throw new UnsupportedOperationException("Lesson can not be transferred");
                    }
                    break;
                case TIME_LATER:
                    int h1=0;
                    int min2=term.inMinute()+term.getduration();
                    while(min2>=60) {
                        h1+=1;
                        min2-=60;
                    }
                    if (przerwy.contains(new BasicTerm(h1,min2))) {
                        if (!term.getskipBreaks()) throw new UnsupportedOperationException("Lesson can not be transferred");
                        else min2 = przerwy.ceiling(new BasicTerm(h1,min2)).endTerm().inMinute();
                        }
                    h1=0;
                    while (min2 >= 60) {
                        h1 += 1;
                        min2 -= 60;
                    }
                    Term term2=new Term(h1,min2,term.getday());
                    term2.setduration(term.getduration());

                    if(lesson.getTimetable().canBeTransferredTo(term2,lesson.getfull_time())){
                        lesson.notifyObservers(term,term2);
                    }
                    else{
                        throw new UnsupportedOperationException("Lesson can not be transferred");
                    }
                    break;
                case DAY_LATER:
                    if(lesson.laterDay()){
                        Term term3=new Term(term.gethour(),term.getminute(),term.getday().nextDay());
                        lesson.notifyObservers(term,term3);
                    }
                    else throw new IllegalArgumentException("Term is busy");
                    break;
                case DAY_EARLIER:
                    if(lesson.earlierDay()){
                        Term term4=new Term(term.gethour(),term.getminute(),term.getday().prevDay());
                        lesson.notifyObservers(term,term4);
                    }
                    else throw new IllegalArgumentException("Term is busy");
                    break;
                default:
                    throw new IllegalArgumentException("Translation" + actions[j] + " is incorrect");
            }
        }
    }

    @Override
    public Object get(Term term){
        int key=term.hashCode();
        if(this.map.containsKey(key)) {
            return this.map.get(key).getname();
        }

        TreeSet<BasicTerm> przerwy=this.breaks;
        if(przerwy.contains(new BasicTerm(term.gethour(),term.getminute()))){
            return "Przerwa";
        }
        return null;
    }

    @Override
    public String toString() {
        Day day= Day.MON;
        String dni="            ";
        int i=7;
        while(i!=0){
            dni+="*"+day+"*              ";
            day=day.nextDay();
            i-=1;
        }

        System.out.println(dni);
        int k=0;
        int len=this.breaks.size();
        Term term=new Term(8,0,null);
        while(term.endTerm().gethour()<20){
            System.out.println("            ******************************************************************************************************************************************************");
            String string1;
            if(term.getminute()<10 && term.endTerm().getminute()<10){
                string1=term.gethour()+":0"+term.getminute()+"-"+term.endTerm().gethour()+":0"+term.endTerm().getminute()+"  *";
            }
            else if(term.getminute()<10){
                string1=term.gethour()+":0"+term.getminute()+"-"+term.endTerm().gethour()+":"+term.endTerm().getminute()+"  *";
            }
            else if(term.endTerm().getminute()<10){
                string1=term.gethour()+":"+term.getminute()+"-"+term.endTerm().gethour()+":0"+term.endTerm().getminute()+"  *";
            }
            else string1=term.gethour()+":"+term.getminute()+"-"+term.endTerm().gethour()+":"+term.endTerm().getminute()+"  *";
            int j=7;
            day= Day.MON;
            while(j!=0){
                term.setday(day);
                if(busy(term)) string1+="      "+this.get(term)+"      *";
                else string1+="                    *";
                j-=1;
                day=day.nextDay();
            }
            System.out.println(string1);
            term=term.endTerm();
            BasicTerm przerwy[]=this.breaks.toArray(BasicTerm[]::new);
            String string2;
            if(k<len){
                BasicTerm przerwa=przerwy[k];
                if(przerwa.getminute()<10 && przerwa.endTerm().getminute()<10){
                    string2=przerwa.gethour()+":0"+przerwa.getminute()+"-"+przerwa.endTerm().gethour()+":0"+przerwa.endTerm().getminute();
                }
                else if(przerwa.getminute()<10){
                    string2=przerwa.gethour()+":0"+przerwa.getminute()+"-"+przerwa.endTerm().gethour()+":"+przerwa.endTerm().getminute();
                }
                else if(przerwa.endTerm().getminute()<10){
                    string2=przerwa.gethour()+":"+przerwa.getminute()+"-"+przerwa.endTerm().gethour()+":0"+przerwa.endTerm().getminute();
                }
                else{
                    string2=przerwa.gethour()+":"+przerwa.getminute()+"-"+przerwa.endTerm().gethour()+":"+przerwa.endTerm().getminute();
                }
                string2+="      --------------------------------------------------------------------------------------------------------------------------------------------------";
                System.out.println(string2);
                term.sethour(przerwa.endTerm().gethour());
                term.setminute(przerwa.endTerm().getminute());
                k+=1;
            }

        }

        return " ";

    }
}


