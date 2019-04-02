import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class Fermata {

    private String fermata=new String();
    private int[][] time;
    private int[][] days;
    private int[][] notes;
    private static final int matrcol=55;

    public Fermata(String fermata,int[][] time,int[][] days,int[][] notes){
        this.fermata=fermata;
        this.time=time;
        this.days=days;
        this.notes=notes;
    }
    public void print(){
        int i,j;
        System.out.println("Nome fermata: "+fermata);
        System.out.println("\n\n");
        for(j=0;j<4;j++){
            for(i=0;i<matrcol;i++){
                System.out.print(time[j][i]);
            }
            System.out.println("\n");
        }
        System.out.println("\n\n");
        for(j=0;j<16;j++){
            for(i=0;i<matrcol;i++){
                System.out.print(days[j][i]);
            }
            System.out.println("\n");
        }
        System.out.println("\n\n");
        for(j=0;j<20;j++){
            for(i=0;i<matrcol;i++){
                System.out.print(notes[j][i]);
            }
            System.out.println("\n");
        }
    }

    public String getName(){
        return fermata;
    }

    private String getTime(int matr_index){
        String res=new String();
        int curHrs=LocalDateTime.now().getHour();
        int curMins=LocalDateTime.now().getMinute();
        int rescount=0;
        res="";
        for(int i=0;i<matrcol && rescount<2;i++){
            if(curHrs<=time[matr_index][i]){
                if(curHrs==time[matr_index][i]){
                    if(curMins<=time[matr_index+1][i]){
                        if(checkParams(i)){
                            res=res+time[matr_index][i]+":"+time[matr_index+1][i]+" ";
                            rescount++;
                        }
                    }
                }else{
                    if(checkParams(i)){
                        res=res+time[matr_index][i]+":"+time[matr_index+1][i]+" ";
                        rescount++;
                    }
                }
            }
        }
        return res;
    }

    private int getCurDayOfWeek(int year,int month,int day) {
        LocalDate a = LocalDate.of(year,month,day);
        return a.get(ChronoField.DAY_OF_WEEK);
    }

    private boolean checkParams(int i) {
        //controllo parametri fermata
        boolean checked=false;
        int curYear=LocalDateTime.now().getYear();
        int curMonth=LocalDateTime.now().getMonthValue();
        int curDay=LocalDateTime.now().getDayOfMonth();
        int dayofweek=getCurDayOfWeek(curYear,curMonth,curDay);
        if(days[dayofweek-1][i]==1)
            checked=true;
        return checked;
    }

    public String search(boolean flag){
        String result=new String();

        if (flag){
            result=getTime(0);
        }else{
            result=getTime(2);
        }
        return result;
    }
}