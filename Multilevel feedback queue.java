
package mythread2;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.Queue;

class Process
{
    Color color;
    char name;
    int ArrivalTime,BurstTime,WaitingTime,TurnaroundTime,RemainingTime,CT;
    int Priority=1;
    int consume=0;
    int consume1=0;
    int value0=0;
    int value = 0;
    int value2=0;
    
    public void demote()
    {
        Priority++;
    }
    
}
 

public class Main extends Application
{
     int rect=0;
     Rectangle [] recs= new Rectangle[1000];
     
    class Operations extends Pane{
    Process [] Givens= new Process[10];
    Process [] ready= new Process[9];
    int[] arrival= new int[10];
    int numOfProcess;
    int i = 0;
    int r=0;
    int time = 0;
    int noofnotfinished;
   
    char c = 'A';
  
    void sortByArrival(int n)
    {
        for(int i=0;i<n;i++)
        {
            
            for(int j=i+1;j<n;j++)
            {
                if(Givens[i].ArrivalTime>Givens[j].ArrivalTime)
                {
                    Process temp=Givens[i];
                    Givens[i]=Givens[j];
                    Givens[j]=temp;
                    int tmp=arrival[i];
                    arrival[i]=arrival[j];
                    arrival[j]=tmp;
                }
            }
        }
    }
    void sortByPriority(int n)
    {
         for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(ready[i].Priority>ready[j].Priority)
                {
                    Process temp=ready[i];
                    ready[i]=ready[j];
                    ready[j]=temp;
                }
                if(ready[i].Priority==ready[j].Priority)
                        {
                            if(ready[i].ArrivalTime>ready[j].ArrivalTime)
                            {
                               Process temp=ready[i];
                               ready[i]=ready[j];
                               ready[j]=temp;
                            }
                        }
            }
        }
    }
    void enterinfo()
    {
        System.out.println("Enter no of processes:");
        Scanner input = new Scanner(System.in);
        numOfProcess = input.nextInt();
        noofnotfinished=numOfProcess;
        for (i = 0, c = 'A'; i < numOfProcess; i++, c++) {
            this.Givens[i] = new Process();
            this.Givens[i].name = c;
            this.Givens[i].color=Color.HOTPINK;
            System.out.println("Enter the arrival time and burst time of process : " + this.Givens[i].name);
            this.Givens[i].ArrivalTime = input.nextInt();
            arrival[i]=this.Givens[i].ArrivalTime;
            this.Givens[i].BurstTime = input.nextInt();
            this.Givens[i].RemainingTime = this.Givens[i].BurstTime; //save burst time in remaining time for each process
            
        }

        this.sortByArrival(numOfProcess);
        time = this.Givens[0].ArrivalTime;
  
    }
    
    void enterlower(Process P)
       {
           
                time++;
                P.RemainingTime=P.RemainingTime-1;
               
                if(P.Priority==1)
                {
                    
                    if(P.consume1==4)
                    { P.demote();
                      
                    }
                   P.consume1++;
                  
                }
                else 
                {
                   
                    if(P.consume==7&&P.Priority==2)
                        P.demote();
                    P.consume++;   
                  
                        
                   
                    
                }
                  
       }
   void calculate(Process P)
   {
       P.WaitingTime = time - P.ArrivalTime - P.BurstTime;
       P.TurnaroundTime = time - P.ArrivalTime; 
       noofnotfinished--;
       System.out.println(P.name + "\t" + "WT: " +  P.WaitingTime + "\t" + "TAT: " +  P.TurnaroundTime); 
   }
   void Gui(Process P,int value,int y)    
   {
        recs[rect]=new Rectangle(Math.abs(time-value)*10,y,value*10,20);
        recs[rect].setFill(Color.rgb((((int)P.name)-64)*30,100,(((int)P.name)-64)*30));
        rect++;
       
   }
    
    int run()
    {
     
         for(int i=0;i<r;i++)
              
       {
           switch(ready[i].Priority)
           {
               case(1): //enter first queue
               {
                   while(ready[i].consume1<5&&ready[i].RemainingTime>0)
                      {
                           
                          
                         enterlower(ready[i]);
                         ready[i].value0++;
                         System.out.println("process" +ready[i].name+" in queue 1 "+" consumed "+ready[i].consume1);
                          if(ready[i].RemainingTime==0)
                           {
                               calculate(ready[i]);
                           }
                        for(int j=0;j<numOfProcess;j++)
                         {
                             
                          if(Givens[j].ArrivalTime==time)
                          { 
                             Gui(ready[i],ready[i].value0,30);
                             ready[i].value0=0;
                            return 0;
                          }
                         }
                             
                        
                     }
                     Gui(ready[i],ready[i].value0,30);
                     break;
                }
                       
               case(2): // enter second queue
               {
                   for(int k=0;k<r;k++)
                   {
                       if(ready[k].Priority<2&&ready[k].RemainingTime!=0)
                           return 0;
                   }
                       
                      
                           while(ready[i].consume<8&&ready[i].RemainingTime>0)
                      {
                           
                         enterlower(ready[i]);
                         ready[i].value++;
                         System.out.println("process" +ready[i].name+" in queue 2 "+" consumed "+ready[i].consume);
                          if(ready[i].RemainingTime==0)  
                              calculate(ready[i]);
                        for(int j=0;j<numOfProcess;j++)
                         {
                             
                          if(Givens[j].ArrivalTime==time)
                          
                          {
                               Gui(ready[i],ready[i].value,70);
                              ready[i].value=0;
                              return 0;
                          }
                              
                         }
                      
                    
                    }
                     Gui(ready[i],ready[i].value,70);
                      break;
  
               }
               case(3): //enter third queue
                   
               {  
                     
                    for(int k=0;k<r;k++)
                   {
                       if(ready[k].Priority<3&&ready[k].RemainingTime!=0)
                             return 0;
                       
                   }
                       
                      
                          while(ready[i].RemainingTime>0)
                      { 
                         
                         enterlower(ready[i]);
                         ready[i].value2++;
                         System.out.println("process" +ready[i].name+" in queue 3 "+" consumed "+(ready[i].consume-8));
                         if(ready[i].RemainingTime==0)
                             calculate(ready[i]);
                         for(int j=0;j<numOfProcess;j++)
                         {
                          if(Givens[j].ArrivalTime==time)
                           {
                             
                             Gui(ready[i],ready[i].value2,100);
                             ready[i].value2=0;
                             return 0;
                          }
                         }
                         
                         
                        
                      }
                      Gui(ready[i],ready[i].value2,100);
                      break;
                  
               } 
               default:
                   break;
           } 
           
          
      }
       return 0;
    }
    void ready()
    {
        
         
        r=0;
        for(int k=0;k<numOfProcess;k++)
        {
            if(Givens[k].ArrivalTime<=time&&Givens[k].RemainingTime!=0)
            {
             ready[r]=Givens[k];
             r++;
            
            }
        }
        if(r==0)
            time++;
        sortByPriority(r);
         
    }
}
     Operations multi = new Operations();
     public void start(Stage ps)
    {
      
        execute();
        for(int i=0;i<rect;i++)
            multi.getChildren().add(recs[i]);
        Scene sc=new Scene(multi,900,900);
        ps.setScene(sc);
        ps.setTitle("Multi level feedback queue");
        ps.show();
    }
     public void execute()
     {
        
         multi.enterinfo();
      
       while(multi.noofnotfinished!=0)
       {
           multi.ready();
           multi.run();
           
       }
      
          
     }
     
    public static void main(String[] args)
    {
       
        launch(args);
    }

}

    


