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
                getAudioPlayer().cue(112000);
                getAudioPlayer().play();
            }

            case '4':
            {
                getAudioPlayer().cue(162000);
                getAudioPlayer().play();
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
