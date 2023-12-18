public class GamePort {

    private Game originalGame;

    private GameMachine portedMachine;

    private String portDeveloper;

    private int gamePortReleaseYear;

    private String cover;

    public GamePort(Game originalGame, GameMachine portedMachine, String portDeveloper, int gamePortReleaseYear, String cover) {
        this.originalGame = originalGame;
        this.portedMachine = portedMachine;
        this.portDeveloper = portDeveloper;
        this.gamePortReleaseYear = gamePortReleaseYear;
        this.cover = cover;
    }

    public Game getOriginalGame() {
        return originalGame;
    }

    public void setOriginalGame(Game originalGame) {
        this.originalGame = originalGame;
    }

    public GameMachine getPortedMachine() {
        return portedMachine;
    }

    public void setPortedMachine(GameMachine portedMachine) {
        this.portedMachine = portedMachine;
    }

    public String getPortDeveloper() {
        return portDeveloper;
    }

    public void setPortDeveloper(String portDeveloper) {
        this.portDeveloper = portDeveloper;
    }

    public int getGamePortReleaseYear() {
        return gamePortReleaseYear;
    }

    public void setGamePortReleaseYear(int gamePortReleaseYear) {
        this.gamePortReleaseYear = gamePortReleaseYear;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


}
