package com.example.retrovideogamesystemca2;

import com.example.retrovideogamesystemca2.*;

public class SearchMethods {

    private GamesSystem system;
    private FastHash<GameMachine> machineHashTable;
    private FastHash<Game> gameHashTable;
    private FastHash<GamePort> gamePortHashTable;

    public SearchMethods() {
        this.system = GamesSystem.getInstance();
        this.machineHashTable = new FastHash<>(1000);
        this.gameHashTable = new FastHash<>(1000);
        this.gamePortHashTable = new FastHash<>(1000);
        initializeHashTables();
    }

    private void initializeHashTables() {
        FastHash<GameMachine> allMachines = system.getGameMachines();
        for (GameMachine machine : allMachines) {
            if (machine != null) {
                String machineKey = generateMachineKey(machine.getMachineName(), machine.getType(), machine.getMachineReleaseYear(), machine.getManufacturer());
                machineHashTable.add(machineKey, machine);
            }
        }

        FastHash<Game> allGames = system.getGames();
        for (Game game : allGames) {
            if (game != null) {
                String gameKey = generateGameKey(game.getGameName(), game.getPublisher(), game.getDeveloper(), game.getGameReleaseYear());
                gameHashTable.add(gameKey, game);
            }
        }

        GamePort[] allGamePorts = system.getAllGamePorts();
        for (GamePort gamePort : allGamePorts) {
            if (gamePort != null) {
                String gamePortKey = generateGamePortKey(gamePort.getName(), gamePort.getMachineName(), gamePort.getReleaseYear());
                gamePortHashTable.add(gamePortKey, gamePort);
            }
        }
    }

    public GameMachine searchGameMachine(String name, String type, int year, String manufacturer) {
        String key = generateMachineKey(name, type, year, manufacturer);
        return machineHashTable.get(key);
    }

    public Game searchGame(String gameTitle, String publisher, String developer, int releaseYear) {
        String key = generateGameKey(gameTitle, publisher, developer, releaseYear);
        return gameHashTable.get(key);
    }

    public GamePort searchGamePort(String gameTitle, String machineName, int releaseYear) {
        String key = generateGamePortKey(gameTitle, machineName, releaseYear);
        return gamePortHashTable.get(key);
    }

    private String generateMachineKey(String machineName, String type, int year, String manufacturer) {
        return machineName + "-" + type + "-" + year + "-" + manufacturer;
    }

    private String generateGameKey(String gameTitle, String publisher, String developer, int releaseYear) {
        return gameTitle + "-" + publisher + "-" + developer + "-" + releaseYear;
    }

    private String generateGamePortKey(String gameTitle, String machineName, int releaseYear) {
        return gameTitle + "-" + machineName + "-" + releaseYear;
    }
}
