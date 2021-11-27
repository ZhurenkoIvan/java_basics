import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import java.util.*;
import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> shops = database.getCollection("Shops");
        MongoCollection<Document> goods = database.getCollection("Goods");
        shops.drop();
        goods.drop();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.contains(" ")) {
                ifContainSpace(input, shops, goods);
            } else if (input.contains("СТАТИСТИКА_ТОВАРОВ")){
                getAllShops(shops);
            } else {
                System.out.println("Неверный ввод");
            }
        }
    }

    private static void ifContainSpace(String input, MongoCollection<Document> shops, MongoCollection<Document> goods) {
        String[] array = input.split(" ");
        if (array[0].contains("ВЫСТАВИТЬ_ТОВАР")) {
            addGoodToShop(shops, goods, array[1], array[2]);
            System.out.println("В магазин " + array[1] + " добавлен товар " + array[2]);
        } else if (array[0].contains("ДОБАВИТЬ_МАГАЗИН")) {
            createShop(shops, array[1]);
            System.out.println("Создан магазин " + array[1]);
        } else if (array[0].contains("ДОБАВИТЬ_ТОВАР")) {
            createGood(goods, array[1], Integer.parseInt(array[2]));
            System.out.println("Добавлен товар " + array[1] + " стоимостью " + array[2]);
        } else {
            System.out.println("Неверный ввод");
            return;
        }

    }


    private static void createShop(MongoCollection<Document> shops, String name) {
        ArrayList<Document> goods = new ArrayList<>();
        shops.insertOne(new Document().append("Name", name).append("Goods", asList()));
    }

    private static void createGood(MongoCollection<Document> goods, String name, int price) {
        goods.insertOne(new Document().append("Name", name).append("Price", price));
    }

    private static void addGoodToShop(MongoCollection<Document> shops, MongoCollection<Document> goods, String shopsName, String goodsName) {
        List<Document> goodsInShop = (List<Document>) shops.find(eq("Name", shopsName)).projection(fields(include("Goods"), excludeId())).map(document -> document.get("Goods")).first();
        Iterator<Document> goodsList = goods.find(eq("Name", goodsName)).iterator();
        Document good = goodsList.next();
        goodsInShop.add(good);
        Iterator<Document> shopList =  shops.find(eq("Name", shopsName)).iterator();
        Document shop = shopList.next();
        Bson update = Updates.set("Goods", goodsInShop);
        UpdateOptions options = new UpdateOptions().upsert(true);
        shops.updateOne(shop, update, options);
    }

    private static void getAllShops(MongoCollection<Document> shops) {
        for (Document shop : shops.find()) {
            List<Document> goodsInShop = (List<Document>) shops.find(shop).projection(fields(include("Goods"), excludeId())).map(document -> document.get("Goods")).first();
            int sum = 0;
            int less100Count = 0;
            TreeSet<Integer> sortedPrice = new TreeSet<>();
            if (goodsInShop != null) {
                for (Document good: goodsInShop) {
                    int price = (int) good.get("Price");
                    sortedPrice.add(price);
                    if (price < 100) {
                        less100Count++;
                    }
                    sum += price;
                }
            }
            System.out.println("Магазин: " + shop.get("Name"));
            System.out.println("Количество товаров в магазине: " + goodsInShop.size());
            System.out.println("Средняя цена товара: " + sum/goodsInShop.size());
            System.out.println("Минимальная стоимость товара: " + sortedPrice.first());
            System.out.println("Максимальная стоимость товара: " + sortedPrice.last());
            System.out.println("Количество товаров меньше 100 рублей: " + less100Count);
        }
    }
}
