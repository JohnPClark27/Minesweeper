import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame{
    String theme = "default";

    public MainMenuGUI(){
        int x = 500;
        int y = 50;
        int width = 425;
        int height = 300;
        setTitle("Main Menu");
        setLocation(x,y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Welcome to Minesweeper!");
        JPanel widthPanel = new JPanel();
        JLabel widthLabel = new JLabel("Width (10): ");
        JSlider widthSlider = new JSlider(8,30,10);
        widthSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                widthLabel.setText("Width ("+widthSlider.getValue()+"): ");
            }
            
        });
        JPanel heightPanel = new JPanel();
        JLabel heightLabel = new JLabel("Height (10): ");
        JSlider heightSlider = new JSlider(8,30,10);
        heightSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                heightLabel.setText("Height ("+heightSlider.getValue()+"): ");
            }
            
        });
        JPanel diffPanel = new JPanel();
        JLabel diffLabel = new JLabel("Choose percent of mines (15%): ");
        JSlider diffSlider = new JSlider(5,40, 15);
        diffSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                diffLabel.setText("Choose a percent of mines ("+diffSlider.getValue()+"%): ");
            }
        });
        JPanel themePanel = new JPanel();
        JLabel themeLabel = new JLabel("Choose a theme (default): ");
        JMenuBar themeMenu = new JMenuBar();
        JMenuItem theme1 = new JMenuItem("Default");
        theme1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                theme = "default";
                themeLabel.setText("Choose a theme (default): ");
            }
            
        });
        JMenuItem theme2 = new JMenuItem("Retro");
        theme2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                theme = "retro";
                themeLabel.setText("Choose a theme (retro): ");
            }
            
        });
        themeMenu.add(theme1);
        themeMenu.add(theme2);
        
        JButton playButton = new JButton("Click here to play!");
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new GameManager(heightSlider.getValue(),widthSlider.getValue(),diffSlider.getValue(),theme);
            }
            
        });

        add(titleLabel);
        widthPanel.add(widthLabel);
        widthPanel.add(widthSlider);
        add(widthPanel);
        heightPanel.add(heightLabel);
        heightPanel.add(heightSlider);
        add(heightPanel);
        diffPanel.add(diffLabel);
        diffPanel.add(diffSlider);
        add(diffPanel);
        themePanel.add(themeLabel);
        themePanel.add(themeMenu);
        add(themePanel);
        add(playButton);

        setVisible(true);
    }


    public static void main(String[] args){
        new MainMenuGUI();
    }
    
}