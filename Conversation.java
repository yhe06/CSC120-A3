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
  ArrayList<String> Transcript; 

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
        String userResponse = input.nextLine();

        while(round!=0){
          userResponse = input.nextLine();

          Transcript.add(userResponse);
          String chatbotResponse = this.respond(userResponse);

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
    //Java loop syntax 
    for (int i = 0;i<this.Transcript.size();i++) {
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
    //split the lowercasedInputStrings and input it in a new array list

    ArrayList<String> splitStrings = new ArrayList<>();

    for (String strings : lowercasedInputString.split(" ")) {
        splitStrings.add(strings);
}

    if (splitStrings.contains("i")||splitStrings.contains("you")||splitStrings.contains("am")||splitStrings.contains("my")
      ||splitStrings.contains("your")||splitStrings.contains("are")){

      for (int i = 0; i < splitStrings.size(); i++) {
        
        if (splitStrings.get(i).equals("i")) {
          //in case the response starts with i, an appropriately capitalizes "you" should appear

          if (i == 0) { 
            returnSplitString.add("You");
          } else { 
            returnSplitString.add("you");
          }

      } else if (splitStrings.get(i).equals("you")) {
        returnSplitString.add("I");
          
      } else if (splitStrings.get(i).equals("am")) {
        returnSplitString.add("are");   

      } else if (splitStrings.get(i).equals("my")) {
        returnSplitString.add("your"); 

      } else if (splitStrings.get(i).equals("your")) {
         returnSplitString.add("my");  

      } else if (splitStrings.get(i).equals("are")) {
        returnSplitString.add("am");
          
    } else {
      returnSplitString.add(splitStrings.get(i));
    } 

    //joins the elements in the array, and adds a question mark at the end
    //need work to make capitalization correct 

      returnString = String.join(" ", returnSplitString);

      //automatically returns the first letter as uppercase, disregarding what the user inputted. Look over more.
      returnString = Character.toUpperCase(returnString.charAt(0)) + returnString.substring(1);

      returnString = returnString + "?";

    }

    } else {
      //canned responses,
      String[] cannedResponses = {"Hmm.", "I see", "Mmhm", "Yeah?", "Ok", "Cool"};

      // index is randomized, so the responses is randomized...
      int index = random.nextInt(cannedResponses.length);
      
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
