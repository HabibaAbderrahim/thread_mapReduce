import java.util.concurrent.*;

public class Map {

    public static long[] mapTask(int[] arr) throws InterruptedException, ExecutionException {

        /* create thread pool of 10 threads, each thread will sum 100 elements
         * and main thread will combine results of 10 threads to produce final output.
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        /*
         * Creating ExecutorCompletionService to add all completed tasks in a queue.
         */
        ExecutorCompletionService<Long> service = new ExecutorCompletionService<>(threadPool);


        /*
         * Below for loop will have 10 loops,
         * each loop will create a Task and will have start and end index to produce sum to that task.
         * Task is then submitted to a service, upon completion, the task will be added to a queue.
         */
        for(int i = 0; i < 1000; i += 100) {
            service.submit(getCallableObject(arr, i, i+99));
        }

        long sum = 0l;

        int count = 10;
        long[] mapOutput = new long[10];
        while(count != 0) { // this while loop will iterate till all tasks have been completed.

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
