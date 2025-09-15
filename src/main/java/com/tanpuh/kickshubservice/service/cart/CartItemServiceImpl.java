package com.tanpuh.kickshubservice.service.cart;

import com.tanpuh.kickshubservice.dto.request.CartItemRequest;
import com.tanpuh.kickshubservice.dto.response.CartItemResponse;
import com.tanpuh.kickshubservice.dto.response.CartResponse;
import com.tanpuh.kickshubservice.entity.Cart;
import com.tanpuh.kickshubservice.entity.CartItem;
import com.tanpuh.kickshubservice.entity.ProductDetail;
import com.tanpuh.kickshubservice.entity.User;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.CartItemMapper;
import com.tanpuh.kickshubservice.mapper.CartMapper;
import com.tanpuh.kickshubservice.repository.CartItemRepository;
import com.tanpuh.kickshubservice.repository.CartRepository;
import com.tanpuh.kickshubservice.repository.ProductDetailRepository;
import com.tanpuh.kickshubservice.repository.UserRepository;
import com.tanpuh.kickshubservice.utils.AuthenticationUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemServiceImpl implements CartItemService {
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    ProductDetailRepository productDetailRepository;
    CartItemMapper cartItemMapper;
    CartMapper cartMapper;
    UserRepository userRepository;

    @Override
    public CartResponse getCartByUsername() {
        String username = AuthenticationUtils.extractUserFromJwt();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Cart cart = cartRepository.findByUser(user);

        List<CartItemResponse> cartItemResponses = cartItemRepository.findAllByCartId(cart.getId())
                .stream()
                .map(cartItemMapper::toResponse)
                .peek(r -> r.setCartId(cart.getId()))
                .toList();

        CartResponse cartResponse = cartMapper.toResponse(cart);
        cartResponse.setCartItems(cartItemResponses);

        return cartResponse;
    }

    @Override
    @Transactional
    public CartItemResponse addToCart(CartItemRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        ProductDetail productDetail = productDetailRepository.findById(request.getProductDetailId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_NOT_FOUND));

        CartItem cartItem = performAddToCart(request, cart, productDetail);

        CartItemResponse cartItemResponse = cartItemMapper.toResponse(cartItem);
        cartItemResponse.setCartId(cart.getId());

        return cartItemResponse;
    }

    @Override
    @Transactional
    public CartItemResponse updateQuantity(Integer id, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        checkStockQuantity(cartItem.getProductDetail().getStockQuantity(), quantity);

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);

        Cart cart = cartItem.getCart();
        cart.setTotalQuantity(cart.getTotalQuantity() + quantity);
        cartRepository.save(cart);

        return cartItemMapper.toResponse(cartItem);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        Cart cart = cartItem.getCart();
        cart.setTotalQuantity(cart.getTotalQuantity() - cartItem.getQuantity());
        cartRepository.save(cart);

        cartItemRepository.deleteById(id);
    }

    private CartItem performAddToCart(CartItemRequest request, Cart cart, ProductDetail productDetail) {
        return cartItemRepository
                .findByCartIdAndProductDetailId(request.getCartId(), productDetail.getId())
                .map(existingCartItem -> updateExistingCartItem(request, cart, productDetail, existingCartItem))
                .orElseGet(() -> createNewCartItem(request, cart, productDetail));

    }

    private CartItem createNewCartItem(CartItemRequest request, Cart cart, ProductDetail productDetail) {
        Integer newCartItemQuantity = request.getQuantity();
        checkStockQuantity(productDetail.getStockQuantity(), newCartItemQuantity);

        cart.setTotalQuantity(cart.getTotalQuantity() + newCartItemQuantity);
        cartRepository.save(cart);

        CartItem cartItem = CartItem.builder()
                .quantity(newCartItemQuantity)
                .productDetail(productDetail)
                .cart(cart)
                .build();
        cartItemRepository.save(cartItem);

        return cartItem;
    }

    private CartItem updateExistingCartItem(CartItemRequest request, Cart cart,
                                            ProductDetail productDetail, CartItem existingCartItem) {
        Integer newCartItemQuantity = existingCartItem.getQuantity() + request.getQuantity();
        checkStockQuantity(productDetail.getStockQuantity(), newCartItemQuantity);

        cart.setTotalQuantity(cart.getTotalQuantity() + request.getQuantity());
        cartRepository.save(cart);

        existingCartItem.setQuantity(newCartItemQuantity);
        cartItemRepository.save(existingCartItem);

        return existingCartItem;
    }

    private void checkStockQuantity(Integer stockQuantity, Integer newQuantity) {
        if (stockQuantity < newQuantity)
            throw new AppException(ErrorCode.PRODUCT_DETAIL_STOCK_QUANTITY_INSUFFICIENT);
    }

}
