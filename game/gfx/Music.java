package game.gfx;

import java.io.*;

import javax.sound.sampled.*;

/**
 * Write a description of class gg here.
 * 
 * @author (Eric Sy) 
 * @version (a version number or a date)
 */

import game.state.*;

public class Music implements Runnable
{
    // instance variables - replace the example below with your own
    private File file;
    private Thread thread;
    public static String currentSong;

    public Clip clip;

    boolean playing;
    boolean running;
    boolean started;

    /**
     * Constructor for objects of class Music
     */
    public Music(String currentSong)
    {
        this.currentSong = currentSong;      
        running = false;
        playing = false;
        started = false;
        thread = new Thread(this,"Background_Music");
    }

    public synchronized void start()
    {
        if(!running)
        {
            running = true; 
            thread.start();
        }
    }

    public synchronized void stop()
    {
        stopPlay();
    }

    public void init()
    {

    }

    public void run()
    {
        init();

        long currNano = System.nanoTime();
        long lastNano = currNano;
        long timer = 0;
        //long lastMillis = System.currentTimeMillis();
        double delta = 0;
        int fps = 20;
        int frames = 0;
        int updates = 0;
        double secondsPerFrame = 1_000_000_000 / fps;  

        while(running)
        {            
            currNano = System.nanoTime();
            delta += (currNano - lastNano) / secondsPerFrame;
            timer += (currNano - lastNano);
            lastNano = currNano;

            if(delta >= 1)
            {
                delta--;
                frames++;
                if(currentSong != null && !started)
                {
                    musicStart();
                }
                //checkMusic();
            }

            if(timer >= 1_000_000_000)
            {
                timer = 0;
                lastNano = currNano;
                //System.out.println("Slime AI Updates/Frames: " + frames);
                frames = 0;
            }
        }
    }

    public void doPlay(String url) {
        if(currentSong != url)
        {
            try {
                currentSong = url;
                stopPlay();
                AudioInputStream inputStream = AudioSystem
                    .getAudioInputStream(SoundLoader.loadSound(url));
                clip = AudioSystem.getClip();
                clip.open(inputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                stopPlay();
                System.err.println(e.getMessage());
            }
        }
    }

    private void stopPlay() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }

    public void checkMusic()
    {
        String song = State.getState().song;
        if(currentSong == null)
        {
            if(song != null)
            {
                if(started)
                    changeMusic(song);
                else
                {
                    currentSong = song;
                    musicStart();
                }
            }
        }
        else if(song == null || !(song.equals(currentSong)))
        {
            changeMusic(song);
        }
    }

    public void changeMusic(String musicFile)
    {        
        currentSong = musicFile;
        clip.stop();
        if(currentSong != null)
        {            
            playMusic();
        }
    }

    public void musicStart()
    {
        started = true;
        playing = true;
        doPlay(currentSong);
        //playMusic();
    }

    public void playMusic() 
    {
        if(currentSong != null)
        {
            try 
            {
                file = SoundLoader.loadSound(currentSong);
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                Thread.sleep(clip.getMicrosecondLength());                
            } catch (Exception e) 
            {
                System.err.println(e.getMessage());
            }
        }
    }
}