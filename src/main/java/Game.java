public class Game {

    private String gameName;

    private String publisher;

    private String description;

    private String developer;

    private String machineDevelopedFor;

    private GameMachine machine;

    private int gameReleaseYear;

    private String cover;

    private GamePort[] ports;

    private int portsCount;


    public Game(String gameName, String publisher, String description, String developer, String machineDevelopedFor, GameMachine machine, int gameReleaseYear, String cover) {
        this.gameName = gameName;
        this.publisher = publisher;
        this.description = description;
        this.developer = developer;
        this.machineDevelopedFor = machineDevelopedFor;
        this.machine = machine;
        this.gameReleaseYear = gameReleaseYear;
        this.cover = cover;
        this.ports = new GamePort[5];
        this.portsCount = 0;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getMachineDevelopedFor() {
        return machineDevelopedFor;
    }

    public void setMachineDevelopedFor(String machineDevelopedFor) {
        this.machineDevelopedFor = machineDevelopedFor;
    }

    public GameMachine getMachine() {
        return machine;
    }

    public void setMachine(GameMachine machine) {
        this.machine = machine;
    }

    public int getGameReleaseYear() {
        return gameReleaseYear;
    }

    public void setGameReleaseYear(int gameReleaseYear) {
        this.gameReleaseYear = gameReleaseYear;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public GamePort[] getPorts() {
        return ports;
    }

    public void setPorts(GamePort[] ports) {
        this.ports = ports;
    }

    public int getPortsCount() {
        return portsCount;
    }

    public void setPortsCount(int portsCount) {
        this.portsCount = portsCount;
    }
}
