package main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra{

    private final int MAX = 10005;
    private final int INF = 1<<30;

    private List<List< Node >> ady = new ArrayList< List< Node > >();
    private int distancia[ ] = new int[ MAX ];
    private boolean visitado[ ] = new boolean[ MAX ];
    private PriorityQueue< Node > Q = new PriorityQueue<Node>();
    private int V;
    private int previo[] = new int[ MAX ];
    private boolean dijkstraEjecutado;

    Dijkstra(int V){
        this.V = V;
        for( int i = 0 ; i <= V ; ++i )
            ady.add(new ArrayList<Node>()) ;
        dijkstraEjecutado = false;
    }

    class Node implements Comparable<Node>{
        int first, second;
        Node( int d , int p ){
            this.first = d;
            this.second = p;
        }
        public int compareTo( Node other){
            if( second > other.second ) return 1;
            if( second == other.second ) return 0;
            return -1;
        }
    };

    private void init(){
        for( int i = 0 ; i <= V ; ++i ){
            distancia[ i ] = INF;
            visitado[ i ] = false;
            previo[ i ] = -1;
        }
    }


    private void relajacion( int actual , int adyacente , int peso ){

        if( distancia[ actual ] + peso < distancia[ adyacente ] ){
            distancia[ adyacente ] = distancia[ actual ] + peso;
            previo[ adyacente ] = actual;
            Q.add( new Node( adyacente , distancia[ adyacente ] ) );
        }
    }

    void dijkstra( int inicial ){
        init();
        Q.add( new Node( inicial , 0 ) );
        distancia[ inicial ] = 0;
        int actual , adyacente , peso;
        while( !Q.isEmpty() ){
            actual = Q.element().first;
            Q.remove();
            if( visitado[ actual ] ) continue;
            visitado[ actual ] = true;

            for( int i = 0 ; i < ady.get( actual ).size() ; ++i ){
                adyacente = ady.get( actual ).get( i ).first;
                peso = ady.get( actual ).get( i ).second;
                if( !visitado[ adyacente ] ){
                    relajacion( actual , adyacente , peso );
                }
            }
        }

        System.out.printf( "Distancias mas cortas iniciando en vertice %d\n" , inicial );
        for( int i = 1 ; i <= V ; ++i ){
            System.out.printf("Vertice %d , distancia mas corta = %d\n" , i , distancia[ i ] );
        }
        dijkstraEjecutado = true;
    }

    void addEdge( int origen , int destino , int peso , boolean dirigido ){
        ady.get( origen ).add( new Node( destino , peso ) );    //grafo diridigo
        if( !dirigido )
            ady.get( destino ).add( new Node( origen , peso ) ); //no dirigido
    }

    void printShortestPath(){
        if( !dijkstraEjecutado ){
            System.out.println("Es necesario ejecutar el algorithmo de Dijkstra antes de poder imprimir el camino mas corto");
            return;
        }
        Scanner sc = new Scanner( System.in );
        System.out.println("\n**************Impresion de camino mas corto**************");
        System.out.printf("Ingrese vertice destino: ");
        int destino;
        destino = sc.nextInt();
        print( destino );
        System.out.printf("\n");
    }


    void print( int destino ){
        if( previo[ destino ] != -1 )
            print( previo[ destino ] );
        System.out.printf("%d " , destino );
    }

    public int getNumberOfVertices() {
        return V;
    }

    public void setNumberOfVertices(int numeroDeVertices) {
        V = numeroDeVertices;
    }
}