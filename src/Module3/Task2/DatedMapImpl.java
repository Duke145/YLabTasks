package Module3.Task2;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class DatedMapImpl implements DatedMap {

    private HashMap<String, String> map = new HashMap<>();
    private HashMap<String, Date> dateMap = new HashMap<>();

    @Override
    public void put(String key, String value) {
        this.map.put(key, value);
        this.dateMap.put(key, new Date(System.currentTimeMillis()));
    }

    @Override
    public String get(String key) {
        return this.map.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return this.map.containsKey(key);
    }

    @Override
    public void remove(String key) {
        this.map.remove(key);
        this.dateMap.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        if (this.map.containsKey(key)) {
            return dateMap.get(key);
        }
        return null;
    }
}
