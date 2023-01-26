import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
public class Duke {
    public static void printInListFormat(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, currTask.description());
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Dupe\nWhat can I do for you?");
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            ArrayList<Task> tasks = new ArrayList<>();
            String line = br.readLine();
            while (!line.equals("bye")) {
                String firstWord = line.split(" ")[0];
                if (line.equals("list")) {
                    printInListFormat(tasks);
                } else if (firstWord.equals("mark")) {
                    Task toMarkDone = tasks.get(Integer.parseInt(line.split(" ")[1]) - 1);
                    toMarkDone.markDone();
                    System.out.printf("Nice! I've marked this task as done:\n %s\n", toMarkDone.description());
                } else if (firstWord.equals("unmark")) {
                    Task toMarkUndone = tasks.get(Integer.parseInt(line.split(" ")[1]) - 1);
                    toMarkUndone.markUndone();
                    System.out.printf("OK, I've marked this task as not done yet:\n %s\n", toMarkUndone.description());
                } else if (firstWord.equals("todo")) {
                    Task newTask = new ToDos(line.substring(5));
                    tasks.add(newTask);
                    System.out.printf("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.\n",
                            newTask.description(), tasks.size());
                } else if (firstWord.equals("deadline")) {
                    String taskInfo = line.substring(9);
                    String taskName = taskInfo.split(" /by")[0];
                    String taskDeadline = taskInfo.split("/by ")[1];
                    Task newTask = new Deadlines(taskName, taskDeadline);
                    tasks.add(newTask);
                    System.out.printf("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.\n",
                            newTask.description(), tasks.size());
                } else if (firstWord.equals("event")) {
                    String taskInfo = line.substring(6);
                    String taskName = taskInfo.split(" /")[0];
                    String taskStart = taskInfo.split(" /")[1].substring(5);
                    String taskEnd = taskInfo.split(" /")[2].substring(3);
                    Task newTask = new Events(taskName, taskStart, taskEnd);
                    tasks.add(newTask);
                    System.out.printf("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.\n",
                            newTask.description(), tasks.size());
                } else if (firstWord.equals("delete")) {
                    Task toDelete = tasks.get(Integer.parseInt(line.split(" ")[1]) - 1);
                    tasks.remove(Integer.parseInt(line.split(" ")[1]) - 1);
                    System.out.printf("Noted. I've removed this task:\n %s \n Now you have %d tasks in the list.\n",
                            toDelete.description(), tasks.size());
                } else {
                    throw new DukeException();
                }
                line = br.readLine();
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised");
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (StringIndexOutOfBoundsException oobe) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        }
    }
}
