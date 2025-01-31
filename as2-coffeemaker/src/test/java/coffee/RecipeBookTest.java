package coffee;
import coffee.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for RecipeBook class.
 */
public class RecipeBookTest {

    private RecipeBook recipeBook;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;

    @BeforeEach
    public void setUp() {
        recipeBook = new RecipeBook();

        recipe1 = new Recipe();
        recipe2 = new Recipe();
        recipe3 = new Recipe();
        recipe4 = new Recipe();
        recipe5 = new Recipe();

        try {
            recipe1.setName("Espresso");
            recipe1.setPrice("50");
            recipe1.setAmtCoffee("3");
            recipe1.setAmtMilk("0");
            recipe1.setAmtSugar("1");
            recipe1.setAmtChocolate("0");

            recipe2.setName("Latte");
            recipe2.setPrice("60");
            recipe2.setAmtCoffee("2");
            recipe2.setAmtMilk("2");
            recipe2.setAmtSugar("1");
            recipe2.setAmtChocolate("0");

            recipe3.setName("Mocha");
            recipe3.setPrice("75");
            recipe3.setAmtCoffee("3");
            recipe3.setAmtMilk("1");
            recipe3.setAmtSugar("2");
            recipe3.setAmtChocolate("2");

            recipe4.setName("Hot Chocolate");
            recipe4.setPrice("65");
            recipe4.setAmtCoffee("0");
            recipe4.setAmtMilk("1");
            recipe4.setAmtSugar("2");
            recipe4.setAmtChocolate("3");

            recipe5.setName("Americano");
            recipe5.setPrice("40");
            recipe5.setAmtCoffee("2");
            recipe5.setAmtMilk("0");
            recipe5.setAmtSugar("1");
            recipe5.setAmtChocolate("0");

        } catch (Exception e) {
            fail("Recipe setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testAddRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertTrue(recipeBook.addRecipe(recipe2));
        assertTrue(recipeBook.addRecipe(recipe3));
        assertTrue(recipeBook.addRecipe(recipe4));
        assertFalse(recipeBook.addRecipe(recipe5)); // Exceeds the limit
    }

    @Test
    public void testAddDuplicateRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertFalse(recipeBook.addRecipe(recipe1));
    }

    @Test
    public void testDeleteRecipe() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.deleteRecipe(0));
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    public void testDeleteInvalidRecipe() {
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    public void testEditRecipe() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.editRecipe(0, recipe2));
        assertNull(recipeBook.editRecipe(1, recipe3));
    }

    @Test
    public void testEditToEmptyRecipe() {
        recipeBook.addRecipe(recipe1);
        Recipe emptyRecipe = new Recipe();
        assertEquals("Espresso", recipeBook.editRecipe(0, emptyRecipe));
    }

    @Test
    public void testGetRecipes() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        assertEquals("Espresso", recipeBook.getRecipes()[0].getName());
        assertEquals("Latte", recipeBook.getRecipes()[1].getName());
    }

    @Test
    public void testGetEmptyRecipes() {
        Recipe[] recipes = recipeBook.getRecipes();
        for (Recipe recipe : recipes) {
            assertNull(recipe);
        }
    }

    @Test
    public void testRecipeLimit() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(recipe3);
        recipeBook.addRecipe(recipe4);
        assertFalse(recipeBook.addRecipe(recipe5)); // Exceeds the recipe limit
    }

    @Test
    public void testEditNonExistentRecipe() {
        assertNull(recipeBook.editRecipe(0, recipe1));
    }

    @Test
    public void testDeleteNonExistentRecipe() {
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    public void testAddNullRecipe() {
        assertFalse(recipeBook.addRecipe(null));
    }

    @Test
    public void testEditRecipeWithNull() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.editRecipe(0, null));
    }

}
