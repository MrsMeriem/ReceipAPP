package foodapp;

import java.util.ArrayList;
import java.util.List;

public class Favorite {
    private List<Recipe> recipes =new ArrayList<>();
    public Favorite(){}
    public List<Recipe> recipes()
    {
        return recipes;
    }
    public void add(Recipe addin)
    {
        recipes.add(addin);
    }
    public void string()
    {
        for (int i=0;i<recipes.size();i++)
        {
            System.out.println(recipes.get(i).name);
        }
        System.out.println("-------------------------");
    }
    public int size()
    {
        return recipes.size();
    }
    public Recipe get(int index)
    {
        return recipes.get(index);
    }
    public boolean contains(Recipe recipe)
    {
        return recipes.contains(recipe);
    }
    public void remove(int id)
    {
        for (int i=0;i<recipes.size();i++)
        {
            if (recipes.get(i).getId()==id)
            {
                recipes.remove(i);
                break;
            }
        }
    }
}
