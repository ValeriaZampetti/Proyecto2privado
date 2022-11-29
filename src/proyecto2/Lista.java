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
public class Lista {
    
    private Nodo pfirst;
    private Nodo plast;
    private int size;

    // Constructor de la lista
    public Lista() {
        this.pfirst = null;
        this.plast = null;
        this.size = 0;
    }

    // Este metodo te dice si la lista esta vacia o no.
    public boolean EsVacio() {
        if (this.getPfirst() == null) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public void InsertarFinal(Object data) {

        Nodo nodo_nuevo_1 = new Nodo(data);

        if (EsVacio()) {
            setPfirst(nodo_nuevo_1);
            setPlast(nodo_nuevo_1);
        } else {
            Nodo aux = getPlast();
            aux.setSiguiente(nodo_nuevo_1);
            setPlast(nodo_nuevo_1);
        }
        setSize(getSize() + 1);
    }
    
    public void Imprimir() {
        if (!EsVacio()) {
            Nodo aux = getPfirst();
            for (int i = 0; i < getSize(); i++) {
                System.out.println(aux.getElemento() + " ");
                aux = aux.getSiguiente();
            }
        } else {
            System.out.println("La lista esta vacia");
        }
    }
    
    public String CadenaCompleta() {
        String cadena = "";
        if (!EsVacio()) {
            Nodo aux = getPfirst();
            for (int i = 0; i < getSize(); i++) {
                cadena += aux.getElemento() + " ";
                aux = aux.getSiguiente();
            }
        } else {
            System.out.println("La lista esta vacia");
        }
        
        return cadena;
    }
    
    public void EliminarInicio() {

        if (!EsVacio()) {
            setPfirst(getPfirst().getSiguiente());
            setSize(getSize() - 1);
        } else {
            System.out.println("La lista esta vacia");
        }
    }

    /**
     * @return the pfirst
     */
    public Nodo getPfirst() {
        return pfirst;
    }

    /**
     * @param pfirst the pfirst to set
     */
    public void setPfirst(Nodo pfirst) {
        this.pfirst = pfirst;
    }

    /**
     * @return the plast
     */
    public Nodo getPlast() {
        return plast;
    }

    /**
     * @param plast the plast to set
     */
    public void setPlast(Nodo plast) {
        this.plast = plast;
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
