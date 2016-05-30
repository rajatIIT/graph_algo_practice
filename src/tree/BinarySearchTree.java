package tree;

import java.util.ArrayList;

public class BinarySearchTree {

	Node rootNode;
	protected int idCounter;
	ArrayList<Node> nodeList;

	// =======================
	// Create a simple BST.
	// @author rajatpawar
	// =======================
	public BinarySearchTree() {
		nodeList = new ArrayList<Node>();
		idCounter = 0;
	}

	public void insert(int data) {
		insertIntoBST(rootNode, data);
	}

	public void viewBST() {
		Utils.printBinaryTree(nodeList);
	}

	public void insertIntoBST(Node node, int data) {

		if (rootNode == null) {
			rootNode = new Node(idCounter, data);
			idCounter++;
			nodeList.add(rootNode);
		} else {

			// traverse from root to insert into BST.
			if (data > node.getData()) {
				// insert into the right side of the tree;
				if (node.getRight() == null) {

					// Execute insertion
					// insert the node right here left to this
					// node. add the new
					// node to the list of nodes.
					Node nodeToBeInserted = new Node(idCounter, data);
					idCounter++;
					node.setRight(nodeToBeInserted);
					nodeList.add(nodeToBeInserted);
				} else
					insertIntoBST(node.getRight(), data);

			} else if (data < node.getData()) {
				// insert into the left side of the tree;
				if (node.getLeft() == null) {
					// insert the node right here: to the left
					Node nodeToBeInserted = new Node(idCounter, data);
					idCounter++;
					node.setLeft(nodeToBeInserted);
					nodeList.add(nodeToBeInserted);
				} else
					insertIntoBST(node.getLeft(), data);

			} else if (data == node.getData()) {
				System.out.println("Data cannot be inserted since"
						+ " duplicate nodes are not allowed in BST.");
			}

		}

	}

}
