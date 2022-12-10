import java.util.concurrent.Callable;

public class CallableObject {

    public static Callable<Long> getCallableObject(int[] arr, int start, int end){

        return new Callable<Long>() {

            @Override
            public Long call() throws Exception {
                return sumArrayElements(arr, start, end);
            }
        };

    }
   //fill
    public static int[] getArray(int size) {
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = i+1;
        }
        return array;
    }
    //sum in rach chunk
    public static long sumArrayElements(int[] arr, int start, int end) {
        long sum = 0l;

        for(int i = start; i <= end; i++) {
            sum += arr[i];
        }

        return sum;
    }
}
