package lr.schedule.cayenne;

import com.fasterxml.jackson.databind.JsonNode;
import com.nhl.link.rest.annotation.LrAttribute;
import com.nhl.link.rest.client.LinkRestClient;
import com.nhl.link.rest.client.protocol.Expression;
import org.apache.cayenne.Cayenne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score {

    private int homeScore;
    private int visitingScore;

    @LrAttribute
    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    @LrAttribute
    public int getVisitingScore() {
        return visitingScore;
    }

    public void setVisitingScore(int visitingScore) {
        this.visitingScore = visitingScore;
    }


    private static Logger LOGGER = LoggerFactory.getLogger(Score.class);

    public static Map<Integer, Score> fetchScores() {

        LOGGER.info("will fetch scores");
        WebTarget target = ClientBuilder.newClient().target("http://127.0.0.1:8081/");

        List<JsonNode> goals = LinkRestClient.client(target)
                .cayenneExp(Expression.query("type = 'goal'"))
                .get(JsonNode.class)
                .getData();

        // merge events

        Map<Integer, Score> scoresByGame = new HashMap<>();

        goals.forEach(goal -> {

            int id = goal.get("gameId").asInt();

            Score newScore = new Score();
            Score oldScore = scoresByGame.putIfAbsent(id, newScore);
            Score score = oldScore != null ? oldScore : newScore;

            if (goal.get("byHomeTeam").asBoolean()) {
                score.homeScore++;
            } else {
                score.visitingScore++;
            }
        });

        return scoresByGame;
    }

    public static void mergeScores(List<Game> games, Map<Integer, Score> scores) {

        LOGGER.info("will merge scores");
        games.forEach(g -> g.setScore(scores.get(Cayenne.intPKForObject(g))));
    }
}
