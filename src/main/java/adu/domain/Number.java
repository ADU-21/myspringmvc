package adu.domain;

public enum Number {
    ONE("1"),
    TWO("2"),
    THREE("3");

    private String value;

    private Number(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Number fromValue(String value) {
        for (Number number : Number.values()) {
            if (number.value.equals(value)) {
                return number;
            }
        }
        throw new IllegalArgumentException("Unknow type：" + value);
    }
}
