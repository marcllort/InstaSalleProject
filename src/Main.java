
import Model.Post;
import Model.User;
import Structures.Helpers.LeafNode;
import Structures.RTree;
import Utils.Funcions;
import Utils.Importador;


public class Main {

    public static Importador importer = new Importador();
    private static Funcions funcio = new Funcions(importer);


    public static void main(String[] args) {
        int menuInicial;
        boolean exit = false;
        Menu menu = new Menu();

        while (!exit) {
            menuInicial = menu.MenuInicial();

            switch (menuInicial) {
                case 1:
                    int userpost = menu.MenuImportacio();
                    String importRoute = menu.RutaFitxer();
                    switchImportacio(userpost, importRoute, menu);

                    break;

                case 2:
                    int exportacio = menu.MenuExportacio();
                    String exportRoute = menu.RutaFitxer();
                    switchExportacio(exportacio, exportRoute);

                    menu.printaCarregantInfo();
                    long initTime1 = System.currentTimeMillis();

                    //Mirem si existeix fitxer i importem, posem exported a true
                    boolean exported = false;//retornara true si no hi ha cap error
                    int elements1 = 0;        //size del arraylist de elements
                    if (exported) {
                        long time = System.currentTimeMillis() - initTime1;
                        menu.printaExportatExit(elements1, (int) time);
                    } else {
                        System.out.println("Error al exportar");
                    }
                    break;

                case 3:
                    int visualitzacio = menu.MenuVisualitzacio();
                    switchVisualitzacio(visualitzacio);
                    break;

                case 4:
                    int insercio = menu.printaMenuInsercio();
                    switchInsercio(insercio);
                    break;

                case 5:
                    int eliminacio = menu.printaMenuEliminacio();
                    switchEliminacio(eliminacio);
                    break;

                case 6:
                    int cerca = menu.printaMenuCerca();
                    switchCerca(cerca);
                    break;

                case 7:
                    int limit = menu.configAutocompletar();
                    funcio.actualitzaTries(limit);
                    break;

                default:
                    exit = true;
                    break;

            }
        }
    }


    private static void switchImportacio(int opcio, String importRoute, Menu menu) {
        long initTime = System.currentTimeMillis();
        int elements = 0;                                                                                                       //  Nombre de elements importats

        switch (opcio) {
            case 1:
                elements = importer.importDataUser(importRoute);
                importer.ArrayListImporter(opcio);
                importer.TriesImporter();
                importer.GraphListImporter();
                importer.trieImporter();

                break;

            case 2:
                elements = importer.importDataPost(importRoute);
                importer.RTreeImporter();
                //importer.ArrayListImporter(opcio);
                importer.HashTableImporter();
                importer.AVLImporter();


                break;

            default:
                System.out.println("Error de opcio! (SwitchImportacio)");
                break;
        }

        menu.printaCarregantInfo();
        boolean imported = elements != -1;                                                                                      // Si retorna -1 elements, vol dir que no s'ha importat

        if (imported) {
            long time = System.currentTimeMillis() - initTime;
            menu.printaImportatExit(elements, (int) time);
        } else {
            System.out.println("Error al importar");
        }

    }

    private static void switchExportacio(int opcio, String exportRoute) {
        switch (opcio) {
            case 1:
                importer.exportUsers(exportRoute);
                break;

            case 2:
                importer.exportPosts(exportRoute);
                break;

            default:
                System.out.println("Error de opcio! (SwitchExportacio)");
                break;

        }
    }

    private static void switchVisualitzacio(int opcio) {
        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                importer.tree.inOrder(importer.tree.root, 0);
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            default:
                System.out.println("Error de opcio! (SwitchVisualitzacio)");
                break;

        }
    }

    private static void switchInsercio(int insercio) {
        switch (insercio) {
            case 1:
                User user = funcio.insercioUser();
                if (user != null) {
                    // insert a graph, tries i arraylist
                }
                break;

            case 2:
                Post post = funcio.insercioPost();
                if (post != null) {
                    importer.tree.addElement(post.getId(), post, post.getPublished_by());

                    LeafNode leafNode = new LeafNode(post);
                    importer.rTree.insertPost(leafNode);
                    // insert a rtree, hashtable i arraylist
                }
                break;

            default:
                System.out.println("Error de opcio! (SwitchInsercio)");
                break;

        }
    }

    private static void switchEliminacio(int eliminacio) {
        switch (eliminacio) {
            case 1:
                funcio.eliminacioUser();
                break;

            case 2:
                funcio.eliminacioPost();
                break;

            default:
                System.out.println("Error de opcio! (SwitchEliminacio)");
                break;

        }
    }

    private static void switchCerca(int cerca) {
        switch (cerca) {
            case 1:
                funcio.cercaUser(true);
                break;

            case 2:
                funcio.cercaPost();
                break;

            case 3:
                funcio.cercaHashtag();
                break;

            case 4:
                funcio.cercaUbicacio();
                break;

            default:
                System.out.println("Error de opcio! (SwitchCerca)  Opcio:" + cerca);
                break;


        }

    }

}
