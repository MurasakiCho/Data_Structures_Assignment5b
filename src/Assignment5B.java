/*
Name: Briana O'Neal
Class: CS 3305/W01
Term: Fall 2022
Instructor: Sharon Perry
Assignment: 5-Part-2-Shoppers
*/
//START
import java.util.*;
//create CheckoutLine class, put five linked lists in an arraylist
class CheckoutLine {
    //variables
    static ArrayList<LinkedList<Integer>> checkoutQueues = new ArrayList<>();
    LinkedList<Integer> line1 = new LinkedList<>();
    LinkedList<Integer> line2 = new LinkedList<>();
    LinkedList<Integer> line3 = new LinkedList<>();
    LinkedList<Integer> line4 = new LinkedList<>();
    LinkedList<Integer> line5 = new LinkedList<>();

    //constructor: add LL to ArrayList
    CheckoutLine(){
        checkoutQueues.add(line1);
        checkoutQueues.add(line2);
        checkoutQueues.add(line3);
        checkoutQueues.add(line4);
        checkoutQueues.add(line5);
    }
    //enqueue method
    //compares queue lengths. enqueue in smallest queue. if queues are equal, enqueue into first available
    public void enqueue(int customer){
        //grab the ArrayList index of the smallest queue. if all queues equal, enqueue to line1
        int temp = 0;
        for(int i = 1; i < 5; i++){
            if(checkoutQueues.get(temp).size() > checkoutQueues.get(i).size()){
                temp = i;
            }
        }
        checkoutQueues.get(temp).add(customer);
        System.out.println("Customer " + customer + " entered queue " + (temp + 1));
    }
    public void enqueue(int customer, int queueNum){
        checkoutQueues.get(queueNum).add(customer);
        System.out.println("Customer " + customer + " entered queue " + (queueNum + 1));
    }

    //dequeue method
    public void dequeue(int customer){
        //checkoutQueues.get();
        System.out.println("Customer " + customer + " left queue " );
    }
    //wait some random time before actual dequeue
    //prints when customer leaves queue
    //get size method. takes in array index?
}
//Customer class implement runnable
class Customer implements Runnable{
    int id;
    static int nextId = 0;
    boolean isRandom = false;
    Random rand = new Random();
    CheckoutLine checkoutLine = new CheckoutLine();

    Customer(){
        id = ++nextId;
    }
    Customer(boolean isRandom){
        id = ++nextId;
        this.isRandom = isRandom;
    }

    @Override
    public void run() {
        if(isRandom) {
            checkoutLine.enqueue(id,rand.nextInt(5));
        }
        else{
            checkoutLine.enqueue(id);
        }
        try{
            Thread.sleep(300);
            checkoutLine.dequeue(id);
        }
        catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }


    }
}
//driver class
public class Assignment5B {
    public static void main(String[] args) {
        //variables

        //for loop for 25 customers
        try{
            for(int i = 0; i < 10; i++){
                Thread customer = new Thread(new Customer(true));
                Thread.sleep(500);
                customer.start();
            }
            for(int i = 0; i < 15; i++){
                Thread customer = new Thread(new Customer());
                Thread.sleep(500);
                customer.start();
            }
        }
        catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }
}

