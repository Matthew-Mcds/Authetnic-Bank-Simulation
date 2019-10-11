import java.util.Scanner;
import java.io.*;

public class Eventtest
{
static int TT = 0;
static int ts = 0;
  static int tt = 0;
  static int count = 0;
  static int CURR = 0;
  static Customer c1;
  static Customer c2;
  static Customer c3;
  static Queue2<Customer> QCust;
  static Customer CUST;
  static int b1;
  static int b2;
  static int b3;
  static int index = 0;
  static int index2 = 0;
  static int d = 0;
  static int y = 0;
  static int[] TaskArray = new int[100];
  static int[] CustArray = new int[100];
  static Task TaskCurr = new Task(916,3);
  static Task T;
  static Event E;
  static int Type = 0;
  static OList<Event> EList = new OList<Event>();
  static Stack1gen<Task> Tasks = new Stack1gen();
  static boolean busy = false;
    
  public static void main(String[] args)throws IOException
  {
  
    
    System.out.println("Matthew Mcdonald Bank Model 1");
    System.out.println("Computer Science 3");
    System.out.println(" ");
      
    E = new Event(918,2); //events
    EList.insert(E);
    E = new Event(918,7);
    EList.insert(E);
    E = new Event(920,1);
    EList.insert(E);
    E= new Event(920,5);
    EList.insert(E);
    E = new Event(925,6);
    EList.insert(E);
    //
    Task tsks = new Task(910,12);
    Tasks.push(tsks);
    tsks=new Task(913,3);
    Tasks.push(tsks);

    QCust = new Queue2<Customer>();
    CUST = new Customer(918,5,5); 
    
    Customer C = new Customer(912,6,15);
    QCust.enqueue(C);
    C = new Customer(913,3,10);
    QCust.enqueue(C);
    C = new Customer(915,4,15);
    QCust.enqueue(C);
    
     T = new Task(925,2); 
    c1= new Customer(908, 3, 20);
    c2= new Customer(905, 4, 15);

    

    File file = new File("TF.txt");
    Scanner sc = new Scanner(file);
    
    for(d = 0; sc.hasNextInt(); d++){
     
     TaskArray[index] = sc.nextInt(); //d
     index++;
      
    }
    index = 0;
    
  // File file2 = new File("CF.txt");
   //Scanner sc2 = new Scanner(file2);
   Scanner sc2 = new Scanner(new File("CF.txt"));
   for(y = 0; sc2.hasNextInt(); y++){
 
    CustArray[index2] = sc2.nextInt();
    index2++;
   }
   index2=0;
    
   
   while(EList.getSize() > 0){
        EList.printList();
        E = EList.getData(1);
        EList.del(E);
        CURR = E.getTime();
        Type = E.getType();
        execute(Type);

    
    }
    System.out.println("Average wait time is: " + (ts/count));
  }
  
  public static void type1(){
   TT = 0;
   System.out.println("Customer exit at: "+c1.getArrival() +" "+ c1.getService() +" "+ c1.getQuitting());
   System.out.println("Current time: "+ CURR);
   System.out.println("Type 1");
   busy = false;
   tt = CURR - c1.getArrival();
   TT = TT + tt;
   ts = TT + tt;
   System.out.println("Through time: " + TT);
   count = count + 1;
  
   if(QCust.getSize()>0){ 
         c1 = QCust.dequeue(); 
         while((c1.getArrival()+c1.getQuitting())<CURR){
            System.out.println("Customer left at teller 1. " +  c1.getQuitting()+" minute wait time " ); 
             if(QCust.getSize()>0){ 
                c1 = QCust.dequeue(); 
                Event next = new Event(CURR+c1.getService(),1);
                EList.insert(next);
                b1 = 1; 
             }else{b1=0;busy=true;break;}  
         }
         if(busy == false){ //if teller is free add an event
             Event next = new Event(CURR+c1.getService(),1);
             EList.insert(next);
             b1 = 1; 
            }
       }else{b1=0;}
    
   }
   
  public static void type2(){
   TT = 0;
   System.out.println("Customer exit at: "+c2.getArrival() +" "+ c2.getService() +" "+ c2.getQuitting());
   System.out.println("Current Time:  "+CURR);
   System.out.println("Type 2");
   busy = false;
   tt = CURR - c2.getArrival();
   TT = TT + tt;
   ts = TT + tt;
   System.out.println("Through time: " + TT);
   count = count + 1;
   if(QCust.getSize()>0){ //4
         c2 = QCust.dequeue(); 
      while((c2.getArrival()+c2.getQuitting())<CURR){
          System.out.println("Customer left at teller 2. " +  c1.getQuitting()+" minute wait time " );
          if(QCust.getSize()>0){
                c2 = QCust.dequeue();                
          }else{b2=0;busy=true;break;}        
      }
        if(busy==false){
          Event next = new Event(CURR+c2.getService(),2);
          EList.insert(next);
          b2 = 1; 
        }
     }else{b2=0;}
     tt = 0;
   }
  
  public static void type3(){
   System.out.println(c3.getArrival() + c3.getService() + c3.getQuitting());
   tt = CURR - c3.getArrival();
   TT = TT + tt;
   ts = TT + tt;
   count = count + 1;
   if(Tasks.getSize() > 0){
    
    if(QCust.getSize() > 0){
    c3 = (Customer)QCust.dequeue();
    while(c3.getArrival() + c3.getQuitting() < CURR){ /////////
    System.out.print("A customer has left the bank");
    c3 = (Customer)QCust.dequeue(); 
    }
    Event next3 = new Event(CURR + c3.getService(), 3);
    EList.insert(next3);
    b3 = 1;
    }
    }
   else{
    TaskCurr = (Task)Tasks.pop();
    
    Event next3 = new Event(CURR + c3.getService(), 5);
    EList.insert(next3);
    b3 = 1;
   }
   }
   
   public static void type5(){
    System.out.println("Curent time: " + CURR);
    System.out.println("Type 5");
    System.out.println("Task exit: " + TaskCurr.getArrivalTime() +" "+ TaskCurr.getServiceTime());
    if(Tasks.getSize()>0){
       TaskCurr = (Task)Tasks.pop();//2) taskcurr=arrnew,sernew
       Event next = new Event(CURR+TaskCurr.getServiceTime(),5);
       EList.insert(next);
       b3 = 1;
     }else if(QCust.getSize()>0) {
         c3 = QCust.dequeue(); 
       while((c3.getArrival()+c3.getQuitting())<CURR){
            System.out.println("\nCustomer already left the bank at: "+CURR); //edit
           if(Tasks.getSize()>0){
               TaskCurr = (Task)Tasks.pop();//2) taskcurr=arrnew,sernew
               Event next = new Event(CURR+TaskCurr.getServiceTime(),5);
               EList.insert(next);
               b3 = 1;
           }else if(QCust.getSize()>0){
                c3 = QCust.dequeue(); 
           }else{b3 = 0; break;}    
       }
         Event next = new Event(CURR+c3.getService(),5); //type 3 or type 5??** i think 5
         EList.insert(next);
         b3 = 1;  
        }else{b3=0;}  
}

public static void type6()throws IOException{
  Tasks.push(T);
  if(Tasks.getSize() == 1 ){
    if(b3 == 0){
    TaskCurr = Tasks.pop();
    Event e = new Event(CURR + TaskCurr.getServiceTime(), 5);
    EList.insert(e);
    b3 = 1;
    }
    
  }
  if(index < d){
    int NewArr = TaskArray[index]; 
    int NewSer = TaskArray[index+1];
    index = index+2;
    
    T = new Task(NewArr,NewSer);
    //Tasks.push(T);
    Event taskNext = new Event(NewArr,6);
    EList.insert(taskNext);
    
    
  }


}

public static void type7(){ 
System.out.println("Customer arrival at: " + CUST.getArrival() + " " + CUST.getService() + " "+ CUST.getQuitting());
System.out.println("Type 7");
QCust.enqueue(CUST);

if(QCust.getSize() == 1){
    if(b1==0||b2==0||b3==0){
        if(b1==0){
            c1 = (Customer)QCust.dequeue();
            Event i = new Event (CURR + c1.getService(),1);
            EList.insert(i);
            b1 =1;
        }
        else if(b2==0){
            c2 = (Customer)QCust.dequeue();
            Event j = new Event (CURR + c2.getService(),2);
            EList.insert(j);
            b2 =1;
        } 
        else{
            c3 = (Customer)QCust.dequeue();
            Event k = new Event (CURR + c3.getService(),3);
            EList.insert(k);
            b3 =1;
        }
    }
}
if( index2<y ){
int newArrival = CustArray[index2];
int newService = CustArray[index2 +1];
int newQuitting = CustArray[index2 +2];
index2 = index2 + 3; 

CUST = new Customer(newArrival,newService,newQuitting);

//QCust.enqueue(CUST);
Event next7 = new Event(newArrival , 7);
EList.insert(next7);
}

}

public static void execute(int type)throws IOException{

 switch(type){
    case 1: type1(); System.out.println("Exit from teller 1 ");
     break;
     
     case 2: type2(); System.out.println("Exit from teller 2 ");
     break;
     
     case 3: type3(); System.out.println("Exit from teller 3 ");
     break;
     
     case 5: type5(); System.out.println("Task completed by teller 3 ");
     break;
     
     case 6: type6(); System.out.println("Task arrival at: "+T.getServiceTime()+" \n"); 
     break;
     
     case 7: type7(); 
     break;

 
 }




}

}
  