package lab5;


import java.util.ArrayList;
import java.util.List;

public class Timetable1 extends AbstractTimetable{


    @Override
    public boolean put(Lesson lesson){

        Term term1=lesson.getterm();
        if(!canBeTransferredTo(term1, lesson.getfull_time())) return false;
        else this.lista.add(lesson);
        return true;
    }
    @Override
    public void perform(Action[] actions){
        int n=actions.length;

        int len=this.lista.size();
        for(int j=0;j<n;j++){
            Lesson lesson=this.lista.get(j%len);
            switch(actions[j]){
                case TIME_EARLIER:
                    if(lesson.earlierTime()){
                        Term term=lesson.getterm();
                        int min=term.inMinute()-term.getduration();
                        int h=0;
                        while(min>=60){
                            h+=1;
                            min-=60;
                        }
                        Term term1=new Term(h,min,term.getday());
                        lista.get(j%len).setterm(term1);
                    }
                    break;
                case TIME_LATER:
                    if(lesson.laterTime()){
                        Term term=lesson.getterm();
                        int min= term.inMinute()+term.getduration();
                        int h=0;
                        while(min>=60){
                            h+=1;
                            min-=60;
                        }
                        Term term1=new Term(h,min,term.getday());
                        lista.get(j%len).setterm(term1);
                    }
                    break;
                case DAY_LATER:
                    if(lesson.laterDay()){
                        Day day=lista.get(j%len).getterm().getday().nextDay();
                        lista.get(j%len).getterm().setday(day);
                    }
                    break;
                case DAY_EARLIER:
                    if(lista.get(j%len).earlierDay()){
                        Day day=lista.get(j%len).getterm().getday().prevDay();
                        lista.get(j%len).getterm().setday(day);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    public Object get(Term term){

        int len=this.lista.size();

        for(int i=0;i<len;i++){

            Lesson lekcja=this.lista.get(i);
            Term term1=lekcja.getterm();
            if(term1.getday()==term.getday()) {
                if (term.inMinute() == term1.inMinute()) return lekcja.getname();
            }

        }
        return null;
    }

    @Override
    public String toString() {
        Day day=Day.MON;
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
            day=Day.MON;
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
