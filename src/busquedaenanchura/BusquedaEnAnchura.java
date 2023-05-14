/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaenanchura;

/**
 *
 * @author LoreyFaby
 */

import java.util.*;

class Nodo {
    String valor;
    List<Nodo> hijos;

    public Nodo(String valor) {
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }
}

public class BusquedaEnAnchura {
    public static void main(String[] args) {
        Nodo raiz = construirArbol();
        List<List<Nodo>> rutas = buscarRutas(raiz);
        mostrarRutas(rutas);
    }

    private static Nodo construirArbol() {
        Nodo raiz = new Nodo("B");
        Nodo b1 = new Nodo("B1");
        Nodo b2 = new Nodo("B2");
        Nodo b3 = new Nodo("B3");
        Nodo b4 = new Nodo("B4");
        Nodo b5 = new Nodo("B5");
        Nodo b6 = new Nodo("B6");
        Nodo a = new Nodo("A");

        raiz.hijos.add(b1);
        raiz.hijos.add(b2);
        b1.hijos.add(a);
        b2.hijos.add(b3);
        b2.hijos.add(b4);
        b3.hijos.add(a);
        b4.hijos.add(b5);
        b4.hijos.add(b6);
        b5.hijos.add(a);
        b6.hijos.add(a);

        return raiz;
    }

    private static List<List<Nodo>> buscarRutas(Nodo raiz) {
        List<List<Nodo>> rutas = new ArrayList<>();
        Queue<List<Nodo>> cola = new LinkedList<>();
        List<Nodo> rutaInicial = new ArrayList<>();
        rutaInicial.add(raiz);
        cola.offer(rutaInicial);

        while (!cola.isEmpty()) {
            List<Nodo> rutaActual = cola.poll();
            Nodo nodoActual = rutaActual.get(rutaActual.size() - 1);

            if (nodoActual.valor.equals("A")) {
                rutas.add(rutaActual);
                continue;
            }

            for (Nodo hijo : nodoActual.hijos) {
                List<Nodo> nuevaRuta = new ArrayList<>(rutaActual);
                nuevaRuta.add(hijo);
                cola.offer(nuevaRuta);
            }
        }

        return rutas;
    }

    private static void mostrarRutas(List<List<Nodo>> rutas) {
        if (rutas.isEmpty()) {
            System.out.println("No se encontraron rutas");
        } else {
            System.out.println("Rutas encontradas:");

            for (List<Nodo> ruta : rutas) {
                for (Nodo nodo : ruta) {
                    System.out.print(nodo.valor + " ");
                }
                System.out.println();
            }
        }
    }
}
