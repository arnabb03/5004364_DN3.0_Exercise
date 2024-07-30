public class TaskManagementSystem {

    static class Task {
        private int taskId;
        private String taskName;
        private String status;

        public Task(int taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
        }

        public int getTaskId() {
            return taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Task(id=" + taskId + ", name=" + taskName + ", status=" + status + ")";
        }
    }

    static class Node {
        Task task;
        Node next;

        public Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    static class LinkedList {
        private Node head;

        public LinkedList() {
            head = null;
        }

        public void addTask(Task task) {
            Node newNode = new Node(task);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public Task searchTask(int taskId) {
            Node current = head;
            while (current != null) {
                if (current.task.getTaskId() == taskId) {
                    return current.task;
                }
                current = current.next;
            }
            return null;
        }

        public void traverseTasks() {
            Node current = head;
            while (current != null) {
                System.out.println(current.task);
                current = current.next;
            }
        }

        public void deleteTask(int taskId) {
            if (head == null) {
                return;
            }
            if (head.task.getTaskId() == taskId) {
                head = head.next;
                return;
            }
            Node current = head;
            while (current.next != null && current.next.task.getTaskId() != taskId) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList taskList = new LinkedList();

        taskList.addTask(new Task(1, "Task A", "Pending"));
        taskList.addTask(new Task(2, "Task B", "In Progress"));
        taskList.addTask(new Task(3, "Task C", "Completed"));

        System.out.println("All Tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearching for task with ID 2:");
        System.out.println(taskList.searchTask(2));

        System.out.println("\nDeleting task with ID 2:");
        taskList.deleteTask(2);

        System.out.println("\nAll Tasks after deletion:");
        taskList.traverseTasks();
    }
}
