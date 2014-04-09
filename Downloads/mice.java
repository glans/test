import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class mice {
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(in.readLine());
		
		while(n-->0){
			in.readLine();
			int v, e, destino,tiempo;
			
			String line[];
			v=Integer.parseInt(in.readLine())+1;
			destino=Integer.parseInt(in.readLine());
			tiempo=Integer.parseInt(in.readLine());
			e=Integer.parseInt(in.readLine());
			
			int previo[] = new int[v]; 
			List< List< Node > > ady = new ArrayList< List< Node > >(); 
			int distancia[ ] = new int[v];          
			boolean visitado[ ] = new boolean[v];  
			PriorityQueue< Node > Q = new PriorityQueue<Node>();
			
			for( int i = 0 ; i <= v; ++i ) ady.add(new ArrayList<Node>()) ; 
			for( int i = 0 ; i < e; ++i ){
				line=in.readLine().split(" ");
				ady.get(Integer.parseInt(line[0])).add( new Node(Integer.parseInt(line[1]) , Integer.parseInt(line[2])));		
			}
			
			int cont=0;
			for (int j = 1; j < v; j++) {
				if(j==destino){
					cont++;
					continue;
				}else{
					for( int i = 0 ; i < v ; ++i ){
				        distancia[ i ] = Integer.MAX_VALUE; 
				        visitado[ i ] = false; 
				        previo[ i ] = -1;
				    }
					
					Q.add( new Node( j , 0 ) ); 
				    distancia[ j ] = 0;      
				    int actual , adyacente , peso;
				    
				    while( !Q.isEmpty() ){                   
				        actual = Q.element().destino;           
				        Q.remove();                          
				        if( visitado[ actual ] ) continue;
				        visitado[ actual ] = true;   

				        for( int i = 0 ; i < ady.get( actual ).size() ; ++i ){ 
				            adyacente = ady.get( actual ).get( i ).destino;  
				            peso = ady.get( actual ).get( i ).peso;      
				            if( !visitado[ adyacente ] ){       
				            	if( distancia[ actual ] + peso < distancia[ adyacente ] ){
				        	        distancia[ adyacente ] = distancia[ actual ] + peso;  
				        	        previo[ adyacente ] = actual;                         
				        	        Q.add( new Node( adyacente , distancia[ adyacente ] ) ); 
				        	    }
				            }
				        }
				    }
				    
				    if(distancia[destino]<=tiempo) cont++;
				    
				}
			}
			System.out.println(cont);
			System.out.println();
		}
	
		
	    
	}

}


class Node implements Comparable<Node>{
	int destino, peso;
	Node( int d , int p ){							
		this.destino = d;
		this.peso = p;
	}
	public int compareTo( Node other){				
		if( peso > other.peso ) return 1;
		if( peso == other.peso ) return 0;
		return -1;
	}
};
