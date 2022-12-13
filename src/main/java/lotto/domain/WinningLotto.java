package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningLotto(winningNumbers, bonusNumber);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public int calculateHit(Lotto lotto) {
        return winningNumbers.hitCount(lotto);
    }

    public boolean calculateBonus(Lotto lotto) {
        return lotto.isHit(bonusNumber);
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
