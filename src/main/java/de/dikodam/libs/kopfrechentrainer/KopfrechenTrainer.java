package de.dikodam.libs.kopfrechentrainer;

public class KopfrechenTrainer {

    private int minStellenanzahl1;
    private int minStellenanzahl2;
    private int maxStellenanzahl1;
    private int maxStellenanzahl2;

    private boolean plusRechnen;
    private boolean minusRechnen;
    private boolean malRechnen;
    private boolean geteiltRechnen;


    public KopfrechenTrainer() {
        minStellenanzahl1 = 1;
        minStellenanzahl2 = 1;
        maxStellenanzahl1 = 2;
        maxStellenanzahl2 = 2;

        plusRechnen = true;
        minusRechnen = false;
        malRechnen = false;
        geteiltRechnen = false;
    }

    public int getMinStellenanzahl1() {
        return minStellenanzahl1;
    }

    public void setMinStellenanzahl1(int minStellenanzahl1) {
        if (minStellenanzahl1 <= 0) {
            throw new IllegalArgumentException("Minimale Stellenanzahl (Argument 1) muss mindestens 1 sein!");
        }
        this.minStellenanzahl1 = minStellenanzahl1;
        if (maxStellenanzahl1 < minStellenanzahl1) {
            maxStellenanzahl1 = minStellenanzahl1;
        }
    }

    public int getMinStellenanzahl2() {
        return minStellenanzahl2;
    }

    public void setMinStellenanzahl2(int minStellenanzahl2) {
        if (minStellenanzahl2 <= 0) {
            throw new IllegalArgumentException("Minimale Stellenanzahl (Argument 2) muss mindestens 1 sein!");
        }
        this.minStellenanzahl2 = minStellenanzahl2;
        if (maxStellenanzahl2 < minStellenanzahl2) {
            maxStellenanzahl2 = minStellenanzahl2;
        }
    }

    public int getMaxStellenanzahl1() {
        return maxStellenanzahl1;
    }

    public void setMaxStellenanzahl1(int maxStellenanzahl1) {
        if (maxStellenanzahl1 <= 0) {
            throw new IllegalArgumentException("Die maximale Stellenanzahl (Argument 1) muss mindestens 1 sein!");
        }

        this.maxStellenanzahl1 = maxStellenanzahl1;

        if (minStellenanzahl1 > maxStellenanzahl1) {
            minStellenanzahl1 = maxStellenanzahl1;
        }
    }

    public int getMaxStellenanzahl2() {
        return maxStellenanzahl2;
    }

    public void setMaxStellenanzahl2(int maxStellenanzahl2) {
        if(maxStellenanzahl2 <= 0) {
            throw new IllegalArgumentException("Die maximale Stellenanzahl (Argument 2) muss mindestens 1 sein!");
        }

        this.maxStellenanzahl2 = maxStellenanzahl2;

        if (minStellenanzahl2 > maxStellenanzahl2) {
            minStellenanzahl2 = maxStellenanzahl2;
        }
    }

    public boolean isPlusRechnen() {
        return plusRechnen;
    }

    public void setPlusRechnen(boolean plusRechnen) {
        this.plusRechnen = plusRechnen;
    }

    public boolean isMinusRechnen() {
        return minusRechnen;
    }

    public void setMinusRechnen(boolean minusRechnen) {
        this.minusRechnen = minusRechnen;
    }

    public boolean isMalRechnen() {
        return malRechnen;
    }

    public void setMalRechnen(boolean malRechnen) {
        this.malRechnen = malRechnen;
    }

    public boolean isGeteiltRechnen() {
        return geteiltRechnen;
    }

    public void setGeteiltRechnen(boolean geteiltRechnen) {
        this.geteiltRechnen = geteiltRechnen;
    }


}
