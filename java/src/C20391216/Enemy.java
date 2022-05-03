package C20391216;

import ie.tudublin.Visual;

public class Enemy extends Visual {

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    int mode = 'e';
    boolean paused = false;
    boolean auto = true;

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

            case 'e':
            {
                getAudioPlayer().cue(0);
                getAudioPlayer().play();
                break;
            }

            case 'k':
            {
                getAudioPlayer().cue(55700);
                getAudioPlayer().play();
                
                break;
            }

            case 'a':
            {
                getAudioPlayer().cue(92500);
                getAudioPlayer().play();
                break;
            }

            case 'r':
            {
                getAudioPlayer().cue(129800);
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
        loadAudio("enemy.mp3");

        getAudioPlayer().setGain(0);
    }

    Eoin Eoin = new Eoin(1920);
    Kieran Kieran = new Kieran(1024);
    Ronan Ronan = new Ronan();
    Aman Aman = new Aman();

    public void draw()
    {
        //automate switching when the bass drops
        // if(getAudioPlayer().position() >= 55700)
        // {
        //     mode = 'k';
        // }
        // if(getAudioPlayer().position() >= 92500)
        // {
        //     mode = 'a';
        // }
        // if(getAudioPlayer().position() >= 129800)
        // {
        //     mode = 'r';
        // }

        switch(mode)
        {
            case 'e': //Eoin SECTION 1
            {
                Eoin.render(this);
                break;
            }
            
            case 'k': //Kieran SECTION 2
            {
                Kieran.render(this);
                break;
            }

            case 'a': //Aman SECTION 3
            {
                Aman.render(this);
                break;
            }

            case 'r': //Ronan SECTION 4
            {
                Ronan.render(this);
                break;
            }
        }
    }
}
