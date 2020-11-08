package Assignment3Starter;
import Assignment3Starter.lib.LoseGameException;
import Assignment3Starter.lib.ServerIsFullException;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import org.json.*;
import java.util.Base64;
import javax.imageio.*;
import java.awt.image.BufferedImage;

/**
 * The ClientGui class is a GUI frontend that displays an image grid, an input text box,
 * a button, and a text area for status. 
 * 
 * Methods of Interest
 * ----------------------
 * show(boolean modal) - Shows the GUI frame with the current state
 *     -> modal means that it opens the GUI and suspends background processes. Processing 
 *        still happens in the GUI. If it is desired to continue processing in the 
 *        background, set modal to false.
 * newGame(int dimension) - Start a new game with a grid of dimension x dimension size
 * insertImage(String filename, int row, int col) - Inserts an image into the grid
 * appendOutput(String message) - Appends text to the output panel
 * submitClicked() - Button handler for the submit button in the output panel
 * 
 * Notes
 * -----------
 * > Does not show when created. show() must be called to show the GUI.
 * 
 */
public class ClientGui implements Assignment3Starter.OutputPanel.EventHandlers {
  JDialog frame;
  PicturePanel picturePanel;
  OutputPanel outputPanel;
  TCPClient tcpCl;
  JSONObject question;

  public ClientGui(String[] args) throws Exception {
    tcpCl = new TCPClient(args, this);
    runGUI();
  }

  private void errorMessageException(Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(frame, "Invalid Argument");
  }

  public void runGUI() {
    String value = JOptionPane.showInputDialog("Select number of puzzle boards between 2 - 3"); 
    if(value == null || (value != null && ("".equals(value)))) {
      tcpCl.end();
      System.exit(0);
    }
    try {
      int count = Integer.parseInt(value);
      if (count > 3 || count < 2) {
        throw new IllegalArgumentException();
      }
      tcpCl.createGame(count);
      clientGui();
      newGame(count);
    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(frame, "Invalid input");
      runGUI();
    } catch (ServerIsFullException e) {
      JOptionPane.showMessageDialog(frame, "Server is busy, try again later");
      runGUI();
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(frame, "Invalid Entry, please try again!");
      runGUI();
    } catch (Exception e) {
      errorMessageException(e);
      runGUI();
    } 
  }
  public void mathQuestion() throws IOException {
    question = tcpCl.getQuestion();
    Integer quest1 = question.getInt("quest1");
    Integer quest2 = question.getInt("quest2");
    Integer quest3 = question.getInt("quest3");
    Integer quest4 = question.getInt("quest4");
    outputPanel.appendOutput("what is " + quest1 + " + " + quest2 +"*"+ quest3 + " + " + quest4 + "?");
  }

  public void clientGui() {
    frame = new JDialog(); 
    frame.setLayout(new GridBagLayout());
    frame.setMinimumSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // setup the top picture frame
    picturePanel = new PicturePanel();
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 0.25;
    frame.add(picturePanel, c);

    // setup the input, button, and output area
    c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    c.weighty = 0.75;
    c.weightx = 1;
    c.fill = GridBagConstraints.BOTH;
    outputPanel = new OutputPanel();
    outputPanel.addEventHandlers(this);
    frame.add(outputPanel, c);
  }
  /**
   * Shows the current state in the GUI
   * @param makeModal - true to make a modal window, false disables modal behavior
   */
  public void show(boolean makeModal) {
    frame.pack();
    frame.setModal(makeModal);
    frame.setVisible(true);
  }

  /**
   * Creates a new game and set the size of the grid 
   * @param dimension - the size of the grid will be dimension x dimension
   */
  public void newGame(int dimension) {
    try {
      picturePanel.newGame(dimension);
      outputPanel.appendOutput("Started new game with a " + dimension + "x" + dimension + " board.");
      mathQuestion();
      
    } catch (Exception e) {
      errorMessageException(e);
    }
  }
  
  /**
   * Insert an image into the grid at position (col, row)
   * 
   * @param filename - filename relative to the root directory
   * @param row - the row to insert into
   * @param col - the column to insert into
   * @return true if successful, false if an invalid coordinate was provided
   * @throws IOException An error occured with your image file
   */
  public boolean insertImage(BufferedImage img, int row, int col) throws IOException {
    String error = "";
    try {
      // insert the image
      if (picturePanel.insertImage(img, row, col)) {
        // put status in output
        outputPanel.appendOutput("Inserting an image (" + row + ", " + col + ")");
        return true;
      }
    } catch(PicturePanel.InvalidCoordinateException e) {
      // put error in output
      error = e.toString();
    }
    outputPanel.appendOutput(error);
    return false;
  }

  /**
   * Submit button handling
   * 
   * Change this to whatever you need
   */
  @Override
  public void submitClicked() {
    // Pulls the input box text
    String input = outputPanel.getInputText();
    // if has input
    if (input.length() > 0) {
      // append input to the output panel
      outputPanel.appendOutput(input);
      try {
        JSONObject head = tcpCl.CheckAnswer(input);
        Boolean corVal = head.getBoolean("corVal");
        if (!corVal) {
          outputPanel.appendOutput("Incorrect, try again");
        } else {
          String imageB64 = head.getString("image");
          byte[] imageBytes = Base64.getDecoder().decode(imageB64);
          BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
          int row = head.getInt("row");
          int col = head.getInt("col");
          insertImage( img, row, col);
          outputPanel.appendOutput("Correct!");
          Boolean win = head.getBoolean("win");
          if (win) {
            Object[] options = {"OK"};
            int op = JOptionPane.showOptionDialog(null, "You win!\nGame will exit now", "Win!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(op == JOptionPane.OK_OPTION) {
              System.exit(0);
            }
          }
          mathQuestion();
        }
      } catch (LoseGameException e) {
        Object[] options = {"OK"};
        int op = JOptionPane.showOptionDialog(null, "You lose!\nGame will exit now", "Lose!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(op == JOptionPane.OK_OPTION) {
          System.exit(0);
        }
      } catch (Exception e) {
        errorMessageException(e);
      }
      // clear input text box
      outputPanel.setInputText("");
    }
  }
  /**
   * Key listener for the input text box
   * 
   * Change the behavior to whatever you need
   */
  @Override
  public void inputUpdated(String input) {
    if (input.equals("surprise")) {
      outputPanel.appendOutput("You found me!");
    }
  }

  public static void main(String[] args) throws Exception {
    // create the frame
    ClientGui main = new ClientGui(args);
    
    // show the GUI dialog as modal
    main.show(true);
  }
}
