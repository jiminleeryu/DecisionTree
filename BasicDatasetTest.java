package sol;

import org.junit.Test;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class to test basic decision tree functionality on a basic training dataset
 */
public class BasicDatasetTest {
    // IMPORTANT: for this filepath to work, make sure the project is open as the top-level directory in IntelliJ
    // (See the first yellow information box in the handout testing section for details)
    String trainingPath = "data/animals.csv"; // TODO: replace with your own input file
    String targetAttribute = "isMammal"; // TODO: replace with your own target attribute
    TreeGenerator testGenerator;
    Dataset trainingSet;

    /**
     * Constructs the decision tree for testing based on the input file and the target attribute.
     */
    @Before
    public void buildTreeForTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        this.trainingSet = training;
        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator = new TreeGenerator();
        this.testGenerator.generateTree(training, this.targetAttribute);
    }

    /**
     * Tests the expected classification of the "tangerine" row is a fruit
     */
    @Test
    public void testClassification() {
        // makes a new (partial) Row representing the bear from the example
        // TODO: make your own rows based on your dataset
        Row bear = new Row("test row (bear)");
        bear.setAttributeValue("fur", "true");
        bear.setAttributeValue("milk", "true");
        bear.setAttributeValue("size", "big");
        bear.setAttributeValue("numberOfLimbs", "4");
        bear.setAttributeValue("tails", "true");
        bear.setAttributeValue("flies", "false");
        bear.setAttributeValue("sleepingPattern", "diurnal");
        bear.setAttributeValue("numberOfEyes", "2");
        // TODO: make your own assertions based on the expected classifications
        // TODO: Uncomment this once you've implemented getDecision
//        Assert.assertEquals("fruit", this.testGenerator.getDecision(bear));
    }

    /**
     * Tests row size by using the size method in the Row class. instantiates
     * a new Row and calls setAttributeValue to assign the data for the row.
     */
    @Test
    public void testRowSize(){
        Row bear = new Row("test row (bear)");
        bear.setAttributeValue("fur", "true");
        bear.setAttributeValue("milk", "true");
        bear.setAttributeValue("size", "big");
        bear.setAttributeValue("numberOfLimbs", "4");
        bear.setAttributeValue("tails", "true");
        bear.setAttributeValue("flies", "false");
        bear.setAttributeValue("sleepingPattern", "diurnal");
        bear.setAttributeValue("numberOfEyes", "2");

        assertEquals(8, bear.getAttributes().size());
        assertEquals("big", bear.getAttributeValue("size"));
    }
    /**
     * Tests getting the default attribute value in a subset of data.
     */
    @Test
    public void testGetMaxOcc(){
        assertEquals("small", this.trainingSet.getMaxOccur("size"));
        assertEquals("false", this.trainingSet.getMaxOccur("milk"));
        assertEquals("false", this.trainingSet.getMaxOccur("fur"));

        assertEquals("true", this.trainingSet.getMaxOccur( "tails"));
        assertEquals("false", this.trainingSet.getMaxOccur("flies"));
        assertEquals("diurnal", this.trainingSet.getMaxOccur("sleepingPattern"));

    }
    /**
     * Tests getting a string list of the different attribute values in a given attribute.
     */
    @Test
    public void testGetAttributes(){
        System.out.println(this.trainingSet.getAttributeValues("sleepingPattern"));
    }

    /**
     * Tests the partition method in the Dataset class which is supposed to return a list
     * of datasets that each has rows with homogenous attribute value for a given
     * attribute.
     */
    @Test
    public void testPartition(){
        assertEquals("diurnal", this.trainingSet.partition("sleepingPattern").get(0).getDataObjects().get(0).
                getAttributeValue("sleepingPattern"));
        assertEquals("nocturnal", this.trainingSet.partition("sleepingPattern").get(1).getDataObjects().get(0).
                getAttributeValue("sleepingPattern"));

        assertEquals("false", this.trainingSet.partition("flies").get(0).getDataObjects().get(5).
                getAttributeValue("flies"));
        assertEquals("true", this.trainingSet.partition("flies").get(1).getDataObjects().get(0).
                getAttributeValue("flies"));
    }

    /**
     * Tests decision tree generation.
     */
    @Test
    public void testTreeGeneration(){
        this.testGenerator.generateTree(this.trainingSet, "isMammal");
    }
}
