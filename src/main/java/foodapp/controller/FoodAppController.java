package foodapp.controller;
import foodapp.Favorite;
import foodapp.Ingredient;
import foodapp.Recipe;
import foodapp.utils.utilsme;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.BuilderFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;


public class FoodAppController implements Initializable {
    boolean favopen=false;
    int index=0;
    Button show=new Button("");
    List<Recipe> Todisplaysort=new ArrayList<>();
    List<Recipe> Todisplay=new ArrayList<>();
    boolean searching=false;
    boolean textfieldshown=false;
    List<Recipe> saved=new ArrayList<>();
    Favorite favorite=new Favorite();
    List<Recipe> recipes;
    Label requirementslabl=new Label();
    Label instructionslabl=new Label();
    @FXML
    Pane Sortpane;
    @FXML
    ImageView saveimg;
    @FXML
    ImageView favimg;
    @FXML
    Button favescroll2;
    @FXML
    CheckBox checkBoxTime;
    @FXML
    CheckBox checkBoxpop;
    @FXML
    VBox ingredientsview;
    @FXML
    VBox requirementsview;
    @FXML
    ScrollPane scroll2;
    @FXML
    ImageView recipeimg;
    @FXML
    Label recipeviewname;
    @FXML
    Pane app;
    @FXML
    ScrollPane scroll1;
    @FXML
    VBox mainmenu;
    @FXML
    TextField textField;
    @FXML
    AnchorPane searchAnchor;
    @FXML
    Button closesearchbtn;
    @FXML
    Button upbtn;
    @FXML
    CheckBox checkBoxhealthy;
    @FXML
    CheckBox checkBoxprice;
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        textField.setOnKeyPressed(event ->
        {
            if (event.getCode()== KeyCode.ENTER)
            {
                globalsearch();
            }
        });
        show.setOnAction(event ->
        {
            mainmenu.getChildren().remove(show);
            if (searching)
            {
                if (checkBoxprice.isSelected()||checkBoxTime.isSelected())
                {
                    displayRecipes(Todisplaysort);
                }
                else
                    displayRecipes(Todisplay);
            }
            else
            {
                if (checkBoxprice.isSelected()||checkBoxTime.isSelected())
                {
                    displayRecipes(Todisplay);
                }
                else
                    displayRecipes(recipes);
            }
        });
        ImageView showmoreimg=new ImageView(new Image("file:src/main/resources/app/foodapp/view/img/expand-button.png"));
        showmoreimg.setFitWidth(20);
        showmoreimg.setFitHeight(20);
        show.setGraphic(showmoreimg);
        show.getStyleClass().add("leaveminimizebtn");
        requirementslabl.setText("requirements");
        ImageView imageView=new ImageView(new Image("file:src/main/resources/app/foodapp/view/img/ingredients.png"));
        imageView.setFitWidth(50);
        imageView.setFitHeight(40);
        requirementslabl.setGraphic(imageView);
        requirementslabl.setPadding(new Insets(0,0,30,0));
        instructionslabl.setText("instructions");
        ImageView imageView1=new ImageView(new Image("file:src/main/resources/app/foodapp/view/img/requirement.png"));
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(40);
        instructionslabl.setGraphic(imageView1);
        instructionslabl.setPadding(new Insets(0,0,30,0));
        requirementsview.setPadding(new Insets(0,0,30,0));
        ingredientsview.setPadding(new Insets(0,0,30,0));
        try {
            recipes=utilsme.getRecipes(100);
            displayRecipes(recipes);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    void stopsearching()
    {
        if (textField.getText()=="")
        {
            searchAnchor.setScaleX(0);
            searchAnchor.setScaleY(0);
            textfieldshown=false;
        }
        else
            textField.setText("");
    }
    @FXML
    void insideButton(MouseEvent e)
    {
        Button btn =(Button) e.getSource();
        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(0.1),btn);
        scaleTransition.setToY(1.1);
        scaleTransition.setToX(1.1);
        scaleTransition.play();
    }
    @FXML
    void LeaveButton(MouseEvent e)
    {
        Button btn=(Button) e.getSource();
        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(0.1),btn);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);
        scaleTransition.play();
    }
    void startSearch()
    {
        textfieldshown=true;
        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(0.2),searchAnchor);
        scaleTransition.setToY(1);
        scaleTransition.setToX(1);
        scaleTransition.play();
    }
    @FXML
    void checkv()
    {
        if (scroll1.getVvalue()==scroll1.getVmin())
        {
            upbtn.setVisible(false);
        }
        else
            upbtn.setVisible(true);
    }
    @FXML
    void up()
    {
        scroll1.setVvalue(0);
        upbtn.setVisible(false);
    }
    @FXML
    void goback()
    {
        if (favopen)
        {
            favpane.setVisible(true);
            favview.getChildren().clear();
            showFavorite();
        }
        else
        {
            Sortpane.setVisible(true);
            checkv();
            scroll1.setVisible(true);
        }
        scroll2.setVisible(false);
    }
    void displayRecipes(List<Recipe> recipes)
    {
        for (int j=index;j<index+12&&j<recipes.size();j+=4)
        {
            HBox hBox =new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(205);
            hBox.setMinHeight(205);
            hBox.setSpacing(50);
            for (int i=j;i<j+4 && i<recipes.size();i++)
            {
                int k=i;
                Image save=new Image("file:src/main/resources/app/foodapp/view/img/star.png");
                Image favourite=new Image("file:src/main/resources/app/foodapp/view/img/save-instagram.png");
                Image unsave=new Image("file:src/main/resources/app/foodapp/view/img/minus.png");
                Image unfavourite=new Image("file:src/main/resources/app/foodapp/view/img/bookmark.png");
                ImageView saveimg=new ImageView(save);
                ImageView favouriteimg=new ImageView(favourite);
                favouriteimg.setFitHeight(15);
                favouriteimg.setFitWidth(15);
                saveimg.setFitHeight(15);
                saveimg.setFitWidth(15);
                Button btnstart=new Button();
                Button btn=new Button();
                btnstart.getStyleClass().add("searchbtn");
                btn.getStyleClass().add("searchbtn");
                btn.setGraphic(favouriteimg);
                btnstart.setGraphic(saveimg);
                if (favorite.contains(recipes.get(k)))
                {
                    favouriteimg.setImage(unfavourite);
                }
                else
                {
                    favouriteimg.setImage(favourite);
                }
                if (saved.contains(recipes.get(k)))
                {
                    saveimg.setImage(unsave);
                }
                else
                {
                    saveimg.setImage(save);
                }
                btn.setOnAction(event ->
                {
                    if (!favorite.contains(recipes.get(k)))
                    {
                        favorite.add(recipes.get(k));
                        favouriteimg.setImage(unfavourite);
                    }
                    else
                    {
                        favorite.remove(recipes.get(k).getId());
                        favouriteimg.setImage(favourite);
                    }
                    favorite.string();
                });
                btnstart.setOnAction(event ->
                {
                    if (!saved.contains(recipes.get(k)))
                    {
                        saved.add(recipes.get(k));
                        saveimg.setImage(unsave);
                    }
                    else
                    {
                        saveimg.setImage(save);
                        for (int l=0;l<saved.size();l++)
                        {
                            if (saved.get(l).getId()==recipes.get(k).getId())
                            {
                                saved.remove(l);
                            }
                        }
                    }
                });
                VBox item=recipes.get(i).Item(btn,btnstart);
                item.setOnMouseEntered(event ->
                {
                    VBox vBox=(VBox) event.getSource();
                    ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(0.1),vBox);
                    scaleTransition.setToY(1.1);
                    scaleTransition.setToX(1.1);
                    scaleTransition.play();
                });
                item.setOnMouseExited(event ->
                {
                    VBox vBox=(VBox) event.getSource();
                    ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(0.2),vBox);
                    scaleTransition.setToY(1);
                    scaleTransition.setToX(1);
                    scaleTransition.play();
                });
                item.setOnMouseClicked(event ->
                {
                    Sortpane.setVisible(false);
                    viewitem(recipes.get(k));
                });
                hBox.getChildren().add(item);
            }
            mainmenu.getChildren().add(hBox);
        }
        if (!((index+12)>=recipes.size()))
        {
            mainmenu.getChildren().add(show);
            index+=12;
        }

    }
    void viewitem(Recipe recipe)
    {
        if (favorite.contains(recipe))
        {
            favimg.setImage(new Image("file:src/main/resources/app/foodapp/view/img/bookmark.png"));
            favescroll2.setText("remove");
        }
        else
        {
            favimg.setImage(new Image("file:src/main/resources/app/foodapp/view/img/save-instagram.png"));
            favescroll2.setText("add");
        }

        favescroll2.setOnAction(event ->
        {
            Image image=new Image("file:src/main/resources/app/foodapp/view/img/bookmark.png");
            Image image1=new Image("file:src/main/resources/app/foodapp/view/img/save-instagram.png");
            ImageView imageView=new ImageView(image);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            if (removeaddfav(recipe))
            {
                recipe.favbtn.setGraphic(imageView);
                favimg.setImage(image);
                favescroll2.setText("remove");
            }
            else
            {
                imageView.setImage(image1);
                recipe.favbtn.setGraphic(imageView);
                favimg.setImage(new Image("file:src/main/resources/app/foodapp/view/img/save-instagram.png"));
                favescroll2.setText("add");
            }

        });
        startSearch();
        ingredientsview.getChildren().clear();
        requirementsview.getChildren().clear();
        ingredientsview.getChildren().add(requirementslabl);
        requirementsview.getChildren().add(instructionslabl);
        recipeimg.setImage(new Image(recipe.getImage()));
        recipeviewname.setText(recipe.getName());
        List<Ingredient> ingredients=recipe.ingredients();
        List<String> instructions=recipe.getInstructions();
        for (int i=0;i<ingredients.size();i++)
        {
            Label label=new Label(i+"-  "+ingredients.get(i).getIngredient());
            label.setWrapText(true);
            label.setFont(Font.font("Arial",12));
            ingredientsview.getChildren().add(label);
        }
        for (int i=0;i<instructions.size();i++)
        {
            Label label=new Label(i+"-  "+instructions.get(i));
            label.setWrapText(true);
            label.setFont(Font.font("Arial",12));
            requirementsview.getChildren().add(label);
        }
        scroll2.setVisible(true);
        scroll1.setVisible(false);
        upbtn.setVisible(false);
    }
    @FXML
    void sortbypopoularity()
    {
        index=0;
        checkBoxhealthy.setSelected(false);
        checkBoxTime.setSelected(false);
        checkBoxprice.setSelected(false);
        mainmenu.getChildren().clear();
        up();
        if (!searching)
        {
            if (checkBoxpop.isSelected())
            {
                for (int i=0;i<recipes.size();i++)
                {
                    if (recipes.get(i).isVeryPopular())
                    {
                        Todisplay.add(recipes.get(i));
                    }
                }
                for (int i=0;i<recipes.size();i++)
                {
                    if (!Todisplay.contains(recipes.get(i)))
                    {
                        Todisplay.add(recipes.get(i));
                    }
                }

                displayRecipes(Todisplay);
            }
            else
                displayRecipes(recipes);
        }
        else
        {
            if (checkBoxpop.isSelected())
            {
                for (int i=0;i<Todisplay.size();i++)
                {
                    if (Todisplay.get(i).isVeryPopular())
                    {
                        Todisplaysort.add(Todisplay.get(i));
                    }
                }
                for (int i=0;i<Todisplay.size();i++)
                {
                    if (!Todisplaysort.contains(Todisplay.get(i)))
                    {
                        Todisplaysort.add(Todisplay.get(i));
                    }
                }

                displayRecipes(Todisplaysort);
            }
            else
                displayRecipes(Todisplay);
        }
    }
    @FXML
    void sortByHealth()
    {
        index=0;
        up();
        checkBoxTime.setSelected(false);
        checkBoxpop.setSelected(false);
        checkBoxprice.setSelected(false);
        mainmenu.getChildren().clear();
        if (!searching)
        {
            if (checkBoxhealthy.isSelected())
            {
                Todisplay.clear();
                for (int i=0;i<recipes.size();i++)
                {
                    if (recipes.get(i).isVeryHealthy())
                    {
                        Todisplay.add(recipes.get(i));
                    }
                }
                for (int i=0;i<recipes.size();i++)
                {
                    if (!Todisplay.contains(recipes.get(i)))
                    {
                        Todisplay.add(recipes.get(i));
                    }
                }

                displayRecipes(Todisplay);
            }
            else
                displayRecipes(recipes);
        }
        else
        {
            if (checkBoxhealthy.isSelected())
            {
                Todisplaysort.clear();
                for (int i=0;i<Todisplay.size();i++)
                {
                    if (Todisplay.get(i).isVeryHealthy())
                    {
                        Todisplaysort.add(Todisplay.get(i));
                    }
                }
                for (int i=0;i<Todisplay.size();i++)
                {
                    if (!Todisplaysort.contains(Todisplay.get(i)))
                    {
                        Todisplaysort.add(Todisplay.get(i));
                    }
                }

                displayRecipes(Todisplaysort);
            }
            else
                displayRecipes(Todisplay);
        }

    }
    @FXML
    void sortByPrice()
    {
        index=0;
        up();
        checkBoxTime.setSelected(false);
        checkBoxpop.setSelected(false);
        checkBoxhealthy.setSelected(false);
        mainmenu.getChildren().clear();
        if (!searching)
        {
            if (checkBoxprice.isSelected())
            {
                Todisplay.clear();
                List<Float> prices=new ArrayList<>();
                List<Integer> ids=new ArrayList<>();
                for (int i=0;i<recipes.size();i++)
                {
                    prices.add(recipes.get(i).getPrice()/(float)100);
                }
                Collections.sort(prices);
                for (int i=0;i<prices.size();i++)
                {
                    for (int j=0;j<recipes.size();j++)
                    {
                        if (prices.get(i)==(recipes.get(j).getPrice()/(float)100) && !ids.contains(recipes.get(j).getId()))
                        {
                            Todisplay.add(recipes.get(j));
                            ids.add(recipes.get(j).getId());
                            break;
                        }
                    }
                }
                displayRecipes(Todisplay);
            }
            else
                displayRecipes(recipes);
        }
        else
        {
            if (checkBoxprice.isSelected())
            {
                Todisplaysort.clear();
                List<Float> prices=new ArrayList<>();
                List<Integer> ids=new ArrayList<>();
                for (int i=0;i<Todisplay.size();i++)
                {
                    prices.add(Todisplay.get(i).getPrice()/(float)100);
                }
                Collections.sort(prices);
                for (int i=0;i<prices.size();i++)
                {
                    for (int j=0;j<Todisplay.size();j++)
                    {
                        if (prices.get(i)==(Todisplay.get(j).getPrice()/(float)100) && !ids.contains(Todisplay.get(j).getId()))
                        {
                            Todisplaysort.add(Todisplay.get(j));
                            ids.add(Todisplay.get(j).getId());
                            break;
                        }
                    }
                }
                displayRecipes(Todisplaysort);
            }
            else
                displayRecipes(Todisplay);
        }
    }
    @FXML
    void sortByTime()
    {
        index=0;
        checkBoxhealthy.setSelected(false);
        checkBoxpop.setSelected(false);
        checkBoxprice.setSelected(false);
        mainmenu.getChildren().clear();
        up();
        if (!searching)
        {
            if (checkBoxTime.isSelected())
            {
                Todisplay.clear();
                List<Integer> ids=new ArrayList<>();
                List<Integer> times=new ArrayList<>();
                for (int i=0;i<recipes.size();i++)
                {
                    times.add(recipes.get(i).getTime());
                }
                Collections.sort(times);
                for (int i=0;i<times.size();i++)
                {
                    for (int j=0;j<recipes.size();j++)
                    {
                        if (times.get(i)==recipes.get(j).getTime() && !ids.contains(recipes.get(j).getId()))
                        {
                            Todisplay.add(recipes.get(j));
                            ids.add(recipes.get(j).getId());
                            break;
                        }
                    }
                }
                displayRecipes(Todisplay);
            }
            else
                displayRecipes(recipes);
        }
        else
        {
            if (checkBoxTime.isSelected())
            {
                Todisplaysort.clear();
                List<Integer> ids=new ArrayList<>();
                List<Integer> times=new ArrayList<>();
                for (int i=0;i<Todisplay.size();i++)
                {
                    times.add(Todisplay.get(i).getTime());
                }
                Collections.sort(times);
                for (int i=0;i<times.size();i++)
                {
                    for (int j=0;j<Todisplay.size();j++)
                    {
                        if (times.get(i)==Todisplay.get(j).getTime() && !ids.contains(Todisplay.get(j).getId()))
                        {
                            Todisplaysort.add(Todisplay.get(j));
                            ids.add(Todisplay.get(j).getId());
                            break;
                        }
                    }
                }
                displayRecipes(Todisplaysort);
            }
            else
                displayRecipes(Todisplay);
        }

    }
    @FXML
    void globalsearch()
    {
        index=0;
        untag();
        Todisplay.clear();
        if (textfieldshown && textField.getText()!="")
        {
            mainmenu.getChildren().clear();
            String search=textField.getText();
            for (int i=0;i<recipes.size();i++)
            {
                boolean found=false;
                if (recipes.get(i).name.toLowerCase().contains(search.toLowerCase()))
                {
                    Todisplay.add(recipes.get(i));
                    found=true;

                }
                List<Ingredient> ingredients=recipes.get(i).ingredients();
                for (int j=0;j<ingredients.size();j++)
                {
                    if (found)
                        break;
                    if (ingredients.get(j).getIngredient().toLowerCase().contains(search.toLowerCase()))
                    {
                        Todisplay.add(recipes.get(i));
                        break;
                    }
                }
            }
            searching=true;
            displayRecipes(Todisplay);
        }
        else
        {
            startSearch();
        }
    }
    @FXML
    void Homebtn()
    {
        favopen=false;
        favpane.setVisible(false);
        Sortpane.setVisible(true);
        textField.setText("");
        index=0;
        Todisplay.clear();
        searching=false;
        mainmenu.getChildren().clear();
        scroll1.setVisible(true);
        scroll2.setVisible(false);
        untag();
        up();
        displayRecipes(recipes);
    }
    void untag()
    {
        checkBoxhealthy.setSelected(false);
        checkBoxprice.setSelected(false);
        checkBoxpop.setSelected(false);
        checkBoxTime.setSelected(false);
    }
    @FXML
    void leavebtn()
    {
        Platform.exit();
    }
    @FXML
    void minimize(MouseEvent e)
    {
        ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
    }
    boolean removeaddfav(Recipe recipe)
    {
        if (!favorite.contains(recipe))
        {
            favorite.add(recipe);
            favorite.string();
            return true;
        }
        else
        {
            favorite.remove(recipe.getId());
            favorite.string();
            return false;
        }
    }
    boolean removeaddsave(Recipe recipe)
    {
        if (!saved.contains(recipe))
        {
            saved.add(recipe);
            return false;
        }
        else
        {
            saved.remove(recipe);
            return true;
        }
    }
    @FXML
    ScrollPane favpane;
    @FXML
    VBox favview;
    @FXML
    void showFavorite()
    {
        favview.getChildren().clear();
        favopen=true;
        Sortpane.setVisible(false);
        upbtn.setVisible(false);
        favpane.setVisible(true);
        if (favorite.size()==0)
        {
            Label label=new Label("There is no favorite Recipe please add some");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(18));
            favview.getChildren().add(label);
        }
        for (int i=0;i<favorite.size();i++)
        {
            int k=i;
            System.out.println(i);
            Recipe recipe =favorite.get(i);
            HBox hBox=new HBox();
            hBox.getStyleClass().add("favhbox");
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(20);
            hBox.setPadding(new Insets(0,10,0,10));
            ImageView imageView=new ImageView(new Image(recipe.getImage()));
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            Label name=new Label(recipe.name);
            name.setPrefWidth(200);
            name.setFont(Font.font(10));
            name.setWrapText(true);
            Button btn =new Button("remove");
            btn.setOnAction(event ->
            {
                Image image1=new Image("file:src/main/resources/app/foodapp/view/img/save-instagram.png");
                ImageView imageView1=new ImageView(image1);
                imageView1.setFitWidth(15);
                imageView1.setFitHeight(15);
                recipe.favbtn.setGraphic(imageView1);
                favorite.remove(recipe.getId());
                favview.getChildren().clear();
                showFavorite();
            });
            hBox.getChildren().addAll(imageView,name,btn);
            hBox.setOnMouseClicked(event ->
            {
                favpane.setVisible(false);
                viewitem(favorite.get(k));
            });
            favview.getChildren().add(hBox);
        }
    }
    @FXML
    void backfav()
    {
        favopen=false;
        favpane.setVisible(false);
        scroll2.setVisible(false);
        scroll1.setVisible(true);
        Sortpane.setVisible(true);
    }
    @FXML
    void saladbtn()
    {
        startSearch();
        textField.setText("salad");
        globalsearch();
        textField.setText("");
        stopsearching();
    }
    @FXML
    void desertbtn()
    {
        startSearch();
        textField.setText("chocolate");
        globalsearch();
        textField.setText("");
        stopsearching();
    }
    @FXML
    void drinkbtn()
    {
        startSearch();
        textField.setText("cocktail");
        globalsearch();
        textField.setText("");
        stopsearching();
    }
    @FXML
    void pizzabtn()
    {
        startSearch();
        textField.setText("pizza");
        globalsearch();
        textField.setText("");
        stopsearching();

    }
    @FXML
    void burgerbtn()
    {
        startSearch();
        textField.setText("burger");
        globalsearch();
        textField.setText("");
        stopsearching();

    }
    @FXML
    void allbtn()
    {
        Homebtn();
    }
}


