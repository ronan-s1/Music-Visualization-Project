package C20391216;

import ie.tudublin.Visual;

public class Heathens extends Visual {

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        // fullScreen(P3D, SPAN);
    }

    int mode = 1;
    boolean paused = false;

    public void keyPressed()
    {
        if (key >= '0' && key <= '4')
        {
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
                getAudioPlayer().cue(119000);
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
        // noCursor();
        
        setFrameSize(1024);
        frameRate(60);

        startMinim();
        loadAudio("heathens.mp3");

        getAudioPlayer().setGain(-20);
    }

    Eoin Eoin = new Eoin(1920);
    Kieran Kieran = new Kieran(1024);
    Ronan Ronan = new Ronan();
    Aman Aman = new Aman();

    public void draw()
    {
        //automate switching when the bass drops
        if(getAudioPlayer().position() >= 64900)
        {
            mode = 2;
        }
        if(getAudioPlayer().position() >= 119000)
        {
            mode = 3;
        }
        if(getAudioPlayer().position() >= 162000)
        {
            mode = 4;
        }

        switch(mode)
        {
            case 1: //Eoin SECTION 1
            {
                Eoin.render(this);
                break;
            }
            
            case 2: //Kieran SECTION 2
            {
                Kieran.render(this);
                break;
            }

            case 3: //Aman SECTION 3
            {
                Aman.render(this);
                break;
            }

            case 4: //Ronan SECTION 4
            {
                Ronan.render(this);
                break;
            }
        }
    }
}
