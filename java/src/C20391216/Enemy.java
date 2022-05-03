package C20391216;

import ie.tudublin.Visual;

public class Enemy extends Visual {

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    int mode = 1;
    boolean paused = false;

    public void keyPressed()
    {
        if (key >= '0' && key <= '4')
        {
		    mode = key - '0';
	    }

        switch (key)
        {
            case ' ':
            {
                if (paused)
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
                getAudioPlayer().cue(120000);
                getAudioPlayer().play();
                break;
            }

            case 'r':
            {
                getAudioPlayer().cue(0);
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
        loadAudio("enemy.mp3");

        getAudioPlayer().setGain(0);
    }

    Eoin Eoin = new Eoin(1920);
    Kieran Kieran = new Kieran(1024);
    Ronan Ronan = new Ronan();
    Aman Aman = new Aman();

    public void draw()
    {
        getAudioPlayer().play();
        
        //automate switching when the bass drops
        if(getAudioPlayer().position() >= 55700 && getAudioPlayer().position() <= 55800)
        {
            mode = 2;
        }
        if(getAudioPlayer().position() >= 92500 && getAudioPlayer().position() <= 92600)
        {
            mode = 3;
        }
        if(getAudioPlayer().position() >= 129900 && getAudioPlayer().position() <= 130000)
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
