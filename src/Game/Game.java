package Game;


import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
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
    private JTextPane textDisplayArea;
    private JPanel rootContainer;

    private ExecutorService pool;

    //===================================================================
    //                      KEYS USED MORE THAN ONCE
    //===================================================================

    final String KEY_JUMP_NEXT_SYSTEM = "Jump to Next System";
    final String KEY_GOTO_CANTEEN = "Go to Canteen";
    final String KEY_GOTO_SHOP = "Go to Shop";
    final String KEY_GET_CAPTURED = "Captured";

    //===================================================================
    //                      KEYS USED MORE THAN ONCE
    //===================================================================

    public Game(){
        init();
        setListeners(this);
    }

    public void init(){
        setTitle("Text Based RPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(rootContainer);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        pool = Executors.newSingleThreadExecutor();
    }

    public void setListeners(ActionListener listener){
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
    }

    final String KEY_NEW_GAME = "New Game";
    final String KEY_LOAD_GAME = "Load Game";
    final String KEY_SHOW_SETTINGS = "Show Settings";
    final String KEY_SHOW_CREDITS = "Show Credits";

    public void start(){
        String title =
                "===================================\n" +
                "WELCOME TO TEXT BASED RPG\n" +
                "===================================\n";
        appendString(title);
        setAction1(KEY_NEW_GAME, "New Game");
        setAction2(KEY_LOAD_GAME, "Load Game");
        setAction3(KEY_SHOW_SETTINGS, "Show Settings");
        setAction4(KEY_SHOW_CREDITS, "Show Credits");
    }

    final String KEY_TAKE_ITEM = "Take Item";
    public void newGame(){
        clearText();
        setAllActions(KEY_TAKE_ITEM, "Take Item!");
        String intro =
                "You are a Rebel on the run from the Federation.\n" +
                "Your mission, is to make it back to the Rebel base.\n" +
                "There, you can report the secret information you found to the head of the Rebel Alliance.\n" +
                "It is up to you to defeat the Federation once and for all.\n" +
                "Good luck soldier.\n";
        appendString(intro);
        appendString("...", 1000);
        appendString("But wait! I forgot! You need a ship don't you!\n");
        appendString("Here! Take this!");
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

    public void takeShip() {
        appendString("You earned a ship! Now you can get off this station and get out of here!\nBut where will you go?");
        setAction1(KEY_JUMP_NEXT_SYSTEM, "Jump to the next system! We're out of here");
        setAction2(KEY_GOTO_CANTEEN, "Stay for a while and ask around about what the federation knows about you");
        setAction3(KEY_GOTO_SHOP, "Screw this ship! It's a hunk of junk! I'm going to buy some upgrades first!");
        setAction4(KEY_GET_CAPTURED, "Can't run from the Federation on an empty stomach and tired... Let's eat and take a nap!");
    }

    public void jumpNextSystem() {
        clearText();
        appendString("=======================");
        appendString("PREPARING JUMP SEQUENCE");
        appendString("=======================");
        appendString("Guns Stowed: Check", 100);
        appendString("All Hatches Secured: Check", 100);
        appendString("Jump Drive Ready: Check", 100);
        appendString("======================================");
        appendString("JUMP SEQUENCE COMPLETE || READ TO JUMP");
        appendString("======================================");
        appendString("Jumping In:");
        appendString("3", 500);
        appendString("2", 500);
        appendString("1", 500);
        appendString("JUMP!", 250);
    }

    public void enterShop() {
        appendString("You enter the market");
    }

    public void enterCanteen() {
        appendString("You enter the canteen");
    }

    public void getCaptured() {
        setAction1(KEY_NEW_GAME, "New Game");
        setAction2(KEY_LOAD_GAME, "Load Game");
        setAction3(KEY_SHOW_SETTINGS, "Settings");
        setAction4(KEY_SHOW_CREDITS, "Credits");
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
                case KEY_TAKE_ITEM:
                    takeShip();
                    break;
                case KEY_JUMP_NEXT_SYSTEM:
                    jumpNextSystem();
                    break;
                case KEY_GOTO_SHOP:
                    enterShop();
                    break;
                case KEY_GOTO_CANTEEN:
                    enterCanteen();
                    break;
                case KEY_GET_CAPTURED:
                    getCaptured();
                    break;

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








    public void setAllActions(String key, String action) {
        setAction1(key, action);
        setAction2(key, action);
        setAction3(key, action);
        setAction4(key, action);
    }

    public void setAction1(String key, String action) {
        button1.setText(action);
        button1.setActionCommand(key);
    }

    public void setAction2(String key, String action) {
        button2.setText(action);
        button2.setActionCommand(key);

    }

    public void setAction3(String key, String action) {
        button3.setText(action);
        button3.setActionCommand(key);

    }

    public void setAction4(String key, String action) {
        button4.setText(action);
        button4.setActionCommand(key);

    }

    @SuppressWarnings("Duplicates")
    public void appendString(String str){
        StyledDocument document = (StyledDocument) textDisplayArea.getDocument();
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
        StyledDocument document = (StyledDocument) textDisplayArea.getDocument();
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
