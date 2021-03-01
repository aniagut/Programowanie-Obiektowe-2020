package lab5;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTimetable implements ITimetable{
    public List<Lesson> lista;

    public void setLista(List<Lesson> lista) {

        this.lista = lista;
    }

    public List<Lesson> getLista() {
        return lista;
    }

    public AbstractTimetable(){
        List<Lesson> lista=new ArrayList<Lesson>();
        this.lista=lista;
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
        int len=this.lista.size();

        for(int i=0;i<len;i++){

            Term term1=this.lista.get(i).getterm();
            if(term1.getday()==term.getday()) {
                if ((term.inMinute() == term1.inMinute())) return true;
            }
        }

        return false;
    }

}

