package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Referencia: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

public class CSVReader {

    public boolean readFile(String route) {

        String liniaRead = new String();                                                // Linia que llegim del CSV
        try (BufferedReader buffer = new BufferedReader(new FileReader(route))) {       // Fem us de bufferedReader per llegir el fitxer

            while ((liniaRead = buffer.readLine()) != null) {                           // Mentres la linia que guardem no sigui null, seguim llegint
                String[] linia = liniaRead.split(",");                            // Separem el que hem llegit per comes
                System.out.println(linia[0]);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
