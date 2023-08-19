package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
public class DecisionTreeTest {
    /**
     * DecisionTreeTest constructor for taking in a training path and target attribute
     */
    //TODO: Write more unit and system tests! Some basic guidelines that we will be looking for:
    // 1. Small unit tests on the Dataset class testing the IDataset methods
    // 2. Small unit tests on the TreeGenerator class that test the ITreeGenerator methods
    // 3. Tests on your own small dataset (expect 70% accuracy on testing data, 95% on training data)
    // 4. Test on the villains dataset (expect 70% accuracy on testing data, 95% on training data)
    // 5. Tests on the mushrooms dataset (expect 70% accuracy on testing data, 95% on training data)
    // Feel free to write more unit tests for your own helper methods -- more details can be found in the handout!
    public DecisionTreeTest(){

        String trainingPath = "data/animals.csv";
        String targetAttribute = "isMammal";
        TreeGenerator testGenerator;
    }
    /**
     * Constructs the decision tree for testing based on the input file and the target attribute.
     */
    @Test
    public void testBasicMethod(){//test getters and size

    }
    /**
     * Tests basic dataset methods in the Dataset class
     */
    @Test
    public void testDatasetMethods(){//test the methods in dataset

    }
    /**
     * Tests the AttributeToSplitOn in the Dataset class
     */
    @Test
    public void testAttributeToSplitOn(){//test attributeToSplitON

    }

    /**
     * Tests the getDef helper method in the Dataset class.
     */
    @Test
    public void testGetDef(Dataset trainingData, String targetAttribute){
        //assertEquals("false", testGenerator);
    }
}
