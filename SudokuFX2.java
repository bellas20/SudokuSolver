package com.drew.sudoku_solver;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SudokuFX2 extends Application{
	
	int[][] sudoMatrix = new int[9][9];
	Integer[][] sudoMatrixInt = new Integer[9][9];
	TextField[][] displayMatrix = new TextField[9][9];
	TextField statusText = new TextField();
	int iter = 0;

	public static void main(String[] args){

		launch(args);

	}//end main


	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Sudoku Solver");

		//String inputData = "437618952518294637629573184193462875756381429284957316372846591941735268865129743";
		//String mask = "100011001010100110101111110010111111010000010111111010011111101011001010100110001";
		String inputData = "395164827781592436246378195653781249879426513124953768932815674517649382468237951";
		String mask =      "101100111011010101100111111100100110111101111011001001111111001101010110111001101";
		
		String maskedData = "";
		
		for (int i=0;i<inputData.length(); i++) {
			char c = inputData.charAt(i);
			char m = mask.charAt(i);
			int numM = (int)m;
			String cStr = Character.toString(c);
			if(numM==48) {
				maskedData += cStr;
			}
			else if(numM==49){
				maskedData += "0";
			}
		}
		System.out.println(maskedData);
		int[][] data = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};	

		int ctrA = 0;
		for (int r=8;r>-1;r--) {
			for (int c=0;c<9;c++) {
					char charB = maskedData.charAt(ctrA);
					data[r][c] = Character.getNumericValue(charB);
					ctrA++;
				
			}
		}
		
		//Initialize Puzzle
		
		//current algorithm cannot solve
		//int[][] data = {{2,0,0,0,0,7,0,0,0},{0,6,4,9,1,5,0,0,0},{0,8,0,0,0,0,0,0,4},{0,0,0,3,0,1,0,7,5},{1,9,0,5,0,8,0,2,6},{5,7,0,2,0,4,0,0,0},{8,0,0,0,0,0,0,3,0},{4,3,0,8,2,9,5,6,0},{0,0,0,1,0,0,0,0,8}};	

		//solvable
		//int[][] data = {{1,9,0,0,0,0,0,3,0},{0,0,2,0,9,0,0,0,1},{0,8,0,0,0,0,0,6,0},{0,3,9,7,0,8,4,0,2},{0,2,0,3,0,9,0,5,0},{8,0,6,5,0,1,3,9,0},{0,6,0,0,0,0,0,4,0},{3,0,0,0,5,0,6,0,0},{0,5,0,0,0,0,0,2,3}};	

		//current algorithm cannot solve
		//int[][] data = {{0,0,0,8,1,0,9,0,0},{0,0,0,0,0,0,1,8,7},{9,0,0,0,0,2,0,0,0},{4,0,0,9,0,0,0,5,0},{0,0,0,1,5,3,0,0,0},{0,6,0,0,0,8,0,0,3},{0,0,0,3,0,0,0,0,8},{5,3,6,0,0,0,0,0,0},{0,0,9,0,7,5,0,0,0}};	

		//solvable
		//int[][] data = {{0,0,9,4,6,7,0,0,0},{0,3,0,0,1,5,0,0,9},{0,5,7,0,2,0,8,0,0},{0,0,3,6,0,0,0,9,8},{9,0,0,0,0,0,0,0,2},{8,2,0,0,0,4,6,0,0},{0,0,8,0,9,0,1,2,0},{5,0,0,2,4,0,0,7,0},{0,0,0,1,7,6,5,0,0}};	

		//solvable
		//int[][] data = {{0,7,4,0,0,6,0,0,9},{0,0,0,0,4,8,0,0,0},{5,0,0,9,0,0,6,0,0},{2,5,1,7,0,0,0,0,0},{6,0,0,0,0,0,0,0,3},{0,0,0,0,0,9,2,5,1},{0,0,6,0,0,7,0,0,2},{0,0,0,6,8,0,0,0,0},{1,0,0,2,0,0,4,6,0}};	
		
		//Zeros Data - Use this data set of zeros to allow User to enter initial values
		//int[][] data = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};	

		//setup box borders
		BorderWidths bWidth = new BorderWidths(3);
		BorderStroke bStrokeBlue = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bWidth);
		BorderStroke bStrokeRed = new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bWidth);
		BorderStroke bStrokeGreen = new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, bWidth);	

		//initialize matrix
		for (int r=0;r<9;r++){
			for (int c=0;c<9;c++){
				sudoMatrix[r][c] = data[r][c];
				sudoMatrixInt[r][c] = (Integer) data[r][c];
				displayMatrix[r][c] = new TextField();
				displayMatrix[r][c].setText(sudoMatrixInt[r][c].toString());
				displayMatrix[r][c].setFont(Font.font("Serif", FontWeight.BOLD, 22));
				int a = whichBox(r,c);
				switch (a){
				case 1: displayMatrix[r][c].setBorder(new Border(bStrokeBlue));
				break;
				case 2: displayMatrix[r][c].setBorder(new Border(bStrokeRed));
				break;
				case 3: displayMatrix[r][c].setBorder(new Border(bStrokeGreen));
				break;
				case 4: displayMatrix[r][c].setBorder(new Border(bStrokeGreen));
				break;
				case 5: displayMatrix[r][c].setBorder(new Border(bStrokeBlue));
				break;
				case 6: displayMatrix[r][c].setBorder(new Border(bStrokeRed));
				break;
				case 7: displayMatrix[r][c].setBorder(new Border(bStrokeRed));
				break;
				case 8: displayMatrix[r][c].setBorder(new Border(bStrokeGreen));
				break;
				case 9: displayMatrix[r][c].setBorder(new Border(bStrokeBlue));
				break;
				}

				displayMatrix[r][c].setAlignment(Pos.CENTER);			
			}
		}//end initialize matrix
		
		statusText.setText("Enter Inital Values");
		
		//setup start button
		Button btn = new Button("Start");
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setFont(Font.font("Serif", FontWeight.BOLD, 22));

		//setup iterate button
		Button btn2 = new Button("Iterate");
		btn2.setMaxWidth(Double.MAX_VALUE);
		btn2.setFont(Font.font("Serif", FontWeight.BOLD, 22));
		
		//setup status text
		statusText.setFont(Font.font("Serif", FontWeight.BOLD, 14));
		
		//setup pane
		GridPane entryRoot = new GridPane();
		//entryRoot.setGridLinesVisible(true);
		entryRoot.setAlignment(Pos.CENTER);
		entryRoot.setVgap(10);
		entryRoot.setHgap(10);
		entryRoot.add(btn, 1, 10,2,1);	
		entryRoot.add(statusText, 3, 10,3,1);

		//place boxes into gridpane 
		for (int i=0;i<9;i++){
			int k = 8-i;
			for (int j=0;j<9;j++){
				entryRoot.add(displayMatrix[i][j], j, k);
			}
		}

		System.out.println("init'ed text fields");

		//create scene and assign to stage
		primaryStage.setScene(new Scene(entryRoot,800,600));
		primaryStage.show();

		//Button Action Event - What to do when "solve" button is pressed
		btn.setOnAction(new EventHandler<ActionEvent>(){

			@Override	public void handle(ActionEvent e){
				System.out.println("button pressed");
				for (int r=0;r<9;r++){
					for (int c=0;c<9;c++){
						sudoMatrix[r][c]=Integer.parseInt(displayMatrix[r][c].getText());
					}
				}

				System.out.println("info pulled from text fields and added to matrix");
				GridPane root = new GridPane();

				root.setAlignment(Pos.CENTER);
				root.setVgap(10);
				root.setHgap(10);
				for (int i=0;i<9;i++){
					int k = 8-i;
					for (int j=0;j<9;j++){

						root.add(displayMatrix[i][j], j, k);
					}
				}
				root.add(statusText, 3, 10,4,1);
				root.add(btn2, 1, 10,2,1);

				primaryStage.setScene(new Scene(root,800,600));
				primaryStage.show();
				System.out.println("New scene set.");
			}
		});
		
		
		//Button Action Event - What to do when "iterate" button is pressed
		btn2.setOnAction(new EventHandler<ActionEvent>(){

			@Override	public void handle(ActionEvent e){
				System.out.println("Iterate button pressed");
				
				//Run Solver thru One Iteration
				solver();
				iter++;
				
				//update display
				updateDisplay();
				
				//output matrix to console
				printMatrix(sudoMatrix);
				
				
				//check if puzzle is solved
				if(checkMatrix(sudoMatrix,iter)) {
					System.out.println("Iterations: "+iter);
					System.out.println("Puzzle solved!");
					
					GridPane root = new GridPane();
	
					root.setAlignment(Pos.CENTER);
					root.setVgap(10);
					root.setHgap(10);
					for (int i=0;i<9;i++){
						int k = 8-i;
						for (int j=0;j<9;j++){
	
							root.add(displayMatrix[i][j], j, k);
						}
					}
					root.add(statusText, 3, 10,4,1);
	
					primaryStage.setScene(new Scene(root,800,600));
					primaryStage.show();
					System.out.println("Final scene set.");
				}
			}
		});		
				
	}//end start
		

	//Solving Method
	public void solver(){


		if (checkZeros(sudoMatrix)==1){

			//update display
			updateDisplay();
			
			for (int r=0;r<9;r++){
				colCycleLoop:
					for (int c=0;c<9;c++){
						if(sudoMatrix[r][c]==0){
							ArrayList<Integer>z=null;

							z=willFit(r,c,sudoMatrix);

							//Check Only Possible Value for Cell
							if (z.size()==1){
								sudoMatrix[r][c]=z.get(0);
								System.out.println("Solution found - Only Value Possible: " + z.get(0) + " at coordinates: " +r+", "+c);
								continue;
							}

							//Check Number has to go in this Cell due to 2 rows and 2 col contain
							else if(z.size()>1){
								ArrayList<Integer> inRowX = new ArrayList<Integer>();
								ArrayList<Integer> inRowY = new ArrayList<Integer>();
								ArrayList<Integer> inColX = new ArrayList<Integer>();
								ArrayList<Integer> inColY = new ArrayList<Integer>();

								int rbx=0;
								int rby=0;
								int cbx=0;
								int cby=0;

								int rb=rowBox(r);
								int cb=colBox(c);
								if (rb==1){
									rbx=r+1;
									rby=r+2;
								}
								if (rb==2){
									rbx=r-1;
									rby=r+1;
								}
								if (rb==3){
									rbx=r-2;
									rby=r-1;
								}
								if (cb==1){
									cbx=c+1;
									cby=c+2;
								}
								if (cb==2){
									cbx=c-1;
									cby=c+1;
								}
								if (cb==3){
									cbx=c-2;
									cby=c-1;
								}

								//check for numbers in row X
								for(int k1=0;k1<9;k1++){
									if (sudoMatrix[rbx][k1]>0){
										inRowX.add(sudoMatrix[rbx][k1]);
									}
								}

								//check for numbers in row Y
								for(int k2=0;k2<9;k2++){
									if (sudoMatrix[rby][k2]>0){
										inRowY.add(sudoMatrix[rby][k2]);
									}
								}

								//check for numbers in column X
								for(int j1=0;j1<9;j1++){
									if (sudoMatrix[j1][cbx]>0){
										inColX.add(sudoMatrix[j1][cbx]);
									}
								}

								//check for numbers in column Y
								for(int j2=0;j2<9;j2++){
									if (sudoMatrix[j2][cby]>0){
										inColY.add(sudoMatrix[j2][cby]);
									}
								}

								boolean rowxfull=false;
								boolean rowyfull=false;
								boolean colxfull=false;
								boolean colyfull=false;

								//check for full row and column within box
								if(sudoMatrix[rbx][c]>0 && sudoMatrix[rbx][cbx]>0 && sudoMatrix[rbx][cby]>0){rowxfull=true;}
								if(sudoMatrix[rby][c]>0 && sudoMatrix[rby][cbx]>0 && sudoMatrix[rby][cby]>0){rowyfull=true;}
								if(sudoMatrix[r][cbx]>0 && sudoMatrix[rbx][cbx]>0 && sudoMatrix[rby][cbx]>0){colxfull=true;}
								if(sudoMatrix[r][cby]>0 && sudoMatrix[rbx][cby]>0 && sudoMatrix[rby][cby]>0){colyfull=true;}


								for (int i=0;i<z.size();i++){
									int valueCheck=z.get(i);

									if(inRowX.contains(valueCheck) && inRowY.contains(valueCheck) && inColX.contains(valueCheck) && inColY.contains(valueCheck)){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 10");
										continue colCycleLoop;
									}
									if(rowxfull==true&&rowyfull==true&&colxfull==true&&colyfull==true){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 10.1");
										continue colCycleLoop;
									}


									if(sudoMatrix[rbx][c]>0 && sudoMatrix[rby][c]>0 && inColX.contains(valueCheck) && inColY.contains(valueCheck)){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 11");
										continue colCycleLoop;
									}
									if(sudoMatrix[r][cbx]>0 && sudoMatrix[r][cby]>0 && inRowX.contains(valueCheck) && inRowY.contains(valueCheck)){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 12");
										continue colCycleLoop;
									}

									if(inColX.contains(valueCheck) && sudoMatrix[r][cby]>0 && inRowX.contains(valueCheck) && inRowY.contains(valueCheck)){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 13");
										continue colCycleLoop;
									}

									if(inColY.contains(valueCheck) && sudoMatrix[r][cbx]>0 && inRowX.contains(valueCheck) && inRowY.contains(valueCheck)){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 14");
										continue colCycleLoop;
									}

									if(inColX.contains(valueCheck) && inColY.contains(valueCheck) && inRowX.contains(valueCheck) && sudoMatrix[rby][c]>0){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 15");
										continue colCycleLoop;
									}

									if(inColX.contains(valueCheck) && inColY.contains(valueCheck) && inRowY.contains(valueCheck) && sudoMatrix[rbx][c]>0){
										sudoMatrix[r][c]=valueCheck;
										System.out.println(valueCheck +" assigned to "+r+", "+c+" Method 16");
										continue colCycleLoop;
									}

								}


							}//end else if


							//check if this is the only cell the value can be placed into
							int t = onlyPossibleCell(r,c,z,sudoMatrix);	
							if (t>0){sudoMatrix[r][c]=t;
							System.out.println(t +" assigned to "+r+", "+c+" Method Only Possible Cell");
							t=0;
							continue colCycleLoop;
							}
						}

					}

			}//end for	
		}//end for


	}//end start method







	//Determine which row and column within the 3x3 box
	public static int rowBox(int r){
		int rbox=0;

		if(r==0 || r==3 || r==6){
			rbox = 1;
		}
		if(r==1 || r==4 || r==7){
			rbox = 2;
		}
		if(r==2 || r==5 || r==8){
			rbox = 3;
		}	
		return rbox;
	}


	public static int colBox(int c){
		int cbox=0;
		if(c==0 || c==3 || c==6){
			cbox = 1;
		}
		if(c==1 || c==4 || c==7){
			cbox = 2;
		}
		if(c==2 || c==5 || c==8){
			cbox = 3;
		}
		return cbox;
	}


	//Determine other boxes
	public static int rowBoxA(int r){
		int rboxA=0;

		if(r==0 || r==1 || r==2){
			rboxA = 2;
		}
		if(r==3 || r==4 || r==5){
			rboxA = 1;
		}
		if(r==6 || r==7 || r==8){
			rboxA = 1;
		}	
		return rboxA;
	}


	public static int colBoxA(int c){
		int cboxA=0;
		if(c==0 || c==1 || c==2){
			cboxA = 2;
		}
		if(c==3 || c==4 || c==5){
			cboxA = 1;
		}
		if(c==6 || c==7 || c==8){
			cboxA = 1;
		}
		return cboxA;
	}						

	//Determine which row and column within the 3x3 box
	public static int rowBoxB(int r){
		int rboxB=0;

		if(r==0 || r==1 || r==2){
			rboxB = 3;
		}
		if(r==3 || r==4 || r==5){
			rboxB = 3;
		}
		if(r==6 || r==7 || r==8){
			rboxB = 2;
		}	
		return rboxB;
	}


	public static int colBoxB(int c){
		int cboxB=0;
		if(c==0 || c==1 || c==2){
			cboxB = 3;
		}
		if(c==3 || c==4 || c==5){
			cboxB = 3;
		}
		if(c==6 || c==7 || c==8){
			cboxB = 2;
		}
		return cboxB;
	}						


	//Method to check what numbers can be placed in a cell
	public static ArrayList<Integer> willFit(int r,int c, int[][] matrix){
		ArrayList<Integer> canFit = new ArrayList<Integer>();
		ArrayList<Integer> inMyRow = new ArrayList<Integer>();
		ArrayList<Integer> inMyCol = new ArrayList<Integer>();
		ArrayList<Integer> box = boxFill(r,c,matrix);

		//check for numbers in row
		for(int k=0;k<9;k++){
			if (matrix[r][k]>0){
				inMyRow.add(matrix[r][k]);
			}
		}

		//check for numbers in column
		for(int j=0;j<9;j++){
			if (matrix[j][c]>0){
				inMyCol.add(matrix[j][c]);
			}
		}

		//check for no matches		
		for (int i=1;i<10;i++){
			if(!(inMyRow.contains(i))){
				if(!(inMyCol.contains(i))){
					if(!(box.contains(i))){
						canFit.add(i);
						//System.out.println("added "+i+" to "+r+", "+c);
					}
				}
			}	
		}

		if (canFit.size()==1){
			//System.out.println("got one "+canFit.get(0)+" at "+r+", "+c);			
		}
		return canFit;
	}


	//Method to place non-Zeros for each box into an array
	public static ArrayList<Integer> boxFill(int r,int c,int[][] matrix){
		ArrayList<Integer> box = new ArrayList<Integer>();
		if(r==1||r==2){r=0;}
		if(r==4||r==5){r=3;}
		if(r==7||r==8){r=6;}
		if(c==1||c==2){c=0;}
		if(c==4||c==5){c=3;}
		if(c==7||c==8){c=6;}		

		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if(matrix[r+i][c+j]>0){
					box.add(matrix[r+i][c+j]);
				}
			}
		}
		return box;
	}


	//Method to check for zeros
	public static int checkZeros(int[][] matrix){
		for(int r=0;r<9;r++){
			for(int c=0;c<9;c++){
				if(matrix[r][c]==0){
					return 1;
				}
			}
		}
		return 0;
	}



	//Method to print box non-Zeros
	public static void printBox(ArrayList<Integer> box){
		System.out.println("Print Box");
		System.out.println(box);
		return;
	}


	//Method to print matrix
	public static void printMatrix(int[][] matrix){
		for (int r = 8;r>-1;r--){
			for (int c = 0;c<9;c++){
				System.out.print(matrix[r][c] + " ");
			}
			System.out.println();
			//System.out.println("*** Row "+ r);
		}
	}


	//Method to determine which box a cell is in
	public static int whichBox(int r,int c){
		if(r<3){
			if(c<3){
				return 1;
			}
			if(c<6){
				return 2;
			}
			return 3;
		}
		if(r<6){
			if(c<3){
				return 4;
			}
			if(c<6){
				return 5;
			}
			return 6;
		}

		if(c<3){
			return 7;
		}
		if(c<6){
			return 8;
		}
		return 9;
	}

	//check if this is the only cell a number can be placed into
	public static int onlyPossibleCell(int r, int c, ArrayList<Integer> possibleValues, int[][] matrix){

		//loop thru possible values for cell
		for (int k=0;k<possibleValues.size();k++){
			ArrayList<Integer> rowBlock = new ArrayList<Integer>();
			ArrayList<Integer> colBlock = new ArrayList<Integer>();
			boolean skip=false;
			int cv=possibleValues.get(k);
			for (int i=0;i<9;i++){
				if(i != r){
					//does the cell already have a value?
					if(matrix[i][c]>0){rowBlock.add(i);
					continue;
					}
					if(rowHavecheckValue(i,cv,matrix)==1){rowBlock.add(i);
					skip=true;}
					if(skip==false){
						if(boxHavecheckValue(i,c,cv,matrix)==1){rowBlock.add(i);
						}
					}
					skip=false;
				}
			}//end check this column for blocked cells for loop

			//check row for blocked out cells
			for (int j=0;j<9;j++){
				if(j != c){
					if(matrix[r][j]>0){colBlock.add(j);
					continue;
					}
					if(colHavecheckValue(j,cv,matrix)==1){colBlock.add(j);
					skip=true;
					}
					if(skip==false){
						if(boxHavecheckValue(r,j,cv,matrix)==1){colBlock.add(j);
						}
					}
					skip=false;
				}
			}//end check this row for blocked cells for loop



			if(rowBlock.size()==8) return cv;
			if(colBlock.size()==8) return cv;

		}//end check value for loop
		return 0;
	}//end method


	//Check Row for Value
	public static int rowHavecheckValue(int r, int cv, int[][] matrix){
		for (int i=0;i<9;i++){
			if(matrix[r][i]==cv){
				return 1;
			}
		}
		return 0;
	}//end method

	//Check Col for Value
	public static int colHavecheckValue(int c, int cv, int[][] matrix){
		for (int i=0;i<9;i++){
			if(matrix[i][c]==cv){
				return 1;
			}
		}
		return 0;
	}//end method

	//Check Box for Value
	public static int boxHavecheckValue(int r, int c, int cv, int[][] matrix){
		ArrayList<Integer> box = boxFill(r,c, matrix);
		for(int i=0;i<box.size();i++){
			if(box.get(i)==cv) return 1;
		}
		return 0;
	}//end method


	//Sudo Checker
	public boolean checkMatrix(int[][] matrix,int iter1){
		int sum = 0;
		boolean matrixGood = true;
		
		if (iter1>20) {
			System.out.println("More than 20 iterations.  Algorithm cannot solve!!");
			statusText.setText("More than 20 iterations.  Algorithm cannot solve!!!");
			return false;
		}
		
		for(int i=0;i<9;i++){
			sum = 0;
			for(int j=0;j<9;j++){
				sum = sum + matrix[i][j];
			}
			if(sum!=45)matrixGood=false;
		}

		for(int j=0;j<9;j++){
			sum = 0;
			for(int i=0;i<9;i++){
				sum = sum + matrix[i][j];
			}
			if(sum!=45)matrixGood=false;
		}

		if (matrixGood == true){
			System.out.println("Solution Checked.  Puzzle Solved in "+ iter1 + " iterations!");
			statusText.setText("Solution Checked. Puzzle Solved in "+ iter1 + " iterations!");
			return true;
		}
		else{System.out.println("Solution Checked.  Puzzle NOT Solved!!");
			statusText.setText("Solution Checked. Puzzle NOT Solved!");
			return false;
		}
	}//end method

	public void updateDisplay(){
		for (int r=0;r<9;r++){
			for (int c=0;c<9;c++){
				sudoMatrixInt[r][c] = (Integer) sudoMatrix[r][c];
				displayMatrix[r][c].setText(sudoMatrixInt[r][c].toString());
			}
		}
		
	}//end method

}//end class