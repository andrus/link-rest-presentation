package lr.events.dao;

class InProgressGame {

    private int id;
    private int homeTeamId;
    private int visitingTeamId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getVisitingTeamId() {
        return visitingTeamId;
    }

    public void setVisitingTeamId(int visitingTeamId) {
        this.visitingTeamId = visitingTeamId;
    }
}
