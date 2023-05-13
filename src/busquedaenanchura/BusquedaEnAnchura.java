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
import javafx.util.Pair;

public class BusquedaEnAnchura {

  // declaracion de variables y Estado Inicial y Objetivo
    private static final String eInicio = "B";
    private static final String eObjetivo = "A";
    
     public static void main(String[] args) {
        List<String> caminoDeSolucion = buscarEnAnchura(eInicio, eObjetivo, mov);

        // Mostrar el resultado en pantalla
        if (caminoDeSolucion != null) {
            System.out.println("Se encontró un camino hacia el objetivo:");
            System.out.println(caminoDeSolucion);
        } else {
            System.out.println("No se encontró un camino hacia el objetivo.");
        }
    }

    // Posibles Movimientos de la pieza
    private static final Map<String, List<String>> mov = new HashMap<>();
    static {
        mov.put("B", Collections.singletonList("B1"));
        mov.put("B1", Arrays.asList("B2", "B"));
        mov.put("B2", Arrays.asList("B3", "B1"));
        mov.put("B3", Arrays.asList("B4", "B2"));
        mov.put("B4", Arrays.asList("B5", "B3"));
        mov.put("B5", Arrays.asList("B6", "B4"));
        mov.put("B6", Arrays.asList("A", "B5"));
        mov.put("A", Collections.emptyList()); 
    }

    public static List<String> buscarEnAnchura(String inicio, String objetivo, Map<String, List<String>> mov) {
        Queue<Pair<String, List<String>>> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();

        cola.add(new Pair<>(inicio, new ArrayList<>()));
        
        while (!cola.isEmpty()) {
            Pair<String, List<String>> nodoActual = cola.poll();
            String estadoActual = nodoActual.getKey();
            List<String> camino = nodoActual.getValue();

            if (estadoActual.equals(objetivo)) {
   
                camino.add(estadoActual);
                return camino;
            }

            if (!visitados.contains(estadoActual)) {
                visitados.add(estadoActual);
                List<String> movPosibles = mov.get(estadoActual);
                for (String movimiento : movPosibles) {
                    List<String> nuevoCamino = new ArrayList<>(camino);
                    nuevoCamino.add(estadoActual);
                    cola.add(new Pair<>(movimiento, nuevoCamino));
                }
            }
        }

        return null;
    }

}
