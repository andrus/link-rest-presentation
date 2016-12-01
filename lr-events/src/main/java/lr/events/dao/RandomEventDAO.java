package lr.events.dao;

import lr.events.model.Event;
import lr.events.model.EventPlayer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class RandomEventDAO implements EventDAO {

    private InProgressGame[] trackedGames;
    private List<Event> trackedGameEvents;

    public RandomEventDAO() {

        this.trackedGameEvents = new CopyOnWriteArrayList<>();

        // hardcoded games...
        trackedGames = new InProgressGame[5];

        trackedGames[0] = new InProgressGame();
        trackedGames[0].setHomeTeamId(10);
        trackedGames[0].setVisitingTeamId(8);
        trackedGames[0].setId(2015020001);

        trackedGames[1] = new InProgressGame();
        trackedGames[1].setHomeTeamId(16);
        trackedGames[1].setVisitingTeamId(3);
        trackedGames[1].setId(2015020002);

        trackedGames[2] = new InProgressGame();
        trackedGames[2].setHomeTeamId(20);
        trackedGames[2].setVisitingTeamId(23);
        trackedGames[2].setId(2015020003);

        trackedGames[3] = new InProgressGame();
        trackedGames[3].setHomeTeamId(26);
        trackedGames[3].setVisitingTeamId(28);
        trackedGames[3].setId(2015020004);

        trackedGames[4] = new InProgressGame();
        trackedGames[4].setHomeTeamId(6);
        trackedGames[4].setVisitingTeamId(52);
        trackedGames[4].setId(2015020005);
    }

    @Override
    public List<Event> getEvents() {

        Random random = new Random();

        int count = trackedGames.length;

        EventType[] allTypes = EventType.values();

        for (int i = 0; i < count; i++) {

            InProgressGame game = trackedGames[random.nextInt(trackedGames.length)];

            EventPlayer player = new EventPlayer();

            if (random.nextBoolean()) {
                player.setTeamId(game.getHomeTeamId());
            } else {
                player.setTeamId(game.getVisitingTeamId());
            }

            // todo: randomize name generation...
            player.setName("Joe Doe");

            Event event = new Event();
            event.setGameId(game.getId());
            event.setType(allTypes[random.nextInt(allTypes.length)].name());
            event.setTimestamp(System.currentTimeMillis() - random.nextInt(1000 * 60 * 3));
            event.setByHomeTeam(random.nextBoolean());

            event.setPlayer(player);

            trackedGameEvents.add(event);
        }


        return trackedGameEvents;
    }
}
