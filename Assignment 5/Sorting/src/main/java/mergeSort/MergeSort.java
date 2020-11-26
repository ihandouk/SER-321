package mergeSort;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class MergeSort {
  /**
   * Thread that declares the lambda and then initiates the work
   */

  public static int message_id = 0;

  public static JSONObject init(int[] array) {
    JSONArray arr = new JSONArray();
    for (var i : array) {
      arr.put(i);
    }
    JSONObject req = new JSONObject();
    req.put("method", "init");
    req.put("data", arr);
    return req;
  }

  public static JSONObject peek() {
    JSONObject req = new JSONObject();
    req.put("method", "peek");
    return req;
  }

  public static JSONObject remove() {
    JSONObject req = new JSONObject();
    req.put("method", "remove");
    return req;
  }
  
  public static void Test(int port,  char arraylist) {
    JSONObject response = null;
    if(arraylist == 'a'){
      int[] a = { 5, 1, 6, 2, 3, 4, 10,634,34,23,653, 23,2 ,6 };
      response = NetworkUtils.send(port, init(a));
    }else if(arraylist == 'b'){
      int[] b = {'q', 'w', 'r', 't', 'a', 's', 'd','f','z','x','c', 'v','b' ,'g','y','u','i'};
      response = NetworkUtils.send(port, init(b));
    }else if(arraylist == 'c'){
      int[] c = new int[500];
      for(int i = 0; i < 500; i++){
        c[i]=(int) ((Math.random()*500));
      }
      response = NetworkUtils.send(port, init(c));
    }
    
    System.out.println(response);
    response = NetworkUtils.send(port, peek());
    System.out.println(response);

    while (true) {
      response = NetworkUtils.send(port, remove());

      if (response.getBoolean("hasValue")) {
        System.out.println(response);;
 
      } else{
        break;
      }
    }
  }

  public static void main(String[] args) {
    // all the listening ports in the setup
    ArrayList<Integer> ports = new ArrayList<>(Arrays.asList(8000, 8001, 8002, 8003, 8004, 8005, 8006,8007, 
    8008, 8009, 8010, 8011, 8012, 8013, 8014));

    // setup each of the nodes
    //      0
    //   1     2
    // 3   4 5   6
    /*new Thread(new Branch(ports.get(0), ports.get(1), ports.get(2))).start();
    
    new Thread(new Branch(ports.get(1), ports.get(3), ports.get(4))).start();
    new Thread(new Sorter(ports.get(3))).start();
    new Thread(new Sorter(ports.get(4))).start();
    
    new Thread(new Branch(ports.get(2), ports.get(5), ports.get(6))).start();
    new Thread(new Sorter(ports.get(5))).start();
    new Thread(new Sorter(ports.get(6))).start();
    */

    /*
                   14
            13  			    12
        11		  10		 9		  8
      7	   6	 5	 4  3  2  1   0

    */
    new Thread(new Branch(ports.get(14), ports.get(13), ports.get(12))).start();

    new Thread(new Branch(ports.get(13), ports.get(11), ports.get(10))).start();
    new Thread(new Sorter(ports.get(11))).start();
    new Thread(new Sorter(ports.get(10))).start();

    new Thread(new Branch(ports.get(12), ports.get(9), ports.get(8))).start();
    new Thread(new Sorter(ports.get(9))).start();
    new Thread(new Sorter(ports.get(8))).start();

    new Thread(new Branch(ports.get(11), ports.get(7), ports.get(6))).start();
    new Thread(new Sorter(ports.get(7))).start();
    new Thread(new Sorter(ports.get(6))).start();

    new Thread(new Branch(ports.get(10), ports.get(5), ports.get(4))).start();
    new Thread(new Sorter(ports.get(5))).start();
    new Thread(new Sorter(ports.get(4))).start();

    new Thread(new Branch(ports.get(9), ports.get(3), ports.get(2))).start();
    new Thread(new Sorter(ports.get(3))).start();
    new Thread(new Sorter(ports.get(2))).start();

    new Thread(new Branch(ports.get(8), ports.get(1), ports.get(0))).start();
    new Thread(new Sorter(ports.get(1))).start();
    new Thread(new Sorter(ports.get(0))).start();

    /* make sure we didn't hang
    *  System.out.println("started");
    * One Sorter
    * long startTime = System.currentTimeMillis();
    * Test(ports.get(3), 'a');
    * long endTime = System.currentTimeMillis();
    * long duration = endTime - startTime;
    * System.out.println("Test: One Sorter \nArraylist [a] \nDuration: "+ duration +" ms");

    * One branch / Two Sorters
    * startTime = System.currentTimeMillis();
    * Test(ports.get(2), 'a');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: One Branch - Two Sorters \nArraylist [a] \nDuration: "+ duration +" ms");

    *  Three Branch / Four Sorters
    * startTime = System.currentTimeMillis();
    * Test(ports.get(0), 'a');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: Three Branch - Four Sorters \nArraylist [a] \nDuration: "+ duration +" ms");

    * Arraylist [b]
    * One Sorter
    * startTime = System.currentTimeMillis();
    * Test(ports.get(3), 'b');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: One Sorter - Arraylist [b] \nDuration: "+ duration +" ms");

    * One branch / Two Sorters
    * startTime = System.currentTimeMillis();
    * Test(ports.get(2), 'b');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: One Branch - Two Sorters \nArraylist [b] \nDuration: "+ duration +" ms");

    * Three Branch / Four Sorters
    * startTime = System.currentTimeMillis();
    * Test(ports.get(0), 'b');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: Three Branch - Four Sorters \nArraylist [b] \nDuration: "+ duration +" ms");

    * Arraylist [c]
    * One Sorter
    * startTime = System.currentTimeMillis();
    * Test(ports.get(3), 'c');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: One Sorter \nArraylist [c] \nDuration: "+ duration +" ms");

    * One branch / Two Sorters
    * startTime = System.currentTimeMillis();
    * Test(ports.get(2), 'c');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: One Branch - Two Sorters \nArraylist [c] \nDuration: "+ duration +" ms");

    * Three Branch / Four Sorters
    * startTime = System.currentTimeMillis();
    * Test(ports.get(0), 'c');
    * endTime = System.currentTimeMillis();
    * duration = endTime - startTime;
    * System.out.println("Test: Three Branch - Four Sorters \nArraylist [c] \nDuration: "+ duration +" ms");
    */
    // Two branch / Three Sorters
    long startTime = System.currentTimeMillis();
    Test(ports.get(10), 'a');
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println("Test: Two branch / Three Sorters \nArraylist [a] \nDuration: "+ duration +" ms");

    // Seven branches / Eight Sorters
    startTime = System.currentTimeMillis();
    Test(ports.get(14), 'a');
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Test: Seven branches / Eight Sorters \nArraylist [a] \nDuration: "+ duration +" ms");

    // Two branch / Three Sorters
    startTime = System.currentTimeMillis();
    Test(ports.get(10), 'b');
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Test: Two branch / Three Sorters \nArraylist [b] \nDuration: "+ duration +" ms");

    // Seven branches / Eight Sorters
    startTime = System.currentTimeMillis();
    Test(ports.get(14), 'b');
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Test: Seven branches / Eight Sorters \nArraylist [b] \nDuration: "+ duration +" ms");

    // Two branch / Three Sorters
    startTime = System.currentTimeMillis();
    Test(ports.get(10), 'c');
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Test: Two branch / Three Sorters \nArraylist [c] \nDuration: "+ duration +" ms");

    // Seven branches / Eight Sorters
    startTime = System.currentTimeMillis();
    Test(ports.get(14), 'c');
    endTime = System.currentTimeMillis();
    duration = endTime - startTime;
    System.out.println("Test: Seven branches / Eight Sorters \nArraylist [c] \nDuration: "+ duration +" ms");
  }
}
