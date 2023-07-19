package duongddtph24297.fpoly.assignment;

public class Product {
    private int id;

    private String name;
    private int price;
     String img;
    private int soluong;
    private String loai;

    public Product() {
    }

    public Product(int id, String name, int price, String img, int soluong, String loai) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.soluong = soluong;
        this.loai = loai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
