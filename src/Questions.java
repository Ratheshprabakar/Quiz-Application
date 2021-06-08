import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Rathesh Prabakar
 *
 */
public class Questions {
	
public void addQuestionToDB() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int count = 0;

		try {

			// Step 1
			Class.forName("com.mysql.jdbc.Driver");

			// Step 2 Establish the connection with the database with user and password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quiz_app?autoReconnect=true&useSSL=false", "username",
					"password");

			// Step 3 Issuing Query
			String query = "insert into quiz_questions values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			
			String filePath = "/home/rathesh/Desktop/questions.txt";
			
			BufferedReader input = new BufferedReader(new FileReader(filePath));
			String[] fileData = null;
			String lineData = null;
			while((lineData = input.readLine()) != null) {
				fileData = lineData.split(",");
				// Step 4 : Execute and process the query
				pstmt.setInt(1,Integer.parseInt(fileData[0])); //question_no
				pstmt.setString(2, fileData[1]); //question
				pstmt.setString(3, fileData[2]); //first_choice
				pstmt.setString(4, fileData[3]); //Second_Choice
				pstmt.setString(5, fileData[4]); //Third_Choice
				pstmt.setString(6, fileData[5]); //Fourth_Choice
				pstmt.setString(7, fileData[6]); //Answer option
	
				count = pstmt.executeUpdate();
				count++;
			}
			if (count != 0)
				System.out.println("Inserted Successfully "+count+" records");
			else
				System.out.println("Error");
			input.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Step 5 : Close all the objects
		finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

public int conductQuiz() {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet questions = null;
	 int quizMark =0;
		try {
			
			//Step 1
			Class.forName("com.mysql.jdbc.Driver");
			
			//Step 2 Establish the connection with the database with user and password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ "quiz_app?autoReconnect=true&useSSL=false","root","Onebill@2020");
			
			// Step 3 Issuing Query
			String query = "select * from quiz_questions";
			
			// Step 4 : Execute and process the query
			 stmt = con.createStatement();
			 questions = stmt.executeQuery(query);
			 Scanner input = new Scanner(System.in);
			 while(questions.next())
				{
				 	System.out.println("\n--------------------------------------------");
					System.out.println(questions.getInt(1)+") "+questions.getString(2));
					System.out.println("\nThe Options are");
					System.out.println("1. "+questions.getString("choice1")); //Choice 1
					System.out.println("2. "+questions.getString("choice2")); // Choice 2
					System.out.println("3. "+questions.getString("choice3")); //Choice 3
					System.out.println("4. "+questions.getString("choice4")); //Choice 4
					
					System.out.println("\nEnter your answer");
					String userAnswer = input.nextLine();
					
					if(userAnswer.equalsIgnoreCase(questions.getString("choice1")) || userAnswer.equalsIgnoreCase(questions.getString("choice2")) || 
							userAnswer.equalsIgnoreCase(questions.getString("choice3")) ||
							userAnswer.equalsIgnoreCase((questions.getString("choice4") )))
					{
						String correctAnswer = questions.getString(7); // Correct Answer
						if(userAnswer.equalsIgnoreCase(correctAnswer))
							quizMark++;
						System.out.println(quizMark);
					}
					else
					{
						System.out.println("Please enter valid option");
					}
					
				}
			input.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//Step 5 : Close all the objects
		finally {
			try {
				if(con!=null)
					con.close();
				if(stmt!= null)
					stmt.close();
				if(questions!=null)
					questions.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return quizMark;
}
}

