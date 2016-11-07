public class musMain {
public static void main(String args[]){
	login l = new login();
	l.logon(); //Login
}
}


public interface iLogin {
	String savePass = "CSC250"; //Password 
}


public class login implements iLogin{ //interface
	public void logon(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to #Music!");
		System.out.print("Enter your Username: "); //Any username acceptable
		String x = scan.nextLine();
		password();
		System.out.println("\nWelcome " + x + ".");
		scanRead s = new scanRead();
		ArrayList<ArrayList<String>> currentList = new ArrayList<ArrayList<String>>();
		currentList = s.textRead();
		menu m = new menu();
		m.mainMenu(currentList);
	}
	private void password(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the public password: "); //must be CSC250
		String y = scan.nextLine();
		if(savePass.equals(y)){
		}
		else{
			System.out.println("Sorry, wrong password.");
			password();
		}
	}
}
public class scanRead {
	ArrayList<ArrayList<String>> lol = new ArrayList<ArrayList<String>>();
	Sort s = new Sort();
	public ArrayList<ArrayList<String>> textRead(){
		String fileName = "C://Users/Randell/Desktop/Java/data.txt";
		String line = null;


		try{//Reads from txt
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);


			while((line = bufferedReader.readLine()) != null) {


				List<String> list = new ArrayList<String>(Arrays.asList(line.split(" ")));
				lol.add((ArrayList<String>) list);
			}
			bufferedReader.close();
			lol = s.sSort(lol); //Sorts Alphabetically
			arraylistBST sortedbycost = new arraylistBST();
			sortedbycost.sort();//Sorts into a tree
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file '" + fileName + "'");


		}
		catch(IOException ex){
			System.out.println("Error reading file '" + fileName + "'");
		}
		return lol;
	}
	public void textWrite(ArrayList<ArrayList<String>> save){
		try{
			FileWriter fileWriter = new FileWriter("C://Users/Randell/Desktop/Java/data.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


			for(int i = 0; i< save.size(); i++)
			{
				for(int j = 0; j< save.get(i).size(); j++)
				{
					bufferedWriter.write(save.get(i).get(j) + " ");
				}
				bufferedWriter.newLine();
			}


			//closes writer
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + "filetoberead.txt" + "'");
		}
		catch(IOException ex)
		{
			System.out.println("Error reading file '" + "filetoberead.txt" + "'");
		}
	}
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;






public class Sort{
	public ArrayList<ArrayList<String>> sSort(ArrayList<ArrayList<String>> list){
		Collections.sort(list, new Comparator<ArrayList<String>>(){
	        @Override
	        public int compare(ArrayList<String> arg0, ArrayList<String> arg1) {
	            return arg0.get(1).compareTo(arg1.get(1));
	        } //Selection Sort
	    });
		return list;
	}
}
public class arraylistBST
{
	public static  Node root;
	public arraylistBST(){
		this.root = null;
	}


	public boolean find(double id)
	{
		Node current = root;
		while(current!=null){
			if(current.data==id){
				return true;
			}else if(current.data>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	public boolean delete(double id)
	{
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.data!=id){
			parent = current;
			if(current.data>id){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){


			//now we have found the minimum element in the right sub tree
			Node successor	= getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}


	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
		//	successsorParent
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	public void insert(double id, ArrayList<String> list){
		Node newNode = new Node(id, list);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(id<current.data){
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	public void display(Node root){
		if(root!=null){
			display(root.left);
			System.out.println(root.data + " " + root.list.get(1) + " "
					+ root.list.get(2) + " " + root.list.get(3) + " " );
			display(root.right);
		}
	}
	public void sort(){
		String fileName = "C://Users/Randell/Desktop/Java/data.txt";
		String line = null;
		ArrayList<ArrayList<String>> lol = new ArrayList<ArrayList<String>>();
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				List<String> list = new ArrayList<String>(Arrays.asList(line.split(" ")));
				lol.add((ArrayList<String>) list);
			}
			bufferedReader.close();
			arraylistBST b = new arraylistBST();




			for(int i = 0; i < lol.size(); i++)
			{
				b.insert(Double.parseDouble(lol.get(i).get(4)), lol.get(i));
			}
			System.out.println("");
			System.out.println("Inventory by price : ");
			b.display(b.root);
			System.out.println("");
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file '" + fileName + "'");            
		}
		catch(IOException ex){
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
}
class Node{
	double data;
	ArrayList<String> list;
	Node left;
	Node right;
	public Node(double data, ArrayList<String> list){
		this.data = data;
		this.list = list;
		left = null;
		right = null;
	}
public class menu extends modify{
	scanRead s = new scanRead();
	Scanner scan= new Scanner(System.in);
	Scanner scan1= new Scanner(System.in);
	Search find = new Search();
	modify e = new modify();
	Sort p = new Sort();
	


	protected void mainMenu(ArrayList<ArrayList<String>> currentList){ //Always return here after method completed
		p.sSort(currentList);
		System.out.println("\nWhat would you like to do?\n"
				+ "1) Add\n"
				+ "2) Delete\n"
				+ "3) Change\n"
				+ "4) Make data backup for original list\n"
				+ "5) Display All\n"
				+ "6) Search\n"
				+ "7) Save and Exit");
		int choice = scan1.nextInt();
		if(choice == 1){
			ArrayList<String> z = e.addon();
			currentList.add(z);
			mainMenu(currentList);
		}
		else if(choice == 2){
			currentList = e.delete(currentList);
			mainMenu(currentList);
		}
		else if(choice == 3){
			currentList = e.delete(currentList);
			ArrayList<String> z = e.addon();
			currentList.add(z);
			mainMenu(currentList);
		}
		else if (choice == 4){
			copy();
			mainMenu(currentList);
		}
		else if (choice == 5){
			displayAll(currentList);
			mainMenu(currentList);
		}
		else if(choice == 6){
			Search g = new Search();
			g.sSearch(currentList);
			mainMenu(currentList);
		}
		else if(choice == 7){
			s.textWrite(currentList);
		}
	}
}
public class modify {
	protected ArrayList<String> addon(){
		int instrument;
		String brand;
		String serial;
		String price;
		Scanner scan = new Scanner(System.in);
		ArrayList<String> data = new ArrayList<String>();
		
		//listing instruments for user to see
		System.out.println("1: Trumpet");
		System.out.println("2: Trombone");
		System.out.println("3: Tuba");
		System.out.println("4: Horn");
		System.out.println("5: Piano");
		System.out.println("6: Keyboard");
		System.out.println("7: Drumset");
		System.out.println("8: Xylophone");
		System.out.println("9: Saxophone");
		System.out.println("10: Flute");
		System.out.println("11: Clarinet");
		System.out.println("12: Oboe");
		System.out.println("13: Piccolo");
		System.out.println("14: Bassoon");
		System.out.println("15: Violin");
		System.out.println("16: Viola");
		System.out.println("17: Cello");
		System.out.println("18: Bass");
		System.out.println("19: Guitar");
		System.out.println("20: Harp");
		
		System.out.print("Type the key value of the instrument being added: ");
		instrument = scan.nextInt();
		
		//adds the key value and instrument into arraylist for you based on what you want
		switch(instrument)
		{
			case 1:
				data.add("1");
				data.add("Trumpet");
				break;
			case 2:
				data.add("2");
				data.add("Trombone");
				break;
			case 3:
				data.add("3");
				data.add("Tuba");
				break;
			case 4:
				data.add("4");
				data.add("Horn");
				break;
			case 5:
				data.add("5");
				data.add("Piano");
				break;
			case 6:
				data.add("6");
				data.add("Keyboard");
				break;
			case 7:
				data.add("7");
				data.add("Drumset");
				break;
			case 8:
				data.add("8");
				data.add("Xylophone");
				break;
			case 9:
				data.add("9");
				data.add("Saxophone");
				break;
			case 10:
				data.add("10");
				data.add("Flute");
				break;
			case 11:
				data.add("11");
				data.add("Clarinet");
				break;
			case 12:
				data.add("12");
				data.add("Oboe");
				break;
			case 13:
				data.add("13");
				data.add("Piccolo");
				break;
			case 14:
				data.add("14");
				data.add("Bassoon");
				break;
			case 15:
				data.add("15");
				data.add("Violin");
				break;
			case 16:
				data.add("16");
				data.add("Viola");
				break;
			case 17:
				data.add("17");
				data.add("Cello");
				break;
			case 18:
				data.add("18");
				data.add("Bass");
				break;
			case 19:
				data.add("19");
				data.add("Guitar");
				break;
			case 20:
				data.add("20");
				data.add("Harp");
				break;
			default:
				System.out.println("Instrument not found");
		}
				
		System.out.print("Enter the brand of the instrument: ");
		brand = scan.next();
		data.add(brand);
		
		System.out.print("Enter a serial number: ");
		serial = scan.next();
		data.add(serial);
		
		System.out.print("Enter the price: ");
		price = scan.next();
		data.add(price);
		return data;
	}
	
protected ArrayList<ArrayList<String>> delete(ArrayList<ArrayList<String>> list){
		Scanner scan = new Scanner(System.in);
		System.out.println("Search: ");
		String search = scan.nextLine();
		for(int o = 0; o < list.size(); o++){
			if(list.get(o).contains(search) == true){
				System.out.println(list.get(o)+" has been deleted.");
				list.remove(list.get(o));
				return list;
			}
		}
		return list;
	}
	
protected void displayAll(ArrayList<ArrayList<String>> lol){
		for(int k = 0; k < lol.size(); k++)
        {
            for(int i = 1; i < lol.get(k).size(); i++)
            {
                System.out.print(lol.get(k).get(i) + "     " + "\t");
            }
            System.out.println(""); 
        }
	}
	
protected void copy(){
		String fileInput = "C://Users/Randell/Desktop/Java/data.txt";
        		String fileoutput = "C://Users/Randell/Desktop/Java/dataBACKUP.txt";
        try 
        {
            FileReader fr=new FileReader(fileInput);
            FileWriter fw=new FileWriter(fileoutput);


            int c;
            while((c=fr.read())!=-1) 
            {
                fw.write(c);
            } 
            fr.close();
            fw.close();


        } 
        catch(IOException e) {
            System.out.println(e);
        } 
        
        System.out.println("Data has been backed up to a separate file.");
	}		
}
public class Search {
	Scanner scan = new Scanner(System.in);
	
	public void sSearch(ArrayList<ArrayList<String>> list){
		System.out.println("Search: ");
		String search = scan.nextLine();


		for(int o = 0; o < list.size(); o++){ //Linear Search
			if(list.get(o).contains(search) == true){
				System.out.println(list.get(o));
			}
		}
	}
}
