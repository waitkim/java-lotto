package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, String bonusNumber) {
        validateWinningLotto(winningNumbers, bonusNumber);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    public int calculateHit(Lotto lotto) {
        return winningNumbers.compareNumber(lotto);
    }

    public int calculateBonus(Lotto lotto) {
        return lotto.compareBonus(bonusNumber);
    }

    private void validateWinningLotto(List<Integer> winningNumbers, String bonusNumber) {
        // LottoNumber라는 객체 만들면 검증을 스스로 하도록 하면 될 듯
        // 그렇게 한다면 Lotto와 LottoNumber가 포함되어 있는지 어떻게 확인?
        checkIsNumber(bonusNumber);
        checkNumberRange(bonusNumber);
        bonusNumberNotIncludedWinningNumbers(winningNumbers, Integer.parseInt(bonusNumber));
    }

    private void checkIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 숫자여야 합니다.");
        }
    }

    private void checkNumberRange(String input) {
        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void bonusNumberNotIncludedWinningNumbers(List<Integer> winningNumbers, int number) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호에 포함되어 있지 않는 수여야 합니다.");
        }
    }
}
