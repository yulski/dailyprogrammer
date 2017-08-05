import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private String[][] input;
    private List<String> pattern;
	private int patternIndex;

    public Solver(String[][] input, List<String> pattern) {
        this.input = input;
        this.pattern = pattern;
		this.patternIndex = 0;
    }

    public List<Integer> solve() {
        for(int i=0; i<input[0].length; i++) {
			if(input[input.length - 1][i].equals(pattern.get(0))) {
				patternIndex = 0;
				List<Integer> solution = traverseMaze(getAbsIndex(input.length - 1, i));
				if(solution != null && isSolution(solution)) {
					return solution;
				}
			}
        }
        return null;
    }

    private List<Integer> traverseMaze(int node) {
		advancePattern();
		int patternIndexAtStart = patternIndex;
		List<Integer> path = new ArrayList<>();
		path.add(node);
        int[] indexes = getRowAndColIndexes(node);
        if(indexes[0] == 0) {
            return path;
        }
		int[] neighbors = findNeighbors(node);
		for(int neighbor : neighbors) {
			if(!isLoop(path, neighbor)) {
				List<Integer> neighborPath = traverseMaze(neighbor);
				if(neighborPath == null) {
					patternIndex = patternIndexAtStart;
					
				} else if(isSolution(neighborPath)) {
					path.addAll(neighborPath);
					return path;
				}
			}
		}
		return null;
    }
	
	private boolean isSolution(List<Integer> path) {
		return getRowAndColIndexes(path.get(path.size() - 1))[0] == 0;
	}
	
	private void advancePattern() {
		int next = patternIndex + 1;
		patternIndex = next % pattern.size();
	}
	
	private int patternBack() {
		int previous = patternIndex - 1;
		return previous % pattern.size();
	}
	
	private boolean isLoop(List<Integer> path, int next) {
		if(path.size() < pattern.size()) {
			return false;
		}
		return path.get(pattern.size() - 1 - patternBack()) == next;
	}
	
	private int[] findNeighbors(int absIndex) {
		int[] indexes = getRowAndColIndexes(absIndex);
		
		int[][] neighbors = {
			new int[]{indexes[0] - 1, indexes[1]},
			new int[]{indexes[0] + 1, indexes[1]},
			new int[]{indexes[0], indexes[1] - 1},
			new int[]{indexes[0], indexes[1] + 1}
		};
		
		int[] filteredNeighbors = Arrays.stream(neighbors)
										.filter(neighborPos -> {
											return isValidPosition(neighborPos[0], neighborPos[1]) && 
												input[neighborPos[0]][neighborPos[1]].equals(pattern.get(patternIndex));
										})
										.mapToInt(neighborPos -> getAbsIndex(neighborPos[0], neighborPos[1]))
										.toArray();
		return filteredNeighbors;
	}

    private int getAbsIndex(int row, int col) {
		if(!isValidPosition(row, col)) {
			return -1;
		}
        return col + (row * input[0].length);
    }

    private int[] getRowAndColIndexes(int absIndex) {
        int rowIndex = (int) Math.floor(absIndex / input[0].length);
        int colIndex = absIndex % input[0].length;
        return new int[] { rowIndex, colIndex };
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < input.length && col >= 0 && col < input[0].length;
    }

}
