package sol;

import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {

    private ITreeNode root;

    /**
     * generateTree method that starts the tree generation with the starting attribute
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        Dataset copy = new Dataset(trainingData.getAttributeList(), trainingData.getDataObjects(),
                trainingData.getSelectionType());
        copy.getAttributeList().remove(targetAttribute);
        String startingAttribute = trainingData.getAttributeToSplitOn();
        copy.getAttributeList().remove(startingAttribute);
        this.root = this.generateBranch(trainingData, targetAttribute);
    }

    /**
     * Helper method for generateBranch that returns ITreeNode, either an AttributeNode
     * or DecisionLeaf
     * @param subset dataset that will be used as part of a recursive call
     * @param targetAttribute the outcome attribute value
     * @return Depending on size of the subsets, generateBranch will return
     * either a new DecisionLeaf or attributeNode
     */
    public ITreeNode generateBranch(Dataset subset, String targetAttribute) {

        String mostFrequentValue = subset.getMaxOccur(targetAttribute);

        if (this.shouldStopSplitting(subset, targetAttribute)) {
            return new DecisionLeaf(mostFrequentValue);
        } else {
            String attributeToSplitOn = subset.getAttributeToSplitOn();
            AttributeNode newNode = new AttributeNode(mostFrequentValue, attributeToSplitOn);
            List<Dataset> subDatasets = subset.partition(attributeToSplitOn);
            List<ValueEdge> outgoingEdges = new ArrayList<>();
            for (Dataset subDataset : subDatasets) {
                ITreeNode branch = this.generateBranch(subDataset, targetAttribute);
                String attributeValue = subDataset.getDataObjects().get(0).getAttributeValue(attributeToSplitOn);
                outgoingEdges.add(new ValueEdge(attributeValue, branch));
            }
            newNode.setOutgoingEdges(outgoingEdges);
            return newNode;
        }
    }

    /**
     * ShouldStopSplitting method that checks when the decision tree should generate a decisionLeaf
     * @param subset the dataset that is checked to see if its contents meet the requirements
     *               of creating a decisionLeaf
     * @param targetAttribute the attribute that is used on partition to check if the size of
     *                        the partition becomes the same value
     * @return boolean deciding whether to stop splitting or continue
     */
    private boolean shouldStopSplitting(Dataset subset, String targetAttribute) {
        return subset.getAttributeList().isEmpty()
                || subset.partition(targetAttribute).isEmpty()
                || subset.getDataObjects().size() == 1
                || subset.partition(targetAttribute).size() == 1;
    }

    /**
     *
     * @param datum the datum to look up a decision for
     * @return the decision for a Row name passed into its parameter.
     */
    @Override
    public String getDecision(Row datum) {
        return this.root.getDecision(datum);
    }
}
