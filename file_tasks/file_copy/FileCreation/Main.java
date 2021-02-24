package FileCreation;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        File original = new File("picture.jpg");
        File copy = new File("copy.jpg");
        try {
            if(!copy.exists()) {
                copy.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(
                FileInputStream fis = new FileInputStream(original);
                FileOutputStream fos = new FileOutputStream(copy);
        ){
            int b  = 0;
            while(b!=-1){
                b = fis.read();
                if(b==-1){
                    break;
                }
                fos.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
