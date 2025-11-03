import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MinesweeperGUI extends JFrame {
    
    int gridSize = 8;
    int mines = (int)((gridSize*gridSize)*0.2);

    JPanel topBar;
   
    JButton flagButton;
    JLabel totalMineLabel;


    MineField playField;

    GameManager game;

    public MinesweeperGUI(GameManager game, int w, int h, int difficulty) {
        double minesDouble = (w*h)*((double)difficulty/100.0);
        mines = (int)minesDouble;
        this.game = game;
        String title = "Minesweeper";
        int horizLoc = 10;
        int vertLoc = 10;
        int width = 1000;
        int height = 1000;

        setTitle(title);
        setLocation(horizLoc,vertLoc);
        setSize(width,height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.darkGray);
        setLayout(new FlowLayout());

        totalMineLabel = new JLabel("Total Mines: 0 Flags Placed: 0");
        
        playField = new MineField(w,h,mines, game);
        
        topBar = new JPanel(new FlowLayout());
        topBar.setBackground(Color.lightGray);
        topBar.add(totalMineLabel);

        add(topBar);
        add(playField);
        
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (game.getGameStatus()=="over"){
                    closeGUI();
                }
            }
        });
        
        setVisible(true);
    }
    public void updateUI(){
        if (game.getGameStatus()=="revealing"){
            totalMineLabel.setText("Total Mines: "+mines+" Flags Places: "+playField.getFlagCount());
        } else if(game.getGameStatus()=="over"&&!game.getWin()){
            totalMineLabel.setText("The game is now finished. Click anywhere to close.");
        } else if(game.getGameStatus()=="over"&&game.getWin()){
            totalMineLabel.setText("Congratulations, you won!");
        } 
    }
    public void closeGUI(){
        dispose();
    }

}
