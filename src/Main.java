public class Main {


    public static void main(String[] args) {
        int menuInicial;
        boolean exit = false;
        Menu menu = new Menu();

        while (!exit) {
            menuInicial = menu.MenuInicial();

            switch (menuInicial) {
                case 1:
                    menu.MenuImportacio();
                    break;

                case 2:
                    menu.MenuExportacio();
                    break;

                case 3:
                    menu.MenuVisualitzacio();
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


}
