//package com.pomodoro;

//import java.awt.*;
//import javax.swing.*;

import javax.swing.JLabel;
import java.awt.Font;

// use for 1sec loop 
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//


import java.lang.Integer;
import java.lang.String;

import javax.swing.JFrame;



public class BtTimer extends JLabel{

	private Integer int_minute, int_second;
	// Pomodoro - minutes
	private Integer default_timer_size = 45; // mins
	public Timer timer;
	private Boolean is_running = false;
	private MainFrame top_frame;
//	private String default_page_title;
//	private String str_minute, str_second;

	public BtTimer(MainFrame frame){

		// This will enable us to access frame object , for example to change title of JFrame...
		top_frame = frame;
//		default_page_title = top_frame.getTitle();

		int_minute = default_timer_size;
		int_second = 0;

		draw_timer();
		timer = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				if(is_running){
					update_number();
					draw_timer();
					sync_frame_title();
				}
			}	
		});
		timer.start(); 
		setFont(new Font(null, Font.BOLD, 38));
	}

	private Integer getTimerSize(){
		return top_frame.getTimerSize() > 0 ? top_frame.getTimerSize() : default_timer_size;
	}

	private void update_number(){
		if(int_second > 0 || int_minute > 0){
	    	int_second--;
			if(int_second < 0){
				int_second = 59;
				int_minute--;
			}
		} else {

			// We came to 00:00 so:
			//    1. Let's change title of window (JFrame)
			top_frame.setTitle("** STOP WORK **");
			//    2. Let's restart put number to starting minute, draw and set is_running to false:
			restart();
		}
	}

	private void draw_timer(){
		String str_minute = Integer.toString(int_minute);
		String str_second = Integer.toString(int_second);
//		echo("Min="+str_minute + " sec = " + str_second);

		if(str_minute.length() < 2){
			str_minute = "0" + str_minute;
		}

		if(str_second.length() < 2){
			str_second = "0" + str_second;
		}

//		echo("Min="+str_minute + " sec = " + str_second);

		String fin = str_minute + ":" + str_second;
//		echo("Set text ("+fin+") called");
		setText(fin);
	}

	public void restart(){
		
		if(is_running == false){
			is_running = true;
		} else {
			// int_minute = default_timer_size;
			int_minute = getTimerSize();
			int_second = 0;
			draw_timer();
			is_running = false;
		}
	} 

	// public void set_timer_size(Integer mins){
	// 	default_timer_size = mins; // set default timer which is being used while restarting...

	// 	// Reset current timer values
	// 	int_minute = mins;
	// 	int_second = 0;

	// 	is_running = false; // stop timer
	// 	draw_timer(); // draw timer again
	// }

	/**
	* Make title of frame equal to timer values
	**/
	private void sync_frame_title(){
		String str_minute = Integer.toString(int_minute);
		String str_second = Integer.toString(int_second);

		if(str_minute.length() < 2){
			str_minute = "0" + str_minute;
		}

		if(str_second.length() < 2){
			str_second = "0" + str_second;
		}

		String v_title = str_minute + ":" + str_second;
		top_frame.setTitle(v_title);
	}

	private void echo(String txt){
		System.out.println(txt);
	}
}