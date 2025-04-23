package _utils;

/**
 * Stores a range [l, r)
 * l inclusive
 * r exclusive
 */
public class Range {

    private int l;
    private int r; // Exclusive
    private int sz;

    public Range(int l, int r) {
        this(l, r, r - l + 1);
    }

    public Range(int l, int r, int sz) {
        this.l = l;
        this.r = r;
        this.sz = sz;
    }

    public int getL() {
        return l;
    }

    public int getR() {
        return r;
    }

    public void setL(int l) {
        this.l = l;
        updateSize();
    }

    public void setR(int r) {
        this.r = r;
        updateSize();
    }

    public void setLR(int l, int r) {
        this.l = l;
        this.r = r;
        updateSize();
    }

    private void updateSize() {
        sz = r - l;
    }

    public int size() {
        return sz;
    }

    public String toString() {
        return String.format("{l=%d,r=%d,size=%d}", l, r, sz);
    }

}