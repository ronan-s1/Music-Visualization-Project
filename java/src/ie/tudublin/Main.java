package ie.tudublin;

import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Main extends PApplet
{	
	Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Main());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}

	public void settings()
    {
        size(1024, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("heathens.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    }
}

