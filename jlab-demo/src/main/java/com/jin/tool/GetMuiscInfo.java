package com.jin.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;

/**
 * 获取音乐信息
 * 
 * @author liguang.jin
 *
 */
public class GetMuiscInfo {
	// 读取mp3音频总时长
	public static void getAudioPlayTime(String mp3) throws Exception
			 {
		File file = new File(mp3);
		FileInputStream fis = new FileInputStream(file);
		int b = fis.available();
		Bitstream bt = new Bitstream(fis);
		Header h = bt.readFrame();
		int time = (int) h.total_ms(b);
		int i = time / 1000;
		System.out.println(i / 60 + ":" + i % 60);
	}

	public static void main(String[] args) {
		try {
			getAudioPlayTime("H:\\dowload\\11.mp3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
