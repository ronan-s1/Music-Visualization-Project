package C20391216;

import ie.tudublin.Visual;

public class Kieran
{
    float ang = 0.0001f;
    int width;
    float y;
    float smoothedY;
    float smoothedAmplitude;
    public Kieran()
    {
        y = 0;
        smoothedY = 0;
        smoothedAmplitude = 0;
    }

    
    public void render(Heathens h)
    {
        h.background(0);
        h.noFill();
        h.stroke(200,200,200);
        // h.ellipse(200, 300, 100, 100);
        h.pushMatrix();
        
        // h.translate(h.width/2, h.height/2);
        // h.rotateX(0.1f);
        // h.rotateY(0.5f);
        // h.rotateZ(1.0f);
        

        h.translate(h.width/2, h.height/2, 0);
        //h.rotateX(ang);
        h.rotateX(ang); 
        ang +=0.5f; 
        h.ellipse(0, 0, 75, 75);
        h.popMatrix();
    }
}    


