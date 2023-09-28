package it.unifi;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AlberoPFFS <T>{
    private NodoPFFS<T> root;
    private int elements;
    private int leaves;
    private int height;

    public NodoPFFS<T> insertRoot(T info){
        setRoot(new NodoPFFS<T>(info));
        elements++;
        leaves++;
        return root;
    }

    public void swapRoots(NodoPFFS<T> newRoot) {
        newRoot.setFirstSon(getRoot());
        getRoot().setFather(newRoot);

        elements++;
        setRoot(newRoot);
    }

    public NodoPFFS<T> insert(T info, NodoPFFS<T> father) {

        if(father.getFirstSon()!=null)
            leaves++;
        elements++;

        if(father.getFirstSon()==null) {
            father.setFirstSon(new NodoPFFS<T>(father, info));
            setHeightIncrement(father.getFirstSon());
            return father.getFirstSon();

        }else {
            NodoPFFS<T> temp = father.getFirstSon();
            while(temp.getBrother()!=null) {
                temp = temp.getBrother();
            }

            temp.setBrother(new NodoPFFS<T>(temp, info));
            setHeightIncrement(temp.getBrother());
            return temp.getBrother();
        }

    }

    private void setHeightIncrement(NodoPFFS<T> newNode) {
        if(getLevel(newNode) > getHeight()) {
            setHeight(getHeight()+1);
        }
    }

    public int getSonsNumber(NodoPFFS<T> node) {
        if(node.getFirstSon()==null) return 0;

        int count = 1;
        node = node.getFirstSon();
        while(node.getBrother()!=null) {
            node = node.getBrother();
            count++;
        }
        return count;
    }

    public List<T> getSonsinfo(NodoPFFS<T> node){
        List<T> nodeInfoList = new LinkedList<T>();

        if(node.getFirstSon()==null) return null;

        nodeInfoList.add(node.getFirstSon().getInfo());
        node = node.getFirstSon();
        while(node.getBrother()!=null) {
            node = node.getBrother();
            nodeInfoList.add(node.getInfo());
        }

        return nodeInfoList;
    }

    public String printSonsInfo(NodoPFFS<T> node){
        return "Sons of node " + node.getInfo() + ": " + printList(getSonsinfo(node));
    }

    public NodoPFFS<T> getFather(NodoPFFS<T> node) {
        if(node.getFather()==null) return null;
        return getFatherRec(node, node.getFather());
    }

    private NodoPFFS<T> getFatherRec(NodoPFFS<T> node, NodoPFFS<T> binaryFather){
        if(binaryFather.getFirstSon()!=null)
            if(binaryFather.getFirstSon()==node)
                return binaryFather;

        return getFatherRec(binaryFather, binaryFather.getFather());
    }

    public int getLevel(NodoPFFS<T> node) {
        if(node.getFather()==null) return 0;
        return getLevelRec(node, node.getFather(), 0);

    }

    private int getLevelRec(NodoPFFS node, NodoPFFS<T> binaryFather, int level) {
        if(binaryFather!=null){
            if(binaryFather.getFirstSon()!=null){
                if(binaryFather.getFirstSon()==node){
                    return getLevelRec(binaryFather, binaryFather.getFather(), level+1);
                }
            }
            return getLevelRec(binaryFather, binaryFather.getFather(), level);
        }
        return level;
    }

    public String getMaryTree() {
        StringBuilder list = new StringBuilder();

        if(getRoot()==null) return "";
        return getMaryTreeRec(root, list);
    }

    private String getMaryTreeRec(NodoPFFS<T> currentNode, StringBuilder list) {
        list.append(currentNode.getInfo().toString());
        if(currentNode.getFirstSon()!=null) {
            list.append('[');
            getMaryTreeRec(currentNode.getFirstSon(), list);
            list.append(']');
        }

        if(currentNode.getBrother()!=null){
            list.append(',');
            getMaryTreeRec(currentNode.getBrother(), list);
        }

        return (list.toString());
    }

    public List<T> dfs(){
        return dfsRec(getRoot(), new LinkedList<T>());
    }

    private List<T> dfsRec(NodoPFFS<T> currentNode, List<T> list){
        list.add(currentNode.getInfo());
        if(currentNode.getFirstSon()!=null) {
            dfsRec(currentNode.getFirstSon(), list);
        }

        if(currentNode.getBrother()!=null){
            dfsRec(currentNode.getBrother(), list);
        }

        return list;
    }

    public List<T> bfs(){
        if(getRoot()==null) return null;

        List<T> nodeList = new LinkedList<T>();
        Queue<NodoPFFS<T>> queue = new LinkedList<NodoPFFS<T>>();

        queue.add(getRoot());

        while (!queue.isEmpty()){
            NodoPFFS<T> currentNode = queue.remove();
            nodeList.add(currentNode.getInfo());

            if(currentNode.getFirstSon()!=null){
                currentNode = currentNode.getFirstSon();

                while (currentNode!=null){
                    queue.add(currentNode);
                    currentNode = currentNode.getBrother();
                }

            }
        }
        return nodeList;
    }

    //GETTERS AND SETTERS
    public NodoPFFS<T> getRoot() {
        return root;
    }
    public int getElements() {
        return elements;
    }
    public int getHeight() {
        return height;
    }
    public void setRoot(NodoPFFS<T> root) {
        this.root = root;
    }
    public void setElements(int elements) {
        this.elements = elements;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getLeaves() {
        return leaves;
    }
    public void setLeaves(int leaves) {
        this.leaves = leaves;
    }

    //TOSTRING
    @Override
    public String toString(){
        StringBuilder info = new StringBuilder();

        //STATUS INFORMATIONS
        info.append("Number of Elements: ").append(getElements()).append("\n");
        info.append("Number of Leaves: ").append(getLeaves()).append("\n");
        info.append("Height: ").append(getHeight()).append("\n");

        //TRAVERSAL
        info.append("DFS: ").append(printList(dfs())).append("\n");
        info.append("BFS: ").append(printList(bfs())).append("\n");

        //M-ARY TREE REPRESENTATION
        info.append("Relative m-ary tree: ").append(getMaryTree());

        return info.toString();
    }

    private String printList(List<T> genericList) {
        StringBuilder output = new StringBuilder();

        for (T info :
                genericList) {
            output.append(info).append(" | ");
        }

        return output.toString();
    }

}