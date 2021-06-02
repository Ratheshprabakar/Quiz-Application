import java.util.Scanner;
import java.sql.SQLException;

/**
 * 
 */

/**
 * @author Rathesh Prabakar
 *
 */
public class Main extends Questions {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// Questions is added into database from input text file
		Questions q = new Questions();
		q.addQuestionToDB();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n-----Welcome to Quiz-----\n");
		System.out.println("Shall we start our quiz(Yes/No)\t");
		String response = input.next().toLowerCase();
		
		System.out.println("Enter Your Name to continue\t");
		String userName = input.next();
		
		if(response.equals("yes"))
		{
			int studentMark= q.conductQuiz();
			System.out.println("Congratulations, "+ userName+ " You did it, You scored "+ studentMark);
			
		}
		else
			System.out.println("OOPS, I guess your brain is now tired, Let's take a break and come again, Thank you");
		
		input.close();
	}

}
