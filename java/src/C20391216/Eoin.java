package C20391216;

import ie.tudublin.Visual;

public class Eoin
{
    int width;
    float[] lerpedBuffer;
    float[] lerpedBuffer2;
    float y;
    float smoothedY;
    float smoothedAmplitude;
    public Eoin(int width)
    {
        this.width = width;
        lerpedBuffer = new float[width];
        lerpedBuffer2 = new float[width];
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

        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            sum += Visual.abs(h.getAudioBuffer().get(i));
            lerpedBuffer2[i] = Visual.lerp(lerpedBuffer2[i], h.getAudioBuffer().get(i), 0.05f);
        }

        average2= sum / (float) h.getAudioBuffer().size();
        h.background(0);
        
        for(int i = 0 ; i < h.getAudioBuffer().size() ; i ++)
        {
            
            float c = Visual.map(i, 0, h.getAudioBuffer().size(), 0, 255);
            h.stroke(c, 255, 255);
            float f1 = lerpedBuffer[i] * halfH * 1.7f;
            h.rect(i, halfH*2.0f + f1, i, halfH - f1);    
            
            float f2 = lerpedBuffer[i] * halfH * 1.7f;
            h.rect(i,0 - f2, i, 0 + f2); 

            float f3 = lerpedBuffer2[i] * halfH * 1.7f;
            h.rect(0+f3,i, 0-f3, i); 

            float f4 = lerpedBuffer2[i] * halfH * 1.7f;
            h.rect(h.height-f4,i, h.width + f4, i); 

        }
    }
}    


