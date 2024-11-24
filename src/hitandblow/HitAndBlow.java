package hitandblow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HitAndBlow {
	public static void main(String[] args) {

		System.out.println("Hit & Blow");
		Scanner scanner = new Scanner(System.in);

		List<Integer> availableNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		Integer size = getSizeFromConsole(scanner);

		// 数字生成
		// NOTE: 要素数が少ないためシャッフルで実装
		List<Integer> shuffled = new ArrayList<>(availableNumbers);
		Collections.shuffle(shuffled);
		List<Integer> choiceNumbers = shuffled.subList(0, size);

		boolean isLoop = true;
		while (isLoop) {

			// ユーザの入力取得
			List<Integer> inputNumbers = getNumberFromConsole(scanner, choiceNumbers.size());

			// 一致確認
			int hitCnt = 0;
			int blowCnt = 0;
			for (int i = 0; i < inputNumbers.size(); i++) {
				if (inputNumbers.get(i) == choiceNumbers.get(i)) {
					// 位置も一致している場合、Hit
					hitCnt++;
				} else if (choiceNumbers.contains(inputNumbers.get(i))) {
					// 位置は違うが含まれている場合、Blow
					blowCnt++;
				}
			}

			if (hitCnt == choiceNumbers.size()) {
				// Hitの数が選択された数字の文字数と同じ場合、正解
				System.out.println("正解です。");
				String strNumbers = "";
				for (Integer n : choiceNumbers) {
					strNumbers += Integer.valueOf(n);
				}

				System.out.println(String.format("選ばれた数字は%sでした。", strNumbers));

				// 処理終了
				// NOTE: 暫定1ゲームで処理終了とする
				scanner.close();
				isLoop = false;
				break;
			}

			// Hit,Blowの数を出力
			System.out.println(String.format("Hit：%d\nBlow:%d", hitCnt, blowCnt));

		}

	}

	/**
	 * コンソールから、生成する桁数を取得
	 * 
	 * @param scanner: スキャナ
	 * @return: 入力された数字（Integer）
	 */
	public static Integer getSizeFromConsole(Scanner scanner) {
		Integer inputSize = 0;
		boolean isLoop = true;
		while (isLoop) {
			System.out.print("生成する数字の桁数を入力してください:");
			String inputText = scanner.nextLine();
			try {
				// 数字か判定
				inputSize = Integer.valueOf(inputText);

				if (inputSize >= 1 && inputSize <= 9) {
					// 指定文字数の数字の場合、IntegerのListにして返却
					isLoop = false;
					break;
				}
			} catch (Exception e) {
				// NOTE: 握りつぶす
			}

			System.out.println("正しい入力がされませんでした。\n以下条件を満たす文字を再度入力してください。");
			System.out.println("- '1'～'9'のの数字を1文字");
		}

		return inputSize;
	}

	/**
	 * コンソールから数字を取得
	 * 
	 * @param scanner: スキャナ
	 * @param size:    入力文受付字数
	 * @return: 入力された数字のリスト（List<Integer>）
	 */
	public static List<Integer> getNumberFromConsole(Scanner scanner, Integer size) {
		List<Integer> inputNumbers = new ArrayList<Integer>();
		boolean isLoop = true;
		while (isLoop) {
			System.out.print(String.format("%d桁の数字を入力してください:", size));
			String inputText = scanner.nextLine();
			try {
				// 数字か判定
				Integer.valueOf(inputText);

				if (inputText.length() == size) {
					// 指定文字数の数字の場合、IntegerのListにして返却
					for (String s : inputText.split("")) {
						inputNumbers.add(Integer.valueOf(s));
					}
					isLoop = false;
					break;
				}
			} catch (Exception e) {
				// NOTE: 握りつぶす
			}

			System.out.println("正しい入力がされませんでした。\n以下条件を満たす文字を再度入力してください。");
			System.out.println(String.format("- '1'～'9'で構成された%d桁の数字", size));
		}

		return inputNumbers;
	}
}
