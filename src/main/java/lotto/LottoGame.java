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

    public void run() {
        try {
            final int purchaseAmount = getPurchaseAmount();
            makeResult(publishLottos(purchaseAmount), makeWinningLotto(), purchaseAmount);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private List<Lotto> publishLottos(int purchaseAmount) {
        final LottoMaker lottoMaker = new LottoMaker();
        final List<Lotto> lottos = lottoMaker.publishLottosByPurchaseAmount(purchaseAmount);
        outputView.printLottos(lottos);
        return lottos;
    }

    private int getPurchaseAmount() {
        return inputView.inputPurchaseAmount();
    }

    private WinningLotto makeWinningLotto() {
        final List<Integer> winningNumber = inputView.inputWinningNumbers();
        final int bonusNumber = inputView.inputBonusNumber();
        return new WinningLotto(winningNumber, bonusNumber);
    }

    private void makeResult(List<Lotto> lottos, WinningLotto winningLotto, int purchaseAmount) {
        final EnumMap<Reward, Integer> winningStatistic = new EnumMap<>(Reward.class);
        initializeWinningStatistic(winningStatistic);
        for (Lotto lotto : lottos) {
            winningStatistic.put(winningLotto.checkHit(lotto),1);
        }
        winningStatistic.remove(Reward.BLANK);
        outputView.printStatistic(winningStatistic, calculateYield(winningStatistic, purchaseAmount));
    }

    private void initializeWinningStatistic(Map<Reward,Integer> winningStatistic) {
        winningStatistic.put(Reward.SECOND, 0);
        winningStatistic.put(Reward.THIRD, 0);
        winningStatistic.put(Reward.FOURTH, 0);
        winningStatistic.put(Reward.FIFTH, 0);
        winningStatistic.put(Reward.FIRST, 0);
    }

    private double calculateYield(Map<Reward, Integer> winningStatistic, int purchaseAmount) {
        final int sum = winningStatistic.entrySet()
                .stream()
                .mapToInt(reward -> reward.getKey().getReward() * reward.getValue())
                .sum();

        return Math.round((double) sum * 1000 / purchaseAmount) / 10.0;
    }
}
