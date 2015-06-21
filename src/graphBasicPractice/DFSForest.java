package graphBasicPractice;

import java.util.ArrayList;

public class DFSForest {
    
    ArrayList<GraphVertex[]> TreeList;
    
    protected ArrayList<GraphVertex[]> getTreeList() {
        return TreeList;
    }

    protected void setTreeList(ArrayList<GraphVertex[]> treeList) {
        TreeList = treeList;
    }

    public DFSForest() {
        TreeList = new ArrayList<GraphVertex[]>();
    }

    public void addTree(GraphVertex[] newTree){
        TreeList.add(newTree);
    }
}
