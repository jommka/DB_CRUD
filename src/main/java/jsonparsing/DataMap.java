package jsonparsing;


import player.Currency;
import player.Item;
import player.Progress;

import java.util.List;
import java.util.Map;

public class DataMap {

    private Map<Integer, String> playerMap;
    private Map<Integer, List<Progress>> progressMap;
    private Map<Integer, List<Item>> itemMap;
    private Map<Integer, List<Currency>> currencyMap;

    public DataMap(){
        this.playerMap = Parser.playerMap();
        this.progressMap = Parser.progressMap();
        this.itemMap = Parser.itemMap();
        this.currencyMap = Parser.currencyMap();
    }

    public Map<Integer, String> getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(Map<Integer, String> playerMap) {
        this.playerMap = playerMap;
    }

    public Map<Integer, List<Progress>> getProgressMap() {
        return progressMap;
    }

    public void setProgressMap(Map<Integer, List<Progress>> progressMap) {
        this.progressMap = progressMap;
    }

    public Map<Integer, List<Item>> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, List<Item>> itemMap) {
        this.itemMap = itemMap;
    }

    public Map<Integer, List<Currency>> getCurrencyMap() {
        return currencyMap;
    }

    public void setCurrencyMap(Map<Integer, List<Currency>> currencyMap) {
        this.currencyMap = currencyMap;
    }
}
