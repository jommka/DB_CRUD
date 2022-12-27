package player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;
import java.util.Map;

public class PlayerInfo {

    public final int playerId;
    public final String nickname;
    public final List<Progress> progresses;
//    public final Map<Integer, Progress> progresses;
    public final List<Currency> currencies;
    public final List<Item> items;

    public PlayerInfo(){
        this.playerId = -1;
        this.nickname = null;
        this.progresses = null;
        this.currencies = null;
        this.items = null;
    }

    @JsonCreator
    public PlayerInfo(
            @JsonProperty ("playerId") int playerId,
            @JsonProperty("nickname") String nickname,
            @JsonProperty ("progresses") List<Progress> progresses,
//            @JsonProperty ("progresses") Map<Integer, Progress> progresses,
            @JsonProperty ("currencies") List<Currency> currencies,
            @JsonProperty ("items") List<Item> items) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.progresses = progresses;
        this.currencies = currencies;
        this.items = items;
    }


}
