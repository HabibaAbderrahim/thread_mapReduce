import java.util.concurrent.ExecutionException;

public class MapReduce {

    public static void main(String[] args) throws InterruptedException, ExecutionException  {

        long startTime = System.currentTimeMillis();
        //array initialisation with 1000 element
        int[] array = CallableObject.getArray(1000);
        mapReduce(array);
        long endTime = System.currentTimeMillis();
        //print time
        System.out.println("Total elapsed time in execution of method is :"+ ((double)(endTime-startTime)/100000) + "ms");



    }
       //call map task and reduce task
    public static void mapReduce(int[] arr) throws InterruptedException, ExecutionException {
        long[] mapOutput = Map.mapTask(arr);//10 threads, 0 chunks each with 100elts --> Result of each chunk stored in arr mapOut
        Reduce.reduceTask(mapOutput);// sum up data from the array of mapTask of each chunk
    }
}
