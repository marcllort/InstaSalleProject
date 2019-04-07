package Utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Funcions {

    private Scanner input;
    private boolean follow = false;
    private String stringUsuari;

    public Funcions(){
        input = new Scanner(System.in);
    }


    // INSERCIÓ INFORMACIÓ

    public void insercioUser() {               //Cal substituir tots els String per un constructor de un tipus User

        //User usuari = new User;
        //usuari.setUsername=input.nextLine ....

        System.out.println("Nom d'usuari:");
        String nouUser = input.nextLine();
        //Comprovem que no existeixi ja el usuari

        System.out.println("Data creació:");
        int dataCreacio = input.nextInt();
        input.nextLine();


        System.out.println("Usuaris que seguirà {Y/N]:");
        String answer = input.nextLine();
        follow = (answer.equalsIgnoreCase("Y") ? true : false);


        while (follow) {
            String userToFollow = input.nextLine();
            // Mirem si ja el segueix, o no existeix el usuari
            // Afegim usuari als follow de nouUser
            System.out.println("Usuaris que seguirà {Y/N]:");
            answer = input.nextLine();
            follow = (answer.equalsIgnoreCase("Y") ? true : false);
        }

        System.out.println("Usuari afegit amb èxit!");
    }

    public void insercioPost() {           //Cal substituir tots els String per un constructor de un tipus Post
        System.out.println("Id post:");
        String idPost = input.nextLine();

        System.out.println("Data creació:");
        int dataPost = input.nextInt();
        input.nextLine();


        System.out.println("Usuari del post:");
        String userPost = input.nextLine();

        System.out.println("Localització post X:");
        double[] location = new double[2];
        location[0] = input.nextDouble();
        input.nextLine();

        System.out.println("Localització post Y:");
        location[1] = input.nextDouble();
        input.nextLine();

        System.out.println("Hashtags:");
        String hashtag = input.nextLine();

        System.out.println("Usuaris que han donat like {Y/N]:");
        String answer = input.nextLine();
        boolean liked = (answer.equalsIgnoreCase("Y") ? true : false);

        String userLiked;
        while (liked) {
            userLiked = input.nextLine();
            //Mirem si ha donat like ja, o si no existeix el usuari

            System.out.println("Usuaris que han donat like {Y/N]:");
            answer = input.nextLine();
            liked = (answer.equalsIgnoreCase("Y") ? true : false);
        }

        System.out.println("Post afegit amb èxit!");

    }


    // ELIMINACIÓ INFORMACIÓ

    public void eliminacioUser() {
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

    public void eliminacioPost() {
        try {
            System.out.println("Id post a eliminar:");
            String idPost = input.nextLine();

            System.out.println("Processant petició...");
            //Comprovem que existeixi el post

            //if(esborraPost(idPost)){
            System.out.println("El post [" + idPost + "] s'ha esborrat correctament del sistema");
            //}else{
            System.out.println("El post [" + idPost + "] NO s'ha esborrat del sistema");
            //}
        } catch (InputMismatchException e) {
            System.out.println("Escriu un id correcte!");
            eliminacioPost();
        }


    }


    // CERCA INFORMACIÓ

    public void cercaUser(boolean start) {

        if(start) {
            stringUsuari = "";
        }

        stringUsuari.concat(input.nextLine()); //cal mirar si funciona

        System.out.println("Possibles suggeriments:");
        //funcio autocompletar busca a tots usuaris, retorna un arraylist, amb el size sabrem quantes opcions tenim
        int nSuggerencies = 2;//size arraylist
        int i;
        for (i = 0; i < nSuggerencies; i++) {
            System.out.println("    " + i + ". ");//+ arraylist.getUsername
        }

        System.out.println("    " + i + ". Cap dels suggerits");
        try {
            int seleccio = input.nextInt();
            input.nextLine();
            if (seleccio != i) {
                //stringUsuari= autocompletar.getUser;
            }

            System.out.println("Carregar informació de l'usuari [" + stringUsuari + "] [Y/N]");
            String answer = input.nextLine();
            boolean carregaCerca = (answer.equalsIgnoreCase("Y") ? true : false);

            if (carregaCerca) {
                //User usuari= getInfoUser(stringUsuari);
                //mostraInformacioUser(usuari); o millor sout(usuari.toString());
            } else {
                cercaUser(false);
            }

        } catch (InputMismatchException e) {
            System.out.println("Opció incorrecte!");
            cercaUser(false);
        }


    }

    public void cercaPost() {
        try {
            System.out.println("ID del post:");
            int idPost = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Escriu un id correcte!");
            cercaPost();
        }


        //Post post = getInfoPost(idPost);
        //System.out.println(post.toString);
    }

    public void cercaHashtag() {
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

    public void cercaUbicacio() {
        try {
            System.out.println("Latitud:");
            double latitud = input.nextDouble();
            input.nextLine();

            System.out.println("Longitud:");
            double longitud = input.nextDouble();
            input.nextLine();

            System.out.println("Radi màxim:");
            int radi = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Valor en format incorrecte!");
            cercaUbicacio();
        }
        /*Arraylist de posts = getPostUbicacio(latituda,longitud)
        System.out.println("S'han trobat"+posts.size()+" post dintre el radi màxim [< "+radi+" km]");
        int i =1;
        for (Post p : posts) {
            System.out.println("[POST "+i+"]");
            System.out.println(p.toString());
            i++;
        }*/
    }

    public void cercaPersonalitzada() {
        //cal preguntar que ha de fer
    }

}
