package com.kib.weblab3.math;

public class HitResultCalculator {

    public boolean calculateHitResult(Double x, Double y, Integer r) {
        return this.checkIfPointInFirstQuarter(x, y, r) || this.checkIfPointInSecondQuarter(x, y, r) || this.checkIfPointInThirdQuarter(x, y, r);
    }

    private boolean checkIfPointInFirstQuarter(Double x, Double y, Integer r) {
        return y <= -x + r && y <= r && y >= 0.0F && x >= 0.0F && x <= r;
    }

    private boolean checkIfPointInSecondQuarter(Double x, Double y, Integer r) {
        return x <= 0.0F && y >= 0.0F && x * x + y * y < r / 2F * r / 2F;
    }

    private boolean checkIfPointInThirdQuarter(Double x, Double y, Integer r) {
        return y <= 0.0F && y >= -r && x >= -r / 2.0F && x <= 0.0F;
    }

}
