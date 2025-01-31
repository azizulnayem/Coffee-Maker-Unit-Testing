package coffee;
import coffee.*;
import coffee.exceptions.RecipeException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Recipe class.
 */
public class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
    }

    @Test
    public void testSetAndGetName() {
        recipe.setName("Espresso");
        assertEquals("Espresso", recipe.getName());
    }

    @Test
    public void testSetAndGetPrice() {
        try {
            recipe.setPrice("50");
            assertEquals(50, recipe.getPrice());
        } catch (RecipeException e) {
            fail("RecipeException should not be thrown");
        }
    }

    @Test
    public void testSetInvalidPrice() {
        assertThrows(RecipeException.class, () -> recipe.setPrice("-10"));
        assertThrows(RecipeException.class, () -> recipe.setPrice("abc"));
    }

    @Test
    public void testSetAndGetAmtCoffee() {
        try {
            recipe.setAmtCoffee("3");
            assertEquals(3, recipe.getAmtCoffee());
        } catch (RecipeException e) {
            fail("RecipeException should not be thrown");
        }
    }

    @Test
    public void testSetInvalidAmtCoffee() {
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("-3"));
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("xyz"));
    }

    @Test
    public void testSetAndGetAmtMilk() {
        try {
            recipe.setAmtMilk("2");
            assertEquals(2, recipe.getAmtMilk());
        } catch (RecipeException e) {
            fail("RecipeException should not be thrown");
        }
    }

    @Test
    public void testSetInvalidAmtMilk() {
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("-2"));
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("abc123"));
    }

    @Test
    public void testSetAndGetAmtSugar() {
        try {
            recipe.setAmtSugar("4");
            assertEquals(4, recipe.getAmtSugar());
        } catch (RecipeException e) {
            fail("RecipeException should not be thrown");
        }
    }

    @Test
    public void testSetInvalidAmtSugar() {
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("-4"));
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("4abc"));
    }

    @Test
    public void testSetAndGetAmtChocolate() {
        try {
            recipe.setAmtChocolate("5");
            assertEquals(5, recipe.getAmtChocolate());
        } catch (RecipeException e) {
            fail("RecipeException should not be thrown");
        }
    }

    @Test
    public void testSetInvalidAmtChocolate() {
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("-5"));
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("choco"));
    }

    @Test
    public void testEquals() {
        Recipe anotherRecipe = new Recipe();
        anotherRecipe.setName("Espresso");
        recipe.setName("Espresso");
        assertTrue(recipe.equals(anotherRecipe));

        anotherRecipe.setName("Latte");
        assertFalse(recipe.equals(anotherRecipe));
    }

    @Test
    public void testToString() {
        recipe.setName("Mocha");
        assertEquals("Mocha", recipe.toString());
    }

    @Test
    public void testHashCode() {
        recipe.setName("Cappuccino");
        Recipe anotherRecipe = new Recipe();
        anotherRecipe.setName("Cappuccino");
        assertEquals(recipe.hashCode(), anotherRecipe.hashCode());

        anotherRecipe.setName("Americano");
        assertNotEquals(recipe.hashCode(), anotherRecipe.hashCode());
    }
    @Test
    public void testEquals_OneRecipeHasNullName() {
        Recipe anotherRecipe = new Recipe();
        anotherRecipe.setName(null);
        recipe.setName("Espresso");
        assertFalse(recipe.equals(anotherRecipe), "Recipe should not be equal if one has a null name.");
    }
    @Test
    public void testSetName_Null() {
        recipe.setName(null);
        assertEquals("", recipe.getName(), "Name should remain empty if set to null.");
    }
    @Test
    public void testToString_EmptyName() {
        assertEquals("", recipe.toString(), "toString() should return an empty string for a recipe with no name.");
    }
    @Test
    public void testHashCode_DifferentNames() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe1.setName("Espresso");
        recipe2.setName("Latte");
        assertNotEquals(recipe1.hashCode(), recipe2.hashCode(), "Hash codes should be different for different names.");
    }
    @Test
    public void testSetName_EmptyString() {
        recipe.setName("");
        assertEquals("", recipe.getName(), "Setting an empty string should not change the name.");
    }
    @Test
    public void testSetPrice_InvalidCharacters() {
        assertThrows(RecipeException.class, () -> recipe.setPrice("!@#$"), "Should throw exception for invalid characters.");
    }

    @Test
    public void testSetAmtChocolate_InvalidCharacters() {
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("abcd"), "Should throw exception for non-numeric input.");
    }

    @Test
    public void testSetAmtMilk_InvalidCharacters() {
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("&*()"), "Should throw exception for special characters.");
    }
    @Test
    public void testHashCode_NullName() {
        Recipe recipe = new Recipe(); // name is null by default
        int hash = recipe.hashCode(); // Should not throw an error
        assertEquals(31, hash, "HashCode should return default value when name is null.");
    }
    @Test
    public void testEquals_NullObject() {
        Recipe recipe = new Recipe();
        assertFalse(recipe.equals(null), "Recipe should not be equal to null.");
    }
    @Test
    public void testEquals_DifferentClass() {
        Recipe recipe = new Recipe();
        Object obj = new Object(); // Different class
        assertFalse(recipe.equals(obj), "Recipe should not be equal to an object of different class.");
    }
    @Test
    public void testEquals_DifferentNames() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Cappuccino");

        Recipe recipe2 = new Recipe();
        recipe2.setName("Latte");

        assertFalse(recipe1.equals(recipe2), "Recipes with different names should not be equal.");
    }
    @Test
    public void testEquals_BothNullNames() {
        Recipe recipe1 = new Recipe(); // name is null by default
        Recipe recipe2 = new Recipe(); // name is null by default

        assertTrue(recipe1.equals(recipe2), "Two recipes with null names should be equal.");
    }
    @Test
    public void testEquals_BothNamesNullDifferentObjects() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        assertTrue(recipe1.equals(recipe2), "Two different Recipe objects with null names should be equal.");
    }
    @Test
    public void testEquals_EmptyName() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("");

        Recipe recipe2 = new Recipe();
        recipe2.setName("");

        assertTrue(recipe1.equals(recipe2), "Two recipes with empty names should be considered equal.");
    }
    @Test
    public void testEquals_SameObject() {
        Recipe recipe = new Recipe();
        assertTrue(recipe.equals(recipe), "Recipe should be equal to itself.");
    }
    @Test
    public void testEquals_SameNames() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Latte");

        Recipe recipe2 = new Recipe();
        recipe2.setName("Latte");

        assertTrue(recipe1.equals(recipe2), "Recipes with identical names should be equal.");
    }
    @Test
    public void testEquals_DifferentObjectType() {
        Recipe recipe = new Recipe();
        String notARecipe = "I am not a recipe";
        assertFalse(recipe.equals(notARecipe), "Recipe should not be equal to a different object type.");
    }
    @Test
    public void testEquals_NullNameVsNonNull() {
        Recipe recipe1 = new Recipe(); // Default name is null
        Recipe recipe2 = new Recipe();
        recipe2.setName("Mocha");

        assertFalse(recipe1.equals(recipe2), "A Recipe with a null name should not be equal to one with a non-null name.");
    }
    @Test
    public void testEquals_BothNamesNull() {
        Recipe recipe1 = new Recipe(); // Default name is null
        Recipe recipe2 = new Recipe(); // Default name is null

        assertTrue(recipe1.equals(recipe2), "Two Recipes with null names should be equal.");
    }
}
