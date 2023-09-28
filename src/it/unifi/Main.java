package it.unifi;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AlberoPFFS<String> tree = new AlberoPFFS<String>();

        tree.insertRoot("A");

		NodoPFFS<String> b = tree.insert("B", tree.getRoot());

		NodoPFFS<String> c = tree.insert("C", tree.getRoot());
		NodoPFFS<String> d = tree.insert("D", tree.getRoot());

		NodoPFFS<String> e = tree.insert("E", b);
		NodoPFFS<String> f = tree.insert("F", b);

		NodoPFFS<String> g = tree.insert("G", c);

		NodoPFFS<String> h = tree.insert("H", g);
		NodoPFFS<String> i = tree.insert("I", g);
		NodoPFFS<String> l = tree.insert("L", g);
		NodoPFFS<String> m = tree.insert("M", g);

		List<String> list = tree.bfs();

		System.out.println(tree.printSonsInfo(b));

		
		System.out.println(tree);



    }

}
