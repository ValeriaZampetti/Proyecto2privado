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
public class NodoAB {
    
    private String dato;
    private NodoAB hijoIzq;
    private NodoAB hijoDer;
    
    // constructor 1
    public NodoAB(String dato) {
        this.dato = dato;
        this.hijoIzq = null;
        this.hijoDer = null;
    }
    
    // constructor 2
    public NodoAB() {
        this.dato = "";
        this.hijoIzq = null;
        this.hijoDer = null;

    }

    /**
     * @return the dato
     */
    public String getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(String dato) {
        this.dato = dato;
    }

    /**
     * @return the hijoIzq
     */
    public NodoAB getHijoIzq() {
        return hijoIzq;
    }

    /**
     * @param hijoIzq the hijoIzq to set
     */
    public void setHijoIzq(NodoAB hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    /**
     * @return the hijoDer
     */
    public NodoAB getHijoDer() {
        return hijoDer;
    }

    /**
     * @param hijoDer the hijoDer to set
     */
    public void setHijoDer(NodoAB hijoDer) {
        this.hijoDer = hijoDer;
    }
    
    
}
