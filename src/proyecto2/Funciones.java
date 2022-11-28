/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Funciones {

    
    public Funciones(){   
        
    }
    
    
    
    
    public ArbolAB Leer_txt(String path) {
        ArbolAB arbol = new ArbolAB();
        String line;
        String info = "";
        File file = new File(path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    if (!line.isEmpty()) {
                        info += line + "\n";
                    }
                }
                if (!"".equals(info)) {
                    String[] cadena_split = info.split("\n");
                    
//                    for (String number : number_split ) {
//                        String cadenaBuena = number.strip();
//                }

                    for (int i = 0; i < cadena_split.length; i++) {
                        String[] cadenaBuena = cadena_split[i].split(","); // Modificar que agarre caracter por caracter de la primera linea
                        
                        // Aqui las Validaciones
                        /*
                        
                        1. La funcion tiene que identificar cual notacion es (infija, postfija/polaca inversa, prefija/polaca)
                           para eso hay que validar.
                        
                        
                        2.
                        
                        
                        2. Luego insertamos en el arbol
                        
                        3. Mostrar arbol
                        */ 
                        
                        arbol.Insertar(cadenaBuena[0], cadenaBuena[1], cadenaBuena[2]);
                    }
                }
                br.close();
                JOptionPane.showMessageDialog(null, "ÉXITO AL LEER! ");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ÉRROR AL LEER! ");
        }

        return arbol;

    }
    
    
    
    
    
    
    
    
}

