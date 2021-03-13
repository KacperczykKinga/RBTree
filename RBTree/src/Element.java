
public class Element<T> {
    Element parent;
    Element leftSon;
    Element rightSon;
    String colour;
    String[] drugieDno;
    T value;
    
    public Element(T something) {
    	this.value=something;
    	this.colour="RED";
    }
    
    public void setDrugieDno(String[] tablicaOdmian) {
    	drugieDno=tablicaOdmian;
    }
    public void setParent(Element parent) {
    	this.parent=parent;
    }

    public void setLeftSon(Element left) {
    	this.leftSon=left;
    }
    
    public void setRightSon(Element right) {
    	this.rightSon=right;
    }
    
    public void setValue(T value) {
    	this.value=value;
    }
    
    public T getValue() {
    	return value;
    }
    
    public Element getParent() {
    	return parent;
    }
    
    public Element getLeftSon() {
    	return leftSon;
    }
    
    public Element getRightSon() {
    	return rightSon;
    }
    
    public String getColour() {
    	return colour;
    }
     public String[] getDrugieDno() {
    	 return drugieDno;
     }
    public void setColour(String kolor) {
    	this.colour=kolor;
    }
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


