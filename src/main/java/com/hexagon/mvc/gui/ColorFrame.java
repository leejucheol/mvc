package com.hexagon.mvc.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.hexagon.mvc.model.ColorManager;

/*
 * web기반으로 제작했던 color 선택 app을 javaSE 의 GUI로 구현해보자
 * */

public class ColorFrame extends JFrame{

	JComboBox<String>box;
	JButton bt;
	JLabel la_msg;
	ColorManager colorManager;
	
	public ColorFrame() {
		box = new JComboBox<String>();
		bt = new JButton("Result");
		la_msg = new JLabel(); 
		colorManager = new ColorManager();
		
		box.addItem("Choose the color you want");
		box.addItem("red");
		box.addItem("blue");
		box.addItem("black");
		box.addItem("white");
		
		// Assemble
		setLayout(new FlowLayout());
		add(box);
		add(bt);
		add(la_msg);
		
		// button과 listener과의 연결
		// Listener 구현체를 내부익명클래스로 구현하면 그나마 코드량을 줄일 수 있지만,
		// 여전히 다른 언어등에 비해 이벤트 연결코드 거창하다. 
		// 위 문제를 해결하려면 함수형 프로그래밍 방법인 익명함수로 처리. java에서는 람아(Lambda)라함
		// js의 화살표 함수와 거의 비슷
		bt.addActionListener((e)->{
			//System.out.println("press me?");
			
			// 모델 객체를 이용하여 결과 출력
			String item = (String)box.getSelectedItem(); // 유저가 선택한 색상(Object)
			String msg = colorManager.getAdvice(item);
			
			la_msg.setText(msg);
			
		});
		
		// Style
		setSize(300, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args) {
		new ColorFrame();
		
	}

}
