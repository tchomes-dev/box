package guessme;

import java.util.Arrays;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int[] priorGuesses;
  private boolean[] candidates = new boolean[9000];
  
  private int guess;
  private int numGuesses;
  private boolean gameOver;
  
  
  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  //
  public ArrayGame() {
    // TODO
	  guess = 0;
	  numGuesses = 0;
	  gameOver = false;
	  
	  priorGuesses = new int[0];
	  candidates = new boolean[9000];
	  
	  for (int i = 0; i < candidates.length; i++) {
		  candidates[i] = true;
	  }
  }
  /**
   * Converts integers into an array of integers 
   * @param input
   */
  public static int[] intConverter(int input) {
	  int temp = input;
	  int[] result = new int[4];
	  for (int i = 3; i >= 0; i--) {
		  result[i] = temp % 10;
		  temp = temp / 10;
	  } return result;
  }
  public int[] add(int input) {
	  int[] temp;
	  
	  if (priorGuesses == null) {
		  temp = new int[1];
		  temp[0] = input;
	  } else {
		  temp = new int[priorGuesses.length + 1];
		  for (int i = 0; i < priorGuesses.length; i++) {
			  temp[i] = priorGuesses[i];
		  } temp[temp.length - 1] = input;
	  } return priorGuesses = temp;	  
  }

  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
	  guess = 0;
	  numGuesses = 0;
	  gameOver = false;
	  
	  candidates = new boolean[9000];
	  priorGuesses = new int[0];
	  
	  for (int i = 0; i < candidates.length; i++) {
		  candidates[i] = true;
	  }
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
	  for (int i = 0; i < priorGuesses.length; i++) {
		  if (priorGuesses[i] == n) {
			  return true;
		  }
	  } return false;
  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {return numGuesses;}

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
	  int matches = 0;
	  int[] ArrayA = intConverter(a); 
	  int[] ArrayB = intConverter(b);
	  
	  for (int i = 0; i < 4; i++) {
		  if (ArrayA[i] == ArrayB[i]) {
			  matches++;
		  }
	  } return matches;
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {return gameOver;}

  /**
   *  Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    // TODO: add guess to the list of prior guesses.
	  if (priorGuesses.length == 0) {
		  guess = 1000;
		  numGuesses++;
		  priorGuesses = add(guess);
	  }else {
		  for (int i = 0; i < candidates.length; i++) {
			  if (candidates[i]) {
				  numGuesses++;
				  guess = i + 1000;
				  priorGuesses = add(guess);
				  return guess;
			  }
		  }
	  } return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    // TODO
	  if (nmatches == 4) {
		  gameOver = true;
		  return true;
	  }
	  
	  boolean error = false;
	  
	  for (int i = 0; i < candidates.length; i++) {
		  if (numMatches(i + 1000, priorGuesses[priorGuesses.length - 1]) != nmatches) {
			  candidates[i] = false;
		  } else {
			  if(candidates[i]) {
				  if(!error) {
					  error = !error;
				  }
			  }	
		  }
	  }
	  
	  if(!error) {
		  gameOver = true;
		  return false;
	  } return true;
  }

  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    // TODO
	  if(priorGuesses.length == 0) {
		  return null;
	  } return priorGuesses;
  }
}
