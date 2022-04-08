package C20391216;

import ie.tudublin.Visual;

public class Eoin extends Visual
{
    
    float[] lerpedBuffer;
    float[] lerpedBuffer2;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    float halfH = height / 2;
    float average = 0;
    float average2 = 0;
    float sum = 0;

    smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
    
    float cx = width / 2;
    float cy = height / 2;

    for(int i = 0 ; i < ab.size() ; i ++)
    {
        sum += abs(ab.get(i));
        lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
    }
    average= sum / (float) ab.size();

    sum = 0;

    for(int i = 0 ; i < ab.size() ; i ++)
    {
        sum += abs(ab.get(i));
        lerpedBuffer2[i] = lerp(lerpedBuffer2[i], ab.get(i), 0.05f);
    }

    average2= sum / (float) ab.size();
}


    public void render(Heathens h)
    {
        
        h.background(0);

        for(int i = 0 ; i < ab.size() ; i ++)
        {
            
            float c = map(i, 0, ab.size(), 0, 255);
            h.stroke(c, 255, 255);
            float f1 = lerpedBuffer[i] * halfH * 1.7f;
            h.rect(i, halfH*2.0f + f1, i, halfH - f1);    
            
            float f2 = lerpedBuffer[i] * halfH * 1.7f;
            h.rect(i,0 - f2, i, 0 + f2); 

            float f3 = lerpedBuffer2[i] * halfH * 1.7f;
            h.rect(0+f3,i, 0-f3, i); 

            float f4 = lerpedBuffer2[i] * halfH * 1.7f;
            h.rect(height-f4,i, width + f4, i); 

        }

        
    }
}

