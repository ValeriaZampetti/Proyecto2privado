/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

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
    
    
    
    
    
    /**GRAFICA EL ARBOL VACIO
     *
     * @return GRAFICO PARA LA INSERCIÓN DE NODOS Y ENLACES
     * 
     */
    public Graph CrearGrafica_arbol(){
        
        System.setProperty("org.graphstream.ui", "swing");
        
        Graph graph = new SingleGraph("ARBOL RN");
        graph.setAttribute("ui.stylesheet", "graph { padding: 40px; } edge { arrow-shape: arrow; arrow-size: 20px, 4px; } node { size: 20px; fill-color: red, black; fill-mode: gradient-horizontal; text-alignment: at-right; text-padding: 3px, 2px; text-background-mode: rounded-box; text-background-color: #EB2; text-color: #222; } ");    
        
        graph.display();
        
        return graph;
    }
    
    /** CREA TODOS LOS NODOS DEL GRAFICO
     *
     * @param grafico
     * @param rz
     * @return GRAFICO LISTO CON TODOS LOS NODOS
     */
    public Graph CrearNodes(Graph grafico, NodoAB rz){
        
        if (rz == null) {
            Node a = grafico.addNode("A");
            a.setAttribute("ui.label", "NULL");
        } else {
            if (rz.getHijoIzq() != null && rz.getHijoDer() != null) {
                IndividualNode(grafico, rz);
                CrearNodes(grafico, rz.getHijoIzq());
                CrearNodes(grafico, rz.getHijoDer());
            } else if (rz.getHijoIzq() != null && rz.getHijoDer() == null) {
                IndividualNode(grafico, rz);
                CrearNodes(grafico, rz.getHijoIzq());
                
            } else if (rz.getHijoIzq() == null && rz.getHijoDer() != null) {
                IndividualNode(grafico, rz);
                CrearNodes(grafico, rz.getHijoDer());
            } else{
                IndividualNode(grafico, rz);
            }
        }     
        return grafico;        
    }

    /**
     *
     * @param grafico
     * @param rz
     * @return
     */
    public Graph CrearEdges(Graph grafico, NodoAB rz){
        
        if (rz == null) {
            Edge ab = grafico.addEdge("a-->b", "a", "b", true);
            ab.setAttribute("ui.label", "NULL");
        } else {
            if (rz.getHijoIzq() != null && rz.getHijoDer() != null) {
                IndividualEdge(grafico, rz, rz.getHijoIzq());
                IndividualEdge(grafico, rz, rz.getHijoDer());
                CrearEdges(grafico, rz.getHijoIzq());
                CrearEdges(grafico, rz.getHijoDer());
            }else if (rz.getHijoIzq() != null && rz.getHijoDer() == null) {
                IndividualEdge(grafico, rz, rz.getHijoIzq());
                CrearEdges(grafico, rz.getHijoIzq());
            }else if (rz.getHijoIzq() == null && rz.getHijoDer() != null) {
                IndividualEdge(grafico, rz, rz.getHijoDer());
                CrearEdges(grafico, rz.getHijoDer());
            }else {
                
            }  
        }
        
        return grafico;
    }

    /** CREA UN ENLACE CON EL NODO 
     *
     * @param graph
     * @param origen
     * @param destino
     * @return
     */
    public Graph IndividualEdge(Graph graph, NodoAB origen, NodoAB destino){
        graph.addEdge(""+origen.getDato()+"-->"+destino.getDato(), ""+origen.getDato(), ""+destino.getDato(), true);
        return graph;
    }
    
    /**CREA UN NODO INDIVIDUAL
     *
     * @param graph
     * @param cualquiera
     * @return EL GRAFICO CON EL NODO INSERTADO
     */
    public Graph IndividualNode(Graph graph, NodoAB cualquiera){
       Node aux = graph.addNode(""+cualquiera.getDato());
       aux.setAttribute("ui.label", cualquiera.getDato());
//       aux.setAttribute("ui.color", cualquiera.getColor()+","+cualquiera.getColor());
       return graph;
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
