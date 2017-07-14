package de.dikodam.libs.kopfrechentrainer;

public class KopfrechenTrainer {

    private int minStellenanzahl1;
    private int minStellenanzahl2;
    private int maxStellenanzahl1;
    private int maxStellenanzahl2;

    private boolean plusErlaubt;
    private boolean minusErlaubt;
    private boolean malErlaubt;
    private boolean geteiltErlaubt;


    public KopfrechenTrainer() {
        minStellenanzahl1 = 1;
        minStellenanzahl2 = 1;
        maxStellenanzahl1 = 2;
        maxStellenanzahl2 = 2;
    }

    public int getMinStellenanzahl1() {
        return minStellenanzahl1;
    }

    public void setMinStellenanzahl1(int minStellenanzahl1) {
        this.minStellenanzahl1 = minStellenanzahl1;
    }

    public int getMinStellenanzahl2() {
        return minStellenanzahl2;
    }

    public void setMinStellenanzahl2(int minStellenanzahl2) {
        this.minStellenanzahl2 = minStellenanzahl2;
    }

    public int getMaxStellenanzahl1() {
        return maxStellenanzahl1;
    }

    public void setMaxStellenanzahl1(int maxStellenanzahl1) {
        this.maxStellenanzahl1 = maxStellenanzahl1;
    }

    public int getMaxStellenanzahl2() {
        return maxStellenanzahl2;
    }

    public void setMaxStellenanzahl2(int maxStellenanzahl2) {
        this.maxStellenanzahl2 = maxStellenanzahl2;
    }

    public boolean isPlusErlaubt() {
        return plusErlaubt;
    }

    public void setPlusErlaubt(boolean plusErlaubt) {
        this.plusErlaubt = plusErlaubt;
    }

    public boolean isMinusErlaubt() {
        return minusErlaubt;
    }

    public void setMinusErlaubt(boolean minusErlaubt) {
        this.minusErlaubt = minusErlaubt;
    }

    public boolean isMalErlaubt() {
        return malErlaubt;
    }

    public void setMalErlaubt(boolean malErlaubt) {
        this.malErlaubt = malErlaubt;
    }

    public boolean isGeteiltErlaubt() {
        return geteiltErlaubt;
    }

    public void setGeteiltErlaubt(boolean geteiltErlaubt) {
        this.geteiltErlaubt = geteiltErlaubt;
    }

    public int drei () {
        return 3;
    }


}
