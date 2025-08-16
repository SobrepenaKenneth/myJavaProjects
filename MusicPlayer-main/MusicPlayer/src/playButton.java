
//import java.awt.event.*;
import javax.swing.*;

public class playButton extends JButton {

    private boolean isClicked = false;
    private final MusicPlayer player = new MusicPlayer(); // music player instance

    //Animation stuff for play button
    private final ImageIcon defaultIcon = new ImageIcon("src/assets/button1.png");
    private final ImageIcon transition1 = new ImageIcon("src/assets/button2.png");
    private final ImageIcon transition2 = new ImageIcon("src/assets/button3.png");
    private final ImageIcon transition3 = new ImageIcon("src/assets/button4.png");
    private final ImageIcon transition4 = new ImageIcon("src/assets/button5.png");
    private final ImageIcon transition5 = new ImageIcon("src/assets/button6.png");
    private final ImageIcon clickedIcon = new ImageIcon("src/assets/button7.png");

    //Animation stuff for pause button
    public playButton() {
        setIcon(defaultIcon);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBounds(0, 0, defaultIcon.getIconWidth(), defaultIcon.getIconHeight());

        addActionListener(e -> {
            Timer transitionTimer = new Timer(30, null);
            final int[] frame = {0};

            transitionTimer.addActionListener(evt -> {
                frame[0]++;
                if (!isClicked) {
                    // Play forward transition
                    switch (frame[0]) {
                        case 1 ->
                            setIcon(transition1);
                        case 2 ->
                            setIcon(transition2);
                        case 3 ->
                            setIcon(transition3);
                        case 4 ->
                            setIcon(transition4);
                        case 5 ->
                            setIcon(transition5);
                        case 6 -> {
                            setIcon(clickedIcon);
                            isClicked = true;
                            player.play("src/assets/music2.wav"); // <-- play music
                            ((Timer) evt.getSource()).stop();
                        }
                    }
                } else {
                    // Play reverse transition
                    switch (frame[0]) {
                        case 1 ->
                            setIcon(transition5);
                        case 2 ->
                            setIcon(transition4);
                        case 3 ->
                            setIcon(transition3);
                        case 4 ->
                            setIcon(transition2);
                        case 5 ->
                            setIcon(transition1);
                        case 6 -> {
                            setIcon(defaultIcon);
                            isClicked = false;
                            player.stop(); // <-- stop music
                            ((Timer) evt.getSource()).stop();
                        }
                    }
                }
            });

            transitionTimer.start();
        });
    }
}
