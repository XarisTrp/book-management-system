package com.nyc.bookmanagement.repos;

import com.nyc.bookmanagement.model.Order;
import com.nyc.bookmanagement.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    public List<Order> findByLibrary(Library library);

//    select sum(p.quantity*m.bookprice)
//    from libraryorderitem p, book m
//    where p.bookid =m.bookid
//    and orderid in (
//            select orderid
//from libraryorder p
//        where p.libraryid ='1')
//@Query(name="select sum(p.quantity*m.boookprice)\n" +
//        "from OrderItem p\n" +
//        "join p.book m \n" +
//        "where p.order in (\n" +
//        "select o\n" +
//        "from Order o\n" +
//        "where o.library =?1)")

    @Query(value = """
            select sum(p.quantity*m.bookprice)
                    from OrderItem p
                    join p.book m
                    join p.order order
                    where order.library = ?1""")
    public Float costTotal(Library library);
}
