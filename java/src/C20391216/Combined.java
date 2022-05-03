package C20391216;
import ie.tudublin.Visual;
public class Combined {
    float[] lerpBuff;
    float width;
    float smoothedAmplitude;
    float swing;
    float swing2;
    float color , x, z, spinning, size;
    float smallPyramidDist = 1.8f;

    public Combined(int width)
    {
        this.width = width;
        lerpBuff = new float[width];
    }

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

    public void render(Enemy e)
    {
        e.colorMode(Visual.HSB);
        e.background(0);
        for(int i = 0 ; i < e.getAudioBuffer().size() ; i ++)
        {
            lerpBuff[i] = Visual.lerp(lerpBuff[i], e.getAudioBuffer().get(i), 0.025f);
        }
        //Aman Section 3 Eye
        e.noFill();
        e.beginShape();
        for(float i = 0; i < Visual.TWO_PI; i += 0.01f)
        {
            e.stroke(0,255,128);
            e.strokeWeight(15);
            float r1 = e.width/2.5f;
            float x1 = r1 * Visual.cos(i);
            float y1 = r1 * Visual.pow(Visual.sin(i), 3) * 0.5f;
            //puts the eye in the center of the screen
            e.vertex(x1+e.width/2, y1+e.height/2);
        
            e.stroke(139, 0, 0);
            e.strokeWeight(5);
            //make the ring go crazy and randomly but still on sync to music
            float r = e.width/9 + (e.getSmoothedAmplitude() * e.random(10,150) * 2);
            float x = r * Visual.cos(i);
            float y = r * Visual.sin(i);
            //center the ring
            e.vertex(x+e.width/2, y+e.height/2);
        }
        e.endShape(Visual.CLOSE);

        //Eoin Section 1 Circle
        e.strokeWeight(1.0f);
        for(int i = 0 ; i < e.getAudioBuffer().size() ; i ++)
        {
            float c = Visual.map(i, 0, e.getAudioBuffer().size(), 0, 255);
            float c2 = Visual.map(i, 0, e.getAudioBuffer().size(), 255, 0);
            e.noFill();
            e.stroke(c2, 255, 255);
            float fcircle = lerpBuff[i] * (e.height/2) * 4.0f;
            e.circle(e.width/2,(e.height/2),e.width * 0.01f + fcircle/2);
            e.stroke(c, 255, 255);
            e.circle(e.width/2,(e.height/2),e.width * 0.03f + fcircle/2);
        }

        //Kieran Section 2 Circle Visualiser
        drawCircleVisualiser(e, smoothedAmplitude * 10, spinning, spinning, 580, 4f, 20);

        //Ronan Section 4 Border and Pyramids
        e.strokeWeight(5);
        color = Enemy.map(smoothedAmplitude, 0.2f, 0.8f, 0, 255);
        e.Ronan.drawBorder(smoothedAmplitude/8, color, e);
        
        e.pushMatrix();
        e.translate(e.width/2, e.height/2, 0);
        e.rotateX(Visual.radians(spinning));
        e.rotateY(Visual.radians(spinning * 2));
        size = Enemy.map(smoothedAmplitude, 0, 1, 10, 50);
    
        // drawing 4 smaller ones
        e.translate(150 * smallPyramidDist, 150 * smallPyramidDist);
        e.Ronan.drawPyramid(size, color, e);
        e.Ronan.drawCircle(size, e);

        e.translate(-300 * smallPyramidDist, -300 * smallPyramidDist);
        e.Ronan.drawPyramid(size, color, e);
        e.Ronan.drawCircle(size, e);

        e.translate(300 * smallPyramidDist, 0);
        e.Ronan.drawPyramid(size, color, e);
        e.Ronan.drawCircle(size, e);

        e.translate(-300 * smallPyramidDist, 300 * smallPyramidDist);
        e.Ronan.drawPyramid(size, color, e);
        e.Ronan.drawCircle(size, e);
        e.popMatrix();

        spinning += (smoothedAmplitude) * 4.0f;
    }
}
