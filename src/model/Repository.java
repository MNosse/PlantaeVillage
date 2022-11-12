package model;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Repository {
    private static Repository instance;
    private boolean isFirstEnterInVillage;
    private Plant currentPlant;
    private LinkedList<String> introductionLines;
    private List<LinkedList<String>> npcsLines;
    
    private Repository() {
    }
    
    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }
    
    public void initializeAttrs() {
        Properties properties = new Properties();
        List<String> propertiesNames;
        try {
            //Initialize IsFirstEnterInVillage
            isFirstEnterInVillage = true;
            
            //Initialize CurrentPlant
            propertiesNames = Arrays.asList(
                    "/plantsProperties/angiospermae.properties",
                    "/plantsProperties/bryophyta.properties",
                    "/plantsProperties/gymnospermae.properties",
                    "/plantsProperties/pteridophyta.properties");
            properties.load(Repository.class.getResourceAsStream(propertiesNames.get(new Random().nextInt(propertiesNames.size()))));
            currentPlant = new Plant(properties.getProperty("imageKey"),
                    properties.getProperty("plantGroup"),
                    Boolean.parseBoolean(properties.getProperty("isVascular")),
                    properties.getProperty("dependentToReproduction"),
                    properties.getProperty("mainLifeCycle"),
                    Boolean.parseBoolean(properties.getProperty("hasRoots")),
                    Boolean.parseBoolean(properties.getProperty("hasStem")),
                    Boolean.parseBoolean(properties.getProperty("hasLeaves")),
                    Boolean.parseBoolean(properties.getProperty("hasSpores")),
                    Boolean.parseBoolean(properties.getProperty("hasSeed")),
                    Boolean.parseBoolean(properties.getProperty("hasFlower")),
                    Boolean.parseBoolean(properties.getProperty("hasFruit")),
                    properties.getProperty("whereFindIt"));
            
            //Initializes IntroductionLines
            introductionLines = new LinkedList<>();
            properties.clear();
            properties.load(Repository.class.getResourceAsStream("/introductionLines/introductionLines.properties"));
            for (int i = 1; i <= properties.keySet().size(); i++) {
                introductionLines.add(properties.getProperty("line"+i));
            }
            
            //Initialize NpcsLines
            npcsLines = new ArrayList<>();
            propertiesNames = Arrays.asList(
                    "/npcsLines/npc1Lines.properties",
                    "/npcsLines/npc2Lines.properties",
                    "/npcsLines/npc3Lines.properties",
                    "/npcsLines/npc4Lines.properties",
                    "/npcsLines/npc5Lines.properties",
                    "/npcsLines/npc6Lines.properties",
                    "/npcsLines/npc7Lines.properties",
                    "/npcsLines/npc8Lines.properties",
                    "/npcsLines/npc9Lines.properties",
                    "/npcsLines/npc10Lines.properties",
                    "/npcsLines/npc11Lines.properties",
                    "/npcsLines/npc12Lines.properties");
            List<Integer> indexesAlreadyUseds = new ArrayList<>();
            for (int i = 0; i < propertiesNames.size(); i++) {
                int index = -1;
                do {
                    index = new Random().nextInt(propertiesNames.size());
                } while(indexesAlreadyUseds.contains(index));
                indexesAlreadyUseds.add(index);
                properties.clear();
                properties.load(Repository.class.getResourceAsStream(propertiesNames.get(index)));
                LinkedList<String> lines = new LinkedList<>();
                for(int j = 1; j <= properties.keySet().size(); j++) {
                    lines.add(properties.getProperty("line" + j));
                }
                npcsLines.add(lines);
            }
        } catch(IOException e) {
            System.exit(0);
        }
    }
    
    public boolean isFirstEnterInVillage() {
        return isFirstEnterInVillage;
    }
    
    public void disableFirstEnterInVillage() {
        isFirstEnterInVillage = false;
    }
    
    public Plant getPlant() {
        return currentPlant;
    }
    
    public LinkedList<String> getIntroductionLines() {
        return introductionLines;
    }
    
    public LinkedList<String> getNpcLines(int index) {
        return npcsLines.get(index);
    }
}
