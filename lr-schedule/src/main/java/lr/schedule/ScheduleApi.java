package lr.schedule;

import com.nhl.link.rest.DataResponse;
import com.nhl.link.rest.EntityUpdate;
import com.nhl.link.rest.LinkRest;
import com.nhl.link.rest.constraints.ConstraintsBuilder;
import com.nhl.link.rest.multisource.LinkRestMultiSource;
import lr.schedule.cayenne.Game;
import lr.schedule.cayenne.Score;
import lr.schedule.cayenne.Team;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleApi {

    @Context
    private Configuration configuration;

    @GET
    public DataResponse<Game> getGames(@Context UriInfo uri) {
        return LinkRest.select(Game.class, configuration)
                .uri(uri)
                .select();
    }

    @GET
    @Path("constrained")
    public DataResponse<Game> getGamesConstrained(@Context UriInfo uri) {

        ConstraintsBuilder<Team> teamConstraints = ConstraintsBuilder
                .idAndAttributes(Team.class);

        ConstraintsBuilder<Game> gameConstraints = ConstraintsBuilder
                .idAndAttributes(Game.class)
                .path(Game.HOME_TEAM, teamConstraints)
                .path(Game.VISITING_TEAM, teamConstraints);

        return LinkRest.select(Game.class, configuration)
                .uri(uri)
                .constraints(gameConstraints)
                .select();
    }

    @PUT
    public DataResponse<Game> createGame(Collection<EntityUpdate<Game>> updates, @Context UriInfo uri) {
        return LinkRest.idempotentCreateOrUpdate(Game.class, configuration).syncAndSelect(updates);
    }

    @GET
    @Path("scoreboard")
    public DataResponse<Game> getGamesWithScore(@Context UriInfo uri) {
        return LinkRestMultiSource
                .select(LinkRest.select(Game.class, configuration).uri(uri), configuration)
                .parallel(Score::fetchScores, Score::mergeScores)
                .select(10, TimeUnit.SECONDS);
    }
}
