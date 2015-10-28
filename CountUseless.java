import java.util.Scanner;
/**
 *
 * @author Nitish Krishna Ganesan
 */
public class CountUseless {
    public static void main(String args[]){
        Scanner s1 = new Scanner(System.in); 
        int totalVertices = s1.nextInt();
        int totalEdges = s1.nextInt();
        double matrix[][][] = new double[totalVertices+1][totalVertices+1][totalVertices+1];
        for(int i=1;i<=totalVertices;i++){
            for(int j=1;j<=totalVertices;j++){
                matrix[i][j][0] = 10000000;
            }
        }
        for(int i=0;i<totalEdges;i++){
            int tempA = s1.nextInt();
            int tempB = s1.nextInt();
            // storing all the weights in the 0th iteration of the matrix
            matrix[tempA][tempB][0] = s1.nextDouble();
        }
        for(int i=1;i<=totalVertices;i++){
            for(int j=1;j<=totalVertices;j++){
                for(int k=1;k<=totalVertices;k++){
                    // the matrix value is the minimum of the previous iteration value and the sum of 
                    // the previous iteration values including i as the intermediate vetex
                    matrix[j][k][i] = Math.min(matrix[j][k][i-1], matrix[j][i][i-1] + matrix[i][k][i-1]);
                }
            }
        }
        int answer=0;
        for(int i=1;i<=totalVertices;i++){
            for(int j=1;j<=totalVertices;j++){
                // if the 0th iteration is not infinity and its value final iteration values are
                // different then that edge is not used so it is counted
                if(matrix[i][j][totalVertices]!=matrix[i][j][0] && matrix[i][j][0]!=10000000){
                    answer++;
                } 
            }
        }
        System.out.println(answer);
    }
}
