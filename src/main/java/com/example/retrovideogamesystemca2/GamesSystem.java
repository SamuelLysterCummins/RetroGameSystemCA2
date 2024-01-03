package com.example.retrovideogamesystemca2;

public class GamesSystem {

    private FastHash<Game> games;
    private FastHash<GamePort> gamePorts;
    private FastHash<GameMachine> gameMachines;
    private static GamesSystem instance;

    public GamesSystem(){
        this.games = new FastHash<>(10);
        this.gameMachines = new FastHash<>(10);
        this.gamePorts = new FastHash<>(10);
    }
    public static GamesSystem getInstance(){
        if(instance == null){
            instance = new GamesSystem();
        }
        return instance;
    }

    public static void setInstance(GamesSystem system) {
        GamesSystem.instance = system;
    }

    public FastHash<Game> getGames() {
        return games;
    }

    public void setGames(FastHash<Game> games) {
        this.games = games;
    }

    public FastHash<GameMachine> getGameMachines() {
        return gameMachines;
    }

    public void setGameMachines(FastHash<GameMachine> gameMachines) {
        this.gameMachines = gameMachines;
    }

    public FastHash<GamePort> getGamePorts() { return gamePorts; }

    public void setGamePorts(FastHash<GamePort> gamePorts) {this.gamePorts = gamePorts; }

    public void addGame(String gameKey, Game game){
        games.add(gameKey, game);
    }

    public Game getGame(String gameKey) {
        return games.get(gameKey);
    }

    public void addGameMachine(String gameMachineKey, GameMachine gameMachine){ gameMachines.add(gameMachineKey, gameMachine);}

    public GameMachine getGameMachine(String gameMachineKey) {
        return gameMachines.get(gameMachineKey);
    }

    public void addGamePort(String gamePortKey, GamePort gamePort) {gamePorts.add(gamePortKey, gamePort);
    }

    public GamePort getGamePort(String gamePortKey) { return gamePorts.get(gamePortKey);
    }


}
