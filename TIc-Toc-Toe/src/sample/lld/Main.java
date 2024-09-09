package sample.lld;

import java.util.ArrayList;
import java.util.HashMap;

class GameManager{
	private HashMap<Integer, Game> games;
	private HashMap<Integer, ArrayList<Game>> players;
	
	public GameManager() {
		games = new HashMap<Integer, Game>();
		players = new HashMap<Integer, ArrayList<Game>>();
	}
	
	public boolean createGame(Player p1, Player p2, int rows, int cols) {
		Game newGame = new Game(p1, p2, rows, cols);
		games.put(newGame.getId(), newGame);
		
		players.computeIfAbsent(p1.getId(), k -> new ArrayList<Game>()).add(newGame);
		players.computeIfAbsent(p2.getId(), k -> new ArrayList<Game>()).add(newGame);
		return true;
	}
	
	public ArrayList<Game> getGameHistory(Player p){
		return players.get(p);
	}
	
	public void markPosition(int gameId, int row, int col) {
		Game currGame = games.get(gameId);
		
		currGame.markPosition(row, col);
		
		if(currGame.getStatus() == Status.Complete) {
			System.out.println("Game Completed!");
			System.out.println(currGame.getWinner() + " won the game!");
		}
	}
	
}

class Game{
	private static int uid = 0;
	
	private int id;
	private Board board;
	private ArrayList<Player> players;
	private Status status;
	private Player playerTurn;
	private Player winner;
	
	public Game(Player p1, Player p2, int rows, int cols) {
		id = uid++;
		players = new ArrayList<>();
		players.add(p1);
		players.add(p2);
		
		board = new Board(rows, cols);
		
		status = Status.Inprogress;
		playerTurn = p1;
		winner = null;
	}
	
	public Player changeTurn() {
		for(Player p : players) {
			if(p != playerTurn) {
				playerTurn = p;
			}
		}
		
		return playerTurn;
	}
	
	public boolean changeStatus(Status newStatus) {
		status = newStatus;
		return true;
	}
	
	public void markPosition(int row, int col) {
		board.markPosition(row, col);
		
		if(board.checkWin()) {
			changeStatus(Status.Complete);
			winner = playerTurn;
			return;
		}
		
		changeTurn();
	}
	
	public Status getStatus(){
		return status;
	}
	
	public Player getWinner() {
		if(winner == null) {
			return null;
		}
		return winner;
	}
	
	public int getId() {
		return id;
	}
}

enum Status{
	Inprogress,
	Complete
}

class Board{
	private int rows;
	private int cols;
	private ArrayList<ArrayList<Integer>> boardStructure;
	
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		for(int i=0; i < rows; i++) {
			ArrayList<Integer> rowList = new ArrayList<>();
			
			for(int j=0; j < cols; j++) {
				rowList.add(0);
			}
			boardStructure.add(rowList);
		}
	}
	

	public boolean checkWin() {
		//check rows, cols, diagonals
		//if winner find return true
		
		return false;
	}


	public void markPosition(int row, int col) {
		boardStructure.get(row).set(col, 1);
	}
	
	public ArrayList<ArrayList<Integer>> getBoard(){
		return boardStructure;
	}
}

class Player{
	private static int uid = 0;
	
	private int id;
	private String name;
	private String email;
	
	public Player() {
		id = uid++;
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
