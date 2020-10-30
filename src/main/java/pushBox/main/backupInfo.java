package pushBox.main;

public class backupInfo {
	private int [][] backupmap;
	private int manx;
	private int many;
	private int lastimg;
	public int[][] getBackupmap() {
		return backupmap;
	}
	public void setBackupmap(int[][] backupmap) {
		this.backupmap = backupmap;
	}
	public int getManx() {
		return manx;
	}
	public void setManx(int manx) {
		this.manx = manx;
	}
	public int getMany() {
		return many;
	}
	public void setMany(int many) {
		this.many = many;
	}
	public int getLastimg() {
		return lastimg;
	}
	public void setLastimg(int lastimg) {
		this.lastimg = lastimg;
	}
	public backupInfo(int[][] backupmap, int manx, int many, int lastimg) {
		super();
		this.backupmap = backupmap;
		this.manx = manx;
		this.many = many;
		this.lastimg = lastimg;
	}
	public backupInfo() {
		super();
	}
	
}
