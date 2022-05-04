package C20391216;

import ie.tudublin.Visual;

public class Circle {
    float cx;
    float cy;
    float cz;
    float angle = 0;

    public Circle(float cx, float cy, float cz)
    {
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
    }

    public void render(Enemy e, float spinning)
    {
        // e.pushMatrix();
        // e.translate(e.width/2, e.height/2, 0);
        // e.rotateX(Visual.radians(spinning * 2));
        // e.rotateY(Visual.radians(spinning * 2));
        // e.rotateZ(Visual.radians(spinning * 2));
        // e.popMatrix();
        e.pushMatrix();
        e.translate(e.width/2, e.height/2, 0);
        e.rotateX(Visual.radians(angle));
        e.rotateY(Visual.radians(angle * 5));
        e.rotateZ(Visual.radians(angle * 5));
        e.translate(cx, cy, cz);
        e.sphere(5 * e.getSmoothedAmplitude());
        e.popMatrix();
        angle += (spinning) * 1.6f ;

    }
}
