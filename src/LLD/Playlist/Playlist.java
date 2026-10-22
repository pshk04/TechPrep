package LLD.Playlist;

import java.util.*;

public class Playlist {

    private Map<Integer, Song> songsMap;

    public Playlist() {
        this.songsMap = new HashMap<>();
    }

    public void add(int songId) {

        if(!this.songsMap.containsKey(songId)){
            Song song= new Song(songId);
            this.songsMap.put(songId, song);
        }
    }

    public void remove(int songId) {
        if(this.songsMap.containsKey(songId)){
            this.songsMap.remove(songId);
        }
    }

    public List<Integer> shuffle() {

        List<Integer> songsList = new ArrayList<>(this.songsMap.keySet());
        Collections.sort(songsList);
        return songsList;
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        playlist.add(1);
        playlist.add(2);
        playlist.add(3);
        System.out.println(playlist.shuffle());
        playlist.remove(2);
        System.out.println(playlist.shuffle());
        playlist.add(2);
        System.out.println(playlist.shuffle());

        System.out.println();

        playlist = new Playlist();

        playlist.add(5);
        playlist.add(5);
        playlist.remove(5);
        System.out.println(playlist.shuffle());
        playlist.remove(5);
        System.out.println(playlist.shuffle());
    }
}
