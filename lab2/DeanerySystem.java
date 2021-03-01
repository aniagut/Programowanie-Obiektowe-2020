package lab2;

public class DeanerySystem {
    public static void main(String[] args){
        Term term1 = new Term(9,45);
        System.out.println(term1);                    //Ma się wypisać: "9:45 [90]"
        Term term2 = new Term(10,15);
        System.out.println(term2);                    //Ma się wypisać: "10:15 [90]"
        System.out.println(term1.earlierThan(term2)); //Ma się wypisać: "true"
        System.out.println(term1.equals(term2));      //Ma się wypisać: "false"
        System.out.println(term1.endTerm(term2));     //Ma się wypisać: "9:45 [30]"
        System.out.println(term1.endTerm());          //Ma się wypisać: "11:15 [90]"
        Day dzien1=Day.MON;
        System.out.println(dzien1);
        System.out.println(dzien1.prevDay());
        Day dzien2=Day.SUN;
        System.out.println(dzien2.nextDay());
    }
}
