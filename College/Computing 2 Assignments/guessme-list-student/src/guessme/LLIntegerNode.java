package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode {
  // TODO
	private int info;
	private LLIntegerNode link;
	
	public LLIntegerNode(int info, LLIntegerNode link) {
		this.info = info;
		this.link = link;
	}
	
	public LLIntegerNode getLink() {
		return link;
	}
	public int getInfo() {
		return info;
	}
	
	public void setInfo(int newInfo) {
		info = newInfo;
	}
	public void setLink(LLIntegerNode newLink) {
		link = newLink;
	}
}

