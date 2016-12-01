package lr.events.model;

import com.nhl.link.rest.annotation.LrAttribute;
import com.nhl.link.rest.annotation.LrRelationship;

public class Event {

    private int gameId;
    private long timestamp;
    private String type;
    private boolean byHomeTeam;
    private EventPlayer player;


    @LrAttribute
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @LrAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @LrAttribute
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @LrRelationship
    public EventPlayer getPlayer() {
        return player;
    }

    public void setPlayer(EventPlayer player) {
        this.player = player;
    }

    @LrAttribute
    public boolean isByHomeTeam() {
        return byHomeTeam;
    }

    public void setByHomeTeam(boolean byHomeTeam) {
        this.byHomeTeam = byHomeTeam;
    }
}
