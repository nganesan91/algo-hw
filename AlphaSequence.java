import java.util.Scanner;
/**
 *
 * @author Nitish Krishna Ganesan
 */
class List2{
    int listOfNodes=0;
    int tempCount=0;
    char arr[];
    List2(int totalVertices,int totalEdges){
        arr = new char[totalEdges];
    }
    void add(char node){
        arr[listOfNodes]=node;
        listOfNodes++;
    }
}
public class AlphaSequence {
    static List2 DFS(int node,List2[] totalList1,int[] seen,List2 sort){
        // marking node as a seen
        seen[node]=1;
        for(int i=0;i<totalList1[node].listOfNodes;i++){
            // every node is checked if it is not seen
            if(seen[totalList1[node].arr[i]]==0){
                DFS(totalList1[node].arr[i],totalList1,seen,sort);
            }
        }
        // the node is added to the sort list if has no more unseen edges
        sort.add((char)node);
        return sort;
    }
    public static void main(String args[]){
        Scanner s1 = new Scanner(System.in); 
        int n = s1.nextInt();
        char arrayStore[][] = new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arrayStore[i][j] = s1.next().charAt(0);
            }
        }
        List2 totalList1[] = new List2[200];
        List2 totalList2[] = new List2[200];
        for(int i=0;i<200;i++){
            totalList1[i] = new List2(n*n,n*n);
            totalList2[i] = new List2(n*n,n*n);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                // calculaitng the incoming edges for each postion in the 
                // matrix, that is the 8 positions sorrounding 
                if(i-1 != -1){
                    if((int)arrayStore[i-1][j] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i-1][j]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i-1][j]);
                    }
                }
                if(j-1 != -1){
                    if((int)arrayStore[i][j-1] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i][j-1]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i][j-1]);
                    }
                }
                if(i+1 != n){
                    if((int)arrayStore[i+1][j] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i+1][j]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i+1][j]);
                    }
                }
                if(j+1 != n){
                    if((int)arrayStore[i][j+1] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i][j+1]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i][j+1]);
                    }
                }
                
                if(i-1 != -1 && j-1 !=-1){
                    if((int)arrayStore[i-1][j-1] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i-1][j-1]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i-1][j-1]);
                    }
                }
                if(i-1 != -1 && j+1 !=n){
                    if((int)arrayStore[i-1][j+1] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i-1][j+1]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i-1][j+1]);
                    }
                }
                if(i+1 != n && j-1 !=-1){
                    if((int)arrayStore[i+1][j-1] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i+1][j-1]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i+1][j-1]);
                    }
                }
                if(i+1 != n && j+1 !=n){
                    if((int)arrayStore[i+1][j+1] < (int)arrayStore[i][j]){
                        totalList1[(int)arrayStore[i+1][j+1]].add(arrayStore[i][j]);
                        totalList1[(int)arrayStore[i][j]].tempCount++;
                        totalList2[(int)arrayStore[i][j]].add(arrayStore[i+1][j+1]);
                    }
                }
            }
        }
        // finding the nodes with no incoming edges
        List2 a = new List2(n*n,n*n);
        for(int x=0;x<n;x++){
                for(int y=0;y<n;y++){
                    if(totalList2[(int)arrayStore[x][y]].listOfNodes==0){
                        a.add(arrayStore[x][y]);
                        break;
                    }
                }
        }
        
        int finalmax=0;
        for(int d=0;d<a.listOfNodes;d++){ // the maximum length is calculated for all the start points
            int seen1[] = new int[91];
            List2 sort = new List2(n*n,n*n);
            // DFS for the topological sorting
            sort= DFS(a.arr[d],totalList1,seen1,sort);

            //calculating the S array
            int S[] = new int[200];
            for(int i=sort.listOfNodes-1;i>-1;i--){
                int max=1;
                for(int j=0;j<totalList2[(int)sort.arr[i]].listOfNodes;j++){
                    int sum = 1 + S[totalList2[(int)sort.arr[i]].arr[j]];
                    if(max < sum){
                        max=sum;
                    }
                }
                S[(int)sort.arr[i]]=max;
            }
            // the max of the S array is printed 
            int max=0;
            for(int i=0;i<sort.listOfNodes;i++){
                if(max<S[sort.arr[i]]){
                    max=S[sort.arr[i]];
                }
            }
            if(max>finalmax){
                // the max for the current start point is more,
                // then it the finalmax
                finalmax=max;
            }
        }
        System.out.println(finalmax);
    }
}
