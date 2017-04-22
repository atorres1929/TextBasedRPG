package Game;


import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Adam Torres on 3/31/2017.
 */
public class Game extends JFrame implements ActionListener{

    private final int TEXT_DELAY = 10;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JTextArea textDisplayArea;
    private JPanel rootContainer;

    private ExecutorService pool;

    public Game(){
        init();
        setListeners(this);
    }

    public void init(){
        setTitle("The World After");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(rootContainer);
        DefaultCaret caret = (DefaultCaret) textDisplayArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pool = Executors.newSingleThreadExecutor();
    }

    public void setListeners(ActionListener listener){
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
    }

    private final String KEY_NEW_GAME = "New Game";
    private final String KEY_LOAD_GAME = "Load Game";
    private final String KEY_SHOW_SETTINGS = "Show Settings";
    private final String KEY_SHOW_CREDITS = "Show Credits";

    public void start(){
        String title =
                "===========================================================\n" +
                "                     THE WORLD AFTER\n" +
                "===========================================================\n";
        appendString(title);
        setAction1(KEY_NEW_GAME, "New Game");
        setAction2(KEY_LOAD_GAME, "Load Game");
        setAction3(KEY_SHOW_SETTINGS, "Show Settings");
        setAction4(KEY_SHOW_CREDITS, "Show Credits");
    }

    private final String KEY_TAKE_GEAR = "Take Gear";
    public void newGame(){
        appendString("Its been 75 years since a major disease only known as the X-virus took over the Earth and things aren't the same.");
        appendString(" ",750);
        appendString("Nations destroyed each other in the panic.\n");
        appendString(" ", 750);
        appendString("Some parts of the void are filled with nuclear hot spots with dangerous creatures.\n");
        appendString(" ", 750);
        appendString("A consequence of the fact that nuclear war broke out.\n");
        appendString(" ", 750);
        appendString("You are a survivor among others just trying to live out your life in the void. You decide" +
                " you can no longer live out in the refugee camps and must head out into the vast void beyond to make a new life.\n");
        appendString(" ", 750);
        appendString("You search a nearby town and find a dead mercenary body in an old store and take his gear.\n");

        appendString("Its been 75 years since a major disease only known as the X-virus took over the Earth and things aren't the same.");
        appendString(" ",750);
        appendString("Nations destroyed each other in the panic.\n");
        appendString(" ", 750);
        appendString("Some parts of the void are filled with nuclear hot spots with dangerous creatures.\n");
        appendString(" ", 750);
        appendString("A consequence of the fact that nuclear war broke out.\n");
        appendString(" ", 750);
        appendString("You are a survivor among others just trying to live out your life in the void. You decide" +
                " you can no longer live out in the refugee camps and must head out into the vast void beyond to make a new life.\n");
        appendString(" ", 750);
        appendString("You search a nearby town and find a dead mercenary body in an old store and take his gear.\n");

        setAllActions(KEY_TAKE_GEAR, "Continue");
    }

    public void takeGear(){
        appendString("You receive - 1 Colt 1911 Pistol");
        appendString("You receive - 1 Light Vest");
        appendString("You receive - 1 Small Medkit");
        appendString("You receive - 1 Combat Knife");
    }


    public void loadGame() {
        //TODO make a function for loading a previous game
    }

    public void showSettings() {
        //TODO make a function for showing settings
    }

    public void showCredits() {
        //TODO make a function for showing credits
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch(command) {
                case KEY_NEW_GAME:
                    newGame();
                    break;
                case KEY_LOAD_GAME:
                    loadGame();
                    break;
                case KEY_SHOW_SETTINGS:
                    showSettings();
                    break;
                case KEY_SHOW_CREDITS:
                    showCredits();
                    break;
                case KEY_TAKE_GEAR:
                    takeGear();
                default:
                    throw new UnknownActionException();

            }
        } catch (UnknownActionException ex){
            ex.printStackTrace();
        }
    }









    /*
     * =================================================================================================================
     *
     *                          DO NOT EDIT BELOW THIS
     *                          DO NOT EDIT BELOW THIS
     *                          DO NOT EDIT BELOW THIS
     *
     * =================================================================================================================
     */








    public void setAllActions(String key, String text) {
        setAction1(key, text);
        setAction2(key, text);
        setAction3(key, text);
        setAction4(key, text);
    }

    public void setAction1(String key, String text) {
        button1.setText(text);
        button1.setActionCommand(key);
    }

    public void setAction2(String key, String text) {
        button2.setText(text);
        button2.setActionCommand(key);

    }

    public void setAction3(String key, String text) {
        button3.setText(text);
        button3.setActionCommand(key);

    }

    public void setAction4(String key, String text) {
        button4.setText(text);
        button4.setActionCommand(key);

    }

    @SuppressWarnings("Duplicates")
    public void appendString(String str){
        PlainDocument document = (PlainDocument) textDisplayArea.getDocument();
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < str.length(); i++) {
                    long currentTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() < currentTime + TEXT_DELAY) {
                    }
                    document.insertString(document.getLength(), ""+str.charAt(i), null);
                    if (i % TEXT_DELAY == 0) {
                        playTextSounds();
                    }
                }
                document.insertString(document.getLength(), "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
        pool.submit(thread);
    }

    @SuppressWarnings("Duplicates")
    public void appendString(String str, int delay){
        PlainDocument document = (PlainDocument) textDisplayArea.getDocument();
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < str.length(); i++) {
                    long currentTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() < currentTime + delay) {
                    }
                    document.insertString(document.getLength(), ""+str.charAt(i), null);
                    playTextSounds();
                }
                document.insertString(document.getLength(), "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
        pool.submit(thread);
    }

    public void clearText() {
        Thread thread = new Thread (() -> {
            textDisplayArea.setText("");
        });
        pool.submit(thread);
    }



    private synchronized void playTextSounds(){
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                InputStream inputStream = Game.class.getResourceAsStream("/Typing Sound.wav");
                InputStream bufferedIn = new BufferedInputStream(inputStream);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
                clip.open(audioInputStream);
                clip.start();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private class UnknownActionException extends Exception {
        public UnknownActionException() {
            super("Unknown action was pressed! Remember to pair your action with your key!");
        }
    }


}
