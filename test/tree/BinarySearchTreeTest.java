package tree;

import org.junit.Test;

public class BinarySearchTreeTest {
    
    @Test
    public void shouldCreateBST() {
        
        
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);tree.viewBST();
        tree.insert(5);tree.viewBST();
        tree.insert(6);tree.viewBST();
        
        
    }

}
