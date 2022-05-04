package C20391216;

import ie.tudublin.Visual;
import java.util.ArrayList;

public class Kieran
{
    float ang = 0;
    int width;
    float[] lerpBuff;
    float[] lerpColors;
    float x, z, prev = 0;
    float smoothedAmplitude;
    float swing, swing2 = 0, color = 0, color2 = 0;
    float spinning;
    float cx,cy,cz;
    ArrayList<Circle> randCircles;

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
    public void drawCircleVisualiser(Enemy e, float rotateX, float rotateY, float rotateZ, float size, float react, float thickness)
    {
        rotateX = Visual.radians(rotateX);
        rotateY = Visual.radians(rotateY);
        rotateZ = Visual.radians(rotateZ);
        e.pushMatrix();
        e.translate(e.width/2, e.height/2, 0);
        e.calculateAverageAmplitude();
        smoothedAmplitude = e.getSmoothedAmplitude();
        swing = Visual.lerp(swing, Visual.map(e.random(0f, 1.0f), 0, 1, -2, 2), 0.01f);
        e.rotateZ(swing + rotateZ);
        e.rotateX(swing/2 + rotateX);
        e.rotateY(swing2 + rotateY);
        swing2 += smoothedAmplitude * 0.025f;
        //e.rotateX(Visual.radians(90));
        e.beginShape();
        int i = 0;
        e.noFill();
        for(float deg = 0; deg <= 360; deg += 20)
        {
            i = Visual.floor(Visual.map(deg, 0, 360, 0, e.getAudioBuffer().size() - 1));
            color = Visual.map(i, 0, e.getAudioBuffer().size() - 1, 0, 255);
            e.stroke(color, 200, 200);
            e.strokeWeight(thickness);
            x = (smoothedAmplitude * size) * Visual.sin(Visual.radians(deg));
            z = (smoothedAmplitude * size) * Visual.cos(Visual.radians(deg));
            if(deg == 360 || deg == 0)
            {
                e.curveVertex(x,lerpBuff[0] * (100 * react) * 1.5f ,z);
                e.curveVertex(x,lerpBuff[0] * (100 * react) * 1.5f ,z);
            }
            else
            {
                e.curveVertex(x,lerpBuff[i] * (100 * react) * 2.0f ,z);
            }
        }
        e.endShape();
        e.popMatrix();
    }

    public void generateCircles(Enemy e, int amount)
    {
        this.randCircles = new ArrayList<Circle>();
        for(int i = 0; i < amount; i++)
        {
            float cx = e.random(-e.width/2, e.width/2);
            float cy = e.random(-e.height/2, e.height/2);
            float cz = e.random(-e.height/2, e.height/2);
            Circle c = new Circle(cx, cy, cz);
            randCircles.add(c); 
        }
        
    }

    public void randomiseCircles(Enemy e)
    {
        for(Circle c:randCircles)
        {
            float cx = e.random(-e.width/2, e.width/2);
            float cy = e.random(-e.height/2, e.height/2);
            float cz = e.random(-e.height/2, e.height/2); 
            c.cx = cx;
            c.cy = cy;
            c.cz = cz;
        }

    }
 
    public void render(Enemy e)
    {
        if(randCircles == null)
        {
            generateCircles(e, 15);
        }
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
            lerpBuff[i] = Visual.lerp(lerpBuff[i], e.getAudioBuffer().get(i), 0.025f);
        }
        e.fill(255);
        e.noStroke();
        color2 += smoothedAmplitude * 5;
        if(color2 > 255)
        {
            color2 = 0;
        }
        e.pushMatrix();
        e.translate(e.width/2, e.height/2, 0);
        e.circle(0,0,smoothedAmplitude * e.width/3);
        e.fill(color2,255,255);
        e.circle(0,0,(smoothedAmplitude * e.width/3) - e.width * 0.01f);
        e.popMatrix();

        drawCircleVisualiser(e, 0, -30, 0, 500, 2.5f,15);
        drawCircleVisualiser(e, 0, -30, -(spinning + smoothedAmplitude), 380, 1f,15);
        drawCircleVisualiser(e, 0 + spinning * 3, 0, 90 + spinning + smoothedAmplitude, 220, 1f, 15);
        drawCircleVisualiser(e, -(0 + spinning * 2), -45, -(180 + spinning + smoothedAmplitude), 260, 1f, 15);
        spinning += 1;
        e.pushMatrix();
        for(Circle c:randCircles)
        {
            float color = Visual.map(randCircles.indexOf(c), 0, randCircles.size(), 0, 255);
            e.fill(color, 255, 255);
            e.stroke(color, 255, 255);
            c.render(e, smoothedAmplitude);
        }
        // e.translate(e.width/2, e.height/2, 0);
        // e.rotateZ(Visual.radians(spinning * 10));

        if(e.frameCount % (10 + ((1/smoothedAmplitude) * 40)) < 2)
        {
            randomiseCircles(e);
        }
        e.popMatrix();
    }
}    


