package foodapp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private String jsonString;
    public JsonReader(String file)
    {
        try {
            String str=new String();
            FileReader reader =new FileReader(file);
            int data = reader.read();
            while (data!=-1)
            {
                str+=(char)data;
                data =reader.read();
            }
            jsonString=str;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public String getJsonString()
    {
        return jsonString;
    }

}