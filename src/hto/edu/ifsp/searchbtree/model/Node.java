package hto.edu.ifsp.searchbtree.model;

import java.io.PrintStream;

public class Node {
	private Integer content;
	private Node father;
	private Node leftChild;
	private Node rightChild;
	
	public Node(int value, Node father, Node leftChild, Node rightChild) {
		super();
		this.content = value;
		this.father = father;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	public int getContent() {
		return content;
	}
	public void setContent(int value) {
		this.content = value;
	}
	public Node getFather() {
		return father;
	}
	public void setFather(Node father) {
		this.father = father;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		if (leftChild != null){
			leftChild.setFather(this);
		}
		this.leftChild = leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		if (rightChild != null){
			rightChild.setFather(this);
		}
		this.rightChild = rightChild;
	}
	
	public void printTree(PrintStream out){
        if (rightChild != null) {
        	rightChild.printTree(out, true, "");
        }
        printNodeValue(out);
        if (leftChild != null) {
        	leftChild.printTree(out, false, "");
        }
    }
	
    private void printNodeValue(PrintStream out){
        if (content == null) {
            out.print("<null>");
        } else {
            out.print(content.toString());
        }
        out.write('\n');
    }
    
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(PrintStream out, boolean isRight, String indent){
        if (rightChild != null) {
        	rightChild.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.print(indent);
        if (isRight) {
            out.print(" /");
        } else {
            out.print(" \\");
        }
        out.print("----- ");
        printNodeValue(out);
        if (leftChild != null) {
        	leftChild.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }

	
}
