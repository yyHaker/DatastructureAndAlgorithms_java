package com.yyHaker.structure.high;

/**
 * Graph
 *
 * @author Le Yuan
 * @date 2017/1/8
 */
public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexArray[];	//存储顶点的数组
    private int adjMat[][];	//存储是否有边界的矩阵数组, 0表示没有边界，1表示有边界
    private int nVerts;	//顶点个数
    private StackX stack;	//深度搜索时用来临时存储的栈
    private QueueX queue;	//广度搜索时用来临时存储的队列

    public Graph() {
        vertexArray = new Vertex[MAX_VERTS];
         adjMat = new int[MAX_VERTS][MAX_VERTS];
         nVerts = 0;
        for(int i = 0; i < MAX_VERTS; i++) {
            for(int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        stack = new StackX();
        queue = new QueueX();
    }

    public void addVertex(char lab) {
        vertexArray[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[start][end] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexArray[v].label);
    }

    /*
     * 深度优先搜索算法(非递归实现):做四件事
     * 1. 用peek()方法检查栈顶的顶点
     * 2. 试图找到这个顶点还未访问的邻节点
     * 3. 如果没有找到，出栈
     * 4. 如果找到这样的顶点，访问这个顶点，并把它放入栈
     * 深度优先算法类似于从树的跟逐个沿不同路径访问到不同的叶节点
     */
    public void depthFirstSearch() {
        //begin at vertex 0
        vertexArray[0].wasVisited = true; //make it
        displayVertex(0);
        stack.push(0);

        while(!stack.isEmpty()) {
            //get an unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(stack.peek());
            if(v == -1) {	//if no such vertex
                stack.pop();
            } else {	//if it exists
                vertexArray[v].wasVisited = true;
                displayVertex(v);
                stack.push(v);
            }
        }
        //stack is empty, so we're done
        for(int i = 0; i < nVerts; i++) {
            vertexArray[i].wasVisited = false;
        }
    }


    //returns an unvisited vertex adj to v
    public int getAdjUnvisitedVertex(int v) {
        for(int i = 0; i < nVerts; i++) {
            if(adjMat[v][i] == 1 && vertexArray[i].wasVisited == false) {//v和i之间有边，且i没被访问过
                return i;
            }
        }
        return -1;
    }

    /*
     * 广度优先搜索算法：做四件事
     * 1. 用remove()方法检查栈顶的顶点
     * 2. 试图找到这个顶点还未访问的邻节点
     * 3. 如果没有找到，该顶点出列
     * 4. 如果找到这样的顶点，访问这个顶点，并把它放入队列中
     *
     * 深度优先算法中，好像表现的要尽快远离起始点，在广度优先算法中，要尽可能靠近起始点。
     * 它首先访问其实顶点的所有邻节点，然后再访问较远的区域。这种搜索不能用栈，而要用队列来实现。
     * 广度优先算法类似于从树的跟逐层往下访问直到底层
     */
    public void breadthFirstSearch() {
        vertexArray[0].wasVisited = true;
        displayVertex(0);
        queue.insert(0);
        int v2;

        while(!queue.isEmpty()) {
            int v1 = queue.remove();
            //until it has no unvisited neighbors
            while((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexArray[v2].wasVisited = true;
                displayVertex(v2);
                queue.insert(v2);
            }
        }

        for(int i = 0; i < nVerts; i++) {
            vertexArray[i].wasVisited = false;
        }
    }


    /**
     * 最小生成树算法
     * 这里使用的是深度优先策略
     */
    public void minSpanningTree() {
        vertexArray[0].wasVisited = true;
        stack.push(0);
        while(!stack.isEmpty()) {
            int currentVertex = stack.peek();
            int v = getAdjUnvisitedVertex(currentVertex);
            if(v == -1) {
                stack.pop();
            } else {
                vertexArray[v].wasVisited = true;  //设置已经被访问的标记
                stack.push(v);
                displayVertex(currentVertex); //from currentV
                displayVertex(v);   //to v
                System.out.print(" ");
            }
        }
        //stack is empty, so we're done
        for(int j = 0; j < nVerts; j++) {
            vertexArray[j].wasVisited = false;
        }
    }

}

/**
 * 顶点类
 */
class  Vertex{
    public char label;             //lable:eg.'A'
    public boolean wasVisited;
    public Vertex(char lab){
        label=lab;
        wasVisited=false;
    }
}

/**
 * 一个循环队列的简单实现
 * 广度搜索时用来临时存储的队列
 */
class QueueX {
    private final int SIZE = 20;
    private int[] queArray;
    private int front;  //队首
    private int rear;   //队尾

    public QueueX() {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }
      //进队列
    public void insert(int j) {
        if(rear == SIZE-1) {
            rear = -1;
        }
        queArray[++rear] = j;
    }
      //出队列（返回出队列的那个元素）
    public int remove() {
        int temp = queArray[front++];
        if(front == SIZE) {
            front = 0;
        }
        return temp;
    }

    public boolean isEmpty() {
        return (rear+1 == front || front+SIZE-1 == rear);
    }
}

/**
 * 简单栈的实现
 * 深度搜索时用来临时存储的栈
 */
 class StackX {
    private final int SIZE = 20;
    private int[] stack;
    private int top;

    public StackX() {
        stack = new int[SIZE];
        top = -1;
    }

    public void push(int j) {
        stack[++top] = j;
    }

    public int pop() {
        return stack[top--];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}

