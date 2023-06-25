package foodapp.utils;
import foodapp.Ingredient;
import foodapp.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class utilsme {
    public static String callUrl="https://api.spoonacular.com/recipes/random?apiKey=bed0513a097d414ea107b26908404df0&number=";
    public static int response(int size)
    {

        Scanner scanner =new Scanner(System.in);
        String result =scanner.next();
        try {
            int value = Integer.parseInt(result);
            if (value>size)
            {
                System.out.println("number has to be less than "+size);
                return response(size);
            }
            else
                return value;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            return response(size);
        }
    }
    public static String Call(String callurl,int number) throws Exception
    {
        URL url = new URL(callurl+number);
        URLConnection connection = url.openConnection();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())))
        {
            String line;
            if ((line = in.readLine()) != null) {
                return line;
            }
            else return "something went wrong";
        }
    }

    public static List<Recipe> getRecipes(int number) throws Exception
    {
        String response = Call(callUrl,number);
        List<Recipe> result=new ArrayList<>();
        JSONObject jsonObject=new JSONObject(response);
        JSONArray recipes=jsonObject.getJSONArray("recipes");
        for (int i=0;i<recipes.length();i++)
        {
            JSONObject object=recipes.getJSONObject(i);
            int id=object.getInt("id");
            String image="https://spoonacular.com/recipeImages/"+id+"-312x231.jpg";
            int price=object.getInt("pricePerServing");
            int time=object.getInt("readyInMinutes");
            String name=object.getString("title");
            boolean vegan=object.getBoolean("vegan");
            boolean cheap=object.getBoolean("cheap");
            boolean veryPopular= object.getBoolean("veryPopular");
            boolean veryHealthy= object.getBoolean("veryHealthy");
            JSONArray extendedIngredients=object.getJSONArray("extendedIngredients");
            List<Ingredient> ingredients=new ArrayList<>();
            for (int j=0;j<extendedIngredients.length();j++)
            {
                JSONObject ingredientsplus=extendedIngredients.getJSONObject(j);
                Ingredient ingredient=new Ingredient(ingredientsplus.getString("original"));
                ingredients.add(ingredient);
            }
            List<String> cuisines=new ArrayList<>();
            JSONArray cuisinesplus= object.getJSONArray("cuisines");
            for (int k=0;k<cuisinesplus.length();k++)
            {
                cuisines.add(cuisinesplus.getString(k));
            }
            JSONArray analyzedInstr= object.getJSONArray("analyzedInstructions");
            List<String> instructions=new ArrayList<>();
            for (int l=0;l<analyzedInstr.length();l++)
            {
                JSONObject instru=analyzedInstr.getJSONObject(l);
                JSONArray steps=instru.getJSONArray("steps");
                for (int m=0;m<steps.length();m++)
                {
                    String step=steps.getJSONObject(m).getString("step");
                    instructions.add(step);
                }
            }
            Recipe recipe=new Recipe(name,ingredients,instructions,cuisines,image,id,vegan,cheap,veryHealthy,veryPopular,time,price);
            result.add(recipe);
        }
        return result;
    }
}
