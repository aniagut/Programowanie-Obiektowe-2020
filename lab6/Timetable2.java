package lab6;

public class Timetable2 extends AbstractTimetable implements Observer {

    private Break breaks[];

    public void setBreaks(Break breaks[]){
        this.breaks=breaks;
    }

    public Break[] getBreaks(){
        return breaks;
    }

    public Timetable2(Break breaks[]){
        super();
        this.breaks=breaks;
    }
    @Override
    public void update(Term term1, Term term2) {
        
        int key1=term1.hashCode();
        int key2=term2.hashCode();
        this.map.put(key2, map.remove(key1));

    }

    @Override
    public boolean put(Lesson lesson) {

        Term term = lesson.getterm();
        if (!canBeTransferredTo(term, lesson.getfull_time())) throw new IllegalArgumentException("Lesson can not be transferred");
        else {
            Break[] przerwy = this.breaks;
            int len2 = przerwy.length;
            for (int i = 0; i < len2; i++) {
                BasicTerm przerwa = przerwy[i].getterm();
                if (term.inMinute() == przerwa.inMinute()) {
                    if (!term.getskipBreaks()){
                        throw new IllegalArgumentException("Lesson can not be transferred");
                    }
                    else {
                        lesson.getterm().sethour(przerwa.endTerm().gethour());
                        lesson.getterm().setminute(przerwa.endTerm().getminute());
                    }

                } else if (term.endTerm().inMinute() == przerwa.endTerm().inMinute()) {
                    if (!term.getskipBreaks()) {
                        throw new IllegalArgumentException("Lesson can not be transferred");
                    }
                    else {
                        int min = lesson.getterm().inMinute() - przerwa.getduration();
                        int h = 0;
                        while (min >= 60) {
                            h += 1;
                            min -= 60;
                        }
                        lesson.getterm().sethour(h);
                        lesson.getterm().setminute(min);
                    }
                }
            }
            Term term1= lesson.getterm();
            int key=term1.hashCode();
            this.map.put(key,lesson);
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
            Break[] przerwy = this.breaks;
            int len2 = przerwy.length;
            switch(actions[j]){
                case TIME_EARLIER:
                    int h=0;
                    int min=term.inMinute()- term.getduration();
                    for (int i = 0; i < len2; i++) {
                        BasicTerm przerwa = przerwy[i].getterm();
                        if (min==przerwa.inMinute())
                        {
                            if(!term.getskipBreaks()) throw new UnsupportedOperationException("Lesson can not be transferred");
                            else min=przerwa.endTerm().inMinute();
                        }
                        else if(min+term.getduration()==przerwa.endTerm().inMinute()){
                            if(!term.getskipBreaks()) throw new UnsupportedOperationException("Lesson can not be transferred");
                            else min-=przerwa.getduration();
                        }
                    }
                    while(min>=60){
                        h+=1;
                        min-=60;
                    }
                    Term term1=new Term(h,min,term.getday());
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
                    int min1=term.inMinute()+term.getduration();
                    for (int i = 0; i < len2; i++) {
                        BasicTerm przerwa = przerwy[i].getterm();
                        if (min1 == przerwa.inMinute()) {
                            if (!term.getskipBreaks()) throw new UnsupportedOperationException("Lesson can not be transferred");
                            else min1 = przerwa.endTerm().inMinute();
                        } else if ((min1 + term.getduration()) == przerwa.endTerm().inMinute()) {
                            if (!term.getskipBreaks()) throw new UnsupportedOperationException("Lesson can not be transferred");
                            else min1 -= przerwa.getduration();
                        }
                    }
                    while (min1 >= 60) {
                        h1 += 1;
                        min1 -= 60;
                    }
                    Term term2=new Term(h1,min1,term.getday());
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


        Break[] przerwy=this.breaks;
        int len2=przerwy.length;
        for(int i=0;i<len2;i++){
            BasicTerm przerwa=przerwy[i].getterm();
            if((term.inMinute()==przerwa.inMinute())) return przerwy[i].toString();

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
        int len=this.breaks.length;
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
            String string2;
            if(k<len){
                BasicTerm przerwa=this.breaks[k].getterm();
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
