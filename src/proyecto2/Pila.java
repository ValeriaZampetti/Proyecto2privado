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
public class Pila {

    // Estos no son mas que apuntadores pfirst y plast.
    private Nodo tope; //pfirst
    private Nodo base; //plast
    private int size;

    //constructor
    public Pila() {

        this.tope = null;
        this.base = null;
        this.size = 0;

    }

    public boolean EsVacio() {
        return tope == null;
    }

    public void Vaciar() {
        tope = null;
        base = null;
        size = 0;
    }

    public void Apilar(Object dato) { // Se puede hacer este metodo de varias formas, pasando el dato directamente o el nodo.
        Nodo nuevo = new Nodo(dato);
        if (EsVacio()) {
            tope = nuevo;
            base = nuevo;
        } else {
            nuevo.setSiguiente(tope);
            tope = nuevo;
        }
        size++;
    }

//    public void Apilar(Nodo nuevo){
//        if(EsVacio()){
//            tope = nuevo;
//            base = nuevo;
//        }
//        else{
//            nuevo.setSiguiente(tope);
//            tope = nuevo;
//        }
//        size++;
//    }
    public void Desapilar() {
        if (EsVacio()) {
            System.out.println("La pila esta vacia");
        } else if (size == 1) {
            Vaciar();
        } else {
            tope = tope.getSiguiente();
            size--;
        }
    }

    // Este metodo desapila pero te guarda la informacion del nodo para que no la pierdas.
    public Nodo Desapilar2() {
        Nodo aux = null;
        if (EsVacio()) {
            return aux;
        } else if (size == 1) {
            aux = tope; // guardamos el nodo en aux.
            Vaciar();
            return aux;
        } else {
            aux = tope;
            tope = tope.getSiguiente();
            size--;
            aux.setSiguiente(null);
            return aux;
        }

    }

    // Este metodo imprimir es recursivo.
    public String Imprimir(String imprimirPila, int contador) {
        if (!EsVacio()) {
            Nodo aux = tope;
            Desapilar();
            imprimirPila += "Nodo " + contador + ": " + aux.getElemento() + "\n";
            contador++;
            imprimirPila = Imprimir(imprimirPila, contador);
            Apilar(aux);
        }
        return imprimirPila;
    }
    
    
    public void ImprimirIterativo(){
        String imprimirPila = "";
        for (int i = 0; i < size; i++) {
            Nodo aux = tope;
            imprimirPila += aux.getElemento() + ",";
            Desapilar();
            Apilar(aux.getElemento());
        }
        System.out.println(imprimirPila);
    }
    

    // Se encarga de insertar al fondo de la pila. Este metodo cuando la pila esta vacia es que inserta la data.
    public void Submerge(Object data) {
        if (!EsVacio()) {
            Nodo aux = tope;
            Desapilar();
            Submerge(data);
            Apilar(aux.getElemento());
        } else {
            Apilar(data);
        }
    }
    
    // Para invertir una pila se hace con este metodo y con el de arriba.
    public void Reverse() {
        if (!EsVacio()) {
            Nodo aux = tope;
            Desapilar();
            Reverse();
            Submerge(aux.getElemento());
        }
    }

    /**
     * @return the tope
     */
    public Nodo getTope() {
        return tope;
    }

    /**
     * @param tope the tope to set
     */
    public void setTope(Nodo tope) {
        this.tope = tope;
    }

    /**
     * @return the base
     */
    public Nodo getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(Nodo base) {
        this.base = base;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

}
