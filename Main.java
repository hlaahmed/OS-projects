import java.util.Scanner;

class Process{
    char name;
    int ArrivalTime,BurstTime,WaitingTime,TurnaroundTime,RemainingTime,CT;



}

class Operations{
    Process [] Q1= new Process[10];
    Process [] Q2= new Process[10];
    Process [] Q3= new Process[10];
    //int i,j;
    void sortByArrival(int n){
        for(int i=0;i<n;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(Q1[i].ArrivalTime>Q1[j].ArrivalTime)
                {
                    Process temp=Q1[i];
                    Q1[i]=Q1[j];
                    Q1[j]=temp;
                }
            }
        }
    }


}
class Main {
    public static void main(String[] args) {
        Operations MLFQ = new Operations();
        int i = 0;
        int j = 0;
        int k = 0;
        int r = 0;
        int time = 0;
        int tq1 = 5;
        int tq2 = 8;
        int flag = 0;

        char c = 'A';
        System.out.println("Enter no of processes:");
        Scanner input = new Scanner(System.in);
        int numOfProcess = input.nextInt();

        for (i = 0, c = 'A'; i < numOfProcess; i++, c++) {
            MLFQ.Q1[i] = new Process();
            MLFQ.Q1[i].name = c;
            System.out.println("Enter the arrival time and burst time of process : " + MLFQ.Q1[i].name);
            MLFQ.Q1[i].ArrivalTime = input.nextInt();
            MLFQ.Q1[i].BurstTime = input.nextInt();
            MLFQ.Q1[i].RemainingTime = MLFQ.Q1[i].BurstTime; //save burst time in remaining time for each process

        }

        MLFQ.sortByArrival(numOfProcess);
        time = MLFQ.Q1[0].ArrivalTime;

        System.out.println("Process in first queue following RR with qt=5");

        for (i = 0; i < numOfProcess; i++) {

            if (MLFQ.Q1[i].RemainingTime <= tq1) {

                time += MLFQ.Q1[i].RemainingTime;/*from arrival time of first process to completion of this process*/
                MLFQ.Q1[i].RemainingTime = 0;
                MLFQ.Q1[i].WaitingTime = time - MLFQ.Q1[i].ArrivalTime - MLFQ.Q1[i].BurstTime;/*amount of time process has been waiting in the first queue*/
                MLFQ.Q1[i].TurnaroundTime = time - MLFQ.Q1[i].ArrivalTime; /*amount of time to execute the process*/

                System.out.println(MLFQ.Q1[i].name + "\t" + "RT: " +  MLFQ.Q1[i].BurstTime + "\t" + "WT: " +  MLFQ.Q1[i].WaitingTime + "\t" + "TAT: " +  MLFQ.Q1[i].TurnaroundTime);


            } else/*process moves to queue 2 with qt=8*/ {
                MLFQ.Q2[k] = MLFQ.Q1[i];

                MLFQ.Q2[k].WaitingTime = time;
                time += tq1;
                MLFQ.Q1[i].RemainingTime -= tq1;
                MLFQ.Q2[k].BurstTime = MLFQ.Q1[i].RemainingTime;
                MLFQ.Q2[k].RemainingTime = MLFQ.Q2[k].BurstTime;
                MLFQ.Q2[k].name = MLFQ.Q1[i].name;
                k = k + 1;
                flag = 1;
            }
        }
            if(flag==1){
                System.out.println("Process in second queue following RR with qt=8");

        }

            for(i=0;i<k;i++) {
                if (MLFQ.Q2[i].RemainingTime <= tq2) {
                    time += MLFQ.Q2[i].RemainingTime;/*from arrival time of first process +BT of this process*/
                    MLFQ.Q2[i].RemainingTime = 0;
                    MLFQ.Q2[i].WaitingTime = time - tq1 - MLFQ.Q2[i].BurstTime - MLFQ.Q2[i].ArrivalTime  ;/*amount of time process has been waiting in the ready queue*/
                    MLFQ.Q2[i].TurnaroundTime = time - MLFQ.Q2[i].ArrivalTime;/*amount of time to execute the process*/
                    System.out.println(MLFQ.Q2[i].name + "\t" + "RT: " + MLFQ.Q2[i].BurstTime + "\t" + "WT: " +  MLFQ.Q2[i].WaitingTime + "\t"+ "TAT: " + MLFQ.Q2[i].TurnaroundTime);

                }

                else/*process moves to queue 3 with FCFS*/
                {
                    MLFQ.Q3[r] = MLFQ.Q2[i];

                    MLFQ.Q3[r].WaitingTime = time;
                    time+=tq2;
                    MLFQ.Q2[i].RemainingTime -= tq2;
                    MLFQ.Q3[r].BurstTime = MLFQ.Q2[i].RemainingTime;
                    MLFQ.Q3[r].RemainingTime = MLFQ.Q3[r].BurstTime;
                    MLFQ.Q3[r].name = MLFQ.Q2[i].name;
                    r = r+1;
                    flag = 2;
                }
            }

            if(flag==2)
            System.out.println("Process in third queue following FCFS ");
            for(i=0;i<r;i++) {
                if (i == 0)
                {
                    MLFQ.Q3[i].CT = MLFQ.Q3[i].BurstTime + time;


            }
                     else
                        MLFQ.Q3[i].CT = MLFQ.Q3[i-1].CT + MLFQ.Q3[i].BurstTime;

                }

        for(i=0;i<r;i++)
        {
            MLFQ.Q3[i].TurnaroundTime =   MLFQ.Q3[i].CT - MLFQ.Q3[i].ArrivalTime;
            MLFQ.Q3[i].WaitingTime= MLFQ.Q3[i].CT +(- MLFQ.Q3[i].BurstTime - tq1 - tq2 - MLFQ.Q3[i].ArrivalTime );
            System.out.println(MLFQ.Q3[i].name + "\t" + "RT: " +  MLFQ.Q3[i].BurstTime + "\t" +"WT: " + MLFQ.Q3[i].WaitingTime + "\t" + "TAT: " +  MLFQ.Q3[i].TurnaroundTime);

        }

    }
}

