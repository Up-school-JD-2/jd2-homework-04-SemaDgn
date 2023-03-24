import MusicStore.Album;
import MusicStore.MusicStoreManager;
import MusicStore.Song;
import MusicStore.User;

import javax.swing.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MusicStoreManager musicStoreManager = new MusicStoreManager();

        Album album1 = new Album("Işıkları Söndürseler Bile", "Manga", Year.of(2014), 150.0);
        musicStoreManager.getAlbums().add(album1);
        Album album2 = new Album("Yalnızlığın Çaresini Bulmuşlar", "Gripin", Year.of(2012), 120.0);
        musicStoreManager.getAlbums().add(album2);
        Album album3 = new Album("Kağıt Evler", "Emre Aydın", Year.of(2010), 200.0);
        musicStoreManager.getAlbums().add(album3);

        Song song1 = new Song("Hint Kumaşı", "Manga", album1, 3.54);
        Song song2 = new Song("Bir Varmış Bir Yokmuş", "Manga", album1, 4.02);
        Song song3 = new Song("Gül Güzeli", "Gripin", album2, 4.14);
        Song song4 = new Song("Neden Bu Elveda", "Gripin", album2, 5.02);
        Song song5 = new Song("Son Defa", "Emre Aydın", album3, 3.44);
        Song song6 = new Song("Hoşçakal", "Emre Aydın", album3, 4.30);


        musicStoreManager.getSongs().add(song1);
        musicStoreManager.getSongs().add(song2);
        musicStoreManager.getSongs().add(song3);
        musicStoreManager.getSongs().add(song4);
        musicStoreManager.getSongs().add(song5);
        musicStoreManager.getSongs().add(song6);
        int choice = 0;
        do {
            System.out.println("************** MUSİC STORE ************** ");
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Lütfen Yapmak İstediğiniz İşlemi seçiniz. ");
            System.out.println("1---> Kullanıcı Oluşturmak ");
            System.out.println("2---> Kullanıcı Girişi");
            System.out.println("3---> Albüm Aramak ");
            System.out.println("4---> Sepete öğe eklemek ");
            System.out.println("5---> Satın Almak ");
            System.out.println("6---> Kullanıcı Listesi ");
            System.out.println("7---> Albüm Listesi ");
            System.out.println("8---> Şarkı Listesi ");
            System.out.println("9---> Çıkış ");
            System.out.print("Seçiminiz---> ");

            choice = scanner.nextInt();
            if (choice == 1) {

                System.out.print("Adınız: ");
                String firstName = scanner.next();
                System.out.print("Soyadınız: ");
                String lastName = scanner.next();
                System.out.print("Kullanıcı Adınız: ");
                String userName = scanner.next();
                System.out.print("Şifreniz: ");
                String password = scanner.next();
                User user = new User(firstName, lastName, userName, password);
                String message = (musicStoreManager.SaveUser(user)) ? "*** Kullanıcı kaydedildi. ***" : "*** Kullanıcı Kaydedilemedi. ***";
                System.out.println(message+"\n");

            }
            else if (choice == 2) {

                System.out.println("*********** KULLANICI GİRİŞİ *********** ");
                System.out.print("Kullanıcı Adınız: ");
                String userName = scanner.next();
                System.out.print("Şifreniz: ");
                String password = scanner.next();
                User user=musicStoreManager.changeSession(userName,password);
                if(user!=null && User.activeUser!=null)
                {
                    System.out.println("*** HoşGeldiniz: "+User.activeUser.getUserName()+" ***\n");
                }
                else
                {
                    System.out.println("*** Girdiğiniz kullanıcı bulunamadı. ***\n");
                }
            }
            else if (choice == 3) {
                System.out.print("Albüm Adı:");
                String findAlbum = scanner.next();

                Album albm = musicStoreManager.findAlbum(findAlbum);

                if (albm != null) {
                    System.out.println("*********** ARADIĞINIZ ALBÜM BULUNDU *********** ");
                    musicStoreManager.findAlbum(albm);
                } else
                    System.out.println("*** Girdiğiniz albüm bulunamadı. ***\n");

            }
            else if (choice == 4) {
                //sepet öğe eklmek
                if(User.activeUser!=null) {
                    System.out.println("*********** SEPET *********** ");
                    System.out.println("Lütfen sepete eklemek istediğiniz albümü seçiniz: ");
                    musicStoreManager.PrintAlbumInfo();
                    System.out.print("Albüm Adı:");
                    String findAlbum = scanner.next();
                    Album albm = musicStoreManager.findAlbum(findAlbum);

                    if (albm != null && User.activeUser != null) {
                        if (musicStoreManager.addCart(albm)) {
                            System.out.println("*** Albüm başarıyla sepetinize eklendi. ***\n");
                        }

                    } else {
                        System.out.println("*** Girdiğiniz albüm eklenemedi. ***");
                        System.out.println("*** Kullanıcı bilgilerinizi yada albüm ismini kontrol ederek tekrar deneyin. ***\n");
                    }
                }
                else
                {
                    System.out.println("*** Lütfen kullanıcı girişi yapınız. ***\n");
                }
            }
            else if (choice == 5) {
                System.out.println("*********** ALIŞVERİŞ TOPLAMI *********** ");
               double totalPrice= musicStoreManager.buyCart(User.activeUser);
                if(User.activeUser!=null ) {
                    if ( totalPrice!=0) {
                        System.out.println("Alışverişinizi tamamlamak istiyor musunuz ? E/H");
                        String result = scanner.next();
                        if (result.toString().equalsIgnoreCase("E")) {
                            musicStoreManager.setCart(new ArrayList<>());
                            System.out.println("*** Siparişiniz oluşturuldu. ***\n");
                        }
                    }
                }
                else
                {
                    System.out.println("*** Lütfen kullanıcı girişi yapınız. ***\n");
                }
            }

            else if (choice == 6) {

                musicStoreManager.PrintUserInfo();

            }
            else if (choice == 7) {

                musicStoreManager.PrintAlbumInfo();

            }
            else if (choice == 8) {
                musicStoreManager.PrintSongInfo();
            }
        } while (choice != 9);
        System.exit(0);

    }


}
