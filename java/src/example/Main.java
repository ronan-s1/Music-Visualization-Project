package example;

public class Main {
    public static void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new RotatingAudioBands());
    }

	public static void main(String[] args)
	{
		// Main main = new Main();
		// main.startUI();
        startUI();		
	}
}
