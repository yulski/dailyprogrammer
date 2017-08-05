import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<String> pattern = new ArrayList<>(Arrays.asList("R", "O", "Y", "P", "O"));
        String originalInput = "R R B R R R B P Y G P B B B G P B P P R\nB G Y P R P Y Y O R Y P P Y Y R R R P P\n" + 
            "B P G R O P Y G R Y Y G P O R Y P B O O\nR B B O R P Y O O Y R P B R G R B G P G\nR P Y G G G P Y P Y O G B O R Y P B Y O\n" + 
            "O R B G B Y B P G R P Y R O G Y G Y R P\nB G O O O G B B R O Y Y Y Y P B Y Y G G\nP P G B O P Y G B R O G B G R O Y R B R\n" + 
            "Y Y P P R B Y B P O O G P Y R P P Y R Y\nP O O B B B G O Y G O P B G Y R R Y R B\nP P Y R B O O R O R Y B G B G O O P B Y\n" + 
            "B B R G Y G P Y G P R R P Y G O O Y R R\nO G R Y B P Y O P B R Y B G P G O O B P\nR Y G P G G O R Y O O G R G P P Y P B G\n" + 
            "P Y P R O O R O Y R P O R Y P Y B B Y R\nO Y P G R P R G P O B B R B O B Y Y B P\nB Y Y P O Y O Y O R B R G G Y G R G Y G\n" + 
            "Y B Y Y G B R R O B O P P O B O R R R P\nP O O O P Y G G Y P O G P O B G P R P B\nR B B R R R R B B B Y O B G P G G O O Y";
        String[][] processedInput = processInput(originalInput);
        Solver solver = new Solver(processedInput, pattern);
        List<Integer> solution = solver.solve();
        if(solution == null) {
            System.out.println("The maze cannot be solved.");
        } else {
            System.out.println("The solution is: \n" + solution);
			outputSolution(processedInput, solution);
        }
    }

    private static String[][] processInput(String originalInput) {
        String[] rows = originalInput.split("\n");
        String[][] processed = new String[rows.length][];
        for(int i=0; i<rows.length; i++) {
            processed[i] = Arrays.stream(rows[i].split(" "))
                                    .map(s -> s.trim())
                                    .toArray(String[]::new);
        }
        return processed;
    }
	
	private static void outputSolution(String[][] input, List<Integer> solution) {
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[i].length; j++) {
				int absIndex = j + (i * input[i].length);
				if(solution.contains(absIndex)) {
					System.out.print(" " + input[i][j] + " ");
				} else {
					System.out.print(" - ");
				}
			}
			System.out.print("\n");
		}
	}

}
