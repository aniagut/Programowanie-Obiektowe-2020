package lab7;

public class Timetable1 extends AbstractTimetable{
    @Override
    public boolean put(Lesson lesson) {

        Term term1 = lesson.getterm();
        if (!canBeTransferredTo(term1, lesson.getfull_time())) {
            throw new IllegalArgumentException("Term is busy");
        } else {
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
            int key1=term.hashCode();
            switch(actions[j]){
                case TIME_EARLIER:
                    if(lesson.earlierTime()){
                        int min=term.inMinute()-term.getduration();
                        int h=0;
                        while(min>=60){
                            h+=1;
                            min-=60;
                        }
                        Term term1=new Term(h,min,term.getday());
                        int key=term1.hashCode();
                        this.map.put(key, map.remove(key1));
                    }
                    else throw new IllegalArgumentException("Term is busy");
                    break;
                case TIME_LATER:
                    if(lesson.laterTime()){
                        int min= term.inMinute()+term.getduration();
                        int h=0;
                        while(min>=60){
                            h+=1;
                            min-=60;
                        }
                        Term term1=new Term(h,min,term.getday());
                        int key=term1.hashCode();
                        this.map.put(key, map.remove(key1));
                    }
                    else throw new IllegalArgumentException("Term is busy");
                    break;
                case DAY_LATER:
                    if(lesson.laterDay()){
                        Term term1=new Term(term.gethour(),term.getminute(),term.getday().nextDay());
                        int key=term1.hashCode();
                        this.map.put(key, map.remove(key1));
                    }
                    else throw new IllegalArgumentException("Term is busy");
                    break;
                case DAY_EARLIER:
                    if(lesson.earlierDay()){
                        Term term1=new Term(term.gethour(),term.getminute(),term.getday().prevDay());
                        int key=term1.hashCode();
                        this.map.put(key, map.remove(key1));
                        lesson.setterm(term1);
                    }
                    else throw new IllegalArgumentException("Term is busy");
                    break;
                default:
                    throw new IllegalArgumentException("Translation " + actions[j] + " is incorrect");
            }
        }
    }
    @Override
    public Object get(Term term){
        if(this.map.containsKey(term)) {
            return this.map.get(term).getname();
        }
        else return null;
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
        Term term=new Term(8,0,null);
        while(term.gethour()<20){
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
                else string1+="                     *";
                j-=1;
                day=day.nextDay();
            }
            System.out.println(string1);
            term=term.endTerm();
        }

        return " ";

    }
}
