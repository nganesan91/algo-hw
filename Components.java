
import java.util.Scanner;
/**
 *
 * @author Nitish Krishna Ganesan
 */
class List{
    int listOfNodes=0;
    int arr[];
    List(int totalVertices, int totalEdges){
        arr = new int[totalEdges];
    }
    void add(int node){
        arr[listOfNodes]=node;
        listOfNodes++;
    }
}
class queue1{
    static int front=0;
    static int count=0;
    static int answer=1;
    static int current=0;
    static int seen[];
    static int seen1[];
    static int array1[];
    // constructor for queue
    queue1(int totalVertices, int totalEdges){
        seen = new int[totalVertices+1];
        seen1 = new int[totalVertices+1];
        array1 = new int[totalEdges*totalEdges];
        
    }
    // adding at the end of queue
    void add(int node){        
        array1[count]=node;
        count++;
    }
    int process(int totalEdges,int totalVertices,int arrayA[],int arrayB[],List totalList[]){
        for(int i=1;i<=totalVertices;i++){
            if(i==1){
                // if it is the first node it is added to queue 
                // and processing of queue is started
                add(1);
                process1(i,0,totalVertices,totalList);
            }else{              
                // else if the node is not seen it is added to the 
                // queue and the process starts
                if(seen[i]!=1){
                    add(i);
                    process1(i,1,totalVertices,totalList);
                }
            }
        }
        return answer;
    }
    void process1(int number,int set,int totalVertices,List totalList[]){
        int flag=0;
        while(front!=count){  // until the end of queue
            seen1[array1[front]]=1; // node marked as seen
            for(int i=0;i<totalList[array1[front]].listOfNodes;i++){
                if(seen[totalList[array1[front]].arr[i]]!=1 && seen1[totalList[array1[front]].arr[i]]!=1){
                    add(totalList[array1[front]].arr[i]);
                    seen1[i]=1;
                }else if(seen[totalList[array1[front]].arr[i]]==1){
                    // if node of the neighbour nodes are seen
                    // it is noted 
                    flag=1;
                }
            }
            front++;
        }
        for(int i=0;i<totalVertices;i++){
            if(seen[i]==0 && seen1[i]==1){
                seen[i]=1;
                seen1[i]=0;
            }
        }
        if(set==1 && flag==0 && number!=totalVertices){
            // if none of the nodes are seen while processing 
            // the queue, the count of the connected components 
            // is increased
            answer++;
        }
    }
}
public class Components {
    
    public static void main(String args[]){
        Scanner s1 = new Scanner(System.in);  
        int totalVertices = s1.nextInt();
        int totalEdges = s1.nextInt();
        List totalList[] = new List[totalVertices+1];
        int arrayA[] = new int[totalEdges];
        int arrayB[] = new int[totalEdges];
        // creating a List of lists for total number of vertices 
        for(int i=0;i<=totalVertices;i++){
            totalList[i] = new List(totalVertices,totalEdges);
        }
        for(int i=0;i<totalEdges;i++){
            arrayA[i]=s1.nextInt();
            arrayB[i]=s1.nextInt();    
            totalList[arrayA[i]].add(arrayB[i]);                  
        }
        queue1 q = new queue1(totalVertices,totalEdges);
        int answer = q.process(totalEdges,totalVertices,arrayA,arrayB,totalList);
        System.out.println(answer);
    }
}
