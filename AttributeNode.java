package sol;

import java.util.List;
import src.ITreeNode;
import src.Row;

/**
 * A class representing an inner node in the decision tree.
 */
public class AttributeNode implements ITreeNode {
    private List<ValueEdge> outgoingEdges;
    private String def, attribute;

    /**
     * The constructor of AttributeNode, responsible for creating the fields of an attribute node such as
     * its outgoing edges, definition, and attribute name.
     * @param def default value that comes from the default value from the attribute node
     * @param attribute the specific attribute that the node comprises
     */
    public AttributeNode(String def, String attribute){
        this.outgoingEdges = null;
        this.def = def;
        this.attribute = attribute;
    }

    /**
     * sets the outgoing edges to the List of value edges
     * @param edges the new edge that is set to the instance of outgoingEdges
     */
    public void setOutgoingEdges(List<ValueEdge> edges) {
        this.outgoingEdges = edges;
    }
    /**
     * the getDecision method from the ITreeNode interface.
     */
    @Override
    public String getDecision(Row forDatum) {
        for (int i = 0; i < this.outgoingEdges.size(); i++) {
            if(this.outgoingEdges.get(i).getResult().equals(forDatum.getAttributeValue(this.attribute))){
                return this.outgoingEdges.get(i).getChild().getDecision(forDatum);
            }
        }
        return this.def;
    }
}
