package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    private String val;
    private ITreeNode child;

    /**
     * ValueEdge constructor that is called when creating an edge of an attribute node in the
     * decision tree.
     * @param val the string that represents the value that the edge represents
     * @param next the node that points to the next attribute node
     */
    public ValueEdge(String val, ITreeNode next) {
        this.val = val;
        this.child = next;
    }

    /**
     * result of whats given to the value edge
     * @return the value of the valueEdge
     */
    public String getResult(){
        return this.val;
    }

    /**
     * gets the child node of the ITreeNode
     * @return the child node
     */
    public ITreeNode getChild(){
        return this.child;
    }

}
