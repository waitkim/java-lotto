package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoMaker;
import lotto.domain.Reward;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    LottoMaker lottoMaker = new LottoMaker();
    List<Lotto> lottos;
    WinningLotto winningLotto;
    EnumMap<Reward, Integer> winningStatistic = new EnumMap<>(Reward.class);
    int purchaseAmount; // 포장 ?

    public void run() {
        try {
            // 입력 , 구매
            purchaseAmount = inputView.askPurchaseAmount();
            lottos = lottoMaker.publishLottosByPurchaseAmount(purchaseAmount);
            outputView.printLottos(lottos);

            // 당첨번호 입력, 보너스 번호 입력
            List<Integer> winningNumber = extractNumbers(inputView.askWinningNumbers());
            String bonusNumber = inputView.askBonusNumber();
            winningLotto = new WinningLotto(winningNumber, bonusNumber);

            // 계산, 출력
            calculateHit();
            outputView.printStatistic(winningStatistic, calculateYield(winningStatistic, purchaseAmount));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private List<Integer> extractNumbers(String winningNumbers) {
        return Stream.of(winningNumbers.split("\\s*,\\s*"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    public void calculateHit() {
        initializeWinningStatistic();
        // stream을 이용할 수 있을까?
        for (int i = 0; i < lottos.size(); i++) {
            int hitCount = winningLotto.calculateHit(lottos.get(i));
            int bonusHit = winningLotto.calculateBonus(lottos.get(i));
            checkLottoHit(winningStatistic, hitCount, bonusHit);
        }
    }

    private void checkLottoHit(Map<Reward, Integer> map, int hit, int bonusHit) {
        if (hit == 6) {
            map.put(Reward.FIRST, 1);
        }
        if (hit == 5 && bonusHit == 1) {
            map.put(Reward.SECOND, 1);
        }
        if (hit == 5) {
            map.put(Reward.THIRD, 1);
        }
        if (hit == 4) {
            map.put(Reward.FOURTH, 1);
        }
        if (hit == 3) {
            map.put(Reward.FIFTH, 1);
        }
    }

    private void initializeWinningStatistic() {
        winningStatistic.put(Reward.SECOND, 0);
        winningStatistic.put(Reward.THIRD, 0);
        winningStatistic.put(Reward.FOURTH, 0);
        winningStatistic.put(Reward.FIFTH, 0);
        winningStatistic.put(Reward.FIRST, 0);
    }

    public double calculateYield(Map<Reward, Integer> winningStatistic, int purchaseAmount) {
        int sum = winningStatistic.entrySet()
                .stream()
                .mapToInt(reward -> reward.getKey().getReward() * reward.getValue())
                .sum();

        //소수 첫째자리 반올림, 왜 박스가 생기지?
        return Math.round(sum * 1000 / purchaseAmount) / 10.0;
    }
}
