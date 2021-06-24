package sections;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Wishlist extends Base {

    By wishListButton = By.xpath("//a[@href='/wishlist/']");
    By wishListInCardListButton = By.xpath("//span[text()='Wish List']");

    By wishListHeader = By.xpath("//h2[text()='Избранное']");

    public Wishlist(WebDriver driver) {
        super(driver);
    }

    public Wishlist clickToWishListButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(wishListButton));
        return this;
    }

    public Wishlist clickToWishListInCardListButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(wishListInCardListButton));
        return this;
    }

    public String getWishListHeader() {
        return driver.findElement(wishListHeader).getText();
    }

    public Wishlist clickToItemButton() {
        String firstItem = this.findFirstItem();
//        System.out.println(firstItem);
        driver.findElement(By.xpath("//a[text()=" + "'" + firstItem + "']")).click();
        return this;
    }

    //SQL
    public static String findFirstItem() {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(list.get(1));
        return list.get(0);
    }

    public static void main(String[] args) {
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT item_sku.name from item " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_sku ON item.id = item_sku.item_id " +
                "JOIN sku_picture_list ON item_sku.id = sku_picture_list.sku_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item_sku.id = sku_picture_list.sku_id and (tag_id = 1 or tag_id = 4)) " +
                "and is_archive = 0 and price != 0 and section = 'catalog' and subsection is null " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position" ;
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list.get(0));
        worker.getSession().disconnect();

    }
}
