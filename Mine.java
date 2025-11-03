import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mine extends JButton{
    
    private String currentStatus;
    private int currentNeighbors;
    private boolean isMine;
    private boolean isProtected;

    //Image Files
    BufferedImage hiddenMineBuffer;Image hiddenMineImage;
    BufferedImage flaggedMineBuffer;Image flaggedMineImage;
    BufferedImage zeroBuffer;Image zero;
    BufferedImage oneBuffer;Image one;
    BufferedImage twoBuffer;Image two;
    BufferedImage threeBuffer;Image three;
    BufferedImage fourBuffer;Image four;
    BufferedImage fiveBuffer;Image five;
    BufferedImage sixBuffer;Image six;
    BufferedImage sevenBuffer;Image seven;
    BufferedImage eightBuffer;Image eight;

    ImageIcon[] imageIcons;


    public Mine(int width, int height,ImageIcon[] images){
        imageIcons = images;
        isMine = false;
        isProtected = false;

        setBackground(Color.black);
        setBorder(null);
        setMargin(new Insets(0, 0, 0, 0));
        
        setStatus("hidden");
    }

    

    public void setStatus(String status){
        currentStatus = status;
        if(currentStatus == "hidden"){
            setIcon(imageIcons[9]);
        }else if(currentStatus=="mine"){
            setIcon(imageIcons[11]);
        } else if(currentStatus =="revealed"){
            setIcon(imageIcons[currentNeighbors]);
        }else if (currentStatus == "flag"){
            setIcon(imageIcons[10]);
        }
    }

    public String getStatus(){
        return currentStatus;
    }

    public void setStatus(int neighbors){
        currentNeighbors = neighbors;
    }

    public int getNeighborMines(){
        return currentNeighbors;
    }

    public void setMine(boolean mine){
        isMine = mine;
    }
    public boolean isMine(){
        return isMine;
    }
    public boolean isProtected(){
        return isProtected;
    }
    public void setProtected(boolean value){
        isProtected = value;
    }
}
