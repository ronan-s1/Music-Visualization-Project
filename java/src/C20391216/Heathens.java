package C20391216;

import ie.tudublin.Visual;

public class Heathens extends Visual {

    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void settings()
    {
        size(1024, 1024, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }

    int mode = 1;
    boolean paused = false;
    public void keyPressed()
    {
        if (key >= '0' && key <= '4') {
			mode = key - '0';
		}
        switch(key)
        {
            case ' ':
            {
                if(paused)
                {
                    getAudioPlayer().play();
                    paused = false;
                }
                else
                {
                    getAudioPlayer().pause();
                    paused = true;
                }
                break;
            }

            case '1':
            {
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
                break;

            }

            case '2':
            {
                getAudioPlayer().cue(64900);
                getAudioPlayer().play();
                break;
            }

            case '3':
            {
                getAudioPlayer().cue(112000);
                getAudioPlayer().play();
                break;
            }

            case '4':
            {
                getAudioPlayer().cue(162000);
                getAudioPlayer().play();
                break;
            }
            
         }
    }

    public void setup()
    {
        colorMode(HSB);
        noCursor();
        
        setFrameSize(1024);

        startMinim();
        loadAudio("heathens.mp3");

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
        
    }

    public void draw()
    {

        
        float halfH = height / 2;
        float average = 0;
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

      switch(mode)
      {
          case 1: //Eoin SECTION 1
          {
            background(0);
           /*  for(int i = 0 ; i < ab.size() ; i ++)
            {
                
                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                float f = lerpedBuffer[i] * halfH * 5.0f;
                line(i, halfH + f, i, halfH - f);                    
            } */

            for(int i = 0 ; i < ab.size() ; i ++)
            {
                
                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                float f = lerpedBuffer[i] * halfH * 2.0f;
                rect(i, halfH*1.9f + f, i, halfH - f);             
            }

            for(int i = 0 ; i < ab.size() ; i ++)
            {
                
                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                float f = lerpedBuffer[i] * halfH * 2.0f;
                rect(i, halfH*1.9f + f, i, halfH - f);             
            }




            break;
          }
          case 2: //Kieran SECTION 2
          {
            System.out.println("HELLO2");
            break;
          }
          case 3: //Aman SECTION 3
          {
            System.out.println("HELLO3");
            break;
          }
          case 4: //Ronan SECTION 4
          {
            System.out.println("HELLO4");
            break;
          }
      }
    }
}
