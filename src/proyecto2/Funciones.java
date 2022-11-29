/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Funciones {

    public Funciones() {

    }

//    public ArbolAB Leer_txt(String path) {
//        ArbolAB arbol = new ArbolAB();
//        String line;
//        String info = "";
//        File file = new File(path);
//
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            } else {
//                FileReader fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);
//                while ((line = br.readLine()) != null) {
//                    if (!line.isEmpty()) {
//                        info += line + "\n";
//                    }
//                }
//                if (!"".equals(info)) {
//                    String[] cadena_split = info.split("\n");
//
////                    for (String number : number_split ) {
////                        String cadenaBuena = number.strip();
////                }
//                    for (int i = 0; i < cadena_split.length; i++) {
//                        String[] cadenaBuena = cadena_split[i].split(","); // Modificar que agarre caracter por caracter de la primera linea
//                        System.out.println(cadenaBuena);
//                        // Aqui las Validaciones
//                        /*
//                        
//                        1. La funcion tiene que identificar cual notacion es (infija, postfija/polaca inversa, prefija/polaca)
//                           para eso hay que validar.
//                        
//                        
//                        2.
//                        
//                        
//                        2. Luego insertamos en el arbol
//                        
//                        3. Mostrar arbol
//                         */
//
////                        arbol.Insertar(cadenaBuena[0], cadenaBuena[1], cadenaBuena[2]);
//                    }
//                }
//                br.close();
//                JOptionPane.showMessageDialog(null, "ÉXITO AL LEER! ");
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "ÉRROR AL LEER! ");
//        }
//
//        return arbol; // retorna un array
//
//    }

//    public boolean isAlphaNumeric(String str) {
//        boolean result = str.matches("^[a-zA-Z0-9]*$");
//        if (result == true) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public String[] Leer_array(String path) {
//        ArbolABB arbol = new ArbolABB();
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
//                    String[] clone_cadenaBuena = {"null"};
                    String[] cadena_split = info.split("\n");
                    if (cadena_split.length > 1) {
                        JOptionPane.showMessageDialog(null, "ERROR EL ARCHIVO DE TEXTO DEBE CONTENER SOLO 1 LINEA");
                    } else {
                        JOptionPane.showMessageDialog(null, "ÉXITO AL LEER! ");
                        //String[] aritmeticos = cadena_split[0].split("");
                        return cadena_split;

                    }
                }

                br.close();
//                JOptionPane.showMessageDialog(null, "ÉXITO AL LEER! ");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ÉRROR AL LEER! "+e.getMessage());
        }
        String[] arreglovacio = new String[1];
        return arreglovacio;
    }

    public Lista TipoDeNotacion(String[] cadena_split) {
        int contador = 0;
        Lista lista = new Lista();
        for (int i = 0; i < cadena_split.length; i++) {
//            System.out.println(cadena_split[i]);
            String[] caracteres = cadena_split[i].split("");
            for (int j = 0; j < caracteres.length; j++) {
                String ultimo = caracteres[caracteres.length - 1];
//                System.out.println(caracteres[j]);

//                System.out.println(ultimo);
                if (caracteres[0].equalsIgnoreCase("/") || caracteres[0].equalsIgnoreCase("+") || caracteres[0].equalsIgnoreCase("-") || caracteres[0].equalsIgnoreCase("*")) {
                    lista.InsertarFinal(caracteres[j]);
                    if (caracteres[j].equalsIgnoreCase(ultimo)) {
                        JOptionPane.showMessageDialog(null, "LA NOTACION ES POLACA");
                        return lista;
                    }
                } else if (ultimo.equalsIgnoreCase("/") || ultimo.equalsIgnoreCase("+") || ultimo.equalsIgnoreCase("-") || ultimo.equalsIgnoreCase("*")) {
                    lista.InsertarFinal(caracteres[j]);
                    if (caracteres[j].equalsIgnoreCase(ultimo)) {
                        JOptionPane.showMessageDialog(null, "LA NOTACION ES POLACA INVERSA");
                        return lista;
                    }
//                    return lista;
                } else if (caracteres[0].equalsIgnoreCase("(") || caracteres[0].equalsIgnoreCase("1") || caracteres[0].equalsIgnoreCase("2") || caracteres[0].equalsIgnoreCase("3") || caracteres[0].equalsIgnoreCase("4") || caracteres[0].equalsIgnoreCase("5") || caracteres[0].equalsIgnoreCase("6") || caracteres[0].equalsIgnoreCase("7") || caracteres[0].equalsIgnoreCase("8") || caracteres[0].equalsIgnoreCase("9")) {
                    contador++;
                    lista.InsertarFinal(caracteres[j]);
                    if (caracteres.length == contador) {
                        JOptionPane.showMessageDialog(null, "LA NOTACION ES INFIJA");
//                        lista.Imprimir();
                        return lista;

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "NO ES NINGUNA NOTACION");

                }

            }
        }
        return null;
    }

    //metodo para validar // **
    public int Precedencia(String operador) {
        switch (operador) {
            case "^":
                return 3;
            case "/":
                return 2;
            case "*":
                return 2;
            case "+":
                return 1;
            case "-":
                return 1;
            default:
                //JOptionPane.showMessageDialog(null, "Sucedió un error");
                return -1;
        }
    }

    //conversion de infijo a postfijo
    public Lista TraduccionInfijaApost(Lista cadena) {
        Pila pila = new Pila();
        Lista listaSalida = new Lista();
        boolean error = false;

        while (!cadena.EsVacio() && !error) {
        //String primerTermino = (String) pila.Desapilar2().getElemento();
            String primerTermino = (String) cadena.getPfirst().getElemento();
            switch (primerTermino) {
                case "0":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "1":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "2":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "3":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "4":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "5":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "6":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "7":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "8":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "9":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "x":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "y":
                    listaSalida.InsertarFinal(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "(":
                    pila.Apilar(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case ")":
                    while (!pila.EsVacio() &&!"(".equals((String) pila.getTope().getElemento())) {
                        String elementoPila = (String) pila.Desapilar2().getElemento();
                        listaSalida.InsertarFinal(elementoPila);
                        if (pila.getTope() != null) 
                        {
                            if ("(".equals((String) pila.getTope().getElemento())) 
                            {
                                pila.Desapilar();
                            }
                        }                        
                    }
                    //String parentesisIzq = primerTermino;
                    cadena.EliminarInicio();

                    break;
                case "+":
                    while (!pila.EsVacio() && Precedencia((String) pila.getTope().getElemento()) >= Precedencia(primerTermino)) {
                        String elementoPila = (String) pila.Desapilar2().getElemento();
                        listaSalida.InsertarFinal(elementoPila);
                    }
                    pila.Apilar(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "-":
                    while (!pila.EsVacio() && Precedencia((String) pila.getTope().getElemento()) >= Precedencia(primerTermino)) {
                        String elementoPila = (String) pila.Desapilar2().getElemento();
                        listaSalida.InsertarFinal(elementoPila);
                    }
                    pila.Apilar(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "*":
                    while (!pila.EsVacio() && Precedencia((String) pila.getTope().getElemento()) >= Precedencia(primerTermino)) {
                        String elementoPila = (String) pila.Desapilar2().getElemento();
                        listaSalida.InsertarFinal(elementoPila);
                    }
                    pila.Apilar(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "/":
                    while (!pila.EsVacio() && Precedencia((String) pila.getTope().getElemento()) >= Precedencia(primerTermino)) {
                        String elementoPila = (String) pila.Desapilar2().getElemento();
                        listaSalida.InsertarFinal(elementoPila);
                    }
                    pila.Apilar(primerTermino);
                    cadena.EliminarInicio();
                    break;
                case "^":
                    while (!pila.EsVacio() && Precedencia((String) pila.getTope().getElemento()) >= Precedencia(primerTermino)) {
                        String elementoPila = (String) pila.Desapilar2().getElemento();
                        listaSalida.InsertarFinal(elementoPila);
                    }
                    pila.Apilar(primerTermino);
                    cadena.EliminarInicio();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Sucedió un error");
                    return listaSalida;
            }
        }

        while (!pila.EsVacio()) {
            String elementoPila = (String) pila.Desapilar2().getElemento();
            listaSalida.InsertarFinal(elementoPila);
        }

        pila.Vaciar();

        return listaSalida;
    }

    
    public ArbolAB TraduccionPostArbol(Lista listaPostFija)
    {
        Pila pila = new Pila();
        ArbolAB arbol = new ArbolAB();
        boolean error = false;
        
        while(!listaPostFija.EsVacio() && !error)
        {
            String primerTermino = (String) listaPostFija.getPfirst().getElemento();
            
            switch(primerTermino)
            {
                case "0":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "1":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "2":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "3":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "4":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "5":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "6":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "7":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "8":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "9":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "x":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "y":
                    pila.Apilar(primerTermino);
                    listaPostFija.EliminarInicio();
                    break;
                case "(":
                    error = true;
                    listaPostFija.EliminarInicio();
                    break;
                case "/":
                    if (pila.getSize() < 2) 
                    {
                        error = true;
                    }else
                    {
                        String a1 = (String) pila.Desapilar2().getElemento();
                        String a2 = (String) pila.Desapilar2().getElemento();
                        
                        ArbolAB arbolExpresion = new ArbolAB();
                        arbolExpresion.Insertar(a1, primerTermino, "left");
                        arbolExpresion.Insertar(a2, primerTermino, "right");
                        
                        arbol.setRoot(arbolExpresion.getRoot());
                    }
                    listaPostFija.EliminarInicio();
                    break;
                case "*":
                    if (pila.getSize() < 2) 
                    {
                        error = true;
                    }else
                    {
                        String a1 = (String) pila.Desapilar2().getElemento();
                        String a2 = (String) pila.Desapilar2().getElemento();
                        
                        ArbolAB arbolExpresion = new ArbolAB();
                        arbolExpresion.Insertar(a1, primerTermino, "left");
                        arbolExpresion.Insertar(a2, primerTermino, "right");
                        
                        arbol.setRoot(arbolExpresion.getRoot());
                    }
                    listaPostFija.EliminarInicio();
                    break;
                case "+":
                    if (pila.getSize() < 2) 
                    {
                        error = true;
                    }else
                    {
                        String a1 = (String) pila.Desapilar2().getElemento();
                        String a2 = (String) pila.Desapilar2().getElemento();
                        
                        ArbolAB arbolExpresion = new ArbolAB();
                        arbolExpresion.Insertar(a1, primerTermino, "left");
                        arbolExpresion.Insertar(a2, primerTermino, "right");
                        
                        arbol.setRoot(arbolExpresion.getRoot());
                    }
                    listaPostFija.EliminarInicio();
                    break;
                case "-":
                    if (pila.getSize() < 2) 
                    {
                        error = true;
                    }else
                    {
                        String a1 = (String) pila.Desapilar2().getElemento();
                        String a2 = (String) pila.Desapilar2().getElemento();
                        
                        ArbolAB arbolExpresion = new ArbolAB();
                        arbolExpresion.Insertar(a1, primerTermino, "left");
                        arbolExpresion.Insertar(a2, primerTermino, "right");
                        
                        arbol.setRoot(arbolExpresion.getRoot());
                    }
                    listaPostFija.EliminarInicio();
                    break;                    
                case "^":
                    if (pila.getSize() < 2) 
                    {
                        error = true;
                    }else
                    {
                        String a1 = (String) pila.Desapilar2().getElemento();
                        String a2 = (String) pila.Desapilar2().getElemento();
                        
                        ArbolAB arbolExpresion = new ArbolAB();
                        arbolExpresion.Insertar(primerTermino, null, null);
                        arbolExpresion.Insertar(a1, primerTermino, "left");
                        arbolExpresion.Insertar(a2, primerTermino, "right");
                        
                        arbol.setRoot(arbolExpresion.getRoot());
                    }
                    listaPostFija.EliminarInicio();
                    break;
                default:
                    listaPostFija.EliminarInicio();
                    System.out.println("Error");
                    break;
            }
        }
        
        if (pila.EsVacio() || pila.getSize() > 1) 
        {
            error = true;
        }
//        else
//        {
//            var elementoPila = (ArbolAB)pila.Desapilar2().getElemento();
//            
//            if (elementoPila.EsVacio())
//            {
//                arbol.Insertar(elementoPila.getRoot().getDato(), null, null);
//            }
//            
//            return arbol;
//        }
        
        if (error)
        {
            pila.Vaciar();
        }
        return arbol;
    }
}
