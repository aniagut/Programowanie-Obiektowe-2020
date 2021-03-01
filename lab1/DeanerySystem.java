package lab1;
import java.util.Scanner;


public class DeanerySystem {
    public static void print(int liczba){
        System.out.println("Argument metody jest liczbą całkowitą");
    }
    public static void print(String napis){
        System.out.println("Argument metody jest napisem");
    }
    public static void print(String tablica[]){
        dnitygodnia[] tab1=new dnitygodnia[7];
        int i=0;
        for(String x:tablica){
            tab1[i]=Enum.valueOf(dnitygodnia.class, tablica[i]);
            i++;
        }
        print(tab1);
    }
    public static void print(dnitygodnia tablica[]){
        for(dnitygodnia x:tablica){
            switch(x){
                case PN:
                    System.out.println("Poniedziałek");
                    break;
                case WT:
                    System.out.println("Wtorek");
                    break;
                case SR:
                    System.out.println("Środa");
                    break;
                case CZ:
                    System.out.println("Czwartek");
                    break;
                case PT:
                    System.out.println("Piątek");
                    break;
                case SB:
                    System.out.println("Sobota");
                    break;
                default:
                    System.out.println("Niedziela");
            }
        }
    }
    public static void main(String[] args){
        print(7);
        print("Ania");
        String[] dow = new String[7];
        for(int i=0;i<7;i++){
            Scanner input = new Scanner(System.in);
            dow[i]=input.nextLine();
        }
        print(dow);
    }
}