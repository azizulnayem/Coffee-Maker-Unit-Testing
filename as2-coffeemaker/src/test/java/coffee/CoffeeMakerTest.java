package coffee;
import coffee.*;
import coffee.exceptions.InventoryException;
import coffee.exceptions.RecipeException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive Unit tests for CoffeeMaker class.
 */
public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @BeforeEach
    public void setUp() throws Exception {
        coffeeMaker = new CoffeeMaker();

        // Set up recipe1
        recipe1 = new Recipe();
        recipe1.setName("Espresso");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("0");
        recipe1.setAmtSugar("1");
        recipe1.setAmtChocolate("0");
        recipe1.setPrice("50");

        // Set up recipe2
        recipe2 = new Recipe();
        recipe2.setName("Latte");
        recipe2.setAmtCoffee("2");
        recipe2.setAmtMilk("2");
        recipe2.setAmtSugar("1");
        recipe2.setAmtChocolate("0");
        recipe2.setPrice("60");

        // Set up recipe3
        recipe3 = new Recipe();
        recipe3.setName("Mocha");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("1");
        recipe3.setAmtSugar("2");
        recipe3.setAmtChocolate("2");
        recipe3.setPrice("75");

        // Set up recipe4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("2");
        recipe4.setAmtChocolate("3");
        recipe4.setPrice("65");
    }

    @Test
    public void testAddRecipe() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertFalse(coffeeMaker.addRecipe(recipe1)); // Adding duplicate
    }

    @Test
    public void testDeleteRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals("Espresso", coffeeMaker.deleteRecipe(0));
        assertNull(coffeeMaker.deleteRecipe(0));
    }

    @Test
    public void testEditRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals("Espresso", coffeeMaker.editRecipe(0, recipe2));
        assertNull(coffeeMaker.editRecipe(1, recipe3));
    }

    @Test
    public void testAddInventory_Normal() {
        try {
            coffeeMaker.addInventory("5", "5", "5", "5");
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
        assertEquals("Coffee: 20\nMilk: 20\nSugar: 20\nChocolate: 20\n", coffeeMaker.checkInventory());
    }

    @Test
    public void testAddInventoryException() {
        assertThrows(InventoryException.class, () -> coffeeMaker.addInventory("-1", "5", "5", "5"));
    }

    @Test
    public void testCheckInventory() {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", coffeeMaker.checkInventory());
    }

    @Test
    public void testMakeCoffee_Normal() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    @Test
    public void testMakeCoffee_InsufficientFunds() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(50, coffeeMaker.makeCoffee(0, 50));
    }

    @Test
    public void testMakeCoffee_InvalidRecipe() {
        assertEquals(100, coffeeMaker.makeCoffee(1, 100));
    }

    @Test
    public void testMultipleRecipes() {
        assertTrue(coffeeMaker.addRecipe(recipe1));
        assertTrue(coffeeMaker.addRecipe(recipe2));
        assertEquals("Latte", coffeeMaker.getRecipes()[1].getName());
    }

    @Test
    public void testAddDuplicateRecipe() {
        coffeeMaker.addRecipe(recipe1);
        assertFalse(coffeeMaker.addRecipe(recipe1));
    }

    @Test
    public void testEditRecipeToNull() {
        coffeeMaker.addRecipe(recipe1);
        Recipe emptyRecipe = new Recipe();
        assertEquals("Espresso", coffeeMaker.editRecipe(0, emptyRecipe));
    }

    @Test
    public void testDeleteNonExistentRecipe() {
        assertNull(coffeeMaker.deleteRecipe(1));
    }

    @Test
    public void testAddAndCheckInventory() {
        try {
            coffeeMaker.addInventory("10", "10", "10", "10");
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
        assertEquals("Coffee: 25\nMilk: 25\nSugar: 25\nChocolate: 25\n", coffeeMaker.checkInventory());
    }
    @Test
    public void testMakeCoffee_RecipeDoesNotExist() {
        int change = coffeeMaker.makeCoffee(10, 100); // Assuming there are only 4 recipes
        assertEquals(100, change, "Should return full amount if recipe does not exist.");
    }
    @Test
    public void testMakeCoffee_NotEnoughMoney_CatchException() {
        try {
            recipe1.setPrice("100");  // Keep it as an int
            coffeeMaker.addRecipe(recipe1);

            int change = coffeeMaker.makeCoffee(0, 50);
            assertEquals(50, change, "Should return full amount paid since not enough money.");
        } catch (RecipeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void testMakeCoffee_InventoryNotEnough() throws RecipeException {
        recipe1.setPrice("50");
        recipe1.setAmtCoffee("100");  // Requires more coffee than available
        coffeeMaker.addRecipe(recipe1);

        int change = coffeeMaker.makeCoffee(0, 50);
        assertEquals(50, change, "Should return full amount paid since inventory is insufficient.");
    }
}