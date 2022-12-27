package player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Currency {

    public final int id;
    public final int playerId;
    public final int resourceId;
    public final String name;
    public final int count;

    public Currency(){
        this.id = -1;
        this.playerId = -1;
        this.resourceId = -1;
        this.name = null;
        this.count = -1;
    }

    @JsonCreator
    public Currency(
            @JsonProperty("id") int id,
            @JsonProperty ("playerId") int playerId,
            @JsonProperty ("resourceId") int resourceId,
            @JsonProperty ("name") String name,
            @JsonProperty ("count") int count){
        this.id = id;
        this.playerId = playerId;
        this.resourceId = resourceId;
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", resourceId=" + resourceId +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }


}
