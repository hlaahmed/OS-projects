package test;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
class MySemaphore  {

int value;
public MySemaphore(int initialvalue) {value=initialvalue;}
synchronized public void Wait(int processid) {
value--;
if (value<0)
try { System.out.println("process "+processid+" is blocked");
    wait(); } catch (InterruptedException e) {}
}
synchronized public void Signal() {
++value;
if (value <=0)
{
    System.out.println("a random process is released");
    notify();
}
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
    MySemaphore mySemaphore;
    productnum  number;
    

    thread1( MySemaphore mySemaphore,productnum number){
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Wait(1);
       //critical section
        number.decrementnum();
        System.out.println("product number is "+number.number+" after t1");
        mySemaphore.Signal();
        
        
    }
}
class thread2 extends Thread{
     MySemaphore mySemaphore;
      productnum  number;
     
    thread2( MySemaphore mySemaphore,productnum number){
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Wait(2);
        //critical section
        number.incrementnum();
        System.out.println("product number is "+number.number+" after t2");
        
        mySemaphore.Signal();
       
    }
}
class thread3 extends Thread{
    MySemaphore mySemaphore;
     productnum  number;
    
    thread3( MySemaphore mySemaphore,productnum number){
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Wait(3);
        //critical section
        number.incrementnum();
        System.out.println("product number is "+number.number+" after t3");

        mySemaphore.Signal();
    }
  }
class thread4 extends Thread{
     MySemaphore mySemaphore;
      productnum  number;
      
    thread4( MySemaphore mySemaphore,productnum number)
    {
        this.mySemaphore = mySemaphore;
        this.number=number;
    }
    public void run(){
        mySemaphore.Wait(4);
        //critical section
        number.decrementnum();
        System.out.println("product number is "+number.number+" after t4");
        
       mySemaphore.Signal();
      
      
    }
}
public class Test{
    public static void main(String args[]) {
     
        MySemaphore mySemaphore = new MySemaphore(1);
        productnum number = new productnum();
     
        thread1 t1=new thread1(mySemaphore,number);
        thread2 t2=new thread2(mySemaphore,number);
        thread3 t3=new thread3(mySemaphore,number);
        thread4 t4=new thread4(mySemaphore,number);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        

} }