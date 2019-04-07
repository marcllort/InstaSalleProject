
import Model.Post;
import Model.User;

import java.util.Scanner;
import java.lang.String;

public class Menu {

    private int opcioEstrutures;
    private int opcioInicial;
    private int opcioInsericio;
    private int opcioEliminacio;
    private int opcioCerca;
    private int paraulesMemoria = 0;
    private boolean follow = false;
    private String userToFollow;
    private java.lang.String stringUsuari;
    private Scanner input;


    public Menu() {
        opcioEstrutures = 0;
        opcioInicial = 0;
        opcioInsericio = 0;
        input = new Scanner(System.in);
    }


    // MENUS

    public int MenuInicial() {
        System.out.println("INSTASALLE");
        System.out.println("Seleccioni acció a realitzar:");
        System.out.println("    1. Importar dades");
        System.out.println("    2. Exportar dades");
        System.out.println("    3. Visualitzar dades");
        System.out.println("    4. Inserir informació");
        System.out.println("    5. Esborrar informació");
        System.out.println("    6. Cercar informació");
        System.out.println("    7. Configurar Autocompletar \n");

        return llegeixOpcioInicial();
    }

    public int MenuImportacio() {
        System.out.println("Importació de l'estat de l'estructura");
        System.out.println("Quina estructura desitja importar?");
        printaEstructures();
        return llegeixOpcioEstructures();
    }

    public int MenuExportacio() {
        System.out.println("Exportació de l'estat de l'estructura");
        System.out.println("Quina estructura desitja exportar?");
        printaEstructures();
        return llegeixOpcioEstructures();
    }

    public int MenuVisualitzacio() {
        System.out.println("Visualització de l'estat de l'estructura");
        System.out.println("Quina estructura desitja visualitzar?");
        printaEstructures();
        return llegeixOpcioEstructures();
    }

    private void printaEstructures() {
        System.out.println("    1. Trie");
        System.out.println("    2. R-Tree");
        System.out.println("    3. AVL Tree");
        System.out.println("    4. Taula de Hash");
        System.out.println("    5. Graph \n");
        System.out.println("    6. Array \n");
    }

    private int llegeixOpcioInicial() {
        try {
            this.opcioInicial = input.nextInt();
            while (opcioInicial < 0 || opcioInicial > 7) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioInicial();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioInicial;
    }

    private int llegeixOpcioEstructures() {
        try {
            this.opcioEstrutures = input.nextInt();
            while (opcioEstrutures < 0 || opcioEstrutures > 6) {
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
        System.out.println("Especifiqui la ruta del fitxer a importar: \n");
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


    // VISUALITZACIÓ INFORMACIÓ

    public void printaVisualitzacio(int mode) {

        switch (mode) {
            case 1:
                System.out.println("Visualitzant Trie");
                break;

            case 2:
                System.out.println("Visualitzant R-Tree");
                break;

            case 3:
                System.out.println("Visualitzant AVL Tree");
                break;

            case 4:
                System.out.println("Visualitzant Taula Hash");
                break;

            case 5:
                System.out.println("Visualitzant Graph");
                break;

            case 6:
                System.out.println("Visualitzant Array");
                break;
        }

    }


    // INSERCIÓ INFORMACIÓ

    public void printaMenuInsercio() {
        System.out.println("Inserció d'informació");
        System.out.println("Quin tipus d'informació vol inserir?");
        System.out.println("    1. Nou Usuari");
        System.out.println("    2. Nou Post");
        switch (llegeixOpcioInsercio()) {
            case 1:
                insercioUser();
                break;

            case 2:
                insercioPost();
                break;
        }
    }

    private int llegeixOpcioInsercio() {
        try {
            this.opcioInsericio = input.nextInt();
            while (opcioInsericio < 0 || opcioInsericio > 2) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioInsercio();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioInsericio;
    }

    private void insercioUser() {
        System.out.println("Nom d'usuari:");
        String nouUser = input.nextLine();
        //Comprovem que no existeixi ja el usuari

        System.out.println("Data creació");
        int dataCreacio = input.nextInt();

        System.out.println("Usuaris que seguirà {Y/N]:");
        String answer = input.nextLine();
        follow = (answer.equalsIgnoreCase("Y"));


        while (follow) {
            userToFollow = input.nextLine();
            // Mirem si ja el segueix, o no existeix el usuari
            // Afegim usuari als follow de nouUser
            System.out.println("Usuaris que seguirà {Y/N]:");
            answer = input.nextLine();
            follow = (answer.equalsIgnoreCase("Y"));
        }

        System.out.println("Usuari afegit amb èxit!");
    }

    private void insercioPost() {
        System.out.println("Id post:");
        String idPost = input.nextLine();

        System.out.println("Data creació:");
        int dataPost = input.nextInt();

        System.out.println("Usuari del post:");
        String userPost = input.nextLine();

        System.out.println("Localització post X:");
        double[] location = new double[2];
        location[0] = input.nextDouble();
        System.out.println("Localització post Y:");
        location[1] = input.nextDouble();

        System.out.println("Hashtags:");
        String hashtag = input.nextLine();

        System.out.println("Usuaris que han donat like {Y/N]:");
        String answer = input.nextLine();
        boolean liked = (answer.equalsIgnoreCase("Y"));

        String userLiked;
        while (liked) {
            userLiked = input.nextLine();
            //Mirem si ha donat like ja, o si no existeix el usuari

            System.out.println("Usuaris que han donat like {Y/N]:");
            liked = (answer.equalsIgnoreCase("Y"));
        }

        System.out.println("Post afegit amb èxit!");

    }


    // ELIMINACIÓ INFORMACIÓ

    public void printaMenuEliminacio() {
        System.out.println("Eliminació d'informació");
        System.out.println("Quin tipus d'informació vol esborrar?");
        System.out.println("    1. Usuari");
        System.out.println("    2. Post");
        switch (llegeixOpcioEliminacio()) {
            case 1:
                eliminacioUser();
                break;

            case 2:
                eliminacioPost();
                break;
        }
    }

    private int llegeixOpcioEliminacio() {
        try {
            this.opcioEliminacio = input.nextInt();
            while (opcioEliminacio < 0 || opcioEliminacio > 2) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioEliminacio();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioEliminacio;
    }

    private void eliminacioUser() {
        System.out.println("Nom d'usuari que s'esborrarà:");
        String eliminaUser = input.nextLine();

        System.out.println("Processant petició...");
        //Comprovem que existeixi el usuari
        //Cal borrar tota la informació realcionada (posts...) menys localiotzacio posts, mirar enunciat

        //if(esborraUser(eliminaUser)){
        System.out.println("L'usuari [" + eliminaUser + "] s'ha esborrat correctament del sistema");
        //}else{
        System.out.println("L'usuari [" + eliminaUser + "] NO s'ha esborrat del sistema");
        //}

    }

    private void eliminacioPost() {
        System.out.println("Id post a eliminar:");
        String idPost = input.nextLine();

        System.out.println("Processant petició...");
        //Comprovem que existeixi el post

        //if(esborraPost(idPost)){
        System.out.println("El post [" + idPost + "] s'ha esborrat correctament del sistema");
        //}else{
        System.out.println("El post [" + idPost + "] NO s'ha esborrat del sistema");
        //}

    }


    // CERCA INFORMACIÓ

    public void printaMenuCerca() {
        System.out.println("Cerca d'informació");
        System.out.println("Quin tipus d'informació vol cercar?");
        System.out.println("    1. Usuari");
        System.out.println("    2. Post");
        System.out.println("    3. Segons hashtag");
        System.out.println("    4. Segons ubicació");
        System.out.println("    5. Personalitzada");


        switch (llegeixOpcioCerca()) {
            case 1:
                stringUsuari = "";
                cercaUser();
                break;

            case 2:
                cercaPost();
                break;

            case 3:
                cercaHashtag();
                break;

            case 4:
                cercaUbicacio();
                break;

            case 5:
                cercaPersonalitzada();
                break;
        }
    }

    private int llegeixOpcioCerca() {
        try {
            this.opcioCerca = input.nextInt();
            while (opcioCerca < 0 || opcioCerca > 2) {
                System.out.println("Opció Incorrecte! Escriu una opció correcta:");
                llegeixOpcioCerca();
            }
        } catch (Exception e) {
            System.out.println("Opció incorrecte!");
        }

        return opcioCerca;
    }

    private void cercaUser() {

        stringUsuari.concat(input.nextLine()); //cal mirar si funciona

        System.out.println("Possibles suggeriments:");
        //funcio autocompletar busca a tots usuaris, retorna un arraylist, amb el size sabrem quantes opcions tenim
        int nSuggerencies = 2;//size arraylist
        int i;
        for (i = 0; i < nSuggerencies; i++) {
            System.out.println("    " + i + ". ");//+ arraylist.getUsername
        }
        i++;
        System.out.println("    " + i + ". Cap dels suggerits");

        int seleccio = input.nextInt();
        if (seleccio != i) {
            //stringUsuari= autocompletar.getUser;
        }

        System.out.println("Carregar informació de l'usuari [" + stringUsuari + "] [Y/N]");
        String answer = input.nextLine();
        boolean carregaCerca = (answer.equalsIgnoreCase("Y"));

        if (carregaCerca) {
            //User usuari= getInfoUser(stringUsuari);
            //mostraInformacioUser(usuari); o millor sout(usuari.toString());
        } else {
            cercaUser();
        }

    }

    private void cercaPost() {
        System.out.println("ID del post:");
        int idPost = input.nextInt();
        //Post post = getInfoPost(idPost);
        //System.out.println(post.toString);
    }

    private void cercaHashtag() {
        System.out.println("Hashtag a buscar:");
        String hashtag = input.nextLine();
        /*Arraylist de posts = getPostHashtag(hashtag)
        System.out.println("S'han trobat"+posts.size()+" post amb el hashtag "+hashtag);
        int i =1;
        for (Post p : posts) {
            System.out.println("[POST "+i+"]");
            System.out.println(p.toString());
            i++;
            if(i == 6){
            break;
            }
        }*/
    }

    private void cercaUbicacio() {
        System.out.println("Latitud:");
        double latitud = input.nextDouble();
        System.out.println("Longitud:");
        double longitud = input.nextDouble();
        System.out.println("Radi màxim:");
        int radi = input.nextInt();
        /*Arraylist de posts = getPostUbicacio(latituda,longitud)
        System.out.println("S'han trobat"+posts.size()+" post dintre el radi màxim [< "+radi+" km]");
        int i =1;
        for (Post p : posts) {
            System.out.println("[POST "+i+"]");
            System.out.println(p.toString());
            i++;
        }*/
    }

    private void cercaPersonalitzada() {
        //cal preguntar que ha de fer
    }


    // CONFIGURACIO AUTOCOMPLETAR

    public void configAutocompletar() {
        System.out.println("Limitar memoria per autocompletar");
        System.out.println("Actualment el limit es troba a [" + paraulesMemoria + "] paraules");
        System.out.println("Quin vols que sigui el nou limit?");
        paraulesMemoria = input.nextInt();

        System.out.println("Processant petició");
        //canviem el nombre de paraules de la funcio autocompletar

        System.out.println("El limit de paraules s'ha actualitzat a [" + paraulesMemoria + "] paraules");

    }

}
