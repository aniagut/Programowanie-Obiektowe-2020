package lab1;

public class World {
    public static void run(String tablica[]){
        int i= tablica.length;
        Direction tab1[]=new Direction[i];
        for(int j=0;j<i;j++){
            tab1[j]=Enum.valueOf(Direction.class,tablica[j]);
        }
        run(tab1);
    }
    public static void run(Direction tablica[]){
        for(Direction x:tablica){
            switch(x){
                case f:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case b:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case l:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
                case r:
                    System.out.println("Zwierzak idzie w prawo");
                    break;
                default:
                    System.out.println("Zwierzak nie porusza się");
            }
        }
    }
    public static void main(String[] args){
        String tablica[]=new String[4];
        tablica[0]="f";
        tablica[1]="f";
        tablica[2]="r";
        tablica[3]="l";
        System.out.println("System startuje");
        run(tablica);
        System.out.println("System kończy pracę");
    }
}
