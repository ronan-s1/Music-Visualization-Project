package C20391216;

import ie.tudublin.Visual;

public class Eoin extends Visual
{
    int width;
    float[] lerpedBuffer;
    float[] lerpedBuffer2;
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

    
    public void render(Heathens h)
    {
        float halfH = h.height / 2;
        float average = 0;
        float average2 = 0;
        float sum = 0;
        smoothedAmplitude = Visual.lerp(smoothedAmplitude, average, 0.1f);
        float cx = h.width / 2;
        float cy = h.height / 2;

        
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            sum += Visual.abs(h.getAudioBuffer().get(i));
            lerpedBuffer[i] = Visual.lerp(lerpedBuffer[i], h.getAudioBuffer().get(i), 0.05f);
        }
        average= sum / (float) h.getAudioBuffer().size();

        sum = 0;

        h.background(0);
        /* h.height = 1920;
        h.width = 1080; */
         
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            
            float c = Visual.map(i, 0, h.getAudioBuffer().size(), 0, 255);
            float c2 = Visual.map(i, 0, h.getAudioBuffer().size(), 255, 0);
            h.stroke(c, 255, 255);
            float f1 = lerpedBuffer[i] * halfH * 2.5f;
            h.rect(i*2, halfH*1.95f + f1, i, halfH - f1);    
            
            float f2 = lerpedBuffer[i] * halfH * 2.5f;
            h.rect(i*2,0 + f2, i, 0 - f2); 

            float f3 = lerpedBuffer[i] * halfH * 2.0f;
            h.rect(0+f3,i, 0-f3, i); 

            float f4 = lerpedBuffer[i] * halfH * 2.0f;
            h.rect(h.width-f4,i, h.height + f4, i); 

            h.noFill();
            h.stroke(c2, 255, 255);
            float fcircle = lerpedBuffer[i] * halfH * 4.0f;
            //h.circle(width/2, 512, 300 + fcircle);
            h.circle(width/2,halfH,600 + fcircle);
            h.stroke(c, 255, 255);
            h.circle(width/2,halfH,200 + fcircle);
            //h.triangle(halfH/2, width/4, halfH/2 - 50,  width/4 + 50, halfH/2 + 50, width/4 + 50);
            //h.rect(250+f4,200+f4, 1425+f4, 625+f4);
            
        }

    
    }


}    


