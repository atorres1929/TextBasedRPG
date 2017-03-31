package Game;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Game(){
        init();
        setListeners();
    }

    public void init(){
        setTitle("Text Based RPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(rootContainer);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setListeners(){
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
    }

    public void start(){
        String title =
                "===================================\n" +
                "WELCOME TO TEXT BASED RPG\n" +
                "===================================\n";
        appendString(title);
    }

    public void appendString(String str){
        StyledDocument document = (StyledDocument) textDisplayArea.getDocument();
        new Thread(() -> {
            try {
                for (int i = 0; i < str.length(); i++) {
                    long currentTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() < currentTime + TEXT_DELAY) {
                    }
                    document.insertString(document.getLength(), ""+str.charAt(i), null);
                }
                document.insertString(document.getLength(), "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void action1() {
        appendString(button1.getText());
    }

    public void action2() {
        appendString(button2.getText());

    }

    public void action3() {
        appendString(button3.getText());

    }

    public void action4() {
        appendString(button4.getText());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        try {
            if (source == button1) {
                action1();
            } else if (source == button2) {
                action2();
            } else if (source == button3) {
                action3();
            } else if (source == button4) {
                action4();
            } else {
                throw new UnknownButtonException();
            }
        } catch (UnknownButtonException ex){
            ex.printStackTrace();
        }
    }

    private class UnknownButtonException extends Exception {
        public UnknownButtonException() {
            super("Unknown source was pressed! Forgot to add listener?");
        }
    }
}
