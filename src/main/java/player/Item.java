package player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Item {

    public final int id;
    public final int playerId;
    public final int resourceId;
    public final int count;
    public final int level;

    public Item(){
        this.id = -1;
        this.playerId = -1;
        this.resourceId = -1;
        this.count = -1;
        this.level = -1;
    }

    @JsonCreator
    public Item(
            @JsonProperty("id") int id,
            @JsonProperty("playerId") int playerId,
            @JsonProperty("resourceId") int resourceId,
            @JsonProperty("count") int count,
            @JsonProperty("level") int level){
        this.id = id;
        this.playerId = playerId;
        this.resourceId = resourceId;
        this.count = count;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", resourceId=" + resourceId +
                ", count=" + count +
                ", level=" + level +
                '}';
    }

}
