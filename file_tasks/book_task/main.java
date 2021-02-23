import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

    public static void main(String[] args) {

        File book = new File("voina_mir.txt");

        try(BufferedReader reader = new BufferedReader(new FileReader(book))){

            int numberOfRows = 0;
            int numberOfCommas = 0;
            int numberOfVoina = 0;
            int numberOfMir = 0;
            int numberOfWords = 0;
            ArrayList<String> words = new ArrayList<>();
            HashMap<String,Integer> wordsStats = new HashMap<>();


            while(true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }

                Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]+");
                Matcher matcher = pattern.matcher(line);
                while(matcher.find()){
                    String word = matcher.group();
                    words.add(word);
                    if(!wordsStats.containsKey(word)) {
                        wordsStats.put(word, 1);
                    }
                    int newValue = wordsStats.get(word)+1;
                    wordsStats.put(word,newValue);
                }

                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == ','){
                        numberOfCommas++;
                    }
                }
                if (line.contains("война") || line.contains("войната") || line.contains("Война") || line.contains("Войната")){
                    numberOfVoina++;
                }

                if (line.contains("мир") || line.contains("мирът") || line.contains("Мир") || line.contains("Мирът")){
                    numberOfMir++;
                }
                System.out.println(line);
                numberOfRows++;

            }

            System.out.println("------------------------------------------");
            System.out.println("1. number of rows: "+numberOfRows);
            System.out.println("2. number of words: "+words.size());
            System.out.println("3. number of commas: "+numberOfCommas);
            ArrayList<Map.Entry<String,Integer>> entries = new ArrayList<>();
            entries.addAll(wordsStats.entrySet());
            entries.sort((o1,o2) -> o2.getValue() - o1.getValue());
            System.out.println("4. most used word is: "+entries.get(0).getKey());
            if(numberOfVoina>numberOfMir){
                System.out.println("5. number of voina words are more than mir");
                System.out.println("voina words: "+numberOfVoina);
                System.out.println("mir words: "+numberOfMir);
            }else{
                System.out.println("5. number of mir words are more than voina");
                System.out.println("   voina words: "+numberOfVoina);
                System.out.println("   mir words: "+numberOfMir);
            }

            System.out.println("6. 10 most common words: ");
            for (int i = 0; i < 10; i++) {
                System.out.println("   "+entries.get(i).getKey());
            }

            for (int i = 0; i < words.size(); i++) {
                ArrayList<String> similarWords = new ArrayList<>();
                similarWords.add(words.get(i));
                for (int j = i; j < words.size(); j++) {
                    if(words.get(i).length() == words.get(j).length()){
                        similarWords.add(words.get(j));
                    }
                }
                similarWords.sort((o1,o2)->o1.length()-o2.length());
                File file = new File(words.get(i).length()+"letter words.txt");
                file.createNewFile();
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                    for(String word : similarWords){
                        writer.write(word+" ");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
