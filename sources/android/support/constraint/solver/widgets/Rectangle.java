package android.support.constraint.solver.widgets;

public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f11x;

    /* renamed from: y */
    public int f12y;

    public void setBounds(int x, int y, int width2, int height2) {
        this.f11x = x;
        this.f12y = y;
        this.width = width2;
        this.height = height2;
    }

    /* access modifiers changed from: package-private */
    public void grow(int w, int h) {
        this.f11x -= w;
        this.f12y -= h;
        this.width += 2 * w;
        this.height += 2 * h;
    }

    /* access modifiers changed from: package-private */
    public boolean intersects(Rectangle bounds) {
        return this.f11x >= bounds.f11x && this.f11x < bounds.f11x + bounds.width && this.f12y >= bounds.f12y && this.f12y < bounds.f12y + bounds.height;
    }

    public boolean contains(int x, int y) {
        return x >= this.f11x && x < this.f11x + this.width && y >= this.f12y && y < this.f12y + this.height;
    }

    public int getCenterX() {
        return (this.f11x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f12y + this.height) / 2;
    }
}
