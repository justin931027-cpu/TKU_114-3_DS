import java.util.Scanner;

public class PlaylistSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        while (true) {
            showMenu();
            System.out.print("請輸入選項：");
            String input = sc.nextLine().trim();

            int option;

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入數字。\n");
                continue;
            }

            switch (option) {
                case 1:
                    addSong(playlist, sc);
                    break;

                case 2:
                    searchSong(playlist, sc);
                    break;

                case 3:
                    removeSong(playlist, sc);
                    break;

                case 4:
                    playlist.printPlaylist();
                    break;

                case 0:
                    System.out.println("播放清單系統結束。");
                    sc.close();
                    return;

                default:
                    System.out.println("選項錯誤，請輸入 0 到 4。");
            }

            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("=== 播放清單系統 ===");
        System.out.println("1. 尾端新增歌曲");
        System.out.println("2. 依代碼搜尋歌曲");
        System.out.println("3. 刪除歌曲");
        System.out.println("4. 顯示完整播放清單");
        System.out.println("0. 結束程式");
    }

    public static void addSong(
            PlaylistLinkedList playlist,
            Scanner sc
    ) {
        System.out.print("請輸入歌曲代碼：");
        String code = sc.nextLine();

        System.out.print("請輸入歌曲名稱：");
        String songName = sc.nextLine();

        playlist.addLast(code, songName);
    }

    public static void searchSong(
            PlaylistLinkedList playlist,
            Scanner sc
    ) {
        System.out.print("請輸入要搜尋的歌曲代碼：");
        String code = sc.nextLine();

        playlist.searchSong(code);
    }

    public static void removeSong(
            PlaylistLinkedList playlist,
            Scanner sc
    ) {
        System.out.print("請輸入要刪除的歌曲代碼：");
        String code = sc.nextLine();

        playlist.removeByCode(code);
    }
}