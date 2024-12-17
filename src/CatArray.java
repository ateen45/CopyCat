// CatArray.java -- Alan Cuevas
/*
 * this is where all the base Cat objects will be stored...
 */

import java.io.File;
import java.util.ArrayList;

public class CatArray {
    
    // catLists
    public ArrayList<Cat> baseList = new ArrayList<Cat>();
    public ArrayList<Cat> catsList = new ArrayList<Cat>();

    public String[] moods = {"angry", "cute", "happy", "hungry", "love", "sad", "silly", "tired"};

    // constructer
    CatArray() {

        createBaseList(); // first few 
        createList(); // all cats
    }


    // iterates through "firstFew" files in each directory
    private void createBaseList() {

        int firstFew = 5;

        for (int i = 0; i < moods.length; i++) {

            for (int j = 0; j < firstFew; j++) {

                String path = String.format("img/cats/%s/%s_cat (%d).png", moods[i], moods[i], j + 1);
                Cat c = new Cat(path, moods[i]);
                baseList.add(c);
            }
        }
    }

    // iterates through all files in the directories listed
    private void createList() {

        for (int i = 0; i < moods.length; i++) {

            String directory = String.format("img/cats/%s", moods[i]);
            
            for (int j = 0; j < new File(directory).list().length; j++) {

                String path = String.format("img/cats/%s/%s_cat (%d).png", moods[i], moods[i], j + 1);
                Cat c = new Cat(path, moods[i]);
                catsList.add(c);
            }
        }
    }

}
