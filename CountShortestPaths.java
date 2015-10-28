import java.util.Scanner;
/**
 * Given is an undirected graph and two of its vertices s and t. Give an O(m+ n) algorithm
 * that computes the number of shortest paths from s to t.
 *
 * @author Nitish Krishna Ganesan
 */
class List1{
    int listOfNodes=0;
    int arr[];
    List1(int totalVertices, int totalEdges){
        arr = new int[totalEdges];
    }
    void add(int node){
        arr[listOfNodes]=node;
        listOfNodes++;
    }
}
public class CountShortestPaths { 
    static int seen[];
    static int shortest;
    static int shortestCount;
    
    public static void main(String args[]){
        Scanner s1 = new Scanner(System.in);  
        int totalVertices = s1.nextInt();
        int totalEdges = s1.nextInt();
        List1 totalList1[] = new List1[totalVertices+1];
        int arrayA[] = new int[totalEdges];
        int arrayB[] = new int[totalEdges];
        int source = s1.nextInt();
        int dest = s1.nextInt();
        // creating a List1 of lists for total number of vertices 
        for(int i=0;i<=totalVertices;i++){
            totalList1[i] = new List1(totalVertices,totalEdges);
        }
        for(int i=0;i<totalEdges;i++){
            arrayA[i]=s1.nextInt();
            arrayB[i]=s1.nextInt();    
            totalList1[arrayA[i]].add(arrayB[i]);
            totalList1[arrayB[i]].add(arrayA[i]);
        }    
        seen = new int[totalVertices];
        for(int i=0;i<totalVertices;i++){
            seen[i]=0;
        }
        int distance=0;
        // starting the depth first search
        DFS(source,dest,totalList1,distance);
        System.out.println(shortestCount);
        
    }
    static void DFS(int vertex,int dest,List1[] totalList1,int distance){
        // marking node asa seen
        seen[vertex]=1;
        distance+=1;
        // if the node has reached destination
        if(vertex==dest){
            // if it the first time destination is reached 
            // the distance is set as shortest 
            if(shortest==0){
                shortest=distance;
                shortestCount=1;
                distance-=1;
                seen[vertex]=0;
            }
            // else if a shorter route is found that distance
            // is set
            else if(distance < shortest){
                shortest = distance;
                shortestCount=1;
                seen[vertex]=0;
            }
            // if a same short route is found count is increased
            else if(shortest==distance){
                shortestCount+=1;
                seen[vertex]=0;
            }
            // if a longer route is found the node is just marked
            // as seen and DFS continues
            else{
                seen[vertex]=0;
            }
        }else{
            for(int i=0;i<totalList1[vertex].listOfNodes;i++){
                // every node is checked if it is not seen
                if(seen[totalList1[vertex].arr[i]]==0){
                    DFS(totalList1[vertex].arr[i],dest,totalList1,distance);
                }
            }
            // when the DFS returns the previous node distance is reduced
            distance-=1;
            seen[vertex]=0;
        }
    }
}
