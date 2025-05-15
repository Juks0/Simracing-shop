public enum Mra {
    FIVE(5),
    NINE(9),
    TWELVE(12),
    SIXTEEN(16),
    TWENTY_ONE(21);

    private final int value;

    Mra(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
