package coffee;
import coffee.*;
import coffee.exceptions.InventoryException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Inventory class.
 */
public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void testInitialInventory() {
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getChocolate());
    }

    @Test
    public void testAddCoffee() {
        try {
            inventory.addCoffee("5");
            assertEquals(20, inventory.getCoffee());
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
    }

    @Test
    public void testAddInvalidCoffee() {
        assertThrows(InventoryException.class, () -> inventory.addCoffee("-5"));
        assertThrows(InventoryException.class, () -> inventory.addCoffee("abc"));
    }

    @Test
    public void testAddMilk() {
        try {
            inventory.addMilk("10");
            assertEquals(25, inventory.getMilk());
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
    }

    @Test
    public void testAddInvalidMilk() {
        assertThrows(InventoryException.class, () -> inventory.addMilk("-10"));
        assertThrows(InventoryException.class, () -> inventory.addMilk("xyz"));
    }

    @Test
    public void testAddSugar() {
        try {
            inventory.addSugar("7");
            assertEquals(22, inventory.getSugar());
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
    }

    @Test
    public void testAddInvalidSugar() {
        assertThrows(InventoryException.class, () -> inventory.addSugar("-7"));
        assertThrows(InventoryException.class, () -> inventory.addSugar("123abc"));
    }

    @Test
    public void testAddChocolate() {
        try {
            inventory.addChocolate("3");
            assertEquals(18, inventory.getChocolate());
        } catch (InventoryException e) {
            fail("InventoryException should not be thrown");
        }
    }

    @Test
    public void testAddInvalidChocolate() {
        assertThrows(InventoryException.class, () -> inventory.addChocolate("-3"));
        assertThrows(InventoryException.class, () -> inventory.addChocolate("$5"));
    }

    @Test
    public void testUseIngredientsSuccess() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtCoffee("5");
            recipe.setAmtMilk("5");
            recipe.setAmtSugar("5");
            recipe.setAmtChocolate("5");
            assertTrue(inventory.useIngredients(recipe));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testUseIngredientsFailure() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtCoffee("20");
            recipe.setAmtMilk("5");
            recipe.setAmtSugar("5");
            recipe.setAmtChocolate("5");
            assertFalse(inventory.useIngredients(recipe));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testToString() {
        String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
        assertEquals(expected, inventory.toString());
    }
    @Test
    public void testAddSugar_InvalidValue() {
        assertThrows(InventoryException.class, () -> inventory.addSugar("-5"),
                "Should throw exception when adding negative sugar.");
    }

    @Test
    public void testSetSugar_NegativeValue() {
        inventory.setSugar(-10);
        assertEquals(0, inventory.getSugar(), "Sugar should not be set to a negative value.");
    }

    @Test
    public void testEnoughIngredients_NotEnoughCoffee() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtCoffee("100");  // Requires more coffee than available
        } catch (coffee.exceptions.RecipeException e)
        {
            fail("Unexpected exception: " + e.getMessage());  // Fail test if exception is thrown
        }
        assertFalse(inventory.enoughIngredients(recipe), "Should return false if not enough coffee.");
    }
    @Test
    public void testEnoughIngredients_NotEnoughMilk() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtMilk("20");  // Requires more milk than available
        } catch (coffee.exceptions.RecipeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(inventory.enoughIngredients(recipe), "Should return false if not enough milk.");
    }

    @Test
    public void testEnoughIngredients_NotEnoughSugar() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtSugar("20");  // Requires more sugar than available
        } catch (coffee.exceptions.RecipeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(inventory.enoughIngredients(recipe), "Should return false if not enough sugar.");
    }

    @Test
    public void testEnoughIngredients_NotEnoughChocolate() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtChocolate("20");  // Requires more chocolate than available
        } catch (coffee.exceptions.RecipeException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(inventory.enoughIngredients(recipe), "Should return false if not enough chocolate.");
    }
    @Test
    public void testSetCoffee_NegativeValue() {
        inventory.setCoffee(-5);
        assertEquals(0, inventory.getCoffee(), "Coffee should not be set to a negative value.");
    }

    @Test
    public void testSetMilk_NegativeValue() {
        inventory.setMilk(-5);
        assertEquals(0, inventory.getMilk(), "Milk should not be set to a negative value.");
    }

    @Test
    public void testSetChocolate_NegativeValue() {
        inventory.setChocolate(-5);
        assertEquals(0, inventory.getChocolate(), "Chocolate should not be set to a negative value.");
    }

    @Test
    public void testAddCoffee_InvalidStringInput() {
        assertThrows(InventoryException.class, () -> inventory.addCoffee("NaN"),
                "Should throw exception when adding non-integer coffee.");
    }

    @Test
    public void testAddMilk_InvalidStringInput() {
        assertThrows(InventoryException.class, () -> inventory.addMilk("NaN"),
                "Should throw exception when adding non-integer milk.");
    }

    @Test
    public void testAddSugar_InvalidStringInput() {
        assertThrows(InventoryException.class, () -> inventory.addSugar("NaN"),
                "Should throw exception when adding non-integer sugar.");
    }

    @Test
    public void testAddChocolate_InvalidStringInput() {
        assertThrows(InventoryException.class, () -> inventory.addChocolate("NaN"),
                "Should throw exception when adding non-integer chocolate.");
    }
    @Test
    public void testInventoryToString_Execution() {
        assertNotNull(inventory.toString(), "toString() method should return a non-null string.");
    }
    @Test
    public void testUseIngredients_NotEnoughMilk() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtMilk("20");
        } catch (coffee.exceptions.RecipeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(inventory.useIngredients(recipe), "Should return false if not enough milk.");
    }

    @Test
    public void testUseIngredients_NotEnoughSugar() {
        Recipe recipe = new Recipe();
        try {
            recipe.setAmtSugar("20");
        } catch (coffee.exceptions.RecipeException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        assertFalse(inventory.useIngredients(recipe), "Should return false if not enough sugar.");
    }
}
