package com.museti.ld26.input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.museti.ld26.Config;
import com.museti.ld26.Game;


public class Mouse implements MouseListener, MouseMotionListener {

	public int clickX = 0, clickY = 0;

	public Mouse() {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if ((e.getButton() == 1) && (getFirstBox().contains(e.getX(), e.getY()))) {
			System.out.println("Past Help One");
			Config.loc = 1;
		}
		System.out.println("Clicked!");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public Rectangle getFirstBox() {
		return new Rectangle(0, 0, 900, 540);
	}
}
