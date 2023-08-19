package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
public class DecisionLeaf implements ITreeNode {
    private String result;
    /**
     * method form ITreeNode interface.
     */
    public DecisionLeaf(String result){
        this.result = result;
    }
    @Override
    public String getDecision(Row forDatum) {
        return this.result;
    }
}
