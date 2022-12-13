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
            publishLottos();
            makeWinningLotto();
            makeResult();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void publishLottos() {
        purchaseAmount = inputView.askPurchaseAmount();
        lottos = lottoMaker.publishLottosByPurchaseAmount(purchaseAmount);
        outputView.printLottos(lottos);
    }

    private void makeWinningLotto() {
        List<Integer> winningNumber = inputView.askWinningNumbers();
        int bonusNumber = inputView.askBonusNumber();
        winningLotto = new WinningLotto(winningNumber, bonusNumber);
    }

    private void makeResult() {
        initializeWinningStatistic()
        for (Lotto lotto : lottos) {
            winningStatistic.put(winningLotto.checkHit(lotto),1);
        }
        winningStatistic.remove(Reward.BLANK);
        outputView.printStatistic(winningStatistic, calculateYield(winningStatistic, purchaseAmount));
    }

    private void initializeWinningStatistic() {
        winningStatistic.put(Reward.SECOND, 0);
        winningStatistic.put(Reward.THIRD, 0);
        winningStatistic.put(Reward.FOURTH, 0);
        winningStatistic.put(Reward.FIFTH, 0);
        winningStatistic.put(Reward.FIRST, 0);
    }

    private double calculateYield(Map<Reward, Integer> winningStatistic, int purchaseAmount) {
        int sum = winningStatistic.entrySet()
                .stream()
                .mapToInt(reward -> reward.getKey().getReward() * reward.getValue())
                .sum();

        return Math.round(sum * 1000 / purchaseAmount) / 10.0;
    }
}
