package jsonparsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import player.Currency;
import player.Item;
import player.PlayerInfo;
import player.Progress;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Parser {

    private static final ObjectMapper objectMapper = getObjectMapper();
    private static final String file = "D:\\Java\\DB_CRUD\\src\\main\\java\\json\\players.json";
    private static final List<PlayerInfo> node = parse();

    public static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    private static List<PlayerInfo> parse(){
        try {
            return objectMapper.readValue(new FileInputStream(Parser.file), new TypeReference<List<PlayerInfo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<Integer, String> playerMap(){
        Map<Integer, String> playerMap = new HashMap<>();
        for (PlayerInfo player: Objects.requireNonNull(node)) {
            playerMap.put(player.playerId, player.nickname);
        }
        return playerMap;
    }

    public static Map<Integer, List<Progress>> progressMap(){
        Map<Integer, List<Progress>> progressMap = new HashMap<>();
        for (PlayerInfo player: Objects.requireNonNull(node)) {
            progressMap.put(player.playerId, player.progresses);
        }
        return progressMap;
    }

    public static Map<Integer, List<Item>> itemMap(){
        Map<Integer, List<Item>> itemMap = new HashMap<>();
        for (PlayerInfo player: Objects.requireNonNull(node)) {
            itemMap.put(player.playerId, player.items);
        }
        return itemMap;
    }

    public static Map<Integer, List<Currency>> currencyMap(){
        Map<Integer, List<Currency>> currencyMap = new HashMap<>();
        for (PlayerInfo player: Objects.requireNonNull(node)) {
            currencyMap.put(player.playerId, player.currencies);
        }
        return currencyMap;
    }

}


