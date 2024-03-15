import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private int time;
    private List<String> ingredients;

    public Recipe(String name, int time, List<String> ingredients) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return time;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return name + ", Cooking time: " + time;
    }
}
