import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.mongodb.MongoClient;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> shops = database.getCollection("Shops");
        MongoCollection<Document> goods = database.getCollection("Goods");

        createShop(shops, "Magnit");
        createShop(shops, "Lenta");
        createGood(goods, "Vafli", 54);
        createGood(goods, "Hleb", 100);
        createGood(goods, "Cheese", 400);

        addGoodToShop(shops, goods, "Magnit", "Hleb");
        addGoodToShop(shops, goods, "Magnit", "Cheese");
        addGoodToShop(shops, goods, "Magnit", "Vafli");

        addGoodToShop(shops, goods, "Lenta", "Hleb");

        getAllGoodsInShop(shops, "Magnit");
        getAllGoodsInShop(shops, "Lenta");



        // Создаем коллекцию


    }

    private static void createShop(MongoCollection<Document> shops, String name) {
        ArrayList<Document> goods = new ArrayList<>();
        shops.insertOne(new Document().append("Name", name).append("Goods", goods));
    }

    private static void createGood(MongoCollection<Document> goods, String name, int price) {
        goods.insertOne(new Document().append("Name", name).append("Price", price));
    }

    private static void addGoodToShop(MongoCollection<Document> shops, MongoCollection<Document> goods, String shopsName, String goodsName) {
        List<Document> shopList = (List<Document>) shops.find(eq("Name", shopsName));
        Document shop = shopList.get(0);
        ArrayList<Document> goodsInShop = (ArrayList<Document>) shop.get("Goods");
        goodsInShop.add((Document) shops.find(eq("Name", goodsName)));
        shops.findOneAndUpdate(eq("Name", shopsName), new Document().append("Name", shopsName).append("Goods", goodsInShop));
    }

    private static void getAllGoodsInShop( MongoCollection<Document> shops, String shop) {
        System.out.println(shops.find(eq("Name", shop)));
    }
}
