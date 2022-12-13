package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> randomNumbers) {
        validate(randomNumbers);
        numbers = randomNumbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("[");
        for (LottoNumber number : numbers) {
            string.append(number.toString());
            string.append(", ");
        }
        string.deleteCharAt(string.length()-1);
        string.deleteCharAt(string.length()-1);
        string.append("]");
        return string.toString();
    }

    public long hitCount(Lotto lotto) {
        return lotto.numbers.stream()
                .filter(this::isHit)
                .count();
    }

    public boolean isHit(LottoNumber number) {
        return numbers.contains(number);
    }

    private void validate(List<Integer> numbers) {
        checkCounts(numbers);
        checkNumberRange(numbers);
        checkEachMustBeDifferent(numbers);
    }

    private void checkCounts(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 총 6개여야 합니다.");
        }
    }

    private void checkNumberRange(List<Integer> numbers) {
        numbers.stream()
                .filter(num -> num < 1 || num > 45)
                .findAny()
                .ifPresent(number -> {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        });
    }

    private void checkEachMustBeDifferent(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 모두 달라야합니다.");
        }
    }
}
