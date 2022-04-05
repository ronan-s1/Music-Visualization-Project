package C20391216;

import ie.tudublin.Visual;

public class Heathens extends Visual {

    public void settings()
    {
        size(800, 800, P3D);
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
                getAudioPlayer().cue(118000);
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
        
        setFrameSize(256);

        startMinim();
        loadAudio("heathens.mp3");
        
    }

    public void draw()
    {
      switch(mode)
      {
          case 1: //Eoin SECTION 1
          {
              System.out.println("HELLO1");
            break;
          }
          case 2: //Kieran SECTION 2
          {
            System.out.println("HELLO2");
            break;
          }
          case 3: //Aman SECTION 3
          {
            colorMode(RGB);
            background(0);

            //create an eye shape using this code
            stroke(255);
            strokeWeight(10);
            fill(255,0,0);
            beginShape();
            for(float i = 0; i < TWO_PI; i += 0.01f)
            {
                float r = width/2.5f;
                float x = r * cos(i);
                float y = r * pow(sin(i), 3) * 0.5f;
                vertex(x+width/2, y+height/2);
            }
            endShape();

            float total = 0;
            float amplitude = 0;
            float smoothedAmplitude = 0;
            for(int i = 0 ; i < getAudioBuffer().size() ; i ++)
            {
                total += abs(getAudioBuffer().get(i));
            }
            amplitude = total / getAudioBuffer().size();
            smoothedAmplitude = lerp(smoothedAmplitude, amplitude, 0.1f);

            //create visualizer	
            for(int i = 0; i < getAudioBuffer().size(); i++)
            {
                float radius = map(smoothedAmplitude, 0, 0.5f, 100, 500);	
                fill(0,255,0);	
                circle(width/2, height/2, radius);
            }

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
