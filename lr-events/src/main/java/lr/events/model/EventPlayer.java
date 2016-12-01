package lr.events.model;

import com.nhl.link.rest.annotation.LrAttribute;

public class EventPlayer {

    private String name;
    private int teamId;

    @LrAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @LrAttribute
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
