package MainJING;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 2/11/17.
 * docker send request machine id, and send back machine id. (类似 data strucutre design)：
 * func1 - request machine, return the smallest id of the available machines
 * func2 - return machine, make the returned machine available
 * follow up: try to reduce space complexity (我用heap做的，最后面试官给的优化结果是类似bucket check)
 */
public class DockerHelper {
    PriorityQueue<Machine> queue;

    DockerHelper(int machineSize) {
        queue = new PriorityQueue<>(machineSize, new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return o1.id - o2.id;
            }
        });
        for (int i = 0; i < machineSize; i++) {
            queue.offer(new Machine(i));
        }
    }

    public static void main(String[] args) {
        DockerHelper docker = new DockerHelper(10);
        Machine first = docker.requestMachine();
        Machine sec = docker.requestMachine();
        System.out.println(first.id);
        System.out.println(sec.id);
        docker.returnMachine(first);
        System.out.println(docker.requestMachine().id);
    }

    public Machine requestMachine() {
        queue.peek().isAvailable = false;
        return queue.poll();
    }

    public void returnMachine(Machine m) {
        m.isAvailable = true;
        queue.offer(m);
    }

    public class Machine {
        int id;
        boolean isAvailable;

        Machine(int id) {
            this.id = id;
            isAvailable = true;
        }
    }
}
