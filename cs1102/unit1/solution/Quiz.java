import java.util.Scanner;


public class Quiz {

    static double score = 0; // Initialize score to 0
    static int numberOfQuestions = 0; // Total number of questions in the quiz

    static Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

    // Question class to hold question data
    static class Question {
        String question;
        String[] options;
        String answer;

        Question(String question, String[] options, String answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }


        
    }

    // Method to get the index of the input letter
    static int getInputLettersIndex(String input) {
        String[] inputLetters = {"a", "b", "c", "d"};
        for (int i = 0; i < inputLetters.length; i++) {
            if (input.equalsIgnoreCase(inputLetters[i])) {
                return i;
            }
        }
        return -1; // Return -1 if input is not valid
    }

    // Method to display the question and options
    static void displayQuestion(Question questionObject) {
        
        String question = questionObject.question;
        String[] options = questionObject.options;
        
        String[] inputLetters = {"a", "b", "c", "d"};
        // Display the question and options
        System.out.println(1 + ". " + question);
        // Display the options with letters
        for (int i = 0; i < options.length; i++) {
            String option = inputLetters[i] + ") " + options[i];
            System.out.println(option);
        }
    }

    // Method to check the user's answer
    static void checkAnswer(Question questionObject) {
        //String question = questionObject.question;
        String[] options = questionObject.options;
        String answer = questionObject.answer;
        // Get the user's answer
        String userAnswer = scanner.nextLine();
        // Check if the answer is valid
        switch (userAnswer.toLowerCase()) {
            case "a": 
            case "b":
            case "c":
            case "d":
                int index = getInputLettersIndex(userAnswer);
                String selectedOption = options[index];
                System.out.println("You selected: " + selectedOption);
                // Check if the answer is correct   
                if (selectedOption.equals(answer)) {
                    System.out.println("Correct!🟩 The answer is " + answer + ".");
                    updateScore();
                } else {
                    System.out.println("Incorrect!🔴 The correct answer is " + answer + ".");
                } 
                break;
            case "q":
                System.out.println("Exiting the quiz.");
                // Close the scanner to prevent resource leaks
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input! Please select a valid option (a, b, c, d) or q to exit.");
                checkAnswer(questionObject);
                break;
        }
    }

    // Method to update the score
    static void updateScore() {
        score += 100 / numberOfQuestions;
    }

    // Method to get the questions
    static Question[] getQuestions() {
        Question[] questions = new Question[5];
        questions[0] = new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Jupiter");
        questions[1] = new Question("Who wrote 'Romeo and Juliet'?", new String[]{"William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"}, "William Shakespeare");
        questions[2] = new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "NaCl"}, "H2O");
        questions[3] = new Question("Which country is known as the Land of the Rising Sun?", new String[]{"China", "Japan", "South Korea", "Thailand"}, "Japan");
        questions[4] = new Question("What is the square root of 64?", new String[]{"6", "7", "8", "9"}, "8");
        return questions;
    }

    static void endQuiz() {
        System.out.println("You have reach the end of this quiz! Your score is: " + score + "/" + 100);
        System.out.println("Thank you for playing!");
        handleStartInput();
    }

    // Method to start the quiz
    static void startQuiz() {
        Question[] questions = getQuestions();

        numberOfQuestions = questions.length; // Update the number of questions
        
        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            // Display the question
            displayQuestion(question);
            // Check the answer
            checkAnswer(question);
        }

        // End the quiz
        endQuiz();
    }

    // Method to handle the start input
    static void handleStartInput() {

            System.out.println("Please type 's' to begin the quiz or q to exit:");

            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "s":
                    System.out.println("Starting the quiz...");
                    try {
                        // Simulate a delay of 2 seconds
                        // This is just for demonstration purposes
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    // Call the method to start the quiz
                    startQuiz();
                    break;
                case "q":
                    System.out.println("Exiting the quiz.");
                    // Close the scanner to prevent resource leaks
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
                    handleStartInput();
                    break;
            }

    }

     
    
    public static void main(String[] args) {
        

        handleStartInput();
    }
}

