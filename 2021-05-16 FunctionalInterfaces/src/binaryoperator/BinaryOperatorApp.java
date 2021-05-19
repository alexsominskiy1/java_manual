package binaryoperator;

import java.util.TreeSet;
import java.util.function.BinaryOperator;

public class BinaryOperatorApp {
	
	static BinaryOperator<TreeSet<Integer>> joinTrees = (tree1, tree2) -> {
		TreeSet<Integer> result = new TreeSet();
		result.addAll(tree1);
		result.addAll(tree2);
		return result;
	};

	public static void main(String[] args) {
		
		TreeSet<Integer> tree1 = new TreeSet<>();
		TreeSet<Integer> tree2 = new TreeSet<>();
		
		tree1.add(1);
		tree1.add(3);
		tree1.add(5);
		tree1.add(4);
		
		tree1.add(2);
		tree1.add(4);
		tree1.add(6);
		
		TreeSet<Integer> joined = joinTrees.apply(tree1, tree2);
		System.out.println(joined);
	}

}
