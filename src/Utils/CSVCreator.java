package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

// Referencia: https://www.mkyong.com/java/how-to-export-data-to-csv-file-java/


public class CSVCreator {

    public void creaFixer(String route, List<String> valors) {      //Caldrà canviar el list string per poder enviar la estructura de dades que toqui
        try {
            FileWriter writer = new FileWriter(route);              // Objecte per escriure fitxers en un document

            for (String s: valors){                                 // Escrivim una linia per cada objecte de la estructura de dades
                writeLine(writer, valors);                          //On diu valors, anirà el objecte de Post o User
            }

            writer.flush();                                         //Tanquem el escritor perque es guardi el fitxer
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLine(Writer w, List<String> valors) throws IOException {

        boolean first = true;
        String separador = ",";                                     // Fem que separi cada camp per una coma

        StringBuilder sb = new StringBuilder();                     // Contruim la linia amb el StringBuilder
        for (String value : valors) {                               //Sera un for de objecte post o usuari, i al value li posarem un value.toString
            if (!first) {
                sb.append(separador);
            }
            sb.append(followCVSformat(value));                      // Fem que segueixi el format de CSV
            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());

    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

}
