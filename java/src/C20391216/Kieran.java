package C20391216;

import ie.tudublin.Visual;
import processing.core.PApplet;

public class Kieran
{
    float ang = 0;
    int width;
    float[] lerpBuff;
    float[] lerpColors;
    float x, z, prev = 0;
    float smoothedAmplitude;
    float swing, swing2 = 0, color = 0, color2 = 0;

    public Kieran(int width)
    {
        this.width = width;
        lerpBuff = new float[width];
    }

    // public float random_colors()
    // {
    //     float result;
        

    //     return result;
    // }
 
    public void render(Heathens h)
    {
        // h.blendMode(Visual.DIFFERENCE);
        // h.fill(1, 1, 1, 255);
        // h.rect(0, 0, h.width, h.height);

        // h.blendMode(Visual.ADD);
        h.background(0);
        h.noFill();
        h.stroke(200,200,200);
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            lerpBuff[i] = Visual.lerp(lerpBuff[i], h.getAudioBuffer().get(i), 0.05f);
        }
        // h.ellipse(200, 300, 100, 100);
        h.pushMatrix();
        
        // h.translate(h.width/2, h.height/2);
        // h.rotateX(0.1f);
        // h.rotateY(0.5f);
        // h.rotateZ(1.0f);
        

        h.translate(h.width/2, h.height/2, 0);
        h.calculateAverageAmplitude();
        smoothedAmplitude = h.getSmoothedAmplitude();
        //swing = Visual.lerp(swing,Visual.map(smoothedAmplitude, 0, 1, -3, 3), 0.1f);
        swing = Visual.lerp(swing, Visual.map(h.random(0f, 1.0f), 0, 1, -3, 3), 0.02f);
        h.rotateZ(swing);
        h.rotateX(swing/2);
        h.rotateY(swing2/4);
        swing2 += smoothedAmplitude;
        h.fill(color2,255,255);
        h.noStroke();
        color2 += smoothedAmplitude * 10;
        if(color2 > 255)
        {
            color2 = 0;
        }
        h.sphere(smoothedAmplitude * 250);
        //h.rotateX(Visual.radians(90));
        h.beginShape();
        int i = 0;
        h.noFill();
        for(float deg = 0; deg <= 360; deg += 30)
        {
            i = Visual.floor(Visual.map(deg, 0, 360, 0, h.getAudioBuffer().size() - 1));
            color = Visual.map(i, 0, h.getAudioBuffer().size() - 1, 0, 255);
            h.stroke(color, 200, 200);
            h.strokeWeight(10);
            x = (smoothedAmplitude * h.width) * Visual.sin(Visual.radians(deg));
            z = (smoothedAmplitude * h.width) * Visual.cos(Visual.radians(deg));
            if(deg == 360 || deg == 0)
            {
                h.curveVertex(x,lerpBuff[0] * (h.height / 2) * 1.5f ,z);
                h.curveVertex(x,lerpBuff[0] * (h.height / 2) * 1.5f ,z);
            }
            else
            {
                h.curveVertex(x,lerpBuff[i] * (h.height / 2) * 2.0f ,z);
            }
            //h.curveVertex(x,0,z);
        }
        h.endShape();
        h.popMatrix();
    }
}    


