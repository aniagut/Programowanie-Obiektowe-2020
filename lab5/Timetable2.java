package lab5;

import java.util.ArrayList;
import java.util.List;

public class Timetable2 extends AbstractTimetable {

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
    public boolean put(Lesson lesson) {

        Term term = lesson.getterm();
        if (!canBeTransferredTo(term, lesson.getfull_time())) return false;
        else {
            Break[] przerwy = this.breaks;
            int len2 = przerwy.length;
            for (int i = 0; i < len2; i++) {
                BasicTerm przerwa = przerwy[i].getterm();
                if (term.inMinute() == przerwa.inMinute()) {
                    if (!term.getskipBreaks()) return false;
                    else {
                        lesson.getterm().sethour(przerwa.endTerm().gethour());
                        lesson.getterm().setminute(przerwa.endTerm().getminute());
                    }

                } else if (term.endTerm().inMinute() == przerwa.endTerm().inMinute()) {
                    if (!term.getskipBreaks()) return false;
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
            this.lista.add(lesson);
            return true;
        }
    }
    @Override
    public void perform(Action[] actions){

        int n=actions.length;

        int len=this.lista.size();
        for(int j=0;j<n;j++){
            Lesson lesson=this.lista.get(j%len);
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
                            if(!term.getskipBreaks()) break;
                            else min=przerwa.endTerm().inMinute();
                        }
                        else if(min+term.getduration()==przerwa.endTerm().inMinute()){
                            if(!term.getskipBreaks()) break;
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
                        lista.get(j%len).setterm(term1);
                    }
                    break;
                case TIME_LATER:
                    int h1=0;
                    int min1=term.inMinute()+term.getduration();
                    for (int i = 0; i < len2; i++) {
                        BasicTerm przerwa = przerwy[i].getterm();
                        if (min1 == przerwa.inMinute()) {
                            if (!term.getskipBreaks()) break;
                            else min1 = przerwa.endTerm().inMinute();
                        } else if ((min1 + term.getduration()) == przerwa.endTerm().inMinute()) {
                            if (!term.getskipBreaks()) break;
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
                        lista.get(j%len).setterm(term2);
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
        Day day=Day.MON;
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
            day=Day.MON;
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
