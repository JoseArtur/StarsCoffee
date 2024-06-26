package org.feup.coffeeshop.service.impl;

import org.feup.coffeeshop.model.dto.FoodsDto;
import org.feup.coffeeshop.model.dto.PurchaseDto;
import org.feup.coffeeshop.model.entity.FoodsRequestEntity;
import org.feup.coffeeshop.model.entity.LoginRequestEntity;
import org.feup.coffeeshop.model.entity.PurchaseRequestEntity;
import org.feup.coffeeshop.model.request.PurchaseRequest;
import org.feup.coffeeshop.model.response.FoodsListResponse;
import org.feup.coffeeshop.repository.FoodsRepository;
import org.feup.coffeeshop.repository.CoffeeRepository;
import org.feup.coffeeshop.model.converter.StarsCoffeeConverter;
import org.feup.coffeeshop.model.dto.UserDto;
import org.feup.coffeeshop.model.dto.VoucherDto;
import org.feup.coffeeshop.model.entity.VoucherRequestEntity;
import org.feup.coffeeshop.model.request.VoucherRequest;
import org.feup.coffeeshop.repository.VoucherRepository;
import org.feup.coffeeshop.model.response.VoucherListResponse;
import org.feup.coffeeshop.model.entity.OrderRequestEntity;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.request.SummaryRequest;
import org.feup.coffeeshop.model.dto.SummaryDto;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.repository.LoginRepository;
import org.feup.coffeeshop.repository.PurchaseRepository;
import org.feup.coffeeshop.service.CoffeeShopService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.Integer.parseInt;

@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {

    private final CoffeeRepository repository;
    private final StarsCoffeeConverter starsCoffeeConverter;
    private final VoucherRepository voucherRepository;
    private final LoginRepository loginRepository;

    private final FoodsRepository foodsRepository;

    private final PurchaseRepository purchaseRepository;

    public CoffeeShopServiceImpl(CoffeeRepository repository, StarsCoffeeConverter starsCoffeeConverter, LoginRepository loginRepository,
                                 FoodsRepository foodsRepository, PurchaseRepository purchaseRepository, VoucherRepository voucherRepository) {
        this.repository = repository;
        this.starsCoffeeConverter = starsCoffeeConverter;
        this.loginRepository = loginRepository;
        this.foodsRepository = foodsRepository;
        this.purchaseRepository = purchaseRepository;
        this.voucherRepository = voucherRepository;
    }


    @Override
    public OrderListResponse getCustomer(Long id) {
        final OrderListResponse response = new OrderListResponse();
        return repository.findById(id)
                .map(entity -> OrderListResponse.builder().customers(Collections.singletonList(starsCoffeeConverter.toDto(entity))).build())
                .orElse(response);
    }

    @Override
    public OrderListResponse getCustomerByEmail(String email) {
        final OrderRequestEntity entities = repository.findByEmail(email);
        return OrderListResponse.builder().customers(Collections.singletonList(starsCoffeeConverter.toDto(entities))).build();
    }
    @Override
    public OrderListResponse getAllCustomers() {
        final List<OrderRequestEntity> entities = repository.findAll();

        final List<UserDto> converted = entities
                .stream()
                .map(starsCoffeeConverter::toDto)
                .collect(Collectors.toList());

        return OrderListResponse.builder().customers(converted).build();

    }

    @Override
    public VoucherListResponse getAllVouchers(String userId) {
        final List<VoucherRequestEntity> entities = voucherRepository.findByUserId(userId);
        // print here the entities
        System.out.println(entities);
        final List<VoucherDto> converted = entities
                .stream()
                .map(starsCoffeeConverter::toVoucherDto)
                .collect(Collectors.toList());
        System.out.println(converted);
        return VoucherListResponse.builder().vouchers(converted).build();

    }

    @Override
    public VoucherListResponse getVoucher(Long id) {
        final VoucherListResponse response = new VoucherListResponse();
        return voucherRepository.findById(id)
                .map(entity -> VoucherListResponse.builder().vouchers(Collections.singletonList(starsCoffeeConverter.toVoucherDto(entity))).build())
                .orElse(response);
    }

    @Override
    public VoucherListResponse addVoucher(VoucherRequest request) {
        final VoucherRequestEntity saved = voucherRepository.save(starsCoffeeConverter.toVoucherEntity(request));
        return VoucherListResponse.builder().vouchers(Collections.singletonList(starsCoffeeConverter.toVoucherDto(saved))).build();
    }

    @Override
    public UserDto createCustomer(OrderRequest request) {
        final OrderRequestEntity saved = repository.save(starsCoffeeConverter.toEntity(request));
        return starsCoffeeConverter.toDto(saved);
    }

    @Override
    public PurchaseDto createPurchase(PurchaseRequest request) {
        final PurchaseRequestEntity saved = purchaseRepository.save(starsCoffeeConverter.toPurchaseEntity(request));
        return starsCoffeeConverter.toPurchaseDto(saved);
    }

    @Override
    public UserDto login(OrderRequest request) {
        LoginRequestEntity response = loginRepository.findByEmailPassword(request.getEmail(), request.getPassword());

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return starsCoffeeConverter.toLoginDto(response);
    }

    @Override
public UserDto updateCustomer(Long id, OrderRequest request) {
    System.out.println("Updating customer with id: " + id);
    
    final Optional<OrderRequestEntity> optionalCustomerEntity = repository.findById(id);
    if (!optionalCustomerEntity.isPresent()) {
        System.out.println("Customer with id: " + id + " not found.");
        return null;
    } else {
        final OrderRequestEntity toBeUpdated = starsCoffeeConverter.toEntity(request);
        toBeUpdated.setId(optionalCustomerEntity.get().getId());
        final OrderRequestEntity saved = repository.save(toBeUpdated);
        System.out.println("Customer with id: " + id + " updated successfully.");
        return starsCoffeeConverter.toDto(saved);
    }
}

    @Override
    public OrderDeleteResponse deleteCustomer(Long id) {
        if (!repository.existsById(id)) {
            return OrderDeleteResponse.builder().deletedCustomerCount(0L).build();
        } else {
            repository.deleteById(id);
            return OrderDeleteResponse.builder().deletedCustomerCount(1L).build();
        }
    }

    @Override
    public OrderDeleteResponse deleteAllCustomers() {
        final long count = repository.count();
        if (count == 0) {
            return OrderDeleteResponse.builder().deletedCustomerCount(0L).build();
        } else {
            repository.deleteAll();
            return OrderDeleteResponse.builder().deletedCustomerCount(count).build();
        }
    }

    @Override
    public FoodsListResponse getFoods() {
        final List<FoodsRequestEntity> entities = foodsRepository.findAll();

        final List<FoodsDto> converted = entities
                .stream()
                .map(starsCoffeeConverter::toFoodsDto)
                .collect(Collectors.toList());

        return FoodsListResponse.builder().foods(converted).build();
    }
    
@Override
public OrderListResponse validateOrder(SummaryRequest request) {

    final OrderRequestEntity optionalCustomerEntity = repository.findByEmail(request.getUserEmail());
    final OrderRequestEntity entities = repository.findByEmail(request.getUserEmail());

    if (optionalCustomerEntity == null) {
        System.out.println("Customer with email: " + request.getUserEmail() + " not found.");
        return null;
    } else {
        System.out.println("Customer with email: " + request.getUserEmail() + " found.");
        Long userPoints = optionalCustomerEntity.getPoints();
        int sumOfVoucherPoints = 0;

        for (VoucherRequest voucher : request.getVouchersUsed()) {
            sumOfVoucherPoints += parseInt(voucher.getPointsRequired());
            System.out.println("Voucher points required: " + voucher.getPointsRequired());
        }

        System.out.println("User points: " + userPoints);
        System.out.println("Sum of voucher points: " + sumOfVoucherPoints);

        if (sumOfVoucherPoints > userPoints) {
            System.out.println("Insufficient points for the vouchers.");
            return null;
        } else {
            System.out.println("Sufficient points for the vouchers.");
            // if the user has sufficient points for the vouchers, then deduct the points from the user
            optionalCustomerEntity.setPoints(userPoints - sumOfVoucherPoints);
            final OrderRequestEntity saved = repository.save(optionalCustomerEntity);
            return OrderListResponse.builder().customers(Collections.singletonList(starsCoffeeConverter.toDto(entities))).build();
        }
    }
}

}

