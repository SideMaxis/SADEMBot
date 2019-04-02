import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.time.LocalDateTime;

public class Bot extends TelegramLongPollingBot {

    private Fermata[] stops=new Fermata[2];
    private LocalDateTime curTime;

    public Bot(Fermata[] fermate){
        this.stops=fermate;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getFirstName() + ": " + update.getMessage().getText());
        SendMessage sendMessage=new SendMessage().setChatId(update.getMessage().getChatId());
        switch(update.getMessage().getText().toLowerCase()){
            case "/start":
                sendMessage.setText("GET TO THE CHOPPER!!!");
                break;
            case "cottolengo":
                sendMessage.setText("COTTOLENGO\n\nDirezione TORINO\nProssimi passaggi: " + stops[0].search(true) + "\n\nDirezione PIOBESI\nProssimi passaggi: "+stops[0].search(false));
                break;
            case "piazza bengasi":
                sendMessage.setText("Fermata richiesta: " + stops[1].getName()+" "+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute());
                break;
            default:
                sendMessage.setText("La ricerca non ha prodotto risultati");
                break;
        }
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "845718555:AAEW_IEL5qySgnbChNdb4HAeZ4kXnrVgl3U";
    }
}
