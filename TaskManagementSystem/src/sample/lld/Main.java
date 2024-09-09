package sample.lld;

import java.util.ArrayList;
import java.util.HashMap;

class TaskManagementSystem{
	private HashMap<Integer, ArrayList<Task>> usersToTasks;
	private HashMap<Integer, ArrayList<Task>> managerToTasks;
	private ArrayList<Task> tasks;
	
	//Constructor
	public TaskManagementSystem() {
		this.usersToTasks = new HashMap<Integer, ArrayList<Task>>();
		this.tasks = new ArrayList<Task>();
	}
	
	
	public boolean createTask(int managerId, int userId, String name, String description) {
		Task newTask = new Task(managerId, userId, name, description);
		return true;
	}
	
	public boolean DeleteTask(int taskId) {
		return true;
	}
	
	public User addUser(String name, String email, String designation) {
		User newUser = new User(name, email, designation);
		return newUser;
	}
	
	public boolean removeUser(int userId) {
		return true;
	}
	
	public ArrayList<Task> getAssignedTask(int userId){
		return usersToTasks.get(userId);
	}
	
	public ArrayList<Task> getManagedTask(int userId){
		return managerToTasks.get(userId);
	}
	
	
	
}

class User{
	private static int uid = 0;
	
	private int userId;
	private String name;
	private String email;
	private String Designation;
	
	
	public User(String name, String email, String designation) {
		this.userId = uid;
		this.name = name;
		this.email = email;
		this.Designation = designation;
		uid++;
	}
	
}

class Manager extends User{

	public Manager(String name, String email, String designation) {
		super(name, email, designation);
	}
	
}

class Employee extends User{

	public Employee(String name, String email, String designation) {
		super(name, email, designation);
	}
	
}

enum status{
	PENDING,
	INPROGRESS,
	INREVIEW,
	COMPLETE
}

class Task{
	private int id;
	private String name;
	private String description;
	private int AssignedTo;
	private int managerId;
	private Enum status;
	private ArrayList<Comment> comments;
	
	public Task(int managerId, int userId, String name, String description) {
		this.managerId = managerId;
		this.AssignedTo = userId;
		this.name = name;
		this.description = description;
	}
	
	public boolean assignTask(int userId) {
		AssignedTo = userId;
		return true;
	}
	
	public boolean changeStatus(Enum newStatus) {
		this.status = newStatus;
		return true;
	}
	
	public Boolean addComment(String commentData) {
		Comment newComment = new Comment(commentData);
		comments.add(newComment);
		return true;
	}
	
	public boolean ModifyTask() {
		return true;
	}
	
	public int getid() {
		return id;
	}
}

class Comment{
	private static int uid = 0;
	
	private int id;
	private String data;
	
	public Comment(String data) {
		this.id = uid;
		this.data = data;
		uid++;
	}
}

public class Main {

	public static void main(String[] args) {
		TaskManagementSystem taskManagementSystem = new TaskManagementSystem();
	}

}
