package C20391216;

import ie.tudublin.Visual;

public class Aman extends Visual
{
    float rotate = 0;
    Boolean eyesClose = false;

    //here is where the main eye is made too
    public void render(Heathens h)
    {
        h.colorMode(RGB);
        h.background(GRAY);

        float halfW = h.width / 2;
        float halfH = h.height / 2;

        float total = 0;
        float amplitude = 0;
        float smoothedAmplitude = 0;
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            total += abs(h.getAudioBuffer().get(i));
        }
        amplitude = total / h.getAudioBuffer().size();
        smoothedAmplitude = lerp(smoothedAmplitude, amplitude, 0.1f);
        //make visualizer more intense
        smoothedAmplitude = smoothedAmplitude * 3;

        //make background up in intensity depending on music
        float colour = smoothedAmplitude * 1500;
        h.background(colour,0,0);

        //create an eye shape using this code
        h.stroke(255,0,0);
        h.beginShape();
        h.strokeWeight(10);
        for(float i = 0; i < TWO_PI; i += 0.01f)
        {
            if(h.getAudioPlayer().position() < 160500)
            {
                float r = h.width/2.5f;
                float x = r * cos(i);
                float y = r * pow(sin(i), 3) * 0.5f;
                //puts the eye in the center of the screen
                h.vertex(x+halfW, y+halfH);
            }
            else
            {
                eyesClose = true;
                h.stroke(0);
                h.background(255);

                float r = h.width/2.5f;
                float x = r * cos(i);
                float y = r * pow(sin(i), 3) * 0.05f;
                //puts the eye in the center of the screen
                h.vertex(x+halfW, y+halfH);
            }
        }
        h.endShape();
        
        //skip everything else when eye closes
        if(eyesClose)
        {
            return;
        }

        //create the iris
        h.strokeWeight(10);
        for(int i = 0; i < h.getAudioBuffer().size(); i++)
        {
            //the iris
            float radius = map(smoothedAmplitude, 0, 0.6f, h.width/4, 500);	
            h.fill(255,0,0);	
            h.circle(halfW, halfH, radius);

            //draw the pupil for the eye
            float radius2 = map(smoothedAmplitude * 1.5f, 0, 0.7f, h.width/20, 500);
            h.fill(0);
            h.circle(halfW, halfH, radius2); 

            h.fill(255);
            h.circle(halfW, halfH, h.getSmoothedAmplitude());
        }

        //drawing the ring inside the eye
        h.noFill();
        h.beginShape();
        h.stroke(139, 0, 0);
        h.strokeWeight(5);
        for(float i = 0; i < TWO_PI; i += 0.01f)
        {
            //make the ring go crazy and randomly but still on sync to music
            float r = h.width/9 + (smoothedAmplitude * h.random(10,150));
            float x = r * cos(i);
            float y = r * sin(i);
            //center the ring
            h.vertex(x+halfW, y+halfH);
        }
        h.endShape(CLOSE);

        //drawing the "tomoe"
        h.fill(0);
        h.stroke(0);
        h.pushMatrix();
        h.translate(halfW, halfH); //translate to center

        rotate += amplitude / 0.7f;
        h.rotate(rotate);
        
        //radius for the tomoe
        float radius3 = map(smoothedAmplitude, 0, 3, h.width/60, 500);

        // 3 'tomoe' made to spin around the center pupil
        for( int i = 0; i < 3; i++ ) 
        {
            h.rotate( TWO_PI/3 );
            h.ellipse(halfW/7.5f, halfH/7.5f, radius3, radius3);
        }  
        h.popMatrix();
    }
}