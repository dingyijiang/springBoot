package com.spring.parent.controller.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class RobotUtil {
	private static Map<String, Integer> keyEventMap = new HashMap<String, Integer>();
	private static Robot robot;
	static {
		keyEventMap.put("0", KeyEvent.VK_0);
		keyEventMap.put("1", KeyEvent.VK_1);
		keyEventMap.put("2", KeyEvent.VK_2);
		keyEventMap.put("3", KeyEvent.VK_3);
		keyEventMap.put("4", KeyEvent.VK_4);
		keyEventMap.put("5", KeyEvent.VK_5);
		keyEventMap.put("6", KeyEvent.VK_6);
		keyEventMap.put("7", KeyEvent.VK_7);
		keyEventMap.put("8", KeyEvent.VK_8);
		keyEventMap.put("9", KeyEvent.VK_9);

		keyEventMap.put("a", KeyEvent.VK_A);
		keyEventMap.put("b", KeyEvent.VK_B);
		keyEventMap.put("c", KeyEvent.VK_C);
		keyEventMap.put("d", KeyEvent.VK_D);
		keyEventMap.put("e", KeyEvent.VK_E);
		keyEventMap.put("f", KeyEvent.VK_F);
		keyEventMap.put("g", KeyEvent.VK_G);
		keyEventMap.put("h", KeyEvent.VK_H);
		keyEventMap.put("i", KeyEvent.VK_I);
		keyEventMap.put("j", KeyEvent.VK_J);
		keyEventMap.put("k", KeyEvent.VK_K);
		keyEventMap.put("l", KeyEvent.VK_L);
		keyEventMap.put("m", KeyEvent.VK_M);
		keyEventMap.put("n", KeyEvent.VK_N);
		keyEventMap.put("o", KeyEvent.VK_O);
		keyEventMap.put("p", KeyEvent.VK_P);
		keyEventMap.put("q", KeyEvent.VK_Q);
		keyEventMap.put("r", KeyEvent.VK_R);
		keyEventMap.put("s", KeyEvent.VK_S);
		keyEventMap.put("t", KeyEvent.VK_T);
		keyEventMap.put("u", KeyEvent.VK_U);
		keyEventMap.put("v", KeyEvent.VK_V);
		keyEventMap.put("w", KeyEvent.VK_W);
		keyEventMap.put("x", KeyEvent.VK_X);
		keyEventMap.put("y", KeyEvent.VK_Y);
		keyEventMap.put("z", KeyEvent.VK_Z);

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}


	public static void keyClick(char key) {
		String keyStr=String.valueOf(key);
		System.out.println(keyStr);
		//判断是不是大写
		if(Character.isUpperCase(key)) {
			robot.keyPress(KeyEvent.VK_SHIFT);
			//大写转小写
			robot.keyPress(keyEventMap.get(keyStr.toLowerCase()));
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(keyEventMap.get(keyStr.toLowerCase()));
			
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_SHIFT);
		}else {
			robot.keyPress(keyEventMap.get(keyStr));
			robot.keyRelease(keyEventMap.get(keyStr));
		}
		
		
	}

	public static void mouseMove(int x, int y) {
		robot.mouseMove(x, y);
	}

	public static void leftMouseClick() {
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public static BufferedImage createScreenCapture(int x, int y, int w, int h) {
		Rectangle rect = new Rectangle(x, y, w, h);
//		BufferedImage image = robot.createScreenCapture(rect);
		return robot.createScreenCapture(rect);
	}

}
