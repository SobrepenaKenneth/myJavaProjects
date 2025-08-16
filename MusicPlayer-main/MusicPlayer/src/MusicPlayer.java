
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer {

    private Clip clip;

    public void play(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("Audio file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        long clipTime;
        clipTime = clip.getMicrosecondPosition();
        clip.stop();

        clip.setMicrosecondPosition(clipTime);
        clip.start();

    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
