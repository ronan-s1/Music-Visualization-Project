package C20391216;

import ie.tudublin.Visual;

public class Eoin
{
    int width;
    float[] lerpedBuffer;
    float y;
    float angle = 0;
    float smoothedY;
    float smoothedAmplitude;

    public Eoin(int width)
    {
        this.width = width;
        lerpedBuffer = new float[width];
        y = 0;
        smoothedY = 0;
        smoothedAmplitude = 0;
    }

    // this is method thats called in the heathens class to run my visualiser
    public void render(Heathens h)
    {
        h.colorMode(Visual.HSB);
        float halfH = h.height / 2;
        float average = 0;
        float sum = 0;
        smoothedAmplitude = Visual.lerp(smoothedAmplitude, average, 0.1f);    

    
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            sum += Visual.abs(h.getAudioBuffer().get(i));
            lerpedBuffer[i] = Visual.lerp(lerpedBuffer[i], h.getAudioBuffer().get(i), 0.05f);
        }
        average= sum / (float) h.getAudioBuffer().size();

        sum = 0;

        h.background(0);

         //For loop to loop through all of the visuals
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            // These are to map through the colour specturm and change colours while visualising
            float c = Visual.map(i, 0, h.getAudioBuffer().size(), 0, 255);
            float c2 = Visual.map(i, 0, h.getAudioBuffer().size(), 255, 0);
            
            // These will add the border to all 4 sides and will be changed with the frequency of the song
            h.stroke(c, 255, 255);
            float f1 = lerpedBuffer[i] * halfH * 2.5f;
            h.rect(i*2, halfH*1.95f + f1, i, halfH - f1);    
            
            float f2 = lerpedBuffer[i] * halfH * 2.5f;
            h.rect(i*2,0 + f2, i, 0 - f2); 

            float f3 = lerpedBuffer[i] * halfH * 2.0f;
            h.rect(0+f3,i, 0-f3, i); 

            float f4 = lerpedBuffer[i] * halfH * 2.0f;
            h.rect(h.width-f4,i, h.height + f4, i); 

            // this adds the 2 circles in the middle that each are mapped to the opposite colour spectrum
            h.noFill();
            h.stroke(c2, 255, 255);
            float fcircle = lerpedBuffer[i] * halfH * 4.0f;
            h.circle(h.width/2,halfH,h.width * 0.1f + fcircle);
            h.stroke(c, 255, 255);
            h.circle(h.width/2,halfH,h.width * 0.3f + fcircle);
            
            
        }
    
    }


}    


