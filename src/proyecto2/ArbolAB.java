/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author marin
 */
public class ArbolAB {

    private NodoAB root;

    // constructor
    public ArbolAB() {
        this.root = null;
    }

    // Primitivas
    public void Vaciar() {
        root = null;
    }

    public boolean EsVacio() {
        return root == null;
    }

    public String Preorden(NodoAB root, String cadena) {
        if (root != null) {
            cadena = cadena + root.getDato() + ",";
            cadena = Preorden(root.getHijoIzq(), cadena);
            cadena = Preorden(root.getHijoDer(), cadena);

        }
        return cadena;
    }

    public String Postorden(NodoAB root, String cadena) {
        if (root != null) {
            cadena = Postorden(root.getHijoIzq(), cadena);
            cadena = Postorden(root.getHijoDer(), cadena);
            cadena = cadena + root.getDato() + ",";

        }
        return cadena;
    }

    // ** RECORRER ARBOL DE MENOR A MAYOR
    public String Inorden(NodoAB root, String cadena) {

        if (root != null) {
            cadena = Inorden(root.getHijoIzq(), cadena);
            cadena = cadena + root.getDato() + ",";
            cadena = Inorden(root.getHijoDer(), cadena);

        }
        return cadena;

    }

    public NodoAB BuscarPadre(NodoAB root, String padre) {
        NodoAB encontrado = null;
        if (root.getDato().equalsIgnoreCase(padre)) {
            return root;
        }
        if (root.getHijoIzq() != null && encontrado == null) {
            encontrado = BuscarPadre(root.getHijoIzq(), padre);
        }

        if (root.getHijoDer() != null && encontrado == null) {
            encontrado = BuscarPadre(root.getHijoDer(), padre);
        }

        return encontrado;
    }

    public void Insertar(String hijo, String padre, String side) {
        NodoAB nodohijo = new NodoAB(hijo);
        if (this.EsVacio()) {
            this.root = nodohijo;
        } else {
            NodoAB nodopadre = BuscarPadre(this.root, padre);
            if (nodopadre == null) {
                System.out.println("el padre no existe");
            } else if (side.equalsIgnoreCase("left") & nodopadre.getHijoIzq() == null) {
                nodopadre.setHijoIzq(nodohijo);
            } else if (side.equalsIgnoreCase("right") & nodopadre.getHijoDer() == null) {
                nodopadre.setHijoDer(nodohijo);
            } else {
                System.out.println("ambos hijos estan llenos");
            }

        }

    }

    
    public void EliminarHijos(NodoAB nodo) {
        if (nodo != null) {
            nodo.setHijoIzq(null);
            nodo.setHijoDer(null);
        }

    }
    
    
    
    
    
    

    /**
     * @return the root
     */
    public NodoAB getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(NodoAB root) {
        this.root = root;
    }

}
