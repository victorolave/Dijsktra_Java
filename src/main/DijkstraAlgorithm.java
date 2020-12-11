package main;

import java.util.Scanner;

public class DijkstraAlgorithm {

    public static void main(String[] args) {

        int E;
        int origen;
        int destino;
        int peso;
        int inicial;
        int V;

        Scanner sc = new Scanner( System.in );

        System.out.print("Ingrese el numero de vertices: ");
        V = sc.nextInt();

        System.out.print("Ingrese el numero de aristas: ");
        E = sc.nextInt();

        Dijkstra dijkstraAlgorithm = new Dijkstra(V);

        for( int i = 0 ; i < E ; ++i ){
            origen = sc.nextInt(); destino = sc.nextInt(); peso = sc.nextInt();
            dijkstraAlgorithm.addEdge(origen, destino, peso, true);
        }

        System.out.print("Ingrese el vertice inicial: ");

        inicial = sc.nextInt();
        dijkstraAlgorithm.dijkstra(inicial);
        dijkstraAlgorithm.printShortestPath();
    }
}