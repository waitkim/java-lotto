package lotto.domain;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int isHit(LottoNumber num) {
        int hit = 0;
        if (number == num.number) {
            hit += 1;
        }
        return hit;
    }

    private void validateNumber(int number) {
        checkNumberRange(number);
    }

    private void checkNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[Error] 로또 번호는 1~45 범위의 숫자여야 합니다.");
        }
    }
}
