package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {

	private ArrayList<Sphere> spheres;
	private ArrayList<Player> hallOfFame;
	private int nivel;
	private int bounces;

	public Game() {
		super();
		this.spheres = new ArrayList<Sphere>();
		this.hallOfFame = new ArrayList<Player>();
		this.nivel = 0;
		this.bounces = 0;
		readSerializableList();
	}

	public ArrayList<Sphere> getSpheres() {
		return spheres;
	}

	public void setSpheres(ArrayList<Sphere> spheres) {
		this.spheres = spheres;
	}

	public ArrayList<Player> getHallOfFame() {
		return hallOfFame;
	}

	public void setHallOfFame(ArrayList<Player> hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getBouncing() {
		return bounces;
	}

	public void setBouncing(int bouncing) {
		this.bounces = bouncing;
	}

	public boolean gameOver() {
		boolean answer = false;
		boolean exit = false;
		for (int i = 0; i < spheres.size() && exit == false; i++) {
			if (spheres.get(i).getClick() == false) {
				answer = false;
				exit = true;
			} else {
				answer = true;
			}
		}
		return answer;
	}

	public int finalScore() {
		return bounces;
	}

	public void saveGame() {
		try {
			PrintWriter writer = new PrintWriter(".\\LastGame");
			for (int i = 0; i < spheres.size(); i++) {
				writer.println(spheres.get(i).text());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Check the route please");
		}
	}
	
	public void readLevel(String path) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String listC[] = new String[6];
			String bfRead;
			while ((bfRead = br.readLine()) != null) {
				listC = bfRead.split(",");
				double radium = Double.parseDouble(listC[0]);
				double posX = Double.parseDouble(listC[1]);
				double posY = Double.parseDouble(listC[2]);
				int wait = Integer.parseInt(listC[3]);
				boolean click = Boolean.parseBoolean(listC[4]);
				char direction = listC[5].charAt(0);
				Sphere newSphere = new Sphere(radium, posX, posY, wait, click, direction);
				spheres.add(newSphere);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}

	public void addScore(int s, String n) {
		hallOfFame.add(new Player(s, n));
	}

	public void sort() {
		for (int i = 0; i < hallOfFame.size() - 1; i++) {
			for (int j = 0; j < hallOfFame.size() - 1; j++) {
				if (hallOfFame.get(j).getScore() > hallOfFame.get(j + 1).getScore()) {
					Player tmp = hallOfFame.get(j + 1);
					hallOfFame.set(j + 1, hallOfFame.get(j));
					hallOfFame.set(j, tmp);

				}
			}
		}
	}

	public void serializableList() {
		try {
			sort();
			FileOutputStream fs = new FileOutputStream(".\\Records");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(hallOfFame);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Player> readSerializableList() {
		ArrayList<Player> obj = null;

		try {

			File f = new File(".\\Records");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (ArrayList<Player>) ois.readObject();
			hallOfFame = obj;
			ois.close();
			fis.close();

		} catch (IOException | ClassNotFoundException ioe) {

		}
		return obj;
	}

	public String[] printHallOfFame() {
		String[] list = new String[hallOfFame.size()];
		boolean end = false;
		sort();
		int counter = 0;
		for (int i = 0; i < hallOfFame.size() && end == false; i++) {
			if (counter < 10) {
				list[i] = hallOfFame.get(i).getName() + "  " + hallOfFame.get(i).getScore();
				counter++;
			} else {
				end = true;
			}
		}
		return list;
	}

}
