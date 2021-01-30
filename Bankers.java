
package mythread2;
import java.util.Scanner;
public class Bankers{
private int need[][],allocate[][],max[][],available[][],processNo,resourceNo;

private void input(){
 Scanner sc=new Scanner(System.in);
 System.out.print("Enter no. of processes and no. of resources : ");
 processNo=sc.nextInt();  //no. of process
 resourceNo=sc.nextInt(); //no. of resources
 need=new int[processNo][resourceNo];  //initializing arrays
 max=new int[processNo][resourceNo];
 allocate=new int[processNo][resourceNo];
 available=new int[1][resourceNo];
 
 System.out.println("Enter allocation matrix -->");
 for(int i=0;i<processNo;i++)
      for(int j=0;j<resourceNo;j++)
     allocate[i][j]=sc.nextInt();  //allocation matrix
  
 System.out.println("Enter max matrix -->");
 for(int i=0;i<processNo;i++)
      for(int j=0;j<resourceNo;j++)
     max[i][j]=sc.nextInt();  //max matrix
  
    System.out.println("Enter available matrix -->");
    for(int j=0;j<resourceNo;j++)
     available[0][j]=sc.nextInt();  //available matrix
    
    sc.close();
}

private int[][] calc_need(){
   for(int i=0;i<processNo;i++)
     for(int j=0;j<resourceNo;j++)  //calculating need matrix
      need[i][j]=max[i][j]-allocate[i][j];
   
   return need;
}

private boolean check(int i){
   //checking if all resources for ith process can be allocated
   for(int j=0;j<resourceNo;j++) 
   if(available[0][j]<need[i][j])
      return false;

return true;
}
public void isSafe(){

   input();
   calc_need();
   boolean[] finished=new boolean[processNo];
   int j=0;

while(j<processNo){

//until all process allocated
  
  boolean allocated=false;
   for(int i=0;i<processNo;i++)
    if(!finished[i] && check(i)){  //trying to allocate
        for(int k=0;k<resourceNo;k++)
          available[0][k]=available[0][k]-need[i][k]+max[i][k];
     System.out.println("Allocated process : "+i);
     allocated=finished[i]=true;
           j++;
       
       }
         
      if(!allocated) break;  //if no allocation
   }
   if(j==processNo)  //if all processes are allocated
    System.out.println("\nSafely allocated");
   else
    System.out.println("All proceess can not be allocated safely");
}

public static void main(String[] args) {
   new Bankers().isSafe();
   }
}