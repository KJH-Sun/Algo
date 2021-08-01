import java.io.IOException;

public class 자물쇠와열쇠 {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(solution(key, lock));
	}

	public static boolean solution(int[][] key, int[][] lock) {
		int keySize = key.length;
		int lockSize = lock.length;
		map = new int[lockSize * 3][lockSize * 3];
		boolean flag = false;
		// lock 크기 세배만큼 배열 선언하여 가운데에 lock 집어넣기
		for (int i = 0; i < lockSize; i++) {
			for (int j = 0; j < lockSize; j++) {
				map[lockSize + i][lockSize + j] = lock[i][j];
			}
		}

		outer: for (int r = 0; r < 4; r++) {
			key = rotate(key);
			for (int a = 0; a < lockSize * 2; a++) {
				for (int b = 0; b < lockSize * 2; b++) {
					for (int i = 0; i < keySize; i++) {
						for (int j = 0; j < keySize; j++) {
							map[a + i][b + j] += key[i][j];
						}
					}

					flag = true;
					loop: for (int i = 0; i < lockSize; i++) {
						for (int j = 0; j < lockSize; j++) {
							if (map[lockSize + i][lockSize + j] != 1) {
								flag = false;
								break loop;
							}
						}
					}
					if (flag) {
						break outer;
					}
					
					for (int i = 0; i < keySize; i++) {
						for (int j = 0; j < keySize; j++) {
							map[a + i][b + j] -= key[i][j];
						}
					}

				}
			}

		}

		return flag;
	}

	private static int[][] rotate(int[][] key) {
		int[][] tmp = new int[key.length][key.length];
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				tmp[i][j] = key[key.length - 1 - j][i];
			}
		}
		return tmp;
	}

}
