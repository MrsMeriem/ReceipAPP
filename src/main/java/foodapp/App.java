package foodapp;
import java.util.List;

import static foodapp.utils.utilsme.*;

public class App {
    static Favorite favorite=new Favorite();
    static List<Recipe> recipes;
    public static Recipe showRecipes(List<Recipe> recipes)
    {
        for (int i=0;i<recipes.size();i++)
        {
            System.out.println((i+1)+"-"+recipes.get(i).name);
        }
        System.out.println("Choose a recipe");
        System.out.println("press 0 to quit the app");
        int response=response(recipes.size());
        if (response==0)
        {
            return null;
        }
        return recipes.get(response-1);
    }
    public static int showFavourite()
    {
        if (favorite.size()==0)
        {
            System.out.println("no favouritre recipes added please add one");
            return 1;
        }

        for (int i=0;i<favorite.size();i++)
        {
            System.out.println((i+1)+"-"+favorite.get(i).name);
        }
        System.out.println("press 1 to remove a recipe");
        System.out.println("press 2 to see all recipes");
        System.out.println("press 0 to end app");
        int res=response(2);
        if (res==1)
        {
            System.out.println("choose wich one");
            int response=response(favorite.size());
            favorite.remove(favorite.get(response-1).getId());
            return 2;
        }
        else
            return 1;
    }
    public static int Recipeoptions(Recipe recipe)
    {
        if(recipe==null)
            return 0;
        System.out.println(recipe.name);
        System.out.println("choose option");
        System.out.println("press 1 to show recipes");
        System.out.println("press 2 to show favourites");
        System.out.println("press 3 to add to favourites");
        System.out.println("press 4 to see ingredients");
        System.out.println("press 0 to  end app");
        int response=response(5);
        if (response==3)
        {
            if (!favorite.contains(recipe))
            {
                favorite.add(recipe);
            }
            else
                System.out.println("you can't add a recipe that is already in");

            return 2;
        }
        else{
            if (response==4)
            {
                System.out.println("ingredients");
                for (int i=0;i<recipe.ingredients.size();i++)
                {
                    System.out.println(i+":"+recipe.ingredients.get(i).ingredient);
                }
                System.out.println("choose option");
                System.out.println("press 1 to show recipes");
                System.out.println("press 2 to show favourites");
                System.out.println("press 3 to add to favourite");
                int response2=response(3);
                if (response2==3)
                {
                    if (!favorite.contains(recipe))
                    {
                        favorite.add(recipe);
                    }
                    else
                        System.out.println("you can't add a recipe that is already in");

                    return 2;
                }

                return response2;
            }
            else
                return response;
        }
    }
    static void launch(int i)
    {
        if (i==0)
        {
            System.out.println("thanks for using our app");
        }
        if (i==1)
        {
            launch(Recipeoptions(showRecipes(recipes)));
        }
        if (i==2)
        {
            launch(showFavourite());
        }
    }
    public static void main(String[] args) throws Exception {
        recipes=getRecipes(10);
        launch(1);
    }
}
