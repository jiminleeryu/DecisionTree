package sol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.AttributeSelection;
import src.IDataset;
import src.Row;

/**
 * A class representing a training dataset for the decision tree
 */
public class Dataset implements IDataset  {

    /**
     * Constructor for a Dataset object
     * @param attributeList - a list of attributes
     * @param dataObjects -  a list of rows
     * @param attributeSelection - an enum for which way to select attributes
     */

    private List<String> attributeList;
    private List<Row> dataObjects;
    private AttributeSelection attributeSelection;

    /**
     *
     * @param attributeList the list of attributes for the dataset
     * @param dataObjects the list of rows that include the values for each attribute
     * @param attributeSelection the selection in the order of the attributes, whether it is
     *                           alphabetical, reverse, or random.
     */
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection attributeSelection) {
        this.attributeSelection = attributeSelection;
        this.dataObjects = new ArrayList<>(dataObjects);
        this.attributeList = new ArrayList<>(attributeList);
    }
    /**
     * getter method that returns a list of string, attributeList.
     */
    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    /**
     * getter method that returns a list of Row, dataObjecys
     */
    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }
    /**
     * getter method that returns enum AttributeSelection
     */
    @Override
    public AttributeSelection getSelectionType() {
        return this.attributeSelection;
    }
    /**
     * return int, the size of the attributeList
     */
    @Override
    public int size() {
        return this.dataObjects.size();
    }

    /**
     * helper method that returns string in certain orders, either ascending/descending
     * alphbetical, or random.
     * @return returns the sorted attribute list, depending on what the case is,
     * it will return either starting with the first value for ASCENDING ALPHABETICAL,
     * the last value for DESCENDING ALPHABETICAL, and a random index for RANDOM.
     */
    public String getAttributeToSplitOn() {
        switch (this.attributeSelection) {
            case ASCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(0);
            }
            case DESCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
            }
            case RANDOM -> {
                return this.attributeList.stream().sorted().toList().get((int)
                        (this.attributeList.size() * Math.random()));
            }
        }
        throw new RuntimeException("Non-Exhaustive Switch Case");
    }

    /**
     * helper method for the generateTree method in TreeGenerater. Partitions given dataset if given an
     * attribute by returning a list of dataset with each containing rows that have homogenous attribute
     * value for the given attribute.
     * @param attribute the attribute that is passed in to be used to separate the dataset
     * @return the list of datasets that include the separated attributes
     */
    public List<Dataset> partition(String attribute){
        List<String> attributes = new ArrayList<>(this.getAttributeValues(attribute));
        List<Dataset> partitioned = new ArrayList<>();

        List<String> newAttributes = this.attributeList.stream().filter(e -> !e.equals(attribute)).toList();

        for (int i = 0; i < attributes.size(); i++) {
            partitioned.add(new Dataset(newAttributes, new ArrayList<>(), this.attributeSelection));
        }
        for (Row row: this.dataObjects) {
            String rowVal = row.getAttributeValue(attribute);
            int j = attributes.indexOf(rowVal);
            partitioned.get(j).addRow(row);
        }
        return partitioned;
    }

    /**
     * helper method that adds a row to the list this.dataObjects.
     * @param row row that is passed in and added to dataObjects
     */
    public void addRow(Row row){
        this.dataObjects.add(row);
    }

    /**
     * helper method that returns a list of string that contains the different attribute value types for each
     * attribute.
     * @param attribute Attribute that is passed in to find the different options
     * @return List of strings of the different attribute options
     */
    public List<String> getAttributeValues(String attribute){
        ArrayList<String> attributeOptions = new ArrayList<>();
        for (Row dataObject : this.dataObjects) {
            String val = dataObject.getAttributeValue(attribute);
            if (!attributeOptions.contains(val)) {
                attributeOptions.add(val);
            }
        }
        return attributeOptions;
    }

    /**
     * Returns the most common occurrence on the list/ compute default value
     * @param targetAttribute target attribute (isMammal)
     * @return String of the most common value (true/false)
     */
    public String getMaxOccur(String targetAttribute){
        int max = 0;
        String getMaxOccur = null;
        List<Dataset> partitionSet = this.partition(targetAttribute);
        for (int i = 0; i < partitionSet.size(); i++) {
            if(partitionSet.get(i).size() >= max){
                getMaxOccur = partitionSet.get(i).dataObjects.get(0).getAttributeValue(targetAttribute);
                max = partitionSet.get(i).size();
            }
        }
        return getMaxOccur;
    }
}


