import java.util.Scanner;
/**
 *
 * @author Nitish Krishna Ganesan
 */
class List3{
    int listOfNodes=0;
    int arrX[];
    int arrY[];
    int prevX[];
    int prevY[];
    int weight[];
    List3(){
        arrX = new int[16];
        arrY = new int[16];
        prevX = new int[16];
        prevY = new int[16];
        weight = new int[16];
    }
    void add(int nodeX,int nodeY,int prev1,int prev2,int weight1){
        arrX[listOfNodes]=nodeX;
        arrY[listOfNodes]=nodeY;
        prevX[listOfNodes]=prev1;
        prevY[listOfNodes]=prev2;
        weight[listOfNodes]=weight1;
        listOfNodes++;
    }
}
public class Bike {
    static int process(List3 totalList1[][],int m,int n,int arrayStore[][],int pos){
        int path[][] = new int[m][n];
        int final1[][] = new int[m][n];
        int prevX[][] = new int[m][n];
        int prevY[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                path[i][j] = 1000000000;
                final1[i][j] = 0;
            }
        }
        // processing kruskal's algorithm
        
        // the initial edge is added to the processing
        if(totalList1[0][0].listOfNodes > pos){
            path[totalList1[0][0].arrX[pos]][totalList1[0][0].arrY[pos]] = 1;
            prevX[totalList1[0][0].arrX[pos]][totalList1[0][0].arrY[pos]] = 0;
            prevY[totalList1[0][0].arrX[pos]][totalList1[0][0].arrY[pos]] = 0;
        }else{
            return 1000000000;
        }
        path[0][0]=0;
        final1[0][0]=1;
        int flag2=0;
        while(final1[m-1][n-1]!=1){ // processing takes place until the destination 
            int min=1000000000;
            int minposX=0;
            int minposY=0;
            int flag=0;
            for(int i=0;i<m;i++){ // the minimum path from list of vertices is taken 
                                  // and made final
                for(int j=0;j<n;j++){
                    if(final1[i][j]!=1){
                        if(min>path[i][j]){
                            min = path[i][j];
                            minposX=i;
                            minposY=j;
                            flag=1;
                        }
                    }
                }
            }
            if(flag==0){ // if there is no minimum path which is not final
                         // before the destination is reached then the flag is set
                flag2=1;
                break;
            }else{
                final1[minposX][minposY]=1;
                for(int i=0;i<totalList1[minposX][minposY].listOfNodes;i++){
                    // if the distance from the minimum path vertex to any of its edges is less
                    // than before, then the path of that node is changed, that is the 
                    // possible edges are updated with the minimum distance and the previous node 
                    // is also updated 
                    if(final1[totalList1[minposX][minposY].arrX[i]][totalList1[minposX][minposY].arrY[i]]==0 && totalList1[minposX][minposY].prevX[i]==prevX[minposX][minposY] && totalList1[minposX][minposY].prevY[i]==prevY[minposX][minposY] ){
                        if(path[totalList1[minposX][minposY].arrX[i]][totalList1[minposX][minposY].arrY[i]] > ( path[minposX][minposY] + totalList1[minposX][minposY].weight[i]) ){
                            path[totalList1[minposX][minposY].arrX[i]][totalList1[minposX][minposY].arrY[i]] = ( path[minposX][minposY] + totalList1[minposX][minposY].weight[i]);
                            prevX[totalList1[minposX][minposY].arrX[i]][totalList1[minposX][minposY].arrY[i]] = minposX;
                            prevY[totalList1[minposX][minposY].arrX[i]][totalList1[minposX][minposY].arrY[i]] = minposY;
                        }
                    }
                }
                // if the destination node is final the traversing stops
                if(final1[m-1][n-1]==1){
                    break;
                }
            }
        }
        if(flag2==0){
            // if the destination is reached the minimum distance to the 
            // destination is returned
            return path[m-1][n-1];
        }else{
            // if the flag is set , i,e if the destination is not reached
            return -1;
        }
    }
    public static void main(String args[]){
        Scanner s1 = new Scanner(System.in); 
        int m = s1.nextInt();
        int n = s1.nextInt();
        List3 totalList1[][] = new List3[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){   
                totalList1[i][j] = new List3();
            }
        }
        int arrayStore[][] = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arrayStore[i][j] = s1.nextInt();
            }
        }
        // the edges are added to corressponding list if the value is less,
        // that is if the edge is downhill. the weight of the edge is based 
        // on the direction 
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    if(i+1 != m && arrayStore[i+1][j] < arrayStore[i][j]){
                        totalList1[i][j].add(i+1,j,0,0,1);
                    }
                    if(j+1 != m && arrayStore[i][j+1] < arrayStore[i][j]){
                        totalList1[i][j].add(i,j+1,0,0,1);
                    }
                }else{
                    if(i-1 != -1){
                        if(arrayStore[i-1][j] > arrayStore[i][j]){
                            if(i+1!=m && arrayStore[i+1][j] < arrayStore[i][j]){
                                totalList1[i][j].add(i+1,j,i-1,j,1);
                            }
                            if(j-1!=-1 && arrayStore[i][j-1] < arrayStore[i][j]){
                                totalList1[i][j].add(i,j-1,i-1,j,4);
                            }
                            if(j+1!=n && arrayStore[i][j+1] < arrayStore[i][j]){
                                totalList1[i][j].add(i,j+1,i-1,j,4);
                            }
                        }
                    }
                    if(j-1 != -1){
                        if(arrayStore[i][j-1] > arrayStore[i][j]){
                            if(j+1!=n && arrayStore[i][j+1] < arrayStore[i][j]){
                                totalList1[i][j].add(i,j+1,i,j-1,1);
                            }
                            if(i-1!=-1 && arrayStore[i-1][j] < arrayStore[i][j]){
                                totalList1[i][j].add(i-1,j,i,j-1,4);
                            }
                            if(i+1!=m && arrayStore[i+1][j] < arrayStore[i][j]){
                                totalList1[i][j].add(i+1,j,i,j-1,4);
                            }
                        }
                    }
                    if(i+1 != m){
                        if(arrayStore[i+1][j] > arrayStore[i][j]){
                            if(i-1!=-1 && arrayStore[i-1][j] < arrayStore[i][j]){
                                totalList1[i][j].add(i-1,j,i+1,j,1);
                            }
                            if(j-1!=-1 && arrayStore[i][j-1] < arrayStore[i][j]){
                                totalList1[i][j].add(i,j-1,i+1,j,4);
                            }
                            if(j+1!=n && arrayStore[i][j+1] < arrayStore[i][j]){
                                totalList1[i][j].add(i,j+1,i+1,j,4);
                            }
                        }
                    }
                    if(j+1 != n){
                        if((int)arrayStore[i][j+1] > (int)arrayStore[i][j]){
                            if(j-1!=-1 && arrayStore[i][j-1] < arrayStore[i][j]){
                                totalList1[i][j].add(i,j-1,i,j+1,1);
                            }
                            if(i-1!=-1 && arrayStore[i-1][j] < arrayStore[i][j]){
                                totalList1[i][j].add(i-1,j,i,j+1,4);
                            }
                            if(i+1!=m && arrayStore[i+1][j] < arrayStore[i][j]){
                                totalList1[i][j].add(i+1,j,i,j+1,4);
                            }
                        }
                    }
                }
            }
        }
        // initially the first node can have two possible directions 
        // so the minimum cost of the two directions is taken
        int ans1 = process(totalList1,m,n,arrayStore,0);
        int ans2 = process(totalList1,m,n,arrayStore,1);
        System.out.println(Math.min(ans1, ans2));
    }
}
