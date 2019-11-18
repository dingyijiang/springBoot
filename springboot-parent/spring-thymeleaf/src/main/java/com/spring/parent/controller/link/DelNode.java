package com.spring.parent.controller.link;
/**
 * 删除倒数第n个节点
 * @author Administrator
 *
 */
public class DelNode {
	public static void main(String[] args) {
		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(12);
		ListNode node3=new ListNode(3);
		ListNode node4=new ListNode(5);
		ListNode node5=new ListNode(16);
		ListNode node6=new ListNode(20);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		node5.next=node6;
		
		System.out.println(printNode(node1));
		System.out.println(printNode(delNode(node1,3)));
		
	}
	
	 public static String printNode(ListNode node) {
	    	String str="";
	    	while(node!=null) {
	    		str+=node.val+"-->";
	    		node=node.next;
	    	}
	    	return str;
	    }
	
	public static ListNode delNode(ListNode head, int n) {
		ListNode dummy=new ListNode(0);
		dummy.next=head;
		ListNode first=dummy;
		ListNode second=dummy;
		for(int i=1;i<=n+1;i++) {//n=3 那么second 落后 first 3个next
			first=first.next;
		}
		while(first!=null) {
			first=first.next;
			second=second.next;
		}
		
		second.next=second.next.next;
		return dummy;
	}
}
