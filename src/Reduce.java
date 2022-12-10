/*
Function reduceTask will take mapTask function output and will combine it to produce the final output.
*/

public class Reduce {

    public static void reduceTask(long[] arr) {
        long sum = 0;
        //for ecah elemnt in array arr
        for(long x : arr) {
            sum += x;
        }

        System.out.println("final result after Reduce data from mapout is " + sum);
    }
}
