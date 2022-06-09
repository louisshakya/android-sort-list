package com.louis.androidlistapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ValidatorTest {
    //Dummy test
    @Test
    void add(){
        Validator test = new Validator();
        int output = test.add(2,2);
        int expected = 4;
        assertEquals(expected,output);
    }

    @Test
    void testToRemoveNullNames() {
        //Input
        ArrayList<List> input = new ArrayList<>();
        input.add(new List("755", "2", ""));
        input.add(new List("203","2",""));
        input.add(new List("684","1","Item 684"));
        input.add(new List("276","1","Item 276"));
        input.add(new List("736","3","null"));

        //Expected
        ArrayList<List> expected = new ArrayList<>();
        expected.add(new List("684","1","Item 684"));
        expected.add(new List("276","1","Item 276"));

        Validator test = new Validator();
        test.removeEmptyNames(input);
        //Output
        ArrayList<List> output = new ArrayList<>(input);
        for (int i = 0; i < output.size(); i++){
            assertEquals(expected.get(i).getName(),output.get(i).getName());
            assertEquals(expected.get(i).getId(),output.get(i).getId());
            assertEquals(expected.get(i).getListId(),output.get(i).getListId());
        }

    }

    @Test
    void testToSortByListId() {
        //Input
        ArrayList<List> input = new ArrayList<>();
        input.add(new List("684","1","Item 684"));
        input.add(new List("276","1","Item 276"));
        input.add(new List("808","4","Item 808"));
        input.add(new List("680","3","Item 680"));
        input.add(new List("534","4","Item 534"));

        //Expected
        ArrayList<List> expected = new ArrayList<>();
        expected.add(new List("684","1","Item 684"));
        expected.add(new List("276","1","Item 276"));
        expected.add(new List("680","3","Item 680"));
        expected.add(new List("808","4","Item 808"));
        expected.add(new List("534","4","Item 534"));

        Validator test = new Validator();
        test.sortByListId(input);

        //Output
        ArrayList<List> output = new ArrayList<>(input);
        for (int i = 0; i < output.size(); i++){
            assertEquals(expected.get(i).getName(),output.get(i).getName());
            assertEquals(expected.get(i).getId(),output.get(i).getId());
            assertEquals(expected.get(i).getListId(),output.get(i).getListId());
        }
    }

    @Test
    void testToSortByName() {
        //Input
        ArrayList<List> input = new ArrayList<>();
        input.add(new List("684","1","Item 684"));
        input.add(new List("276","1","Item 276"));
        input.add(new List("808","4","Item 808"));
        input.add(new List("680","3","Item 680"));
        input.add(new List("534","4","Item 534"));

        //Expected
        ArrayList<List> expected = new ArrayList<>();
        expected.add(new List("276","1","Item 276"));
        expected.add(new List("534","4","Item 534"));
        expected.add(new List("680","3","Item 680"));
        expected.add(new List("684","1","Item 684"));
        expected.add(new List("808","4","Item 808"));

        Validator test = new Validator();
        test.sortByName(input);

        //Output
        ArrayList<List> output = new ArrayList<>(input);
        for (int i = 0; i < output.size(); i++){
            assertEquals(expected.get(i).getName(),output.get(i).getName());
            assertEquals(expected.get(i).getId(),output.get(i).getId());
            assertEquals(expected.get(i).getListId(),output.get(i).getListId());
        }
    }

    @Test
    void testToSortByBoth() {
        //Input
        ArrayList<List> input = new ArrayList<>();
        input.add(new List("684","1","Item 684"));
        input.add(new List("276","1","Item 276"));
        input.add(new List("808","4","Item 808"));
        input.add(new List("680","3","Item 680"));
        input.add(new List("534","4","Item 534"));

        //Expected
        ArrayList<List> expected = new ArrayList<>();
        expected.add(new List("276","1","Item 276"));
        expected.add(new List("684","1","Item 684"));
        expected.add(new List("680","3","Item 680"));
        expected.add(new List("534","4","Item 534"));
        expected.add(new List("808","4","Item 808"));

        Validator test = new Validator();

        //Output
        ArrayList<List> output = new ArrayList<>();
        output = test.sortByBoth(input);
        for (int i = 0; i < output.size(); i++){
            assertEquals(expected.get(i).getName(),output.get(i).getName());
            assertEquals(expected.get(i).getId(),output.get(i).getId());
            assertEquals(expected.get(i).getListId(),output.get(i).getListId());
        }
    }
}