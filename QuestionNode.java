//Finn Parker
//3.11.21
//CS145 - Darrell Criss
//Lab 6 - 20 Questions
//Defines a QuestionNode object, which in turn defines a QuestionTree

public class QuestionNode {
	// essential variables
	public String data;
	public QuestionNode yes;
	public QuestionNode no;

//constructors
	public QuestionNode(String data) {
		this(data, null, null);
	}

	public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
		this.data = data;
		this.yes = yes;
		this.no = no;
	}
}
