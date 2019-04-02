import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.*;

public class Main {

    private static final int l=2;

    public static void main(String args[]){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi();
        Fermata[] fermate=new Fermata[l];
        readFile(fermate);
        Bot bot=new Bot(fermate);

        try{
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    private static void readFile(Fermata[] fermate) {
        String path = "timeTable.txt";
        String path2="freq_notes.txt";
        String s; //file orari
        String s2=new String();
        String stopName;
        int[][] notes=new int[20][55];
        int[][] days=new int[16][55];


        boolean flag=true;
        int line_counter=1;
        int stop_counter=0;
        int j;
        String[] parts=new String[55];

        try {
            FileReader inputFil = new FileReader(path);
            BufferedReader in = new BufferedReader(inputFil);
            FileReader inputFil2 = new FileReader(path2);
            BufferedReader in2 = new BufferedReader(inputFil2);
            //legge file frequenza
            for(j=0;j<16;j++){
                s2=in2.readLine();
                carica(s2,parts,days,j);
            }
            for(j=0;j<20;j++){
                s2=in2.readLine();
                carica(s2,parts,notes,j);
            }
            //legge file orari
            s = in.readLine();
            for(int c=0;c<l;c++) {
                int[][] hrs=new int[4][55];
                stopName = s;
                for(j=0;j<4;j++){
                    s=in.readLine();
                    carica(s,parts,hrs,j);
                }
                Fermata tempstop=new Fermata(stopName,hrs,days,notes);
                fermate[c]=tempstop;
                s = in.readLine();
            }
            in.close();
            in2.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void carica(String s, String[] parts,int[][] hrs,int matr_row) {
        parts = s.split(" ");
        for(int i=0;i<parts.length;i++){
            hrs[matr_row][i]= Integer.parseInt(parts[i]);
        }
    }
}
