package lr.schedule.cayenne;

import com.nhl.link.rest.annotation.LrAttribute;
import lr.schedule.cayenne.auto._Game;

public class Game extends _Game {

    private static final long serialVersionUID = 1L;

    private Score score;

    @LrAttribute
    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
