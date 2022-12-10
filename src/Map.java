import java.util.concurrent.*;

public class Map {

    public static long[] mapTask(int[] arr) throws InterruptedException, ExecutionException {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        /*
         * Add all completed tasks in a queue.
         */
        ExecutorCompletionService<Long> service = new ExecutorCompletionService<>(threadPool);


        /*
           split the array of 1k elements into 10 small data chunks (each chunk will have 100 elements)
           and each chunk will be processed by a separate thread concurrently.
         * each loop will create a Task and will have start and end index to produce sum to that task.
         * Task is then submitted to a service, upon completion, the task will be added to a queue.
         */
        for(int i = 0; i < 1000; i += 100) {
            service.submit(CallableObject.getCallableObject(arr, i, i+99));
        }

        long sum = 0l;

        int count = 10;//each thread works on chunk
        long[] mapOutput = new long[10];
        // this while loop will iterate till all tasks have been completed.
        while(count != 0) {
            /*
             * this will poll the completed task from the queue
             * If no task is completed then it will return null.
             */
            Future<Long> future = service.poll();
            if(future != null) {
                Long value = future.get(); // this will return the result from the completed task (callable type).
                mapOutput[count-1] = value;
                count--; // decrementing count value after reading the completed task, if all tasks are completed then count will be 0 and while loop will be terminated.
            }
        }
        threadPool.shutdown(); // shutting down the thread pool once all the tasks are completed.
        return mapOutput;

    }
}
