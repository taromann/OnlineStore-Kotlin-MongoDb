package com.github.assemblathe1.kotlinstore.services;//package com.geekbrains.spring.web.services;
//
//import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
//import com.geekbrains.spring.web.repositories.OrdersRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//    private final OrdersRepository ordersRepository;
//    private final CartService cartService;
//    private final ProductsService productsService;
//
//    @Transactional
//    public void createOrder(User user, OrderDetailsDto orderDetailsDto) {
//        Old_Cart currentCart = cartService.getCurrentCart();
//        Old_Order order = new Old_Order();
//        order.setAddress(orderDetailsDto.getAddress());
//        order.setPhone(orderDetailsDto.getPhone());
//        order.setUser(user);
//        order.setTotalPrice(currentCart.getTotalPrice());
//        List<Old_OrderItem> items = currentCart.getItems().stream()
//                .map(o -> {
//                    Old_OrderItem item = new Old_OrderItem();
//                    item.setOrder(order);
//                    item.setQuantity(o.getQuantity());
//                    item.setPricePerProduct(o.getPricePerProduct());
//                    item.setPrice(o.getPrice());
//                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
//                    return item;
//                }).collect(Collectors.toList());
//        order.setItems(items);
//        ordersRepository.save(order);
//        currentCart.clear();
//    }
//
//    public List<Old_Order> findOrdersByUsername(String username) {
////       return ordersRepository.findAllByUsername(username);
//        return ordersRepository.findAllByUserUsername(username);
//    }
//}
