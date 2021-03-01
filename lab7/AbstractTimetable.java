package lab7;

import java.util.LinkedHashMap;

public abstract class AbstractTimetable implements ITimetable {
    public LinkedHashMap<Integer,Lesson> map;

    public void setMap(LinkedHashMap<Integer,Lesson> map) {

        this.map = map;
    }

    public LinkedHashMap<Integer,Lesson> getMap() {
        return map;
    }

    public AbstractTimetable(){
        LinkedHashMap<Integer,Lesson> map=new LinkedHashMap<>();
        this.map=map;
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time){

        if(busy(term)) return false;

        else{

            if(term.gethour()<8) return false;
            int i=term.getday().ordinal();
            if (full_time){

                if(i<4){
                    if(term.endTerm().gethour()<20) return true;
                    else return term.endTerm().gethour() == 20 && term.endTerm().getminute() == 0;
                }
                else if (i==4){
                    if(term.endTerm().gethour()<17) return true;
                    else return term.endTerm().gethour() == 17 && term.endTerm().getminute() == 0;
                }
                else return false;
            }
            else{
                if(i==4){
                    if(term.gethour()<17) return false;
                    else if(term.endTerm().gethour()<20) return true;
                    else return term.endTerm().gethour() == 20 && term.endTerm().getminute() == 0;
                }
                else if(i>4){
                    if(term.endTerm().gethour()<20) return true;
                    else return term.endTerm().gethour() == 20 && term.endTerm().getminute() == 0;
                }
                else return false;

            }
        }
    }
    @Override
    public boolean busy(Term term){
        int key=term.hashCode();
        return map.containsKey(key);
    }
}
