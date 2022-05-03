package C20391216;

import ie.tudublin.Visual;

public class Enemy extends Visual {

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    int mode = 1;
    boolean paused = true;

    public void keyPressed()
    {
        if (key >= '0' && key <= '5')
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
                getAudioPlayer().play();
                break;
            }

            case 'r':
            {
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
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

        getAudioPlayer().setGain(-25);
    }

    Eoin Eoin = new Eoin(1920);
    Kieran Kieran = new Kieran(1024);
    Ronan Ronan = new Ronan();
    Aman Aman = new Aman();
    Combined Combined = new Combined(1024);

    public void draw()
    {    
        //automate switching when the bass drops
        if(getAudioPlayer().position() >= 55700 && getAudioPlayer().position() <= 55800)
        {
            mode = 2;
        }
        if(getAudioPlayer().position() >= 92700 && getAudioPlayer().position() <= 92800)
        {
            mode = 3;
        }
        if(getAudioPlayer().position() >= 129900 && getAudioPlayer().position() <= 130000)
        {
            mode = 4;
        }

        if(getAudioPlayer().position() >= 154900 && getAudioPlayer().position() <= 155000)
        {
            mode = 5;
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

            case 5:
            {
                Combined.render(this);
                break;
            }
        }
    }
}
