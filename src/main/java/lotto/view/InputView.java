package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class InputView {
    public int askPurchaseAmount() {
        String input = Console.readLine();
        // 입력을 담당하는 클래스에서 검증까지?
        try {
            Integer.parseInt(input);
            System.out.println("구입금액을 입력해 주세요.");
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
        return Integer.parseInt(input);
    }

    public String askWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public String askBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }
}
