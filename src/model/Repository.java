package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Repository {
    private static Repository instance;
    private boolean isFirstEnterInVillage;
    private Plant currentPlant;
    private LinkedList<String> introductionLines;
    private List<LinkedList<String>> npcsLines;
    
    private Repository() {
    }
    
    public synchronized static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }
    
    public void initializeAttrs() {
        File file;
        File[] files;
        Properties properties = new Properties();
        try {
            //Initialize IsFirstEnterInVillage
            isFirstEnterInVillage = true;
            
            //Initialize CurrentPlant
            file = new File("./src/global/plantsProperties");
            if(file.exists() && file.isDirectory()) {
                files = file.listFiles();
                if (files != null && files.length > 0) {
                    properties.load(Files.newInputStream(files[new Random().nextInt(files.length)].toPath()));
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
                }
            }
            
            //Initializes IntroductionLines
            introductionLines = new LinkedList<>();
            properties.clear();
            properties.load(new FileInputStream("./src/global/introductionLines/introductionLines.properties"));
            for (int i = 1; i <= properties.keySet().size(); i++) {
                introductionLines.add(properties.getProperty("line"+i));
            }
            
            //Initialize NpcsLines
            npcsLines = new ArrayList<>();
            file = new File("./src/global/npcsLines");
            if(file.exists() && file.isDirectory()) {
                files = file.listFiles();
                if (files != null && files.length > 0) {
                    List<Integer> indexesAlreadyUseds = new ArrayList<>();
                    for (int i = 0; i < files.length; i++) {
                        int index = -1;
                        do {
                            index = new Random().nextInt(files.length);
                        } while(indexesAlreadyUseds.contains(index));
                        indexesAlreadyUseds.add(index);
                        file = files[index];
                        properties.clear();
                        properties.load(Files.newInputStream(file.toPath()));
                        LinkedList<String> lines = new LinkedList<>();
                        for(int j = 1; j <= properties.keySet().size(); j++) {
                            lines.add(properties.getProperty("line" + j));
                        }
                        npcsLines.add(lines);
                    }
                }
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
