import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MineField extends JPanel{

    Mine[][] field;
    ImageIcon[] imageIcons;
    GameManager game;
    int size;
    int squareSize;
    int mines;
    int width;
    int height;

    int flags = 0;
    int fieldWidth = 850;
    int fieldHeight = 850;
    int squareGapTotal = 25;


    BufferedImage hiddenMineBuffer;Image hiddenMineImage;ImageIcon hiddenIcon;
    BufferedImage flaggedMineBuffer;Image flaggedMineImage;ImageIcon flagIcon;
    BufferedImage mineBuffer;Image mineImage;ImageIcon mineIcon;
    BufferedImage zeroBuffer;Image zero;ImageIcon zeroIcon;
    BufferedImage oneBuffer;Image one;ImageIcon oneIcon;
    BufferedImage twoBuffer;Image two;ImageIcon twoIcon;
    BufferedImage threeBuffer;Image three;ImageIcon threeIcon;
    BufferedImage fourBuffer;Image four;ImageIcon fourIcon;
    BufferedImage fiveBuffer;Image five;ImageIcon fiveIcon;
    BufferedImage sixBuffer;Image six;ImageIcon sixIcon;
    BufferedImage sevenBuffer;Image seven;ImageIcon sevenIcon;
    BufferedImage eightBuffer;Image eight;ImageIcon eightIcon;

    public MineField(int width, int height,int mineCount, GameManager game){
  
        mines = mineCount;
        setBackground(Color.darkGray);
        this.width = width;
        this.height = height;
        this.game = game;
        if (width>=height){
            squareSize = fieldWidth/width;
        }else{
            squareSize = fieldHeight/height;
        }
       

        imageIcons = new ImageIcon[12];

        try {
            hiddenMineBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\hidden.png"));
            hiddenMineImage = hiddenMineBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            hiddenIcon = new ImageIcon(hiddenMineImage);
            imageIcons[9] = hiddenIcon;
            flaggedMineBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\flag.png"));
            flaggedMineImage = flaggedMineBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            flagIcon = new ImageIcon(flaggedMineImage);
            imageIcons[10] = flagIcon;
            mineBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\mine.png"));
            mineImage = mineBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            mineIcon = new ImageIcon(mineImage);
            imageIcons[11] = mineIcon;
            zeroBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\0.png"));
            zero = zeroBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            zeroIcon = new ImageIcon(zero);
            imageIcons[0] = zeroIcon;
            oneBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\1.png"));
            one = oneBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            oneIcon = new ImageIcon(one);
            imageIcons[1] = oneIcon;
            twoBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\2.png"));
            two = twoBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            twoIcon = new ImageIcon(two);
            imageIcons[2] = twoIcon;
            threeBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\3.png"));
            three = threeBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            threeIcon = new ImageIcon(three);
            imageIcons[3] = threeIcon;
            fourBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\4.png"));
            four = fourBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            fourIcon = new ImageIcon(four);
            imageIcons[4] = fourIcon;
            fiveBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\5.png"));
            five = fiveBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            fiveIcon = new ImageIcon(five);
            imageIcons[5] = fiveIcon;
            sixBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\6.png"));
            six = sixBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            sixIcon = new ImageIcon(six);
            imageIcons[6] = sixIcon;
            sevenBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\7.png"));
            seven = sevenBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            sevenIcon = new ImageIcon(seven);
            imageIcons[7] = sevenIcon;
            eightBuffer = ImageIO.read(new File("src\\"+game.getTheme()+"\\8.png"));
            eight = eightBuffer.getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
            eightIcon = new ImageIcon(eight);
            imageIcons[8] = eightIcon;

        } catch (Exception ex) {
            System.out.println(ex);
        }

        GridLayout layout = new GridLayout(width,height);
        layout.setHgap(squareGapTotal/width);
        layout.setVgap(squareGapTotal/height);

        setLayout(layout);
        field = new Mine[width][height];
        deployMineObjects();

       
        
    }

    private void deployMineObjects(){
        
        for (int i = 0;i<width;i++){
            for (int u = 0; u<height; u++){
                field[i][u] = new Mine(squareSize,squareSize,imageIcons);
                field[i][u].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        Mine source = (Mine)e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1){
                            click(source);
                        } else if (e.getButton() == MouseEvent.BUTTON3){
                            if (game.getGameStatus()=="revealing"){
                                if (source.getStatus()=="hidden"){
                                    source.setStatus("flag");
                                    flags++;
                                } else if(source.getStatus()=="flag"){
                                    source.setStatus("hidden");
                                    flags--;
                                }
                            }
                        }
                        game.update();
                        
                    }
                });
                add(field[i][u]);
            }
        }
    }

    private void deployMines(int amount){
        int minesToPlant = amount;
        Random rand = new Random();

        while (minesToPlant!=0){
            int randomRow = rand.nextInt(width);
            int randomCol = rand.nextInt(height);
            if (!field[randomRow][randomCol].isMine()&&!field[randomRow][randomCol].isProtected()){
                field[randomRow][randomCol].setMine(true);
                minesToPlant--;
            }
        }

        
        for (int i = 0;i<width;i++){
            for (int u = 0; u<height; u++){
                if (!field[i][u].isMine()){
                    int minesTouched=0;
                    ArrayList<Mine> neighbors = getNeighbors(field[i][u]);
                    for (int n = 0; n<neighbors.size();n++){
                        if (neighbors.get(n).isMine()==true){
                            minesTouched++;
                        }
                    }
                    field[i][u].setStatus(minesTouched);
                }
            }
        } 
        
    }

    public int[] findMine(Object mine){
        int[] mineLocation = new int[2];
        for (int i = 0;i<width;i++){
            for (int u = 0; u<height; u++){
                if(field[i][u] == mine){
                    mineLocation[0] = i;mineLocation[1] = u;
                    return mineLocation;
                }
            }
        }
        mineLocation[0] = -1;mineLocation[1] = -1;
        return mineLocation;
    }

    private void addMine(int row, int column, ArrayList<Mine> list){
        if (getMine(row,column)!=null){
            list.add(getMine(row,column));
        }
    }

    public Mine getMine(int row, int column){
        try{
            return field[row][column];
        }catch (Exception e){
            return null;
        }
    }

    private ArrayList<Mine> getNeighbors(Mine mine){
        ArrayList<Mine> neighborMines = new ArrayList<Mine>();
        int row;
        int column;

        row = findMine(mine)[0];
        column = findMine(mine)[1];

        addMine(row-1,column-1,neighborMines);
        addMine(row-1,column,neighborMines);
        addMine(row-1,column+1,neighborMines);
        addMine(row,column-1,neighborMines);
        addMine(row,column+1,neighborMines);
        addMine(row+1,column-1,neighborMines);
        addMine(row+1,column,neighborMines);
        addMine(row+1,column+1,neighborMines);

        return neighborMines;
    }

    private void click(Mine mine){
        if (game.getGameStatus()=="start"){
            mine.setProtected(true);
            ArrayList<Mine> sourceNeighbors = getNeighbors(mine);
            for (int i = 0;i<sourceNeighbors.size();i++){
                sourceNeighbors.get(i).setProtected(true);
            }
            game.setGameStatus("revealing");
            deployMines(mines);
            click(mine);
        }else if (game.getGameStatus()=="revealing"){
            if (!mine.isMine()&&mine.getStatus()=="hidden"){
                mine.setStatus("revealed");
                
                if (mine.getNeighborMines()==0){
                    ArrayList<Mine> minesToReveal = getNeighbors(mine);
                    for (int i = 0;i<minesToReveal.size();i++){
                        click(minesToReveal.get(i));
                    }
                }
                if (getRevealedTiles()+mines==(width*height)){
                    game.setGameStatus("over");
                    game.win();
                }
            }
            if (mine.isMine()&&mine.getStatus()=="hidden"){
                mine.setStatus("mine");

                revealMines();
                game.setGameStatus("over");
            }
        }else if(game.getGameStatus()=="over"){
            game.toMenu();
        }
        game.update();
    }

    public int getFlagCount(){
        return flags;
    }
    public void revealMines(){
        for (int i = 0;i<width;i++){
            for (int u = 0; u<height; u++){
                if(field[i][u].isMine()){
                    field[i][u].setStatus("mine");
                }
            }
        }
    }
    public int getRevealedTiles(){
        int tilesRevealed=0;
        for (int i = 0;i<width;i++){
            for (int u = 0; u<height; u++){
                if(field[i][u].getStatus()=="revealed"){
                    tilesRevealed++;
                }
            }
        }
        return tilesRevealed;
    }
}