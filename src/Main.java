public class Main {


    public static void main(String[] args) {
        int menuInicial;
        boolean exit = false;
        Menu menu = new Menu();

        while (!exit) {
            menuInicial = menu.MenuInicial();

            switch (menuInicial) {
                case 1:
                    int importacio = menu.MenuImportacio();
                    switchImportacio(importacio);
                    String importRoute = menu.RutaFitxer();

                    menu.printaCarregantInfo();
                    long initTime = System.currentTimeMillis();
                    //Mirem si existeix fitxer i importem, posem imported a true
                    boolean imported = false;//retornara true si no hi ha cap error
                    int elements = 0;        //size del arraylist de elements
                    if (imported) {
                        long time = System.currentTimeMillis() - initTime;
                        menu.printaImportatExit(elements, (int) time);
                    } else {
                        System.out.println("Error al importar");
                    }
                    break;

                case 2:
                    int exportacio = menu.MenuExportacio();
                    switchExportacio(exportacio);

                    break;

                case 3:
                    int visualitzacio = menu.MenuVisualitzacio();
                    switchVisualitzacio(visualitzacio);
                    break;

                case 4:
                    menu.printaMenuInsercio();
                    break;

                case 5:
                    menu.printaMenuEliminacio();
                    break;

                case 6:
                    menu.printaMenuCerca();
                    break;

                case 7:
                    menu.configAutocompletar();
                    break;

                default:
                    exit = true;
                    break;

            }
        }
    }

    private static void switchImportacio(int opcio) {
        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            default:
                break;

        }
    }

    private static void switchExportacio(int opcio) {
        switch (opcio) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            default:
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
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            default:
                break;

        }
    }

}
