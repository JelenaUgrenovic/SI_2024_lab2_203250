package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void checkCart() {
    }

    @Test
    public void checkCart_EmptyItemList_ReturnsFalse() {
        List<Item> allItems = new ArrayList<>();
        assertTrue(SILab2.checkCart(allItems, 100));
    }

    @Test
    public void checkCart_OneItemWithDiscountAndValidBarcode_ReturnsTrue() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("Name", "0123", 200, 0.1f));
        assertTrue(SILab2.checkCart(allItems, 300));
    }

    @Test
    public void checkCart_OneItemWithoutBarcode_ThrowsException() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("Name", null, 200, 0));
        try {
            SILab2.checkCart(allItems, 100);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException ex) {
            assertEquals("No barcode!", ex.getMessage());
        }
    }

    @Test
    public void checkCart_OneItemWithInvalidBarcode_ThrowsException() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("Name", "abc123", 200, 0));
        try {
            SILab2.checkCart(allItems, 100);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException ex) {
            assertEquals("Invalid character in item barcode!", ex.getMessage());
        }
    }

    @Test
    public void checkCart_OneItemWithDiscountUnder300AndNonZeroStartingBarcode_ReturnsTrue() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("Name", "12345", 200, 0.1f));
        assertTrue(SILab2.checkCart(allItems, 100));
    }

    @Test
    public void checkCart_OneItemWithDiscountOver300AndNonZeroStartingBarcode_ReturnsTrue() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("Name", "12345", 400, 0.1f));
        assertTrue(SILab2.checkCart(allItems, 350));
    }

    @Test
    public void checkCart_OneItemWithDiscountOver300AndZeroStartingBarcode_ReturnsTrue() {
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("Name", "012345", 400, 0.1f));
        assertTrue(SILab2.checkCart(allItems, 350));
    }

    @Test
    public void checkCart_ConditionCombination_AllConditionsTrue_ReturnsTrue() {
        Item item = new Item("Name", "012345", 400, 0.1f);
        assertTrue(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_FirstTwoTrueThirdFalse_ReturnsFalse() {
        Item item = new Item("Name", "112345", 400, 0.1f);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_FirstThirdTrueSecondFalse_ReturnsFalse() {
        Item item = new Item("Name", "012345", 400, 0);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_SecondThirdTrueFirstFalse_ReturnsFalse() {
        Item item = new Item("Name", "012345", 200, 0.1f);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_OnlyFirstTrue_ReturnsFalse() {
        Item item = new Item("Name", "112345", 400, 0);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_OnlySecondTrue_ReturnsFalse() {
        Item item = new Item("Name", "112345", 200, 0.1f);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_OnlyThirdTrue_ReturnsFalse() {
        Item item = new Item("Name", "012345", 200, 0);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }

    @Test
    public void checkCart_ConditionCombination_AllConditionsFalse_ReturnsFalse() {
        Item item = new Item("Name", "112345", 200, 0);
        assertFalse(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0');
    }
}