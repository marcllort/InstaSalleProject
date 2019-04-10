package Utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

// Referencia: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

public class CSVReader<E> {

    public ArrayList<E> readFile(String route, Boolean user) {                               // Boolean user indica true si importem un user i false si importem un post

        ArrayList<E> resultat = new ArrayList<>();
        String liniaRead = new String();                                                // Linia que llegim del CSV


        try (BufferedReader buffer = new BufferedReader(new FileReader(route))) {       // Fem us de bufferedReader per llegir el fitxer

            while ((liniaRead = buffer.readLine()) != null) {                           // Mentres la linia que guardem no sigui null, seguim llegint
                String[] linia = liniaRead.split(",");                            // Separem el que hem llegit per comes
                System.out.println(linia[0]);
                if (user) {
                    //resultat.add(new User(linia[0], Timestamp.valueOf(linia[1]), linia[2]));             //https://stackoverflow.com/questions/42170837/how-to-read-a-csv-file-into-an-array-list-in-java
                } else {
                    //resultat.add(new Post(Integer.valueOf(linia[0]),linia[1],Timestamp.valueOf(linia[2])), linia[3], linia[4]);
                }
            }

            return resultat;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
