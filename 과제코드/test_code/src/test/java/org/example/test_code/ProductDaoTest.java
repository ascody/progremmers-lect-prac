package org.example.test_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(AppConfig.class)
public class ProductDaoTest {
    @Autowired private ProductDao dao;

    @BeforeEach
    public void setup() { dao.deleteAll(); }

    @Test
    void add() {
        assertEquals(0, dao.getCount());
        Product product = new Product("p1", "연필", 500);

        dao.add(product);
        assertEquals(1, dao.getCount());
    }

    @Test
    void get() {
        Product product = new Product("p1", "연필", 500);
        dao.add(product);

        Product found =  dao.get(product.getId());
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getPrice(), found.getPrice());
    }

    @Test
    void add_중복_id_예외() {
        final Product product = new Product("dup", "지우개", 300);
        dao.add(product);

        Executable action = new Executable() {
            @Override
            public void execute() throws Throwable {
                dao.add(product);
            }
        };
        assertThrows(IllegalStateException.class, action);
    }

    @Test
    void get_없는_id_예외() {
        Executable action = new Executable() {
            @Override
            public void execute() {
                dao.get("없는_id");             // 행 없음 → 예외!
            }
        };
        assertThrows(NoSuchElementException.class, action);
    }

    @Disabled
    @Test
    void 일부러_실패하는_테스트() {
        dao.add(new Product("fail_demo", "공책", 1000));
        assertEquals(2, dao.getCount());
    }


}
