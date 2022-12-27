package player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Progress {

    public final int id;
    public final int playerId;
    public final int resourceId;
    public final int score;
    public final int maxScore;

    public Progress(){
        this.id = -1;
        this.playerId = -1;
        this.resourceId = -1;
        this.score = -1;
        this.maxScore = -1;
    }

    @JsonCreator
    public Progress(
            @JsonProperty("id") int id,
            @JsonProperty("playerId") int playerId,
            @JsonProperty("resourceId") int resourceId,
            @JsonProperty("score") int score,
            @JsonProperty("maxScore") int maxScore) {
        this.id = id;
        this.playerId = playerId;
        this.resourceId = resourceId;
        this.score = score;
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", resourceId=" + resourceId +
                ", score=" + score +
                ", maxScore=" + maxScore +
                '}';
    }

}
