package test;
 abstract class mylock
{
    boolean[] flags = new boolean[2];
    int turn;
    abstract void locking(int processID);
}
final class lock extends mylock
{
     void locking(int processID)
    {
        flags[processID]=true;
        turn=Math.abs(processID-1);
        if(flags[Math.abs(processID-1)]&&!flags[processID])
          System.out.println("process "+processID+" can't enter the critical section and is waiting for process "+Math.abs(processID-1));
        else if(flags[processID]&&!flags[Math.abs(processID-1)])
          System.out.println("process "+processID+" will enter critical section ");
        else if(turn==processID)
        {
            System.out.println("process "+processID+" will enter critical section ");
        }
        else
              System.out.println("process "+processID+" can't enter the critical section and is waiting for process "+Math.abs(processID-1));
        while(flags[Math.abs(processID-1)]&&turn==Math.abs(processID-1)); //waiting
    }
  
}
class productnum
{
    int number=10;
    void incrementnum()
    {
        number++;
    }
     void decrementnum()
    {
        number--;
    }
}

 class thread1 extends Thread{
   
    mylock l;
    productnum n;
    int processID=0;

    thread1(mylock l,productnum n){
      this.l=l;
      this.n=n;
    }
    public void run(){
        l.locking(processID);
       //critical section
       n.decrementnum();
       System.out.println("process 0 decrements num so the new number is "+n.number);
       l.flags[processID]=false;
       System.out.println("process "+processID+" exited the critical section ");
      
       
        
    }
}
class thread2 extends Thread{
     mylock l;
     productnum n;
     int processID=1;

    thread2(mylock l,productnum n){
      this.l=l;
      this.n=n;
    }
    public void run(){
         l.locking(processID);
       //critical section
         n.incrementnum();
         System.out.println("process 1 increments num so the new number is "+n.number);
         System.out.println("process "+processID+" exited the critical section ");
        l.flags[processID]=false;
        
       
    }
}

public class Test{
    public static void main(String args[]) {
     
       mylock l = new lock();
       productnum number = new productnum();
     
        thread1 t1=new thread1(l,number);
        thread2 t2=new thread2(l,number);
        
        t1.start();
        t2.start();
      
        

} }
