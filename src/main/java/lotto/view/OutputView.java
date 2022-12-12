package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Reward;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printLottos(List<Lotto> lottos) {
        //size를 써야할까
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.stream()
                .forEach(Lotto::printNumbers);
    }

    public void printStatistic(Map<Reward, Integer> winningStatistic, double yield) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printWinningStatistic(winningStatistic);
        System.out.printf("총 수익률은 %.1f%%입니다.\n", yield);
    }

    public void printWinningStatistic(Map<Reward, Integer> winningStatistic) {
        //스트림 이용할 것
        for (Map.Entry<Reward, Integer> entry : winningStatistic.entrySet()) {
            System.out.println(entry.getKey().getSentence() + " - " + entry.getValue() + "개");
        }
    }
}
