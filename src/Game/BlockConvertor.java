package Game;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BlockConvertor {
    private static BlockConvertor instance; // Singlton design pattern
    private static final Map<Character, Integer> mappingX = new HashMap<Character, Integer>();
    private static final Map<Character, Integer> mappingY = new HashMap<Character, Integer>();
    private BlockConvertor(){

    }


    public void initPos() {
        mappingX.put('a', 0);
        mappingX.put('b', 1);
        mappingX.put('c', 2);
        mappingX.put('d', 3);
        mappingX.put('e', 4);
        mappingX.put('f', 5);
        mappingX.put('g', 6);
        mappingX.put('h', 7);

        mappingY.put('8', 0);
        mappingY.put('7', 1);
        mappingY.put('6', 2);
        mappingY.put('5', 3);
        mappingY.put('4', 4);
        mappingY.put('3', 5);
        mappingY.put('2', 6);
        mappingY.put('1', 7);
    }
    public static BlockConvertor getInstance(){
        if (instance == null){
            instance = new BlockConvertor();
        }
        return instance;
    }


    public Position convertBlockToPosition(String blockName) {
        return new Position(mappingY.get(blockName.charAt(1)), mappingX.get(blockName.charAt(0)));
    }

    public String convertPositionToBlock(Position position) {
        int x = position.getX();
        int y = position.getY();
        return String.valueOf(getKey(mappingX, x) +
                String.valueOf(getKey(mappingY, y)));
    }
    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;

    }

}