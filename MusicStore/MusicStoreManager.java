package MusicStore;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicStoreManager {
    private List<Album> albums;
    private List<Song> songs;
    private List<User> users;
    private List<Cart> cart;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public MusicStoreManager() {
        albums = new ArrayList<>();
        songs = new ArrayList<>();
        users = new ArrayList<>();
        cart = new ArrayList<>();
    }

    public boolean SaveUser(User user) {
        return users.add(user);
    }

    public Album findAlbum(String albumName) {
        Album album = albums.stream()
                .filter(a -> albumName.equalsIgnoreCase(a.getAlbumName()))
                .findAny()
                .orElse(null);
        return album;
    }

    public void findAlbum(Album album) {
        System.out.println("Albüm Adı: " + album.getAlbumName());
        System.out.println("Şarkıcı Adı: " + album.getSingerName());
        System.out.println("Çıkış Yılı: " + album.getReleaseDate());
        System.out.println("----- Albüm Şarkıarı -----");
        for (Song song : songs) {

            if (song.getAlbum().getAlbumName().equalsIgnoreCase(album.getAlbumName())) {
                System.out.println("\tŞarkı Adı: " + song.getSongName());
                System.out.println("\tSüresi: " + song.getLongOfSing());
                System.out.print("\n");

            }
        }
        System.out.println("-----------------------------------------");
    }

    public void PrintSongInfo() {
        System.out.println("------- Şarkı Listesi -------");
        for (Song song : songs) {
            System.out.println("Şarkı Adı: " + song.getSongName());
            System.out.println("\tŞarkıcı: " + song.getSingerName());
            System.out.println("\tAlbüm Adı: " + song.getAlbum().getAlbumName());
            System.out.println("\tSüresi: " + song.getLongOfSing());
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------");

    }

    public void PrintUserInfo() {
        System.out.println("------- Kullanıcı Listesi -------");
        for (User user : users) {
            System.out.println("Kullanıcı Adı: " + user.getUserName());
            System.out.println("\tAdı: " + user.getFirstName());
            System.out.println("\tSoyadı: " + user.getLastName());
            System.out.println("\tŞifresi: " + user.getPassword());
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------");
    }

    public void PrintAlbumInfo() {
        System.out.println("------- Albüm Listesi -------");
        for (Album album : albums) {
            System.out.println("Albüm Adı: " + album.getAlbumName());
            System.out.println("\tŞarkıcı: " + album.getSingerName());
            System.out.println("\tÇıkış Yılı: " + album.getReleaseDate());
            System.out.println("\tFiyat: " + NumberFormat.getCurrencyInstance(new Locale("tr", "TR"))
                    .format(album.getPrice()));
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------");
    }

    public boolean addCart(Album album) {
        Cart cart1 = new Cart();
        cart1.setUser(User.activeUser);
        cart1.setAlbum(album);
        cart1.setTotalPrice(album.getPrice());
        return cart.add(cart1);


    }
    public double buyCart(User user) {
        double totalPrice=0.0;
        for (Cart cart :cart ) {
            if(cart.getUser()==user)
            {
                System.out.println("Albüm Adı: " + cart.getAlbum().getAlbumName());
                System.out.println("\tŞarkıcı: " +cart.getAlbum().getSingerName());
                System.out.println("\tAlbüm Fiyatı: " + NumberFormat.getCurrencyInstance(new Locale("tr", "TR")).format(cart.getAlbum().getPrice()));
                totalPrice+=cart.getTotalPrice();
                System.out.print("\n");

            }
            else
            {
                System.out.println("Aktif kullanıcı bilgisi hatalı.");
                System.out.println("Lütfen tekrar Kullanıcı giişi yapınız.\n");
            }

        }
        System.out.println("\tAlışveriş Tutarı: " + NumberFormat.getCurrencyInstance(new Locale("tr", "TR")).format(totalPrice));
        System.out.println("-----------------------------------------");
        return totalPrice;
    }

    public User changeSession(String userName, String password) {
        User user1 = users.stream()
                .filter(a -> userName.equalsIgnoreCase(a.getUserName()))
                .filter(a -> password.equalsIgnoreCase(a.getPassword()))
                .findAny()
                .orElse(null);

        if (user1 != null) {
            User.activeUser = user1;
        }

        return user1;
    }


}
