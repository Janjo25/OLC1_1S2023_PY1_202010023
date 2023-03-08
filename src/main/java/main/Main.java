package main.java.main;

import main.java.gui.Interface;

public class Main {
    public static void main(String[] args) {
        /*Para iniciar JFlex, se debe agregar su carpeta al PATH.
         * Luego, se usa el comando: 'jflex -d <directorio> <archivo>'.*/

        /*Para iniciar CUP, se debe abrir una terminal en su carpeta.
         * Luego, se usa el comando: 'java -jar java-cup-11b.jar -destdir <directorio> -parser <archivo>'.*/
        new Interface();
    }
}
