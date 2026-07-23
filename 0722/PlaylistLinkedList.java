public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        head = null;
    }

    public void addLast(String code, String songName) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("歌曲代碼不可空白。");
            return;
        }

        if (songName == null || songName.trim().isEmpty()) {
            System.out.println("歌曲名稱不可空白。");
            return;
        }

        if (findByCode(code) != null) {
            System.out.println("新增失敗，歌曲代碼不可重複。");
            return;
        }

        PlaylistNode newNode = new PlaylistNode(
                code.trim(),
                songName.trim()
        );

        if (head == null) {
            head = newNode;
            System.out.println("歌曲新增成功。");
            return;
        }

        PlaylistNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        System.out.println("歌曲新增成功。");
    }

    public PlaylistNode findByCode(String code) {
        if (code == null) {
            return null;
        }

        PlaylistNode current = head;

        while (current != null) {
            if (current.code.equalsIgnoreCase(code.trim())) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public void searchSong(String code) {
        PlaylistNode song = findByCode(code);

        if (song == null) {
            System.out.println("找不到歌曲。");
        } else {
            System.out.println(song);
        }
    }

    public void removeByCode(String code) {
        if (head == null) {
            System.out.println("播放清單是空的，無法刪除。");
            return;
        }

        if (code == null || code.trim().isEmpty()) {
            System.out.println("歌曲代碼不可空白。");
            return;
        }

        if (head.code.equalsIgnoreCase(code.trim())) {
            System.out.println("刪除成功：" + head.songName);
            head = head.next;
            return;
        }

        PlaylistNode current = head;

        while (current.next != null) {
            if (current.next.code.equalsIgnoreCase(code.trim())) {
                System.out.println(
                        "刪除成功：" + current.next.songName
                );

                current.next = current.next.next;
                return;
            }

            current = current.next;
        }

        System.out.println("刪除失敗，找不到歌曲。");
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單是空的。");
            return;
        }

        System.out.println("=== 完整播放清單 ===");

        PlaylistNode current = head;
        int number = 1;

        while (current != null) {
            System.out.println(number + ". " + current);
            current = current.next;
            number++;
        }
    }
}