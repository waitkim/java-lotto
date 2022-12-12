package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        ArrayList<Integer> numbersToSort = new ArrayList<>(numbers);
        Collections.sort(numbersToSort);
        this.numbers = numbersToSort;
    }

    public void printNumbers() {
        System.out.println(numbers);
    }

    public int compareNumber(Lotto lotto) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (numbers.contains(lotto.numbers.get(i))) {
                count += 1;
            }
        }
        return count;
    }

    public int compareBonus(int number) {
        int bonus = 0;
        if (numbers.contains(number)) {
            bonus += 1;
        }
        return bonus;
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
