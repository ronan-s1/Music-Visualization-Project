package C20391216;

import ie.tudublin.Visual;

public class aman extends Visual
{
    float rotate = 0;
    public void render(Heathens h)
    {
        h.colorMode(RGB);
        h.background(GRAY);

        float halfW = h.width / 2;
        float halfH = h.height / 2;

        //create an eye shape using this code
        h.stroke(0);
        h.strokeWeight(10);
        h.fill(255);
        h.beginShape();
        for(float i = 0; i < TWO_PI; i += 0.01f)
        {
            float r = h.width/2.5f;
            float x = r * cos(i);
            float y = r * pow(sin(i), 3) * 0.5f;
            //puts the eye in the center of the screen
            h.vertex(x+halfW, y+halfH);
        }
        h.endShape();

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
        smoothedAmplitude = smoothedAmplitude * 5;

        //create the iris
        h.strokeWeight(10);
        h.stroke(0);
        for(int i = 0; i < h.getAudioBuffer().size(); i++)
        {
            //the iris
            float radius = map(smoothedAmplitude, 0, 0.6f, h.width/4, 500);	
            h.fill(255,0,0);	
            h.circle(halfW, halfH, radius);

            //draw the pupil for the eye
            float radius2 = map(smoothedAmplitude, 0, 0.7f, h.width/20, 500);
            h.fill(0);
            h.circle(halfW, halfH, radius2);
        }

        //drawing the ring inside the eye
        h.noFill();
        h.strokeWeight(10);
        h.beginShape();
        h.stroke(139, 0, 0);
        h.strokeWeight(5);
        for(float i = 0; i < TWO_PI; i += 0.01f)
        {
            //make the ring go crazy and randomly but still on beat
            float r = h.width/9 + (smoothedAmplitude * h.random(50,80));
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
        h.translate(halfW, halfH);

		for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
			total += abs(h.getAudioBuffer().get(i));
		}
		amplitude = total / h.getAudioBuffer().size();

        rotate += amplitude / 1.5f;
        h.rotate(rotate);
        
        for(int i = 0; i < h.getAudioBuffer().size(); i++)
        {
            float radius3 = map(smoothedAmplitude, 0, 3, h.width/60, 500);

            //tomoes are placed in a triangular way
            h.ellipse(0, h.height/9.5f, radius3, radius3);
            h.ellipse(h.width/11.5f, h.height/-18.5f, radius3, radius3);
            h.ellipse(h.width/-11.5f, h.height/-18.5f, radius3, radius3);
            
        }
        h.popMatrix();
    }
}