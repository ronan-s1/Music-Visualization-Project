package C20391216;

import ie.tudublin.Visual;

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
 
    public void render(Enemy e)
    {
        // e.blendMode(Visual.DIFFERENCE);
        // e.fill(1, 1, 1, 255);
        // e.rect(0, 0, e.width, e.height);

        // e.blendMode(Visual.ADD);
        e.background(0);
        e.colorMode(Visual.HSB);
        e.noFill();
        e.stroke(200,200,200);
        for(int i = 0 ; i < e.getAudioBuffer().size() ; i ++)
        {
            lerpBuff[i] = Visual.lerp(lerpBuff[i], e.getAudioBuffer().get(i), 0.05f);
        }
        // e.ellipse(200, 300, 100, 100);
        e.pushMatrix();
        
        // e.translate(e.width/2, e.height/2);
        // e.rotateX(0.1f);
        // e.rotateY(0.5f);
        // e.rotateZ(1.0f);
        

        e.translate(e.width/2, e.height/2, 0);
        e.calculateAverageAmplitude();
        smoothedAmplitude = e.getSmoothedAmplitude();
        //swing = Visual.lerp(swing,Visual.map(smoothedAmplitude, 0, 1, -3, 3), 0.1f);
        swing = Visual.lerp(swing, Visual.map(e.random(0f, 1.0f), 0, 1, -3, 3), 0.02f);
        e.rotateZ(swing);
        e.rotateX(swing/2);
        e.rotateY(swing2/4);
        swing2 += smoothedAmplitude;
        e.fill(color2,255,255);
        e.noStroke();
        color2 += smoothedAmplitude * 10;
        if(color2 > 255)
        {
            color2 = 0;
        }
        e.sphere(smoothedAmplitude * 250);
        //e.rotateX(Visual.radians(90));
        e.beginShape();
        int i = 0;
        e.noFill();
        for(float deg = 0; deg <= 360; deg += 30)
        {
            i = Visual.floor(Visual.map(deg, 0, 360, 0, e.getAudioBuffer().size() - 1));
            color = Visual.map(i, 0, e.getAudioBuffer().size() - 1, 0, 255);
            e.stroke(color, 200, 200);
            e.strokeWeight(10);
            x = (smoothedAmplitude * e.width) * Visual.sin(Visual.radians(deg));
            z = (smoothedAmplitude * e.width) * Visual.cos(Visual.radians(deg));
            if(deg == 360 || deg == 0)
            {
                e.curveVertex(x,lerpBuff[0] * (e.height / 2) * 1.5f ,z);
                e.curveVertex(x,lerpBuff[0] * (e.height / 2) * 1.5f ,z);
            }
            else
            {
                e.curveVertex(x,lerpBuff[i] * (e.height / 2) * 2.0f ,z);
            }
            //e.curveVertex(x,0,z);
        }
        e.endShape();
        e.popMatrix();
    }
}    


