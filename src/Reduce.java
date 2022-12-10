public class Reduce {

    public static void reduceTask(long[] arr) {
        long sum = 0;
        //for ecah elemnt in array arr
        for(long x : arr) {
            sum += x;
        }

        System.out.println("Value is " + sum);
    }
}
