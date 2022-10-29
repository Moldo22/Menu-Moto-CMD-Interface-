import java.io.FileWriter;
import java.io.IOException;

public class Logging extends Message{
    private static Logging instance;
    private Logging(){

    }
    public static Logging getInstance(){
        if (null == instance){
            instance = new Logging();
        }
        return instance;
    }
    void setMessage(String msg){
        try {
            FileWriter myWriter = new FileWriter("Logging.txt", true);
            myWriter.write(msg);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
