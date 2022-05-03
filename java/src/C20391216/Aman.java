package C20391216;

import ie.tudublin.Visual;

public class Aman extends Visual
{
    float rotate = 0;
    Boolean eyesClose = false;

    //here is where the main eye is made too
    public void render(Enemy e)
    {
        e.colorMode(RGB);
        e.background(GRAY);

        float halfW = e.width / 2;
        float halfH = e.height / 2;

        float total = 0;
        float amplitude = 0;
        float smoothedAmplitude = 0;
        for(int i = 0 ; i < e.getAudioBuffer().size() ; i ++)
        {
            total += abs(e.getAudioBuffer().get(i));
        }
        amplitude = total / e.getAudioBuffer().size();
        smoothedAmplitude = lerp(smoothedAmplitude, amplitude, 0.1f);
        //make visualizer more intense
        smoothedAmplitude = smoothedAmplitude * 3;

        //make background up in intensity depending on music
        float colour = smoothedAmplitude * 1500;
        e.background(colour,0,0);

        //drawing the ring inside the eye
        e.noFill();
        e.beginShape();
        for(float i = 0; i < TWO_PI; i += 0.01f)
        {
            e.stroke(0);
            e.strokeWeight(15);
            float r1 = e.width/2.5f;
            float x1 = r1 * cos(i);
            float y1 = r1 * pow(sin(i), 3) * 0.5f;
            //puts the eye in the center of the screen
            e.vertex(x1+halfW, y1+halfH);
        
            e.stroke(139, 0, 0);
            e.strokeWeight(5);
            //make the ring go crazy and randomly but still on sync to music
            float r = e.width/9 + (smoothedAmplitude * e.random(10,150) * 2);
            float x = r * cos(i);
            float y = r * sin(i);
            //center the ring
            e.vertex(x+halfW, y+halfH);
        }
        e.endShape(CLOSE);

        //create the iris
        e.strokeWeight(10);
        for(int i = 0; i < e.getAudioBuffer().size(); i++)
        {
            //the iris
            float radius = map(smoothedAmplitude, 0, 0.6f, e.width/4, 500);	
            e.fill(255,0,0);	
            e.circle(halfW, halfH, radius);

            //draw the pupil for the eye
            float radius2 = map(smoothedAmplitude * 1.5f, 0, 0.7f, e.width/20, 500);
            e.fill(0);
            e.circle(halfW, halfH, radius2); 

            e.fill(255);
            e.circle(halfW, halfH, e.getSmoothedAmplitude());
        }


        //drawing the "tomoe"
        e.fill(0);
        e.stroke(0);
        e.pushMatrix();
        e.translate(halfW, halfH); //translate to center

        rotate += amplitude / 0.7f;
        e.rotate(rotate);
        
        //radius for the tomoe
        float radius3 = map(smoothedAmplitude, 0, 3, e.width/60, 500);

        // 3 'tomoe' made to spin around the center pupil
        for( int i = 0; i < 3; i++ ) 
        {
            e.rotate( TWO_PI/3 );
            e.ellipse(halfW/7.5f, halfH/7.5f, radius3, radius3);
        }  
        e.popMatrix();
    }
}