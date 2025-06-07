package Quiz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Raiting  implements Serializable{
    List<Game> games;
}
class Game  implements Serializable {
    String gamer;
    Integer raiting;
    Date gameDate;

    @Override
    public String toString() {
        return gamer + " — " + raiting + " баллов (" + gameDate + ")";
    }
}
