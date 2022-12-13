package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMaker {
    public List<Lotto> publishLottosByPurchaseAmount(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseAmount / 1000; i++) {
            lottos.add(publishLotto());
        }
        return lottos;
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        isMultiplesOf1000(purchaseAmount);
        isOverThan1000(purchaseAmount);
    }

    private Lotto publishLotto() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }

    private static void isMultiplesOf1000(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000으로 나누어떨어져야 합니다.");
        }
    }

    private static void isOverThan1000(int purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000보다 커야 합니다.");
        }
    }
}
