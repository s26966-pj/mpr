package org.example;

public enum Type {
    PREMIUM(1.5), STANDARD(1), ECONOMY(0.5);

    private final double multiplier;

    Type(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
