package lab5;


public class ActionsParser {
    public static Action[] parse(String[] tablica){
        int n=tablica.length;
        Action tab1[]=new Action[n];
        for(int i=0;i<n;i++){
            switch(tablica[i]){
                case "d-":
                    tab1[i]=Action.DAY_EARLIER;
                    break;
                case "d+":
                    tab1[i]=Action.DAY_LATER;
                    break;
                case "t-":
                    tab1[i]=Action.TIME_EARLIER;
                    break;
                case "t+":
                    tab1[i]=Action.TIME_LATER;
                    break;
                default:
                    tab1[i]=null;
            }
        }
        return tab1;
    }
}
