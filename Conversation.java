// You should **not** update any call signatures in this file
// only modify the body of each function

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a chatbot that gives canned and mirrored responses.
 * 
 * @author Yushan He
 * 
 */

class Conversation implements ConversationRequirements {
  // Attributes 
  ArrayList<String> Transcript; //import arraylist, tutor suggestion because ArrayList, you don't have to initialize a length

  /**
   * Constructor 
   */

  Conversation() {
    /**
   * Array list for the transcript, responses get appended to this Array List
   */
    this.Transcript = new ArrayList<>();
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
        Scanner input = new Scanner(System.in); //we want the scanner object that is an input object, which reads KEYBOARD STROKES.

        System.out.println("How many rounds?");
        int round = input.nextInt();

        String greeting = "Hi there! What's on your mind?";
        String exit = "See ya!";
        Transcript.add(greeting);
        
        System.out.println(greeting);
        String response = input.nextLine();

        while(round!=0){
          response = input.nextLine();
          Transcript.add(response);

          String chatbotResponse = this.respond(response);
          System.out.println(chatbotResponse); //not sure why this is working,,, respond isn't a created attribute yet so why can I call it?
          Transcript.add(chatbotResponse);
          round -= 1;
        }

        System.out.println(exit); //add this later to transcript
        Transcript.add(exit);
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("");
    System.out.println("TRANSCRIPT:");
    for (int i = 0;i<this.Transcript.size();i++) { //Java loop syntax 
      System.out.println(this.Transcript.get(i));
    }

  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  
  public String respond(String inputString) {
    //used for canned responses
    Random random = new Random(); 

    //initialize a return string 
    String returnString = ""; 

    //create an array list for splitting the input string into separate elements
    ArrayList<String> returnSplitString = new ArrayList<>();

    //set them all to lowercase to avoid capitalization caveats
    String lowercasedInputString = inputString.toLowerCase();

    //splitting the string into elements in an array to analyze them individually.
    String[] splitStrings = lowercasedInputString.split(" ");

    if (inputString.contains("i")||inputString.contains("you")||inputString.contains("am")||inputString.contains("my")||inputString.contains("your")||inputString.contains("are")){

      for (int i = 0; i < splitStrings.length; i++) {
        if (splitStrings[i].equals("i")) {
          //in case the response starts with i, an appropriately capitalizes "you" should appear

          if (i == 0) { 
            returnSplitString.add("You");
          } else { 
            returnSplitString.add("you");
          }

      } else if (splitStrings[i].equals("you")) {
        returnSplitString.add("I");
          
      } else if (splitStrings[i].equals("am")) {
        returnSplitString.add("are");   

      } else if (splitStrings[i].equals("my")) {
        returnSplitString.add("your"); 

      } else if (splitStrings[i].equals("your")) {
         returnSplitString.add("my");  

      } else if (splitStrings[i].equals("are")) {
        returnSplitString.add("am");
          
    } else {
      returnSplitString.add(splitStrings[i]);
    } 

    //joins the elements in the array, and adds a question mark at the end
      returnString = String.join(" ", returnSplitString);
      returnString = returnString + "?";

    }

    } else {
      //canned responses,
      String[] cannedResponses = {"Hmm.", "I see", "Mmhm", "Yeah?", "Ok"};

      int index = random.nextInt(cannedResponses.length); // index is randomized, so the responses is randomized...
      String cannedReturnString = cannedResponses[index];
      return cannedReturnString;
    }
    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
