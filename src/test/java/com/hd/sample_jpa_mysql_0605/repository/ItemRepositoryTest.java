package com.hd.sample_jpa_mysql_0605.repository;

import com.hd.sample_jpa_mysql_0605.constant.ItemSellStatus;
import com.hd.sample_jpa_mysql_0605.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository; // 필드 의존성 주입을 시행완료

    @Test
    @DisplayName("상품 저장 테스트")
    public void createTest(){
        Item item = new Item();
        item.setItemNm("테스트상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);
        log.debug("Item : {}",savedItem);
    }

    public void createItemList(){
        for (int i = 1; i < 10; i++) {
            Item item = new Item();
        item.setItemNm("테스트상품" + i);
        item.setPrice(10000 + i);
        item.setItemDetail("테스트 상품 설명" + i);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        Item savedItem = itemRepository.save(item); // insert, update
        log.debug("Item : {}",savedItem);

        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> list = itemRepository.findByItemNm("테스트상품5");
        for (Item item : list){
            log.debug("Item : {}", item);
        }
    }

    @Test
    @DisplayName("상품 or 제품 상세 설명")
    public void findByItemNmOrItemDetail(){
        this.createItemList();
        List<Item> list = itemRepository.findByItemNmOrItemDetail("테스트상품1","테스트 상품 설명5");
        for (Item item : list){
            log.debug("Item : {}", item);
        }
    }

    @Test
    @DisplayName("JPQL 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> list = itemRepository.findByItemDetail("테스트 상품 설명4");
         for (Item item : list){
            log.debug("Item : {}", item);
        }
    }

    @Test
    @DisplayName("Native SQL 테스트")
    public void findByItemDetailByNative(){
        this.createItemList();
        List<Item> list = itemRepository.findByItemDetailByNative("테스트 상품 설명8");
        for (Item item : list){
            log.debug("Item : {}", item);
        }
    }
}