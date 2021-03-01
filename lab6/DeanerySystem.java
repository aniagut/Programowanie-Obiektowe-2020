package lab6;


public class DeanerySystem {
    public static void main(String[] args) {

        Break przerwa1=new Break(new BasicTerm(9,30));

        Break przerwa2=new Break(new BasicTerm(11,5));
        przerwa2.getterm().setduration(10);
        Break przerwa3=new Break(new BasicTerm(12,45));

        Break przerwa4=new Break(new BasicTerm(14,20));
        przerwa4.getterm().setduration(20);
        Break przerwa5=new Break(new BasicTerm(16,10));

        Break przerwa6=new Break(new BasicTerm(17,45));

        Break[] breaks={przerwa1,przerwa2,przerwa3,przerwa4,przerwa5,przerwa6};
        Timetable2 timetable=new Timetable2(breaks);
        Lesson l1=new Lesson(timetable,new Term(8,0,Day.MON),"Fizyka-W","Kąkol",2);
        Lesson l2=new Lesson(timetable,new Term(17,50,Day.MON),"PO-W","Smywiński-Pohl",2);
        Lesson l3=new Lesson(timetable,new Term(8,0,Day.TUE),"RPIS-W","Smołka",2);
        Lesson l4=new Lesson(timetable,new Term(9,35,Day.TUE),"RRIR-W","Schaefer",2);
        Lesson l5=new Lesson(timetable,new Term(11,15,Day.TUE),"RPIS","Smołka",2);
        Lesson l6=new Lesson(timetable,new Term(16,15,Day.TUE),"Geometryczne-W","Głut",2);
        Lesson l7=new Lesson(timetable,new Term(8,0,Day.WED),"Funkcyjne-W","Dębski",2);
        Lesson l8=new Lesson(timetable,new Term(9,35,Day.WED),"Bazy Danych-W","Zygmunt",2);
        Lesson l9=new Lesson(timetable,new Term(12,50,Day.WED),"PO","Polak",2);
        Lesson l11=new Lesson(timetable,new Term(17,50,Day.WED),"Grafowe-W","Meszka",2);
        Lesson l12=new Lesson(timetable,new Term(9,35,Day.THU),"RRIR","Woźniak",2);
        Lesson l13=new Lesson(timetable,new Term(11,15,Day.THU),"Funkcyjne","Łoś",2);
        Lesson l14=new Lesson(timetable,new Term(14,40,Day.THU),"Bazy danych","Marcjan",2);
        Lesson l15=new Lesson(timetable,new Term(16,15,Day.THU),"Geometryczne","Głut",2);
        Lesson l16=new Lesson(timetable,new Term(17,50,Day.THU),"Grafowe","Łoś",2);
        Lesson l17=new Lesson(timetable,new Term(12,45,Day.FRI),"Fizyka-L","Armatys",2);
        Lesson l18=new Lesson(timetable, new Term(9,35,Day.THU),"Nowa lekcja","Nowy nauczyciel",2);
        try {
            timetable.put(l1);
            timetable.put(l2);
            timetable.put(l3);
            timetable.put(l4);
            timetable.put(l5);
            timetable.put(l6);
            timetable.put(l7);
            timetable.put(l8);
            timetable.put(l9);
            timetable.put(l11);
            timetable.put(l12);
            timetable.put(l13);
            timetable.put(l14);
            timetable.put(l15);
            timetable.put(l16);
            timetable.put(l17);
            timetable.put(l18);
        }catch(IllegalArgumentException a){
            System.out.println("Term is already busy");
        }
        finally{
            System.out.println(timetable);

            System.out.println(l2.earlierTime());

            System.out.println(l11.laterTime());

            System.out.println(l13.laterTime());
            try{
                Action[] actions = new ActionsParser().parse(args);

                timetable.perform(actions);

            }
            catch(IllegalArgumentException b){
                System.out.println("Źle sformułowany argument. Podaj jeden z argumentów: t+,t-,d+,d-");
            }
            catch(UnsupportedOperationException c){
                System.out.println("Term is already busy.");
            }
            finally{
                System.out.println(timetable);

                Term term1 = new Term(9,35,Day.MON);
                Term term2=new Term(9,35,Day.MON);
                Term term3=new Term(10,24,Day.MON);
                System.out.println(term1.hashCode()==term2.hashCode());
                System.out.println(term1.hashCode()==term3.hashCode());
            }
        }


    }
}
