package lab6;

public enum Day {

    MON,TUE,WED,THU,FRI,SAT,SUN;

    @Override
    public String toString() {
        switch(this){
            case MON:
                return "Poniedziałek";
            case TUE:
                return "Wtorek";
            case WED:
                return "Środa";
            case THU:
                return "Czwartek";
            case FRI:
                return "Piątek";
            case SAT:
                return "Sobota";
            default:
                return "Niedziela";
        }
    }
    public Day nextDay(){
        Day arr[]=Day.values();
        int len=arr.length;
        int i=this.ordinal();
        if(i+1<len){
            return arr[i+1];
        }
        return arr[0];
    }

    public Day prevDay(){
        Day arr[]=Day.values();
        int len=arr.length;
        int i=this.ordinal();
        if(i>0){
            return arr[i-1];
        }
        return arr[len-1];
    }
}