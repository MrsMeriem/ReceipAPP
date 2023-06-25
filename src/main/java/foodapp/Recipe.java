package foodapp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public Button savebtn;
    public Button favbtn;
    boolean vegan;
    boolean cheap;
    boolean veryPopular;
    boolean veryHealthy;
    List<String> cuisines=new ArrayList<>();
    List<String> instructions=new ArrayList<>();
    int id;
    int time;
    int price;
    String image;
    public String name;
    List<Ingredient> ingredients;
    public Recipe(String name,List<Ingredient> ingredients,List<String> instructions,List<String> cuisines,String image,int id,boolean vegan,boolean cheap,boolean veryHealthy,boolean veryPopular,int time,int price)
    {
        this.price=price;
        this.time=time;
        this.id=id;
        this.instructions=instructions;
        this.ingredients=ingredients;
        this.cuisines=cuisines;
        this.name=name;
        this.image= image;
        this.vegan=vegan;
        this.cheap=cheap;
        this.veryHealthy=veryHealthy;
        this.veryPopular=veryPopular;
    }
    public int getPrice()
    {
        return this.price;
    }
    public boolean isVeryHealthy()
    {
        return veryHealthy;
    }
    public int getTime()
    {
        return this.time;
    }
    public boolean isVeryPopular()
    {
        return this.veryPopular;
    }
    public String getName()
    {
        return name;
    }
    public Recipe(String name,List<Ingredient> ingredients)
    {
        this.name=name;
        this.ingredients =ingredients;
    }
    public Recipe(String name,List<Ingredient> ingredients,int id,String image)
    {
        this.id=id;
        this.image=image;
        this.name=name;
        this.ingredients =ingredients;
    }
    public Recipe(String name,int id,String image)
    {
        this.id=id;
        this.image=image;
        this.name=name;
    }
    public int getId()
    {
        return id;
    }
    public List<Ingredient> ingredients()
    {
        return ingredients;
    }
    public List<String> getInstructions(){return instructions;}
    public void string()
    {
        System.out.println("Id:"+id+"\n"+"Name:"+name+"\n"+"Image:"+image+"\n"+"Ingredients:");
        for (int i=0;i<ingredients.size();i++)
        {
            System.out.print(ingredients.get(i).ingredient+"\n");
        }
        System.out.println("Steps:");
        for (String step:instructions)
        {
            System.out.println(step);
        }
    }
    public String getImage()
    {
        return image;
    }
    public VBox Item(Button favouritebtn,Button starbtn)
    {
        favbtn=favouritebtn;
        savebtn=starbtn;
        HBox btncountainer=new HBox();
        btncountainer.setAlignment(Pos.CENTER);
        btncountainer.getChildren().addAll(favouritebtn);
        btncountainer.setSpacing(10);
        VBox result=new VBox();
        result.setPrefWidth(130);
        result.setPrefHeight(190);
        result.setMaxSize(130,190);
        ImageView itemimage=new ImageView(new Image(image));
        itemimage.setFitHeight(90);
        itemimage.setFitWidth(110);
        Label title=new Label(this.name);
        title.setFont(Font.font("Georgia",12));
        title.setAlignment(Pos.CENTER);
        title.setPrefSize(130,60);
        title.setWrapText(true);
        Label price=new Label("price: "+((float) this.price/100)+" $");
        price.setPrefSize(130,20);
        Label time=new Label("time: "+this.time+" min");
        Label healthy=new Label();
        Label popular=new Label();
        if (veryHealthy)
        {
            healthy.setText("Healthy : Very Healthy");
        }
        else
        {
            healthy.setText("Healthy : Not Healthy");
        }
        if (veryPopular)
            popular.setText("Popular : Very Popular");
        else
            popular.setText("Popular : Not Popular");
        popular.setAlignment(Pos.CENTER);
        healthy.setAlignment(Pos.CENTER);
        time.setAlignment(Pos.CENTER);
        price.setAlignment(Pos.CENTER);
        time.setPrefSize(130,20);
        result.setAlignment(Pos.CENTER);
        result.getChildren().add(itemimage);
        result.getChildren().add(title);
        result.getChildren().add(time);
        result.getChildren().add(price);
        result.getChildren().add(healthy);
        result.getChildren().add(popular);
        result.getChildren().add(btncountainer);
        result.getStyleClass().add("item");
        return result;
    }

    public void String()
    {
        System.out.println(id);
        System.out.println(name);
        System.out.println(image);
        System.out.println(cheap);
        System.out.println(veryHealthy);
        System.out.println(veryPopular);
        System.out.println(vegan);
        System.out.println("----------------------");
    }

}
