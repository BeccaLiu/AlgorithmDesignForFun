package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rliu on 11/28/16.
 * Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class CourseScheduleI {
    public static void main(String[] args) {
        /* 1 <- 2 -> 4
         * ^    ^    |
         * |    |    v
         * 0 -> 3 <- 5
         * loop in [2 4 5 3]
         */
        int course = 6;
        int[][] prerequisites = {{1, 0}, {3, 0}, {1, 2}, {2, 3}, {4, 2}, {5, 4}, {3, 5}};
        System.out.println(validCourse(course, prerequisites));
        System.out.println(bfsTakeCourse(course, prerequisites));
    }

    /* can represent the course as a directed graph
     * Keys is if there is a loop in the graph, which mean each course in the loop is prerequisites of another class, hence impossible to complete the course
     * Data Structure needed: Topological order
     * if topological order exist-> true;
     * DFS and use true or false to mark visited
     * problem exist: when we visit from 0 to 1 and market 0,1 as visited, when we start from 2 and visited 1, can we assume there is no loop because 1 is visited? the answer is no
     * ex. start from 0, and mark 0 and 3 as visited, when start from 2 visit 4 and then 5 and then 3, if there is only two states of the node, we will fail to find the loop2,4,5,3
     * states: visited,not visited,on current path
     */
    public static boolean validCourse(int course, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> courses = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            if (!courses.containsKey(prerequisites[i][1]))
                courses.put(prerequisites[i][1], new ArrayList<>());
            courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int[] checker = new int[course];
        for (int i = 0; i < course; i++) {
            if (checker[i] == 0 && !dfsHelper(i, courses, new int[course]))
                return false;
        }
        return true;
    }

    public static boolean dfsHelper(int courseId, HashMap<Integer, ArrayList<Integer>> prerequisites, int[] states) {
        ArrayList<Integer> list = prerequisites.get(courseId);
        states[courseId] = -1;
        for (int j = 0; list != null && j < list.size(); j++) {
            if (states[list.get(j)] == -1 || dfsHelper(list.get(j), prerequisites, states) == false)
                return false;
        }
        states[courseId] = 1;
        return true;
    }

    //BFS is a little bit different
    //we can not chose random start course, we need to choose the course that is has no prerequisite course
    //if the there is no start course, means we can not even start take course
    //we can use an array to count the prerequisite course
    //
    public static boolean bfsTakeCourse(int course, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> coursesInfo = new ArrayList<>();
        int[] inCount = new int[course];
        for (int i = 0; i < course; i++)
            coursesInfo.add(new ArrayList<Integer>());

        for (int i = 0; i < prerequisites.length; i++) {
            inCount[prerequisites[i][0]]++;
            coursesInfo.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        //queue maintains all course that inCount ==0
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inCount.length; i++) {
            if (inCount[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int curCourse = queue.remove();
            ArrayList<Integer> nextCourses = coursesInfo.get(curCourse);
            for (int i = 0; i < nextCourses.size(); i++) {
                int next = nextCourses.get(i);
                inCount[next]--;
                //only add inCount ==0 course to queue
                if (inCount[next] == 0)
                    queue.add(next);
            }
        }

        for (int i = 0; i < inCount.length; i++) {
            if (inCount[i] != 0) {
                return false;
            }
        }
        return true;

    }
}
