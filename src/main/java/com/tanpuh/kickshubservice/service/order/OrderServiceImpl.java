package com.tanpuh.kickshubservice.service.order;

import com.tanpuh.kickshubservice.dto.request.OrderRequest;
import com.tanpuh.kickshubservice.dto.response.OrderItemResponse;
import com.tanpuh.kickshubservice.dto.response.OrderResponse;
import com.tanpuh.kickshubservice.entity.*;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.OrderItemMapper;
import com.tanpuh.kickshubservice.mapper.OrderMapper;
import com.tanpuh.kickshubservice.repository.*;
import com.tanpuh.kickshubservice.utils.AuthenticationUtils;
import com.tanpuh.kickshubservice.utils.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    UserRepository userRepository;
    CartRepository cartRepository;
    ProductDetailRepository productDetailRepository;
    ProductRepository productRepository;
    OrderMapper orderMapper;
    OrderItemMapper orderItemMapper;
    CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        String username = AuthenticationUtils.extractUserFromJwt();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        // map request to entity
        Order order = orderMapper.toEntity(request);
        order = order.toBuilder()
                .userId(user.getId())
                .status(OrderStatus.PENDING.getName())
                .deliveryFee(50000L)
                .createdAt(LocalDateTime.now())
                .build();
        orderRepository.save(order);

        // map cart item to order item, and calc total amount
        long totalAmount = 0L;
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cart.getId());
        List<OrderItem> orderItemList = new ArrayList<>();
        List<ProductDetail> productDetailList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = mapCartItemToOrderItem(cartItem);
            orderItem.setOrder(order);
            orderItemList.add(orderItem);

            totalAmount += orderItem.getSubtotal();

            ProductDetail productDetail = cartItem.getProductDetail();
            productDetail.setStockQuantity(productDetail.getStockQuantity() - orderItem.getQuantity());
            productDetail.setSoldQuantity(orderItem.getQuantity());
            productDetailList.add(productDetail);

            Product product = productDetail.getProduct();
            product.setStockQuantity(product.getStockQuantity() - orderItem.getQuantity());
            product.setSoldQuantity(orderItem.getQuantity());
            productList.add(product);
        }
        orderItemRepository.saveAll(orderItemList);
        productDetailRepository.saveAll(productDetailList);
        productRepository.saveAll(productList);

        cart.setTotalQuantity(0);
        cartRepository.save(cart);
        cartItemRepository.deleteAll(cartItems);

        // update total amount and list of order items to order
        order = order.toBuilder()
                .totalAmount(totalAmount)
                .build();
        orderRepository.save(order);

        // map order item to order item response
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            OrderItemResponse orderItemResponse = orderItemMapper.toResponse(orderItem);
            orderItemResponse.setOrderId(order.getId());
            orderItemResponses.add(orderItemResponse);
        }

        // map order to order response
        OrderResponse orderResponse = orderMapper.toResponse(order);
        orderResponse.setOrderItems(orderItemResponses);

        return orderResponse;
    }

    private OrderItem mapCartItemToOrderItem(CartItem cartItem) {
        return OrderItem.builder()
                .productDetailId(cartItem.getProductDetail().getId())
                .price(cartItem.getProductDetail().getPrice())
                .productDetailName(cartItem.getProductDetail().getName())
                .quantity(cartItem.getQuantity())
                .subtotal(cartItem.getQuantity() * cartItem.getProductDetail().getPrice())
                .build();
    }
}
