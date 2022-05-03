package C20391216;

public class Circle {
    float cx;
    float cy;
    float cz;

    public Circle(float cx, float cy, float cz)
    {
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
    }

    public void render(Enemy e)
    {
        e.pushMatrix();
        e.translate(cx, cy, cz);
        e.sphere(10 * e.getSmoothedAmplitude());
        e.popMatrix();
    }
}
