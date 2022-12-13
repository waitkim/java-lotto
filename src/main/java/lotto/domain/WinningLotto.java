package lotto.domain;

import java.util.List;
import java.util.Map;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningLotto(winningNumbers, bonusNumber);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public Reward checkHit(Lotto lotto) {
        long hitCount = 0;
        boolean bonusHit;
        hitCount = winningNumbers.hitCount(lotto);
        bonusHit = lotto.isHit(bonusNumber);
        return convertReward(hitCount, bonusHit);
    }

    private Reward convertReward(long hitCount, boolean bonusHit) {
        if (hitCount == 6) {
            return Reward.FIRST;
        }
        if (hitCount == 5 && bonusHit) {
            return Reward.SECOND;
        }
        if (hitCount == 5) {
            return Reward.THIRD;
        }
        if (hitCount == 4) {
            return Reward.FOURTH;
        }
        if (hitCount == 3) {
            return Reward.FIFTH;
        }
        return Reward.BLANK;
    }

    private void validateWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        bonusNumberNotIncludedWinningNumbers(winningNumbers, bonusNumber);
    }

    private void bonusNumberNotIncludedWinningNumbers(List<Integer> winningNumbers, int number) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호에 포함되어 있지 않는 수여야 합니다.");
        }
    }
}
