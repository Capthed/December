package com.capthed.abyss.input;

import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickName;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import com.capthed.util.Debug;

public abstract class Controller {

	private static String contName;
	private static ByteBuffer buff;
	private static FloatBuffer buff2;
	private static int[] buttons;
	private static float[] axis;
	private static boolean has = true;
	
	public static void init() {
		try {
			contName = glfwGetJoystickName(GLFW_JOYSTICK_1);
			
			buff = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
			buff2 = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
			
			buttons = new int[buff.limit()];
			axis = new float[buff2.limit()];
			Debug.print(contName);
			has = true;
		} catch (Exception e) {
			Debug.err("No joystick");
			has = false;
		}
	}
	
	public static void update() {
		if (!has) return;
		int i = 0;
		buff = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
		while(buff.hasRemaining()) {
			int b = buff.get();
			if ((buttons[i] == 1 || buttons[i] == 2)&& b == 1) {
				buttons[i] = 2;
			}
			else
				buttons[i] = b;
			i++;
		}
		
		int i2 = 0;
		buff2 = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
		while(buff2.hasRemaining()) {
			axis[i2++] = buff2.get();
		}
	}
	
	/** @return True if the button is held down. */
	public static boolean isButtonDown(int b) {
		if (!has) return false;
		return buttons[b] == 0 ? false : true;
	}
	
	/** 
	 * @return True if the button is pressed. Returns true just the frame that the button was clicked and false until
	 * it is released and clicked again.
	 */
	public static boolean isButtonPressed(int b) {
		if (!has) return false;
		return buttons[b] == 1 ? true : false;
	}
	
	/** @return The value of the axis requested (between -1 and 1)*/
	public static float getAxis(int id) {
		if (!has) return 0;
		return axis[id];
	}
	
	public static String getContName() { return contName; }

	/** @return True if a controller is connected, false otherwise. */
	public static boolean isHas() {
		return has;
	}

	public static void setHas(boolean has) {
		Controller.has = has;
	}
}
