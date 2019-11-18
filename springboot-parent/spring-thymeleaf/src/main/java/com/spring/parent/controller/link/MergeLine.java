package com.spring.parent.controller.link;
/**
 * 合并链表
 * @author Administrator
 *
 */
public class MergeLine {
	public static void main(String[] args) {
		
	}
	//输入：1->2->4, 1->3->4
	//输出：1->1->2->3->4->4
	public static ListNode metgeTwoNodeList(ListNode node1,ListNode node2) {
		if(node1==null) {
			return node2;
		}
		if(node2==null) {
			return node1;
		}
		if(node1.val<=node2.val) {
			node1.next=metgeTwoNodeList(node1.next,node2);
			return node1;
		}else {
			node2.next=metgeTwoNodeList(node1,node2.next);
			return node2;
		}
	}
}
