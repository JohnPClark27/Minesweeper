public class GameManager {
    MinesweeperGUI gameGUI;

    private boolean flagging = false;
    private String gameStatus = "start";
    private String theme;
    private boolean win = false;

    public GameManager(int width, int height, int difficulty, String theme){
        this.theme = theme;
        gameGUI = new MinesweeperGUI(this, width, height, difficulty);
    }

    public String getTheme(){
        return theme;
    }

    public String getGameStatus(){
        return gameStatus;
    }
    public void setGameStatus(String status){
        gameStatus = status;
    }
    public void toggleFlag(){
        if (flagging==false){
            flagging = true;
        }else{
            flagging = false;

        }
    }
    public boolean getFlag(){
        return flagging;
    }
    public void update(){
        gameGUI.updateUI();
    }
    public void closeMinesweeper(){

    }
    
    public void toMenu(){
        
        gameGUI.closeGUI();
    }
    public void win(){
        win = true;
    }
    public boolean getWin(){
        return win;
    }
}

