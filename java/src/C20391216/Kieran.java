package C20391216;

import ie.tudublin.Visual;

public class Kieran
{
    boolean twocubes = false;
    float smoothedBoxSize = 0;
    public void render(Heathens h)
    {
        h.calculateAverageAmplitude();
        h.background(0);
        h.noFill();
        h.lights();
        h.stroke(Visual.map(h.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        h.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        h.translate(0, 0, -250);
               
        float boxSize = 50 + (h.getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = Visual.lerp(smoothedBoxSize, boxSize, 0.2f);        
        if (twocubes)
        {
            h.pushMatrix();
            h.translate(-200, 0, 0);
           h.rotateY(angle);
            h.rotateX(angle);
            h.box(smoothedBoxSize);
            //strokeWeight(1);
            //sphere(smoothedBoxSize);
            h.popMatrix();
            h.pushMatrix();
            h.translate(200, 0, 0);
            h.rotateY(angle);
            h.rotateX(angle);
            h.strokeWeight(2); 
            h.box(smoothedBoxSize);
            h.popMatrix();
        }
        else
        {
            h.rotateY(angle * 5);
            h.rotateX(angle * 10);
            //strokeWeight(1);
            //sphere(smoothedBoxSize/ 2);            
            h.strokeWeight(5);
            
            h.box(smoothedBoxSize);
        }
        angle += 0.01f;
    }
    float angle = 0;
}

