//Provides function to QuestionMain, plays a game of 20 questions, saves and loads binary trees for the game, reports two stats on the game

import java.io.PrintStream;
import java.util.Scanner;

public class QuestionTree {
	// few important variables...
	private int gamesPlayed = 0;
	private int gamesWon = 0;
	QuestionNode overallRoot;
	Scanner userIn;

//definition of question tree
	public QuestionTree(UserInterface ui) {
		overallRoot = new QuestionNode("computer");
		userIn = new Scanner(System.in);
	}

//Plays game(s) of 20 questions with user
	public void play() {
		QuestionNode present = overallRoot;
		String response = "wrong";
		while (present.yes != null && present.no != null) {
			System.out.println(present.data);
			response = userIn.nextLine();
			boolean wrongAnswer = true;
			while (wrongAnswer) {
				if (response.toLowerCase().charAt(0) == 'y') {
					present = present.yes;
					wrongAnswer = false;
				} else if (response.toLowerCase().charAt(0) == 'n') {
					present = present.no;
					wrongAnswer = false;
				} else {
					System.out.print("That wasn't an appropriate response! Please try that again: ");
					response = userIn.nextLine();
				}
			}
		}
		gamesPlayed++;
		System.out.println("Would your object happent to be " + present.data + "?");
		response = userIn.nextLine();
		if (response.charAt(0) == 'y') {
			System.out.println("I win!");
			gamesWon++;
		} else {
			System.out.println("I lose. What is your object? ");
			String answer = userIn.nextLine();
			System.out.println("Type a yes/no question to distinguish your item from " + present.data + ":");
			String question = userIn.nextLine();
			System.out.println("And what is the answer for your object?");
			String yOn = userIn.nextLine();
			boolean wrongAnswer = true;
			while (wrongAnswer) {
				if (yOn.toLowerCase().charAt(0) == 'y' || yOn.toLowerCase().charAt(0) == 'n') {
					wrongAnswer = false;
				} else {
					System.out.println("That wasn't an appropriate answer! Please try writing your answer again:");
					yOn = userIn.nextLine();
				}
			}
			String placeHolder = present.data;
			present.data = question;
			if (yOn.toLowerCase().charAt(0) == 'y') {
				present.yes = new QuestionNode(answer);
				present.no = new QuestionNode(placeHolder);
			} else {
				present.yes = new QuestionNode(placeHolder);
				present.no = new QuestionNode(answer);
			}

		}

	}

//makes a call to saveAssist and saves the present binary tree
	public void save(PrintStream output) {
		if (output == null) {
			throw new IllegalArgumentException();
		} else {
			saveAssist(overallRoot, output);
		}
	}

//constructs the file to be saved
	private void saveAssist(QuestionNode root, PrintStream output) {
		if (root.yes == null && root.no == null) {
			output.print("A:");
			output.println(root.data);
		} else {
			output.print("Q:");
			output.println(root.data);
			saveAssist(root.yes, output);
			saveAssist(root.no, output);
		}
	}

//calls a method to load a binary tree
	public void load(Scanner input) {
		while (input.hasNextLine()) {
			overallRoot = loadAssist(input);
		}
	}

//loads a binary tree from a file
	private QuestionNode loadAssist(Scanner input) {
		String line = input.nextLine();
		String type = line.substring(0, 1);
		String data = line.substring(2);
		QuestionNode root = new QuestionNode(data);
		if (type.contains("Q")) {
			root.yes = loadAssist(input);
			root.no = loadAssist(input);
		}
		return root;
	}

//returns total games
	public int totalGames() {
		return gamesPlayed;
	}

//returns games won by the computer
	public int gamesWon() {
		return gamesWon;
	}
}
