package FileSearch;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File root = new File("F:\\intellij projects\\imageCopy");
        File searched = new File("copy.jpg");

        for (File file : root.listFiles()) {
            if(file.getName().equals(searched.getName())){
                System.out.println("file found");
                break;
            }
        }

    }

}
