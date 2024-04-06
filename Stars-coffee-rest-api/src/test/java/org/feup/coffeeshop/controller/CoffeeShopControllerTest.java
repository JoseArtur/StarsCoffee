package org.feup.coffeeshop.controller;

import org.feup.coffeeshop.base.BaseControllerTest;
import org.feup.coffeeshop.model.dto.CoffeeShopDto;
import org.feup.coffeeshop.model.request.OrderRequest;
import org.feup.coffeeshop.model.response.OrderDeleteResponse;
import org.feup.coffeeshop.model.response.OrderListResponse;
import org.feup.coffeeshop.service.CoffeeShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.fail;
import static org.feup.coffeeshop.builder.CustomerMockDataBuilder.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(value = CoffeeShopController.class)
class CoffeeShopControllerTest extends BaseControllerTest {

    private static final long CUSTOMER_ID = 1;
    private OrderListResponse orderListResponse;
    private OrderRequest orderRequest;
    private CoffeeShopDto coffeeShopDto;
    private OrderDeleteResponse orderDeleteResponse;

    @MockBean
    private CoffeeShopService coffeeShopService;

    @BeforeEach
    void setUp() {
        orderListResponse = generateCustomerListResponse(CUSTOMER_ID);
        orderRequest = generateCustomerRequest();
        coffeeShopDto = generateCustomerDto(CUSTOMER_ID);
        orderDeleteResponse = generateCustomerDeleteResponse();
        this.mockMvc = webAppContextSetup(wac)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    //TODO: write authorization tests for non-get requests
    @Test
    void getCustomer() {
        when(coffeeShopService.getCustomer(CUSTOMER_ID)).thenReturn(orderListResponse);
        try {
            mockMvc.perform(get("/customer/{id}", CUSTOMER_ID)
                            .with(httpBasic("user", "password"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).getCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void getCustomer_notFound() {
        when(coffeeShopService.getCustomer(CUSTOMER_ID)).thenReturn(new OrderListResponse());
        try {
            mockMvc.perform(get("/customer/{id}", CUSTOMER_ID)
                            .with(httpBasic("user", "password"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).getCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void getAllCustomers() {
        when(coffeeShopService.getAllCustomers()).thenReturn(orderListResponse);
        try {
            mockMvc.perform(get("/customers")
                            .with(httpBasic("user", "password"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).getAllCustomers();
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void createCustomer() {
        when(coffeeShopService.createCustomer(orderRequest)).thenReturn(coffeeShopDto);

        try {
            mockMvc.perform(post("/register").with(httpBasic("admin", "admin"))
                            .with(csrf())
                            .contentType(contentType)
                            .content(json(orderRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).createCustomer(orderRequest);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void updateCustomer() {
        when(coffeeShopService.updateCustomer(CUSTOMER_ID, orderRequest)).thenReturn(coffeeShopDto);
        try {
            mockMvc.perform(put("/customer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf())
                            .contentType(contentType)
                            .content(json(orderRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).updateCustomer(CUSTOMER_ID, orderRequest);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void updateCustomer_notFound() {
        when(coffeeShopService.updateCustomer(CUSTOMER_ID, orderRequest)).thenReturn(null);
        try {
            mockMvc.perform(put("/customer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf())
                            .contentType(contentType)
                            .content(json(orderRequest))
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).updateCustomer(CUSTOMER_ID, orderRequest);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void deleteCustomer() {
        when(coffeeShopService.deleteCustomer(CUSTOMER_ID)).thenReturn(orderDeleteResponse);
        try {
            mockMvc.perform(delete("/customer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).deleteCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void deleteCustomer_noContent() {
        when(coffeeShopService.deleteCustomer(CUSTOMER_ID)).thenReturn(OrderDeleteResponse.builder().deletedCustomerCount(0L).build());
        try {
            mockMvc.perform(delete("/customer/{id}", CUSTOMER_ID)
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).deleteCustomer(CUSTOMER_ID);
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void deleteAllCustomers() {
        when(coffeeShopService.deleteAllCustomers()).thenReturn(orderDeleteResponse);
        try {
            mockMvc.perform(delete("/customers")
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).deleteAllCustomers();
        verifyNoMoreInteractions(coffeeShopService);
    }

    @Test
    void deleteAllCustomers_noContent() {
        when(coffeeShopService.deleteAllCustomers()).thenReturn(OrderDeleteResponse.builder().deletedCustomerCount(0L).build());
        try {
            mockMvc.perform(delete("/customers")
                            .with(httpBasic("admin", "admin"))
                            .with(csrf()))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            fail(e);
        }

        verify(coffeeShopService, times(1)).deleteAllCustomers();
        verifyNoMoreInteractions(coffeeShopService);
    }
}




