package lotto.domain;

public enum Reward {
    BLANK(0,""),
    FIFTH(5000, "3개 일치 (5,000원)"),
    FOURTH(50000, "4개 일치 (50,000원)"),
    THIRD(1500000, "5개 일치 (1,500,000원)"),
    SECOND(30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIRST(2000000000, "6개 일치 (2,000,000,000원)");

    private final int reward;
    private final String sentence;

    // 계산을 위해 어쩔 수 없이 -> 없앨 방법 생각
    public int getReward() {
        return reward;
    }

    // 출력을 위해 어쩔 수 없이 -> 없앨 방법 생각
    public String getSentence() {
        return sentence;
    }

    private Reward(int reward, String sentence) {
        this.reward = reward;
        this.sentence = sentence;
    }
}
