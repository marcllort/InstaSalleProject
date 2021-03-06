
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.String;

public class Menu {

    private int opcioEstrutures;
    private int opcioInicial;
    private int opcioInsericio;
    private int opcioEliminacio;
    private int opcioCerca;
    private int paraulesMemoria = 50;
    private Scanner input;


    public Menu() {
        opcioEstrutures = 0;
        opcioInicial = 0;
        opcioInsericio = 0;
        input = new Scanner(System.in);
    }


    // MENUS

    public int MenuInicial() {
        System.out.println("\n\nINSTASALLE");
        System.out.println("Seleccioni acció a realitzar:");
        System.out.println("    1. Importar dades");
        System.out.println("    2. Exportar dades");
        System.out.println("    3. Visualitzar dades");
        System.out.println("    4. Inserir informació");
        System.out.println("    5. Esborrar informació");
        System.out.println("    6. Cercar informació");
        System.out.println("    7. Configurar Autocompletar");
        System.out.println("    8. Exit \n");

        return llegeixOpcioInicial();
    }

    public int MenuImportacio() {
        System.out.println("Importació de l'estat de l'estructura");
        System.out.println("Quina estructura desitja importar?");
        printaUserPost();
        return llegeixOpcioUserPost();
    }

    public int MenuExportacio() {
        System.out.println("Exportació de l'estat de l'estructura");
        System.out.println("Quina estructura desitja exportar?");
        printaUserPost();
        return llegeixOpcioUserPost();
    }

    public int MenuVisualitzacio() {
        System.out.println("Visualització de l'estat de l'estructura");
        System.out.println("Quina estructura desitja visualitzar?");
        printaEstructures();
        return llegeixOpcioEstructures();
    }


    private void printaUserPost() {
        System.out.println("    1. Users");
        System.out.println("    2. Posts\n");
    }

    private void printaEstructures() {
        System.out.println("    1. Trie");
        System.out.println("    2. R-Tree");
        System.out.println("    3. AVL Tree");
        System.out.println("    4. Taula de Hash");
        System.out.println("    5. Graph");
        System.out.println("    6. Array \n");
    }

    private int llegeixOpcioInicial() {
        try {
            this.opcioInicial = input.nextInt();
            input.nextLine();
            while (opcioInicial < 0 || opcioInicial > 8) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioInicial();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioInicial;
    }

    private int llegeixOpcioUserPost() {
        try {
            this.opcioEstrutures = input.nextInt();
            input.nextLine();

            while (opcioEstrutures < 1 || opcioEstrutures > 2) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioUserPost();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioEstrutures;
    }

    private int llegeixOpcioEstructures() {
        try {
            this.opcioEstrutures = input.nextInt();
            input.nextLine();

            while (opcioEstrutures < 1 || opcioEstrutures > 6) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioEstructures();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioEstrutures;
    }


    // RUTA FITXER

    public String RutaFitxer() {
        System.out.println("Especifiqui la ruta del fitxer a importar/exportar: \n");
        return input.nextLine();
    }

    public void printaCarregantInfo() {
        System.out.println("Carregant informació...");
    }


    // RESULTAT OPERACIÓ

    public void printaImportatExit(int elements, int temps) {
        System.out.println("Importació realitzada amb exit");
        System.out.println(elements + " importats en " + temps + "ms");
    }

    public void printaExportatExit(int elements, int temps) {
        System.out.println("Exportació realitzada amb exit");
        System.out.println(elements + " exportats en " + temps + "ms");
    }


    // INSERCIÓ INFORMACIÓ

    public int printaMenuInsercio() {
        System.out.println("Inserció d'informació");
        System.out.println("Quin tipus d'informació vol inserir?");
        System.out.println("    1. Nou Usuari");
        System.out.println("    2. Nou Post");
        int opcio = llegeixOpcioInsercio();
        if (opcio < 0 || opcio > 2) {
            System.out.println("Opció incorrecte!");
            return -1;
        }
        return opcio;
    }

    private int llegeixOpcioInsercio() {
        try {
            this.opcioInsericio = input.nextInt();
            input.nextLine();

            while (opcioInsericio < 0 || opcioInsericio > 2) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioInsercio();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioInsericio;
    }


    // ELIMINACIÓ INFORMACIÓ

    public int printaMenuEliminacio() {
        System.out.println("Eliminació d'informació");
        System.out.println("Quin tipus d'informació vol esborrar?");
        System.out.println("    1. Usuari");
        System.out.println("    2. Post");
        int opcio = llegeixOpcioEliminacio();
        if (opcio < 0 || opcio > 2) {
            System.out.println("Opció incorrecte!");
            return -1;
        }
        return opcio;
    }

    private int llegeixOpcioEliminacio() {
        try {
            this.opcioEliminacio = input.nextInt();
            input.nextLine();

            while (opcioEliminacio < 0 || opcioEliminacio > 2) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioEliminacio();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioEliminacio;
    }


    // CERCA INFORMACIÓ

    public int printaMenuCerca() {
        System.out.println("Cerca d'informació");
        System.out.println("Quin tipus d'informació vol cercar?");
        System.out.println("    1. Usuari");
        System.out.println("    2. Post");
        System.out.println("    3. Segons hashtag");
        System.out.println("    4. Segons ubicació\n");

        int opcio = llegeixOpcioCerca();
        System.out.println(opcio);
        if (opcio < 1 || opcio > 4) {
            System.out.println("Opció incorrecte!");
            return -1;
        }
        return opcio;
    }

    private int llegeixOpcioCerca() {
        try {
            this.opcioCerca = input.nextInt();
            input.nextLine();

            while (opcioCerca < 0 || opcioCerca > 5) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioCerca();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioCerca;
    }


    // CONFIGURACIO AUTOCOMPLETAR

    public int configAutocompletar() {
        try {
            System.out.println("Limitar memoria per autocompletar");
            System.out.println("Actualment el limit es troba a [" + paraulesMemoria + "] paraules");
            System.out.println("Quin vols que sigui el nou limit?");
            paraulesMemoria = input.nextInt();
            input.nextLine();


            System.out.println("Processant petició");
            //canviem el nombre de paraules de la funcio autocompletar

            return paraulesMemoria;

        } catch (InputMismatchException e) {
            System.out.println("Valor en format incorrecte!");
            System.out.println("Valor posat per defetcte : 100");
            configAutocompletar();
            return 100;
        }

    }

}
