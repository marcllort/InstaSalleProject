package Utils;

import Model.Post;
import Model.User;
import Structures.ArrayListt;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Funcions {

    private Scanner input;
    private boolean follow = false;
    private String stringUsuari;
    private Importador importer;

    public Funcions(Importador importer) {
        input = new Scanner(System.in);
        this.importer = importer;
    }


    // INSERCIÓ INFORMACIÓ

    public User insercioUser(int structure) {

        User usuari = new User();

        System.out.println("Nom d'usuari:");
        String nouUser = input.nextLine();

        boolean exists = checkUserExists(nouUser, structure);                                                                   //Comprovem que no existeixi ja el usuari

        if (!exists) {
            usuari.setUsername(nouUser);

            System.out.println("Data creació: (yyyy-mm-dd)");
            usuari.setCreation(Timestamp.valueOf(input.nextLine() + " 00:00:00"));                                              // Per defecte posem hora a les 12 de la nit


            System.out.println("Usuaris que seguirà {Y/N]:");
            String answer = input.nextLine();
            follow = (answer.equalsIgnoreCase("Y") ? true : false);

            while (follow) {
                String userToFollow = input.nextLine();
                exists = checkUserExists(userToFollow, structure);                                                               // Comprovem que no existeixi ja el usuari
                if (exists) {
                    boolean followed = usuari.getTo_follow().contains(userToFollow) ? true : false;                              // Mirem si ja el segueix, o no existeix el usuari
                    if (followed) {
                        System.out.println("Usuari " + userToFollow + " ja seguit!");
                    } else {
                        usuari.addFollwing(userToFollow);
                    }
                }
                // Afegim usuari als follow de nouUser
                System.out.println("Usuaris que seguirà {Y/N]:");
                answer = input.nextLine();
                follow = (answer.equalsIgnoreCase("Y") ? true : false);
            }

            return usuari;                                                                                                      // Retornem el usuari, per afegir-lo a la estructura que toqui

        } else {
            System.out.println("Usuari ja existeix!");
            return null;
        }
    }

    private boolean checkUserExists(String nouUser, int structure) {
        User usuari = new User();
        switch (structure) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                usuari = (User) importer.tree.search(nouUser.hashCode());
                return usuari != null;

            case 4:
                break;

            case 5:
                break;

            case 6:
                 usuari = (User)importer.arrayUsers.searchUser(nouUser);
                return usuari != null;

        }
        return false;
    }

    public Post insercioPost(int structure) {

        Post post = new Post();

        System.out.println("Id post:");
        int idPost = input.nextInt();
        input.nextLine();
        boolean exists = checkPostExists(idPost, structure);                                                                    // Comprovem que no existeixi ja el post
        if (!exists) {
            post.setId(Integer.valueOf(idPost));

            System.out.println("Data creació:");
            post.setPublished_when(Timestamp.valueOf(input.nextLine() + " 00:00:00"));                                          // Per defecte posem hora a les 12 de la nit

            System.out.println("Usuari del post:");
            String userPost = input.nextLine();
            boolean existsUser = checkUserExists(userPost, structure);                                                          // Comprovem que no existeixi ja el usuari

            if (existsUser) {
                post.setPublished_by(userPost);

                System.out.println("Localització post X:");
                Double[] location = new Double[2];
                location[0] = input.nextDouble();
                input.nextLine();

                System.out.println("Localització post Y:");
                location[1] = input.nextDouble();
                input.nextLine();

                post.setLocation(location);

                System.out.println("Hashtags {Y/N]:");
                String answer = input.nextLine();
                boolean yes = (answer.equalsIgnoreCase("Y") ? true : false);

                while (yes) {
                    String hashtag = input.nextLine();
                    boolean added = post.getHashtags().contains(hashtag) ? true : false;                                        // Mirem si el hashtag ja esta al array de hashtags

                    if (added) {
                        System.out.println("Hashtag: " + hashtag + " ja afegit a la llista de hashtags!");
                    } else {
                        post.addHashtag(hashtag);
                    }

                    System.out.println("Hashtags {Y/N]:");
                    answer = input.nextLine();
                    yes = (answer.equalsIgnoreCase("Y") ? true : false);
                }


                System.out.println("Usuaris que han donat like {Y/N]:");
                answer = input.nextLine();
                boolean liked = (answer.equalsIgnoreCase("Y") ? true : false);

                String userLiked;
                while (liked) {
                    userLiked = input.nextLine();
                    existsUser = checkUserExists(userLiked, structure);                                                         // Comprovem que no existeixi ja el usuari
                    if (existsUser) {
                        boolean likedby = post.getLiked_by().contains(userLiked) ? true : false;                                //Mirem si ha donat like ja, o si no existeix el usuari
                        if (likedby) {
                            System.out.println("Usuari: " + userLiked + " ja ha donat like!");
                        } else {
                            post.addLike(userLiked);
                        }
                    } else {
                        System.out.println("Usuari: " + userLiked + " no existeix!");
                    }
                    System.out.println("Usuaris que han donat like {Y/N]:");
                    answer = input.nextLine();
                    liked = (answer.equalsIgnoreCase("Y") ? true : false);
                }

                return post;
            } else {
                System.out.println("Usuari inexistent!");
                return null;
            }
        } else {
            System.out.println("Post ja existeix!");
            return null;
        }
    }

    private boolean checkPostExists(int postId, int structure) {
        switch (structure) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                Post post = (Post) importer.tree.search(postId);                                                                 // Si fos un string, ja li farem un hash, cal mirar a quin arbre hu busquem
                return post != null;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;
        }
        return false;
    }

    // ELIMINACIÓ INFORMACIÓ

    public void eliminacioUser(int opcio) {

        boolean borrat = false;

        System.out.println("Nom d'usuari que s'esborrarà:");
        String eliminaUser = input.nextLine();

        System.out.println("Processant petició...");

        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                borrat = importer.tree.deleteElement(eliminaUser.hashCode());
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;
        }
        //Cal borrar tota la informació realcionada (posts...) menys localitzacio posts, mirar enunciat-----------------

        if (borrat) {
            System.out.println("L'usuari [" + eliminaUser + "] s'ha esborrat correctament del sistema");
        } else {
            System.out.println("L'usuari [" + eliminaUser + "] NO s'ha esborrat del sistema");
        }

    }

    public void eliminacioPost(int opcio) {

        boolean borrat = false;

        System.out.println("Id post a eliminar:");
        int idPost = input.nextInt();
        input.nextLine();

        System.out.println("Processant petició...");

        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                borrat = importer.tree.deleteElement(idPost);                                                           // cal completar,a fegir a quin arbre?----------------
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;
        }

        if (borrat) {
            System.out.println("El post [" + idPost + "] s'ha esborrat correctament del sistema");
        } else {
            System.out.println("El post [" + idPost + "] NO s'ha esborrat del sistema");
        }

    }


    // CERCA INFORMACIÓ

    public void cercaUser(boolean start, int opcio) {

        if (start) {
            stringUsuari = "";                                                                                                  // Iniciem el string de autocompletar buit al principi
        }
        String addUser = input.nextLine();
        stringUsuari = stringUsuari.concat(addUser); //cal mirar si funciona

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
                User usuari = buscaUserEstructura(opcio);                                                                       // Busquem el usuari, i mostrem la seva info
                if (usuari != null) usuari.mostraInformacioUser();
            } else {
                cercaUser(false, opcio);
            }

        } catch (InputMismatchException e) {
            System.out.println("Opció incorrecte!");
            cercaUser(false, opcio);
        }

    }

    private User buscaUserEstructura(int opcio) {
        User user = new User();
        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                user = (User) importer.tree.search(stringUsuari.hashCode());
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;
        }
        return user;
    }

    public void cercaPost(int opcio) {
        try {
            System.out.println("ID del post:");
            int idPost = input.nextInt();
            input.nextLine();
            Post post = buscaPostEstructura(idPost, opcio);
            if (post != null) post.mostraInformacioPost();

        } catch (InputMismatchException e) {
            System.out.println("Escriu un id correcte!");
            cercaPost(opcio);
        }

    }

    private Post buscaPostEstructura(int idPost, int opcio) {
        Post post = new Post();
        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                post = (Post) importer.tree.search(idPost);
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;
        }
        return post;
    }

    public void cercaHashtag(int opcio) {
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

    public void cercaUbicacio(int opcio) {
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
            cercaUbicacio(opcio);
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

    public void cercaPersonalitzada(int opcio) {
        //cal preguntar que ha de fer
    }

}
