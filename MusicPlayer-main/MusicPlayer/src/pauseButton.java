
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;

public class pauseButton extends JButton {

    private Clip clip;

    public pauseButton(Clip clip) {
        super("Pause");
        this.clip = clip;

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
            }
        });
    }
}
