package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MediaController
{
    private static MediaPlayer mediaPlayer = null;
    public static void initializeMedia()
    {
        String path = "src/audio/music.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
    }

    public static void pause()
    {
        mediaPlayer.pause();
    }

    public static void stop()
    {
        mediaPlayer.stop();
    }

    public static void play()
    {
        mediaPlayer.play();
    }

    public static void playNewAudio(String path)
    {
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static boolean isPlaying()
    {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
}
