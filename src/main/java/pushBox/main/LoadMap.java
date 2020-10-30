package pushBox.main;

import java.io.*;

public class LoadMap {
	private int[][] map = new int[20][20];
	private int level;
	private int manx,many;
	
	public int getManx() {
		return manx;
	}

	public int getMany() {
		return many;
	}

	public int[][] getMap() {
		return map;
	}

	public LoadMap() {
		// TODO Auto-generated constructor stub
	}

	public LoadMap(int level) {
		// TODO Auto-generated constructor stub
		this.level = level;

		File file = new File("src/main/java/pushBox/maps/" + this.level + ".map");

		FileReader fr = null;
		BufferedReader brf;
		try {
			fr = new FileReader(file);
			brf = new BufferedReader(fr);
			for (int i = 0; i < 20; i++) {

				String line = brf.readLine();

				for (int j = 0; j < 20; j++) {
					byte[] temp = line.getBytes();
					map [i][j] = temp[j]-48;
					if(map[i][j]==5||map[i][j]==6||map[i][j]==7||map[i][j]==8){
						this.manx = i;
						this.many = j;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fr!=null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
