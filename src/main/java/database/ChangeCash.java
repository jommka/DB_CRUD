package database;

import jsonparsing.DataMap;
import player.Currency;
import player.Item;
import player.Progress;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static crud.NotFoundException.notFoundException;

public enum ChangeCash {

    PLAYER_STRATEGY("player") {
        @Override
        public void insert(String[] condition) {
            map.getPlayerMap().put(Integer.valueOf(condition[0]), condition[1]);
        }

        @Override
        public void remove(String[] condition) {
            map.getPlayerMap().remove(Integer.valueOf(condition[1]));
        }

        @Override
        public void update(String[] condition) {
            map.getPlayerMap().replace(Integer.valueOf(condition[condition.length - 1]), condition[1]);
//            System.out.println(map.getPlayerMap().get(10001));
        }

        @Override
        public void read() throws SQLException{
            System.out.println("Player: ");
            System.out.println(Database.getPlayer());
            System.out.println("-------------------------------------------------");
        }
    },
    ITEM_STRATEGY("item") {
        @Override
        public void insert(String[] condition) {
//            if (item.size() == 1) {
//                map.getItemMap().put(Integer.parseInt(condition[1]), item);
//            } else {
//                map.getItemMap().replace(Integer.parseInt(condition[1]), item);
//            }
            map.getItemMap().put(Integer.parseInt(condition[1]), getListItem(condition));
//            System.out.println(map.getItemMap().size());
        }

        @Override
        public void remove(String[] condition) {
            map.getItemMap().remove(Integer.valueOf(condition[1]));
        }

        @Override
        public void update(String[] condition) {
//            map.getItemMap().replace(condition[condition.length - 1], item);
        }

        @Override
        public void read() throws SQLException {
            System.out.println("Item: ");
            for (Integer key: Database.getItem().keySet()){
                System.out.println("key: " + key);
                for (String p: Database.getItem().get(key)){
                    System.out.print(p + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------------------------------");
        }
    },
    PROGRESS_STRATEGY("progress") {
        @Override
        public void insert(String[] condition) {
            map.getProgressMap().put(Integer.parseInt(condition[1]), getListProgress(condition));
        }

        @Override
        public void remove(String[] condition) {
            map.getProgressMap().remove(Integer.valueOf(condition[1]));
        }

        @Override
        public void update(String[] condition) {
//            map.getItemMap().replace(condition[condition.length - 1], progress);
        }

        @Override
        public void read() throws SQLException {
            System.out.println("Progress: ");
            for (Integer key: Database.getProgress().keySet()){
                System.out.println("key: " + key);
                for (String p: Database.getProgress().get(key)){
                    System.out.print(p + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------------------------------");
        }
    },
    CURRENCY_STRATEGY("currency") {
        @Override
        public void insert(String[] condition) {
//            if (currency.size() == 1) {
//                map.getCurrencyMap().put(Integer.parseInt(condition[1]), currency);
//            } else {
//                map.getCurrencyMap().replace(Integer.parseInt(condition[1]), currency);
//            }
            map.getCurrencyMap().put(Integer.parseInt(condition[1]), getListCurrency(condition));
        }

        @Override
        public void remove(String[] condition) {
            map.getCurrencyMap().remove(Integer.valueOf(condition[1]));
        }

        @Override
        public void update(String[] condition) {
//            map.getItemMap().replace(condition[condition.length - 1], currency);
        }

        @Override
        public void read() throws SQLException {
            System.out.println("Currency: ");
            for (Integer key: Database.getCurrency().keySet()){
                System.out.println("key: " + key);
                for (String p: Database.getCurrency().get(key)){
                    System.out.print(p + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------------------------------");
        }
    };

    private final String tableName;
    public static final DataMap map = new DataMap();

    ChangeCash(String tableName) {
        this.tableName = tableName;
    }

    private static List<Item> getListItem(String[] condition){
        List<Item> item = map.getItemMap().getOrDefault(Integer.parseInt(condition[1]), new ArrayList<>());
        item.add(new Item(Integer.parseInt(condition[1]), Integer.parseInt(condition[0]),
                Integer.parseInt(condition[2]), Integer.parseInt(condition[3]), Integer.parseInt(condition[4])));
        return item;
    }

    private static List<Currency> getListCurrency(String[] condition){
        List<Currency> currency = map.getCurrencyMap().getOrDefault(Integer.parseInt(condition[1]), new ArrayList<>());
        currency.add(new Currency(Integer.parseInt(condition[1]), Integer.parseInt(condition[0]),
                Integer.parseInt(condition[2]), condition[3], Integer.parseInt(condition[4])));
        return currency;
    }

    private static List<Progress> getListProgress(String[] condition){
        List<Progress> progress = map.getProgressMap().getOrDefault(Integer.parseInt(condition[1]), new ArrayList<>());
        progress.add(new Progress(Integer.parseInt(condition[1]), Integer.parseInt(condition[0]),
                Integer.parseInt(condition[2]), Integer.parseInt(condition[3]), Integer.parseInt(condition[4])));
        return progress;
    }

    public abstract void insert(String[] condition);
    public abstract void remove(String[] condition);
    public abstract void update(String[] condition);
    public abstract void read() throws SQLException;


    public static ChangeCash value(String tableName) {
        return Arrays.stream(values())
                .filter(it -> it.tableName.equals(tableName))
                .findFirst()
                .orElseThrow(notFoundException("Oops, incorrect expression!"));
    }
}
