package CollectionLearning;

import java.util.LinkedList;

/**
 * LinkedList 是双向链式结构
 * 使用LinkedList 实现 Stack 和 Queue
 *
 * Stack 后进先出
 * Queue 先进先出
 *
 * 动态数组、栈、队列、链表 等结构用List，具体区分
 * 快速插入，删除元素，应该使用LinkedList
 * 快速随机访问元素，应该使用ArrayList
 *
 * 对于单线程环境，使用非同步类 List (ArrayList/LinkedList)
 * 对于多线程环境，使用同步类 Vector
 *
 */
public class JavaLinkedList {

    static class MyStack{

        private final int size = 10;
        private LinkedList<Object> linkedList = new LinkedList<>();
        public void push(Object obj){
            linkedList.add(obj);
        }
        public Object pop(){
            Object obj = linkedList.getLast();
            linkedList.removeLast();
            return obj;
        }
        public boolean empty(){
            return linkedList.isEmpty();
        }
        public Object top(){
            return linkedList.getLast();
        }
        public int size(){
            return linkedList.size();
        }
    }

    static class MyQueue{
        private LinkedList<Object> linkedList = new LinkedList<>();
        public void push(Object obj){
            linkedList.add(obj);
        }
        public Object pop(){
            Object obj = linkedList.getFirst();
            linkedList.removeFirst();
            return obj;
        }
        public Object front(){
            return  linkedList.getFirst();
        }
        public int size(){
            return linkedList.size();
        }
        public boolean empty(){
            return linkedList.isEmpty();
        }
        public Object back(){
            return linkedList.getLast();
        }
    }
    public static void main(String [] args){

        // stack 类的简单使用
        System.out.println("stack的使用");
        MyStack stack = new MyStack();
        stack.push("Gaoooyh");
        stack.push("Hiawons");
        System.out.println(stack.top() + " " + stack.size() );
        stack.pop();
        stack.push("Lcc's cat");
        while(!stack.empty()){
            System.out.print(stack.pop() + "  ");
        }


        // queue 类的简单使用
        System.out.println("\nqueue的使用");
        MyQueue queue = new MyQueue();
        queue.push("Gaoooyh");
        queue.push("Hiawons");
        System.out.println(queue.front() + " " + queue.back() + " " +queue.size());
        queue.push("Lcc's cat");
        while (!queue.empty()){
            System.out.print(queue.pop() + " ");
        }
    }

}