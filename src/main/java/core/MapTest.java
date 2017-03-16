package core;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MapTest {
    private Map<String, List<String>> map;

    public MapTest(Map<String, List<String>> map) {
        this.map = map;
    }
}
