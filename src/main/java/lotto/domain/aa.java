package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class aa {

    public static void main(String[] args) {
        Map<Reward, Integer> map = new EnumMap<>(Reward.class);
        System.out.println(map);

        map.put(Reward.FIRST, 1);
        System.out.println(map);
    }

}
